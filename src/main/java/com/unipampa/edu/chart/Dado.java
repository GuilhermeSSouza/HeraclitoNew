/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.chart;

import java.io.Serializable;

/**
 *
 * @author heraclitoserver
 */
public class Dado implements Serializable{
    
    private String nomeparametro;
    private int valorparametro;

    public Dado(String nomeparametro, int valorparametro) {
        this.nomeparametro = nomeparametro;
        this.valorparametro = valorparametro;
    }

    public int getValorparametro() {
        return valorparametro;
    }

    public void setValorparametro(int valorparametro) {
        this.valorparametro = valorparametro;
    }

    public String getNomeparametro() {
        return nomeparametro;
    }

    public void setNomeparametro(String nomeparametro) {
        this.nomeparametro = nomeparametro;
    }
    
    
    
    
}
