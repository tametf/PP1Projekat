// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class RelopLessE extends Relop {

    public RelopLessE () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RelopLessE(\n");

        buffer.append(tab);
        buffer.append(") [RelopLessE]");
        return buffer.toString();
    }
}