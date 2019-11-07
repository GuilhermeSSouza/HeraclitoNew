/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.proposicional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class TruthTableColumn {

    private String name;
    private List<Boolean> values;
    
    private PartialEvaluateStatus columnNameEvaluateStatus;
    private List<PartialEvaluateStatus> valuesEvaluateStatus;

    public TruthTableColumn() {
        values = new ArrayList<Boolean>();
        valuesEvaluateStatus = new ArrayList<PartialEvaluateStatus>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the values
     */
    public List<Boolean> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(List<Boolean> values) {
        this.values = values;
    }

    /**
     * @return the columnNameEvaluateStatus
     */
    public PartialEvaluateStatus getColumnNameEvaluateStatus() {
        return columnNameEvaluateStatus;
    }

    /**
     * @param columnNameEvaluateStatus the columnNameEvaluateStatus to set
     */
    public void setColumnNameEvaluateStatus(PartialEvaluateStatus columnNameEvaluateStatus) {
        this.columnNameEvaluateStatus = columnNameEvaluateStatus;
    }

    /**
     * @return the valuesEvaluateStatus
     */
    public List<PartialEvaluateStatus> getValuesEvaluateStatus() {
        return valuesEvaluateStatus;
    }

    /**
     * @param valuesEvaluateStatus the valuesEvaluateStatus to set
     */
    public void setValuesEvaluateStatus(List<PartialEvaluateStatus> valuesEvaluateStatus) {
        this.valuesEvaluateStatus = valuesEvaluateStatus;
    }
}
