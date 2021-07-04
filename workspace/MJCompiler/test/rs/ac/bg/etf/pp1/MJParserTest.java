package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.test.CompilerError;
import rs.ac.bg.etf.pp1.test.CompilerError.CompilerErrorType;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.mj.runtime.Code;

import java.io.FileOutputStream;

public class MJParserTest {

	public static List<CompilerError> ce = new ArrayList<CompilerError>();
	
	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Logger log = Logger.getLogger(MJParserTest.class);

		Reader br = null;
		try {

			File sourceCode = new File("test/program.mj");
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());

			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			//System.out.println("BROJ: " + Yylex.elementi());
			
			//leksicke greske			
			for(int i = 0; i < lexer.returnErrors().size(); i++) {
				ce.add(lexer.returnErrors().get(i));
			} 
			//System.out.println("BROJ: " + Yylex.elementi());
			MJParser p = new MJParser(lexer);
			//System.out.println("BROJ: " + Yylex.elementi());
			
			Symbol s = p.parse(); // pocetak parsiranja
			
			Program prog = (Program) (s.value);
			//sintaksne greske
			for(int i = 0; i < p.returnErrors().size(); i++) {
				ce.add(p.returnErrors().get(i));
			}
			//System.out.println("BROJ: " + Yylex.elementi());
			Tab.init();
			Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", new Struct(Struct.Bool)));
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");

			// ispis prepoznatih programskih konstrukcija
			SemanticAnalyzer v = new SemanticAnalyzer();
			prog.traverseBottomUp(v);//obilazi celo stablo
			
			//semanticke greske
			for(int i = 0; i < v.returnErrors().size(); i++) {
				ce.add(v.returnErrors().get(i));
			} 
			log.info(" Print count calls = " + v.printCallCount);

			log.info(" Deklarisanih promenljivih ima = " + v.localVarsCount+v.globalVarsCount);

			log.info("===================================");
			Tab.dump();

			if (!p.errorDetected && v.passed()) {
				File objFile = new File("test/program.obj");
				if (objFile.exists())
					objFile.delete();

				CodeGenerator codeGenerator = new CodeGenerator();
				prog.traverseBottomUp(codeGenerator);//od korena koji si dobio, ti sad obidji celo stablo
				Code.dataSize = v.nVars;
				Code.mainPc = codeGenerator.getMainPc();
				Code.write(new FileOutputStream(objFile));
				log.info("Parsiranje uspesno zavrseno!");
			} else {
				if (p.errorDetected) {
					log.error("GRESKA!");
				}
				if (!v.passed()) {
					log.error("GRESKAaaa!");
				}
				log.error("Parsiranje NIJE uspesno zavrseno!");
			}
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
	}
}
