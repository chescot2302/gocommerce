/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author SISTEMAS
 */
public class ActivaBdUsuarioRPC implements Serializable{
    private Integer idActivaBdUsuario;
    private Date fechaIni;
    private Date fechaFin;
    private String estadoActivacion;
    private BdUsuarioRPC beanBdUsuario;
    private Long version;
    private String operacion;

    public Integer getIdActivaBdUsuario() {
        return idActivaBdUsuario;
    }

    public void setIdActivaBdUsuario(Integer idActivaBdUsuario) {
        this.idActivaBdUsuario = idActivaBdUsuario;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstadoActivacion() {
        return estadoActivacion;
    }

    public void setEstadoActivacion(String estadoActivacion) {
        this.estadoActivacion = estadoActivacion;
    }

    public BdUsuarioRPC getBeanBdUsuario() {
        return beanBdUsuario;
    }

    public void setBeanBdUsuario(BdUsuarioRPC beanBdUsuario) {
        this.beanBdUsuario = beanBdUsuario;
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
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.idActivaBdUsuario);
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
        final ActivaBdUsuarioRPC other = (ActivaBdUsuarioRPC) obj;
        if (!Objects.equals(this.idActivaBdUsuario, other.idActivaBdUsuario)) {
            return false;
        }
        return true;
    }

    

}
