package com.gocommerce.server.model.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.gocommerce.server.model.beans.Credencial;

/**
 *
 * @author SISTEMAS
 */
public class CredencialUser extends Credencial{
    private String miClavePrivada;

    public String getMiClavePrivada() {
        return miClavePrivada;
    }

    public void setMiClavePrivada(String miClavePrivada) {
        this.miClavePrivada = miClavePrivada;
    }          
}
