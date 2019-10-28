/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.chart;

import com.unipampa.edu.DAO.ConnectionFactory;
import com.unipmpa.edu.controller.DadosGrafico;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.OhlcChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author heraclitoserver
 */
@ManagedBean
@SessionScoped
public class GraficoBean implements Serializable {

    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private PieChartModel pieModel3;

    @PostConstruct
    public void init() {

        createPieModels();

    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    public PieChartModel getPieModel2() {
        return pieModel2;
    }
    public PieChartModel getPieModel3() {
        return pieModel3;
    }

    private void createPieModels() {
        createPieModel1();
        createPieModel2();
        createPieModel3();
    }
    
    
    private void createPieModel2(){
         List<Dado> dado = null;
        try {
            dado = getDadoNivel(1);
        } catch (Exception ex) {
            Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
        }

        pieModel2 = new PieChartModel();
        for (Dado dadolist : dado) {
            pieModel2.set(dadolist.getNomeparametro(), dadolist.getValorparametro());
        }

        pieModel2.setTitle("Total de Exercicios Realizados Por Nível");
        pieModel2.setLegendPosition("w");
        pieModel2.setShadow(false);
    
    
    }

    private void createPieModel1() {

        List<Dado> dado = null;
        try {
            dado = getDado(1);
        } catch (Exception ex) {
            Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
        }

        pieModel1 = new PieChartModel();
        for (Dado dadolist : dado) {
            pieModel1.set(dadolist.getNomeparametro(), dadolist.getValorparametro());
        }

        pieModel1.setTitle("Total de Exercicios Realizados");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
    }
    
    private void createPieModel3() {

        List<Dado> dado = null;
        try {
            dado = getDado(1);
        } catch (Exception ex) {
            Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
        }

        pieModel3 = new PieChartModel();
        for (Dado dadolist : dado) {
            pieModel3.set(dadolist.getNomeparametro(), dadolist.getValorparametro());
        }

        pieModel3.setTitle("Total de Passo Realizados");
        pieModel3.setLegendPosition("w");
        pieModel3.setShadow(false);
    }

    public static List<Dado> getDado(int iduser) throws Exception {
        List<Dado> dado = new ArrayList<Dado>();
        Dado novoDado;
        String query = "SELECT exercicio.corretoexercicio, count(exercicio.corretoexercicio) FROM heraclitodb.exercicio where exercicio.user_iduser = ? group by exercicio.corretoexercicio";

        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, iduser);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int correto = rs.getInt(1);
                int qtdade = rs.getInt(2);

                if (correto == 0) {
                    novoDado = new Dado("Errado", qtdade);

                } else {
                    novoDado = new Dado("Correto", qtdade);
                }

                dado.add(novoDado);

            }

            ConnectionFactory.closeConnection(conn, st, rs);

            return dado;

        } catch (SQLException e) {
            return null;
        }

    }
    
    public static List<Dado> getDadoNivel(int iduser) throws Exception {
        List<Dado> dado = new ArrayList<Dado>();
        Dado novoDado;
        String query = "SELECT nivelexercicio, count(exercicio.nivelexercicio) FROM heraclitodb.exercicio where exercicio.user_iduser =?\n" +
" group by exercicio.nivelexercicio";
        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, iduser);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int nivel = rs.getInt(1);
                int qtdade = rs.getInt(2);

                if (nivel == 1) {
                    novoDado = new Dado("Nível 1", qtdade);

                }else if(nivel == 2){
                    novoDado = new Dado("Nível 2", qtdade);
                }else if(nivel == 3){
                    novoDado = new Dado("Nível 3", qtdade);
                }else {
                    novoDado = new Dado("Nível 3", qtdade);
                }

                dado.add(novoDado);

            }

            ConnectionFactory.closeConnection(conn, st, rs);

            return dado;

        } catch (SQLException e) {
            return null;
        }

    }
    
     public static List<Dado> getDadoPasso(int iduser) throws Exception {
        List<Dado> dado = new ArrayList<Dado>();
        Dado novoDado;
        String query = "SELECT passo.corretopasso, count(passo.corretopasso) FROM heraclitodb.passo where passo.user_iduser =?\n" +
" group by passo.corretopasso";
        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, iduser);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int correto = rs.getInt(1);
                int qtdade = rs.getInt(2);

                if ( correto == 1) {
                    novoDado = new Dado("Correto", qtdade);

                }else {
                    novoDado = new Dado("Errado", qtdade);
                }

                dado.add(novoDado);

            }

            ConnectionFactory.closeConnection(conn, st, rs);

            return dado;

        } catch (SQLException e) {
            return null;
        }

    }
}
