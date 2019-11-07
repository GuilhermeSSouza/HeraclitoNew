package com.unipampa.edu.regra;

import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;
import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import java.util.List;

public class Conjuncao {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String studentId, String Argumento) 
            throws LogicException {
        /* Confere parâmetros */
        Agente a = Agente.getInstancia();
        
        if (listaDeLinhas.size() != 2) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeConjuncao#0");
            throw new LogicException("Número de linhas inválido!");
        }
        
        /* Cria variáveis temporárias */
        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);
        LinhaProva linha2 = (LinhaProva) listaDeLinhas.get(1);        
        StringBuilder sblinha1 = new StringBuilder(
                FuncoesString.adicionarParenteses(linha1.getLinha()));
        StringBuilder sblinha2 = new StringBuilder(
                FuncoesString.adicionarParenteses(linha2.getLinha()));
        
        /* Prepara string de saída */        
        sblinha1.append(OperadorLogico.CONJUNCAO);
        sblinha1.append(sblinha2);
        
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeConjuncao#1");
        /* Retorno */
        return new LinhaDeduzida(sblinha1.toString(), Regras.CJ);        
    }

}
