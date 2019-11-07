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
public class HipotesePC {

    public static LinhaProva aplicarRegra(String str)
            throws LogicException {
        /* Retorno */
        LinhaProva input = new LinhaDeduzida(str, Regras.CH);
        LinhaProva ret = input;
                
        if (OperadorLogico.CONDICIONAL.equals(input.getOperadorPrincipal())) {
            StringBuilder sb = new StringBuilder(input.getLinha());
            int posicao = input.getPosicaoOperadorPrincipal();
            
            ret = new LinhaDeduzida(sb.substring(0, posicao), Regras.HPC);
            
            ret.setHipoteseResultado(input.getLinha());
        } else {
            throw new LogicException
                ("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                        + "Principal operador lógico deve ser "
                        + OperadorLogico.CONDICIONAL);
        }
        
        return ret;
    }

    
}
