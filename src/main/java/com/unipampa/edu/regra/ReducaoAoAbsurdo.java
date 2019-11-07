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
public class ReducaoAoAbsurdo {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String studentId, String Argumento)
            throws LogicException {
        Agente a = Agente.getInstancia();
        /* Confere parâmetros */
        if (listaDeLinhas.size() != 2) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeReducaoAbsurdo#0");
            throw new LogicException("Número de linhas inválido!");
        }

        /* Cria variáveis temporárias */
        LinhaProva linhaRet = (LinhaProva) listaDeLinhas.get(0);
        LinhaProva linhaAbs = (LinhaProva) listaDeLinhas.get(1);

        /* Confere parâmetros */
        if (linhaAbs.getOperadorPrincipal() != OperadorLogico.CONJUNCAO) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeReducaoAbsurdo#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal da última linha precisa ser uma conjunção");
        }

        /* Posição dos operadores principais */
        int posicaodisjuncao = linhaAbs.getPosicaoOperadorPrincipal();

        /* Subargumentos para tratar */
        StringBuilder sblinha = new StringBuilder(linhaAbs.getLinha());
        String linhapre = sblinha.substring(0, posicaodisjuncao);
        String linhapos = sblinha.substring(posicaodisjuncao
                + OperadorLogico.CONJUNCAO.getLength());

        /* Remove parênteses */
        linhapre = FuncoesString.removerParentesesReduntantes(
                linhapre);
        linhapos = FuncoesString.removerParentesesReduntantes(
                linhapos);
        LinhaProva lpre = new LinhaHipotese(linhapre);
        LinhaProva lpos = new LinhaHipotese(linhapos);

        /* Confere igualdade se linha é ~P^P ou P^~P*/
        if (lpre.getOperadorPrincipal() == OperadorLogico.NEGACAO) {
            int posicao = lpre.getPosicaoOperadorPrincipal();
            sblinha = new StringBuilder(lpre.getLinha());
            sblinha.delete(posicao, posicao + OperadorLogico.NEGACAO.getLength());
            String strl = sblinha.toString();
            strl = FuncoesString.removerParentesesReduntantes(strl);
            if (!lpos.getLinha().equals(strl)) {
                if (lpos.getOperadorPrincipal() == OperadorLogico.NEGACAO) {
                    posicao = lpos.getPosicaoOperadorPrincipal();
                    sblinha = new StringBuilder(lpos.getLinha());
                    sblinha.delete(posicao, posicao + OperadorLogico.NEGACAO.getLength());
                    strl = sblinha.toString();
                    strl = FuncoesString.removerParentesesReduntantes(strl);
                    if(!lpre.getLinha().equals(strl)) {
                    a.SendMessage(studentId + "#" + Argumento + "#CorretudeReducaoAbsurdo#0");
                        throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                            + "esta linha não define um absurdo");
                    }
                } else {
                    a.SendMessage(studentId + "#" + Argumento + "#CorretudeReducaoAbsurdo#0");
                    throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                            + "esta linha não define um absurdo");
                }
            }
        } else if (lpos.getOperadorPrincipal()==OperadorLogico.NEGACAO) {
            int posicao = lpos.getPosicaoOperadorPrincipal();
            sblinha = new StringBuilder(lpos.getLinha());
            sblinha.delete(posicao, posicao + OperadorLogico.NEGACAO.getLength());
            String strl = sblinha.toString();
            strl = FuncoesString.removerParentesesReduntantes(strl);
            if (!lpre.getLinha().equals(strl)) {
                a.SendMessage(studentId + "#" + Argumento + "#CorretudeReducaoAbsurdo#0");
                throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "esta linha não define um absurdo");
            }
        } else {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeReducaoAbsurdo#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                + "esta linha não define um absurdo");
        }    

        sblinha = new StringBuilder(OperadorLogico.NEGACAO.toString());
        String strret = linhaRet.getLinha();
        strret = FuncoesString.adicionarParenteses(strret);
        sblinha.append(strret);
        strret = sblinha.toString();
        /* Retorno */
        if(!linhaRet.getRegra().equals(Regras.HRAA)) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeReducaoAbsurdo#0");
            throw new LogicException("Hipotese não é para RAA!");
        }
        LinhaProva novalinha = new LinhaDeduzida(strret, Regras.RAA);
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeReducaoAbsurdo#1");
        return novalinha;
    }

}
