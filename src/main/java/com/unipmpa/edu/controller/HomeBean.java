/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipmpa.edu.controller;

import com.unipampa.edu.model.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Bean basica que controla ações da HOME
 * @author heraclitoserver
 */
@ManagedBean
@SessionScoped
public class HomeBean {
    
    private String nameUser;
    private String idUser;
    private String userAcesso;
   
    /**
     *
     */
    public HomeBean() {
        userLogado();
    }
    
    /**
     *
     */
    public final void userLogado() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        User user = (User) session.getAttribute("User");
        if (user == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            nameUser = user.getName();
            idUser = user.getId();
            userAcesso = user.getAcesso();
        
        }

    }

    /**
     *
     * @return nameUser
     */
    public String getNameUser() {
        return nameUser;
    }

    /**
     *
     * @return idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     *
     * @return userAcesso
     */
    public String getUserAcesso() {
        return userAcesso;
    }
    
    
    
    
    
    
    
    
    
}
