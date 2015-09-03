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
public class TipoVenta implements Serializable {

    private String idTipoVenta;
    private String descripcion;
    private String cuenta;
    private Integer manejaStock;
    private Integer cobroEnCaja;
    private Long version;
    private String operacion;

    public String getIdTipoVenta() {
        return idTipoVenta;
    }

    public void setIdTipoVenta(String idTipoVenta) {
        this.idTipoVenta = idTipoVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Integer getManejaStock() {
        return manejaStock;
    }

    public void setManejaStock(Integer manejaStock) {
        this.manejaStock = manejaStock;
    }

    public Integer getCobroEnCaja() {
        return cobroEnCaja;
    }

    public void setCobroEnCaja(Integer cobroEnCaja) {
        this.cobroEnCaja = cobroEnCaja;
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
        hash = 89 * hash + Objects.hashCode(this.idTipoVenta);
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
        final TipoVenta other = (TipoVenta) obj;
        if (!Objects.equals(this.idTipoVenta, other.idTipoVenta)) {
            return false;
        }
        return true;
    }
    
    

}
