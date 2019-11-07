/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.proposicional;

import com.unipampa.edu.heraclito.StringHelper;
import static com.wagnerandade.coollection.Coollection.eq;
import static com.wagnerandade.coollection.Coollection.from;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.antlr.v4.misc.OrderedHashMap;

/**
 *
 * @author rafaelbueno
 */
public class NodeHelper {

    public static HashMap<String, Boolean> extractVariablesValues(Node node) {
        HashMap<String, Boolean> values = new OrderedHashMap<String, Boolean>();
        extractVariablesValues(node, values);
        return values;
    }

    private static void extractVariablesValues(Node node, HashMap<String, Boolean> values) {
        if (node.getNodeType() == NodeType.Terminal) {
            if (!values.containsKey(node.getText()) && node.getValue() != null) {
                values.put(node.getText(), node.getValue());
                return;
            }
        }

        for (Node child : node.getChildren()) {
            extractVariablesValues(child, values);
        }
    }

    public static void setVariablesValues(Node node, HashMap<String, Boolean> values) {
        if (node.getNodeType() == NodeType.Terminal && values.containsKey(node.getText())) {
            node.setValue(values.get(node.getText()));
            return;
        }

        for (Node child : node.getChildren()) {
            setVariablesValues(child, values);
        }
    }

    public static String[] extractVariables(Node node) {
        List<String> variables = new ArrayList<String>();

        extractVariables(node, variables);
        Collections.sort(variables, String.CASE_INSENSITIVE_ORDER);

        return variables.toArray(new String[variables.size()]);
    }

    private static void extractVariables(Node node, List<String> variables) {
        if (node.getNodeType() == NodeType.Terminal) {
            if (!variables.contains(node.getText())) {
                variables.add(node.getText());
            }

            return;
        }

        for (Node child : node.getChildren()) {
            extractVariables(child, variables);
        }
    }
    
    public static Boolean isVariableOrNegatedVariable(Node node) {
        String nodeText = StringHelper.removeWhitesSpaces(node.getText());
        return nodeText.length() == 1 || nodeText.length() == 2;
    }

    public static Boolean nodeExists(List<Node> nodes, Node node) {
        return from(nodes)
                .where("getText", eq(node.getText()))
                .first() != null;
    }
}
