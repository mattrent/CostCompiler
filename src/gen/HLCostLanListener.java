// Generated from C:/Users/simon/IdeaProjects/CostCompiler/src/HLCostLan.g4 by ANTLR 4.13.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HLCostLanParser}.
 */
public interface HLCostLanListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#prg}.
	 * @param ctx the parse tree
	 */
	void enterPrg(HLCostLanParser.PrgContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#prg}.
	 * @param ctx the parse tree
	 */
	void exitPrg(HLCostLanParser.PrgContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(HLCostLanParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(HLCostLanParser.InitContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#serviceDecl}.
	 * @param ctx the parse tree
	 */
	void enterServiceDecl(HLCostLanParser.ServiceDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#serviceDecl}.
	 * @param ctx the parse tree
	 */
	void exitServiceDecl(HLCostLanParser.ServiceDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(HLCostLanParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(HLCostLanParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterStm(HLCostLanParser.StmContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitStm(HLCostLanParser.StmContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#serviceCall}.
	 * @param ctx the parse tree
	 */
	void enterServiceCall(HLCostLanParser.ServiceCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#serviceCall}.
	 * @param ctx the parse tree
	 */
	void exitServiceCall(HLCostLanParser.ServiceCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(HLCostLanParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(HLCostLanParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#letIn}.
	 * @param ctx the parse tree
	 */
	void enterLetIn(HLCostLanParser.LetInContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#letIn}.
	 * @param ctx the parse tree
	 */
	void exitLetIn(HLCostLanParser.LetInContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#expOrCall}.
	 * @param ctx the parse tree
	 */
	void enterExpOrCall(HLCostLanParser.ExpOrCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#expOrCall}.
	 * @param ctx the parse tree
	 */
	void exitExpOrCall(HLCostLanParser.ExpOrCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#expPlus}.
	 * @param ctx the parse tree
	 */
	void enterExpPlus(HLCostLanParser.ExpPlusContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#expPlus}.
	 * @param ctx the parse tree
	 */
	void exitExpPlus(HLCostLanParser.ExpPlusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callFun}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterCallFun(HLCostLanParser.CallFunContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callFun}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitCallFun(HLCostLanParser.CallFunContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binExp}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBinExp(HLCostLanParser.BinExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binExp}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBinExp(HLCostLanParser.BinExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code derExp}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterDerExp(HLCostLanParser.DerExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code derExp}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitDerExp(HLCostLanParser.DerExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valExp}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterValExp(HLCostLanParser.ValExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valExp}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitValExp(HLCostLanParser.ValExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringExp}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterStringExp(HLCostLanParser.StringExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringExp}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitStringExp(HLCostLanParser.StringExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(HLCostLanParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(HLCostLanParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#basictype}.
	 * @param ctx the parse tree
	 */
	void enterBasictype(HLCostLanParser.BasictypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#basictype}.
	 * @param ctx the parse tree
	 */
	void exitBasictype(HLCostLanParser.BasictypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#complexType}.
	 * @param ctx the parse tree
	 */
	void enterComplexType(HLCostLanParser.ComplexTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#complexType}.
	 * @param ctx the parse tree
	 */
	void exitComplexType(HLCostLanParser.ComplexTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#structType}.
	 * @param ctx the parse tree
	 */
	void enterStructType(HLCostLanParser.StructTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#structType}.
	 * @param ctx the parse tree
	 */
	void exitStructType(HLCostLanParser.StructTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#typeArr}.
	 * @param ctx the parse tree
	 */
	void enterTypeArr(HLCostLanParser.TypeArrContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#typeArr}.
	 * @param ctx the parse tree
	 */
	void exitTypeArr(HLCostLanParser.TypeArrContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(HLCostLanParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(HLCostLanParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#formalParams}.
	 * @param ctx the parse tree
	 */
	void enterFormalParams(HLCostLanParser.FormalParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#formalParams}.
	 * @param ctx the parse tree
	 */
	void exitFormalParams(HLCostLanParser.FormalParamsContext ctx);
}