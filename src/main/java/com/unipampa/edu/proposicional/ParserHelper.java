package com.unipampa.edu.proposicional;

import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

/**
 *
 * @author Rafael
 */
public class ParserHelper {

    /**
     *
     * @param formula
     * @return
     * @throws heraclito.proposicional.SyntaxException
     */
    public static Node parseFormula(String formula) throws SyntaxException {
        ANTLRInputStream input = new ANTLRInputStream(formula);

        ProposicionalLogicLexer lexer = new ProposicionalLogicLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ProposicionalLogicParser parser = new ProposicionalLogicParser(tokens);
        ProposicionalLogicParser.FormulaContext context = parser.formula();

        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new SyntaxException();
        }

        return translate(context);
    }

    private static Node translate(ProposicionalLogicParser.FormulaContext context) {

        Node node = new Node();
        node.setText(context.getText());
        node.setNodeType(evaluateNodeType(context));

        context = bypassEqualChild(context);

        if (node.getNodeType() == NodeType.NonTerminal) {
            node.setOperator(evaluateOperator(context));
        }

        for (ProposicionalLogicParser.FormulaContext child : context.formula()) {
            translateInternal(child, node);
        }

        return node;
    }

    private static void translateInternal(ProposicionalLogicParser.FormulaContext context, Node parent) {

        Node node = new Node();
        node.setText(context.getText());
        node.setNodeType(evaluateNodeType(context));

        context = bypassEqualChild(context);

        if (node.getNodeType() == NodeType.NonTerminal) {
            node.setOperator(evaluateOperator(context));
        }

        parent.getChildren().add(node);

        for (ProposicionalLogicParser.FormulaContext child : context.formula()) {
            translateInternal(child, node);
        }
    }

    private static ProposicionalLogicParser.FormulaContext bypassEqualChild(ProposicionalLogicParser.FormulaContext context) {

        String formula = context.getText();
        List<ProposicionalLogicParser.FormulaContext> childs = context.formula();

        if (childs.size() == 1) {
            ProposicionalLogicParser.FormulaContext child = childs.get(0);

            if (formula.equals("(" + child.getText() + ")")) {
                return child;
            }
        }

        return context;
    }

    private static NodeType evaluateNodeType(ProposicionalLogicParser.FormulaContext context) {
        return context.formula().isEmpty() ? NodeType.Terminal : NodeType.NonTerminal;
    }

    private static Operator evaluateOperator(ProposicionalLogicParser.FormulaContext context) {

        for (ParseTree child : context.children) {
            if (child instanceof TerminalNodeImpl) {

                if (child.getText() == "~") {
                    return Operator.Negation;

                } else if (child.getText() == "^") {
                    return Operator.Conjuction;

                } else if (child.getText() == "v") {
                    return Operator.Disjuction;

                } else if (child.getText() == "->") {
                    return Operator.Implication;

                } else if (child.getText() == "<->") {
                    return Operator.Biconditional;

                }
                return null;
            }

            return null;
        }
        
        return null;
    }
}
