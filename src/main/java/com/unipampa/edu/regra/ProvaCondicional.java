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
public class ProvaCondicional {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String studentId, String Argumento) 
            throws LogicException {
        /* Confere parâmetros */
        Agente a = Agente.getInstancia();
        if (listaDeLinhas.size() != 2) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeProvaCondicional#0");
            throw new LogicException("Número de linhas inválido!");
        }

        /* Cria variáveis temporárias */
        LinhaProva linhaPre = (LinhaProva) listaDeLinhas.get(0);
        LinhaProva linhaPos = (LinhaProva) listaDeLinhas.get(1);
        String strPre = linhaPre.getLinha();
        String strPos = linhaPos.getLinha();
        
        strPre = FuncoesString.adicionarParenteses(strPre);
        strPos = FuncoesString.adicionarParenteses(strPos);
        
        StringBuilder sblinha = new StringBuilder(strPre);
        sblinha.append(OperadorLogico.CONDICIONAL);
        sblinha.append(strPos);        
        
        /* Retorno */
        LinhaProva novalinha = new LinhaDeduzida(sblinha.toString(), Regras.PC);
        
        if(!linhaPre.getRegra().equals(Regras.HPC)) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeProvaCondicional#0");
            throw new LogicException("Hipotese não é para PC!");
        }
        if(!linhaPre.getHipoteseResultado().equals(novalinha.getLinha())) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeProvaCondicional#0");
            throw new LogicException("Última linha não é a esperada!");
        }
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeProvaCondicional#1");
        return novalinha;
    }
    
}
