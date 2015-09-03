/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.util;

import java.io.Serializable;

/**
 *
 * @author PC-Docente
 */
public class Parametro implements Serializable{    
    private String inOrOut;
    private Object valor;

    public Parametro(String inOrOut, Object valor) {
        this.inOrOut = inOrOut;
        this.valor = valor;
    }

    
    public String getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(String inOrOut) {
        this.inOrOut = inOrOut;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    
    
}
