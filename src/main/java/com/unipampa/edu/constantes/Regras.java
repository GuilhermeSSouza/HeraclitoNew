/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.constantes;

/**
 *
 * @author gia
 */
public enum Regras {

    HIP("Hip", "Hipótese", false),
    CH("CH", "Criar Hipótese", false),
    HPC("HPC", "Hipótese PC", false),
    HRAA("HRAA", "Hipótese RAA", false),
    RAA("RAA", "Redução ao Absurdo", false),
    DN("DN", "Dupla Negação", false),
    PC("PC", "Prova Condicional", false),
    MP("MP", "Modus Ponens", false),
    CJ("Cj", "Conjunção", false),
    SP("Sp", "Simplificação", false),
    AD("Ad", "Adição", false),
    MDJ("-Dj", "Remoção da Disjunção", false),
    PEQ("+Eq", "Introdução da Equivalência", false),
    MEQ("-Eq", "Eliminação da Equivalência", false),
    CL("CL", "Copiar Linha", false),
    MT("MT", "Modus Tollens", true),
    SH("SH", "Silogismo Hipotético", true),
    SD("SD", "Silogismo Disjuntivo", true),
    DC("DC", "Dilema Construtivo", true),
    EXP("EXP", "Exportação", true),
    INC("INC", "Inconsistência", true);

    private final String sigla;
    private final String nome;
    private final Boolean ehDerivada;

    Regras(String sigla, String nome, Boolean ehDerivada) {
        this.sigla = sigla;
        this.nome = nome;
        this.ehDerivada = ehDerivada;
    }

    public String getSigla() {
        return this.sigla;
    }

    public String getTutorID() {
/*  
    // lower case sigla = tutor ID
    HIP("Hip", "Hipótese", false),
        write_rule(hip) :- !, write('Hip').
    CH("CH", "Criar Hipótese", false),
        write_rule(ch) :- !, write('CH').
    RAA("RAA", "Redução ao Absurdo", false),
        write_rule(raa) :- !, write('RAA').
    DN("DN", "Dupla Negação", false),
        write_rule(dn) :- !, write('DN').
    PC("PC", "Prova Condicional", false),
        write_rule(pc) :- !, write('PC').
    MP("MP", "Modus Ponens", false),
        write_rule(mp) :- !, write('MP').
    MT("MT", "Modus Tollens", true),
        write_rule(mt) :- !, write('MT').
    SH("SH", "Silogismo Hipotético", true),
        write_rule(sh) :- !, write('SH')
    SD("SD", "Silogismo Disjuntivo", true),
        write_rule(sd) :- !, write('SD').
    DC("DC", "Dilema Construtivo", true),
        write_rule(dc) :- !, write('DC').
    INC("INC", "Inconsistência", true);
        write_rule(inc) :- !, write('Inc').

    // lower case sigla != tutor ID
    HPC("HPC", "Hipótese PC", false),
        write_rule(hippc) :- !, write('Hip-PC').
    HRAA("HRAA", "Hipótese RAA", false),
        write_rule(hipraa) :- !, write('Hip-RAA').
    CJ("Cj", "Conjução", false),
        write_rule(conj) :- !, write('Cj').
    SP("Sp", "Simplificação", false),
        write_rule(simp) :- !, write('Sp').
    AD("Ad", "Adição", false),
        write_rule(adic) :- !, write('Ad').
    MDJ("-Dj", "Remoção da Disjunção", false),
        write_rule(djelim) :- !, write('-Dj').
    PEQ("+Eq", "Introdução da Equivalência", false),
        write_rule(eqintrod) :- !, write('+Eq').
    MEQ("-Eq", "Eliminação da Equivalência", false),
        write_rule(eqelim) :- !, write('-Eq').
    CL("CL", "Copiar Linha", false),
        write_rule(idem) :- !, write('Idem').
    EXP("EXP", "Exportação", true),
        write_rule(export) :- !, write('Exp').
    
*/
        if (this.sigla=="HPC")
                return "hippc";
        else if (this.sigla=="HRAA")
                return "hipraa";
        else if (this.sigla=="Cj")
                return "conj";
        else if (this.sigla=="Sp")
                return "simp";
        else if (this.sigla=="Ad")
                return "adic";
        else if (this.sigla=="-Dj")
                return "djelim";
        else if (this.sigla=="+Eq")
                return "eqintrod";
        else if (this.sigla=="-Eq")
                return "eqelim";
        else if (this.sigla=="CL")
                return "idem";
        else if (this.sigla=="Exp")
                return "export";
        else    
                return this.sigla.toLowerCase();
        
}
    public String getNome() {
        return this.nome;
    }
}
