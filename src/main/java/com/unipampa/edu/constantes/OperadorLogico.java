/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.constantes;

/**
 *
 * @author Rafael
 */
public enum OperadorLogico {
    CONJUNCAO("^", 1, true), DISJUNCAO("v", 1, true), CONDICIONAL("->", 2, false), 
        BICONDICIONAL("<->", 2, true), NEGACAO("~", 0, false), RESULTA("|-", 3, false);
    
    private final String expressao;
    private final int prioridade;
    private final boolean ehComutativo;
    OperadorLogico(String expressao, int prio, boolean ehComutativo) {
        this.expressao = expressao;
        this.prioridade = prio;
        this.ehComutativo = ehComutativo;
    }
    
    public String toString() {
        return this.expressao;
    }
    
    public int getLength() {
        return this.expressao.length();
    }
    
    public String getExpressao() {
        return this.expressao;
    }
    
    public int getPrioridade() {
        return this.prioridade;
    }
         
}
