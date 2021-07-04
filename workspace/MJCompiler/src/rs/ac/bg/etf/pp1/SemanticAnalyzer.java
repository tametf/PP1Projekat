package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.test.CompilerError;
import rs.ac.bg.etf.pp1.test.CompilerError.CompilerErrorType;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {

	int printCallCount = 0;

	public static int[] niz = new int[100];
	public static int i = 1;

	public List<CompilerError> ce = new ArrayList<CompilerError>();
	boolean erretected = false;
	int nVars = 0;

	Struct currentType = null;
	Struct boolType = MySymbolTable.find("bool").getType();

	int localVarsCount = 0;
	int localArraysCount = 0;
	int globalVarsCount = 0;
	int globalArraysCount = 0;
	int constCount = 0;
	int currentConstValue = 0;

	Struct currentTypeConst = MySymbolTable.noType;

	int methodsCount = 0;
	boolean inClass = false;
	Obj currentMethod = null;

	boolean factorNewType = false;
	boolean nullValue = false;
	boolean errorDetected = false;
	boolean newArray = false;
	boolean mainFormParams = false;

	MyDumpTable dstv = null;

	Logger log = Logger.getLogger(getClass());

	class Stablo {
		int idBlock;
		List<Integer> instrukcije = new ArrayList<Integer>();
		List<Stablo> blokovi = new ArrayList<Stablo>();
		Stablo spoljasnjiBlok = null;
	}
	
	int redniBroj = 0;
	int idBloka = 0;
	Stablo koren = null;
	Stablo trenutno = null;
	
	public void visit(StmtStart stmtStart) {
		/* sastavi */
		trenutno.instrukcije.add(++redniBroj);
		
	}
	
	/* oznacava pocetak svakog bloka*/
 	public void visit(If _if) {
 		Stablo novoStablo = new Stablo();
 		novoStablo.idBlock = ++idBloka;
 		novoStablo.spoljasnjiBlok=trenutno;
 		trenutno.blokovi.add(novoStablo);
 		trenutno=novoStablo;
 	}
 	
 	public void endBlockVisitor() {
 		trenutno = trenutno.spoljasnjiBlok;
 	}
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	public List<CompilerError> returnErrors() {
		return ce;
	}

	public boolean passed() {
		return !errorDetected;
	}

	public void visit(ProgName ProgName) {
		MySymbolTable.insert(Obj.Prog, ProgName.getProgName(), MySymbolTable.noType);
		MySymbolTable.openScope();
	}

	public void visit(Program program) {
		koren = new Stablo();
		koren.idBlock = ++idBloka;
		trenutno=koren;
		
		nVars = MySymbolTable.currentScope().getnVars();

		Obj mainMethod = MySymbolTable.find("main");
		if (mainMethod == MySymbolTable.noObj) {
			
			String opisGreske = "Program nema main funkciju.";
			int linija = program.getLine();
			greska(linija, opisGreske);
			return;
		}

		MySymbolTable.chainLocalSymbols(MySymbolTable.find(program.getProgName().getProgName()));

		MySymbolTable.closeScope();
	}

	public void visit(Type type) {

		currentType = MySymbolTable.noType;
		Obj typeNode = MySymbolTable.find(type.getNameOfType());

		if (typeNode == MySymbolTable.noObj) {
			
			String opisGreske = "Nije pronadjen tip " + type.getNameOfType() + " u tabeli simbola! ";
			int linija = type.getLine();
			greska(linija, opisGreske);
			
			
			currentType = MySymbolTable.noType;
			type.struct = MySymbolTable.noType;
		} else {
			if (Obj.Type != typeNode.getKind()) {
				String opisGreske = "Ime " + type.getNameOfType() + " ne predstavlja tip.";
				int linija = type.getLine();
				greska(linija, opisGreske);
				currentType = MySymbolTable.noType;
				type.struct = MySymbolTable.noType;
			} else {	
				currentType = typeNode.getType();
				type.struct = typeNode.getType();
			}
		}
	}

	public void visit(NumConstant numConstant) {
		currentConstValue = numConstant.getNumValue();
		currentTypeConst = MySymbolTable.intType;
		numConstant.struct = MySymbolTable.intType;
	}

	public void visit(CharConstant charConstant) {
		currentConstValue = charConstant.getCharValue().charAt(1);
		currentTypeConst = MySymbolTable.charType;
		charConstant.struct = MySymbolTable.charType;
	}

	public void visit(BoolConstant boolConstant) {
		currentConstValue = boolConstant.getBoolValue();
		currentTypeConst = MySymbolTable.boolType;
		boolConstant.struct = MySymbolTable.boolType;
	}

	public void visit(ConstDeclOne constDeclOne) {
		Obj varNode = MySymbolTable.find(constDeclOne.getNameOfConstant());
		if (varNode != MySymbolTable.noObj) {
			String opisGreske = "Konstanta " + constDeclOne.getNameOfConstant() + " je vec definisana.";
			int linija = constDeclOne.getLine();
			greska(linija, opisGreske);

		} else {
			if (currentTypeConst != currentType) {
				String opisGreske = "Tip konstante  " + constDeclOne.getNameOfConstant()
						+ " se razlikuje od ocekivanog tipa!";
				int linija = constDeclOne.getLine();
				greska(linija, opisGreske);
			} else {
				varNode = MySymbolTable.insert(Obj.Con, constDeclOne.getNameOfConstant(), currentType);
				varNode.setAdr(currentConstValue);
				constCount++;
				String infoTekst = "Definisana je konstanta " + constDeclOne.getNameOfConstant() + " na liniji "
						+ constDeclOne.getLine();
				report_info(infoTekst, null);
			}
		}
	}

	public void greska(int linija, String opisGreske) {
		report_error(opisGreske, null);
		CompilerError newError = new CompilerError(linija, opisGreske, CompilerErrorType.SEMANTIC_ERROR);
		ce.add(newError);
	}

	public void visit(VarIdentGlobal varIdentGlobal) {

		Obj varNode = MySymbolTable.currentScope().findSymbol(varIdentGlobal.getNameOfVar());
		if (varNode != null) {
			String opisGreske = "Globalna promenljiva  " + varIdentGlobal.getNameOfVar() + " je vec definisana!";
			int linija = varIdentGlobal.getLine();
			greska(linija, opisGreske);
		} else {
			if (varIdentGlobal.getOptBrackets() instanceof OptBracketsNo) {

				// u pitanju je promenljiva, ne niz

				varNode = MySymbolTable.insert(Obj.Var, varIdentGlobal.getNameOfVar(), currentType);
				dstv = new MyDumpTable();
				dstv.visitObjNode(varNode);

				report_info("Deklarisana globalna promenljiva " + varIdentGlobal.getNameOfVar() + " na liniji "
						+ varIdentGlobal.getLine() + " : " + dstv.getOutput(), null);
				globalVarsCount++;
			} else {

				varNode = MySymbolTable.insert(Obj.Var, varIdentGlobal.getNameOfVar(),
						new Struct(Struct.Array, currentType));
				dstv = new MyDumpTable();
				dstv.visitObjNode(varNode);

				report_info("Deklarisan globalni niz " + varIdentGlobal.getNameOfVar() + " na liniji "
						+ varIdentGlobal.getLine() + ":" + dstv.getOutput(), null);
				globalArraysCount++;

			}

		}

	}

	public void visit(VarIdentLocal varIdentLocal) {

		Obj varNode = MySymbolTable.currentScope().findSymbol(varIdentLocal.getNameOfLocalVar());
		if (varNode != null) {
			String opisGreske = "Lokalna promenljiva  " + varIdentLocal.getNameOfLocalVar() + " je vec definisana!";
			int linija = varIdentLocal.getLine();
			greska(linija, opisGreske);
		} else {
			if (varIdentLocal.getOptBrackets() instanceof OptBracketsNo) {

				varNode = MySymbolTable.insert(Obj.Var, varIdentLocal.getNameOfLocalVar(), currentType);
				dstv = new MyDumpTable();
				dstv.visitObjNode(varNode);

				String infoTekst = "Deklarisana lokalna promenljiva " + varIdentLocal.getNameOfLocalVar() + "na liniji "
						+ varIdentLocal.getLine() + " : " + dstv.getOutput();
				report_info(infoTekst, null);
				localVarsCount++;

			} else {
				varNode = MySymbolTable.insert(Obj.Var, varIdentLocal.getNameOfLocalVar(),
						new Struct(Struct.Array, currentType));
				dstv = new MyDumpTable();
				dstv.visitObjNode(varNode);

				report_info("Deklarisan lokalni niz sa imenom: " + varIdentLocal.getNameOfLocalVar() + "na liniji "
						+ varIdentLocal.getLine() + " : " + dstv.getOutput(), null);
				localArraysCount++;

			}
		}
	}

	public void visit(ReturnValueTypeVoid returnValueTypeVoid) {

		Obj funkc = MySymbolTable.find(returnValueTypeVoid.getNameOfMethod());

		if (funkc.getName().equals(returnValueTypeVoid.getNameOfMethod())) {
			String opisGreske = "Greska! Metoda je vec definisana ";
			int linija = returnValueTypeVoid.getLine();
			greska(linija, opisGreske);
			return;
		} else {
			currentMethod = MySymbolTable.insert(Obj.Meth, returnValueTypeVoid.getNameOfMethod(), MySymbolTable.noType);
			returnValueTypeVoid.obj = currentMethod;
			MySymbolTable.openScope();
			dstv = new MyDumpTable();
			dstv.visitObjNode(currentMethod);

			report_info("Obradjuje se funkcija " + returnValueTypeVoid.getNameOfMethod() + " tip funkcije: VOID :"
					+ dstv.getOutput(), null);
			methodsCount++;
		}

	}

	public void visit(ReturnValueTypeOther returnValueTypeOther) {
		Obj funkc = MySymbolTable.find(returnValueTypeOther.getNameOfMethod());
		if (funkc.getName().equals(returnValueTypeOther.getNameOfMethod())) {

			String opisGreske = "Greska! Metoda je vec definisana ";
			int linija = returnValueTypeOther.getLine();
			greska(linija, opisGreske);

		} else {
			if (returnValueTypeOther.getNameOfMethod().equals("main")) {
				String opisGreske = "Greska! Metoda main ne moze imati povratni tip int";
				int linija = returnValueTypeOther.getLine();

				greska(linija, opisGreske);

			} else {
				currentMethod = MySymbolTable.insert(Obj.Meth, returnValueTypeOther.getNameOfMethod(),
						returnValueTypeOther.getType().struct);
				returnValueTypeOther.obj = currentMethod;

				MySymbolTable.openScope();
				dstv = new MyDumpTable();
				dstv.visitObjNode(currentMethod);

				report_info("Obradjuje se funkcija " + returnValueTypeOther.getNameOfMethod() + " tip funkcije: "
						+ returnValueTypeOther.getType().getNameOfType() + " : " + dstv.getOutput(), null);
				methodsCount++;

			}
		}
	}

	public void visit(MethodDeclaration methodDecl) {

		if (currentMethod == null)
			return;

		boolean greskaF = false;
		if ((currentMethod.getName().equals("main")) && (methodDecl.getFormPars() instanceof FormParams)) {
			String opisGreske = "Metoda main ne sme imati formalne parametre.";
			int linija = methodDecl.getLine();
			greska(linija, opisGreske);
			greskaF = true;

		}
		if ((currentMethod.getName().equals("main"))
				&& (methodDecl.getReturnValueType() instanceof ReturnValueTypeOther)) {
			String opisGreske = "Povratni tip main metode mora biti void.";
			int linija = methodDecl.getLine();
			greska(linija, opisGreske);
			greskaF = true;
		}
		if (!greskaF) {
			MySymbolTable.chainLocalSymbols(currentMethod);
			MySymbolTable.closeScope();
			currentMethod = null;
			greskaF = false;
		}

	}

	public void visit(DesignatorArray designator) {

		String ident = designator.getDesignatorIdent().getDesignatorName();

		Obj obj = MySymbolTable.find(ident);

		if (obj == MySymbolTable.noObj) {
	
			String opisGreske = "Promenljiva "
					+ designator.getDesignatorIdent().getDesignatorName() + " nije pronadjenaa u tableli simbola.";
			int linija = designator.getLine();
			greska(linija, opisGreske);
			designator.obj = MySymbolTable.noObj;
		} 
		else
		if (obj.getKind() != Obj.Var && obj.getKind() != Obj.Con && obj.getKind() != Obj.Meth) {
			
			String opisGreske = "Promenljiva "
					+ designator.getDesignatorIdent().getDesignatorName() + " nije pronadjena u tableli simbola.";
			int linija = designator.getLine();
			greska(linija, opisGreske);
			
			designator.obj = MySymbolTable.noObj;
		} else

		if (designator.getExpr().struct != MySymbolTable.intType) {

			String opisGreske = "Indeks niza "
					+ designator.getDesignatorIdent().getDesignatorName() + " mora biti tipa int.";
			int linija = designator.getLine();
			greska(linija, opisGreske);
			designator.obj = MySymbolTable.noObj;
		} else {
			Struct type = designator.getDesignatorIdent().obj.getType().getElemType();
			designator.obj = new Obj(Obj.Elem, ident + "_elem", type != null ? type : MySymbolTable.noType);
			
		}


	}

	public void visit(DesignatorNoArray designator) {

		Obj designatorObj = MySymbolTable.find(designator.getDesignatorIdent().getDesignatorName());

		designator.obj = designatorObj;
		
		if (designatorObj == MySymbolTable.noObj) {
			
			designator.obj = MySymbolTable.noObj;
			
			String opisGreske ="Promenljiva "
					+ designator.getDesignatorIdent().getDesignatorName() + " nije pronadjena u tableli simbola.";
			int linija = designator.getLine();
			greska(linija, opisGreske);
			return;
		}
		if (designatorObj.getKind() != Obj.Var && designatorObj.getKind() != Obj.Con
				&& designatorObj.getKind() != Obj.Meth) {
		
			String opisGreske = "Promenljiva "
					+ designator.getDesignatorIdent().getDesignatorName() + " nije pronadjena u tableli simbola.";
			int linija = designator.getLine();
			greska(linija, opisGreske);
			designator.obj = MySymbolTable.noObj;
		}
	}

	public void visit(FactorDesignator factorDesignator) {
		factorDesignator.struct = factorDesignator.getDesignator().obj.getType();
	}
	
	public void visit(NegationFactorDesignator negationFactorDesignator) {
		negationFactorDesignator.struct = negationFactorDesignator.getDesignator().obj.getType();
	}

	public void visit(Cnst cnst) {
		cnst.struct = cnst.getConstantFactor().struct;
	}
	
	public void visit(NegCnst negCnst) {
		negCnst.struct = negCnst.getConstantFactor().struct;
	}
	
	

	public void visit(NumConstantFactor numConstantFactor) {
		numConstantFactor.struct = MySymbolTable.intType;
	}

	public void visit(CharConstantFactor charConstantFactor) {
		charConstantFactor.struct = MySymbolTable.charType;
	}

	public void visit(BoolConstantFactor boolConstantFactor) {
		boolConstantFactor.struct = MySymbolTable.boolType;
	}

	public void visit(FactorExpr factorExpr) {
		factorExpr.struct = factorExpr.getExpr().struct;
	}
	
	public void visit(NegationFactorExpr negationFactorExpr) {
		negationFactorExpr.struct = negationFactorExpr.getExpr().struct;
	}

	public void visit(FactorStandardFunction factorStandardFunction) {
		factorStandardFunction.struct = factorStandardFunction.getStandardFunction().struct;
	}

	public void visit(StandardFunctionChr standardFunctionChr) {

		if (standardFunctionChr.getExpr().struct != MySymbolTable.intType) {
			String opisGreske ="Neispravan tip izraza u funkciji chr.";
			int linija = standardFunctionChr.getLine();
			greska(linija, opisGreske);
		} else {
			report_info("CHR funkcija na liniji " + standardFunctionChr.getLine(), null);
		}
		standardFunctionChr.struct = MySymbolTable.find("chr").getType();
	}

	public void visit(StandardFunctionOrd standardFunctionOrd) {
		if (standardFunctionOrd.getExpr().struct != MySymbolTable.charType) {
			String opisGreske ="Neispravan tip izraza u funkciji ord.";
			int linija = standardFunctionOrd.getLine();
			greska(linija, opisGreske);
		} else {
			report_info("Ord funkcija na liniji " + standardFunctionOrd.getLine(), null);
		}
		standardFunctionOrd.struct = MySymbolTable.find("ord").getType();
	}

	public void visit(StandardFunctionLen standardFunctionLen) {
		if (newArray == false) {
			String opisGreske ="Neispravan tip izraza u funkciji len.";
			int linija = standardFunctionLen.getLine();
			greska(linija, opisGreske);
		} else {
			newArray = false;
		}
		if (standardFunctionLen.getExpr().struct.getKind() != Struct.Array) {
		
			String opisGreske = "Neispravan tip izraza u funkciji len";
			int linija = standardFunctionLen.getLine();
			greska(linija, opisGreske);
		} else {
			report_info("LEN funkcija na liniji " + standardFunctionLen.getLine(), null);
		}
		standardFunctionLen.struct = MySymbolTable.find("len").getType();
	}

	public void visit(FactorNewTypeExpr factorNewTypeExpr) {
		if (factorNewTypeExpr.getExpr().struct != MySymbolTable.intType) {
		
			String opisGreske = "Velicina niza koji se pravi mora biti tipa int.";
			int linija = factorNewTypeExpr.getLine();
			greska(linija, opisGreske);
			
		}
		factorNewTypeExpr.struct = new Struct(Struct.Array, factorNewTypeExpr.getType().struct);
		factorNewType = true;
		newArray = true;
	}

	public void visit(MulopFactorListOne mulopFactorListOne) {
		mulopFactorListOne.struct = mulopFactorListOne.getFactor().struct;
	}

	public void visit(MulopFactorListMore mulopFactorListMore) {

		Struct s1 = mulopFactorListMore.getMulopFactorList().struct;
		Struct s2 = mulopFactorListMore.getFactor().struct;

		if (mulopFactorListMore.getMulopFactorList().struct.getKind() == Struct.Array)
			s1 = mulopFactorListMore.getMulopFactorList().struct.getElemType();
		if (mulopFactorListMore.getFactor().struct.getKind() == Struct.Array)
			s2 = mulopFactorListMore.getFactor().struct.getElemType();

		if (!s1.equals(s2) || s1 != MySymbolTable.intType) {
			mulopFactorListMore.struct = MySymbolTable.noType;
		
			String opisGreske ="Neodgovarajuci tip uz operaciju mnozenja, mora biti INT!";
			int linija = mulopFactorListMore.getLine();
			greska(linija, opisGreske);
		} else {
			mulopFactorListMore.struct = mulopFactorListMore.getMulopFactorList().struct;
		}

	}

	public void visit(TermFactorMulop termFactorMulop) {
		termFactorMulop.struct = termFactorMulop.getMulopFactorList().struct;
	}

	boolean minus = false;

	public void visit(AddopTermListOne addopTermListOne) {

		Struct str = addopTermListOne.getTerm().struct;

		if (addopTermListOne.getTerm().struct.getKind() == Struct.Array)
			str = addopTermListOne.getTerm().struct.getElemType();

		if (minus && str != MySymbolTable.intType) {

			String opisGreske = "Vrednost ne moze biti negativna, jer nije tipa int!";
			int linija = addopTermListOne.getLine();
			greska(linija, opisGreske);
		}

		addopTermListOne.struct = addopTermListOne.getTerm().struct;
		minus = false;
	}

	public void visit(OptMinusYes OptMinusYes) {
		minus = true;
	}

	public void visit(AddopTermListMore addopTermListMore) {

		Struct str1 = addopTermListMore.getAddopTermList().struct;
		Struct str2 = addopTermListMore.getTerm().struct;

		if (str1.getKind() == Struct.Array)
			str1 = addopTermListMore.getAddopTermList().struct.getElemType();
		if (str2.getKind() == Struct.Array)
			str2 = addopTermListMore.getTerm().struct.getElemType();

		if (!str1.equals(str2) || str2 != MySymbolTable.intType) {

			addopTermListMore.struct = MySymbolTable.noType;
			
			String opisGreske = "Neodgovarajuci tip uz operaciju sabiranja, mora biti int!";
			int linija = addopTermListMore.getLine();
			greska(linija, opisGreske);
		} else {
			addopTermListMore.struct = MySymbolTable.intType;
		}

		addopTermListMore.struct = addopTermListMore.getTerm().struct;
	}

	public void visit(Expr1AddopTerm expr1AddopTerm) {
		expr1AddopTerm.struct = expr1AddopTerm.getAddopTermList().struct;
	}

	public void visit(Expression expression) {
		expression.struct = expression.getExpr1().struct;
	}

	public void visit(DesignatorAssign designatorAssign) {
		niz[i++] = 4;

		Struct struct1 = designatorAssign.getDesignator().obj.getType();
		Struct struct2 = designatorAssign.getExpr().struct;

		if (designatorAssign.getDesignator().obj.getKind() != Obj.Var
				&& designatorAssign.getDesignator().obj.getKind() != Obj.Elem) {
			String opisGreske = "Leva strana dodele mora biti promenljiva ili element niza!";
			int linija = designatorAssign.getLine();
			greska(linija, opisGreske);
		}

		if (!struct1.assignableTo(struct2)) {
			String opisGreske = "Nekompatibilni tipovi pri dodeli vrednosti!";
			int linija = designatorAssign.getLine();
			greska(linija, opisGreske);

		}
		designatorAssign.struct = designatorAssign.getDesignator().obj.getType();

	}

	public void visit(DesignatorIncrement designatorIncrement) {

		niz[i++] = 5;

		Obj incrementObj = designatorIncrement.getDesignator().obj;

		if (incrementObj.getKind() != Obj.Var && incrementObj.getKind() != Obj.Elem) {
			String opisGreske = "Uz operaciju dekrement se moze naci samo promenljiva ili element niza.";
			int linija = designatorIncrement.getLine();
			greska(linija, opisGreske);
			return;
		}

		if (incrementObj.getType().getKind() == Struct.Array) {
			String opisGreske = "Uz operaciju dekrement se moze naci samo promenljiva ili element niza.";
			int linija = designatorIncrement.getLine();
			greska(linija, opisGreske);
			return;
		}

		if (incrementObj.getType() != MySymbolTable.intType) {
			String opisGreske = "Samo celobrojne vrednosti se mogu inkrementirati ili dekrementirati.";
			int linija = designatorIncrement.getLine();
			greska(linija, opisGreske);
			return;
		}

	}

	public void visit(DesignatorDecrement designatorDecrement) {
		niz[i++] = 6;

		Obj decrementObj = designatorDecrement.getDesignator().obj;

		if (decrementObj.getKind() != Obj.Var && decrementObj.getKind() != Obj.Elem) {
			String opisGreske = "Uz operaciju dekrement se moze naci samo promenljiva ili element niza.";
			int linija = designatorDecrement.getLine();
			greska(linija, opisGreske);
			return;
		}

		if (decrementObj.getType().getKind() == Struct.Array) {
			String opisGreske = "Uz operaciju dekrement se moze naci samo promenljiva ili element niza.";
			int linija = designatorDecrement.getLine();
			greska(linija, opisGreske);
			return;
		}

		if (decrementObj.getType() != MySymbolTable.intType) {
			String opisGreske = "Samo celobrojne vrednosti se mogu inkrementirati ili dekrementirati.";
			int linija = designatorDecrement.getLine();
			greska(linija, opisGreske);
			return;
		}
	}

	public void visit(DesignatorStmt designatorStmt) {
		designatorStmt.struct = designatorStmt.getDesignatorStatement().struct;
	}

	
	public void visit(Negation negation) {
      //  Struct struct = negation

        /*if (struct != MyTab.intType) {
            reportError("Mogu se negirati samo celobrojne vrednosti", expr);

            if (expr.getMoreAddops() instanceof MoreAddopElements) {
                Addop addop = ((MoreAddopElements)expr.getMoreAddops()).getAddopMore().getAddop();
                reportError("Operacija " + addopToChar(addop) + " se moze koristiti samo sa celobrojnim vrednostima", expr);
            }
        }*/

        //negation.struct = struct;
    }
	public void visit(ReadStatement readStatement) {
		niz[i++] = 2;

		int readStruct = readStatement.getDesignator().obj.getKind();
		Struct readType = readStatement.getDesignator().obj.getType();
		Struct arrayElemType = readStatement.getDesignator().obj.getType().getElemType();
		int arrayStruct = readStatement.getDesignator().obj.getType().getKind();

		if (readStruct != Obj.Var && readStruct != Obj.Elem) {
			String opisGreske = "Neispravna read funkcija. Vrednost se moze upisati samo u promenljivu ili element niza.";
			int linija = readStatement.getLine();
			greska(linija, opisGreske);
		}

		if (arrayStruct == Struct.Array) {
			String opisGreske = "Neispravna read funkcija. Ne moze se ucitati vrednost u niz.";
			int linija = readStatement.getLine();
			greska(linija, opisGreske);
		}

	}

	public void visit(PrintStatement printStatement) {
		if (printStatement.getExpr().struct == MySymbolTable.charType) {
			// System.out.print("roknucu se");
			niz[i++] = 10;
		} else {
			niz[i++] = 1;
		}
		int printStruct = printStatement.getExpr().struct.getKind();
		Struct elemType = printStatement.getExpr().struct.getElemType();
		if (printStruct == Struct.Array) {
			String opisGreske = "Neispravna print funkcija. Vrednost se moze biti samo promenljiva ili element niza.";
			int linija = printStatement.getLine();
			greska(linija, opisGreske);
		} else {

			printStatement.struct = printStatement.getExpr().struct;
			printCallCount++;
		}
	}

	public void visit(StatementsList statementsList) {
		statementsList.struct = statementsList.getStatement().struct;
	}

	// Prepoznavanje kraja programa

	public void visit(EndOfProgram endOfProgram) {
		System.out.println("");
		System.out.println("---Informacije o programu---");
		System.out.println("Lokalnih promenljivih ima: " + localVarsCount);
		System.out.println("Globalnih promenljivih ima: " + globalVarsCount);
		System.out.println("Konstanti ima: " + constCount);

		if (localVarsCount > 256) {
			
			String opisGreske = "Broj lokalnih promenljivih u programu ne sme biti veci od 256.";
			int linija = endOfProgram.getLine();
			greska(linija, opisGreske);

		}
		if (globalVarsCount > 65536) {
		
			String opisGreske = "Broj globalnih promenljivih u programu ne sme biti veci od 65536.";
			int linija = endOfProgram.getLine();
			greska(linija, opisGreske);
		}
	}

	public void visit(MulopMul mulopMul) {
		mulopMul.struct = new Struct(Code.mul);
	}

	public void visit(MulopDiv mulopDiv) {
		mulopDiv.struct = new Struct(Code.div);
	}

	public void visit(MulopMod mulopMod) {
		mulopMod.struct = new Struct(Code.rem);
	}

	public void visit(AddopPlus addopPlus) {
		addopPlus.struct = new Struct(Code.add);
	}

	public void visit(AddopMinus addopMinus) {
		addopMinus.struct = new Struct(Code.sub);
	}

	public void visit(ConditionOne conditionOne) {
		conditionOne.struct = conditionOne.getCondTerm().struct;
	}

	public void visit(ConditionList conditionList) {

	}

	public void visit(CondFactExprRelopExpr condFactExprRelopExpr) {
		boolean greska = false;
		if (elemOfArray.size() == 1) {
		
			String opisGreske = "Ne moze se porediti ceo niz sa elementom niza.";
			int linija = condFactExprRelopExpr.getLine();
			greska(linija, opisGreske);
			greska = true;
		}

		boolean arrayRelop = false;
		if (!(condFactExprRelopExpr.getRelop() instanceof RelopEqual
				|| condFactExprRelopExpr.getRelop() instanceof RelopNotEqual)
				&& condFactExprRelopExpr.getExpr().struct.getKind() == Struct.Array && (elemOfArray.size() == 0)) {
			arrayRelop = true;
		}

		if (condFactExprRelopExpr.getExpr().struct.getKind() == Struct.Array && arrayRelop) {
	
			String opisGreske = "Neodgovarajuci relacioni operator! Uz nizove mogu stajati samo != ili ==.";
			int linija = condFactExprRelopExpr.getLine();
			greska(linija, opisGreske);

			arrayRelop = false;
		} else {

			Struct s1 = condFactExprRelopExpr.getExpr1().struct;
			Struct s2 = condFactExprRelopExpr.getExpr().struct;

			if (elemOfArray.size() == 2 && condFactExprRelopExpr.getExpr().struct.getKind() == Struct.Array) {
				s1 = condFactExprRelopExpr.getExpr1().struct.getElemType();

				s2 = condFactExprRelopExpr.getExpr().struct.getElemType();
			}

			if (!s1.equals(s2) && !greska) {
		
				String opisGreske = "Neodgovarajuci tip! Mogu se porediti samo izrazi istog tipa.";
				int linija = condFactExprRelopExpr.getLine();
				greska(linija, opisGreske);
			}
		}
		elemOfArray.clear();
		greska = false;
		condFactExprRelopExpr.struct = MySymbolTable.boolType;

	}

	public void visit(IfCondition ifCond) {
		if (ifCond.getCondition().struct != MySymbolTable.boolType) {
			
			String opisGreske = "Neispravan tip u if naredbi.";
			int linija = ifCond.getLine();
			greska(linija, opisGreske);
		}

	}

	public void visit(CondTermOne condTermOne) {
		condTermOne.struct = condTermOne.getCondFact().struct;
	}

	public void visit(CondFactExprOnly condFactExprOnly) {
		niz[i++] = 3;
		condFactExprOnly.struct = condFactExprOnly.getExpr().struct;
	}

	boolean parametersOfFunctionFound = false;

	Obj designatorObj = null;

	public void visit(DesignatorMethodCall methodCall) {
		if (designatorObj.getKind() != Obj.Meth) {
	
			String opisGreske =  "Ocekuje se poziv metode.";
			int linija = methodCall.getLine();
			greska(linija, opisGreske);
		} else {
			methodCall.struct = designatorObj.getType();
			if (methodCall.getActPars() instanceof ActParams)
				parametersOfFunctionFound = true;
		}
	}

	public void visit(FormParsOneParam formParsOneParam) {
		Obj varNode = MySymbolTable.currentScope().findSymbol(formParsOneParam.getParamName());
		if (varNode != null) {
			String opisGreske = "Naziv parametra se vec koristi.";
			int linija = formParsOneParam.getLine();
			greska(linija, opisGreske);
		} else {
			MySymbolTable.insert(Obj.Var, formParsOneParam.getParamName(), formParsOneParam.getType().struct);
			report_info("Parametar funkcije " + formParsOneParam.getParamName() + " na liniji "
					+ formParsOneParam.getLine() + " je ubacen u MySymbolTableelu simbola", null);

		}
	}

	Struct typeOfExpression = null;

	boolean returnWord = false;

	public void visit(ReturnStatement returnStatement) {
		if (currentMethod == null) {
			String opisGreske = "Return ne sme stajati van metode.";
			int linija = returnStatement.getLine();
			greska(linija, opisGreske);
		} else {
			returnWord = true;
			Struct s = returnStatement.getExpr().struct;
			if (!s.assignableTo(currentMethod.getType())) {
				String opisGreske = "Povratni tip ne odgovara tipu metode.";
				int linija = returnStatement.getLine();
				greska(linija, opisGreske);
			}
		}
	}

	public void visit(ReturnNoExprStatement returnNoExprStatement) {
		if (currentMethod == null) {

			String opisGreske = "Return ne sme stajati van metode.";
			int linija = returnNoExprStatement.getLine();
			greska(linija, opisGreske);
		} else {
			if (currentMethod.getType().getKind() != Struct.None) {
				
				String opisGreske = "Void metoda ne sme imati rec return u sebi.";
				int linija = returnNoExprStatement.getLine();
				greska(linija, opisGreske);
			}
		}
	}

	public List<Boolean> elemOfArray = new ArrayList<Boolean>();

	public void visit(OptExprYes optExprYes) {

		if (optExprYes.getParent().getParent().getParent().getParent().getParent().getParent().getParent()
				.getParent() instanceof CondFactExprRelopExpr) {
			elemOfArray.add(true);
		}

		if (optExprYes.getExpr().struct.getKind() != Struct.Array
				&& optExprYes.getExpr().struct.getKind() != Struct.Int) {
			
			String opisGreske = "Izraz mora biti tipa int.";
			int linija = optExprYes.getLine();
			greska(linija, opisGreske);

		}
		typeOfExpression = optExprYes.getExpr().struct;
	}

	boolean switchStatement = false;
	boolean doWhile = false;

	public void visit(DoWhileStatement doWhileStatement) {
		doWhile = false;
	}

	public void visit(BreakStatement breakStatement) {
		if (!switchStatement && !doWhile) {

			String opisGreske = "Break naredba nije dozvoljena van switch ili do-while naredbe.";
			int linija = breakStatement.getLine();
			greska(linija, opisGreske);
		}
	}

	public void visit(SwitchExpr switchExpr) {
		switchStatement = false;
		listOfNumberSwitch.clear();
		if (switchExpr.getExpr().struct != MySymbolTable.intType) {
	
			String opisGreske = "Expr u switch naredbi mora biti tipa int.";
			int linija = switchExpr.getLine();
			greska(linija, opisGreske);
		}
	}

	public void visit(Switch switch_) {
		switchStatement = true;
	}

	public void visit(Do do_) {
		doWhile = true;
	}

	public void visit(ContinueStatement continueStatement) {
		if (!doWhile) {
			String opisGreske = "Continue naredba nije dozvoljena van do-while naredbe.";
			int linija = continueStatement.getLine();
			greska(linija, opisGreske);
		}
	}

	LinkedList<Integer> listOfNumberSwitch = new LinkedList<Integer>();

	public void visit(NumConst numConst) {

		if (listOfNumberSwitch.contains(numConst.getNumConstSwitch())) {
			
			String opisGreske = "Ponovljen broj u switch naredbi.";
			int linija = numConst.getLine();
			greska(linija, opisGreske);
		} else {
			listOfNumberSwitch.add(numConst.getNumConstSwitch());
		}
	}

	public void visit(DesignatorJump numConst) {
		niz[i++] = 7;
	}

	public void visit(DesignatorIdent s) {
		s.obj = MySymbolTable.find(s.getDesignatorName());
	}

	public void visit(FormParam fp) {
		currentMethod.setLevel(currentMethod.getLevel() + 1);
	}

}
