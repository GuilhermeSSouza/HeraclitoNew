/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.DAO;

import com.unipampa.edu.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author heraclitoserver
 */
public class UserDB {

    public UserDB() {
    }

    public static boolean insertUser(String nome, String email) throws Exception {

        String query = "INSERT INTO user ("
                + "iduser,"
                + "nomeuser,"
                + "emailuser) VALUES ("
                + "null, ?, ?)";

        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, nome);
            st.setString(2, email);

            st.executeUpdate();
            st.close();
            ConnectionFactory.closeConnection(conn, st);
            return true;

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
            return false;
        }

    }

    /**
     * @param nomeuserbusca
     * @param emailuserbusca
     * @return
     */
    public static User selectUser2(String nomeuserbusca, String emailuserbusca) throws Exception {

        String query = " SELECT * FROM heraclitodb.user WHERE user.nomeuser=  ?  AND  user.emailuser=   ? ";

        //String query = "SELECT * FROM heraclitodb.user WHERE nomeuser = " + "\'" + nomeuserbusca + "\'" + " AND emailuser =" + "\'" + emailuserbusca + "\'";
        User user = null;
        //List listuser = new ArrayList();
        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, nomeuserbusca);
            st.setString(2, emailuserbusca);
            ResultSet rs;
            
            
            rs = st.executeQuery();

            while (rs.next()) {

                user = new User(rs.getString("nomeuser"), rs.getString("emailuser"));

                user.setId(rs.getInt("iduser"));
                //listuser.add(user);

            }
            st.close();
            ConnectionFactory.closeConnection(conn, st, rs);
            return user;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;

        }

    }

    public static User selectUser(String email) throws Exception {

        String query = " SELECT * FROM heraclitodb.user WHERE emailuser=  ? ";

        //String query = "SELECT * FROM heraclitodb.user WHERE nomeuser = " + "\'" + nomeuserbusca + "\'" + " AND emailuser =" + "\'" + emailuserbusca + "\'";
        User user = null;
        //List listuser = new ArrayList();
       
        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs;
                        rs = stmt.executeQuery();

            while (rs.next()) {

                user = new User(rs.getString("nomeuser"), rs.getString("emailuser"));

                user.setId(rs.getInt("iduser"));
                //listuser.add(user);

            }
            stmt.close();
            ConnectionFactory.closeConnection(conn, stmt, rs);
            return user;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;

        }

    }

}
