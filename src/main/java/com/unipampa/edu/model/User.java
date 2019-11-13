/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.model;

/**
 * Model do User do sistema, criado para manter user ou parte dos dados na 
 * session a fim de diminuir buscar ao banco (Poucos dados é viavél, sobrecarregar a ssession não é recomendado
 * pode ser melhor fazer varias consultas ao banco)
 * @author heraclitoserver
 */
public class User {
    
    private String name;
    private int id;
    private String email;
    private String acesso; /* verificar a prioridade e gerar as restrições de 
    conteudo que ele pode acessar - (Pensando no futuro quando o sistema suportar
    criar e gerenciar turmas de alunos, essa restrição pode ser usada na criação do filter-- ou mesmo dentro da session)*/

    /**
     *
     */
    public User() {
    }

    /**
     * Contrutor basico
     * @param name
     * @param id
     * @param email
     * @param acesso 
     */
    public User(String name, int id, String email, String acesso) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.acesso = acesso;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getAcesso() {
        return acesso;
    }

    /**
     *
     * @param acesso
     */
    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }

   
    
    
}
