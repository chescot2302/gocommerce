/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@XmlRootElement(name = "itemplan")
public class ItemPlan implements Serializable{
    private Integer idEquipPlan;
    private String idItem;
    private String idItemPlan;
    private Integer idLista;
    private String cDocu;
    private String nDocu;
    private BigDecimal cantidad=BigDecimal.ZERO;
    private BigDecimal precio=BigDecimal.ZERO;
    private BigDecimal total=BigDecimal.ZERO;
    private Long version;
    private String operacion;    
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }        
    
    @XmlElement(name="idequipplan")
    public Integer getIdEquipPlan() {
        return idEquipPlan;
    }

    public void setIdEquipPlan(Integer idEquipPlan) {
        this.idEquipPlan = idEquipPlan;
    }
    
    @XmlElement(name="codi")    
    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }
    
    @XmlElement(name="codiplan")    
    public String getIdItemPlan() {
        return idItemPlan;
    }

    public void setIdItemPlan(String idItemPlan) {
        this.idItemPlan = idItemPlan;
    }
    
    @XmlElement(name="idlista")    
    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }
    
    @XmlElement(name="cdocu")    
    public String getcDocu() {
        return cDocu;
    }

    public void setcDocu(String cDocu) {
        this.cDocu = cDocu;
    }
    
    @XmlElement(name="ndocu")    
    public String getnDocu() {
        return nDocu;
    }

    public void setnDocu(String nDocu) {
        this.nDocu = nDocu;
    }
    
    @XmlElement(name="cantidad")    
    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
    
    @XmlElement(name="precio")    
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    @XmlElement(name="total")        
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
        hash = 41 * hash + Objects.hashCode(this.idEquipPlan);
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
        final ItemPlan other = (ItemPlan) obj;
        if (!Objects.equals(this.idEquipPlan, other.idEquipPlan)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
