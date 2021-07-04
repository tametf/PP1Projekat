// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class SwitchExpr extends Expr1 {

    private Switch Switch;
    private Expr Expr;
    private SwitchStatementsList SwitchStatementsList;

    public SwitchExpr (Switch Switch, Expr Expr, SwitchStatementsList SwitchStatementsList) {
        this.Switch=Switch;
        if(Switch!=null) Switch.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.SwitchStatementsList=SwitchStatementsList;
        if(SwitchStatementsList!=null) SwitchStatementsList.setParent(this);
    }

    public Switch getSwitch() {
        return Switch;
    }

    public void setSwitch(Switch Switch) {
        this.Switch=Switch;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public SwitchStatementsList getSwitchStatementsList() {
        return SwitchStatementsList;
    }

    public void setSwitchStatementsList(SwitchStatementsList SwitchStatementsList) {
        this.SwitchStatementsList=SwitchStatementsList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Switch!=null) Switch.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(SwitchStatementsList!=null) SwitchStatementsList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Switch!=null) Switch.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(SwitchStatementsList!=null) SwitchStatementsList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Switch!=null) Switch.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(SwitchStatementsList!=null) SwitchStatementsList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchExpr(\n");

        if(Switch!=null)
            buffer.append(Switch.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SwitchStatementsList!=null)
            buffer.append(SwitchStatementsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchExpr]");
        return buffer.toString();
    }
}
