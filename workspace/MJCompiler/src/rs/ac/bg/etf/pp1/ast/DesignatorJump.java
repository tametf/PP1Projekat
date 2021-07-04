// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class DesignatorJump extends DesignatorStatement {

    private Jump Jump;
    private Integer num;

    public DesignatorJump (Jump Jump, Integer num) {
        this.Jump=Jump;
        if(Jump!=null) Jump.setParent(this);
        this.num=num;
    }

    public Jump getJump() {
        return Jump;
    }

    public void setJump(Jump Jump) {
        this.Jump=Jump;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num=num;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Jump!=null) Jump.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Jump!=null) Jump.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Jump!=null) Jump.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorJump(\n");

        if(Jump!=null)
            buffer.append(Jump.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+num);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorJump]");
        return buffer.toString();
    }
}
