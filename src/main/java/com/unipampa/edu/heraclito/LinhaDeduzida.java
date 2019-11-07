/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.heraclito;

import com.unipampa.edu.execption.LogicException;
import com.unipampa.edu.constantes.Regras;
import java.util.ArrayList;
import java.util.List;
import com.unipampa.edu.constantes.FuncoesString;


/**
 *
 * @author Rafael
 */
public class LinhaDeduzida extends LinhaProva {

    private List<Integer> linhas;

    public LinhaDeduzida(String input, Regras regraAplicada) throws LogicException {
        super(input);
        this.linhas = new ArrayList();
        this.regraAplicada = regraAplicada;
    }

    public LinhaDeduzida(String input, Regras regraAplicada, int nivelHipotese) throws LogicException {
        super(input, nivelHipotese);
        this.linhas = new ArrayList();
        this.regraAplicada = regraAplicada;
    }

    public LinhaDeduzida(String input, Regras regraAplicada, int nivelHipotese, List<Integer> linhas) throws LogicException {
        super(input, nivelHipotese);
        this.linhas = linhas;
        this.regraAplicada = regraAplicada;
    }

    public void setLinhas(List<Integer> linhas) {
        this.linhas = linhas;
    }

    public List<Integer> getLinhas() {
        return this.linhas;
    }

    public String getStringLinhas() {
        if (Regras.HPC.equals(this.regraAplicada)
                || Regras.HRAA.equals(this.regraAplicada)) {
            return "";
        }

        StringBuilder sb = new StringBuilder("");
        if (linhas.size() > 0) {
            sb.append("(");
            
            for (int i : this.linhas) {
                sb.append(i);
                if (Regras.RAA.equals(this.getRegra())
                        || Regras.PC.equals(this.getRegra())) {
                    sb.append("-");
                } else {
                    sb.append(",");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
        }
        return sb.toString();
    }
    
    
        public String getRefs() {
        if (Regras.HPC.equals(this.regraAplicada)
                || Regras.HRAA.equals(this.regraAplicada)) {
            return FuncoesString.toCanonicalFormula(this.getHipoteseResultado().toLowerCase());
        }

        StringBuilder sb = new StringBuilder("");
        if (linhas.size() > 0) {
            for (int i : this.linhas) {
                sb.append(i);
                sb.append(",");                    
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        LinhaDeduzida cloned = (LinhaDeduzida) super.clone();
        List<Integer> clonedL = new ArrayList();
        for (int i : this.linhas) {
            clonedL.add(i);
        }
        cloned.setLinhas(clonedL);
        return cloned;
    }

}
