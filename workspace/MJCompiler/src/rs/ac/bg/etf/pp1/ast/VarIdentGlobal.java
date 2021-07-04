// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class VarIdentGlobal extends VarIdent {

    private String nameOfVar;
    private OptBrackets OptBrackets;

    public VarIdentGlobal (String nameOfVar, OptBrackets OptBrackets) {
        this.nameOfVar=nameOfVar;
        this.OptBrackets=OptBrackets;
        if(OptBrackets!=null) OptBrackets.setParent(this);
    }

    public String getNameOfVar() {
        return nameOfVar;
    }

    public void setNameOfVar(String nameOfVar) {
        this.nameOfVar=nameOfVar;
    }

    public OptBrackets getOptBrackets() {
        return OptBrackets;
    }

    public void setOptBrackets(OptBrackets OptBrackets) {
        this.OptBrackets=OptBrackets;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptBrackets!=null) OptBrackets.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptBrackets!=null) OptBrackets.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptBrackets!=null) OptBrackets.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarIdentGlobal(\n");

        buffer.append(" "+tab+nameOfVar);
        buffer.append("\n");

        if(OptBrackets!=null)
            buffer.append(OptBrackets.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarIdentGlobal]");
        return buffer.toString();
    }
}
