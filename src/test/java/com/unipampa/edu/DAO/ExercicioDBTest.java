/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.DAO;

import com.unipampa.edu.model.Exerciciobanco;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heraclitoserver
 */
public class ExercicioDBTest {
    
    public ExercicioDBTest() {
    }

    @Test
    public void testInsertExercico() throws Exception {
        System.out.println("insertExercico");
        String formula = "";
        int correto = 0;
        int ajuda = 0;
        String inico = "";
        String fim = "";
        int nivel = 0;
        int userid = 0;
        boolean expResult = false;
        boolean result = ExercicioDB.insertExercico(formula, correto, ajuda, inico, fim, nivel, userid);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSelectExercicio() throws Exception {
        System.out.println("selectExercicio");
        int idexercicio = 0;
        Exerciciobanco expResult = null;
        Exerciciobanco result = ExercicioDB.selectExercicio(idexercicio);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSelectExercicioList() throws Exception {
        System.out.println("selectExercicioList");
        int iduser = 0;
        List<Exerciciobanco> expResult = null;
        List<Exerciciobanco> result = ExercicioDB.selectExercicioList(iduser);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateExercicioHoraFim() throws Exception {
        System.out.println("updateExercicioHoraFim");
        String fimexercicio = "";
        int idexercicio = 0;
        boolean expResult = false;
        boolean result = ExercicioDB.updateExercicioHoraFim(fimexercicio, idexercicio);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateExercicioCorreto() throws Exception {
        System.out.println("updateExercicioCorreto");
        int idexercicio = 0;
        boolean expResult = false;
        boolean result = ExercicioDB.updateExercicioCorreto(idexercicio);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateExercicioAjuda() throws Exception {
        System.out.println("updateExercicioAjuda");
        int ajuda = 0;
        int idexercicio = 0;
        boolean expResult = false;
        boolean result = ExercicioDB.updateExercicioAjuda(ajuda, idexercicio);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSelectByLastID() {
        System.out.println("selectByLastID");
        int iduser = 0;
        int expResult = 0;
        int result = ExercicioDB.selectByLastID(iduser);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
