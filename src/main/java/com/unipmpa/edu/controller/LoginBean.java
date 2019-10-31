/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipmpa.edu.controller;

import com.unipampa.edu.DAO.ConnectionFactory;
import com.unipampa.edu.model.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Boolean isLogin = false;
    private User user = null;

    /**
     *
     */
    public LoginBean() {
    }

    /**
     *
     */
    public void logar() {

        try {
            user = login(usermane, userpassword);
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (user != null) {

            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
            session.setAttribute("isLogin", true);
            session.setAttribute("iduser", user.getId());
            session.setAttribute("User", user);

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Home.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        this.isLogin = false;

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public String getUsermane() {
        return usermane;
    }

    /**
     *
     * @param usermane
     */
    public void setUsermane(String usermane) {
        this.usermane = usermane;
    }

    /**
     *
     * @return
     */
    public String getUserpassword() {
        return userpassword;
    }

    /**
     *
     * @param userpassword
     */
    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    /**
     *
     * @return
     */
    public Boolean getIsLogin() {
        return isLogin;
    }

    /**
     *
     * @param isLogin
     */
    public void setIsLogin(Boolean isLogin) {
        this.isLogin = isLogin;
    }

    /**
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @param login
     * @param senha
     * @return User
     * @throws Exception
     */
    public User login(String login, String senha) throws Exception {

        User userlogin = null;
        String query = "SELECT iduser, nomeuser, emailuser, acessouser FROM heraclitodb.user where emailuser = ? and senhauser = ?;";

        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, login);

            st.setString(2, senha);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                userlogin = new User();
                userlogin.setId(rs.getString(1));
                userlogin.setName(rs.getString(2));
                userlogin.setEmail(rs.getString(3));
                userlogin.setAcesso(rs.getString(4));

            }

            ConnectionFactory.closeConnection(conn, st, rs);

            return userlogin;

        } catch (SQLException e) {
            return null;
        }

    }
    
    /**
     *
     */
    public void logout(){
    
    FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().invalidateSession();
        try {
            fc.getCurrentInstance().getExternalContext().redirect("Heraclito.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    
    }

}
