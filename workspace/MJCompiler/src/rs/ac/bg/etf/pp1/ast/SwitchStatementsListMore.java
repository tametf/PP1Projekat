// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class SwitchStatementsListMore extends SwitchStatementsList {

    private SwitchStatementsList SwitchStatementsList;
    private SwitchStatement SwitchStatement;

    public SwitchStatementsListMore (SwitchStatementsList SwitchStatementsList, SwitchStatement SwitchStatement) {
        this.SwitchStatementsList=SwitchStatementsList;
        if(SwitchStatementsList!=null) SwitchStatementsList.setParent(this);
        this.SwitchStatement=SwitchStatement;
        if(SwitchStatement!=null) SwitchStatement.setParent(this);
    }

    public SwitchStatementsList getSwitchStatementsList() {
        return SwitchStatementsList;
    }

    public void setSwitchStatementsList(SwitchStatementsList SwitchStatementsList) {
        this.SwitchStatementsList=SwitchStatementsList;
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
        if(SwitchStatementsList!=null) SwitchStatementsList.accept(visitor);
        if(SwitchStatement!=null) SwitchStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchStatementsList!=null) SwitchStatementsList.traverseTopDown(visitor);
        if(SwitchStatement!=null) SwitchStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchStatementsList!=null) SwitchStatementsList.traverseBottomUp(visitor);
        if(SwitchStatement!=null) SwitchStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchStatementsListMore(\n");

        if(SwitchStatementsList!=null)
            buffer.append(SwitchStatementsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SwitchStatement!=null)
            buffer.append(SwitchStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchStatementsListMore]");
        return buffer.toString();
    }
}
