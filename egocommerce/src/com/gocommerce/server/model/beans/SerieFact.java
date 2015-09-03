/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author SISTEMAS
 */
public class SerieFact implements Serializable{
    private Integer idSerieFact;
    private String codi;
    private String serie;
    private String cdocu;
    private String ndocu;
    private String flag;
    private String codalm;
    private String telefono;

    public Integer getIdSerieFact() {
        return idSerieFact;
    }

    public void setIdSerieFact(Integer idSerieFact) {
        this.idSerieFact = idSerieFact;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCdocu() {
        return cdocu;
    }

    public void setCdocu(String cdocu) {
        this.cdocu = cdocu;
    }

    public String getNdocu() {
        return ndocu;
    }

    public void setNdocu(String ndocu) {
        this.ndocu = ndocu;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCodalm() {
        return codalm;
    }

    public void setCodalm(String codalm) {
        this.codalm = codalm;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idSerieFact);
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
        final SerieFact other = (SerieFact) obj;
        if (!Objects.equals(this.idSerieFact, other.idSerieFact)) {
            return false;
        }
        return true;
    }
    
    
}
