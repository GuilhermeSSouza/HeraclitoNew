/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.DAO;

import com.unipampa.edu.DAO.UserDB;
import com.unipampa.edu.model.Exerciciobanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author heraclitoserver
 */
public class ExercicioDB {

    UserDB us = null;
    ConnectionFactory conexaodb = null;

    public ExercicioDB() {
    }

    public static boolean insertExercico(String formula, int correto, int ajuda, String inico, String fim, int nivel, int userid) throws Exception {

        String query = "INSERT INTO exercicio ("
                + "idexercicio,"
                + "formulaexercicio,"
                + "corretoexercicio,"
                + "ajudaexercicio,"
                + "inicioexercicio,"
                + "fimexercicio,"
                + "nivelexercicio,"
                + "user_iduser) VALUES ("
                + "null,?,?,?,?,?,?,?)";

        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, formula);
            st.setInt(2, correto);
            st.setInt(3, ajuda);
            st.setString(4, inico);
            st.setString(5, fim);
            st.setInt(6, nivel);
            st.setInt(7, userid);

            st.executeUpdate();
            
            ConnectionFactory.closeConnection(conn, st);

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return false;
        }
        
        return false;
    }

    public static Exerciciobanco selectExercicio(int idexercicio) throws Exception {
        String query = "SELECT * FROM heraclitodb.exercicio WHERE idexercicio = ?";
        Exerciciobanco edb = null;
        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, idexercicio);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("idexercicio");
                String formula = rs.getString("formulaexercicio");
                int correto = rs.getInt("corretoexercicio");
                int ajuda = rs.getInt("ajudaexercicio");
                String datainicio = rs.getString("inicioexercicio");
                String datafim = rs.getString("fimexercicio");

                edb = new Exerciciobanco(id, correto, ajuda, formula, datainicio, datafim);

            }

           
            ConnectionFactory.closeConnection(conn, st, rs);

            return edb;

        } catch (SQLException e) {
            return null;
        }

    }

    public static List<Exerciciobanco> selectExercicioList(int iduser) throws Exception {
        String query = "SELECT * FROM heraclitodb.exercicio WHERE user_iduser = ?";
        List<Exerciciobanco> elist = new ArrayList<Exerciciobanco>();
        Exerciciobanco edb = null;
        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, iduser);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("idexercicio");
                String formula = rs.getString("formulaexercicio");
                int correto = rs.getInt("corretoexercicio");
                int ajuda = rs.getInt("ajudaexercicio");
                String datainicio = rs.getString("inicioexercicio");
                String datafim = rs.getString("fimexercicio");

                edb = new Exerciciobanco(id, correto, ajuda, formula, datainicio, datafim);
                elist.add(edb);

            }

            ConnectionFactory.closeConnection(conn, st, rs);
            return elist;

        } catch (SQLException e) {
            return null;
        }

    }

    public static boolean updateExercicioHoraFim(String fimexercicio, int idexercicio) throws Exception {

        String query = "UPDATE heraclitodb.exercicio SET fimexercicio = ? WHERE idexercicio = ?";

        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, fimexercicio);
            st.setInt(2, idexercicio);

            st.executeUpdate();
            ConnectionFactory.closeConnection(conn, st);
            return true;

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return false;
        }

    }

    public static boolean updateExercicioCorreto(int idexercicio) throws Exception {

        String query = "UPDATE heraclitodb.exercicio SET corretoexercicio = ? WHERE idexercicio = ?";

        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, 1);
            st.setInt(2, idexercicio);

            st.executeUpdate();
            ConnectionFactory.closeConnection(conn, st);
            return true;

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return false;
        }

    }

    public static boolean updateExercicioAjuda(int ajuda, int idexercicio) throws Exception {

        String query = "UPDATE heraclitodb.exercicio SET ajudaexercicio = ? WHERE idexercicio = ?";

        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, ajuda);
            st.setInt(2, idexercicio);

            st.executeUpdate();
            ConnectionFactory.closeConnection(conn, st);
            return true;

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return false;
        }

    }

    public static int selectByLastID(int iduser) {

        int idexercicio = -1;
        String query = "SELECT idexercicio FROM heraclitodb.exercicio WHERE user_iduser = ? ORDER BY idexercicio DESC LIMIT 1";

        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, iduser);

            ResultSet rs;

            rs = st.executeQuery();

            while (rs.next()) {
                idexercicio = rs.getInt("idexercicio");
            }

            ConnectionFactory.closeConnection(conn, st, rs);
            return idexercicio;
        } catch (Exception e) {
        }
        return idexercicio;
    }

}
