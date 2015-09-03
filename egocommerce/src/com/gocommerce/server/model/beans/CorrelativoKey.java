/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 *
 * @author SISTEMAS
 */

public class CorrelativoKey implements Serializable{
    public String idDocumento;
    public String idPuntoEmision;
    public CorrelativoKey(){        
    }
    
    public CorrelativoKey(String value){
        StringTokenizer token=new StringTokenizer(value,"::");
        token.nextToken();
        this.idDocumento=token.nextToken();
        this.idPuntoEmision=token.nextToken();   
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idDocumento);
        hash = 97 * hash + Objects.hashCode(this.idPuntoEmision);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CorrelativoKey other = (CorrelativoKey) obj;
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        if (!Objects.equals(this.idPuntoEmision, other.idPuntoEmision)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+"::"+this.idDocumento+"::"+this.idPuntoEmision;
    
    
}
    }

