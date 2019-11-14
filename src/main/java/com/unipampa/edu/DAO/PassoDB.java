/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.DAO;

import com.unipampa.edu.model.Passobanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author heraclitoserver
 */
public class PassoDB {

    Passobanco passo = null;
    
    public PassoDB() {
    }

    public static boolean insertPasso(int correto, String passo, String prob, String tempopasso, int idexercico, int iduser) throws Exception {

        String query = "INSERT INTO passo ("
                + "idpasso,"
                + "corretopasso,"
                + "regrapasso,"
                + "probabilidadepasso,"
                + "tempopasso,"
                + "exercicio_idexercicio,"
                + "user_iduser) VALUES ("
                + "null,?,?,?,?,?,?)";

        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, correto);
            st.setString(2, passo);
            st.setString(3, prob);
            st.setString(4, tempopasso);
            st.setInt(5, idexercico);
            st.setInt(6, iduser);

            st.executeUpdate();
            ConnectionFactory.closeConnection(conn, st);
            return true;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public Passobanco selectPasso(int idpasso) throws Exception {

        Passobanco pb = null;
        String query = "SELECT * FROM heraclitodb.passo WHERE idpasso =" + idpasso;

        try {
            
            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery(query);
            while (rs.next()) {

                int id = rs.getInt("idpasso");
                int co = rs.getInt("corretopasso");
                String regra = rs.getString("regrapasso");
                float pro = rs.getFloat("probabilidadepasso");
                String tpasso = rs.getString("tempopasso");

                pb = new Passobanco(id, co, regra, tpasso, pro);

            }
            stmt.close();
            ConnectionFactory.closeConnection(conn, stmt, rs);
            return pb;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;

        }

    }

    public List<Passobanco> selectPassoList(int idexercicio) throws Exception {

        Passobanco pb = null;
        String query = "SELECT * FROM heraclitodb.passo WHERE exercicio_idexercicio =" + idexercicio;
        List<Passobanco> pblist = new ArrayList<Passobanco>();
        try {

            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery(query);

            
            while (rs.next()) {

                int id = rs.getInt("idpasso");
                int co = rs.getInt("corretopasso");
                String regra = rs.getString("regrapasso");
                float pro = rs.getFloat("probabilidadepasso");
                String tpasso = rs.getString("tempopasso");

                pb = new Passobanco(id, co, regra, tpasso, pro);
                pblist.add(pb);

            }
            stmt.close();
            ConnectionFactory.closeConnection(conn, stmt, rs);
            

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;

        }
        return pblist;
    }

    public static boolean updatePassoCorreto(int idpasso, String prob) throws Exception {
        String query = "UPDATE heraclitodb.passo SET corretopasso = 1, probabilidadepasso = ? WHERE idpasso = ?";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, prob);
            st.setInt(2, idpasso);
            st.executeUpdate();
            st.close();
            ConnectionFactory.closeConnection(conn, st);
            
        } catch (SQLException e) {

            return false;
        }
        return true;
    }

    public static int selectByLastIDPasso(int iduser) {

        int idpassolast = -1;
        String query = "SELECT idpasso FROM heraclitodb.passo WHERE user_iduser = ? ORDER BY idpasso DESC LIMIT 1";

        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, iduser);

            ResultSet rs;

            rs = st.executeQuery();

            while (rs.next()) {
                idpassolast = rs.getInt("idpasso");
            }

            st.close();
            ConnectionFactory.closeConnection(conn, st);
            
        } catch (Exception e) {
            return idpassolast;
        }
        return idpassolast;
    }

}
