/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.chart;

import com.unipampa.edu.DAO.ConnectionFactory;
import com.unipampa.edu.model.User;
import com.unipmpa.edu.controller.LoginBean;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import org.primefaces.model.chart.PieChartModel;

/**
 * Bean usada para gerar graficos basicos para o usuario que logar no sistema
 * (Não existe cadastro, o banco é compartilhado pela versão antiga do Heraclito e essa que será talvez a nova versao)
 * @author heraclitoserver
 */
@ManagedBean
@SessionScoped
public class GraficoBean implements Serializable {

    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private PieChartModel pieModel3;
    private LineChartModel lineModel1;
    private LineChartModel lineModel2;
    private LineChartModel zoomModel;
    
    
    
    private String nameUser;
    private int idUser;
    private String userAcesso;

    
    /**
     * Verifica se ao tentar acessar uma URL diretamente, o usuario existe, caso não, redireciona para o login.
     * Essa não é a forma mais eficaz de realizar isso. Filter jsf, ou alguma API do hibernet, etc é seguramente melhor.
     * Mas devido as informações aqui não serem de alto risto, um teste com sessio pode ser suficiente. (Verificar no futuro esse ponto)
     */
    public GraficoBean() {
        userLogado();

    }

    /**
     *
     */
    @PostConstruct
    public void init() {

        createPieModels();
        createLineModels();

    }

    /**
     *
     */
    public final void userLogado() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        User user = (User) session.getAttribute("User");
        if (user == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            nameUser = user.getName();
            idUser = user.getId();
            userAcesso = user.getAcesso();
        
        }

    }

    /**
     *
     * @param event
     */
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     *
     * @return
     */
    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    /**
     *
     * @return
     */
    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    /**
     *
     * @return
     */
    public PieChartModel getPieModel3() {
        return pieModel3;
    }

    /**
     *
     * @return
     */
    public LineChartModel getLineModel1() {

        return lineModel1;
    }

    /**
     *
     * @return
     */
    public LineChartModel getLineModel2() {

        return lineModel2;
    }

    /**
     *
     * @return
     */
    public String getNome(){
        return nameUser;
    }
    private void createPieModels() {
        createPieModel1();
        createPieModel2();
        createPieModel3();
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        ChartSeries regras = new ChartSeries();
        List<Dado> dado = null;
        regras.setLabel("Regras");

        try {
            dado = getDadoRegras(String.valueOf(idUser));
        } catch (Exception ex) {
            Logger.getLogger(GraficoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Dado d : dado) {
            regras.set(d.getNomeparametro(), d.getValorparametro());
        }

        /*boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);*/
        model.addSeries(regras);

        return model;
    }

    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Número de Regras Aplicadas");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Nome"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Qtdes");
        yAxis.setMin(0);
        yAxis.setMax(200);

        zoomModel = initLinearModel();
        zoomModel.setTitle("Zoom");
        zoomModel.setZoom(true);
        zoomModel.setLegendPosition("e");
        yAxis = zoomModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }

    private void createPieModel2() {
        List<Dado> dado = null;
        try {
            dado = getDadoNivel(String.valueOf(idUser));
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
            dado = getDado(String.valueOf(idUser));
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
            dado = getDado(String.valueOf(idUser));
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

    /**
     *
     * @param iduser
     * @return
     * @throws Exception
     */
    public static List<Dado> getDado(String iduser) throws Exception {
        List<Dado> dado = new ArrayList<Dado>();
        Dado novoDado;
        String query = "SELECT exercicio.corretoexercicio, count(exercicio.corretoexercicio) FROM heraclitodb.exercicio where exercicio.user_iduser = ? group by exercicio.corretoexercicio";

        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, iduser);

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

    /**
     *
     * @param iduser
     * @return
     * @throws Exception
     */
    public static List<Dado> getDadoNivel(String iduser) throws Exception {
        List<Dado> dado = new ArrayList<Dado>();
        Dado novoDado;
        String query = "SELECT nivelexercicio, count(exercicio.nivelexercicio) FROM heraclitodb.exercicio where exercicio.user_iduser =?\n"
                + " group by exercicio.nivelexercicio";
        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, iduser);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int nivel = rs.getInt(1);
                int qtdade = rs.getInt(2);

                if (nivel == 1) {
                    novoDado = new Dado("Nível 1", qtdade);

                } else if (nivel == 2) {
                    novoDado = new Dado("Nível 2", qtdade);
                } else if (nivel == 3) {
                    novoDado = new Dado("Nível 3", qtdade);
                } else {
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

    /**
     *
     * @param iduser
     * @return
     * @throws Exception
     */
    public static List<Dado> getDadoPasso(String iduser) throws Exception {
        List<Dado> dado = new ArrayList<Dado>();
        Dado novoDado;
        String query = "SELECT passo.corretopasso, count(passo.corretopasso) FROM heraclitodb.passo where passo.user_iduser =?\n"
                + " group by passo.corretopasso";
        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, iduser);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int correto = rs.getInt(1);
                int qtdade = rs.getInt(2);

                if (correto == 1) {
                    novoDado = new Dado("Correto", qtdade);

                } else {
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

    /**
     *
     * @param iduser
     * @return
     * @throws Exception
     */
    public static List<Dado> getDadoRegras(String iduser) throws Exception {
        List<Dado> dado = new ArrayList<Dado>();
        Dado novoDado;
        String query = "SELECT count(corretopasso), regrapasso FROM heraclitodb.passo where passo.user_iduser =? group by regrapasso;";
        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, iduser);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int qtdade = rs.getInt(1);
                String regra = rs.getString(2);
                novoDado = new Dado(regra, qtdade);
                dado.add(novoDado);

            }

            ConnectionFactory.closeConnection(conn, st, rs);

            return dado;

        } catch (SQLException e) {
            return null;
        }

    }

    /**
     *
     * @param iduser
     * @return
     * @throws Exception
     */
    public static List<Dado> getDadoRegrasCorreto(String iduser) throws Exception {
        List<Dado> dado = new ArrayList<Dado>();
        Dado novoDado;
        String query = "SELECT corretopasso, regrapasso FROM heraclitodb.passo where passo.user_iduser = ?";
        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, iduser);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int qtdade = rs.getInt(1);
                int regra = rs.getInt(2);

                /*
                * criar um enum para as regras
                 */
                //dado.add(novoDado);
            }

            ConnectionFactory.closeConnection(conn, st, rs);

            return dado;

        } catch (SQLException e) {
            return null;
        }

    }

}
