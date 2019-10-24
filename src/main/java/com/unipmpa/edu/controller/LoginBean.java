/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipmpa.edu.controller;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author heraclitoserver
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    private String usermane;
    private String userpassword;
    private Boolean isLogin;

    public LoginBean() {
    }

    public String logar() {

        if (usermane.equals("guilthys@gmail.com") && userpassword.equals("Cg758469")) {

            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
            session.setAttribute("isLogin", true);
            

            return "/Home";
        }

        this.isLogin = false;

        return "";
    }

       public void isLogado() throws IOException {

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        this.isLogin = (Boolean) session.getAttribute("isLogin");

        if (isLogin) {

         FacesContext.getCurrentInstance().getExternalContext().redirect("/Home.xhtml");;

        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");;
    }

    public String getUsermane() {
        return usermane;
    }

    public void setUsermane(String usermane) {
        this.usermane = usermane;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public Boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Boolean isLogin) {
        this.isLogin = isLogin;
    }

}
