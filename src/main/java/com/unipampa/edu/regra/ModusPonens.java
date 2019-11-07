package com.unipampa.edu.regra;

import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;

import java.util.List;

public class ModusPonens {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String studentId, String Argumento)
            throws LogicException {
        Agente a = Agente.getInstancia();
        /* Confere parâmetros */
        if (listaDeLinhas.size() != 2) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeModusPonens#0");
            throw new LogicException("Número de linhas inválido!");
        }

        /* Cria variáveis temporárias */
        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);
        LinhaProva linha2 = (LinhaProva) listaDeLinhas.get(1);
        String linhaRetorno;

        /* Confere parâmetros */
        try {
            if (OperadorLogico.CONDICIONAL.equals(linha1.getOperadorPrincipal())) {
                int posicao = linha1.getPosicaoOperadorPrincipal();
                StringBuilder sblinha = new StringBuilder(linha1.getLinha());
                String linhaComparacao = sblinha.substring(0, posicao);
                linhaComparacao = FuncoesString.removerParentesesReduntantes(linhaComparacao);

                if (!linhaComparacao.equals(linha2.getLinha())) {
                    a.SendMessage(studentId + "#" + Argumento + "#CorretudeModusPonens#0");
                    throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                            + "hipótese inválida");
                }

                linhaRetorno = sblinha.substring(posicao
                        + OperadorLogico.CONDICIONAL.getLength());
            } else {
                a.SendMessage(studentId + "#" + Argumento + "#CorretudeModusPonens#0");
                throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                        + "operador principal inválido");
            }
        } catch (LogicException e) {
            if (OperadorLogico.CONDICIONAL.equals(linha2.getOperadorPrincipal())) {
                int posicao = linha2.getPosicaoOperadorPrincipal();
                StringBuilder sblinha = new StringBuilder(linha2.getLinha());
                String linhaComparacao = sblinha.substring(0, posicao);
                linhaComparacao = FuncoesString.removerParentesesReduntantes(linhaComparacao);

                if (!linhaComparacao.equals(linha1.getLinha())) {
                    a.SendMessage(studentId + "#" + Argumento + "#CorretudeModusPonens#0");
                    throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                            + "hipótese inválida");
                }

                linhaRetorno = sblinha.substring(posicao
                        + OperadorLogico.CONDICIONAL.getLength());
            } else {
                a.SendMessage(studentId + "#" + Argumento + "#CorretudeModusPonens#0");
                throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                        + "operador principal inválido");
            }
        }

        /* Retorno */
        LinhaProva novalinha = new LinhaDeduzida(linhaRetorno, Regras.MP);
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeModusPonens#1");
        return novalinha;
    }
}
