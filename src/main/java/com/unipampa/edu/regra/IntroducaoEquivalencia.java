package com.unipampa.edu.regra;

import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;

import java.util.List;

public class IntroducaoEquivalencia {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String studentId, String Argumento)
            throws LogicException {
        Agente a = Agente.getInstancia();
        // A->B, B->A então A<->B
        /* Confere parâmetros */
        if (listaDeLinhas.size() != 2) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeEquivalencia#0");
            throw new LogicException("Número de linhas inválido!");
        }

        /* Cria variáveis temporárias */
        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);
        LinhaProva linha2 = (LinhaProva) listaDeLinhas.get(1);

        /* Confere parâmetros */
        if (linha1.getOperadorPrincipal() != OperadorLogico.CONDICIONAL &&
                linha2.getOperadorPrincipal() != OperadorLogico.CONDICIONAL) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeEquivalencia#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");
        }

        /* Posição dos operadores principais */
        int posicao1 = linha1.getPosicaoOperadorPrincipal();
        int posicao2 = linha2.getPosicaoOperadorPrincipal();

        /* Subargumentos para tratar */
        StringBuilder sblinha = new StringBuilder(linha1.getLinha());
        String linha1pre = sblinha.substring(0, posicao1);
        String linha1pos= sblinha.substring(posicao1 + 
                OperadorLogico.CONDICIONAL.getLength());
        sblinha = new StringBuilder(linha2.getLinha());
        String linha2pre = sblinha.substring(0, posicao2);
        String linha2pos= sblinha.substring(posicao2 + 
                OperadorLogico.CONDICIONAL.getLength());

        /* Remove parênteses */
        linha1pre = FuncoesString.removerParentesesReduntantes(linha1pre);
        linha1pos = FuncoesString.removerParentesesReduntantes(linha1pos);
        linha2pre = FuncoesString.removerParentesesReduntantes(linha2pre);
        linha2pos = FuncoesString.removerParentesesReduntantes(linha2pos);
        
        /* Confere parâmetros */
        if(!linha1pre.equals(linha2pos) || !linha1pos.equals(linha2pre)) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeEquivalencia#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra");
        }

        linha1pre = FuncoesString.adicionarParenteses(linha1pre);
        linha1pos = FuncoesString.adicionarParenteses(linha1pos);
        String retorno = linha1pre + OperadorLogico.BICONDICIONAL + linha1pos;
        
        /* Retorno */
        LinhaProva novalinha = new LinhaDeduzida(retorno, Regras.PEQ);
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeEquivalencia#1");
        return novalinha;
    }

}
