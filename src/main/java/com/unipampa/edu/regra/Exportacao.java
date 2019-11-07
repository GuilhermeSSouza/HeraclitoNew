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
import com.unipampa.edu.heraclito.LinhaHipotese;
import com.unipampa.edu.heraclito.LinhaProva;

import java.util.List;

/**
 *
 * @author gia
 */
public class Exportacao {

    // (P^Q)->R então P->(Q->R)
    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String studentId, String Argumento)
            throws LogicException {
        Agente a = Agente.getInstancia();
        if (listaDeLinhas.size() != 1) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeExportacao#0");
            throw new LogicException("Número de linhas inválido!");
        }

        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);

        if (linha1.getOperadorPrincipal() != OperadorLogico.CONDICIONAL) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeExportacao#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");

        }

        int posicao1 = linha1.getPosicaoOperadorPrincipal();
        
        StringBuilder sblinha = new StringBuilder(linha1.getLinha());
        String cond1pre = sblinha.substring(0, posicao1);
        String cond1pos = sblinha.substring(posicao1 + 2);
        
        cond1pos = FuncoesString.adicionarParenteses(cond1pos); // R
        
        LinhaProva temp = new LinhaHipotese(cond1pre);
        if (temp.getOperadorPrincipal() != OperadorLogico.CONJUNCAO) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeExportacao#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador da expressão secundária inválido");

        }
        
        int posicao2 = temp.getPosicaoOperadorPrincipal();
        
        sblinha = new StringBuilder(temp.getLinha());
        String conju1pre = sblinha.substring(0, posicao2);
        String conju1pos = sblinha.substring(posicao2 + 1);
        
        conju1pre = FuncoesString.adicionarParenteses(conju1pre); // P
        conju1pos = FuncoesString.adicionarParenteses(conju1pos); // Q;                
        
        String retorno = (conju1pre + OperadorLogico.CONDICIONAL
                + FuncoesString.adicionarParenteses(conju1pos + 
                        OperadorLogico.CONDICIONAL + cond1pos));

        LinhaProva novalinha = new LinhaDeduzida(retorno, Regras.EXP);
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeExportacao#1");
        return novalinha;
    }

}
