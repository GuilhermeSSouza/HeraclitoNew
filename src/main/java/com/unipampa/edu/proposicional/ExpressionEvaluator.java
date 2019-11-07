package com.unipampa.edu.proposicional;

/**
 *
 * @author Rafael Bueno
 */
public class ExpressionEvaluator {

    public static boolean evaluateBinary(boolean left, boolean right, Operator operator) {
        switch (operator) {
            case Conjuction:
                return left && right;
            case Disjuction:
                return left || right;
            case Implication:
                return !left || right;
            case Biconditional:
                return left == right;
            default:
                return false;
        }
    }
}
