/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.bayesagent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.recommenders.jayes.BayesNet;
import org.eclipse.recommenders.jayes.BayesNode;

/**
 *
 * @author Cristiano
 */
public class IO {
    
    public IO(){
        //connection = OpenConnection();
    }
    
    public void GravarEvidencias(String aluno, Map<BayesNode, String> Evidencias, Map<String, String> Conhecimento){
        
        File ArquivoEvidencias = new File (aluno + "_Evidencias.data");
        try{
            FileWriter fileW = new FileWriter(ArquivoEvidencias);
            BufferedWriter buffW = new BufferedWriter (fileW);
            for (Map.Entry<BayesNode, String> ev : Evidencias.entrySet())
            {
                buffW.write(ev.getKey() + " " + ev.getValue());
                buffW.newLine();
            }
            buffW.close ();
        } catch (IOException e) {
            e.printStackTrace();
        }
            
        File ArquivoConhecimento = new File (aluno + "_Conhecimento.data");
        try{
            FileWriter fileW = new FileWriter(ArquivoConhecimento);
            BufferedWriter buffW = new BufferedWriter (fileW);
            for (Map.Entry<String, String> nodo : Conhecimento.entrySet())
            {
                //System.out.println(nodo.getKey() + " " + nodo.getValue());
                buffW.write(nodo.getKey() + " " + nodo.getValue());
                buffW.newLine();
            }
            buffW.close ();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Map<BayesNode,String> LerEvidencias(String aluno, BayesNet net){
        Map<BayesNode,String> ev = new HashMap();
        
        String fileName = aluno + "_Evidencias.data";

        String line = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                String s[] = line.split(" ");
                ev.put(net.getNode(s[0]),s[1]);
            }
            
            bufferedReader.close();         
        }catch(FileNotFoundException ex) {
            try{
                FileWriter fileW = new FileWriter("bayes.log");
                BufferedWriter buffW = new BufferedWriter (fileW);
//                buffW.write(System.getProperty("user.dir"));
                buffW.close ();
            } catch (IOException e) {
                e.printStackTrace();
            }            
//            System.out.println(System.getProperty("user.dir"));
            System.out.println("Unable to open file '" + fileName + "'");
        }catch(IOException ex) {
            try{
                FileWriter fileW = new FileWriter("bayes.log");
                BufferedWriter buffW = new BufferedWriter (fileW);
//                buffW.write(System.getProperty("user.dir"));
                buffW.write("\nError reading file '" + fileName + "'");
                buffW.close ();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println(System.getProperty("user.dir"));
            System.out.println("Error reading file '" + fileName + "'");
        }
        return ev;
    }
    
    public Map<BayesNode,String> InicializarEvidencias(BayesNet net){
        Map<BayesNode,String> ev = new HashMap();

        String fileName = "EvidenciaBase.data";

        String line;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                String s[] = line.split(" ");
                ev.put(net.getNode(s[0]),s[1]);
            }
            
            bufferedReader.close();         
        }catch(FileNotFoundException ex) {
            try{
                FileWriter fileW = new FileWriter("bayes.log");
                BufferedWriter buffW = new BufferedWriter (fileW);
//                buffW.write(System.getProperty("user.dir"));
                buffW.write("\nUnable to open file '" + fileName + "'");
                buffW.close ();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Unable to open file '" + fileName + "'");
        }catch(IOException ex) {
            try{
                FileWriter fileW = new FileWriter("bayes.log");
                BufferedWriter buffW = new BufferedWriter (fileW);
//                buffW.write(System.getProperty("user.dir"));
                buffW.write("\nError reading file '" + fileName + "'");
                buffW.close ();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Error reading file '" + fileName + "'");
        }
        return ev;
    }
    /*
    private static Connection OpenConnection(){
        try {
            String driverName = "com.mysql.jdbc.Driver";                        
            Class.forName(driverName);
            // Configurando a nossa conexão com um banco de dados//
            String serverName = "cristiano";    //caminho do servidor do BD
            String mydatabase = "Gala!fassi18";        //nome do seu banco de dados

            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "cristiano";        //nome de um usuário de seu BD      
            String password = "Gala!fassi18";      //sua senha de acesso
            Connection aux = DriverManager.getConnection(url, username, password);
            System.out.println("Conexao OK");
            return aux;
        } catch (ClassNotFoundException e) {
            //Driver não encontrado
            System.out.println("O driver expecificado nao foi encontrado.");
        } catch (SQLException e) {
            //Não conseguindo se conectar ao banco
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
        }
        return null;
    }
    */
    public void SalvarEvidencias(String Aluno, String Argumento, String Regra, String Evidencia, String Status, String Prob){
        try{
            FileWriter fileW = new FileWriter("Dados.dat", true);
            BufferedWriter buffW = new BufferedWriter (fileW);
            buffW.write(java.time.Instant.now() +";" + Aluno.trim() + ";" + Argumento.trim() + ";" + Evidencia.trim() + ";" + Status.trim() + ";" + Regra.trim() + ";" + Float.valueOf(Prob)+ "\n");
            buffW.close ();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        /*
        Statement statement;
        try {
            statement = connection.createStatement();
            //Atualiza a tabela de Evidencias
            statement.executeUpdate("INSERT INTO `evidencias`(`DATAHORA`, `ALUNO`, `ARGUMENTO`, `EVIDENCIA`, `STATUS`) "
                    + "VALUES (NOW(), '" + Aluno.trim() + "', '" + Argumento.trim() + "', '" + Evidencia.trim() + "' ," + Status.trim() + ")");
            
            // execute the query, and get a java resultset
            ResultSet rs = statement.executeQuery("SELECT ID FROM evidencias ORDER BY ID DESC LIMIT 1");

            // iterate through the java resultset
            int id = -1;
            while (rs.next())
            {
                id = rs.getInt("id");
            }
            
            //Atualiza a tabela de Regras
            statement.executeUpdate("INSERT INTO `regras_detalhe`(`ID_EVIDENCIAS`, `ALUNO`, `REGRA`, `PROBABILIDADE`) "
                    + "VALUES (" + id + ", '" + Aluno.trim() + "', '" + Regra.trim() + "', " + Float.valueOf(Prob) + ")");
            
        } catch (SQLException ex) {
            Logger.getLogger(BayesAgent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception  ex){
            System.out.println("NULO");
        }
        */
    }
    
}
