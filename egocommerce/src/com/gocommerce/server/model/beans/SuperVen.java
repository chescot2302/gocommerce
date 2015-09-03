/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@XmlRootElement(name = "superven")
public class SuperVen implements Serializable{
    private Integer idSuperVen;
    private Integer idMesa;
    private String idVendedor;
    private Date fechaIni;
    private Date fechaFin;
    private String estado;
    private String dni;
    private String nomConsultor;
    private Long version;
    private String operacion;
    
    @XmlElement(name="idsuperven")
    public Integer getIdSuperVen() {
        return idSuperVen;
    }

    public void setIdSuperVen(Integer idSuperVen) {
        this.idSuperVen = idSuperVen;
    }
    
    @XmlElement(name="idmesa")
    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }
    
    @XmlElement(name="idvendedor")
    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
    
    @XmlElement(name="fechaini")
    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }
    
    @XmlElement(name="fechafin")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    @XmlElement(name="estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNomConsultor() {
        return nomConsultor;
    }

    public void setNomConsultor(String nomConsultor) {
        this.nomConsultor = nomConsultor;
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
        hash = 29 * hash + Objects.hashCode(this.idSuperVen);
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
        final SuperVen other = (SuperVen) obj;
        if (!Objects.equals(this.idSuperVen, other.idSuperVen)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
