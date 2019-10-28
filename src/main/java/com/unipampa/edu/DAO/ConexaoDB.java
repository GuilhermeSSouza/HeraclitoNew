/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.DAO;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author heraclitoserver
 */

public class ConexaoDB implements Serializable {

    public static String status = "Não conectou....";
    private static Connection connection = null;

    public  ConexaoDB() {

    }

    public static java.sql.Connection getConexaoDB() {

        try {
            String driveName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driveName);

            String serverName = "localhost";
            String mydataBase = "heraclitodb";
            String url = "jdbc:mysql://" + serverName + "/" + mydataBase;
            String userName = "root";
            String password = "Cg758469";

            connection = DriverManager.getConnection(url, userName, password);

            if (connection != null) {

                status = ("STATUS ---> Conectado com sucesso!");
            } else {

                status = ("STATUS ---> Não foi possivel realizar a conexão");
            }

            return connection;

        } catch (ClassNotFoundException e) {

            System.out.println("O driver expecificado não foi encontrado");

            return null;
        } catch (SQLException e) {

            System.out.println("Não foi possivle conectar ao banco de dados");

            return null;

        }

    }

    public static boolean CloseConexao() {

        try {
            connection.close();
            return true;

        } catch (SQLException e) {
            return false;
        }

    }

    public static java.sql.Connection ReiniciarConexao() {

        CloseConexao();

        return getConexaoDB();

    }

    public static String statusConection() {

        return status;

    }

}
