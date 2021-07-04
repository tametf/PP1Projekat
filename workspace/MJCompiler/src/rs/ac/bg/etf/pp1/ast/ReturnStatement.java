// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class ReturnStatement extends Statement {

    private StmtStart StmtStart;
    private Return Return;
    private Expr Expr;

    public ReturnStatement (StmtStart StmtStart, Return Return, Expr Expr) {
        this.StmtStart=StmtStart;
        if(StmtStart!=null) StmtStart.setParent(this);
        this.Return=Return;
        if(Return!=null) Return.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public StmtStart getStmtStart() {
        return StmtStart;
    }

    public void setStmtStart(StmtStart StmtStart) {
        this.StmtStart=StmtStart;
    }

    public Return getReturn() {
        return Return;
    }

    public void setReturn(Return Return) {
        this.Return=Return;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StmtStart!=null) StmtStart.accept(visitor);
        if(Return!=null) Return.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StmtStart!=null) StmtStart.traverseTopDown(visitor);
        if(Return!=null) Return.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StmtStart!=null) StmtStart.traverseBottomUp(visitor);
        if(Return!=null) Return.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReturnStatement(\n");

        if(StmtStart!=null)
            buffer.append(StmtStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Return!=null)
            buffer.append(Return.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnStatement]");
        return buffer.toString();
    }
}
