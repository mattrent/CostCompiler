package ast;

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

            //return new CallServiceNode(idCall,exp,ctx.stm(0));
        }

        if(ctx.cond() != null & ctx.stm().size() == 2){
            // 'if' exp 'then' stm 'else' stm
            Node exp = visit(ctx.cond());
            Node stm1 = visit(ctx.stm(0));
            Node stm2 = visit(ctx.stm(1));

            //return new IfNode(exp,stm1,stm2);
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
            Node letIn = visitLetIn(ctx.letIn());
            //return new LetInNode(letIn);
        }

        if(ctx.ID() != null && ctx.listCount() != null){
            String id = ctx.ID().getText();
            Node listCount = visitListCount(ctx.listCount());
            //return new CallNode(id,listCount);
        }
        return super.visitStm(ctx);
    }

    @Override
    public Node visitLetIn(LetInContext ctx) {
        return super.visitLetIn(ctx);
    }

    @Override
    public Node visitCond(CondContext ctx) {
        return super.visitCond(ctx);
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
        return super.visitBinExp(ctx);
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
