package com.unipampa.edu.regra;

import com.unipampa.edu.heraclito.Agente;
import com.unipampa.edu.heraclito.LinhaDeduzida;
import com.unipampa.edu.heraclito.LinhaProva;
import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.Lado;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import java.util.List;

public class Adicao {

    public static LinhaProva aplicarRegra(List<LinhaProva> listaDeLinhas,
        String str, Lado lado, String studentId, String Argumento) 
        throws LogicException {
        Agente a = Agente.getInstancia();
        //System.out.println(this.studentId + "#" + content.split("\n")[1] + "#NovoArgumento");

        /* Confere parâmetros */
        if(listaDeLinhas.size() != 1) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeAdicao#0");
            throw new LogicException("Número de linhas inválido!");
        }        
        if(!FuncoesString.saoParentesesValidos(str)) {
            a.SendMessage(studentId + "#" + Argumento + "#CorretudeAdicao#0");
            throw new LogicException("Entrada inválida!");
        }
        
        /* Cria variáveis temporárias */
        LinhaProva linha1 = (LinhaProva) listaDeLinhas.get(0);                
        StringBuilder sblinha = new StringBuilder(
                FuncoesString.adicionarParenteses(linha1.getLinha()));
        str = FuncoesString.adicionarParenteses(str);
        
        /* Prepara string de saída */        
        if(lado.equals(Lado.ESQUERDA)) {
            sblinha.insert(0, OperadorLogico.DISJUNCAO);
            sblinha.insert(0, str);
        }
        else {
            sblinha.insert(sblinha.length(), OperadorLogico.DISJUNCAO);
            sblinha.insert(sblinha.length(), str);
        }
        a.SendMessage(studentId + "#" + Argumento + "#CorretudeAdicao#1");

        /* Retorno */
        return new LinhaDeduzida(sblinha.toString(), Regras.AD);
    }
    
}
