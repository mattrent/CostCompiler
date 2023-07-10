package gen;// Generated from C:/Users/simon/Desktop/CostCompiler/src\HLCostLan.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HLCostLanParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HLCostLanVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(HLCostLanParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(HLCostLanParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStm(HLCostLanParser.StmContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(HLCostLanParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binExp}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinExp(HLCostLanParser.BinExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code derExp}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDerExp(HLCostLanParser.DerExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valExp}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValExp(HLCostLanParser.ValExpContext ctx);
}