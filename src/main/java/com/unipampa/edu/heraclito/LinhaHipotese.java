/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unipampa.edu.heraclito;

import com.unipampa.edu.execption.LogicException;
import com.unipampa.edu.constantes.Regras;

/**
 *
 * @author gia
 */
public class LinhaHipotese extends LinhaProva {

    public LinhaHipotese(String input) throws LogicException {
        super(input);
        this.regraAplicada = Regras.HIP;        
    }
    
}
