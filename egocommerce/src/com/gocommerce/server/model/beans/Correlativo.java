/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.util.Objects;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * @author SISTEMAS
 */
@PersistenceCapable(objectIdClass = CorrelativoKey.class,table = "tbl01cor",detachable="true")
public class Correlativo implements Serializable{
    @Persistent
    @PrimaryKey
    @Column(name = "cdocu")
    private String idDocumento;
    @Persistent
    @PrimaryKey
    @Column(name = "codpto")
    private String idPuntoEmision;
    @Persistent
    @Column(name = "nomdoc")
    private String nombreDocumento;
    @Persistent
    @Column(name = "nroini")
    private String nroInicio;
    @Persistent
    @Column(name = "nrofin")
    private String nroFin;
    @NotPersistent
    private Long version;
    @NotPersistent
    private String operacion;
    @NotPersistent
    private String serie;
    @NotPersistent
    private String preimpreso;
    @NotPersistent
    private String docSerie;

    public String getDocSerie() {
        return docSerie;
    }

    public void setDocSerie(String docSerie) {
        this.docSerie = docSerie;
    }        

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getIdPuntoEmision() {
        return idPuntoEmision;
    }

    public void setIdPuntoEmision(String idPuntoEmision) {
        this.idPuntoEmision = idPuntoEmision;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getNroInicio() {
        return nroInicio;
    }

    public void setNroInicio(String nroInicio) {
        this.nroInicio = nroInicio;
    }

    public String getNroFin() {
        return nroFin;
    }

    public void setNroFin(String nroFin) {
        this.nroFin = nroFin;
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

    public String getPreimpreso() {
        return preimpreso;
    }

    public void setPreimpreso(String preimpreso) {
        this.preimpreso = preimpreso;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
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
        final Correlativo other = (Correlativo) obj;
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        if (!Objects.equals(this.idPuntoEmision, other.idPuntoEmision)) {
            return false;
        }
        return true;
    }
    
    
    
}
