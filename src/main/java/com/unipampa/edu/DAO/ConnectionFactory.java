/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.DAO;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 *
 * @author heraclitoserver
 */
public class ConnectionFactory {

    /**
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception{
      try{

         Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/heraclitodb", "root", "Cg758469");
      } 

      catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }   

    /**
     *
     * @param conn
     * @param stmt
     * @param rs
     * @throws Exception
     */
    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs)throws Exception{
        close(conn,stmt,rs);
    }

    /**
     *
     * @param conn
     * @param stmt
     * @throws Exception
     */
    public static void closeConnection(Connection conn, Statement stmt)throws Exception{
        close(conn,stmt,null);
    }

    /**
     *
     * @param conn
     * @throws Exception
     */
    public static void closeConnection(Connection conn)throws Exception{
        close(conn,null,null);
    }

    private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception{
        try{
            if(rs!=null) rs.close();
            if(stmt!=null)stmt.close();
            if(conn!=null)conn.close();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}