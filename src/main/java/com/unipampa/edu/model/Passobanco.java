/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.model;

/**
 *
 * @author heraclitoserver
 */
public class Passobanco {
    private int id;
    private int correto;
    private String regra;
    private String horaExecultado;
    private float probabilidade;

    public Passobanco(int id, int correto, String regra, String horaExecultado, float probabilidade) {
        this.id = id;
        this.correto = correto;
        this.regra = regra;
        this.horaExecultado = horaExecultado;
        this.probabilidade = probabilidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCorreto() {
        return correto;
    }

    public void setCorreto(int correto) {
        this.correto = correto;
    }

    public String getRegra() {
        return regra;
    }

    public void setRegra(String regra) {
        this.regra = regra;
    }

    public String getHoraExecultado() {
        return horaExecultado;
    }

    public void setHoraExecultado(String horaExecultado) {
        this.horaExecultado = horaExecultado;
    }

    public float getProbabilidade() {
        return probabilidade;
    }

    public void setProbabilidade(float probabilidade) {
        this.probabilidade = probabilidade;
    }
    
    
    
    
    
    
    
}
