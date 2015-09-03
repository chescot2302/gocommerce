/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.gjson;

import java.io.Serializable;

/**
 *
 * @author SISTEMAS
 */
public class GjsonKey implements Serializable{
    private String id;
    private String tipoDato;
    private String identificador;
    private String key;

    public GjsonKey(String id, String tipoDato, String identificador) {
        this.id = id;
        this.tipoDato = tipoDato;
        this.identificador = identificador;
        this.key=id+"@"+tipoDato+"@"+identificador;
    }

    public String getId() {
        return id;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getKey() {
        return key;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setKey(String key) {
        this.key = key;
    }
       
}
