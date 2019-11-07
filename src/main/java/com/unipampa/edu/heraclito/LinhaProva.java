/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.heraclito;

import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.execption.LogicException;

/**
 *
 * @author Rafael
 */
public abstract class LinhaProva implements Cloneable {

    protected String input;
    protected Regras regraAplicada;
    protected boolean estaTravada;
    protected int nivelHipotese;
    protected String hipoteseResultado;

    public LinhaProva(String input)
            throws LogicException {        
        input = FuncoesString.upperCaseNotV(FuncoesString.removerEspacos(input));
        if (input == null || input.isEmpty()) {
            throw new LogicException("Linha vazia! Digite um valor.");
        }
        if (!FuncoesString.saoParentesesValidos(input)) {
            throw new LogicException("Linha inválida! Faltam ou sobram parênteses.");
        }
        this.input = FuncoesString.removerParentesesReduntantes(input);
        this.estaTravada = false;
        this.nivelHipotese = 0;
        this.hipoteseResultado = "NONE";
    }

    public LinhaProva(String input, int nivelHipotese)
            throws LogicException {
        this(input);
        this.nivelHipotese = nivelHipotese;
    }

    public boolean ehInputValido(String input) {
        return FuncoesString.saoParentesesValidos(input);
    }

    @Override
    public String toString() {
        StringBuilder sblinha = new StringBuilder("");
        for (int i = 0; i < this.nivelHipotese; i++) {
            sblinha.append("|");
        }
        sblinha.append(input);
        /*if (estaTravada) {
            sblinha.append(" (lk)");
        }*/
        return sblinha.toString();
    }

    public String getLinha() {
        return this.input;
    }

    public int getNivelHipotese() {
        return this.nivelHipotese;
    }

    public void setLinha(String input) {
        this.input = input;
    }
    
    public void setNivelHipotese(int nivelHipotese) {
        this.nivelHipotese = nivelHipotese;
    }

    public Regras getRegra() {
        return this.regraAplicada;
    }

    public String getHipoteseResultado() {
        return hipoteseResultado;
    }

    public void setHipoteseResultado(String hipoteseResultado) {
        this.hipoteseResultado = hipoteseResultado;
    }
    
    public int getPosicaoOperadorPrincipal() {
        OperadorLogico op = null;
        int posOp = -1;
        int parentesesCounter = 0;
        int parentesesOp = -1;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                parentesesCounter++;
            } else if (c == ')') {
                parentesesCounter--;
            } else if (parentesesOp == -1 || parentesesCounter <= parentesesOp) {
                String currentStr = input.substring(i);
                for (OperadorLogico opIt : OperadorLogico.values()) {
                    if (currentStr.startsWith(opIt.getExpressao())) {
                        if ((op == null)
                                || (parentesesCounter < parentesesOp)
                                || ((opIt.getPrioridade() > op.getPrioridade())
                                && (parentesesOp == parentesesCounter))) {
                            parentesesOp = parentesesCounter;
                            posOp = i;
                            op = opIt;
                        }
                    }
                }
            }
        }
        return posOp;
    }

    public OperadorLogico getOperadorPrincipal() {
        OperadorLogico op = null;
        int posOp = -1;
        int parentesesCounter = 0;
        int parentesesOp = -1;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                parentesesCounter++;
            } else if (c == ')') {
                parentesesCounter--;
            }

            if (parentesesOp == -1 || parentesesCounter <= parentesesOp) {
                String currentStr = input.substring(i);
                for (OperadorLogico opIt : OperadorLogico.values()) {
                    if (currentStr.startsWith(opIt.getExpressao())) {
                        if ((op == null)
                                || (parentesesCounter < parentesesOp)
                                || ((opIt.getPrioridade() > op.getPrioridade())
                                && (parentesesOp == parentesesCounter))) {
                            parentesesOp = parentesesCounter;
                            posOp = i;
                            op = opIt;
                        }
                    }
                }
            }
        }
        return op;
    }

    public Boolean estaTravada() {
        return estaTravada;
    }

    public void Travar() {
        estaTravada = true;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            if (((LinhaProva) obj).input.equals(this.input)) {
                return true;
            }
        } catch (Exception e) {
        }
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
