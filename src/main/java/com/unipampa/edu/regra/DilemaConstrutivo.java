package com.unipampa.edu.regra;

import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;
import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import java.util.List;

public class DilemaConstrutivo {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String studentId, String Argumento)
            throws LogicException {
        Agente a = Agente.getInstancia();
        /* Confere parâmetros */
        if (listaDeLinhas.size() != 3) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeDilemaConstrutivo#0");
            throw new LogicException("Número de linhas inválido!");
        }
        
        /* Cria variáveis temporárias */
        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);
        LinhaProva linha2 = (LinhaProva) listaDeLinhas.get(1);
        LinhaProva linha3 = (LinhaProva) listaDeLinhas.get(2);
        LinhaProva linhaDisjuncao;
        LinhaProva linhaCondicional1, linhaCondinicional2;

        /* Confere parâmetros - operador principal (1)
         se op principal são 2*(->) e 1*(^)*/
        if (linha1.getOperadorPrincipal() == OperadorLogico.DISJUNCAO) {
            linhaDisjuncao = linha1;            
            linhaCondicional1 = linha2;
            linhaCondinicional2 = linha3;
            if(linha2.getOperadorPrincipal() != OperadorLogico.CONDICIONAL
                    || linha3.getOperadorPrincipal() != OperadorLogico.CONDICIONAL) {
                a.SendMessage(studentId + "#" + Argumento + "#CorretudeDilemaConstrutivo#0");
                throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");
            }
        } else if (linha2.getOperadorPrincipal() == OperadorLogico.DISJUNCAO) {
            linhaDisjuncao = linha2;
            linhaCondicional1 = linha1;
            linhaCondinicional2 = linha3;
            if(linha1.getOperadorPrincipal() != OperadorLogico.CONDICIONAL
                    || linha3.getOperadorPrincipal() != OperadorLogico.CONDICIONAL) {
                a.SendMessage(studentId + "#" + Argumento + "#CorretudeDilemaConstrutivo#0");
                throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");
            }
        } else if (linha3.getOperadorPrincipal() == OperadorLogico.DISJUNCAO) {
            linhaDisjuncao = linha3;
            linhaCondicional1 = linha1;
            linhaCondinicional2 = linha2;
            if(linha1.getOperadorPrincipal() != OperadorLogico.CONDICIONAL
                    || linha2.getOperadorPrincipal() != OperadorLogico.CONDICIONAL) {
                a.SendMessage(studentId + "#" + Argumento + "#CorretudeDilemaConstrutivo#0");
                throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");
            }
        } else {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeDilemaConstrutivo#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");
        }

        /* Posição dos operadores principais */
        int posicao1 = linhaCondicional1.getPosicaoOperadorPrincipal();
        int posicao2 = linhaCondinicional2.getPosicaoOperadorPrincipal();
        int posiscaodisjuncao = linhaDisjuncao.getPosicaoOperadorPrincipal();
        
        /* Subargumentos para tratar */
        StringBuilder sblinha = new StringBuilder(linhaCondicional1.getLinha());
        String condicional1pre = sblinha.substring(0, posicao1);
        String condicional1pos = sblinha.substring(posicao1 + 
                OperadorLogico.CONDICIONAL.getLength());
        sblinha = new StringBuilder(linhaCondinicional2.getLinha());
        String condicional2pre = sblinha.substring(0, posicao2);
        String condicional2pos = sblinha.substring(posicao2 + 
                OperadorLogico.CONDICIONAL.getLength());
        sblinha = new StringBuilder(linhaDisjuncao.getLinha());
        String disjuncaopre = sblinha.substring(0, posiscaodisjuncao);
        String disjuncaopos = sblinha.substring(posiscaodisjuncao + 
                OperadorLogico.DISJUNCAO.getLength());
        
        /* Remove parênteses */
        condicional1pre = FuncoesString.removerParentesesReduntantes(condicional1pre);
        condicional2pre = FuncoesString.removerParentesesReduntantes(condicional2pre);
        disjuncaopre = FuncoesString.removerParentesesReduntantes(disjuncaopre);
        disjuncaopos = FuncoesString.removerParentesesReduntantes(disjuncaopos);
        
        /* Confere parâmetros - operador principal (2)
         P->Q, R->S, PvR - se P == P e R == R */
        if (!((disjuncaopre.equals(condicional1pre) && disjuncaopos.equals(condicional2pre))
                || (disjuncaopre.equals(condicional2pre) && disjuncaopos.equals(condicional1pre)))) {
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra");
        }
        
        /* Prepara string de saída */  
        condicional1pos = FuncoesString.adicionarParenteses(condicional1pos);
        condicional2pos = FuncoesString.adicionarParenteses(condicional2pos);
        condicional1pos = condicional1pos.concat(OperadorLogico.DISJUNCAO + condicional2pos);
        
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeDilemaConstrutivo#1");
        /* Retorno */
        return new LinhaDeduzida(condicional1pos, Regras.DC);
    }

}
