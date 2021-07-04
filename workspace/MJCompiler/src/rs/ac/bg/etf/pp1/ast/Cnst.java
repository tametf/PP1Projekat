// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class Cnst extends Factor {

    private ConstantFactor ConstantFactor;

    public Cnst (ConstantFactor ConstantFactor) {
        this.ConstantFactor=ConstantFactor;
        if(ConstantFactor!=null) ConstantFactor.setParent(this);
    }

    public ConstantFactor getConstantFactor() {
        return ConstantFactor;
    }

    public void setConstantFactor(ConstantFactor ConstantFactor) {
        this.ConstantFactor=ConstantFactor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstantFactor!=null) ConstantFactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstantFactor!=null) ConstantFactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstantFactor!=null) ConstantFactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Cnst(\n");

        if(ConstantFactor!=null)
            buffer.append(ConstantFactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Cnst]");
        return buffer.toString();
    }
}
