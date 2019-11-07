/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.bayesagent;

import java.util.HashMap;
import java.util.Map;

public class BayesAgent{
    
    private Map<String,Aluno> Alunos = new HashMap();
    String prob = null;
    public BayesAgent(){
        
    }
    
    public void putMessage(Object myObject){
        // create a Statement from the connection
        //System.out.println(String.valueOf(myObject));

        //Formato myObject: ALUNO#ARGUMENTO#EVIDENCIA#STATUS
        String s[] = String.valueOf(myObject).split("#");
        String Key = s[0]+"#"+s[1];

        if(!Alunos.containsKey(Key)){
            Alunos.put(Key, new Aluno(s[0], s[1]));
        }

        if(s.length == 4){
            System.out.println("O que chegou aqui: " + s);
            Alunos.get(Key).evidence.put(Alunos.get(Key).net.getNode(s[2]),s[3]);

            Map<String, String> Rede = new HashMap();
            String Regra = getRegra(s[2]);

            Rede.putAll(Alunos.get(Key).AtualizarConhecimento(Alunos.get(Key).net, Alunos.get(Key).evidence, Regra));

            Alunos.get(Key).io.SalvarEvidencias(s[0], s[1], Regra, s[2], s[3], Rede.get("Conhecimento" + Regra));
            prob = Rede.get("Conhecimento" + Regra);

            //Alunos.get(s[0]).io.GravarEvidencias(s[0], Alunos.get(Key).evidence, Rede);
        }else{

            if(s[2].equals("NovoArgumento")){
                System.out.println("Novo Argumento Informado: " + Key);
                if(Alunos.containsKey(Key)){
                    Alunos.remove(Key);
                }
                Alunos.put(Key, new Aluno(s[0], s[1]));
                Map<String, String> Rede = new HashMap();
                String Regra = getRegra(s[2]);

                Rede.putAll(Alunos.get(Key).AtualizarConhecimento(Alunos.get(Key).net, Alunos.get(Key).evidence, Regra));
                
                
                
                /**
                 * teste null e update banco com probabilidade
                 */
                
                
                prob = Rede.get("Conhecimento" + Regra);

                //Alunos.get(s[0]).io.GravarEvidencias(s[0], Alunos.get(Key).evidence, Rede);
            }else if(s[2].equals("Conceito")){
                Alunos.get(Key).Conceito = 1;
            }else if(s[2].equals("Exemplo")){
                Alunos.get(Key).Exemplo = 1;
            }else if(s[2].equals("Ajuda")){
                Alunos.get(Key).Ajuda = 1;
            }else if(s[2].equals("FimProva")){
                
            }
        }
    }
        
    private String getRegra(String ev){
        String aux = ev.replace("Corretude", "");
        aux = aux.replace("Ajuda", "");
        aux = aux.replace("Exemplo", "");
        aux = aux.replace("Conceito", "");
        return aux;
    }
    
    public String getProb(){
    return prob;
    }
    
}
