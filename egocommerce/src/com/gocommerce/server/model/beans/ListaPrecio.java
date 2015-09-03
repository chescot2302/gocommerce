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
@PersistenceCapable(table = "TBLLISTAPRECIO",detachable="true")
public class ListaPrecio implements Serializable{
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
    @Column(name = "IDLISTA")
    private Integer id;
    @Persistent
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Persistent
    @Column(name = "CONDICION")
    private String condicion;
    @Persistent
    @Column(name = "PAGOMENSUAL")
    private Double pagoMensual;
    @Persistent
    @Column(name = "VIGENCIA")
    private Integer vigencia;
    @Persistent
    @Column(name = "FECHAINI")
    private Date fechaIni;
    @Persistent
    @Column(name = "FECHAFIN")
    private Date fechaFin;
    @Persistent
    @Column(name = "ESTADOACTIVA")
    private String estadoActiva;    
    @Persistent
    @Column(name = "VERSION")
    private Long version;
    @NotPersistent
    private String operacion;
    @Persistent
    @Column(name = "IDCATEGORIALISTA")
    private CategoriaLista beanCategoriaLista;
    @NotPersistent
    private Integer idCategoriaLista;
    @Persistent
    @Column(name = "CANAL")
    private String canal;

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }       

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(Double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public Integer getVigencia() {
        return vigencia;
    }

    public void setVigencia(Integer vigencia) {
        this.vigencia = vigencia;
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

    public String getEstadoActiva() {
        return estadoActiva;
    }

    public void setEstadoActiva(String estadoActiva) {
        this.estadoActiva = estadoActiva;
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

    public CategoriaLista getBeanCategoriaLista() {
        return beanCategoriaLista;
    }

    public void setBeanCategoriaLista(CategoriaLista beanCategoriaLista) {
        this.beanCategoriaLista = beanCategoriaLista;
    }

    public Integer getIdCategoriaLista() {
        return idCategoriaLista;
    }

    public void setIdCategoriaLista(Integer idCategoriaLista) {
        this.idCategoriaLista = idCategoriaLista;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final ListaPrecio other = (ListaPrecio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
            
}
