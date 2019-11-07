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
 *
 * @author gia
 */
public class ModusTollens {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String studentId, String Argumento) 
            throws LogicException {
        Agente a = Agente.getInstancia();
        /* Confere parâmetros */
        if(listaDeLinhas.size() != 2) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeModusTollens#0");
            throw new LogicException("Número de linhas inválido!");
        }
        
        /* Cria variáveis temporárias */
        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);
        LinhaProva linha2 = (LinhaProva) listaDeLinhas.get(1);
        LinhaProva linhaCondicional;
        LinhaProva linhaNegacao;
        
        /* Confere parâmetros */
        if(linha1.getOperadorPrincipal() == OperadorLogico.CONDICIONAL &&
                linha2.getOperadorPrincipal() == OperadorLogico.NEGACAO) {
            linhaCondicional = linha1;
            linhaNegacao = linha2;
        }        
        else if(linha2.getOperadorPrincipal() == OperadorLogico.CONDICIONAL &&
                linha1.getOperadorPrincipal() == OperadorLogico.NEGACAO) {
            linhaCondicional = linha2;
            linhaNegacao = linha1;
        }
        else {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeModusTollens#0");
            throw new LogicException
                ("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                        + "operador principal inválido");
        }
        
        /* Posição dos operadores principais */        
        int posicaocondicional = linhaCondicional.getPosicaoOperadorPrincipal();
        int posicaonegacao = linhaNegacao.getPosicaoOperadorPrincipal();
        
        /* Subargumentos para tratar */
        StringBuilder sblinha = new StringBuilder(linhaCondicional.getLinha());
        String linhacondicionalpre = sblinha.substring(0, posicaocondicional);
        String linhacondicionalpos = sblinha.substring(posicaocondicional + 
                OperadorLogico.CONDICIONAL.getLength());
        sblinha = new StringBuilder(linhaNegacao.getLinha());
        String linhanegacaoremov = sblinha.substring(posicaonegacao + 
                OperadorLogico.NEGACAO.getLength());
        linhanegacaoremov = FuncoesString.removerParentesesReduntantes(linhanegacaoremov);
        
        /* Remove parênteses */
        linhacondicionalpre = FuncoesString.removerParentesesReduntantes(
                linhacondicionalpre);
        linhacondicionalpos = FuncoesString.removerParentesesReduntantes(
                linhacondicionalpos);
        
        if(!linhanegacaoremov.equals(linhacondicionalpos)) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeModusTollens#0");
            throw new LogicException
                ("Formato inválido das fórmula(s) selecionada(s) para a regra");
        }
        
        /* Retorno */        
        linhacondicionalpre = FuncoesString.adicionarParenteses(linhacondicionalpre);
        sblinha = new StringBuilder(OperadorLogico.NEGACAO.getExpressao());
        sblinha.append(linhacondicionalpre);
        LinhaProva novalinha = new LinhaDeduzida(sblinha.toString(), Regras.MT);
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeModusTollens#1");
        return novalinha;
    }
    
}
