/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.DAO;

import com.unipampa.edu.model.Passobanco;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heraclitoserver
 */
public class PassoDBTest {
    
    public PassoDBTest() {
    }

    @Test
    public void testInsertPasso() throws Exception {
        System.out.println("insertPasso");
        int correto = 0;
        String passo = "";
        String prob = "";
        String tempopasso = "";
        int idexercico = 0;
        int iduser = 0;
        boolean expResult = false;
        boolean result = PassoDB.insertPasso(correto, passo, prob, tempopasso, idexercico, iduser);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSelectPasso() throws Exception {
        System.out.println("selectPasso");
        int idpasso = 0;
        PassoDB instance = new PassoDB();
        Passobanco expResult = null;
        Passobanco result = instance.selectPasso(idpasso);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSelectPassoList() throws Exception {
        System.out.println("selectPassoList");
        int idexercicio = 0;
        PassoDB instance = new PassoDB();
        List<Passobanco> expResult = null;
        List<Passobanco> result = instance.selectPassoList(idexercicio);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdatePassoCorreto() throws Exception {
        System.out.println("updatePassoCorreto");
        int idpasso = 0;
        String prob = "";
        boolean expResult = false;
        boolean result = PassoDB.updatePassoCorreto(idpasso, prob);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSelectByLastIDPasso() {
        System.out.println("selectByLastIDPasso");
        int iduser = 0;
        int expResult = 0;
        int result = PassoDB.selectByLastIDPasso(iduser);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
