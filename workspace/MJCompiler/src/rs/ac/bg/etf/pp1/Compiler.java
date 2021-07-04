package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.event.PopupMenuListener;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.test.CompilerError;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;

public class Compiler implements rs.ac.bg.etf.pp1.test.Compiler {

	public static List<CompilerError> allErrors = new ArrayList<CompilerError>();

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}

	public static boolean compiled = false;
	public static Program programField = null;
	public static SemanticAnalyzer semAnalyzer = null;

	public static void main(String[] args) {
		Logger log = Logger.getLogger(Compiler.class);

		// String ulaz = "test/javniTest.mj";
		// String izlaz = "test/javniTest.obj";
		String ulaz = args[0];
		String izlaz = args[1];

		File sourceCode = new File(ulaz);
		log.info("Compiling source file: " + sourceCode.getAbsolutePath());

		File objFile = new File(izlaz);

		Compiler compile = new Compiler();
		List<CompilerError> allErrors = compile.compile(ulaz, izlaz);

		tsdump();
		codeGenerator(args[1]);
		if(allErrors.size() > 0)
			System.out.println("******************************SVE GRESKE*************************************");
	
		Collections.sort(allErrors, new Comparator<CompilerError>() {
			@Override 
			public int compare(CompilerError ce1, CompilerError ce2) {
				return ce1.getLine() - ce2.getLine();
			}
		});
		for (int i = 0; i < allErrors.size(); i++) {
			System.out.println(allErrors.get(i));
		}

	}
	@Override
	public List<CompilerError> compile(String sourceFilePath, String outputFilePath) {

		/**
		 * @param args
		 * @throws Exception
		 */
		Logger log = Logger.getLogger(Compiler.class);

		Reader br = null;
		try {

			File sourceCode = new File(sourceFilePath);
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			// System.out.println("BROJ1: " + Yylex.elementi());

			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);

			MJParser p = new MJParser(lexer);
			Symbol s = p.parse(); // pocetak parsiranja

			Program prog = (Program) (s.value);
			programField = prog;
			MySymbolTable.init();
			// MySymbolTable.currentScope.addToLocals(new Obj(Obj.Type, "bool", new
			// Struct(Struct.Bool)));
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");

			// ispis prepoznatih programskih konstrukcija
			SemanticAnalyzer v = new SemanticAnalyzer();
			prog.traverseBottomUp(v);// obilazi celo stablo
			semAnalyzer = v;

			
			// leksicke greske
			// System.out.println("LEKSICKIH GRESAKA IMA " + lexer.returnErrors().size() +
			// ", A ONE SU: ");
			for (int i = 0; i < lexer.returnErrors().size(); i++) {
				allErrors.add(lexer.returnErrors().get(i));
				//System.out.println(lexer.returnErrors().get(i));
			}

			// sintaksne greske
			for (int i = 0; i < p.returnErrors().size(); i++) {
				allErrors.add(p.returnErrors().get(i));
				//System.out.println(p.returnErrors().get(i));
			}

			// semanticke greske
			for (int i = 0; i < v.returnErrors().size(); i++) {
				allErrors.add(v.returnErrors().get(i));
				//System.out.println(v.returnErrors().get(i));
			}
			
			
			if ((!p.errorDetected && v.passed() && (lexer.returnErrors().size() == 0))) {
				compiled = true;
			}
			// log.info(" Print count calls = " + v.printCallCount);
			// log.info(" Deklarisanih promenljivih ima = " +
			// v.localVarsCount+v.globalVarsCount);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e1) {
					log.error(e1.getMessage(), e1);
				}
		}
		return allErrors;
	}

	public static void tsdump() {
		Logger log = Logger.getLogger(Compiler.class);
		log.info("===================================");
		MySymbolTable.dump();
	}

	public static void codeGenerator(String outputFilePath) {
		Logger log = Logger.getLogger(Compiler.class);

		if (compiled) {

			File objFile = new File(outputFilePath);
			if (objFile.exists())
				objFile.delete();

			CodeGenerator codeGenerator = new CodeGenerator();
			programField.traverseBottomUp(codeGenerator);// od korena koji si dobio, ti sad obidji celo stablo
			Code.dataSize = semAnalyzer.nVars;
			Code.mainPc = codeGenerator.getMainPc();
			try {
				Code.write(new FileOutputStream(objFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("Parsiranje uspesno zavrseno!");
		} else {
			log.error("Parsiranje NIJE uspesno zavrseno!");
		}
	}

}
