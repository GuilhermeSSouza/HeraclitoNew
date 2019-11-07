/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.bayesagent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.recommenders.jayes.BayesNet;
import org.eclipse.recommenders.jayes.BayesNode;
import org.eclipse.recommenders.jayes.inference.IBayesInferer;
import org.eclipse.recommenders.jayes.inference.junctionTree.JunctionTreeAlgorithm;

public class Aluno {
    
    public IO io = new IO();
    public BayesNet net = new BayesNet();
    public Map<BayesNode,String> evidence = new HashMap();
    public String Aluno;
    public String Argumento;
        
    public int Conceito;
    public int Exemplo;
    public int Ajuda;
    
    public Aluno(String aluno, String argumento){
        Aluno = aluno;
        Argumento = argumento;
        Conceito = 0;
        Exemplo = 0;
        Ajuda = 0;
        
        net = newNet();
        /*
        BayesNode ConhecimentoEquivalencia = CriarRegra("Equivalencia", net);
        BayesNode ConhecimentoDuplaNegacao = CriarRegra("DuplaNegacao", net);
        BayesNode ConhecimentoAdicao = CriarRegra("Adicao", net);
        BayesNode ConhecimentoConjuncao = CriarRegra("Conjuncao", net);
        
        CriarRegra("SilogismoHipotetico", net, ConhecimentoDuplaNegacao);
        CriarRegra("ProvaCondicional", net, ConhecimentoDuplaNegacao);
        CriarRegra("ModusPonens", net, ConhecimentoDuplaNegacao);
        CriarRegra("Inconsistencia", net, ConhecimentoDuplaNegacao);
        
        CriarRegra("ModusTollens", net, ConhecimentoEquivalencia, ConhecimentoDuplaNegacao);
        CriarRegra("DilemaConstrutivo", net, ConhecimentoEquivalencia, ConhecimentoAdicao);
        CriarRegra("Simplificacao", net, ConhecimentoAdicao, ConhecimentoConjuncao);
        CriarRegra("SilogismoDisjuntivo", net, ConhecimentoDuplaNegacao, ConhecimentoConjuncao);
        CriarRegra("ReducaoAbsurdo", net, ConhecimentoDuplaNegacao, ConhecimentoConjuncao);
        CriarRegra("Exportacao", net, ConhecimentoEquivalencia, ConhecimentoConjuncao);
        BayesNode ConhecimentoEliminacaoDisjuncao = CriarRegra("EliminacaoDisjuncao", net, ConhecimentoEquivalencia, ConhecimentoConjuncao);
        CriarRegra("EliminacaoEquivalencia", net, ConhecimentoEliminacaoDisjuncao);
        */        
        
        evidence = io.InicializarEvidencias(net);
        if (evidence.isEmpty()){
            evidence.putAll(InicializarEvidencias("Equivalencia", net));
            evidence.putAll(InicializarEvidencias("DuplaNegacao", net));
            evidence.putAll(InicializarEvidencias("Adicao", net));
            evidence.putAll(InicializarEvidencias("Conjuncao", net));
            evidence.putAll(InicializarEvidencias("SilogismoHipotetico", net));
            evidence.putAll(InicializarEvidencias("ProvaCondicional", net));
            evidence.putAll(InicializarEvidencias("ModusPonens", net));
            evidence.putAll(InicializarEvidencias("Inconsistencia", net));
            evidence.putAll(InicializarEvidencias("ModusTollens", net));
            evidence.putAll(InicializarEvidencias("DilemaConstrutivo", net));
            evidence.putAll(InicializarEvidencias("Simplificacao", net));
            evidence.putAll(InicializarEvidencias("SilogismoDisjuntivo", net));
            evidence.putAll(InicializarEvidencias("ReducaoAbsurdo", net));
            evidence.putAll(InicializarEvidencias("Exportacao", net));
            evidence.putAll(InicializarEvidencias("EliminacaoDisjuncao", net));
            evidence.putAll(InicializarEvidencias("EliminacaoEquivalencia", net));
        }
        
        Map<String, String> Rede = new HashMap();
        
        Rede.putAll(AtualizarConhecimento(net, evidence, ""));
        
        //io.GravarEvidencias(Aluno, evidence, Rede);
        
    }
    
    
    public BayesNet newNet(){
        BayesNet net = new BayesNet();
        IO io = new IO();
        Map<BayesNode,String> evidence = new HashMap();
        
        BayesNode ConhecimentoEquivalencia = CriarRegra("Equivalencia", net);
        BayesNode ConhecimentoDuplaNegacao = CriarRegra("DuplaNegacao", net);
        BayesNode ConhecimentoAdicao = CriarRegra("Adicao", net);
        BayesNode ConhecimentoConjuncao = CriarRegra("Conjuncao", net);
        
        CriarRegra("SilogismoHipotetico", net, ConhecimentoDuplaNegacao);
        CriarRegra("ProvaCondicional", net, ConhecimentoDuplaNegacao);
        CriarRegra("ModusPonens", net, ConhecimentoDuplaNegacao);
        CriarRegra("Inconsistencia", net, ConhecimentoDuplaNegacao);
        
        CriarRegra("ModusTollens", net, ConhecimentoEquivalencia, ConhecimentoDuplaNegacao);
        CriarRegra("DilemaConstrutivo", net, ConhecimentoEquivalencia, ConhecimentoAdicao);
        CriarRegra("Simplificacao", net, ConhecimentoAdicao, ConhecimentoConjuncao);
        CriarRegra("SilogismoDisjuntivo", net, ConhecimentoDuplaNegacao, ConhecimentoConjuncao);
        CriarRegra("ReducaoAbsurdo", net, ConhecimentoDuplaNegacao, ConhecimentoConjuncao);
        CriarRegra("Exportacao", net, ConhecimentoEquivalencia, ConhecimentoConjuncao);
        BayesNode ConhecimentoEliminacaoDisjuncao = CriarRegra("EliminacaoDisjuncao", net, ConhecimentoEquivalencia, ConhecimentoConjuncao);
        CriarRegra("EliminacaoEquivalencia", net, ConhecimentoEliminacaoDisjuncao);
        
        return net;
    }
    
    public Map<BayesNode, String> InicializarEvidencias(String regra, BayesNet net){
        Map<BayesNode, String> ev = new HashMap();
                
        BayesNode Corretude = net.getNode("Corretude" + regra);
        BayesNode Exemplo = net.getNode("Exemplo" + regra);
        BayesNode Conceito = net.getNode("Conceito" + regra);
        BayesNode Ajuda = net.getNode("Ajuda" + regra);
        ev.put(Corretude, "0");
        ev.put(Exemplo, "0");
        ev.put(Conceito, "0");
        ev.put(Ajuda, "0");

        return ev;
    }
    
    public Map<BayesNode, String> AtualizarEvidencias(BayesNet net, Map<BayesNode, String> ev, String Regra){
        ev.replace(net.getNode("Conceito" + Regra), String.valueOf(Conceito));
        ev.replace(net.getNode("Exemplo" + Regra), String.valueOf(Exemplo));
        ev.replace(net.getNode("Ajuda" + Regra), String.valueOf(Ajuda));

        Conceito = 0;
        Exemplo = 0;
        Ajuda = 0;
        
        return ev;
    }
    
    public Map<String, String> AtualizarConhecimento(BayesNet net, Map<BayesNode, String> ev, String Regra){
        Map<String, String> rede = new HashMap();
        
        if(!Regra.equals(""))
            ev = AtualizarEvidencias(net, ev, Regra);
        
        IBayesInferer inferer = new JunctionTreeAlgorithm();
        inferer.setNetwork(net);
        inferer.setEvidence(ev);
        
        /*
        for(Map.Entry<BayesNode, String> entry : ev.entrySet()) {
            BayesNode key = entry.getKey();
            String value = entry.getValue();

            System.out.println(key + " - " + value);
        }
        */
        
        
        //System.out.println(ev.containsKey(net.getNode("Conceito" + regra)));
        
        BayesNode ConhecimentoEquivalencia = net.getNode("ConhecimentoEquivalencia");
        BayesNode ConhecimentoDuplaNegacao = net.getNode("ConhecimentoDuplaNegacao");
        BayesNode ConhecimentoAdicao = net.getNode("ConhecimentoAdicao");
        BayesNode ConhecimentoConjuncao = net.getNode("ConhecimentoConjuncao");
        BayesNode ConhecimentoSilogismoHipotetico = net.getNode("ConhecimentoSilogismoHipotetico");
        BayesNode ConhecimentoProvaCondicional = net.getNode("ConhecimentoProvaCondicional");
        BayesNode ConhecimentoModusPonens = net.getNode("ConhecimentoModusPonens");
        BayesNode ConhecimentoInconsistencia = net.getNode("ConhecimentoInconsistencia");
        BayesNode ConhecimentoModusTollens = net.getNode("ConhecimentoModusTollens");
        BayesNode ConhecimentoDilemaConstrutivo = net.getNode("ConhecimentoDilemaConstrutivo");
        BayesNode ConhecimentoSimplificacao = net.getNode("ConhecimentoSimplificacao");
        BayesNode ConhecimentoSilogismoDisjuntivo = net.getNode("ConhecimentoSilogismoDisjuntivo");
        BayesNode ConhecimentoReducaoAbsurdo = net.getNode("ConhecimentoReducaoAbsurdo");
        BayesNode ConhecimentoExportacao = net.getNode("ConhecimentoExportacao");
        BayesNode ConhecimentoEliminacaoDisjuncao = net.getNode("ConhecimentoEliminacaoDisjuncao");
        BayesNode ConhecimentoEliminacaoEquivalencia = net.getNode("ConhecimentoEliminacaoEquivalencia");
        
        double[] beliefsC;
        beliefsC = inferer.getBeliefs(ConhecimentoEquivalencia);
        rede.put(ConhecimentoEquivalencia.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoDuplaNegacao);
        rede.put(ConhecimentoDuplaNegacao.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoAdicao);
        rede.put(ConhecimentoAdicao.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoConjuncao);
        rede.put(ConhecimentoConjuncao.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoSilogismoHipotetico);
        rede.put(ConhecimentoSilogismoHipotetico.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoProvaCondicional);
        rede.put(ConhecimentoProvaCondicional.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoModusPonens);
        rede.put(ConhecimentoModusPonens.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoInconsistencia);
        rede.put(ConhecimentoInconsistencia.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoModusTollens);
        rede.put(ConhecimentoModusTollens.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoDilemaConstrutivo);
        rede.put(ConhecimentoDilemaConstrutivo.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoSimplificacao);
        rede.put(ConhecimentoSimplificacao.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoSilogismoDisjuntivo);
        rede.put(ConhecimentoSilogismoDisjuntivo.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoReducaoAbsurdo);
        rede.put(ConhecimentoReducaoAbsurdo.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoExportacao);
        rede.put(ConhecimentoExportacao.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoEliminacaoDisjuncao);
        rede.put(ConhecimentoEliminacaoDisjuncao.getName(), String.valueOf(beliefsC[0]));
        beliefsC = inferer.getBeliefs(ConhecimentoEliminacaoEquivalencia);
        rede.put(ConhecimentoEliminacaoEquivalencia.getName(), String.valueOf(beliefsC[0]));
        
        return rede;
    }
    
    public BayesNode CriarRegra(String regra, BayesNet net){
        
        //Criando as variaveis observaveis
        BayesNode Corretude = net.createNode("Corretude"+regra);
        Corretude.addOutcomes("1", "0");
        Corretude.setProbabilities(0.8, 0.2);
        
        BayesNode Exemplo = net.createNode("Exemplo"+regra);
        Exemplo.addOutcomes("1", "0");
        Exemplo.setProbabilities(0.5, 0.5);

        BayesNode Conceito = net.createNode("Conceito"+regra);
        Conceito.addOutcomes("1", "0");
        Conceito.setProbabilities(0.5, 0.5);
        
        BayesNode Ajuda = net.createNode("Ajuda"+regra);
        Ajuda.addOutcomes("1", "0");
        Ajuda.setProbabilities(0.7, 0.3);
        
        BayesNode Conhecimento = net.createNode("Conhecimento"+regra);
        Conhecimento.addOutcomes("Conhece", "Desconhece");
        Conhecimento.setParents(Arrays.asList(Corretude, Exemplo, Conceito, Ajuda));
        Conhecimento.setProbabilities(
            0.7, 0.3,    //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Sim
            0.95, 0.05,  //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Nao
            0.6, 0.4,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Sim
            0.9, 0.1,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Nao
            0.6, 0.4,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Sim
            0.9, 0.1,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Nao
            0.5, 0.5,    //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Sim
            0.95, 0.05,  //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Nao

            0.1, 0.9,    //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Sim
            0.2, 0.8,    //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Nao
            0.1, 0.9,    //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Sim
            0.15, 0.85,  //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Sim
            0.1, 0.9,    //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Sim
            0.2, 0.8,    //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Sim
            0.1, 0.9,    //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Sim
            0.05, 0.95   //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Sim
        );
        
        return Conhecimento;
    }
    
    public BayesNode CriarRegra(String regra, BayesNet net, BayesNode Regra){
        
        //Criando as variaveis observaveis
        BayesNode Corretude = net.createNode("Corretude"+regra);
        Corretude.addOutcomes("1", "0");
        Corretude.setProbabilities(0.8, 0.2);
        
        BayesNode Exemplo = net.createNode("Exemplo"+regra);
        Exemplo.addOutcomes("1", "0");
        Exemplo.setProbabilities(0.5, 0.5);

        BayesNode Conceito = net.createNode("Conceito"+regra);
        Conceito.addOutcomes("1", "0");
        Conceito.setProbabilities(0.5, 0.5);
        
        BayesNode Ajuda = net.createNode("Ajuda"+regra);
        Ajuda.addOutcomes("1", "0");
        Ajuda.setProbabilities(0.7, 0.3);
        
        BayesNode Conhecimento = net.createNode("Conhecimento"+regra);
        Conhecimento.addOutcomes("Conhece", "Desconhece");
        Conhecimento.setParents(Arrays.asList(Corretude, Exemplo, Conceito, Ajuda, Regra));
        Conhecimento.setProbabilities(
            0.7, 0.3,    //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Sim     Regra Sim
            0.6, 0.4,    //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Sim     Regra Nao
            0.95, 0.05,  //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Nao     Regra Sim
            0.85, 0.15,  //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Nao     Regra Nao
            0.6, 0.4,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Sim     Regra Sim
            0.5, 0.5,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Sim     Regra Nao
            0.9, 0.1,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Nao     Regra Sim
            0.8, 0.2,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Nao     Regra Nao
            0.6, 0.4,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Sim     Regra Sim
            0.5, 0.5,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Sim     Regra Nao
            0.9, 0.1,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Nao     Regra Sim
            0.8, 0.2,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Nao     Regra Nao
            0.5, 0.5,    //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Sim     Regra Sim
            0.4, 0.6,    //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Sim     Regra Nao
            0.95, 0.05,  //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Nao     Regra Sim
            0.85, 0.15,  //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Nao     Regra Nao

            0.1, 0.9,    //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Sim     Regra Sim
            0.05, 0.95,  //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Sim     Regra Nao
            0.2, 0.8,    //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Nao     Regra Sim
            0.05, 0.95,  //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Nao     Regra Nao
            0.1, 0.9,    //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Sim     Regra Sim
            0.05, 0.95,  //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Sim     Regra Nao
            0.15, 0.85,  //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Nao     Regra Sim
            0.05, 0.95,  //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Nao     Regra Nao
            0.1, 0.9,    //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Sim     Regra Sim
            0.05, 0.95,  //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Sim     Regra Nao
            0.2, 0.8,    //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Nao     Regra Sim
            0.1, 0.9,    //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Nao     Regra Nao
            0.1, 0.9,    //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Sim     Regra Sim
            0.05, 0.95,  //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Sim     Regra Nao
            0.05, 0.95,  //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Nao     Regra Sim
            0.05, 0.95   //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Nao     Regra Nao
        );
        
        return Conhecimento;
    }
    
    public BayesNode CriarRegra(String regra, BayesNet net, BayesNode Regra1, BayesNode Regra2){
        
        //Criando as variaveis observaveis
        BayesNode Corretude = net.createNode("Corretude"+regra);
        Corretude.addOutcomes("1", "0");
        Corretude.setProbabilities(0.8, 0.2);
        
        BayesNode Exemplo = net.createNode("Exemplo"+regra);
        Exemplo.addOutcomes("1", "0");
        Exemplo.setProbabilities(0.5, 0.5);

        BayesNode Conceito = net.createNode("Conceito"+regra);
        Conceito.addOutcomes("1", "0");
        Conceito.setProbabilities(0.5, 0.5);
        
        BayesNode Ajuda = net.createNode("Ajuda"+regra);
        Ajuda.addOutcomes("1", "0");
        Ajuda.setProbabilities(0.7, 0.3);
        
        BayesNode Conhecimento = net.createNode("Conhecimento"+regra);
        Conhecimento.addOutcomes("Conhece", "Desconhece");
        Conhecimento.setParents(Arrays.asList(Corretude, Exemplo, Conceito, Ajuda, Regra1, Regra2));
        Conhecimento.setProbabilities(
            0.8, 0.2,    //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Sim     Regra1 Sim     Regra2 Sim
            0.7, 0.3,    //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Sim     Regra1 Sim     Regra2 Nao
            0.6, 0.4,    //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Sim     Regra1 Nao     Regra2 Sim
            0.5, 0.5,    //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Sim     Regra1 Nao     Regra2 Nao
            0.95, 0.05,  //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Nao     Regra1 Sim     Regra2 Sim
            0.90, 0.1,   //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Nao     Regra1 Sim     Regra2 Nao
            0.85, 0.15,  //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Nao     Regra1 Nao     Regra2 Sim
            0.8, 0.2,    //Corretude Sim    Exemplo Sim    Conceito Sim     Ajuda Nao     Regra1 Nao     Regra2 Nao
            0.7, 0.3,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Sim     Regra1 Sim     Regra2 Sim
            0.6, 0.4,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Sim     Regra1 Sim     Regra2 Nao
            0.5, 0.5,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Sim     Regra1 Nao     Regra2 Sim
            0.4, 0.6,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Sim     Regra1 Nao     Regra2 Nao
            0.95, 0.05,  //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Nao     Regra1 Sim     Regra2 Sim
            0.9, 0.1,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Nao     Regra1 Sim     Regra2 Nao
            0.8, 0.2,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Nao     Regra1 Nao     Regra2 Sim
            0.7, 0.3,    //Corretude Sim    Exemplo Sim    Conceito Nao     Ajuda Nao     Regra1 Nao     Regra2 Nao
            0.7, 0.3,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Sim     Regra1 Sim     Regra2 Sim
            0.6, 0.4,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Sim     Regra1 Sim     Regra2 Nao
            0.5, 0.5,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Sim     Regra1 Nao     Regra2 Sim
            0.4, 0.6,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Sim     Regra1 Nao     Regra2 Nao
            0.95, 0.05,  //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Nao     Regra1 Sim     Regra2 Sim
            0.9, 0.1,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Nao     Regra1 Sim     Regra2 Nao
            0.8, 0.2,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Nao     Regra1 Nao     Regra2 Sim
            0.7, 0.3,    //Corretude Sim    Exemplo Nao    Conceito Sim     Ajuda Nao     Regra1 Nao     Regra2 Nao
            0.6, 0.4,    //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Sim     Regra1 Sim     Regra2 Sim
            0.5, 0.5,    //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Sim     Regra1 Sim     Regra2 Nao
            0.4, 0.6,    //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Sim     Regra1 Nao     Regra2 Sim
            0.45, 0.55,  //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Sim     Regra1 Nao     Regra2 Nao
            0.95, 0.05,  //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Nao     Regra1 Sim     Regra2 Sim
            0.95, 0.05,  //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Nao     Regra1 Sim     Regra2 Nao
            0.85, 0.15,  //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Nao     Regra1 Nao     Regra2 Sim
            0.80, 0.2,   //Corretude Sim    Exemplo Nao    Conceito Nao     Ajuda Nao     Regra1 Nao     Regra2 Nao

            0.2, 0.8,    //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Sim     Regra1 Sim     Regra2 Sim
            0.1, 0.9,    //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Sim     Regra1 Sim     Regra2 Nao
            0.05, 0.95,  //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Sim     Regra1 Nao     Regra2 Sim
            0.05, 0.95,  //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Sim     Regra1 Nao     Regra2 Nao
            0.3, 0.7,    //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Nao     Regra1 Sim     Regra2 Sim
            0.2, 0.8,    //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Nao     Regra1 Sim     Regra2 Nao
            0.05, 0.95,  //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Nao     Regra1 Nao     Regra2 Sim
            0.05, 0.95,  //Corretude Não    Exemplo Sim    Conceito Sim     Ajuda Nao     Regra1 Nao     Regra2 Nao
            0.2, 0.8,    //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Sim     Regra1 Sim     Regra2 Sim
            0.1, 0.9,    //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Sim     Regra1 Sim     Regra2 Nao
            0.05, 0.95,  //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Sim     Regra1 Nao     Regra2 Sim
            0.05, 0.95,  //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Sim     Regra1 Nao     Regra2 Nao
            0.2, 0.8,    //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Nao     Regra1 Sim     Regra2 Sim
            0.15, 0.85,  //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Nao     Regra1 Sim     Regra2 Nao
            0.05, 0.95,  //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Nao     Regra1 Nao     Regra2 Sim
            0.05, 0.95,  //Corretude Não    Exemplo Sim    Conceito Nao     Ajuda Nao     Regra1 Nao     Regra2 Nao
            0.2, 0.8,    //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Sim     Regra1 Sim     Regra2 Sim
            0.1, 0.9,    //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Sim     Regra1 Sim     Regra2 Nao
            0.05, 0.95,  //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Sim     Regra1 Nao     Regra2 Sim
            0.05, 0.95,  //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Sim     Regra1 Nao     Regra2 Nao
            0.3, 0.7,    //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Nao     Regra1 Sim     Regra2 Sim
            0.2, 0.8,    //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Nao     Regra1 Sim     Regra2 Nao
            0.1, 0.9,    //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Nao     Regra1 Nao     Regra2 Sim
            0.1, 0.9,    //Corretude Não    Exemplo Nao    Conceito Sim     Ajuda Nao     Regra1 Nao     Regra2 Nao
            0.2, 0.8,    //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Sim     Regra1 Sim     Regra2 Sim
            0.1, 0.9,    //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Sim     Regra1 Sim     Regra2 Nao
            0.05, 0.95,  //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Sim     Regra1 Nao     Regra2 Sim
            0.05, 0.95,  //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Sim     Regra1 Nao     Regra2 Nao
            0.1, 0.9,    //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Nao     Regra1 Sim     Regra2 Sim
            0.05, 0.95,  //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Nao     Regra1 Sim     Regra2 Nao
            0.05, 0.95,  //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Nao     Regra1 Nao     Regra2 Sim
            0.05, 0.95   //Corretude Não    Exemplo Nao    Conceito Nao     Ajuda Nao     Regra1 Nao     Regra2 Nao
        );
        
        return Conhecimento;
    }

}
