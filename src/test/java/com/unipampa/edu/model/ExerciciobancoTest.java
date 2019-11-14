/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.model;

import com.unipampa.edu.helper.Datehelp;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heraclitoserver
 */
public class ExerciciobancoTest {
    
    public ExerciciobancoTest() {
    }

    @Test
    public void testGetId() {
        System.out.println("getId");
        Exerciciobanco instance = new Exerciciobanco(1, 0, 0, "A->B-C|-B", Datehelp.getDateTime(), null);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetId() {
        System.out.println("setId");
        Exerciciobanco instance = new Exerciciobanco(1, 0, 0, "A->B-C|-B", Datehelp.getDateTime(), null);
        int id = 0;        
        instance.setId(id);
        assertEquals(id, instance.getId());
        
    }
    @Test
    public void testGetCorreto() {
        System.out.println("getCorreto");
        Exerciciobanco instance = new Exerciciobanco(1, 0, 0, "A->B-C|-B", Datehelp.getDateTime(), null);
        int expResult = 0;
        int result = instance.getCorreto();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetCorreto() {
        System.out.println("setCorreto");
        int correto = 1;
        Exerciciobanco instance = new Exerciciobanco(1, 0, 0, "A->B-C|-B", Datehelp.getDateTime(), null);
        instance.setCorreto(correto);
        assertEquals(correto, instance.getCorreto());
    }

    @Test
    public void testGetAjuda() {
        System.out.println("getAjuda");
        Exerciciobanco instance = new Exerciciobanco(1, 0, 0, "A->B-C|-B", Datehelp.getDateTime(), null);
        int expResult = 0;
        int result = instance.getAjuda();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetAjuda() {
        System.out.println("setAjuda");
        int ajuda = 1;
        Exerciciobanco instance = new Exerciciobanco(1, 0, 0, "A->B-C|-B", Datehelp.getDateTime(), null);
        instance.setAjuda(ajuda);
        assertEquals(instance.getAjuda(), ajuda);
        
    }

    @Test
    public void testGetFromula() {
        System.out.println("getFromula");
        Exerciciobanco instance = new Exerciciobanco(1, 0, 0, "A->B-C|-B", Datehelp.getDateTime(), null);
        String expResult = "A->B-C|-B";
        String result = instance.getFromula();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetFromula() {
        System.out.println("setFromula");
        String fromula ="A->B-C|-C";
        Exerciciobanco instance = new Exerciciobanco(1, 0, 0, "A->B-C|-B", Datehelp.getDateTime(), null);
        instance.setFromula(fromula);
        assertEquals(instance.getFromula(), fromula);
    }

    @Test
    public void testGetInico() {
        System.out.println("getInico");
        Exerciciobanco instance = new Exerciciobanco(1, 0, 0, "A->B-C|-B", Datehelp.getDateTime(), null);
         String result = instance.getInico();
        assertNotNull(result);
        
    }

    @Test
    public void testSetInico() {
        System.out.println("setInico");
        String inicio = Datehelp.getDateTime();
        Exerciciobanco instance = new Exerciciobanco(1, 0, 0, "A->B-C|-B", inicio, null);
        instance.setInico(inicio);
        
        assertEquals(inicio, instance.getInico());
        
    }

    @Test
    public void testGetFim() {
        System.out.println("getFim");
        String expResult = Datehelp.getDateTime();
        Exerciciobanco instance = new Exerciciobanco(1, 0, 0, "A->B-C|-B", Datehelp.getDateTime(), expResult);
        
        String result = instance.getFim();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetFim() {
        System.out.println("setFim");
        String fim = Datehelp.getDateTime();
        Exerciciobanco instance = new Exerciciobanco(1, 0, 0, "A->B-C|-B", Datehelp.getDateTime(), fim);;
        instance.setFim(fim);
        assertEquals(instance.getFim(), fim);
    }
    
}
