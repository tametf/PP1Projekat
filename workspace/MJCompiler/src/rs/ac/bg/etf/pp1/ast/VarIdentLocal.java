// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class VarIdentLocal implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String nameOfLocalVar;
    private OptBrackets OptBrackets;

    public VarIdentLocal (String nameOfLocalVar, OptBrackets OptBrackets) {
        this.nameOfLocalVar=nameOfLocalVar;
        this.OptBrackets=OptBrackets;
        if(OptBrackets!=null) OptBrackets.setParent(this);
    }

    public String getNameOfLocalVar() {
        return nameOfLocalVar;
    }

    public void setNameOfLocalVar(String nameOfLocalVar) {
        this.nameOfLocalVar=nameOfLocalVar;
    }

    public OptBrackets getOptBrackets() {
        return OptBrackets;
    }

    public void setOptBrackets(OptBrackets OptBrackets) {
        this.OptBrackets=OptBrackets;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
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
        buffer.append("VarIdentLocal(\n");

        buffer.append(" "+tab+nameOfLocalVar);
        buffer.append("\n");

        if(OptBrackets!=null)
            buffer.append(OptBrackets.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarIdentLocal]");
        return buffer.toString();
    }
}
