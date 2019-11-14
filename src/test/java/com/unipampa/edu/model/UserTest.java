/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heraclitoserver
 */
public class UserTest {
    
    public UserTest() {
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        User instance = new User("MeuNome", 0, "meuemail@email.com", "0");
        String expResult = "MeuNome";
        String result = instance.getName();
        assertEquals(expResult, result);
        
    }

    
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        User instance = new User();
        int result = instance.hashCode();
        assertNotNull(result);
        
    }

    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new User("MeuNome", 0, "meuemail@email.com", "0");
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
    }
    
}
