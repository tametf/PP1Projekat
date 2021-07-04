// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclarationListMore extends ConstDeclarationList {

    private ConstDeclarationList ConstDeclarationList;
    private ConstDeclOne ConstDeclOne;

    public ConstDeclarationListMore (ConstDeclarationList ConstDeclarationList, ConstDeclOne ConstDeclOne) {
        this.ConstDeclarationList=ConstDeclarationList;
        if(ConstDeclarationList!=null) ConstDeclarationList.setParent(this);
        this.ConstDeclOne=ConstDeclOne;
        if(ConstDeclOne!=null) ConstDeclOne.setParent(this);
    }

    public ConstDeclarationList getConstDeclarationList() {
        return ConstDeclarationList;
    }

    public void setConstDeclarationList(ConstDeclarationList ConstDeclarationList) {
        this.ConstDeclarationList=ConstDeclarationList;
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
        if(ConstDeclarationList!=null) ConstDeclarationList.accept(visitor);
        if(ConstDeclOne!=null) ConstDeclOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclarationList!=null) ConstDeclarationList.traverseTopDown(visitor);
        if(ConstDeclOne!=null) ConstDeclOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclarationList!=null) ConstDeclarationList.traverseBottomUp(visitor);
        if(ConstDeclOne!=null) ConstDeclOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclarationListMore(\n");

        if(ConstDeclarationList!=null)
            buffer.append(ConstDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclOne!=null)
            buffer.append(ConstDeclOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclarationListMore]");
        return buffer.toString();
    }
}
