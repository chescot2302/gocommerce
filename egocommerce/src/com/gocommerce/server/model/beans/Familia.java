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
public class Familia implements Serializable{
    private String codFam;
    private String descripcion;
    private Long version;
    private String operacion;

    public String getCodFam() {
        return codFam;
    }

    public void setCodFam(String codFam) {
        this.codFam = codFam;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codFam);
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
        final Familia other = (Familia) obj;
        if (!Objects.equals(this.codFam, other.codFam)) {
            return false;
        }
        return true;
    }
    
    
}
