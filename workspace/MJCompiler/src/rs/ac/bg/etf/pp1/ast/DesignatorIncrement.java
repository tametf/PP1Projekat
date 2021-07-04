// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class DesignatorIncrement extends DesignatorStatement {

    private Designator Designator;
    private Inc Inc;
    private ErrorHelper ErrorHelper;

    public DesignatorIncrement (Designator Designator, Inc Inc, ErrorHelper ErrorHelper) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Inc=Inc;
        if(Inc!=null) Inc.setParent(this);
        this.ErrorHelper=ErrorHelper;
        if(ErrorHelper!=null) ErrorHelper.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Inc getInc() {
        return Inc;
    }

    public void setInc(Inc Inc) {
        this.Inc=Inc;
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
        if(Designator!=null) Designator.accept(visitor);
        if(Inc!=null) Inc.accept(visitor);
        if(ErrorHelper!=null) ErrorHelper.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Inc!=null) Inc.traverseTopDown(visitor);
        if(ErrorHelper!=null) ErrorHelper.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Inc!=null) Inc.traverseBottomUp(visitor);
        if(ErrorHelper!=null) ErrorHelper.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorIncrement(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Inc!=null)
            buffer.append(Inc.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ErrorHelper!=null)
            buffer.append(ErrorHelper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorIncrement]");
        return buffer.toString();
    }
}
