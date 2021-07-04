package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;

public class CounterVisitor extends VisitorAdaptor {
	
	protected int count;
	
	public int getCount() {
		return count;
	}

	public static class FormParamCounter extends CounterVisitor {
		public void visit(FormParsOne formParsOne) {
			count++;
		}
	}
	
	
	public static class VarCounter extends CounterVisitor {
		public void visit(VarIdentLocal varIdentLocal) {
			count++;
		}
	}
}
