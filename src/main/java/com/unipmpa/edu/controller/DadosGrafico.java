/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipmpa.edu.controller;

import com.unipampa.edu.DAO.ConexaoDB;
import com.unipampa.edu.chart.Dado;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author heraclitoserver
 */
public class DadosGrafico implements Serializable{

    public DadosGrafico() {
    }
    
    
    
    public List<Dado> exercicio(int iduser){
    
    
        List<Dado> dado = new ArrayList<Dado>();
        Dado novoDado;
        String query = "SELECT exercicio.corretoexercicio, count(exercicio.corretoexercicio) FROM heraclitodb.exercicio where exercicio.user_iduser = ? group by exercicio.corretoexercicio";
        
        try {

            Connection conn = ConexaoDB.getConexaoDB();
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, iduser);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int correto = rs.getInt(1);
                int qtdade = rs.getInt(2);
                
                if(correto == 0){
                    novoDado = new Dado("Errado", qtdade);
                
                }else{
                    novoDado = new Dado("Correto", qtdade);
                }
                
                dado.add(novoDado);

                

            }

            st.close();
            ConexaoDB.CloseConexao();

            return dado;

        } catch (SQLException e) {
            return null;
        }
    
    
    
    
    }
    
}
