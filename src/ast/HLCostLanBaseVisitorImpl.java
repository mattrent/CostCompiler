package ast;

import exp.BinExpNode;
import gen.*;
import org.antlr.v4.runtime.misc.Pair;
import typeNode.TypeNode;
import utilities.Utils;

import java.util.ArrayList;
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
        ArrayList<Node> stm = new ArrayList<>();
        ReturnTypeNode returnType;
        formalParamsNode = visitFormalParams(ctx.formalParams());

        if(ctx.type()!= null){
            returnType = new ReturnTypeNode(Utils.castType(ctx.type()));
        }else{
            returnType = new ReturnTypeNode(new IdNode(ctx.ID(1).getText()));
        }
        //StmNode stm = visitStm(ctx.stm());

        return super.visitFund(ctx);
    }

    @Override
    public Node visitDeclarationService(DeclarationServiceContext ctx) {
        return super.visitDeclarationService(ctx);
    }

    @Override
    public Node visitStm(StmContext ctx) {
        if(ctx.ID() != null && ctx.exp() != null){
            //'call'ID'('exp(','exp)*')' stm?';'
            String idCall = ctx.ID().getText();
            ArrayList<Node> exp = new ArrayList<>();
            for (ExpContext expContext : ctx.exp()) {
                exp.add(visit(expContext));
            }

            return new CallServiceNode(idCall,exp,ctx.stm(0));
        }

        if(ctx.cond() != null & ctx.stm().size() == 2){
            // 'if' exp 'then' stm 'else' stm
            if(ctx.cond().ID() != null){
                ArrayList<Node> actualParams = new ArrayList<>();
                for (ExpContext expContext : ctx.cond().exp()) {
                    actualParams.add(visit(expContext));
                }
                CallServiceNode callServiceNode = new CallServiceNode(ctx.cond().ID().getText(),actualParams,null);
                return new IfNode(callServiceNode,visitStm(ctx.stm(0)),visitStm(ctx.stm(1)));
            }else{
                Node exp = visitCond(ctx.cond());

                Node stm1 = visit(ctx.stm(0));
                Node stm2 = visit(ctx.stm(1));

                return new IfNode(exp,stm1,stm2);
            }
        }
        //'for' '('ID 'in' '(' '0'','exp ')' ')' '{' stm '}'   /
        if(ctx.ID() != null && ctx.exp() != null && ctx.stm() != null){
            String id = ctx.ID().getText();
            Node exp = visit(ctx.exp(0));
            Node stm = visitStm(ctx.stm().get(0));

            //return new ForNode(id,exp,stm);
        }

        //  'for''('listCount';'exp';'listExp')''{' stm '}'
        if(ctx.listCount() != null && ctx.exp() != null && ctx.listExp() != null && ctx.stm() != null){
            Node listCount = visitListCount(ctx.listCount());
            Node exp = visit(ctx.exp(0));
            Node listExp = visitListExp(ctx.listExp());
            Node stm = visitStm(ctx.stm().get(0));

            //return new ForNode(id,exp,stm);
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
              return visit(ctx.exp(0));
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
        return super.visitCallFun(ctx);
    }

    @Override
    public Node visitBinExp(BinExpContext ctx) {
        return new BinExpNode(visit(ctx.exp(0)),visit(ctx.exp(1)),ctx.op.getText());
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
}
