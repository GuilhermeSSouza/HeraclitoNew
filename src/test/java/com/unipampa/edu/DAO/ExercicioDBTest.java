/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.DAO;

import com.unipampa.edu.model.Exerciciobanco;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author heraclitoserver
 */
public class ExercicioDBTest {

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return dateFormat.format(date);
    }

    public ExercicioDBTest() {
    }

    @Test
    public void testInsertExercico() {
        System.out.println("insertExercico");
        String formula = "B->A,B |-A";
        int correto = 1;
        int ajuda = 0;

        String dStr = getDateTime();
        boolean result = false;
        String fim = null;
        int nivel = 1;
        int userid = 1;
        boolean expResult = true;
        try {
            result = ExercicioDB.insertExercico(formula, correto, ajuda, dStr, fim, nivel, userid);
        } catch (Exception e) {
        }

        assertEquals(expResult, result);
    }

    @Test
    public void testSelectExercicio() throws Exception {
        System.out.println("selectExercicio");
        int idexercicio = 10;
        
            Exerciciobanco result = ExercicioDB.selectExercicio(idexercicio);
            assertNotNull(result);
        
       
    }

    @Test
    public void testSelectExercicioList() throws Exception {
        System.out.println("selectExercicioList");
        int iduser = 1;
        List<Exerciciobanco> result = ExercicioDB.selectExercicioList(iduser);
        assertNotNull(result);

    }

    @Test
    public void testUpdateExercicioHoraFim() throws Exception {
        System.out.println("updateExercicioHoraFim");
        String fimexercicio = getDateTime();
        int idexercicio = 15;
        boolean result = ExercicioDB.updateExercicioHoraFim(fimexercicio, idexercicio);
        assertEquals(true, result);

    }

    @Test
    public void testUpdateExercicioCorreto() throws Exception {
        System.out.println("updateExercicioCorreto");
        int idexercicio = 11;
        boolean expResult = true;
        boolean result = ExercicioDB.updateExercicioCorreto(idexercicio);
        assertEquals(expResult, result);

    }

    @Test
    public void testUpdateExercicioAjuda() throws Exception {
        System.out.println("updateExercicioAjuda");
        int ajuda = 1;
        int idexercicio = 13;
        boolean expResult = true;
        boolean result = ExercicioDB.updateExercicioAjuda(ajuda, idexercicio);
        assertEquals(expResult, result);

    }

    @Test
    public void testSelectByLastID() {
        System.out.println("selectByLastID");
        int iduser = 1;

        int result = ExercicioDB.selectByLastID(iduser);
        assertNotNull(result);

    }

}
