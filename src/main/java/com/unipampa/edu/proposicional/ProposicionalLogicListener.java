// Generated from ProposicionalLogic.g4 by ANTLR 4.5.3

package com.unipampa.edu.proposicional;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ProposicionalLogicParser}.
 */
public interface ProposicionalLogicListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ProposicionalLogicParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(ProposicionalLogicParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProposicionalLogicParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(ProposicionalLogicParser.FormulaContext ctx);
}