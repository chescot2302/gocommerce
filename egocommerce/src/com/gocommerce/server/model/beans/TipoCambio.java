/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
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
@PersistenceCapable(table = "tbl01tca",detachable="true")
public class TipoCambio implements Serializable{
    @Persistent
    @PrimaryKey
    @Column(name = "fecha")
    private Date fecha;
    @Persistent
    @Column(name = "mesle")
    private String mes;
    @Persistent
    @Column(name = "diale")
    private String dia;
    @Persistent
    @Column(name = "tccom")
    private BigDecimal tcCompra;
    @Persistent
    @Column(name = "tcvta")
    private BigDecimal tcVenta;
    @Persistent
    @Column(name = "tcmer")
    private BigDecimal tcMercado;
    @NotPersistent
    private Long version;
    @NotPersistent
    private String operacion;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public BigDecimal getTcCompra() {
        return tcCompra;
    }

    public void setTcCompra(BigDecimal tcCompra) {
        this.tcCompra = tcCompra;
    }

    public BigDecimal getTcVenta() {
        return tcVenta;
    }

    public void setTcVenta(BigDecimal tcVenta) {
        this.tcVenta = tcVenta;
    }

    public BigDecimal getTcMercado() {
        return tcMercado;
    }

    public void setTcMercado(BigDecimal tcMercado) {
        this.tcMercado = tcMercado;
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
        hash = 53 * hash + Objects.hashCode(this.fecha);
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
        final TipoCambio other = (TipoCambio) obj;
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }
    
    
}
