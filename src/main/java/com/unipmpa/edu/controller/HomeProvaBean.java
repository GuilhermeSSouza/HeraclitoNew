/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipmpa.edu.controller;

import com.unipampa.edu.DAO.ExercicioDB;
import com.unipampa.edu.execption.LogicException;
import com.unipampa.edu.helper.Datehelp;
import com.unipampa.edu.heraclito.Manager;
import com.unipampa.edu.heraclito.Prova;
import com.unipampa.edu.model.User;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author heraclitoserver
 */
@ManagedBean
@ApplicationScoped
public class HomeProvaBean {

    private String nameUser;
    private int idUser;
    private String userAcesso;
    private String novaProva;
    private String SelectProvaBasica;
    private String selectProvaIntermediaria;
    private String selectProvaAvancada;

    /**
     * Creates a new instance of HomeProva
     */
    public HomeProvaBean() {
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
        } else {
            nameUser = user.getName();
            idUser = user.getId();
            userAcesso = user.getAcesso();

        }

    }

    public void exercicoHipotese() {

    }

    
    
    /**
     * Refatorar este metodo a fim de que ele seja um so com os prava.
     * Isso diminui o uso das session (Rever o Manager e sua necessidade)
     * @throws Exception 
     */
    public void provaBasica() throws Exception {

        try {

            Prova prova = new Prova(String.valueOf(idUser), SelectProvaBasica);

            Manager manager = new Manager(String.valueOf(idUser), prova);
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("manager", manager);

            //Inserindo novo exercicio no banco
            ExercicioDB eDB = new ExercicioDB();
            Datehelp dH = new Datehelp();
            String horaexercicio = dH.getDateTime();

            //Pegar nivel do exercicio
            if (nameUser != null) {
                boolean insertOK = eDB.insertExercico(SelectProvaBasica, 0, 0, horaexercicio, null, 1, idUser);
                if (insertOK) {
                    Integer idexercicio = eDB.selectByLastID(idUser);
                    fc = FacesContext.getCurrentInstance();
                    session = (HttpSession) fc.getExternalContext().getSession(false);
                    session.setAttribute("idExercicio", idexercicio);

                }

            }

        } catch (LogicException e) {
        }

    }

    public void provaIntermediarias() {

    }

    public void provaAvancadas() {

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
    public String getUserAcesso() {
        return userAcesso;
    }

    public String getNovaProva() {
        return novaProva;
    }

    public void setNovaProva(String novaProva) {
        this.novaProva = novaProva;
    }

    public String getSelectProvaBasica() {
        return SelectProvaBasica;
    }

    public void setSelectProvaBasica(String SelectProvaBasica) {
        this.SelectProvaBasica = SelectProvaBasica;
    }

    public String getSelectProvaIntermediaria() {
        return selectProvaIntermediaria;
    }

    public void setSelectProvaIntermediaria(String selectProvaIntermediaria) {
        this.selectProvaIntermediaria = selectProvaIntermediaria;
    }

    public String getSelectProvaAvancada() {
        return selectProvaAvancada;
    }

    public void setSelectProvaAvancada(String selectProvaAvancada) {
        this.selectProvaAvancada = selectProvaAvancada;
    }

}
