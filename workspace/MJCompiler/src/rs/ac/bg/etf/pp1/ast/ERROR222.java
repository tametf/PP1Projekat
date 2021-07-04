// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class ERROR222 extends Statement {

    private ErrorHelper ErrorHelper;

    public ERROR222 (ErrorHelper ErrorHelper) {
        this.ErrorHelper=ErrorHelper;
        if(ErrorHelper!=null) ErrorHelper.setParent(this);
    }

    public ErrorHelper getErrorHelper() {
        return ErrorHelper;
    }

    public void setErrorHelper(ErrorHelper ErrorHelper) {
        this.ErrorHelper=ErrorHelper;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ErrorHelper!=null) ErrorHelper.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ErrorHelper!=null) ErrorHelper.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ErrorHelper!=null) ErrorHelper.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ERROR222(\n");

        if(ErrorHelper!=null)
            buffer.append(ErrorHelper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ERROR222]");
        return buffer.toString();
    }
}
