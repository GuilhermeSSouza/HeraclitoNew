/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.DAO;

import com.unipampa.edu.helper.Datehelp;
import com.unipampa.edu.model.Passobanco;
import java.text.DateFormat;
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

    /**
     *
     * @throws Exception
     */
    @Test
    public void testInsertPasso() throws Exception {
        System.out.println("insertPasso");
        int correto = 0;
        String passo = "CJ";
        String prob = "0.6753";
        String tempopasso = Datehelp.getDateTime();
        int idexercico = 45;
        int iduser = 1;
        boolean expResult = true;
        boolean result = PassoDB.insertPasso(correto, passo, prob, tempopasso, idexercico, iduser);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSelectPasso() throws Exception {
        System.out.println("selectPasso");
        int idpasso = 15;
        PassoDB instance = new PassoDB();
        Passobanco expResult = null;
        Passobanco result = instance.selectPasso(idpasso);
        assertNotEquals(expResult, result);
        
    }

    @Test
    public void testSelectPassoList() throws Exception {
        System.out.println("selectPassoList");
        int idexercicio = 10;
        PassoDB instance = new PassoDB();
        List<Passobanco> expResult = null;
        List<Passobanco> result = instance.selectPassoList(idexercicio);
        assertNotEquals(expResult, result);
        
    }

    @Test
    public void testUpdatePassoCorreto() throws Exception {
        System.out.println("updatePassoCorreto");
        int idpasso = 16;
        String prob = "0.8765";
        boolean expResult = false;
        boolean result = PassoDB.updatePassoCorreto(idpasso, prob);
        assertNotEquals(expResult, result);
        
    }

    @Test
    public void testSelectByLastIDPasso() {
        System.out.println("selectByLastIDPasso");
        int iduser = 1;
        int expected = -1;
        int result = PassoDB.selectByLastIDPasso(iduser);
        assertNotEquals(expected,result);
        
    }
    
}
