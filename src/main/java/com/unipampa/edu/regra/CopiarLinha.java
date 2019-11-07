package com.unipampa.edu.regra;

import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;
import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;

import java.util.List;

/**
 * @author Rafael Koch Peres
 */
public class CopiarLinha {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas)
            throws LogicException {
        if (listaDeLinhas.size() != 1) {
            throw new LogicException("Número de linhas inválido!");
        }

        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);

        String strret = FuncoesString.removerParentesesReduntantes(linha1.getLinha());
                
        /* Retorno */
        return new LinhaDeduzida(strret, Regras.CL);
    }
}
