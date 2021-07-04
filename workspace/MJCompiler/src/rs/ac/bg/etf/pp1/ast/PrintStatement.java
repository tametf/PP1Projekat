// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class PrintStatement extends Statement {

    private StmtStart StmtStart;
    private Print Print;
    private Expr Expr;
    private OptNumConst OptNumConst;

    public PrintStatement (StmtStart StmtStart, Print Print, Expr Expr, OptNumConst OptNumConst) {
        this.StmtStart=StmtStart;
        if(StmtStart!=null) StmtStart.setParent(this);
        this.Print=Print;
        if(Print!=null) Print.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.OptNumConst=OptNumConst;
        if(OptNumConst!=null) OptNumConst.setParent(this);
    }

    public StmtStart getStmtStart() {
        return StmtStart;
    }

    public void setStmtStart(StmtStart StmtStart) {
        this.StmtStart=StmtStart;
    }

    public Print getPrint() {
        return Print;
    }

    public void setPrint(Print Print) {
        this.Print=Print;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public OptNumConst getOptNumConst() {
        return OptNumConst;
    }

    public void setOptNumConst(OptNumConst OptNumConst) {
        this.OptNumConst=OptNumConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StmtStart!=null) StmtStart.accept(visitor);
        if(Print!=null) Print.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(OptNumConst!=null) OptNumConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StmtStart!=null) StmtStart.traverseTopDown(visitor);
        if(Print!=null) Print.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(OptNumConst!=null) OptNumConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StmtStart!=null) StmtStart.traverseBottomUp(visitor);
        if(Print!=null) Print.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(OptNumConst!=null) OptNumConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatement(\n");

        if(StmtStart!=null)
            buffer.append(StmtStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Print!=null)
            buffer.append(Print.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptNumConst!=null)
            buffer.append(OptNumConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatement]");
        return buffer.toString();
    }
}
