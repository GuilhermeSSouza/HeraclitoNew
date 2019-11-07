package com.unipampa.edu.regra;

import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;
import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
/**
 * @author Rafael Koch Peres
 */
public class CriarHipotese {

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
        } else if (OperadorLogico.CONDICIONAL.equals(input.getOperadorPrincipal())) {
            StringBuilder sb = new StringBuilder(input.getLinha());
            int posicao = input.getPosicaoOperadorPrincipal();
            
            ret = new LinhaDeduzida(sb.substring(0, posicao), Regras.HPC);
            
            ret.setHipoteseResultado(input.getLinha());
        } else {
            throw new LogicException
                ("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                        + "Operador principal deve ser "
                        + OperadorLogico.CONDICIONAL
                        + " ou "
                        + OperadorLogico.NEGACAO);
        }
        
        return ret;
    }

}
