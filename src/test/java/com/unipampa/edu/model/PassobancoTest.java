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
public class PassobancoTest {
    
    public PassobancoTest() {
    }

    @Test
    public void testGetId() {
        System.out.println("getId");
        Passobanco instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Passobanco instance = null;
        instance.setId(id);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCorreto() {
        System.out.println("getCorreto");
        Passobanco instance = null;
        int expResult = 0;
        int result = instance.getCorreto();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetCorreto() {
        System.out.println("setCorreto");
        int correto = 0;
        Passobanco instance = null;
        instance.setCorreto(correto);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRegra() {
        System.out.println("getRegra");
        Passobanco instance = null;
        String expResult = "";
        String result = instance.getRegra();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetRegra() {
        System.out.println("setRegra");
        String regra = "";
        Passobanco instance = null;
        instance.setRegra(regra);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetHoraExecultado() {
        System.out.println("getHoraExecultado");
        Passobanco instance = null;
        String expResult = "";
        String result = instance.getHoraExecultado();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetHoraExecultado() {
        System.out.println("setHoraExecultado");
        String horaExecultado = "";
        Passobanco instance = null;
        instance.setHoraExecultado(horaExecultado);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetProbabilidade() {
        System.out.println("getProbabilidade");
        Passobanco instance = null;
        float expResult = 0.0F;
        float result = instance.getProbabilidade();
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetProbabilidade() {
        System.out.println("setProbabilidade");
        float probabilidade = 0.0F;
        Passobanco instance = null;
        instance.setProbabilidade(probabilidade);
        fail("The test case is a prototype.");
    }
    
}
