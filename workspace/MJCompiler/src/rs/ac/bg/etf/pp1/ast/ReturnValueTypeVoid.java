// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class ReturnValueTypeVoid extends ReturnValueType {

    private String nameOfMethod;

    public ReturnValueTypeVoid (String nameOfMethod) {
        this.nameOfMethod=nameOfMethod;
    }

    public String getNameOfMethod() {
        return nameOfMethod;
    }

    public void setNameOfMethod(String nameOfMethod) {
        this.nameOfMethod=nameOfMethod;
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
        buffer.append("ReturnValueTypeVoid(\n");

        buffer.append(" "+tab+nameOfMethod);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnValueTypeVoid]");
        return buffer.toString();
    }
}
