// Generated from C:/Users/simon/Desktop/CostCompiler/src\HLCostLan.g4 by ANTLR 4.12.0
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HLCostLanParser}.
 */
public interface HLCostLanListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(HLCostLanParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(HLCostLanParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#declarationService}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationService(HLCostLanParser.DeclarationServiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#declarationService}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationService(HLCostLanParser.DeclarationServiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#fund}.
	 * @param ctx the parse tree
	 */
	void enterFund(HLCostLanParser.FundContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#fund}.
	 * @param ctx the parse tree
	 */
	void exitFund(HLCostLanParser.FundContext ctx);
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
	 * Enter a parse tree produced by {@link HLCostLanParser#callService}.
	 * @param ctx the parse tree
	 */
	void enterCallService(HLCostLanParser.CallServiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#callService}.
	 * @param ctx the parse tree
	 */
	void exitCallService(HLCostLanParser.CallServiceContext ctx);
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
	 * Enter a parse tree produced by {@link HLCostLanParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(HLCostLanParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(HLCostLanParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#listCount}.
	 * @param ctx the parse tree
	 */
	void enterListCount(HLCostLanParser.ListCountContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#listCount}.
	 * @param ctx the parse tree
	 */
	void exitListCount(HLCostLanParser.ListCountContext ctx);
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#listExp}.
	 * @param ctx the parse tree
	 */
	void enterListExp(HLCostLanParser.ListExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#listExp}.
	 * @param ctx the parse tree
	 */
	void exitListExp(HLCostLanParser.ListExpContext ctx);
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
	 * Enter a parse tree produced by {@link HLCostLanParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(HLCostLanParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(HLCostLanParser.AssignmentContext ctx);
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
	 * Enter a parse tree produced by {@link HLCostLanParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(HLCostLanParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(HLCostLanParser.ArrayTypeContext ctx);
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
	 * Enter a parse tree produced by {@link HLCostLanParser#structAssignment}.
	 * @param ctx the parse tree
	 */
	void enterStructAssignment(HLCostLanParser.StructAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#structAssignment}.
	 * @param ctx the parse tree
	 */
	void exitStructAssignment(HLCostLanParser.StructAssignmentContext ctx);
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