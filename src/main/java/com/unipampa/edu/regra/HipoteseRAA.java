/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unipampa.edu.regra;

import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;


/**
 *
 * @author jcgluz
 */
public class HipoteseRAA {

    public static LinhaProva aplicarRegra(String str)
            throws LogicException {
        /* Retorno */
        LinhaProva input = new LinhaDeduzida(str, Regras.CH);
        LinhaProva ret = input;
                
        if (OperadorLogico.NEGACAO.equals(input.getOperadorPrincipal())) {
            StringBuilder sb = new StringBuilder(input.getLinha());
            int posicao = input.getPosicaoOperadorPrincipal();
            sb.delete(posicao, posicao + 1 * OperadorLogico.NEGACAO.getLength());

            ret = new LinhaDeduzida(sb.toString(), Regras.HRAA);
            
            ret.setHipoteseResultado(input.getLinha());
        } else {
            throw new LogicException
                ("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                        + "Principal operador lógico deve ser "
                        + OperadorLogico.NEGACAO);
        }
        
        return ret;
    }

    
}
