// generated with ast extension for cup
// version 0.8
// 3/6/2021 15:2:53


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclaration extends MethodDecl {

    private ReturnValueType ReturnValueType;
    private FormPars FormPars;
    private VarDeclList VarDeclList;
    private Lbrace Lbrace;
    private StatementList StatementList;
    private EndOfMethod EndOfMethod;

    public MethodDeclaration (ReturnValueType ReturnValueType, FormPars FormPars, VarDeclList VarDeclList, Lbrace Lbrace, StatementList StatementList, EndOfMethod EndOfMethod) {
        this.ReturnValueType=ReturnValueType;
        if(ReturnValueType!=null) ReturnValueType.setParent(this);
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.Lbrace=Lbrace;
        if(Lbrace!=null) Lbrace.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
        this.EndOfMethod=EndOfMethod;
        if(EndOfMethod!=null) EndOfMethod.setParent(this);
    }

    public ReturnValueType getReturnValueType() {
        return ReturnValueType;
    }

    public void setReturnValueType(ReturnValueType ReturnValueType) {
        this.ReturnValueType=ReturnValueType;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public Lbrace getLbrace() {
        return Lbrace;
    }

    public void setLbrace(Lbrace Lbrace) {
        this.Lbrace=Lbrace;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public EndOfMethod getEndOfMethod() {
        return EndOfMethod;
    }

    public void setEndOfMethod(EndOfMethod EndOfMethod) {
        this.EndOfMethod=EndOfMethod;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ReturnValueType!=null) ReturnValueType.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(Lbrace!=null) Lbrace.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
        if(EndOfMethod!=null) EndOfMethod.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReturnValueType!=null) ReturnValueType.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(Lbrace!=null) Lbrace.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
        if(EndOfMethod!=null) EndOfMethod.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReturnValueType!=null) ReturnValueType.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(Lbrace!=null) Lbrace.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        if(EndOfMethod!=null) EndOfMethod.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclaration(\n");

        if(ReturnValueType!=null)
            buffer.append(ReturnValueType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Lbrace!=null)
            buffer.append(Lbrace.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EndOfMethod!=null)
            buffer.append(EndOfMethod.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclaration]");
        return buffer.toString();
    }
}
