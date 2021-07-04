// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class IfCondition extends IfCond {

    private Lparen Lparen;
    private Condition Condition;
    private Rparen Rparen;

    public IfCondition (Lparen Lparen, Condition Condition, Rparen Rparen) {
        this.Lparen=Lparen;
        if(Lparen!=null) Lparen.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.Rparen=Rparen;
        if(Rparen!=null) Rparen.setParent(this);
    }

    public Lparen getLparen() {
        return Lparen;
    }

    public void setLparen(Lparen Lparen) {
        this.Lparen=Lparen;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public Rparen getRparen() {
        return Rparen;
    }

    public void setRparen(Rparen Rparen) {
        this.Rparen=Rparen;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Lparen!=null) Lparen.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(Rparen!=null) Rparen.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Lparen!=null) Lparen.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(Rparen!=null) Rparen.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Lparen!=null) Lparen.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(Rparen!=null) Rparen.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfCondition(\n");

        if(Lparen!=null)
            buffer.append(Lparen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Rparen!=null)
            buffer.append(Rparen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfCondition]");
        return buffer.toString();
    }
}
