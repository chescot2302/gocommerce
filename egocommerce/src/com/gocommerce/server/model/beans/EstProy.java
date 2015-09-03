/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author SISTEMAS
 */
public class EstProy {
   private Integer idEstadoProy;
   private Date fechaInc;
   private Date FechaCese;
   private Integer estado;
   private String estadoActual;
   private Long version;    
   private String operacion;
   private String tipoDoc;
   private String correlativo;
   private String observacion;

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
   
   

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }
   
   

    public Integer getIdEstadoProy() {
        return idEstadoProy;
    }

    public void setIdEstadoProy(Integer idEstadoProy) {
        this.idEstadoProy = idEstadoProy;
    }

    public Date getFechaInc() {
        return fechaInc;
    }

    public void setFechaInc(Date fechaInc) {
        this.fechaInc = fechaInc;
    }

    public Date getFechaCese() {
        return FechaCese;
    }

    public void setFechaCese(Date FechaCese) {
        this.FechaCese = FechaCese;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
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
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.idEstadoProy);
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
        final EstProy other = (EstProy) obj;
        if (!Objects.equals(this.idEstadoProy, other.idEstadoProy)) {
            return false;
        }
        return true;
    }
   
   
}
