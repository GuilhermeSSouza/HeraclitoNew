/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.DAO;

import com.unipampa.edu.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heraclitoserver
 */
public class UserDBTest {
    
    public UserDBTest() {
    }

    @Test
    public void testInsertUser() throws Exception {
        System.out.println("insertUser");
        String nome = "";
        String email = "";
        boolean expResult = false;
        boolean result = UserDB.insertUser(nome, email);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSelectUser2() throws Exception {
        System.out.println("selectUser2");
        String nomeuserbusca = "";
        String emailuserbusca = "";
        User expResult = null;
        User result = UserDB.selectUser2(nomeuserbusca, emailuserbusca);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSelectUser() throws Exception {
        System.out.println("selectUser");
        String email = "";
        User expResult = null;
        User result = UserDB.selectUser(email);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
