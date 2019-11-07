/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.heraclito;

import com.unipampa.edu.proposicional.Node;
import com.unipampa.edu.proposicional.ParserHelper;
import com.unipampa.edu.proposicional.ProposicionalEvaluator;
import com.unipampa.edu.proposicional.SyntaxException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Rafael
 */
@Path("/node")
public class NodeResource {

    private static final String SWAC_INTERNAL_SERVICE_BASE = 
            "http://localhost:8090/swac/";

     @Context
    private UriInfo context;

    /**
     * Creates a new instance of NodeResource
     */
    public NodeResource() {
    }

    @GET
    @Path("/{formula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Node parseFormula(@PathParam("formula") String formula) throws SyntaxException {
        Node node = ParserHelper.parseFormula(formula);
        return node;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/avaliarExercicioDecomposicao")
    public Boolean avaliarExercicioDecomposicao(Node content) throws SyntaxException {
        Node node = ParserHelper.parseFormula(content.getText());
        return ProposicionalEvaluator.areEqual(node, content);
    }
    String dcNodeToStringTerm(Node node) {
        String partialEval;
        if (node.getPartialEvaluateStatus() == null) {
            partialEval = "none";
        } else {
            partialEval = node.getPartialEvaluateStatus().toString().toLowerCase();
        }
        String oper;
        if (node.getOperator() == null) {
            oper = "none";
        } else {
            oper = node.getOperator().toString().toLowerCase();
        }
        String children = "[ ";
        for (int i = 0; i < node.getChildren().size(); i++) {
            children += dcNodeToStringTerm( node.getChildren().get(i) );
            if (i + 1 < node.getChildren().size()) {
                children += ", ";
            }
        }
        children += "]";
        
        if(node.getText() == null)
            node.setText("");
        
        return "dcnode( " + node.getText().toLowerCase() + ", " +
                    partialEval + ", " +
                    oper + ", " +
                    children + ")";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/avaliarExercicioDecomposicaoSupervisionado/{userName}")
    public Node avaliarExercicioDecomposicaoSupervisionado(
                @PathParam("userName") String userName,
                Node content) throws SyntaxException {
        Node node = ParserHelper.parseFormula(content.getText());
        ProposicionalEvaluator.areEqualPartial(node, content);
        
        // In supervised mode, send a message to the Heraclito tutor
        // informing what is happening
        String swacInternalServiceUrl = 
                SWAC_INTERNAL_SERVICE_BASE+"student_profile/formula_editor/inform";
        String tutorMsgContent = 
                "new_formula_decomp_step\n"+
                userName+"\n"+
                dcNodeToStringTerm(content);
        NetClientPost.Post(swacInternalServiceUrl,tutorMsgContent);
       
        return content;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/avaliarExercicioCalculo")
    public Boolean avaliarExercicioCalculo(Node content) throws SyntaxException {
        return ProposicionalEvaluator.isCalculationCorrect(content);
    }

     String evNodeToStringTerm(Node node) {
        String partialEval;
        if (node.getPartialEvaluateStatus() == null) {
            partialEval = "none";
        } else {
            partialEval = node.getPartialEvaluateStatus().toString().toLowerCase();
        }
        String oper;
        if (node.getOperator() == null) {
            oper = "none";
        } else {
            oper = node.getOperator().toString().toLowerCase();
        }
        String val;
        if (node.getValue() == null) {
            val = "none";
        } else {
            val = node.getValue().toString().toLowerCase();
        }
        String children = "[ ";
        for (int i = 0; i < node.getChildren().size(); i++) {
            children += evNodeToStringTerm( node.getChildren().get(i) );
            if (i + 1 < node.getChildren().size()) {
                children += ", ";
            }
        }
        children += "]";
        return "evnode( " + node.getText().toLowerCase() + ", " +
                    partialEval + ", " +
                    oper + ", " +
                    val + ", " +
                    children + ")";
    }

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/avaliarExercicioCalculoSupervisionado/{userName}")
    public Node avaliarExercicioCalculoSupervisionado(
            @PathParam("userName") String userName,
            Node content) throws SyntaxException {
        ProposicionalEvaluator.calculateParcial(content);
        
        // In supervised mode, send a message to the Heraclito tutor
        // informing what is happening
        String swacInternalServiceUrl = 
                SWAC_INTERNAL_SERVICE_BASE+"student_profile/formula_editor/inform";
        String tutorMsgContent = 
                "new_formula_eval_step\n"+
                userName+"\n"+
                evNodeToStringTerm(content);
        NetClientPost.Post(swacInternalServiceUrl,tutorMsgContent);
        
        return content;
    }
}
