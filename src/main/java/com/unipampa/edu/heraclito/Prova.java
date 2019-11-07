package com.unipampa.edu.heraclito;

import com.unipampa.edu.constantes.FuncoesString;
import com.unipampa.edu.constantes.Lado;
import com.unipampa.edu.constantes.OperadorLogico;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.execption.LogicException;
import com.unipampa.edu.regra.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Rafael
 */
public class Prova implements Cloneable {
    
    private String entrada;
    private List<String> hipoteses;
    private List<String> hipotesesAdicionadas;
    private String resultado;
    private List<LinhaProva> linhas;
    private boolean finalizada;
    private boolean podeAplicarRegra;
    
    private String studentId;
    
    private static final String SWAC_POST_INFORM = 
            "http://localhost:8090/swac/student_profile/proof_editor/inform";


    public Prova(String student, String entrada) throws LogicException {
        Agente.getInstancia();
        this.entrada = FuncoesString.upperCaseNotV(entrada);
        this.linhas = new ArrayList();
        this.hipoteses = new ArrayList<String>();
        this.hipotesesAdicionadas = new ArrayList<String>();
        this.podeAplicarRegra = false;
        this.finalizada = false;
        this.studentId = student;
        this.processaEntrada(entrada);
    }
    
    public String getEntrada() {
        return this.entrada;
    }
    
    public List<LinhaProva> getLinhas() {
        return this.linhas;
    }
    
    public void setLinhas(ArrayList<LinhaProva> linhas) {
        this.linhas = linhas;
    }
    
    public boolean estaFinalizada() {
        return finalizada;
    }
    
    private void processaEntrada(String entrada) throws LogicException {
        if (entrada == null || entrada.isEmpty()) {
            throw new LogicException("É necessário digitar algo para a entrada");
        }
        
        if (!FuncoesString.saoParentesesValidos(entrada)) {
            throw new LogicException("Parênteses inválidos!");
        }
        
        LinhaHipotese linhaEntrada = new LinhaHipotese(entrada);
        if (linhaEntrada.getOperadorPrincipal() != OperadorLogico.RESULTA) {
            throw new LogicException("Falta o operador "
                    + OperadorLogico.RESULTA.getExpressao());
        }
        
        entrada = FuncoesString.removerEspacos(entrada);
        
        int posicaoResulta = linhaEntrada.getPosicaoOperadorPrincipal();
        
        StringBuilder sb = new StringBuilder(entrada);
        
        String strhipoteses = sb.substring(0, posicaoResulta);
        
        String resultado = sb.substring(posicaoResulta
                + OperadorLogico.RESULTA.getLength());
        
        LinhaHipotese linhaSaida = new LinhaHipotese(resultado);
        this.resultado = linhaSaida.getLinha();
        
        if (strhipoteses.length() > 0) {
            String[] hipoteses = strhipoteses.split(",");
            for (String hip : hipoteses) {
                linhaSaida = new LinhaHipotese(hip);
                this.hipoteses.add(linhaSaida.getLinha());
            }
        } else {
            podeAplicarRegra = true;
        }
        
        this.informTutorNewArg();
    }
    
    private void informTutorNewArg(){
        // Inform tutor that a new argument (a new proof exercise) has started

        // Check if there is some student logged
        if (studentId==null || studentId.isEmpty()) 
            return;
        
        // Type of message: new_arg
        String content="new_arg";
        content += "\n";
        
        //Agente a = Agente.getInstancia();
        //a.SendMessage(studentId );

        // Proof argument
        content +="[";
        for (Iterator<String> i = this.hipoteses.iterator(); i.hasNext();){
            String hip = i.next();
            // Hipotesis
            
            content += FuncoesString.toCanonicalFormula(hip.toString().toLowerCase());
            if (i.hasNext())
                content += ",";
        }
        content+="] :-- ";
        // Conclusion
        content+=FuncoesString.toCanonicalFormula(this.resultado.toLowerCase());
        
        //Envia uma mensagem ao agente com o argumento
        Agente a = Agente.getInstancia();
        System.out.println(this.studentId + "#" + content.split("\n")[1] + "#NovoArgumento");
        a.SendMessage(this.studentId + "#" + content.split("\n")[1] + "#NovoArgumento");
        
        content+="\n";   
                
        // Student's identification
        content += this.studentId;
        content+="\n";  
        
        // Post message to Heraclito tutoring server
        NetClientPost.Post(SWAC_POST_INFORM, content);
    }
    
    
    
    public boolean temHipotesesPendentes() {
        return !this.podeAplicarRegra;
    }
    
    public void mostrarHipoteses() {
        for (String linha : this.hipoteses) {
            try {
                adicionarHipotese(linha);
            } catch (LogicException e) {
            }
        }
    }
    
    public void adicionarHipotese(String input) throws LogicException {
        LinhaProva hipotese = new LinhaHipotese(input);
        if (finalizada) {
            throw new LogicException("A prova foi finalizada!");
        } else if (!this.hipoteses.contains(hipotese.getLinha())) {
            throw new LogicException("Hipótese inválida para esta prova!");
        } else if (this.hipotesesAdicionadas.contains(hipotese.getLinha())) {
            throw new LogicException("Hipótese já adicionada!");
        }
        
        this.linhas.add(hipotese);
        this.hipotesesAdicionadas.add(hipotese.getLinha());
        if (this.hipoteses.size() == this.hipotesesAdicionadas.size()) {
            this.podeAplicarRegra = true;
        }
        this.informTutorNewHypotLine();

    }
    
    private List<LinhaProva> preAplicarRegra(List<Integer> linhas)
            throws LogicException {
        /* Cria lista de linhas (objetos), verificando validade destas */
        List<LinhaProva> listaDeLinhas = new ArrayList<LinhaProva>();
        if (linhas == null || linhas.isEmpty()) {
            return listaDeLinhas;
        }
        
        for (Integer l : linhas) {
            if ((((int) l - 1) < 0) || ((int) l - 1) >= this.linhas.size()) {
                throw new LogicException("Linha " + l + " inválida!");
            }
            
            LinhaProva temp = this.linhas.get((int) l - 1);
            if (temp.estaTravada()) {
                throw new LogicException("Linha " + l + " inválida!");
            }
            listaDeLinhas.add(temp);
        }
        return listaDeLinhas;
    }
    
//    public List<LinhaProva> preAplicarPCvRAA(List<Integer> linhas)
    public List<Integer> preAplicarPCvRAA(List<Integer> linhas)
            throws LogicException {
        List<Integer> paramlist = new ArrayList<Integer>(2);
        
        if (linhas == null || linhas.size() != 1) {
            throw new LogicException("Número de linhas inválido. Número correto: 1");
        }
        
        int index = linhas.get(0) - 1;
        
        if (index < 0 || index >= this.linhas.size()) {
            throw new LogicException("Número de linha inválido!");
        }
        
        int nvhip = this.linhas.get(index).getNivelHipotese();
        
        if (nvhip < 1) {
            throw new LogicException("Linha inválida. Precisa fazer parte de uma hipótese!");
        }
        
        int i = 1, linicial = 0, lfinal = 0;
        for (LinhaProva it : this.linhas) {
            if (!it.estaTravada() && (it.getNivelHipotese() == nvhip)) {
                if (linicial == 0) {
                    linicial = i;
                }
                lfinal = i;
            } else if (!it.estaTravada() && it.getNivelHipotese() > nvhip) {
                throw new LogicException("Linha inválida. Há hipóteses a serem fechadas!");
            } else if (it.getNivelHipotese() < nvhip && linicial != 0) {
                break;
            }
            i++;
        }
        
        paramlist.add(linicial);
        paramlist.add(lfinal);
        
        return paramlist;
    }
    
    private void travarLinhas(List<Integer> linhas)
            throws LogicException {
        if (linhas == null || linhas.size() != 1) {
            throw new LogicException("Número de linhas inválido. Número correto: 1");
        }
        
        int index = linhas.get(0) - 1;
        
        if (index < 0 || index >= this.linhas.size()) {
            throw new LogicException("Linha inválida!");
        }
        
        int nvhip = this.linhas.get(index).getNivelHipotese();
        
        for (LinhaProva it : this.linhas) {
            if (!it.estaTravada() && (it.getNivelHipotese() >= nvhip)) {
                it.Travar();
            }
        }
    }
    
    public void aplicarRegra(Regras regra, List<Integer> linhas,
            String str, Lado lado, String sID) throws LogicException {
        studentId = sID;
        if (!podeAplicarRegra) {
            throw new LogicException("Adicione primeiro as hipóteses!");
        }
        if (this.finalizada) {
            throw new LogicException("A prova foi finalizada!");
        }
        
        List<LinhaProva> listaDeLinhas = null;
        List<Integer> linhasPCvRAA = null;
        
        if (regra == Regras.PC || regra == Regras.RAA) {
            linhasPCvRAA = preAplicarPCvRAA(linhas);
            listaDeLinhas = preAplicarRegra(linhasPCvRAA);
        } else {
            listaDeLinhas = preAplicarRegra(linhas);
        }
        LinhaProva novaLinha;

        /* Aplicada regra requisitada */
        if (regra == Regras.AD) {
            novaLinha = Adicao.aplicarRegra(listaDeLinhas, str, lado, studentId, getArgumento());
        } else if (regra == Regras.CH) {
            novaLinha = CriarHipotese.aplicarRegra(str);
        } else if (regra == Regras.HPC) {
            novaLinha = HipotesePC.aplicarRegra(str);
        } else if (regra == Regras.HRAA) {
            novaLinha = HipoteseRAA.aplicarRegra(str);
        } else if (regra == Regras.CJ) {
            novaLinha = Conjuncao.aplicarRegra(listaDeLinhas, studentId, getArgumento());
        } else if (regra == Regras.CL) {
            novaLinha = CopiarLinha.aplicarRegra(listaDeLinhas);
        } else if (regra == Regras.DC) {
            novaLinha = DilemaConstrutivo.aplicarRegra(listaDeLinhas, studentId, getArgumento());
        } else if (regra == Regras.DN) {
            novaLinha = DuplaNegacao.aplicarRegra(listaDeLinhas, studentId, getArgumento());
        } else if (regra == Regras.EXP) {
            novaLinha = Exportacao.aplicarRegra(listaDeLinhas, studentId, getArgumento());
        } else if (regra == Regras.INC) {
            novaLinha = Incosistencia.aplicarRegra(listaDeLinhas, str, studentId, getArgumento());
        } else if (regra == Regras.MDJ) {
            novaLinha = EliminacaoDisjuncao.aplicarRegra(listaDeLinhas, studentId, getArgumento());
        } else if (regra == Regras.MEQ) {
            novaLinha = EliminacaoEquivalencia.aplicarRegra(listaDeLinhas, lado, studentId, getArgumento());
        } else if (regra == Regras.MP) {
            novaLinha = ModusPonens.aplicarRegra(listaDeLinhas, studentId, getArgumento());
        } else if (regra == Regras.MT) {
            novaLinha = ModusTollens.aplicarRegra(listaDeLinhas, studentId, getArgumento());
        } else if (regra == Regras.PC) {
            novaLinha = ProvaCondicional.aplicarRegra(listaDeLinhas, studentId, getArgumento());
        } else if (regra == Regras.PEQ) {
            novaLinha = IntroducaoEquivalencia.aplicarRegra(listaDeLinhas, studentId, getArgumento());
        } else if (regra == Regras.RAA) {
            novaLinha = ReducaoAoAbsurdo.aplicarRegra(listaDeLinhas, studentId, getArgumento());
        } else if (regra == Regras.SD) {
            novaLinha = SilogismoDisjuntivo.aplicarRegra(listaDeLinhas, studentId, getArgumento());
        } else if (regra == Regras.SH) {
            novaLinha = SilogismoHipotetico.aplicarRegra(listaDeLinhas, studentId, getArgumento());
        } else if (regra == Regras.SP) {
            novaLinha = Simplificacao.aplicarRegra(listaDeLinhas, lado, studentId, getArgumento());
        } else {
            throw new LogicException("Operação inválida!");
        }
        
        if (this.linhas.size()
                > 0) {
            LinhaProva ultimaLinha = this.linhas.get(this.linhas.size() - 1);
            novaLinha.setNivelHipotese(ultimaLinha.getNivelHipotese());
            if (!novaLinha.getRegra().equals(Regras.HPC) && !novaLinha.getRegra().equals(Regras.HRAA)) {
                novaLinha.setHipoteseResultado(ultimaLinha.getHipoteseResultado());
            }
        } else {
            novaLinha.setNivelHipotese(0);
        }
        
        if (regra.equals(Regras.PC)
                || regra.equals(Regras.RAA)) {
            travarLinhas(linhas);
            novaLinha.setNivelHipotese(novaLinha.getNivelHipotese() - 1);
        } else if (regra.equals(Regras.CH) || regra.equals(Regras.HPC) || regra.equals(Regras.HRAA)) {
            novaLinha.setNivelHipotese(novaLinha.getNivelHipotese() + 1);
        }
        
        this.linhas.add(novaLinha);
        
        if (novaLinha.getLinha()
                .equals(this.resultado)
                && (novaLinha.getNivelHipotese() == 0)) {
            this.finalizada = true;
        }
        
        if (novaLinha instanceof LinhaDeduzida) {
            LinhaDeduzida ld = (LinhaDeduzida) novaLinha;
            if (regra == Regras.PC || regra == Regras.RAA) {
                ld.setLinhas(linhasPCvRAA);
            } else {
                ld.setLinhas(linhas);
            }
        }
        
        this.informTutorNewStepLine();
    }
    
    private void informTutorNewHypotLine(){
        // Inform tutor that a new hypothesis was added by the student
        
        // Check if there is some student logged
        if (studentId==null || studentId.isEmpty()) 
            return;
        
        // Type of message: new_hypot
        String content="new_hypot\n";           
        
        // Proof argument
        content +="[";
        for (Iterator<String> i = this.hipoteses.iterator(); i.hasNext();){
            String hip = i.next();
            // Hipotesis
            content += FuncoesString.toCanonicalFormula(hip.toString().toLowerCase());
            if (i.hasNext())
                content += ",";
        }
        content+="] :-- ";
        // Conclusion
        content+=FuncoesString.toCanonicalFormula(this.resultado.toLowerCase());
        content+="\n";   
        
        // List of steps of partial proof
        int i = 1;
        content +="[";
        for (LinhaProva lp : this.linhas) {
            content += "step(";
            // Line index
            content += Integer.toString(i)+",";
            // Proof level
            content += Integer.toString(lp.getNivelHipotese())+",";
            // Formula
            content += FuncoesString.toCanonicalFormula(lp.getLinha().toLowerCase())+",";
            // Rule
            content += "'"+lp.getRegra().getTutorID()+"'"+",[";
            // References
            if (lp instanceof LinhaDeduzida) {
                LinhaDeduzida ld = (LinhaDeduzida) lp;
                content += ld.getRefs();
            };
            content += "])";
            i++;
            if (i<=this.linhas.size())
                content+=",";
            }
        content += "]";
        content+="\n";   
        
        // Student's identification
        content += this.studentId;
        content+="\n";  
        
        // Post message to Heraclito tutoring server
        NetClientPost.Post(SWAC_POST_INFORM, content);
    }
    
    private void informTutorNewStepLine(){
        // Inform tutor that a new step was added by the student
        
        // Check if there is some student logged
        if (studentId==null || studentId.isEmpty()) 
            return;
        
        // Type of message: new_step 
        String content="new_step\n";
        
        // Proof argument
        content +="[";
        for (Iterator<String> i = this.hipoteses.iterator(); i.hasNext();){
            String hip = i.next();
            // Hipotesis
            content += FuncoesString.toCanonicalFormula(hip.toString().toLowerCase());
            if (i.hasNext())
                content += ",";
        }
        content+="] :-- ";
        // Conclusion
        content+=FuncoesString.toCanonicalFormula(this.resultado.toLowerCase());
        content+="\n";   
        
        // List of steps of partial proof
        int i = 1;
        content +="[";
        for (LinhaProva lp : this.linhas) {
            content += "step(";
            // Line index
            content += Integer.toString(i)+",";
            // Proof level
            content += Integer.toString(lp.getNivelHipotese())+",";
            // Formula
            content += FuncoesString.toCanonicalFormula(lp.getLinha().toLowerCase())+",";
            // Rule
            content += "'"+lp.getRegra().getTutorID()+"'"+",[";
            // References
            if (lp instanceof LinhaDeduzida) {
                LinhaDeduzida ld = (LinhaDeduzida) lp;
                content += ld.getRefs();
            };
            content += "])";
            i++;
            if (i<=this.linhas.size())
                content+=",";
            }
        content += "]";
        content+="\n";   
        
        // Student's identification
        content += this.studentId;
        content+="\n";  
        
        // Post message to Heraclito tutoring server
        NetClientPost.Post(SWAC_POST_INFORM, content);
    }
    

    
    @Override
    public String toString() {
        /*StringBuilder sb = new StringBuilder();
         sb.append(this.entrada).append("\n");
         int i = 1;
         for (LinhaProva linha : this.linhas) {
         sb.append(i + " ");
         i++;
         sb.append(linha.toString());
         sb.append("\n");
         }*/
        return this.toHTMLString();
    }
    
    public String toHTMLString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr class=\"linhatravada\"><th></th><th>");
        sb.append(this.entrada);
        sb.append("</th><th></th><th></th></tr>");
        int i = 1;
        for (LinhaProva linha : this.linhas) {
            sb.append("<tr id\"linha").append(i);
            if (linha.estaTravada()) {
                sb.append("\" class=\"linhatravada");
            }
            sb.append("\"><td>").append(i).append("</td>");
            i++;
            sb.append("<td>").append(linha.toString()).append("</td>");
            sb.append("<td>").append(linha.getRegra().toString());
            if (linha instanceof LinhaDeduzida) {
                LinhaDeduzida ld = (LinhaDeduzida) linha;
                sb.append(ld.getStringLinhas());
                if (linha.getRegra().equals(Regras.HPC) 
                        || linha.getRegra().equals(Regras.HRAA)) {
                    sb.append(" (").append(linha.getHipoteseResultado()).append(")");
                }                
            }
            sb.append("</td>");
            sb.append("<td></td></tr>");
        }
        return sb.toString();
    }
    
    public List<String> getHipoteses() {
        return hipoteses;
    }
    
    public void setHipoteses(List<String> hipoteses) {
        this.hipoteses = hipoteses;
    }
    
    public List<String> getHipotesesAdicionadas() {
        return hipotesesAdicionadas;
    }
    
    public void setHipotesesAdicionadas(List<String> hipotesesAdicionadas) {
        this.hipotesesAdicionadas = hipotesesAdicionadas;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        Prova cloned = (Prova) super.clone();
        
        ArrayList<LinhaProva> clonedL = new ArrayList<LinhaProva>();
        for (LinhaProva l : this.linhas) {
            clonedL.add((LinhaProva) l.clone());
        }
        cloned.setLinhas(clonedL);
        
        ArrayList<String> clonedH = new ArrayList<String>();
        for (String l : this.hipoteses) {
            clonedH.add(l);
        }
        cloned.setHipoteses(clonedH);
        
        ArrayList<String> clonedHA = new ArrayList<String>();
        for (String l : this.hipotesesAdicionadas) {
            clonedHA.add(l);
        }
        cloned.setHipotesesAdicionadas(clonedHA);
        
        return cloned;
    }
    public String getArgumento(){
        String content ="[";
            
        for (Iterator<String> i = this.hipoteses.iterator(); i.hasNext();){
            String hip = i.next();
            // Hipotesis

            content += FuncoesString.toCanonicalFormula(hip.toString().toLowerCase());
            if (i.hasNext())
                content += ",";
        }
        content+="] :-- ";
        // Conclusion
        content+=FuncoesString.toCanonicalFormula(this.resultado.toLowerCase());
        return content;
    }
}
