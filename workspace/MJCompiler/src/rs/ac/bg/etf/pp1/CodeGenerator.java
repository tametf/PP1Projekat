package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import rs.ac.bg.etf.pp1.SemanticAnalyzer.Stablo;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.test.CompilerError;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	public int mainPc = 0;
	boolean minus = false;
	String relop = "";
	boolean elsePartHere = false;
	boolean jmpAfterThen = false;
	Struct boolType = Tab.find("bool").getType();

	public int redniBroj = 0;

	class JumpInfo {
		int redniBroj;
		int fixupAddr;
	}

	class InstrInfo {
		int redniBroj;
		int addr;
	}

	List<InstrInfo> listaInstrukcija = new ArrayList<>();
	List<JumpInfo> odavdeSkacem = new ArrayList<>();

	public int getMainPc() {
		return mainPc;

	}

	public void visit(StmtStart stmtStart) {
		++redniBroj;

		for (JumpInfo info : odavdeSkacem) {
			if (info.redniBroj == redniBroj) {
				Code.fixup(info.fixupAddr);
			}
		}

		InstrInfo info = new InstrInfo();
		info.redniBroj = redniBroj;
		info.addr = Code.pc;
		listaInstrukcija.add(info);
	}

	Stablo koren;

	
	public void visit(DesignatorJump designatorJump) {
		/*
		 * int adresa = 3; for (int i = 1; i < designatorJump.getNum(); i++) { if
		 * (SemanticAnalyzer.niz[i] == 2 || SemanticAnalyzer.niz[i] == 4) adresa += 2;
		 * if (SemanticAnalyzer.niz[i] == 1) adresa += 3; if (SemanticAnalyzer.niz[i] ==
		 * 3) adresa += 5; if (SemanticAnalyzer.niz[i] == 5 || SemanticAnalyzer.niz[i]
		 * == 6) adresa += 4; if (SemanticAnalyzer.niz[i] == 7) adresa += 3; if
		 * (SemanticAnalyzer.niz[i] == 10) adresa += 7; }
		 * 
		 * Code.putJump(adresa);
		 */

		int srcInstr = redniBroj, dstInstr = designatorJump.getNum();
		
		List<Integer> addrs = new ArrayList<>();
		
		addrs.add(srcInstr); 
		addrs.add(dstInstr);
		
		List<Stablo> nodes = nadjiCvorove(addrs);
	
		Stablo src = nodes.get(0);
		Stablo dst = nodes.get(1);
		
		
		boolean nasao = false;
		while (src != null) {
			if (src == dst) {
				nasao = true;
				break;
			}
			src = src.spoljasnjiBlok;
		}

		if (!nasao) {
			Code.put(Code.trap);
			Code.put(dstInstr);
			return;
		}

		for (InstrInfo info : listaInstrukcija) {
			if (info.redniBroj == dstInstr) {
				Code.putJump(info.addr);
				return;
			}
		}

		Code.putJump(0);

		JumpInfo info = new JumpInfo();
		info.redniBroj = dstInstr;
		info.fixupAddr = Code.pc - 2;
		odavdeSkacem.add(info);
	}

	private List<Stablo> nadjiCvorove(List<Integer> addrs) {
		Stablo[] ret = new Stablo[addrs.size()];
		int brNadjenih = nadjiCvorove(koren, addrs, ret, 0);
		
		if (brNadjenih < addrs.size()) {
			return null;
		}
		
		return Arrays.asList(ret);
	}
	
	private int nadjiCvorove(Stablo pocetni, List<Integer> addrs, Stablo[] ret, int brNadjenih) {
		for (int instr : pocetni.instrukcije) {
			for (int i = 0; i < addrs.size(); ++i) {
				if(addrs.get(i) == instr) {
					ret[i] = pocetni;
					++brNadjenih;
				}
			}
		}
		
		if(brNadjenih == addrs.size()) {
			return brNadjenih;
		}
		
		for (Stablo dete : pocetni.blokovi) {
			brNadjenih = nadjiCvorove(dete, addrs, ret, brNadjenih);
			
			if (brNadjenih == addrs.size()) {
				return brNadjenih;
			}
		}
		
		return brNadjenih;
	}

	public void visit(ReturnValueTypeVoid returnValueTypeVoid) {

		if ("main".equalsIgnoreCase(returnValueTypeVoid.getNameOfMethod())) {
			mainPc = Code.pc;
		}

		returnValueTypeVoid.obj.setAdr(Code.pc);

		SyntaxNode methodNode = returnValueTypeVoid.getParent();

		Code.put(Code.enter);
		Code.put(returnValueTypeVoid.obj.getLevel()); // za prvi nivo ovde je 0
		Code.put(returnValueTypeVoid.obj.getLocalSymbols().size());

	}

	public void visit(MethodDeclaration methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);

	}

	public void visit(NumConstantFactor numConstant) {

		Code.loadConst(numConstant.getNumValue());
		if (numConstant.getParent() instanceof NegCnst) {
			Code.put(Code.neg);
		}

	}

	public void visit(BoolConstantFactor boolConstant) {
		Code.loadConst(boolConstant.getBoolValue());

	}

	public void visit(CharConstantFactor charConstant) {
		Code.loadConst(charConstant.getCharValue().charAt(1));

	}

	public void visit(FactorDesignator factorDesignator) {
		Code.load(factorDesignator.getDesignator().obj);
	}

	public void visit(NegationFactorDesignator negationFactorDesignator) {
		Code.load(negationFactorDesignator.getDesignator().obj);
		Code.put(Code.neg);
	}

	public void visit(PrintStatement printStatement) {

		// ako ne stampam element niza

		if (printStatement.getOptNumConst() instanceof OptNumConstYes) {
			Code.loadConst(((OptNumConstYes) printStatement.getOptNumConst()).getN1());
		} else {
			Code.loadConst(1);
		}

		if (printStatement.getExpr().struct == MySymbolTable.charType) {
			Code.put(Code.bprint);
		} else {
			Code.put(Code.print);
		}
	}

	public void visit(ReadStatement readStatement) {
		Obj readObj = readStatement.getDesignator().obj;

		if (readObj.getType() == MySymbolTable.charType) {
			Code.put(Code.bread);
		} else {
			Code.put(Code.read);
		}

		if (readObj.getKind() == Obj.Elem && readObj.getType().getKind() == Struct.Bool) {
			Code.put(Code.bastore);

		} else {
			Code.store(readStatement.getDesignator().obj);
		}
	}

	public void visit(DesignatorAssign designatorAssign) {
		Code.store(designatorAssign.getDesignator().obj);
	}

	public void visit(DesignatorIncrement designatorIncrement) {
		if (designatorIncrement.getDesignator() instanceof DesignatorArray) {
			Code.put(Code.dup2);
		}
		Code.load(designatorIncrement.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designatorIncrement.getDesignator().obj);
	}

	public void visit(DesignatorArray designatorArray) {
		// Code.load(designatorArray.obj);
	}

	public void visit(DesignatorDecrement designatorDecrement) {
		if (designatorDecrement.getDesignator() instanceof DesignatorArray) {
			Code.put(Code.dup2);
		}
		Code.load(designatorDecrement.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designatorDecrement.getDesignator().obj);
	}

	public void visit(DesignatorIdent designatorIdent) {
		if (designatorIdent.getParent() instanceof DesignatorArray)
			Code.load(designatorIdent.obj);
	}

	public void visit(AddopTermListMore addopTermListMore) {
		if (addopTermListMore.getAddop() instanceof AddopPlus) {
			Code.put(Code.add);
		} else if (addopTermListMore.getAddop() instanceof AddopMinus) {
			Code.put(Code.sub);
		}
	}

	public void visit(MulopFactorListMore mulopFactorListMore) { // moram da
		// stavim mull operator na stek
		if (mulopFactorListMore.getMulop() instanceof MulopMul) {
			Code.put(Code.mul);
		} else if (mulopFactorListMore.getMulop() instanceof MulopDiv) {
			Code.put(Code.div);
		} else if (mulopFactorListMore.getMulop() instanceof MulopMod) {
			Code.put(Code.rem);
		}
	}

	public void visit(FactorNewTypeExpr factorNewTypeExpr) {

		Code.put(Code.newarray);

		if (factorNewTypeExpr.getType().struct == MySymbolTable.intType) {
			Code.put(1);
		} else {
			Code.put(0);
		}

	}

	public void visit(NegationFactorExpr negationFactorExpr) {
		Code.put(Code.neg);
	}

	public void visit(AddopTermListOne addopTermListOne) {
		if (!(addopTermListOne.getParent().getParent().getParent() instanceof OptExprYes)) {
			if (minus == true) {
				Code.put(Code.neg);
				minus = false;
			}
		}
	}

	public void visit(DesignatorMethodCall methodCall) {

		Code.put(Code.call);
		Code.put(methodCall.getDesignator().obj.getAdr() - Code.pc);
	}

	boolean rrr = false;

	public void visit(DesignatorNoArray des) {
		// Code.load(des.obj);

	}

	public void visit(OptExprYes optExprYes) {
		// adr[index]=val

		// na steku treba da imamo: adr;index;val

		// na steku imamo: index i treba da stavimo adresu niza

		// Designator addressOfArray = (Designator) optExprYes.getParent();
		// Code.load(addressOfArray.obj); // sada imamo index;adr

		// Code.put(Code.dup_x1); // sada imamo adr;index;adr //Code.put(Code.pop);

		// sada imamo adr;index

		// i dodace se val u smeni Expr

	}

	public void visit(Expression Expression) {
		if (Expression.getParent().getParent() instanceof OptExprYes) {

		}
	}

	public void visit(StandardFunctionLen standardFunctionLen) {
		Code.put(Code.arraylength);
	}

	Stack<Integer> ifStack = new Stack<>();
	Stack<Integer> elseStack = new Stack<>();

	public void visit(CondFactExprRelopExpr condFactExprRelopExpr) {
		if (condFactExprRelopExpr.getRelop() instanceof RelopEqual) {
			Code.putFalseJump(Code.eq, 0); // skacem ako mi je expr!=expr
		} else if (condFactExprRelopExpr.getRelop() instanceof RelopNotEqual) {
			Code.putFalseJump(Code.ne, 0);
		} else if (condFactExprRelopExpr.getRelop() instanceof RelopGrt) {
			Code.putFalseJump(Code.gt, 0);
		} else if (condFactExprRelopExpr.getRelop() instanceof RelopGrtE) {
			Code.putFalseJump(Code.ge, 0);
		} else if (condFactExprRelopExpr.getRelop() instanceof RelopLess) {
			Code.putFalseJump(Code.lt, 0);
		} else if (condFactExprRelopExpr.getRelop() instanceof RelopLessE) {
			Code.putFalseJump(Code.le, 0);
		}
		ifStack.add(Code.pc - 2); // cuvam na steku odakle skacem u slucaju izraza expr relop expr
	}

	public void visit(CondFactExprOnly condFactExprOnly) {
		Code.put(Code.const_1); // stavim 1 jer je ovde izraz samo expr, pa da bih imala sa cime da uporedim
		Code.putFalseJump(Code.eq, 0); // skacem ako mi je expr != 0
		ifStack.add(Code.pc - 2); // stavljam na stek adresu trenutnu, da znam odakle skacem if(adresa!=-1)
	}

	public void visit(Else Else) {
		Code.putJump(0); // skacem bezuslovno
		int jmpElse = ifStack.pop(); // skinem ono sto mi je na steku od if-a
		ifStack.add(Code.pc - 2); // na stek stavim adresu za else (odnosno nju cu da
		// fixupujem posle celog // if-else dela)
		Code.fixup(jmpElse); // fixup-ujem gde ce da skoci if ako treba da se odradi else deo }
	}

	public void visit(IfElseStatement ies) {
		int stackValueForElseJump = ifStack.pop(); // na kraju if-else statement popujem adresu gde skacem iz else
		Code.fixup(stackValueForElseJump); // fixupujem gde ce da se skace iz else ako treba da se preskoci else
	}

	public void visit(IfStatement ifStatement) {
		int stackValue = ifStack.pop();
		// na kraju if statementa, to je ako uopste nemam else, popujem gde sam skocila
		// iz ifa
		Code.fixup(stackValue); // fixupujem tu adresu, ako se skok
// desio, ako nije, onda cu samo doci dovde i // necu imati zapravo koji skok da
		// fixupujem ali nece biti problema
	}

	/* read 2 print 1 condF=3 assign 4 inc 5 dec 6 jump 7 */

}