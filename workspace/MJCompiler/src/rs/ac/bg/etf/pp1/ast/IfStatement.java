// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class IfStatement extends Statement {

    private StmtStart StmtStart;
    private BlockStart BlockStart;
    private IfCond IfCond;
    private Statement Statement;

    public IfStatement (StmtStart StmtStart, BlockStart BlockStart, IfCond IfCond, Statement Statement) {
        this.StmtStart=StmtStart;
        if(StmtStart!=null) StmtStart.setParent(this);
        this.BlockStart=BlockStart;
        if(BlockStart!=null) BlockStart.setParent(this);
        this.IfCond=IfCond;
        if(IfCond!=null) IfCond.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public StmtStart getStmtStart() {
        return StmtStart;
    }

    public void setStmtStart(StmtStart StmtStart) {
        this.StmtStart=StmtStart;
    }

    public BlockStart getBlockStart() {
        return BlockStart;
    }

    public void setBlockStart(BlockStart BlockStart) {
        this.BlockStart=BlockStart;
    }

    public IfCond getIfCond() {
        return IfCond;
    }

    public void setIfCond(IfCond IfCond) {
        this.IfCond=IfCond;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StmtStart!=null) StmtStart.accept(visitor);
        if(BlockStart!=null) BlockStart.accept(visitor);
        if(IfCond!=null) IfCond.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StmtStart!=null) StmtStart.traverseTopDown(visitor);
        if(BlockStart!=null) BlockStart.traverseTopDown(visitor);
        if(IfCond!=null) IfCond.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StmtStart!=null) StmtStart.traverseBottomUp(visitor);
        if(BlockStart!=null) BlockStart.traverseBottomUp(visitor);
        if(IfCond!=null) IfCond.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStatement(\n");

        if(StmtStart!=null)
            buffer.append(StmtStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(BlockStart!=null)
            buffer.append(BlockStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfCond!=null)
            buffer.append(IfCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStatement]");
        return buffer.toString();
    }
}
