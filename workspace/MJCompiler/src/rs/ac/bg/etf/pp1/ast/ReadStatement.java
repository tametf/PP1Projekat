// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class ReadStatement extends Statement {

    private StmtStart StmtStart;
    private Read Read;
    private Designator Designator;

    public ReadStatement (StmtStart StmtStart, Read Read, Designator Designator) {
        this.StmtStart=StmtStart;
        if(StmtStart!=null) StmtStart.setParent(this);
        this.Read=Read;
        if(Read!=null) Read.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public StmtStart getStmtStart() {
        return StmtStart;
    }

    public void setStmtStart(StmtStart StmtStart) {
        this.StmtStart=StmtStart;
    }

    public Read getRead() {
        return Read;
    }

    public void setRead(Read Read) {
        this.Read=Read;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StmtStart!=null) StmtStart.accept(visitor);
        if(Read!=null) Read.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StmtStart!=null) StmtStart.traverseTopDown(visitor);
        if(Read!=null) Read.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StmtStart!=null) StmtStart.traverseBottomUp(visitor);
        if(Read!=null) Read.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReadStatement(\n");

        if(StmtStart!=null)
            buffer.append(StmtStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Read!=null)
            buffer.append(Read.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReadStatement]");
        return buffer.toString();
    }
}
