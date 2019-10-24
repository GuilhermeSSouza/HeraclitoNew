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
public class User {
    
    private String name;
    private String id;
    private String email;
    private String acesso; /* verificar a prioridade e gerar as restrições de conteudo que ele pode acessar*/

    public User() {
    }

    public User(String name, String id, String email, String acesso) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.acesso = acesso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 59 * hash + (this.acesso != null ? this.acesso.hashCode() : 0);
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
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.acesso == null) ? (other.acesso != null) : !this.acesso.equals(other.acesso)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
}
