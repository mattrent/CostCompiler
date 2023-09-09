package ast;

import ast.typeNode.ArrayType;
import exp.BinExpNode;
import exp.DerExpNode;
import exp.ValNode;
import gen.*;
import org.antlr.v4.runtime.misc.Pair;
import ast.typeNode.TypeNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import utilities.Utils;

import java.util.ArrayList;
import java.util.List;

import gen.HLCostLanParser.*;


public class HLCostLanBaseVisitorImpl extends HLCostLanBaseVisitor<Node> {
    @Override
    public Node visitMain(MainContext ctx) {
        ArrayList<Node> complexType = new ArrayList<>();
        ArrayList<Node> decServices = new ArrayList<>();
        ArrayList<Node> funDec = new ArrayList<>();

        for (DeclarationServiceContext decService : ctx.declarationService()) {
            decServices.add(visitDeclarationService(decService));
        }

        for (FundContext fund : ctx.fund()) {
            funDec.add(visitFund(fund));
        }



        return new MainProgramNode(complexType, decServices, funDec);
    }

    @Override
    public Node visitFund(FundContext ctx) {
        String id = ctx.ID().get(0).getText();
        FormalParams formalParamsNode;

        ReturnTypeNode returnType;
        
        formalParamsNode = visitFormalParams(ctx.formalParams());

        if(ctx.type()!= null){
            returnType = new ReturnTypeNode(Utils.castType(ctx.type()));
        }else{
            returnType = new ReturnTypeNode(new IdNode(ctx.ID(1).getText()));
        }
        Node stm = visitStm(ctx.stm());

        return new FunDeclarationNode( returnType,new IdNode(id),formalParamsNode,stm);
    }

    @Override
    public Node visitDeclarationService(DeclarationServiceContext ctx) {

        IdNode id = new IdNode(ctx.ID(0).getText());
        ArrayList<Pair<IdNode,TypeNode>> params = new ArrayList<>();
        for(int i = 1;i<ctx.ID().size();i++){
            params.add(new Pair<IdNode, TypeNode>(new IdNode(ctx.ID(i).getText()),Utils.castType(ctx.type(i-1))));
        }

        ReturnTypeNode returnType = new ReturnTypeNode(Utils.castType(ctx.type(ctx.type().size()-1)));
        return new DecService(id,params,returnType);
    }

    @Override
    public Node visitStm(StmContext ctx) {
        if(ctx.cond() != null && ctx.stm().size() == 2){
            // 'if' exp 'then' stm 'else' stm
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
        //'for' '('ID 'in' '(' '0'','exp ')' ')' '{' stm '}'   /
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

        if(ctx.ID() != null && ctx.listCount() != null){
            String id = ctx.ID().getText();
            Node listCount = visitListCount(ctx.listCount());
            return new CallNode(new IdNode(id),listCount);
        }
        return super.visitStm(ctx);
    }

    @Override
    public LetInNode visitLetIn(LetInContext ctx) {
        ArrayList<AssignmentNode> listAssignment = new ArrayList<>();
        ArrayList<AssignmentNode> structAssignment = new ArrayList<>();


        for( AssignmentContext assignmentNode: ctx.assignment()){
            for (int j = 0; j < assignmentNode.ID().size(); j++) {
                listAssignment.add(new AssignmentNode(new IdNode(assignmentNode.ID(j).getText()),visit(assignmentNode.exp(j))));
            }
        }

        for( StructAssignmentContext assignmentNode: ctx.structAssignment()){
            for (int j = 0; j < assignmentNode.ID().size(); j++) {
                listAssignment.add(new AssignmentNode(new IdNode(assignmentNode.ID(j).getText()),visit(assignmentNode.exp(j))));
            }
        }

        Node stm = visitStm(ctx.stm());

        return new LetInNode(listAssignment,structAssignment,stm);
    }

    @Override
    public Node visitStringExp(StringExpContext ctx) {
        return new StringNode(ctx.getText());
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
    public Node visitListCount(ListCountContext ctx) {

        return super.visitListCount(ctx);
    }

    @Override
    public Node visitListExp(ListExpContext ctx) {
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
                if(ctx.arrayType(i).typeArr() != null){
                    //ArrayNode
                    ArrayType arr = new ArrayType(Utils.castType(ctx.arrayType(i).typeArr().type()));
                    params.add(new Pair<IdNode, TypeNode>(new IdNode(ctx.ID(i).getText()),arr));
                }else
                    params.add(new Pair<IdNode, TypeNode>(new IdNode(ctx.ID(i).getText()),Utils.castType(ctx.arrayType(i-1).type())));
            }

            return new StructNode(id,params);
        }else{
            //ArrayNode
            ArrayType arr = new ArrayType(Utils.castType(ctx.typeArr().type()));
            return arr;
        }
    }

    public Node visitCallService(CallServiceContext ctx) {
        if(ctx.ID() != null && ctx.exp() != null) {
            //'call'ID'('exp(','exp)*')' stm?';'
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
                formalParamsList.add(new Pair<>(ctx.ID(i).getText(),Utils.castType(ctx.type(i))));
            }

        }
        return new FormalParams(formalParamsList);
    }

    @Override
    public Node visitValExp(ValExpContext ctx) {
        return new ValNode(ctx.getText());
    }
}
