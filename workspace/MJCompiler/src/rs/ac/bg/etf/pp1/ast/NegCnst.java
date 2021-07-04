// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class NegCnst extends Factor {

    private Negation Negation;
    private ConstantFactor ConstantFactor;

    public NegCnst (Negation Negation, ConstantFactor ConstantFactor) {
        this.Negation=Negation;
        if(Negation!=null) Negation.setParent(this);
        this.ConstantFactor=ConstantFactor;
        if(ConstantFactor!=null) ConstantFactor.setParent(this);
    }

    public Negation getNegation() {
        return Negation;
    }

    public void setNegation(Negation Negation) {
        this.Negation=Negation;
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
        if(Negation!=null) Negation.accept(visitor);
        if(ConstantFactor!=null) ConstantFactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Negation!=null) Negation.traverseTopDown(visitor);
        if(ConstantFactor!=null) ConstantFactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Negation!=null) Negation.traverseBottomUp(visitor);
        if(ConstantFactor!=null) ConstantFactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NegCnst(\n");

        if(Negation!=null)
            buffer.append(Negation.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstantFactor!=null)
            buffer.append(ConstantFactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NegCnst]");
        return buffer.toString();
    }
}
