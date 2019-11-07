/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unipampa.edu.regra;

import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;
import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.Lado;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import java.util.List;

/**
 *
 * @author gia
 */
public class Simplificacao {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, Lado lado, String studentId, String Argumento) throws LogicException {
        
        Agente a = Agente.getInstancia();
        /* Confere parâmetros */
        if(listaDeLinhas.size() != 1) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeSimplificacao#0");
            throw new LogicException("Número de linhas inválido!");
        }
        
        /* Cria variáveis temporárias */
        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);                
        StringBuilder sblinha = new StringBuilder(
                FuncoesString.removerParentesesReduntantes(linha1.getLinha()));

         /* Confere parâmetros */
        if (linha1.getOperadorPrincipal() != OperadorLogico.CONJUNCAO) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeSimplificacao#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");
        }
        
        /* Posição dos operadores principais */        
        int posicao1 = linha1.getPosicaoOperadorPrincipal();       
        
        String linha1pre = sblinha.substring(0, posicao1);
        String linha1pos = sblinha.substring(posicao1
                + OperadorLogico.CONJUNCAO.getLength());
        
        linha1pre = FuncoesString.removerParentesesReduntantes(linha1pre);
        linha1pos = FuncoesString.removerParentesesReduntantes(linha1pos);
        
        /* Prepara string de saída */        
        
        String strret;
        
        if(lado.equals(Lado.ESQUERDA)) {
            strret = linha1pre;
        }
        else {
            strret = linha1pos;
        }
        
        /* Retorno */
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeSimplificacao#1");
        return new LinhaDeduzida(strret, Regras.SP);
    }
    
}
