/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipampa.edu.heraclito;

/**
 *
 * @author Rafael Bueno
 */
public class StringHelper {

    public static String removeWhitesSpaces(String text) {
        return text.replaceAll("\\s+", "");
    }
    
    public static Boolean isNullOrEmptyWhitespace(String text) {
        if(text == null) {
            return Boolean.TRUE;
        }
        
        if(StringHelper.removeWhitesSpaces(text).length() == 0) {
            return Boolean.TRUE;
        }
        
        return Boolean.FALSE;
    }
    
    public static String removeExternalParentesis(String text) {
        return text.replaceFirst("^\\s*\\({1}", "").replaceFirst("\\)\\s*$", "");
    }
    
    public static Boolean containsExternalParentesis(String text) {
        return text.matches("^\\s*\\(.*?\\)\\s*$");
    }
}
