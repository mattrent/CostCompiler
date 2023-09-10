// Generated from C:/Users/simon/Desktop/CostCompiler/src\HLCostLan.g4 by ANTLR 4.12.0
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
	 * Visit a parse tree produced by {@link HLCostLanParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(HLCostLanParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#declarationService}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationService(HLCostLanParser.DeclarationServiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#typeDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDecl(HLCostLanParser.TypeDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#fund}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFund(HLCostLanParser.FundContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStm(HLCostLanParser.StmContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#callService}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallService(HLCostLanParser.CallServiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#letIn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetIn(HLCostLanParser.LetInContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(HLCostLanParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#listExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListExp(HLCostLanParser.ListExpContext ctx);
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
	 * Visit a parse tree produced by {@link HLCostLanParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(HLCostLanParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(HLCostLanParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#complexType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexType(HLCostLanParser.ComplexTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(HLCostLanParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#typeArr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArr(HLCostLanParser.TypeArrContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#structAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructAssignment(HLCostLanParser.StructAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HLCostLanParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(HLCostLanParser.AssignContext ctx);
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