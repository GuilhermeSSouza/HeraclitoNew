package com.unipampa.edu.heraclito;



import com.unipampa.edu.bayesagent.BayesAgent;

public class Agente {
    private static Agente instancia;
    private static BayesAgent bayes;
    
    public Agente(){}
    
    public static Agente getInstancia() {
        if (instancia == null) {
            instancia = new Agente();
            bayes = new BayesAgent();
//            runAgent();
//            instancia.SendMessage("1#ARG#CorretudeEquivalencia#1");
        } 
        return instancia;
    }
    public String getPath(){
        String path = System.getProperty("user.dir");
//        try {
            //Thread.sleep(1000);
//            bayesagent.BayesAgent b = bayes.getO2AInterface(bayesagent.BayesAgent.class);
//            path = path + "   " + b.getPath();
//        } catch (StaleProxyException ex) {
//            Logger.getLogger(Agente.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Agente.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return path;
    }
/*    
    private static void runAgent(){
        // Get a hold on JADE runtime
        jade.core.Runtime rt = jade.core.Runtime.instance();

        // Exit the JVM when there are no more containers around
        rt.setCloseVM(true);
        System.out.print("runtime created\n");

        rt.setCloseVM(true);
        System.out.print("runtime created\n");

        // now set the default Profile to start a container
        jade.core.ProfileImpl pContainer = new jade.core.ProfileImpl(null, 1099, null);
        System.out.println("Launching the agent container ..."+pContainer);

        jade.wrapper.AgentContainer cont = rt.createAgentContainer(pContainer);
        System.out.println("Launching the agent container after ..."+pContainer);

        System.out.println("containers created");
        System.out.println("Launching the rma agent on the main container ...");
        
        try {
            bayes = cont.createNewAgent("bayes", "bayesagent.BayesAgent", new Object[0]);
            bayes.start();
        } catch (StaleProxyException ex) {
            System.out.println("ERRO");
        }
    }
    */
    
    public void SendMessage(String message){
        Object obj = message;
        System.out.println(message);
//        try {
            bayes.putMessage(obj);
            //bayes.putO2AObject(obj, true);
//        } catch (StaleProxyException ex) {
//            System.out.println("Erro ao enviar mensagem ao agente.");
//        }
    }
    
    public BayesAgent getBayes(){
    return bayes;
    }
}


/*
                            Agente a = Agente.getInstancia();
                            %>
                            if (what == "Ajuda"){
                                <%
                                System.out.println(studentId + "#" + prova.getArgumento() + "#Ajuda");
                                a.SendMessage(studentId + "#" + prova.getArgumento() + "#Ajuda");
                                %>
                            }else if(what == "Exemplo"){
                                <%
                                System.out.println(studentId + "#" + prova.getArgumento() + "#Exemplo");
                                a.SendMessage(studentId + "#" + prova.getArgumento() + "#Exemplo");
                                %>
                            }else if(what == "Conceito"){
                                <%
                                System.out.println(studentId + "#" + prova.getArgumento() + "#Conceito");
                                a.SendMessage(studentId + "#" + prova.getArgumento() + "#Conceito");
                                %>                                
                            }*/