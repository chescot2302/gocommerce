/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

/**
 *
 * @author SISTEMAS
 */
public class CredencialAdmin extends Credencial{
    private String bd;
    private String miClavePublica;

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getMiClavePublica() {
        return miClavePublica;
    }

    public void setMiClavePublica(String miClavePublica) {
        this.miClavePublica = miClavePublica;
    }
    
    
}
