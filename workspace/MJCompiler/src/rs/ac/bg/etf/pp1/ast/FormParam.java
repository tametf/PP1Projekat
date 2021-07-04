// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class FormParam extends FormParsList {

    private FormParsOne FormParsOne;

    public FormParam (FormParsOne FormParsOne) {
        this.FormParsOne=FormParsOne;
        if(FormParsOne!=null) FormParsOne.setParent(this);
    }

    public FormParsOne getFormParsOne() {
        return FormParsOne;
    }

    public void setFormParsOne(FormParsOne FormParsOne) {
        this.FormParsOne=FormParsOne;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParsOne!=null) FormParsOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsOne!=null) FormParsOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsOne!=null) FormParsOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParam(\n");

        if(FormParsOne!=null)
            buffer.append(FormParsOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParam]");
        return buffer.toString();
    }
}
