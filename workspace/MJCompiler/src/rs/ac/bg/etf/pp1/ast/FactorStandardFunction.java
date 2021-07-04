// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class FactorStandardFunction extends Factor {

    private StandardFunction StandardFunction;

    public FactorStandardFunction (StandardFunction StandardFunction) {
        this.StandardFunction=StandardFunction;
        if(StandardFunction!=null) StandardFunction.setParent(this);
    }

    public StandardFunction getStandardFunction() {
        return StandardFunction;
    }

    public void setStandardFunction(StandardFunction StandardFunction) {
        this.StandardFunction=StandardFunction;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StandardFunction!=null) StandardFunction.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StandardFunction!=null) StandardFunction.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StandardFunction!=null) StandardFunction.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorStandardFunction(\n");

        if(StandardFunction!=null)
            buffer.append(StandardFunction.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorStandardFunction]");
        return buffer.toString();
    }
}
