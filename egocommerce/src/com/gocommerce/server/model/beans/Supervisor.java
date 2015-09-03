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
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * @author SISTEMAS
 */
@PersistenceCapable(table = "TBLSUPERVISOR",detachable="true")
public class Supervisor implements Serializable{
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @PrimaryKey 
    @Column(name="IDSUPERVISOR")
    private Integer idSupervisor;
    @Persistent
    @Column(name="DNI")
    private String dni;
    @Persistent
    @Column(name="NOMBRES")
    private String nombres;
    @Persistent
    @Column(name="APELLIDOS")
    private String apellidos;
    @NotPersistent
    private Long version;
    @NotPersistent
    private String operacion;
    @Persistent
    @Column(name="IDPTOEMISION")
    private String idPtoEmision;
    @Persistent
    @Column(name="IDMESA")
    private Integer idMesa;
    @NotPersistent
    private String nomMesa;
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
    @Persistent
    @Column(name="ESTADO")
    private Integer estado;
    @Persistent
    @Column(name="CODIGOALTERNO")
    private String codigoAlterno;
    @Persistent
    @Column(name="CANAL")
    private String canal;
    @Persistent
    @Column(name="CORREO")
    private String correo;
    @Persistent
    @Column(name="CELULAR")
    private String celular;
    @NotPersistent
    private Date fechaInc;
    @NotPersistent
    private Date fechaCese;

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getFechaInc() {
        return fechaInc;
    }

    public void setFechaInc(Date fechaInc) {
        this.fechaInc = fechaInc;
    }

    public Date getFechaCese() {
        return fechaCese;
    }

    public void setFechaCese(Date fechaCese) {
        this.fechaCese = fechaCese;
    }
        
    public String getIdPtoEmision() {
        return idPtoEmision;
    }

    public void setIdPtoEmision(String idPtoEmision) {
        this.idPtoEmision = idPtoEmision;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public String getNomMesa() {
        return nomMesa;
    }

    public void setNomMesa(String nomMesa) {
        this.nomMesa = nomMesa;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getCodigoAlterno() {
        return codigoAlterno;
    }

    public void setCodigoAlterno(String codigoAlterno) {
        this.codigoAlterno = codigoAlterno;
    }
    
    

    public Integer getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(Integer idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
        hash = 29 * hash + Objects.hashCode(this.idSupervisor);
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
        final Supervisor other = (Supervisor) obj;
        if (!Objects.equals(this.idSupervisor, other.idSupervisor)) {
            return false;
        }
        return true;
    }
    
    
    
}
