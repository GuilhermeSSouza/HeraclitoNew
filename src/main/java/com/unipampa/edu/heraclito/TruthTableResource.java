/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.heraclito;

import com.unipampa.edu.proposicional.Node;
import com.unipampa.edu.proposicional.Node;
import com.unipampa.edu.proposicional.NodeHelper;
import com.unipampa.edu.proposicional.PartialEvaluateStatus;
import com.unipampa.edu.proposicional.SyntaxException;
import com.unipampa.edu.proposicional.TruthTableColumn;
import com.unipampa.edu.proposicional.TruthTableEvaluator;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Rafael
 */
@Path("/truthTable")
public class TruthTableResource {

    private static final String SWAC_INTERNAL_SERVICE_BASE = 
            "http://localhost:8090/swac/";
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TruthTableResource
     */
    public TruthTableResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/obterValorLogicoVariaveisTabelaVerdade")
    public List<TruthTableColumn> obterValorLogicoVariaveisTabelaVerdade(Node node) throws SyntaxException {
        String[] variables = NodeHelper.extractVariables(node);
        List<TruthTableColumn> variablesWithValues = TruthTableEvaluator.generateLogicValue(variables);
        return variablesWithValues;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/avaliarExercicioTabelaVerdade")
    public Boolean avaliarExercicioTabelaVerdade(List<TruthTableColumn> content, @QueryParam("formula") String formula) throws SyntaxException {
        return TruthTableEvaluator.evaluateTruthTable(formula, content);
    }
    //JCGLUZ
    String truthTableToStringTerm(
            List<TruthTableColumn> gentable, 
            List<TruthTableColumn> table) {
        String ttcols = "[";

        for (int i = 0; i < table.size(); i++) {
            String colname = "none";
            if (table.get(i).getName()!=null)
                colname = table.get(i).getName().toLowerCase();
            
            String formulaeval="none";
            if (table.get(i).getColumnNameEvaluateStatus()!= null) {
                formulaeval = table.get(i).getColumnNameEvaluateStatus().toString().toLowerCase();
            }
            String genformula = "none";
            if (i < gentable.size()) {
                if (gentable.get(i).getName()!=null) {
                    genformula = gentable.get(i).getName().toLowerCase();
                }                
            }
            String colvalueseval;
            if (formulaeval.equals("incorrect")) {
                colvalueseval="none";           
            } else {
                colvalueseval = "correct";
                for (int j = 0; j < table.get(0).getValuesEvaluateStatus().size(); j++) {
                    if (table.get(i).getValuesEvaluateStatus().get(j) == null ||
                            table.get(i).getValues().get(j) == null) {
                        colvalueseval = "none";
                    } else if (table.get(i).getValuesEvaluateStatus().get(j).compareTo(
                                PartialEvaluateStatus.Incorrect) == 0) {
                            colvalueseval = "incorrect";
                            break;
                    }
                }
            }
/*
            String colpartevals = "[";
            for (int j = 0; j < table.get(0).getValuesEvaluateStatus().size(); j++) {
                if (table.get(i).getValuesEvaluateStatus().get(j) == null) {
                    colpartevals += "none";                
                } else {
                    colpartevals += table.get(i).getValuesEvaluateStatus().get(j).toString().toLowerCase();
                }
                if (j + 1 < table.get(0).getValuesEvaluateStatus().size()) {
                    colpartevals += ", ";
                }
            }
            colpartevals+="]";
            String colvalues = "[";
            for (int j = 0; j < table.get(0).getValues().size(); j++) {
                if (table.get(i).getValues().get(j) == null) {
                    colvalues += "none";
                } else {
                    colvalues += table.get(i).getValues().get(j).toString().toLowerCase();
                }
                if (j + 1 < table.get(0).getValues().size()) {
                    colvalues += ", ";
                }
            }
            colvalues+="]";
*/
            ttcols += 
                "ttcol("+colname+", "+formulaeval+", "+genformula+", "+colvalueseval+" )";
            if (i+1 < table.size()) {
                ttcols += ", ";
            }
        }
        ttcols += "]";
        return ttcols;
    }


    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/avaliarExercicioTabelaVerdadeSupervisionado/{userName}")
    public List<TruthTableColumn> avaliarExercicioTabelaVerdadeSupervisionado(
            @PathParam("userName") String userName,
            List<TruthTableColumn> content, 
            @QueryParam("formula") String formula) throws SyntaxException {
        
        List<TruthTableColumn> evaluatedPartialTruthTable =
            TruthTableEvaluator.evaluateTruthTablePartial(formula, content);
        
        // JCGLUZ
        // In supervised mode, send a message to the Heraclito tutor
        // informing what is happening
        List<TruthTableColumn> generatedTable =
            TruthTableEvaluator.generateTruthTableForFormula(formula);
        String swacInternalServiceUrl = 
                SWAC_INTERNAL_SERVICE_BASE+"student_profile/truth_table_editor/inform";
        String tutorMsgContent = 
                "new_truth_tab_step\n"+
                userName+"\n"+
                formula.toString().toLowerCase()+"\n"+
                truthTableToStringTerm(generatedTable,evaluatedPartialTruthTable);
        NetClientPost.Post(swacInternalServiceUrl,tutorMsgContent);
        
        return evaluatedPartialTruthTable;
    }
}
