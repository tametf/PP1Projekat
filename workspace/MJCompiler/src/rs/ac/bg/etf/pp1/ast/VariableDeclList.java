// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class VariableDeclList extends SpecDeclList {

    private SpecDeclList SpecDeclList;
    private VarDecl VarDecl;

    public VariableDeclList (SpecDeclList SpecDeclList, VarDecl VarDecl) {
        this.SpecDeclList=SpecDeclList;
        if(SpecDeclList!=null) SpecDeclList.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public SpecDeclList getSpecDeclList() {
        return SpecDeclList;
    }

    public void setSpecDeclList(SpecDeclList SpecDeclList) {
        this.SpecDeclList=SpecDeclList;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SpecDeclList!=null) SpecDeclList.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SpecDeclList!=null) SpecDeclList.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SpecDeclList!=null) SpecDeclList.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VariableDeclList(\n");

        if(SpecDeclList!=null)
            buffer.append(SpecDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VariableDeclList]");
        return buffer.toString();
    }
}
