package ast;

import ast.statement.*;
import ast.typeNode.ArrayType;
import ast.typeNode.IdType;
import ast.exp.BinExpNode;
import ast.exp.DerExpNode;
import ast.exp.ValExpNode;
import ast.typeNode.VoidType;
import gen.*;
import org.antlr.v4.runtime.misc.Pair;
import ast.typeNode.TypeNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import utilities.Utils;

import java.util.ArrayList;

import gen.HLCostLanParser.*;


public class HLCostLanBaseVisitorImpl extends HLCostLanBaseVisitor<Node> {
    @Override
    public Node visitPrg(PrgContext ctx) {
        ArrayList<Node> complexType = new ArrayList<>();
        ArrayList<Node> decServices = new ArrayList<>();
        ArrayList<Node> funDec = new ArrayList<>();

        for (ServiceDeclContext decService : ctx.serviceDecl()){
            decServices.add(visitServiceDecl(decService));
        }

        for (FunctionDeclContext fund : ctx.functionDecl()) {
            funDec.add(visitFunctionDecl(fund));
        }

        for(ComplexTypeContext complexTypeContext : ctx.complexType()){
            complexType.add(visitComplexType(complexTypeContext));
        }
        return new ProgramNode(complexType, decServices, funDec, visitInit(ctx.init()));
    }

    @Override
    public Node visitInit(InitContext ctx) {
        FormalParams formalParamsNode =ctx.formalParams() != null ? visitFormalParams(ctx.formalParams()) : null;

        return new FunDeclarationNode(new VoidType(),new IdNode("main"),formalParamsNode,visitStm(ctx.stm()), ctx.start.getLine());
    }

    @Override
    public Node visitFunctionDecl(FunctionDeclContext ctx) {
        String id = ctx.ID().getText();
        if(id.equals("main")) {
            System.err.println("Error: main function is reserved");
            System.exit(1);
        }
        FormalParams formalParamsNode;

        ReturnTypeNode returnType;
        
        formalParamsNode =ctx.formalParams() != null ? visitFormalParams(ctx.formalParams()) : null;

        if(ctx.type()!= null){
            returnType = new ReturnTypeNode(Utils.castType(ctx.type().basictype()));
        }else{
            returnType = new ReturnTypeNode(new IdNode(ctx.ID().getText()));
        }
        Node stm = visitStm(ctx.stm());

        return new FunDeclarationNode( returnType,new IdNode(id),formalParamsNode,stm, ctx.start.getLine());
    }

    @Override
    public Node visitServiceDecl(ServiceDeclContext ctx) {

        IdNode id = new IdNode(ctx.ID().getText());
        ArrayList<Pair<IdNode,TypeNode>> params = new ArrayList<>();
        for(int i = 0;i< ctx.type().size()-1;i++){
            if(ctx.type(i).basictype()!= null)
                params.add(new Pair<IdNode, TypeNode>(null,Utils.castType(ctx.type(i).basictype())));
            else
                params.add(new Pair<IdNode, TypeNode>(new IdNode(ctx.type(i).ID().getText()),null));

            }
        ReturnTypeNode returnType;
        if(ctx.type(ctx.type().size()-1).basictype() == null)
            returnType = new ReturnTypeNode(new IdNode(ctx.type(ctx.type().size()-1).ID().getText()));
        else
            returnType = new ReturnTypeNode(Utils.castType(ctx.type(ctx.type().size()-1).basictype()));
        return new DecService(id,params,returnType);
    }

    @Override
    public Node visitStm(StmContext ctx) {
        if(ctx.cond() != null && ctx.stm().size() == 2){
            // 'if' ast.exp 'then' stm 'else' stm
            int line = ctx.start.getLine();

            if(ctx.cond()!= null && ctx.cond().ID() != null){
                ArrayList<Node> actualParams = new ArrayList<>();
                for (ExpContext expContext : ctx.cond().exp()) {
                    actualParams.add(visit(expContext));
                }
                CallServiceNode callServiceNode = new CallServiceNode(ctx.cond().ID().getText(),actualParams,null);
                return new IfNode(visitCond(ctx.cond()),visitStm(ctx.stm(0)),visitStm(ctx.stm(1)),line);
            }else{
                Node exp = visitCond(ctx.cond());

                Node stm1 = visit(ctx.stm(0));
                Node stm2 = visit(ctx.stm(1));

                return new IfNode(exp,stm1,stm2,line);
            }
        }
        //'for' '('ID 'in' '(' '0'','ast.exp ')' ')' '{' stm '}'   /
        if(ctx.ID() != null && ctx.exp() != null && ctx.stm() != null){
            String id = ctx.ID().getText();
            Node exp = (Node) visit(ctx.exp());
            Node stm = visitStm(ctx.stm().get(0));
            int line = ctx.start.getLine();
            return new ForNode(id,exp,stm,line);
        }

        if(ctx.callService() != null){
            return visitCallService(ctx.callService());
        }

        //let in
        if(ctx.letIn() != null){
            return visitLetIn(ctx.letIn());
        }

        if(ctx.ID() != null && ctx.listExp() != null){
            String id = ctx.ID().getText();
            ArrayList<Node> listExp = new ArrayList<>();
            for (ExpContext expContext : ctx.listExp().exp()) {
                listExp.add(visit(expContext));
            }
            return new CallNode(new IdNode(id),listExp);
        }

        if (ctx.structAssignment()!= null){
            ArrayList<AssignmentNodeIn> structAssignment = new ArrayList<>();
                StructAssignmentContext assignmentNode = ctx.structAssignment();
                for (int j = 0; j < ctx.structAssignment().assign().size(); j++) {
                    if(assignmentNode.assign(j).exp() != null)
                        structAssignment.add(new AssignmentNodeIn(new IdNode(assignmentNode.ID(0).getText()),new IdNode(assignmentNode.ID(1).getText()),visit(assignmentNode.assign(j).exp())));
                    else
                        structAssignment.add(new AssignmentNodeIn(new IdNode(assignmentNode.ID(0).getText()),new IdNode(assignmentNode.ID(1).getText()),visitStm(assignmentNode.assign(j).stm())));

            }
            return new ListAssignmentMain(structAssignment);
        }

        if(ctx.exp() != null){
            return visit(ctx.exp());
        }

        return super.visitStm(ctx);
    }

    @Override
    public LetInNode visitLetIn(LetInContext ctx) {

        ArrayList<LetAssignmentNode> listAssignment = new ArrayList<>();
        ArrayList<AssignmentNodeIn> structAssignment = new ArrayList<>();

        for( AssignmentContext assignmentNode: ctx.assignment()){
            IdNode idType;
            if(assignmentNode.structType() != null)
                idType = new IdNode(assignmentNode.structType().ID().getText());
            else
                idType = null;
            for (int j = 0; j < assignmentNode.ID().size(); j++) {
                if(assignmentNode.assign(j).exp() != null)
                    listAssignment.add(new LetAssignmentNode(idType,new IdNode(assignmentNode.ID(j).getText()),visit(assignmentNode.assign(j).exp())));
                else
                    listAssignment.add(new LetAssignmentNode(idType,new IdNode(assignmentNode.ID(j).getText()),visitStm(assignmentNode.assign(j).stm())));
            }
        }
        for( StructAssignmentContext assignmentNode: ctx.structAssignment()){
            for (int j = 1; j < assignmentNode.ID().size(); j++) {
                if(assignmentNode.assign(j-1).exp() != null)
                    structAssignment.add(new AssignmentNodeIn(new IdNode(assignmentNode.ID(0).getText()),new IdNode(assignmentNode.ID(j).getText()),visit(assignmentNode.assign(j-1).exp())));
                else
                    structAssignment.add(new AssignmentNodeIn(new IdNode(assignmentNode.ID(0).getText()),new IdNode(assignmentNode.ID(j).getText()),visitStm(assignmentNode.assign(j-1).stm())));
            }
        }
        Node stm = visitStm(ctx.stm());

        return new LetInNode(listAssignment,structAssignment,stm);
    }


    @Override
    public Node visitCond(CondContext ctx) {
       if(ctx.ID()!= null){
           //Call Service
            ArrayList<Node> actualParams = new ArrayList<>();
            for (ExpContext expContext : ctx.exp()) {
                actualParams.add(visit(expContext));
            }
            return new CallServiceNode(ctx.ID().getText(),actualParams,null);
       }else{
              //BinExp
               return(visit(ctx.exp(0)));

       }
    }

    @Override
    public Node visitListExp(ListExpContext ctx) {
        ArrayList<Node> listExp = new ArrayList<>();
        for (ExpContext expContext : ctx.exp()) {
            listExp.add(visit(expContext));
        }
        return super.visitListExp(ctx);
    }

    @Override
    public Node visitCallFun(CallFunContext ctx) {

        ArrayList<String> idList = new ArrayList<>();
        for (TerminalNode id : ctx.ID()) {
            idList.add(id.getText());
        }
        return new CallFunNode(idList);
    }

    @Override
    public Node visitBinExp(BinExpContext ctx) {
        return new BinExpNode(visit(ctx.exp(0)),visit(ctx.exp(1)),ctx.op.getText());
    }

    @Override
    public Node visitComplexType(ComplexTypeContext ctx) {

        if(ctx.ID()!= null){
            //Struct Node
            IdNode id = new IdNode(ctx.ID(0).getText());
            ArrayList<Pair<IdNode,TypeNode>> params = new ArrayList<>();
            for(int i = 1;i<ctx.ID().size();i++){
                if(ctx.arrayType(i-1).typeArr() != null){
                    //ArrayNode
                    ArrayType arr = new ArrayType(Utils.castType(ctx.arrayType(i-1).typeArr().type().basictype()));
                    params.add(new Pair<IdNode, TypeNode>(new IdNode(ctx.ID(i).getText()),arr));
                }else
                    params.add(new Pair<IdNode, TypeNode>(new IdNode(ctx.ID(i).getText()),Utils.castType(ctx.arrayType(i-1).type().basictype())));
            }
            return new StructNode(id,params);
        }
        return super.visitComplexType(ctx);
    }
    public Node visitCallService(CallServiceContext ctx) {
        if(ctx.ID() != null && ctx.exp() != null) {
            //'call'ID'('ast.exp(','ast.exp)*')' stm?';'
            String idCall = ctx.ID().getText();
            ArrayList<Node> exp = new ArrayList<>();
            for (ExpContext expContext : ctx.exp()) {
                exp.add(visit(expContext));
            }
            if (ctx.stm() != null)
                return new CallServiceNode(idCall, exp, visit(ctx.stm()));

            return new CallServiceNode(idCall, exp);
        }
        return null;
    }

    public Node visitDerExp(DerExpContext ctx) {
        return new DerExpNode(ctx.ID().getText());
    }
    public FormalParams visitFormalParams(FormalParamsContext ctx) {
        ArrayList<Pair<String, TypeNode>> formalParamsList = new ArrayList<>();

        if(!ctx.isEmpty()){
            for(int i = 0; i< ctx.ID().size();i++){
                int j =ctx.ID().size();
                int count = ctx.type().size();

                if(ctx.type(i).basictype() == null){
                    formalParamsList.add(new Pair<String, TypeNode>(ctx.ID(i).getText(),new IdType(ctx.type(i).ID().getText())));
                }else{
                    formalParamsList.add(new Pair<String, TypeNode>(ctx.ID(i).getText(),Utils.castType(ctx.type(i).basictype())));
                }
            }

        }
        return new FormalParams(formalParamsList);
    }

    @Override
    public Node visitValExp(ValExpContext ctx) {
        return new ValExpNode(ctx.getText());
    }
    @Override
    public Node visitStringExp(StringExpContext ctx) {
        return new StringNode(ctx.getText());
    }
}
