// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class DesignatorNoArray extends Designator {

    private DesignatorIdent DesignatorIdent;

    public DesignatorNoArray (DesignatorIdent DesignatorIdent) {
        this.DesignatorIdent=DesignatorIdent;
        if(DesignatorIdent!=null) DesignatorIdent.setParent(this);
    }

    public DesignatorIdent getDesignatorIdent() {
        return DesignatorIdent;
    }

    public void setDesignatorIdent(DesignatorIdent DesignatorIdent) {
        this.DesignatorIdent=DesignatorIdent;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorIdent!=null) DesignatorIdent.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorIdent!=null) DesignatorIdent.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorIdent!=null) DesignatorIdent.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorNoArray(\n");

        if(DesignatorIdent!=null)
            buffer.append(DesignatorIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorNoArray]");
        return buffer.toString();
    }
}
