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
@PersistenceCapable(table = "fcu0000",detachable="true")
public class Usuario implements Serializable{    
    @PrimaryKey
    @Column(name = "codusu")
    private String id;
    @Persistent
    @Column(name = "nomusu")
    private String nombres;
    @Persistent
    @Column(name = "nomacc")
    private String nick;
    @Persistent
    @Column(name = "clausu")
    private String clave;
    @Persistent
    @Column(name = "fecing")
    private Date fechaIngreso;
    @Persistent
    @Column(name = "nivusu")
    private String nivel;
    @Persistent
    @Column(name = "codpto")
    private String idPuntoEmision;
    @Persistent
    @Column(name = "codgru")
    private String idGrupo;
    @Persistent
    @Column(name = "codven")
    private String idVendedor;
    @Persistent
    @Column(name = "codalm")
    private String idAlmacen;
    @Persistent
    @Column(name = "cdocu")
    private String idDocumento;
    @NotPersistent
    private Long version;
    @NotPersistent
    private String operacion;
    @NotPersistent
    private Integer idBdUsuario;
    @NotPersistent
    private String nomPtoEmision;
    @NotPersistent
    private String idTienda;
    @NotPersistent
    private String nomTienda;
    @NotPersistent
    private String idSucursal;
    @NotPersistent
    private String nomSucursal;
    @NotPersistent
    private String idLocalidad;
    @NotPersistent
    private String nomLocalidad;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getIdPuntoEmision() {
        return idPuntoEmision;
    }

    public void setIdPuntoEmision(String idPuntoEmision) {
        this.idPuntoEmision = idPuntoEmision;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(String idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
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

    public Integer getIdBdUsuario() {
        return idBdUsuario;
    }

    public void setIdBdUsuario(Integer idBdUsuario) {
        this.idBdUsuario = idBdUsuario;
    } 

    public String getNomPtoEmision() {
        return nomPtoEmision;
    }

    public void setNomPtoEmision(String nomPtoEmision) {
        this.nomPtoEmision = nomPtoEmision;
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

    public String getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(String idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getNomLocalidad() {
        return nomLocalidad;
    }

    public void setNomLocalidad(String nomLocalidad) {
        this.nomLocalidad = nomLocalidad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.idBdUsuario);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.idBdUsuario, other.idBdUsuario)) {
            return false;
        }
        return true;
    }
    
    

    
    
    
    
}
