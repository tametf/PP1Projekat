// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarationListLocalOne extends VarDeclarationListLocal {

    private VarDeclListOneLocal VarDeclListOneLocal;

    public VarDeclarationListLocalOne (VarDeclListOneLocal VarDeclListOneLocal) {
        this.VarDeclListOneLocal=VarDeclListOneLocal;
        if(VarDeclListOneLocal!=null) VarDeclListOneLocal.setParent(this);
    }

    public VarDeclListOneLocal getVarDeclListOneLocal() {
        return VarDeclListOneLocal;
    }

    public void setVarDeclListOneLocal(VarDeclListOneLocal VarDeclListOneLocal) {
        this.VarDeclListOneLocal=VarDeclListOneLocal;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclListOneLocal!=null) VarDeclListOneLocal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclListOneLocal!=null) VarDeclListOneLocal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclListOneLocal!=null) VarDeclListOneLocal.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarationListLocalOne(\n");

        if(VarDeclListOneLocal!=null)
            buffer.append(VarDeclListOneLocal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarationListLocalOne]");
        return buffer.toString();
    }
}
