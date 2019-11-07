package com.unipampa.edu.regra;

import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.Lado;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;

import java.util.List;

public class EliminacaoEquivalencia {

    // (P<->Q) então (P->Q)^(Q->P)
    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, Lado lado, String studentId, String Argumento)
            throws LogicException {
        Agente a = Agente.getInstancia();
        /* Confere parâmetros */
        if (listaDeLinhas.size() != 1) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeElminacaoEquivalencia#0");
            throw new LogicException("Número de linhas inválido!");
        }
        
        /* Cria variáveis temporárias */
        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);

        /* Confere parâmetros - operador principal (1) */
        if (linha1.getOperadorPrincipal() != OperadorLogico.BICONDICIONAL) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeElminacaoEquivalencia#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");

        }

        /* Posição dos operadores principais */
        int posicao1 = linha1.getPosicaoOperadorPrincipal();
        
        /* Subargumentos para tratar */
        StringBuilder sblinha = new StringBuilder(linha1.getLinha());
        String bicond1pre = sblinha.substring(0, posicao1);
        String bicond1pos = sblinha.substring(posicao1 +
                OperadorLogico.BICONDICIONAL.getLength());

        /* Adiciona parênteses */
        bicond1pre = FuncoesString.adicionarParenteses(bicond1pre); // (P)
        bicond1pos = FuncoesString.adicionarParenteses(bicond1pos); // (R)
        
        /* Prepara string de saída */ 
        String condicional1 = bicond1pre +
                OperadorLogico.CONDICIONAL + bicond1pos;
        String condicional2 = bicond1pos +
                OperadorLogico.CONDICIONAL + bicond1pre;
        condicional1 = FuncoesString.adicionarParenteses(condicional1);
        condicional2 = FuncoesString.adicionarParenteses(condicional2);
        
        String retorno;
        
        if(lado.equals(Lado.ESQUERDA)) {
            retorno = condicional1;
        }
        else {
            retorno = condicional2;
        }
        
        /* Retorno */
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeEliminacaoEquivalencia#1");
        return new LinhaDeduzida(retorno, Regras.MEQ);
    }
    
}
