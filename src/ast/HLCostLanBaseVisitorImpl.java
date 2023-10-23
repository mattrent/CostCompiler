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

        if(ctx.type().ID() != null){
            returnType = new ReturnTypeNode(new IdNode(ctx.ID().getText()));
        }else{
            returnType = new ReturnTypeNode(Utils.castType(ctx.type().basictype()));
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
        if(ctx.expOrCall() != null && ctx.stm().size() == 2){
            // 'if' ast.exp 'then' stm 'else' stm
            int line = ctx.start.getLine();

            return new IfNode(visitExpOrCall(ctx.expOrCall()),visitStm(ctx.stm(0)),visitStm(ctx.stm(1)),line);
        }
        //'for' '('ID 'in' '(' '0'','ast.exp ')' ')' '{' stm '}'   /
        if(ctx.ID() != null && ctx.exp() != null && ctx.stm() != null){
            String id = ctx.ID().getText();
            Node exp = (Node) visit(ctx.exp());
            Node stm = visitStm(ctx.stm().get(0));
            int line = ctx.start.getLine();
            return new ForNode(id,exp,stm,line);
        }

        if(ctx.serviceCall() != null){
            return visitServiceCall(ctx.serviceCall());
        }

        //let in
        if(ctx.letIn() != null){
            return visitLetIn(ctx.letIn());
        }

        if(ctx.functionCall() != null){
            return visitFunctionCall(ctx.functionCall());
        }

        if (ctx.expPlus()!= null){
            return visitExpPlus(ctx.expPlus());
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
        for(int i= 0;i< ctx.expPlus().size();i++){
            ExpPlusContext exp = ctx.expPlus(i);
            if(exp.ID(i) != null)
                structAssignment.add(new AssignmentNodeIn(new IdNode(ctx.expPlus(i).ID(0).getText()),new IdNode(ctx.ID(i).getText()),visitExpPlus(ctx.expPlus(i))));
            else
                listAssignment.add(new LetAssignmentNode(null,new IdNode(ctx.ID(i).getText()),visitExpPlus(ctx.expPlus(i))));
        }

        Node stm = visitStm(ctx.stm());

        return new LetInNode(listAssignment,structAssignment,stm);
    }

    @Override
    public Node visitFunctionCall(FunctionCallContext ctx) {
        String id = ctx.ID().getText();
        ArrayList<Node> listExp = new ArrayList<>();
        for (ExpContext expContext : ctx.exp()) {
            listExp.add(visit(expContext));
        }
        return new CallNode(new IdNode(id),listExp);
    }

    @Override
    public Node visitExpOrCall(ExpOrCallContext ctx) {
       if(ctx.serviceCall()!= null){
           //Call Service
            ArrayList<Node> actualParams = new ArrayList<>();
            for (ExpContext expContext : ctx.serviceCall().exp()) {
                actualParams.add(visit(expContext));
            }
            return new CallServiceNode(ctx.serviceCall().ID().getText(),actualParams,null);
       }else{
              //BinExp
               return(visit(ctx.exp()));

       }
    }


    @Override
    public Node visitExpPlus(ExpPlusContext ctx) {
        if(ctx.serviceCall() != null){
            return visitServiceCall(ctx.serviceCall());
        }
        else if (ctx.functionCall() != null){
            return visitFunctionCall(ctx.functionCall());
        }
        else if(ctx.ID() != null && !ctx.ID().isEmpty()){
            ArrayList<LetAssignmentNode> list = new ArrayList<>();
            int len = ctx.ID().size();
            int lenAss = ctx.expPlus().size();
            for(int i = 0; i < ctx.expPlus().size(); i++) {
                list.add(new LetAssignmentNode(null, new IdNode(ctx.ID(i+1).getText()), visitExpPlus(ctx.expPlus(i))));
            }
            return new StructAssignmentNode(new IdNode(ctx.ID(0).getText()), list);
        }else return visit(ctx.exp());
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

        if(ctx.NUMBER() == null){
            //Struct Node
            IdNode id = new IdNode(ctx.ID(0).getText());
            ArrayList<Pair<IdNode,TypeNode>> params = new ArrayList<>();
            for(int i = 1;i<ctx.ID().size();i++){
                if(ctx.structType(i-1).typeArr() != null){
                    //ArrayNode
                    ArrayType arr = new ArrayType(Utils.castType(ctx.structType(i-1).typeArr().type().basictype()));
                    params.add(new Pair<IdNode, TypeNode>(new IdNode(ctx.ID(i).getText()),arr));
                }else
                    params.add(new Pair<IdNode, TypeNode>(new IdNode(ctx.ID(i).getText()),Utils.castType(ctx.structType(i-1).type().basictype())));
            }
            return new StructNode(id,params);
        }else{
            //Array Node

            if(ctx.type().ID()!= null){
                return new ArrayType(new IdType(ctx.type().ID().getText()),new IdNode(ctx.ID(0).getText()),Integer.parseInt(ctx.NUMBER().getText()));
            }else
                return new ArrayType(Utils.castType(ctx.type().basictype()),new IdNode(ctx.ID(0).getText()),Integer.parseInt(ctx.NUMBER().getText()));
        }
    }
    public Node visitServiceCall(ServiceCallContext ctx) {
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
