/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.proposicional;

import com.esotericsoftware.kryo.Kryo;
import com.unipampa.edu.heraclito.StringHelper;
import static com.wagnerandade.coollection.Coollection.from;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import org.antlr.v4.misc.OrderedHashMap;

/**
 *
 * @author rafaelbueno
 */
public class TruthTableEvaluator {

    private static final String ALIAS_END_FORMULA = "final";
    
    public static List<TruthTableColumn> generateLogicValue(String[] variables) {

        List<TruthTableColumn> variablesWithValues = new ArrayList<TruthTableColumn>(variables.length);
        int totalLines = (int) Math.pow(2, variables.length);
        int fator = 1;

        for (String variable : variables) {
            TruthTableColumn column = new TruthTableColumn();
            column.setName(variable);

            fator *= 2;
            int totalLogicValue = totalLines / fator;

            while (column.getValues().size() < totalLines) {
                for (int j = 1; j >= 0; j--) {
                    Boolean logicValue = (j == 1);

                    for (int z = 0; z < totalLogicValue; z++) {
                        column.getValues().add(logicValue);
                    }
                }
            }

            variablesWithValues.add(column);
        }

        return variablesWithValues;
    }

    public static Boolean evaluateTruthTable(String formula, List<TruthTableColumn> tableToEvaluate) throws SyntaxException {
        List<TruthTableColumn> generatedTable = generateTruthTableForFormula(formula);
        return compareTruthTable(generatedTable, tableToEvaluate, formula);
    }

    public static List<TruthTableColumn> evaluateTruthTablePartial(String formula, List<TruthTableColumn> tableToEvaluate) throws SyntaxException {
        List<TruthTableColumn> generatedTable = generateTruthTableForFormula(formula);
        return compareTruthTablePartial(generatedTable, tableToEvaluate, formula);
    }

    public static List<TruthTableColumn> generateTruthTableForFormula(String formula) throws SyntaxException {
        Kryo kryo = new Kryo();
        List<Node> lines = new ArrayList<Node>();

        Node node = ParserHelper.parseFormula(formula);
        String[] variables = NodeHelper.extractVariables(node);
        List<TruthTableColumn> variablesWithValues = generateLogicValue(variables);

        int totalLines = variablesWithValues.get(0).getValues().size();

        for (int x = 0; x < totalLines; x++) {
            HashMap<String, Boolean> values = new OrderedHashMap<String, Boolean>();

            for (int y = 0; y < variables.length; y++) {
                values.put(
                        variablesWithValues.get(y).getName(),
                        variablesWithValues.get(y).getValues().get(x)
                );
            }

            Node newNode = kryo.copy(node);
            NodeHelper.setVariablesValues(newNode, values);
            newNode.calculateValue();
            lines.add(newNode);
        }

        return extractTruthTable(lines);
    }

    private static List<TruthTableColumn> extractTruthTable(List<Node> lines) {

        ArrayList<ArrayList<Node>> tableRows = new ArrayList<ArrayList<Node>>();

        for (Node line : lines) {
            tableRows.add((ArrayList<Node>) convertToTableRow(line));
        }

        return convertLinesToColumns(tableRows);
    }

    private static List<Node> convertToTableRow(Node node) {
        List<Node> variables = new ArrayList<Node>();
        TreeMap<Integer, ArrayList<Node>> columns = new TreeMap<Integer, ArrayList<Node>>(
                new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        convertToTableRow(node, variables, columns, 0);
        return buildTableRow(variables, columns);
    }

    private static void convertToTableRow(Node node, List<Node> variables, TreeMap<Integer, ArrayList<Node>> columns, int level) {
        for (Node children : node.getChildren()) {
            convertToTableRow(children, variables, columns, level + 1);
        }

        if (NodeHelper.isVariableOrNegatedVariable(node)) {
            if (!NodeHelper.nodeExists(variables, node)) {
                variables.add(node);
            }
        } else {
            columns.putIfAbsent(level, new ArrayList<Node>());

            if (!NodeHelper.nodeExists(columns.get(level), node)) {
                columns.get(level).add(node);
            }
        }
    }

    private static List<Node> buildTableRow(List<Node> variables, TreeMap<Integer, ArrayList<Node>> columns) {
        List<Node> result = new ArrayList<Node>();

        variables = from(variables).orderBy("getText").all();

        for (int i = 0; i < variables.size(); i++) {
            result.add(variables.get(i));
        }

        for (Integer key : columns.keySet()) {
            for (Node node : columns.get(key)) {
                result.add(node);
            }
        }

        return result;
    }

    private static List<TruthTableColumn> convertLinesToColumns(ArrayList<ArrayList<Node>> tableRows) {
        int totalColumns = tableRows.get(0).size();
        List<TruthTableColumn> result = new ArrayList<TruthTableColumn>();

        for (int y = 0; y < totalColumns; y++) {
            String columnName = tableRows.get(0).get(y).getText();

            TruthTableColumn column = new TruthTableColumn();
            column.setName(columnName);

            for (int x = 0; x < tableRows.size(); x++) {
                Boolean value = tableRows.get(x).get(y).getValue();
                column.getValues().add(value);
            }

            result.add(column);
        }

        return result;
    }

    private static Boolean compareTruthTable(List<TruthTableColumn> table1, List<TruthTableColumn> table2, String formula) {
        int totalColumns1 = table1.size();
        int totalColumns2 = table2.size();
        int totalLines1 = table1.get(0).getValues().size();
        int totalLines2 = table2.get(0).getValues().size();

        if (totalColumns1 != totalColumns2 || totalLines1 != totalLines2) {
            return Boolean.FALSE;
        }

        for (int y = 0; y < totalColumns1; y++) {

            if (!compareColumnName(table1.get(y), table2.get(y), formula)) {
                return Boolean.FALSE;
            }

            for (int x = 0; x < totalLines1; x++) {
                if (!Objects.equals(table1.get(y).getValues().get(x), table2.get(y).getValues().get(x))) {
                    return Boolean.FALSE;
                }
            }
        }

        return Boolean.TRUE;
    }

    private static Boolean compareColumnName(TruthTableColumn column1, TruthTableColumn column2, String formula) {
        String temp1 = StringHelper.removeWhitesSpaces(TranslateAlias(column1.getName(), formula));
        String temp2 = StringHelper.removeWhitesSpaces(TranslateAlias(column2.getName(), formula));
        
        if(StringHelper.containsExternalParentesis(temp1)) {
            temp1 = StringHelper.removeExternalParentesis(temp1);
        }
        
        if(StringHelper.containsExternalParentesis(temp2)) {
            temp2 = StringHelper.removeExternalParentesis(temp2);
        }

        return temp1.equals(temp2);
    }

    private static List<TruthTableColumn> compareTruthTablePartial(List<TruthTableColumn> generatedTable, List<TruthTableColumn> tableToEvaluate, String formula) {

        for (int y = 0; y < tableToEvaluate.size(); y++) {
            tableToEvaluate.get(y).setValuesEvaluateStatus(new ArrayList<PartialEvaluateStatus>());

            if (y >= generatedTable.size()) {
                tableToEvaluate.get(y).setColumnNameEvaluateStatus(PartialEvaluateStatus.Leftover);

                for (int x = 0; x < tableToEvaluate.get(y).getValues().size(); x++) {
                    tableToEvaluate.get(y).getValuesEvaluateStatus().add(PartialEvaluateStatus.Leftover);
                }

                continue;
            }

            if (StringHelper.isNullOrEmptyWhitespace(tableToEvaluate.get(y).getName())
                    || compareColumnName(tableToEvaluate.get(y), generatedTable.get(y), formula)) {

                tableToEvaluate.get(y).setColumnNameEvaluateStatus(PartialEvaluateStatus.Correct);
            } else {
                tableToEvaluate.get(y).setColumnNameEvaluateStatus(PartialEvaluateStatus.Incorrect);
            }

            for (int x = 0; x < tableToEvaluate.get(y).getValues().size(); x++) {
                if (tableToEvaluate.get(y).getValues().get(x) == null
                        || tableToEvaluate.get(y).getValues().get(x) == generatedTable.get(y).getValues().get(x)) {

                    tableToEvaluate.get(y).getValuesEvaluateStatus().add(PartialEvaluateStatus.Correct);
                } else {
                    tableToEvaluate.get(y).getValuesEvaluateStatus().add(PartialEvaluateStatus.Incorrect);
                }
            }
        }

        return tableToEvaluate;
    }
    
    private static String TranslateAlias(String columnName, String formula) {
        if(StringHelper.removeWhitesSpaces(columnName).equalsIgnoreCase(ALIAS_END_FORMULA)) {
            return formula;
        }
        
        return columnName;
    }
}
