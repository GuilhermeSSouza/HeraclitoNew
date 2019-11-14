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
public class Exerciciobanco {
    
    private int  id;
    private int correto;
    private int ajuda;
    private String fromula;
    private String inicio;
    private String fim;

    public Exerciciobanco(int id, int correto, int ajuda, String fromula, String inicio, String fim) {
        this.id = id;
        this.correto = correto;
        this.ajuda = ajuda;
        this.fromula = fromula;
        this.inicio = inicio;
        this.fim = fim;
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

    public int getAjuda() {
        return ajuda;
    }

    public void setAjuda(int ajuda) {
        this.ajuda = ajuda;
    }

    public String getFromula() {
        return fromula;
    }

    public void setFromula(String fromula) {
        this.fromula = fromula;
    }

    public String getInico() {
        return inicio;
    }

    public void setInico(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }
    
    
    
    
    
}
