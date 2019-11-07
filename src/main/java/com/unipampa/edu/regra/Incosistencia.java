package com.unipampa.edu.regra;

import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;

import java.util.List;

public class Incosistencia {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String str, String studentId, String Argumento)
            throws LogicException {
        Agente a = Agente.getInstancia();
        if (listaDeLinhas.size() != 2) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeInconsistencia#0");
            throw new LogicException("Número de linhas inválido!");
        }

        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);
        LinhaProva linha2 = (LinhaProva) listaDeLinhas.get(1);

        String str1 = FuncoesString.removerParentesesReduntantes(linha1.getLinha());
        String str2 = FuncoesString.removerParentesesReduntantes(linha2.getLinha());

        StringBuilder sblinha1 = new StringBuilder(str1);
        StringBuilder sblinha2 = new StringBuilder(str2);

        // Confere se linha 1 ~P
        if (OperadorLogico.NEGACAO.equals(linha1.getOperadorPrincipal())) {
            sblinha1.delete(0, 0 + 1 * OperadorLogico.NEGACAO.getLength());
            // Confere se linha 1 sem ~ = linha 2
            if (!str2.equals(sblinha1.toString())) {
                // Confere se linha 2 ~P
                if (OperadorLogico.NEGACAO.equals(linha2.getOperadorPrincipal())) {
                    sblinha2.delete(0, 0 + 1 * OperadorLogico.NEGACAO.getLength());

                    // Confere se linha 2 sem ~ = linha 1
                    if (!str1.equals(sblinha2.toString())) {
                        a.SendMessage(studentId + "#" + Argumento + "#CorretudeInconsistencia#0");
                        throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra");
                    }
                } else {
                    a.SendMessage(studentId + "#" + Argumento + "#CorretudeInconsistencia#0");
                    throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra");
                }
            }
        } else {
            // Confere se linha 2 ~P
            if (OperadorLogico.NEGACAO.equals(linha2.getOperadorPrincipal())) {
                sblinha2.delete(0, 0 + 1 * OperadorLogico.NEGACAO.getLength());

                // Confere se linha 2 sem ~ = linha 1
                if (!str1.equals(sblinha2.toString())) {
                    a.SendMessage(studentId + "#" + Argumento + "#CorretudeInconsistencia#0");
                    throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra");
                }
            } else {
                a.SendMessage(studentId + "#" + Argumento + "#CorretudeInconsistencia#0");
                throw new LogicException("Formato inválido das fórmula(s) selecionada(s) para a regra");
            }
        }

        String linhaRetorno = str;
        LinhaProva novalinha = new LinhaDeduzida(linhaRetorno, Regras.INC);
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeInconsistencia#1");
        return novalinha;
    }

}
