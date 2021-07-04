// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class DesignatorDecrement extends DesignatorStatement {

    private Designator Designator;
    private Dec Dec;
    private ErrorHelper ErrorHelper;

    public DesignatorDecrement (Designator Designator, Dec Dec, ErrorHelper ErrorHelper) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Dec=Dec;
        if(Dec!=null) Dec.setParent(this);
        this.ErrorHelper=ErrorHelper;
        if(ErrorHelper!=null) ErrorHelper.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Dec getDec() {
        return Dec;
    }

    public void setDec(Dec Dec) {
        this.Dec=Dec;
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
        if(Dec!=null) Dec.accept(visitor);
        if(ErrorHelper!=null) ErrorHelper.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Dec!=null) Dec.traverseTopDown(visitor);
        if(ErrorHelper!=null) ErrorHelper.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Dec!=null) Dec.traverseBottomUp(visitor);
        if(ErrorHelper!=null) ErrorHelper.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorDecrement(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Dec!=null)
            buffer.append(Dec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ErrorHelper!=null)
            buffer.append(ErrorHelper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorDecrement]");
        return buffer.toString();
    }
}
