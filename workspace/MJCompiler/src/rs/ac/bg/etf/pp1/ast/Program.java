// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class Program implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ProgName ProgName;
    private SpecDeclList SpecDeclList;
    private Lbrace Lbrace;
    private MethodDeclList MethodDeclList;
    private EndOfProgram EndOfProgram;

    public Program (ProgName ProgName, SpecDeclList SpecDeclList, Lbrace Lbrace, MethodDeclList MethodDeclList, EndOfProgram EndOfProgram) {
        this.ProgName=ProgName;
        if(ProgName!=null) ProgName.setParent(this);
        this.SpecDeclList=SpecDeclList;
        if(SpecDeclList!=null) SpecDeclList.setParent(this);
        this.Lbrace=Lbrace;
        if(Lbrace!=null) Lbrace.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
        this.EndOfProgram=EndOfProgram;
        if(EndOfProgram!=null) EndOfProgram.setParent(this);
    }

    public ProgName getProgName() {
        return ProgName;
    }

    public void setProgName(ProgName ProgName) {
        this.ProgName=ProgName;
    }

    public SpecDeclList getSpecDeclList() {
        return SpecDeclList;
    }

    public void setSpecDeclList(SpecDeclList SpecDeclList) {
        this.SpecDeclList=SpecDeclList;
    }

    public Lbrace getLbrace() {
        return Lbrace;
    }

    public void setLbrace(Lbrace Lbrace) {
        this.Lbrace=Lbrace;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public EndOfProgram getEndOfProgram() {
        return EndOfProgram;
    }

    public void setEndOfProgram(EndOfProgram EndOfProgram) {
        this.EndOfProgram=EndOfProgram;
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
        if(ProgName!=null) ProgName.accept(visitor);
        if(SpecDeclList!=null) SpecDeclList.accept(visitor);
        if(Lbrace!=null) Lbrace.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
        if(EndOfProgram!=null) EndOfProgram.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgName!=null) ProgName.traverseTopDown(visitor);
        if(SpecDeclList!=null) SpecDeclList.traverseTopDown(visitor);
        if(Lbrace!=null) Lbrace.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
        if(EndOfProgram!=null) EndOfProgram.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgName!=null) ProgName.traverseBottomUp(visitor);
        if(SpecDeclList!=null) SpecDeclList.traverseBottomUp(visitor);
        if(Lbrace!=null) Lbrace.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        if(EndOfProgram!=null) EndOfProgram.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program(\n");

        if(ProgName!=null)
            buffer.append(ProgName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SpecDeclList!=null)
            buffer.append(SpecDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Lbrace!=null)
            buffer.append(Lbrace.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EndOfProgram!=null)
            buffer.append(EndOfProgram.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program]");
        return buffer.toString();
    }
}
