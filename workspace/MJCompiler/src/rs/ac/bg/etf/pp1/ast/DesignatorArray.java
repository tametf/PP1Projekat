// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class DesignatorArray extends Designator {

    private DesignatorIdent DesignatorIdent;
    private Lbracket Lbracket;
    private Expr Expr;
    private Rbracket Rbracket;

    public DesignatorArray (DesignatorIdent DesignatorIdent, Lbracket Lbracket, Expr Expr, Rbracket Rbracket) {
        this.DesignatorIdent=DesignatorIdent;
        if(DesignatorIdent!=null) DesignatorIdent.setParent(this);
        this.Lbracket=Lbracket;
        if(Lbracket!=null) Lbracket.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.Rbracket=Rbracket;
        if(Rbracket!=null) Rbracket.setParent(this);
    }

    public DesignatorIdent getDesignatorIdent() {
        return DesignatorIdent;
    }

    public void setDesignatorIdent(DesignatorIdent DesignatorIdent) {
        this.DesignatorIdent=DesignatorIdent;
    }

    public Lbracket getLbracket() {
        return Lbracket;
    }

    public void setLbracket(Lbracket Lbracket) {
        this.Lbracket=Lbracket;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public Rbracket getRbracket() {
        return Rbracket;
    }

    public void setRbracket(Rbracket Rbracket) {
        this.Rbracket=Rbracket;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorIdent!=null) DesignatorIdent.accept(visitor);
        if(Lbracket!=null) Lbracket.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(Rbracket!=null) Rbracket.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorIdent!=null) DesignatorIdent.traverseTopDown(visitor);
        if(Lbracket!=null) Lbracket.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(Rbracket!=null) Rbracket.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorIdent!=null) DesignatorIdent.traverseBottomUp(visitor);
        if(Lbracket!=null) Lbracket.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(Rbracket!=null) Rbracket.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArray(\n");

        if(DesignatorIdent!=null)
            buffer.append(DesignatorIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Lbracket!=null)
            buffer.append(Lbracket.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Rbracket!=null)
            buffer.append(Rbracket.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorArray]");
        return buffer.toString();
    }
}
