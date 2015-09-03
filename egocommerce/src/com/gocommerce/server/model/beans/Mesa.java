/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

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
@PersistenceCapable(table="TBLMESA",detachable="true")
public class Mesa {
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @PrimaryKey 
    @Column(name="IDMESA")
    private Integer idMesa;
    @Persistent
    @Column(name="DESCRIPCION")
    private String descripcion;
    @Persistent
    @Column(name="IDCOORDINADOR")
    private Integer idCoordinador; 
    @Persistent
    @Column(name="IDGERENTEZONAL")
    private Integer idGerenteZonal; 
    @Persistent
    @Column(name="IDSUPERVISOR")
    private Integer idSupervisor; 
    @Persistent
    @Column(name="IDSUCURSAL")
    private String idSucursal;    
    @Persistent
    @Column(name="ESTADO")
    private String estado;   
    @Persistent
    @Column(name = "FECHAINI")
    private Date fechaIni;
    @Persistent
    @Column(name = "FECHAFIN")
    private Date fechaFin;
    @NotPersistent
    private String nomCoordinador;
    @NotPersistent
    private String nomGerenteZonal;
    @NotPersistent
    private String nomSupervisor;
    @NotPersistent
    private String nomSucursal;
    @NotPersistent
    private Long version;
    @NotPersistent
    private String operacion;
    @NotPersistent
    private Integer idSuperVen;
    @NotPersistent
    private String idVendedor;

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }        

    public Integer getIdSuperVen() {
        return idSuperVen;
    }

    public void setIdSuperVen(Integer idSuperVen) {
        this.idSuperVen = idSuperVen;
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
    
    

    public Integer getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public Integer getIdGerenteZonal() {
        return idGerenteZonal;
    }

    public void setIdGerenteZonal(Integer idGerenteZonal) {
        this.idGerenteZonal = idGerenteZonal;
    }

    public Integer getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(Integer idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNomCoordinador() {
        return nomCoordinador;
    }

    public void setNomCoordinador(String nomCoordinador) {
        this.nomCoordinador = nomCoordinador;
    }

    public String getNomGerenteZonal() {
        return nomGerenteZonal;
    }

    public void setNomGerenteZonal(String nomGerenteZonal) {
        this.nomGerenteZonal = nomGerenteZonal;
    }

    public String getNomSupervisor() {
        return nomSupervisor;
    }

    public void setNomSupervisor(String nomSupervisor) {
        this.nomSupervisor = nomSupervisor;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public void setNomSucursal(String nomSucursal) {
        this.nomSucursal = nomSucursal;
    }
    
    

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
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
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.idMesa);
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
        final Mesa other = (Mesa) obj;
        if (!Objects.equals(this.idMesa, other.idMesa)) {
            return false;
        }
        return true;
    }
    
    
    
}
