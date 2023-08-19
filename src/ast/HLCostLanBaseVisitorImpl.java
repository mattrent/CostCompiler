package ast;

import gen.HLCostLanBaseVisitor;
import gen.HLCostLanParser.*;
import org.antlr.v4.runtime.misc.Pair;
import typeNode.TypeNode;
import utilities.Utils;
import java.util.ArrayList;

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
