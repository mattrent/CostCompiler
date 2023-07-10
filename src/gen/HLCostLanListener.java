package gen;// Generated from C:/Users/simon/Desktop/CostCompiler/src\HLCostLan.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HLCostLanParser}.
 */
public interface HLCostLanListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HLCostLanParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(HLCostLanParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link HLCostLanParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(HLCostLanParser.FuncContext ctx);
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
}