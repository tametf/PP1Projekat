// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class SwitchStatementsListOne extends SwitchStatementsList {

    private SwitchStatement SwitchStatement;

    public SwitchStatementsListOne (SwitchStatement SwitchStatement) {
        this.SwitchStatement=SwitchStatement;
        if(SwitchStatement!=null) SwitchStatement.setParent(this);
    }

    public SwitchStatement getSwitchStatement() {
        return SwitchStatement;
    }

    public void setSwitchStatement(SwitchStatement SwitchStatement) {
        this.SwitchStatement=SwitchStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SwitchStatement!=null) SwitchStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchStatement!=null) SwitchStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchStatement!=null) SwitchStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchStatementsListOne(\n");

        if(SwitchStatement!=null)
            buffer.append(SwitchStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchStatementsListOne]");
        return buffer.toString();
    }
}
