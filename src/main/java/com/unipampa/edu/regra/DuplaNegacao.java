package com.unipampa.edu.regra;

import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaHipotese;
import com.unipampa.edu.heraclito.LinhaProva;

import java.util.List;

public class DuplaNegacao {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas, String studentId, String Argumento) throws LogicException {
        Agente a = Agente.getInstancia();
            /* Confere parâmetros */
        if(listaDeLinhas.size() != 1) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeDuplaNegacao#0");
            throw new LogicException("Número de linhas inválido!");
        }
        
        /* Cria variáveis temporárias */
        LinhaProva linha = (LinhaProva) listaDeLinhas.get(0);
        
        /* Confere parâmetros - operador principal (1) */
        if(linha.getOperadorPrincipal() != OperadorLogico.NEGACAO) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeDuplaNegacao#0");
            throw new LogicException
                ("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                        + "operador principal inválido");
        }
        
        /* Posição dos operadores principais */
        int posicao = linha.getPosicaoOperadorPrincipal();
        
        /* Remove primeira negação */
        StringBuilder sblinha = new StringBuilder(linha.getLinha());
        sblinha.delete(posicao, posicao+1*OperadorLogico.NEGACAO.getLength());
        
        /* Confere parâmetros - operador principal (2) - segunda negação */
        linha = new LinhaHipotese(sblinha.toString());
        if(linha.getOperadorPrincipal() != OperadorLogico.NEGACAO) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeDuplaNegacao#0");
            throw new LogicException
                ("Formato inválido das fórmula(s) selecionada(s) para a regra - "
                        + "operador principal inválido");
        }
        
        /* Posição dos operadores principais */
        posicao = linha.getPosicaoOperadorPrincipal();
        
        /* Remove segunda negação e prepara string de saída */
        StringBuilder sblinha1 = new StringBuilder(linha.getLinha());
        sblinha1.delete(posicao, posicao+1*OperadorLogico.NEGACAO.getLength());        
        
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeDuplaNegacao#1");
        /* Retorno */
        return new LinhaDeduzida(sblinha1.toString(), Regras.DN);
    }
    
}
