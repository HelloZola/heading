// Generated from E:/IDEA Studio/drawing board/heading/train-antlr/src/main/java/com/vi/demo\Calculator.g4 by ANTLR 4.12.0
package com.vi.demo.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculatorParser}.
 */
public interface CalculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CalculatorParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CalculatorParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(CalculatorParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(CalculatorParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(CalculatorParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(CalculatorParser.FactorContext ctx);
}