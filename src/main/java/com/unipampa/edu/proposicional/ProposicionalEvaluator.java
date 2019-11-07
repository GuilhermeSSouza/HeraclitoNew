/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.proposicional;

import com.unipampa.edu.heraclito.StringHelper;
import java.util.HashMap;

/**
 *
 * @author Rafael Bueno
 */
public class ProposicionalEvaluator {

    public static Boolean areEqual(Node original, Node created) {
        Boolean areEqual = compareFormula(original.getText(), created.getText());

        if (areEqual && original.getChildren().size() == created.getChildren().size()) {

            for (int i = 0; i < original.getChildren().size(); i++) {
                areEqual = areEqual(original.getChildren().get(i), created.getChildren().get(i));

                if (!areEqual) {
                    return Boolean.FALSE;
                }
            }

            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public static void areEqualPartial(Node original, Node created) {
        if (original == null) {
            created.setPartialEvaluateStatus(PartialEvaluateStatus.Leftover);
        } else if (original.getText() != null && created.getText() != null) {
            Boolean areEqual = compareFormula(original.getText(), created.getText());

            if (areEqual) {
                created.setPartialEvaluateStatus(PartialEvaluateStatus.Correct);
                // JCGLUZ
                created.setOperator(original.getOperator());
            } else {
                created.setPartialEvaluateStatus(PartialEvaluateStatus.Incorrect);
            }
        }

        for (int i = 0; i < created.getChildren().size(); i++) {
            if (original != null && i < original.getChildren().size()) {
                areEqualPartial(original.getChildren().get(i), created.getChildren().get(i));
            } else {
                areEqualPartial(null, created.getChildren().get(i));
            }
        }
    }

    public static Boolean isCalculationCorrect(Node node) throws SyntaxException {
        Node parsed = ParserHelper.parseFormula(node.getText());

        HashMap<String, Boolean> values = NodeHelper.extractVariablesValues(node);
        NodeHelper.setVariablesValues(parsed, values);

        parsed.calculateValue();

        return compareValue(parsed, node);
    }

    private static Boolean compareValue(Node parsed, Node node) {
        if (node.getValue() != parsed.getValue()) {
            return Boolean.FALSE;
        }

        for (int i = 0; i < parsed.getChildren().size(); i++) {
            Boolean areEqual = compareValue(parsed.getChildren().get(i), node.getChildren().get(i));

            if (!areEqual) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    public static void calculateParcial(Node node) throws SyntaxException {
        Node parsed = ParserHelper.parseFormula(node.getText());

        HashMap<String, Boolean> values = NodeHelper.extractVariablesValues(node);

        if (values.isEmpty()) {
            return;
        }

        NodeHelper.setVariablesValues(parsed, values);
        parsed.calculateValue();
        compareValueParcial(parsed, node);
    }

    private static void compareValueParcial(Node parsed, Node node) {
        if (node.getValue() != null) {
            if (node.getValue() == parsed.getValue()) {
                node.setPartialEvaluateStatus(PartialEvaluateStatus.Correct);
            } else {
                node.setPartialEvaluateStatus(PartialEvaluateStatus.Incorrect);
            }
        }

        for (int i = 0; i < parsed.getChildren().size(); i++) {
            compareValueParcial(parsed.getChildren().get(i), node.getChildren().get(i));
        }
    }

    private static Boolean compareFormula(String formula1, String formula2) {
        String temp1 = StringHelper.removeWhitesSpaces(formula1);
        String temp2 = StringHelper.removeWhitesSpaces(formula2);
        
        if(StringHelper.containsExternalParentesis(temp1)) {
            temp1 = StringHelper.removeExternalParentesis(temp1);
        }
        
        if(StringHelper.containsExternalParentesis(temp2)) {
            temp2 = StringHelper.removeExternalParentesis(temp2);
        }

        return temp1.equals(temp2);
    }
}
