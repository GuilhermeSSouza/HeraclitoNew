package com.unipampa.edu.proposicional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael Bueno
 */
public class Node {

    private NodeType nodeType;
    private Boolean value;
    private String text;
    private List<Node> children;
    private Operator operator;
    private PartialEvaluateStatus partialEvaluateStatus;
    private int idNode;
    private Boolean editable;

    public Node() {
        children = new ArrayList<Node>();
    }

    /**
     * @return the nodeType
     */
    public NodeType getNodeType() {
        return nodeType;
    }

    /**
     * @param nodeType the nodeType to set
     */
    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * @return the value
     */
    public Boolean getValue() {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Boolean value) {
        this.value = value;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    public List<Node> getChildren() {
        return children;//.toArray(new Node[0]);
    }

    /**
     * @return the operator
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * @param operator the connective to set
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return this.getText();
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void calculateValue() {
        if (nodeType == NodeType.Terminal) {
            return;
        }

        if (children.size() == 1) {
            children.get(0).calculateValue();
            Boolean childValue = children.get(0).getValue();

            if (childValue != null) {
                this.value = (operator == Operator.Negation ? !childValue : childValue);
                return;
            }
        } else if (children.size() == 2) {
            children.get(0).calculateValue();
            children.get(1).calculateValue();

            Boolean leftValue = children.get(0).getValue();
            Boolean rightValue = children.get(1).getValue();

            if (leftValue != null && rightValue != null) {
                this.value = ExpressionEvaluator.evaluateBinary(leftValue, rightValue, operator);
                return;
            }
        }

        this.value = null;
    }

    /**
     * @return the partialEvaluateStatus
     */
    public PartialEvaluateStatus getPartialEvaluateStatus() {
        return partialEvaluateStatus;
    }

    /**
     * @param partialEvaluateStatus the partialEvaluateStatus to set
     */
    public void setPartialEvaluateStatus(PartialEvaluateStatus partialEvaluateStatus) {
        this.partialEvaluateStatus = partialEvaluateStatus;
    }

    /**
     * @return the idNode
     */
    public int getIdNode() {
        return idNode;
    }

    /**
     * @param idNode the idNode to set
     */
    public void setIdNode(int idNode) {
        this.idNode = idNode;
    }

    /**
     * @return the editable
     */
    public Boolean getEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(Boolean editable) {
        this.editable = editable;
    }
}
