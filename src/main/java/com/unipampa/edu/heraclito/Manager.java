
package com.unipampa.edu.heraclito;

import com.unipampa.edu.constantes.Lado;
import com.unipampa.edu.constantes.Regras;
import com.unipampa.edu.credenciais.CredentialsException;
import com.unipampa.edu.credenciais.User;
import com.unipampa.edu.execption.LogicException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** @author Rafael Koch Peres */
public class Manager {
    private Prova prova;
    private Stack<Prova> undo;
    private Stack<Prova> redo;
    
    private String studentId;
    
    private static Pattern pattern;

    private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String SWAC_POST_REQUEST = 
            "http://localhost:8090/swac/student_profile/proof_editor/request";

    public Manager(String student, Prova prova) {
        this.studentId = student;
        this.prova = prova;
        this.undo = new Stack();
        this.redo = new Stack();
    }
    
    public String getArgumento(){
        return this.prova.getArgumento();
    }

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }
    
    public boolean hasProva() {
        return this.prova!=null;
    }
    
    private void updateStack() throws CloneNotSupportedException {
        this.undo.push((Prova) this.prova.clone());
        this.redo = new Stack();
    }
    
    public void undo() {
        this.redo.push(this.prova);
        this.prova = this.undo.pop();
    }
    
    public void redo() {
        this.undo.push(this.prova);
        this.prova = this.redo.pop();
    }
    
    public boolean canUndo() {
        if(this.undo.size() > 0)
            return true;
        return false;
    }
    
    public boolean canRedo() {
        if(this.redo.size() > 0)
            return true;
        return false;
    }
    
    public void aplicarRegra(Regras regra, List<Integer> linhas,
            String str, Lado lado, String sID) 
            throws LogicException, CloneNotSupportedException {
        this.updateStack();
        this.prova.aplicarRegra(regra, linhas, str, lado, sID);
    }
    
    public void adicionarHipotese(String str) 
            throws CloneNotSupportedException, LogicException {
        this.updateStack();
        this.prova.adicionarHipotese(str);
    }
    
    public void mostrarHipoteses() 
            throws CloneNotSupportedException {
        this.updateStack();
        this.prova.mostrarHipoteses();
    }
    
    public void limparProva() 
            throws LogicException, CloneNotSupportedException {
        this.updateStack();
        this.prova = new Prova(this.studentId, this.prova.getEntrada());
    }
    
    public boolean provaTemHipotesesPendentes() {
        return this.prova.temHipotesesPendentes();
    }
    
    public boolean provaEstaFinalizada() {
        return this.prova.estaFinalizada();
    }
    
    	/**
	 * Validate email with regular expression
	 * 
	 * @param email
	 *            email for validation
	 * @return true valid email, false invalid email
	 */
    public static boolean emailValidate(String email) {
        Matcher matcher;
        
        if (pattern==null)
            pattern = Pattern.compile(EMAIL_PATTERN);
	matcher = pattern.matcher(email);
	return matcher.matches();

    }


    public static String cadastrar(String nome, String sobrenome, String email,
            String senha, String matricula) throws CredentialsException {
        if(nome == null || nome.isEmpty()) {
            throw new CredentialsException("Favor, preencha o nome.");
        }
        
        if(sobrenome == null || sobrenome.isEmpty()) {
            throw new CredentialsException("Favor, preencha o sobrenome.");
        }
        
        if(email == null || email.isEmpty() || !emailValidate(email)) {
            throw new CredentialsException("Favor, preencha o email.");
        }
        
        if(senha == null || senha.isEmpty()) {
            throw new CredentialsException("Favor, preencha a senha.");
        }
        
        if (!requestTutorEnroll(nome,sobrenome,email,senha)){
            throw new CredentialsException("Cadastro novo não aceito porque usuário já está cadastrado.");            
        }
        return "Cadastro realizado com sucesso!";
    }
    
 
    private static boolean requestTutorEnroll(
                String nome, String sobrenome, String email, String senha){
        // Request tutor to enroll a new student
        // Type of command: enroll
        String content="enroll";
        content += "\n";
        
        // E-Mail
        content+=email.toLowerCase();
        content+="\n";   
        
        // Password
        content+=senha;
        content+="\n";   
        
        // First name
        content+=nome;
        content+="\n";   

        // Last name 
        content+=sobrenome;
        content+="\n";  
        
        // Post command to Heraclito tutoring server
        return NetClientPost.Post(SWAC_POST_REQUEST, content);
    }

    
    public static String login(String userid, String senha) throws CredentialsException {
        if(userid == null || userid.isEmpty()) {
            throw new CredentialsException("Favor, preencha o login de usuário.");
        }
        
        if(senha == null || senha.isEmpty()) {
            throw new CredentialsException("Favor, preencha a senha.");
        }
        
        if (!requestTutorLogin(userid,senha)){
            throw new CredentialsException("Login não aceito: email ou senha inválida.");            
        }
        return "Login realizado com sucesso!";
    }
    
 
    private static boolean requestTutorLogin(String userid, String senha){
        // Request tutor to login a student
        // Type of command: login
        String content="login";
        content += "\n";
        
        // E-Mail
        content+=userid.toLowerCase();
        content+="\n";   
        
        // Password
        content+=senha;
        content+="\n";   
        
        // Post command to Heraclito tutoring server
        return NetClientPost.Post(SWAC_POST_REQUEST, content);
    }

    
    public static String changePass(String userid, String oldpass, String newpass) throws CredentialsException {
        if(userid == null || userid.isEmpty()) {
            throw new CredentialsException("Favor, preencha o email.");
        }
        
        if(oldpass == null || oldpass.isEmpty()) {
            throw new CredentialsException("Favor, preencha a senha antiga.");
        }
        
        if(newpass == null || newpass.isEmpty()) {
            throw new CredentialsException("Favor, preencha a nova senha.");
        }
        
        if (!requestTutorChangePass(userid,oldpass,newpass)){
            throw new CredentialsException("Mudança de senha não aceita.");            
        }
        return "Senha alterada com sucesso!";
    }
    
 
    private static boolean requestTutorChangePass(String userid, String oldpass, String newpass){
        // Request tutor to login a student
        // Type of command: login
        String content="changepass";
        content += "\n";
        
        // User ID
        content+=userid.toLowerCase();
        content+="\n";   
        
        // Old Password
        content+=oldpass;
        content+="\n";   
        
        // New Password
        content+=newpass;
        content+="\n";   
        
        // Post command to Heraclito tutoring server
        return NetClientPost.Post(SWAC_POST_REQUEST, content);
    }

    
   public static String logout(String userid) throws CredentialsException {
       if (!requestTutorLogout(userid)){
            throw new CredentialsException("Logout não aceito.");            
        }
        return "Logout realizado com sucesso!";
    }
    
 
    private static boolean requestTutorLogout(String userid){
        // Request tutor to login a student
        // Type of command: login
        String content="logout";
        content += "\n";
        
        // User ID
        content+=userid.toLowerCase();
        content+="\n";   
        
       // Post command to Heraclito tutoring server
        return NetClientPost.Post(SWAC_POST_REQUEST, content);
    }

    
     
    @Override
    public String toString() {
        return this.prova.toHTMLString();
    }
}