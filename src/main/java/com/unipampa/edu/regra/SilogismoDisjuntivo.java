/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.regra;

import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;

import java.util.List;

/**
 * REGRA POR ACEITAR Pv~Q, Q
 * @author gia
 */
public class SilogismoDisjuntivo {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String studentId, String Argumento)
            throws LogicException {
        
        Agente a = Agente.getInstancia();
        
        /* Confere parâmetros */
        if (listaDeLinhas.size() != 2) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeSilogismoDisjuntivo#0");
            throw new LogicException("Número de linhas inválido!");
        }

        /* Cria variáveis temporárias */
        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);
        LinhaProva linha2 = (LinhaProva) listaDeLinhas.get(1);
        LinhaProva linhaDisjuncao = null;
        LinhaProva linhaNegacao = null;
        LinhaProva linhaDisjuncaoNegada = null;
        LinhaProva linhaAfirmacao = null;

        /* Confere parâmetros */
        if (linha1.getOperadorPrincipal() == OperadorLogico.DISJUNCAO
                && linha2.getOperadorPrincipal() == OperadorLogico.NEGACAO) {
            linhaDisjuncao = linha1;
            linhaNegacao = linha2;
        } else if (linha2.getOperadorPrincipal() == OperadorLogico.DISJUNCAO
                && linha1.getOperadorPrincipal() == OperadorLogico.NEGACAO) {
            linhaDisjuncao = linha2;
            linhaNegacao = linha1;
        } else if (linha1.getOperadorPrincipal() == OperadorLogico.DISJUNCAO
                && linha2.getOperadorPrincipal() == null) {
            linhaDisjuncaoNegada = linha1;
            linhaAfirmacao = linha2;
        } else if (linha2.getOperadorPrincipal() == OperadorLogico.DISJUNCAO
                && linha1.getOperadorPrincipal() == null) {
            linhaDisjuncaoNegada = linha2;
            linhaAfirmacao = linha1;
        } else {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeSilogismoDisjuntivo#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");
        }

        String strret = null;

        if (linhaDisjuncao!=null) {
            /* Posição dos operadores principais */
            int posicaodisjuncao = linhaDisjuncao.getPosicaoOperadorPrincipal();
            int posicaonegacao = linhaNegacao.getPosicaoOperadorPrincipal();

            /* Subargumentos para tratar */
            StringBuilder sblinha = new StringBuilder(linhaDisjuncao.getLinha());
            String linhadisjuncaopre = sblinha.substring(0, posicaodisjuncao);
            String linhadisjuncaopos = sblinha.substring(posicaodisjuncao
                    + OperadorLogico.DISJUNCAO.getLength());
            sblinha = new StringBuilder(linhaNegacao.getLinha());
            String linhanegacaoremov = sblinha.substring(posicaonegacao
                    + OperadorLogico.NEGACAO.getLength());
            linhanegacaoremov = FuncoesString.removerParentesesReduntantes(linhanegacaoremov);
            
            /* Remove parênteses */
            linhadisjuncaopre = FuncoesString.removerParentesesReduntantes(
                    linhadisjuncaopre);
            linhadisjuncaopos = FuncoesString.removerParentesesReduntantes(
                    linhadisjuncaopos);
        
            /* Escolhe qual das duas premissas da disjunção é a saída */
            if (!linhanegacaoremov.equals(linhadisjuncaopre)) {
                if (!linhanegacaoremov.equals(linhadisjuncaopos)) {
                    a.SendMessage(studentId + "#" + Argumento + "#CorretudeSilogismoDisjuntivo#0");
                    throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra");
                } else {
                    strret = linhadisjuncaopre;
                }
            } else {
                strret = linhadisjuncaopos;
            }
            
            
        } else {
            
            /* Posição dos operadores principais */
            int posicaodisjuncaoneg = linhaDisjuncaoNegada.getPosicaoOperadorPrincipal();
            int posicaoafirmacao = 0;
            /* Subargumentos para tratar */
            StringBuilder sblinha = new StringBuilder(linhaDisjuncaoNegada.getLinha());
            String linhadisjuncaonegpre = sblinha.substring(0, posicaodisjuncaoneg);
            String linhadisjuncaonegpos = sblinha.substring(posicaodisjuncaoneg
                    + OperadorLogico.DISJUNCAO.getLength());
            sblinha = new StringBuilder(linhaAfirmacao.getLinha());
            String linhaafirmacaoneg = OperadorLogico.NEGACAO.toString()
                    +  sblinha.substring(posicaoafirmacao);
            linhaafirmacaoneg = FuncoesString.removerParentesesReduntantes(linhaafirmacaoneg);
            
            /* Remove parênteses */
            linhadisjuncaonegpre = FuncoesString.removerParentesesReduntantes(
                    linhadisjuncaonegpre);
            linhadisjuncaonegpos = FuncoesString.removerParentesesReduntantes(
                    linhadisjuncaonegpos);
        
            /* Escolhe qual das duas premissas da disjunção é a saída */
            if (!linhaafirmacaoneg.equals(linhadisjuncaonegpre)) {
                if (!linhaafirmacaoneg.equals(linhadisjuncaonegpos)) {
                    a.SendMessage(studentId + "#" + Argumento + "#CorretudeSilogismoDisjuntivo#0");
                    throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra");
                } else {
                    strret = linhadisjuncaonegpre;
                }
            } else {
                strret = linhadisjuncaonegpos;
            }
            
            
        }
        /* Retorno */
        LinhaProva novalinha = new LinhaDeduzida(strret, Regras.SD);
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeSilogismoDisjuntivo#1");
        return novalinha;
    }

}
