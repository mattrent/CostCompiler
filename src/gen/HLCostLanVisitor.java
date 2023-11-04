// Generated from C:/Users/simon/IdeaProjects/CostCompiler/src/HLCostLan.g4 by ANTLR 4.13.1
package gen;
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
	 * Visit a parse tree produced by {@link HLCostLanParser#prg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrg(HLCostLanParser.PrgContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInit(HLCostLanParser.InitContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#serviceDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitServiceDecl(HLCostLanParser.ServiceDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#functionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDecl(HLCostLanParser.FunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStm(HLCostLanParser.StmContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#serviceCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitServiceCall(HLCostLanParser.ServiceCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(HLCostLanParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#letIn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetIn(HLCostLanParser.LetInContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#expOrCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpOrCall(HLCostLanParser.ExpOrCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#expPlus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpPlus(HLCostLanParser.ExpPlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callFun}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallFun(HLCostLanParser.CallFunContext ctx);
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
	/**
	 * Visit a parse tree produced by the {@code stringExp}
	 * labeled alternative in {@link HLCostLanParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExp(HLCostLanParser.StringExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(HLCostLanParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#basictype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasictype(HLCostLanParser.BasictypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#complexType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexType(HLCostLanParser.ComplexTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#structType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructType(HLCostLanParser.StructTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#typeArr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArr(HLCostLanParser.TypeArrContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(HLCostLanParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#formalParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParams(HLCostLanParser.FormalParamsContext ctx);
}