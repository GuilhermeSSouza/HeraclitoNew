/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.chart;

import java.io.Serializable;

/**
 * Classe que consite numa lista de dados, basicamente uma String 
 * (Nome que sera exibido no grafico) e (Int que é o dado que sera usado para criar o grafico)
 * @author heraclitoserver
 */
public class Dado implements Serializable{
    
    private String nomeparametro;
    private int valorparametro;

    /**
     * 
     * @param nomeparametro String nome que é exibido no grafico 
     * (Que pode ser recuperado do banco ou formatado a depender do conjunto de dados)
     * @param valorparametro int dado que é propriamente usado para compor o grafico
     */
    public Dado(String nomeparametro, int valorparametro) {
        this.nomeparametro = nomeparametro;
        this.valorparametro = valorparametro;
    }

    /**
     * 
     * @return  int valorparameto
     */
    public int getValorparametro() {
        return valorparametro;
    }

    /**
     *
     * @param valorparametro
     */
    public void setValorparametro(int valorparametro) {
        this.valorparametro = valorparametro;
    }

    /**
     *
     * @return
     */
    public String getNomeparametro() {
        return nomeparametro;
    }

    /**
     *
     * @param nomeparametro
     */
    public void setNomeparametro(String nomeparametro) {
        this.nomeparametro = nomeparametro;
    }
    
    
    
    
}
