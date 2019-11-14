/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author heraclitoserver
 */
public class ConnectionFactoryTest {
    
    public ConnectionFactoryTest() {
    }

    @Test
    public void testGetConnection() throws Exception {
        
        Connection result = ConnectionFactory.getConnection();
        assertNotNull(result);
        
    }

    @Test@Ignore
    public void testCloseConnection_3args() throws Exception {
        System.out.println("closeConnection");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ConnectionFactory.closeConnection(conn, stmt, rs);
        fail("The test case is a prototype.");
    }

    @Test@Ignore
    public void testCloseConnection_Connection_Statement() throws Exception {
        System.out.println("closeConnection");
        Connection conn = null;
        Statement stmt = null;
        ConnectionFactory.closeConnection(conn, stmt);
        fail("The test case is a prototype.");
    }

    @Test@Ignore
    public void testCloseConnection_Connection() throws Exception {
        System.out.println("closeConnection");
        Connection conn = null;
        ConnectionFactory.closeConnection(conn);
        fail("The test case is a prototype.");
    }
    
}
