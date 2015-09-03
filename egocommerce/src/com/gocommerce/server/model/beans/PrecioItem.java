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
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@XmlRootElement(name = "precioitem")
@PersistenceCapable(table = "TBLPRECIOSITEM",detachable="true")
public class PrecioItem implements Serializable{
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
    @Column(name = "IDPRECIOSITEM")
    private Integer id;
    @Persistent
    @Column(name = "IDLISTA")
    private Integer idListaPrecio;
    @Persistent
    @Column(name = "IDITEM")
    private String idItem;
    @NotPersistent
    private String codigo;
    @NotPersistent
    private String descripcion;
    @NotPersistent
    private String marca;
    @Persistent
    @Column(name = "VALORVENTA")
    private BigDecimal ValorVenta=BigDecimal.ZERO;
    @Persistent
    @Column(name = "IGV")
    private BigDecimal igv=BigDecimal.ZERO;
    @Persistent
    @Column(name = "PRECIOSD")
    private BigDecimal precioSD=BigDecimal.ZERO;
    @Persistent
    @Column(name = "DESCUENTO")
    private BigDecimal descuento=BigDecimal.ZERO;
    @Persistent
    @Column(name = "PRECIOVENTA")
    private BigDecimal precioVenta=BigDecimal.ZERO;
    @Persistent
    @Column(name = "IDMONEDA")
    private String idMoneda="S";
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
    @Persistent
    @Column(name = "SCPRECIO")
    private Integer isEditable;
    @NotPersistent
    private String operacion;
    @NotPersistent
    private Integer vigencia;
    @NotPersistent
    private BigDecimal pagoMensual;
    @NotPersistent
    private String estadoColor;

    public String getEstadoColor() {
        return estadoColor;
    }

    public void setEstadoColor(String estadoColor) {
        this.estadoColor = estadoColor;
    }
              
    @XmlElement(name="idpreciositem")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @XmlElement(name="idlista")
    public Integer getIdListaPrecio() {
        return idListaPrecio;
    }

    public void setIdListaPrecio(Integer idListaPrecio) {
        this.idListaPrecio = idListaPrecio;
    }
    
    @XmlElement(name="iditem")
    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }        
    
    @XmlElement(name="valorventa")
    public BigDecimal getValorVenta() {
        return ValorVenta;
    }

    public void setValorVenta(BigDecimal ValorVenta) {
        this.ValorVenta = ValorVenta;
    }
    
    @XmlElement(name="igv")
    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }    
    
    @XmlElement(name="preciosd")
    public BigDecimal getPrecioSD() {
        return precioSD;
    }

    public void setPrecioSD(BigDecimal precioSD) {
        this.precioSD = precioSD;
    }
    
    @XmlElement(name="descuento")
    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }
    
    @XmlElement(name="precioventa")
    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    @XmlElement(name="idmoneda")
    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }
    
    @XmlElement(name="fechaini")
    public Date getFechaIni() {
        return fechaIni;
    }
    
    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }
    
    @XmlElement(name = "fechafin")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    @XmlElement(name = "estadoactiva")
    public String getEstadoActiva() {
        return estadoActiva;
    }

    public void setEstadoActiva(String estadoActiva) {
        this.estadoActiva = estadoActiva;
    }
    
    @XmlElement(name = "version")
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

    public Integer getVigencia() {
        return vigencia;
    }

    public void setVigencia(Integer vigencia) {
        this.vigencia = vigencia;
    }

    public BigDecimal getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(BigDecimal pagoMensual) {
        this.pagoMensual = pagoMensual;
    }   
    
    @XmlElement(name = "scprecio")
    public Integer getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Integer isEditable) {
        this.isEditable = isEditable;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final PrecioItem other = (PrecioItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
