// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class VarDeclListLocalOne extends VarDeclListOneLocal {

    private VarIdentLocal VarIdentLocal;

    public VarDeclListLocalOne (VarIdentLocal VarIdentLocal) {
        this.VarIdentLocal=VarIdentLocal;
        if(VarIdentLocal!=null) VarIdentLocal.setParent(this);
    }

    public VarIdentLocal getVarIdentLocal() {
        return VarIdentLocal;
    }

    public void setVarIdentLocal(VarIdentLocal VarIdentLocal) {
        this.VarIdentLocal=VarIdentLocal;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarIdentLocal!=null) VarIdentLocal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarIdentLocal!=null) VarIdentLocal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarIdentLocal!=null) VarIdentLocal.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclListLocalOne(\n");

        if(VarIdentLocal!=null)
            buffer.append(VarIdentLocal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclListLocalOne]");
        return buffer.toString();
    }
}
