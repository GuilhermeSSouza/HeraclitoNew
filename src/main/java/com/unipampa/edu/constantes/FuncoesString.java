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
public class FuncoesString {

    // Remove todos espaços da string
    public static String removerEspacos(String str) {
        return str.replaceAll("\\s+", "");
    }

    // Adicionar parênteses às bordas, se tamanho > 1
    public static String adicionarParenteses(String str) {
        str = removerParentesesReduntantes(str);
        if (str.length() > 2) {
            StringBuilder tempsb = new StringBuilder(str);
            tempsb.insert(0, '(');
            tempsb.insert(tempsb.length(), ')');
            str = tempsb.toString();
        }
        return str;
    }

    // Remove parênteses desnecessários das bordas
    public static String removerParentesesReduntantes(String str) {
        boolean continuarRemocao;
        do {
            continuarRemocao = false;
            if (str.startsWith("(")
                    && str.endsWith(")")) {
                // Nova string sem parênteses inicial e final
                String substring = str.substring(
                        1, str.length() - 1);
                if (saoParentesesValidos(substring)) {
                    str = substring;
                    continuarRemocao = true;
                }
            }
        } while (continuarRemocao);
        return str;
    }

    // Apenas conta os parênteses e diz se são válidos
    public static boolean saoParentesesValidos(String str) {
        int contador = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                contador++;
            } else if (c == ')') {
                contador--;
            }

            if (contador < 0) {
                return false;
            }
        }
        if (contador != 0) {
            return false;
        }
        return true;
    }

    public static String upperCaseNotV(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = input.length(); i < n; i++) {
            char c = input.charAt(i);
            if(c != 'v') {
                c = Character.toUpperCase(c);
            }
            sb.append(c);
        }
        return sb.toString();
    }
    
    public static String toCanonicalFormula(String formula){
        String canonFormula="";
        for (int i = 0, n = formula.length(); i < n; i++) {
            char c = formula.charAt(i);
            if (c=='v') {
                canonFormula+=" v ";           
            } else if (c=='~') {
                canonFormula+=" ~ ";
            } else {
                canonFormula+=c;
            }
        }
        return canonFormula;
    }
    

}
