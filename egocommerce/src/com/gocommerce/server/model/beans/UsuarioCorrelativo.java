/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.util.Date;
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
@PersistenceCapable(table = "TBLUSUARIOCORRELATIVO",detachable="true")
public class UsuarioCorrelativo implements Serializable{
    @Persistent
    @PrimaryKey
    @Column(name = "IDUSERCORRE")
    private Integer id;
    @Persistent
    @Column(name = "CODUSU")
    private String idUsuario;
    @NotPersistent
    private String nomUsuario;
    @NotPersistent
    private String nomAcceso;
    @Persistent
    @Column(name = "IDBDUSUARIO")
    private Integer idBdUsuario;
    @Persistent
    @Column(name = "CDOCU")
    private String idDocumento;
    @NotPersistent
    private String nomDocumento;
    @Persistent
    @Column(name = "CODPTO")
    private String idPuntoEmision;
    @NotPersistent
    private String nomPtoEmision;
    @Persistent
    @Column(name = "SERIE")
    private String serie;
    @Persistent
    @Column(name = "FECHAINI")
    private Date fechaIni;
    @Persistent
    @Column(name = "FECHAFIN")
    private Date fechaFin;
    @Persistent
    @Column(name = "ESTADO")
    private String estado;
    @NotPersistent
    private Long version;
    @NotPersistent
    private String operacion;          
    @NotPersistent
    private String idSucursal; 
    @NotPersistent
    private String nomSucursal;
    @NotPersistent
    private String idTienda;
    @NotPersistent
    private String nomTienda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getNomAcceso() {
        return nomAcceso;
    }

    public void setNomAcceso(String nomAcceso) {
        this.nomAcceso = nomAcceso;
    }       

    public Integer getIdBdUsuario() {
        return idBdUsuario;
    }

    public void setIdBdUsuario(Integer idBdUsuario) {
        this.idBdUsuario = idBdUsuario;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNomDocumento() {
        return nomDocumento;
    }

    public void setNomDocumento(String nomDocumento) {
        this.nomDocumento = nomDocumento;
    }

    public String getIdPuntoEmision() {
        return idPuntoEmision;
    }

    public void setIdPuntoEmision(String idPuntoEmision) {
        this.idPuntoEmision = idPuntoEmision;
    }

    public String getNomPtoEmision() {
        return nomPtoEmision;
    }

    public void setNomPtoEmision(String nomPtoEmision) {
        this.nomPtoEmision = nomPtoEmision;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public void setNomSucursal(String nomSucursal) {
        this.nomSucursal = nomSucursal;
    }

    public String getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(String idTienda) {
        this.idTienda = idTienda;
    }

    public String getNomTienda() {
        return nomTienda;
    }

    public void setNomTienda(String nomTienda) {
        this.nomTienda = nomTienda;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final UsuarioCorrelativo other = (UsuarioCorrelativo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
