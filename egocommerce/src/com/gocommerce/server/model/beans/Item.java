package com.gocommerce.server.model.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@PersistenceCapable(table = "prd0101",detachable="true")
public class Item implements Serializable{
    @Persistent
    @PrimaryKey
    @Column(name = "codi")
    private String id;
    @Persistent
    @Column(name = "codf")
    private String codigo;
    @Persistent
    @Column(name = "descr")
    private String descripcion;
    @Persistent
    @Column(name = "marc")
    private String marca;
    @Persistent
    @Column(name = "umed")
    private String unidad;
    @Persistent
    @Column(name = "persnt")
    private Double percepcion;
    @Persistent
    @Column(name = "ubica")
    private String ubica;
    @Persistent
    @Column(name = "mone")
    private String moneda;
    @Persistent
    @Column(name = "ucom")
    private String ucom;
    @Persistent
    @Column(name = "pcns")
    private BigDecimal costoSoles;
    @Persistent
    @Column(name = "aigv")
    private String afectoIgv;
    @Persistent
    @Column(name = "msto")
    private String manejaStock;
    @Persistent
    @Column(name = "lserie")
    private Integer lserie;
    @NotPersistent
    private Long version;
    @NotPersistent
    private String operacion;
    @NotPersistent
    private String ctaVenta;
    @NotPersistent
    private List<ItemSerie> series=new ArrayList();

    public List<ItemSerie> getSeries() {
        return series;
    }

    public void setSeries(List<ItemSerie> series) {
        this.series = series;
    }        

    public Integer getLserie() {
        return lserie;
    }

    public void setLserie(Integer lserie) {
        this.lserie = lserie;
    }        

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Double getPercepcion() {
        return percepcion;
    }

    public void setPercepcion(Double percepcion) {
        this.percepcion = percepcion;
    }

    public String getUbica() {
        return ubica;
    }

    public void setUbica(String ubica) {
        this.ubica = ubica;
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

    public String getCtaVenta() {
        return ctaVenta;
    }

    public void setCtaVenta(String ctaVenta) {
        this.ctaVenta = ctaVenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getUcom() {
        return ucom;
    }

    public void setUcom(String ucom) {
        this.ucom = ucom;
    }

    public BigDecimal getCostoSoles() {
        return costoSoles;
    }

    public void setCostoSoles(BigDecimal costoSoles) {
        this.costoSoles = costoSoles;
    }   

    public String getAfectoIgv() {
        return afectoIgv;
    }

    public void setAfectoIgv(String afectoIgv) {
        this.afectoIgv = afectoIgv;
    }

    public String getManejaStock() {
        return manejaStock;
    }

    public void setManejaStock(String manejaStock) {
        this.manejaStock = manejaStock;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Item other = (Item) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
