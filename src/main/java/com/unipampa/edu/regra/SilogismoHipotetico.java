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
public class SilogismoHipotetico {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String studentId, String Argumento)
            throws LogicException {
        Agente a = Agente.getInstancia();
        // A->B, B->C então A->C
        /* Confere parâmetros */
        if (listaDeLinhas.size() != 2) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeSilogismoHipotetico#0");
            throw new LogicException("Número de linhas inválido!");
        }

        /* Cria variáveis temporárias */
        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);
        LinhaProva linha2 = (LinhaProva) listaDeLinhas.get(1);

        /* Confere parâmetros */
        if (linha1.getOperadorPrincipal() != OperadorLogico.CONDICIONAL
                && linha2.getOperadorPrincipal() != OperadorLogico.CONDICIONAL) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeSilogismoHipotetico#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");
        }

        /* Posição dos operadores principais */
        int posicao1 = linha1.getPosicaoOperadorPrincipal();
        int posicao2 = linha2.getPosicaoOperadorPrincipal();

        /* Subargumentos para tratar */
        StringBuilder sblinha = new StringBuilder(linha1.getLinha());
        String linha1pre = sblinha.substring(0, posicao1);
        String linha1pos = sblinha.substring(posicao1
                + OperadorLogico.CONDICIONAL.getLength());
        sblinha = new StringBuilder(linha2.getLinha());
        String linha2pre = sblinha.substring(0, posicao2);
        String linha2pos = sblinha.substring(posicao2
                + OperadorLogico.CONDICIONAL.getLength());

        /* Remove parênteses */
        linha1pre = FuncoesString.removerParentesesReduntantes(linha1pre);
        linha1pos = FuncoesString.removerParentesesReduntantes(linha1pos);
        linha2pre = FuncoesString.removerParentesesReduntantes(linha2pre);
        linha2pos = FuncoesString.removerParentesesReduntantes(linha2pos);

        /* Confere parâmetros */
        sblinha = null;
        if (!linha1pos.equals(linha2pre)) {
            if (!linha1pre.equals(linha2pos)) {
                a.SendMessage(studentId + "#" + Argumento + "#CorretudeSilogismoHipotetico#0");
                throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra");
            } else {
                linha1pos = FuncoesString.adicionarParenteses(linha1pos);
                linha2pre = FuncoesString.adicionarParenteses(linha2pre);

                sblinha = new StringBuilder(linha2pre);
                sblinha.append(OperadorLogico.CONDICIONAL);
                sblinha.append(linha1pos);
            }
        } else {
            linha1pre = FuncoesString.adicionarParenteses(linha1pre);
            linha2pos = FuncoesString.adicionarParenteses(linha2pos);

            sblinha = new StringBuilder(linha1pre);
            sblinha.append(OperadorLogico.CONDICIONAL);
            sblinha.append(linha2pos);
        }

        /* Retorno */
        LinhaProva novalinha = new LinhaDeduzida(sblinha.toString(), Regras.SH);
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeSilogismoHipotetico#1");
        return novalinha;
    }

}
