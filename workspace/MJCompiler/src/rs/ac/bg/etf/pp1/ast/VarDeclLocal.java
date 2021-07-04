// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class VarDeclLocal implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private VarDeclarationListLocal VarDeclarationListLocal;

    public VarDeclLocal (Type Type, VarDeclarationListLocal VarDeclarationListLocal) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclarationListLocal=VarDeclarationListLocal;
        if(VarDeclarationListLocal!=null) VarDeclarationListLocal.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarDeclarationListLocal getVarDeclarationListLocal() {
        return VarDeclarationListLocal;
    }

    public void setVarDeclarationListLocal(VarDeclarationListLocal VarDeclarationListLocal) {
        this.VarDeclarationListLocal=VarDeclarationListLocal;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarDeclarationListLocal!=null) VarDeclarationListLocal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclarationListLocal!=null) VarDeclarationListLocal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclarationListLocal!=null) VarDeclarationListLocal.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclLocal(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclarationListLocal!=null)
            buffer.append(VarDeclarationListLocal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclLocal]");
        return buffer.toString();
    }
}
