// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclarationListOne extends ConstDeclarationList {

    private ConstDeclOne ConstDeclOne;

    public ConstDeclarationListOne (ConstDeclOne ConstDeclOne) {
        this.ConstDeclOne=ConstDeclOne;
        if(ConstDeclOne!=null) ConstDeclOne.setParent(this);
    }

    public ConstDeclOne getConstDeclOne() {
        return ConstDeclOne;
    }

    public void setConstDeclOne(ConstDeclOne ConstDeclOne) {
        this.ConstDeclOne=ConstDeclOne;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclOne!=null) ConstDeclOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclOne!=null) ConstDeclOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclOne!=null) ConstDeclOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclarationListOne(\n");

        if(ConstDeclOne!=null)
            buffer.append(ConstDeclOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclarationListOne]");
        return buffer.toString();
    }
}
