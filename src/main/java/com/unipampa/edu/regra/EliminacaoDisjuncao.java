package com.unipampa.edu.regra;

import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;
import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import java.util.List;

public class EliminacaoDisjuncao {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String studentId, String Argumento) 
        throws LogicException {
        Agente a = Agente.getInstancia();
        /* Confere parâmetros */
        if (listaDeLinhas.size() != 3) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeElminacaoDisjuncao#0");
            throw new LogicException("Número de linhas inválido!");
        }

         /* Cria variáveis temporárias */
        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);
        LinhaProva linha2 = (LinhaProva) listaDeLinhas.get(1);
        LinhaProva linha3 = (LinhaProva) listaDeLinhas.get(2);
        LinhaProva linhaDisjuncao;
        LinhaProva linhaCond1, linhaCond2;
        
        /* Confere parâmetros - operador principal (1) */
        if (linha1.getOperadorPrincipal() == OperadorLogico.DISJUNCAO) {
            linhaDisjuncao = linha1;            
            linhaCond1 = linha2;
            linhaCond2 = linha3;
            if(linha2.getOperadorPrincipal() != OperadorLogico.CONDICIONAL
                    || linha3.getOperadorPrincipal() != OperadorLogico.CONDICIONAL) {
                a.SendMessage(studentId + "#" + Argumento + "#CorretudeElminacaoDisjuncao#0");
                throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");
            }
        } else if (linha2.getOperadorPrincipal() == OperadorLogico.DISJUNCAO) {
            linhaDisjuncao = linha2;
            linhaCond1 = linha1;
            linhaCond2 = linha3;
            if(linha1.getOperadorPrincipal() != OperadorLogico.CONDICIONAL
                    || linha3.getOperadorPrincipal() != OperadorLogico.CONDICIONAL) {
                a.SendMessage(studentId + "#" + Argumento + "#CorretudeElminacaoDisjuncao#0");
                throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");
            }
        } else if (linha3.getOperadorPrincipal() == OperadorLogico.DISJUNCAO) {
            linhaDisjuncao = linha3;
            linhaCond1 = linha1;
            linhaCond2 = linha2;
            if(linha1.getOperadorPrincipal() != OperadorLogico.CONDICIONAL
                    || linha2.getOperadorPrincipal() != OperadorLogico.CONDICIONAL) {
                a.SendMessage(studentId + "#" + Argumento + "#CorretudeElminacaoDisjuncao#0");
                throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");
            }
        } else {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeElminacaoDisjuncao#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "operador principal inválido");
        }
        
        /* Posição dos operadores principais */
        int posicao1 = linhaCond1.getPosicaoOperadorPrincipal();
        int posicao2 = linhaCond2.getPosicaoOperadorPrincipal();
        int posdisjunc = linhaDisjuncao.getPosicaoOperadorPrincipal();
        
        /* Subargumentos para tratar */
        StringBuilder sblinha = new StringBuilder(linhaCond1.getLinha());
        String cond1pre = sblinha.substring(0, posicao1);
        String cond1pos = sblinha.substring(posicao1 + OperadorLogico.CONDICIONAL.getLength());
        sblinha = new StringBuilder(linhaCond2.getLinha());
        String cond2pre = sblinha.substring(0, posicao2);
        String cond2pos = sblinha.substring(posicao2 + OperadorLogico.CONDICIONAL.getLength());
        sblinha = new StringBuilder(linhaDisjuncao.getLinha());
        String disj1 = sblinha.substring(0, posdisjunc);
        String disj2 = sblinha.substring(posdisjunc + OperadorLogico.DISJUNCAO.getLength());
        
        /* Remove parênteses */
        cond1pre = FuncoesString.removerParentesesReduntantes(cond1pre);
        cond2pre = FuncoesString.removerParentesesReduntantes(cond2pre);
        disj1 = FuncoesString.removerParentesesReduntantes(disj1);
        disj2 = FuncoesString.removerParentesesReduntantes(disj2);
        
        /* Confere parâmetros (2)
         P->Q, R->Q, PvR - se P == P e R == R */
        if (!((disj1.equals(cond1pre) && disj2.equals(cond2pre))
                || (disj1.equals(cond2pre) && disj2.equals(cond1pre)))) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeElminacaoDisjuncao#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra");
        }        
        /* P->Q, R->Q, PvR - se Q == Q */
        if (!(cond1pos.equals(cond2pos))) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeElminacaoDisjuncao#0");
            throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                    + "termos após condicional devem ser iguais");
        }
        
        /* Retorno */
        System.out.println(a.toString());
        System.out.println(studentId);
        System.out.println(Argumento);
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeEliminacaoDisjuncao#1");
        
        return new LinhaDeduzida(cond1pos, Regras.MDJ);
    }
    
}
