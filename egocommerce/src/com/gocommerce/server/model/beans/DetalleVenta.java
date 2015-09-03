/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@XmlRootElement(name = "detalleventa")
public class DetalleVenta implements Serializable{
    private String idDetalleVenta;
    private Date fechaEmision;    
    private String tipoDoc;
    private String correlativo;
    private String tipoFacturacion;
    private String idCliente;
    private String nomCliente;
    private String rucCliente;
    private BigDecimal tipoCambio;    
    private String idMonedaItem;
    private String moneda;
    private String afectoIgv;     
    private Integer secuencia;    
    private String idItem;
    private String codigo;  
    private String marca;
    private String unidadMedida;
    private String descripcion;    
    private BigDecimal costo;    
    private BigDecimal cantidad;    
    private BigDecimal precioUnitario;
    private BigDecimal precio;
    private BigDecimal montoAfecto;
    private BigDecimal porcDescuento;
    private BigDecimal totalNeto;
    private BigDecimal montoPercepcion;
    private String idAlmacen;
    private String idTipoVenta;
    private String manejaStock;
    private String unidadCom;
    private BigDecimal ValorVenta;
    private BigDecimal totalVenta;
    private Long version;
    private String operacion; 
    private String ctaVenta;
    private Double afectoPercepcion; 
    private BigDecimal montoIgv;
    private BigDecimal montoNoAfecto;
    private BigDecimal precioPlan;
    private String idVendedor;
    private String idCondicionVenta;
    private Integer vigencia=0;
    private Integer estadoAnulacion=0;//0 NO ANULADO - 1 ANULADO
    private Integer idPrecioItem;
    private Integer idLista;
    private List<ItemSerie> series=new ArrayList();
    private List<ItemPlan> equipos=new ArrayList();
    private Integer lserie;
    private String plan;
    private String condPlan;
    private String nomAlmacen;
    private String categoriaVenta;
    private String usuarioReg;
    
    
    
    @XmlElement(name="USERREG")
    public String getUsuarioReg() {
        return usuarioReg;
    }

    public void setUsuarioReg(String usuarioReg) {
        this.usuarioReg = usuarioReg;
    }
    
    @XmlElement(name="CATVENTA")
    public String getCategoriaVenta() {
        return categoriaVenta;
    }

    public void setCategoriaVenta(String categoriaVenta) {
        this.categoriaVenta = categoriaVenta;
    }                   

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getCondPlan() {
        return condPlan;
    }

    public void setCondPlan(String condPlan) {
        this.condPlan = condPlan;
    }

    public String getNomAlmacen() {
        return nomAlmacen;
    }

    public void setNomAlmacen(String nomAlmacen) {
        this.nomAlmacen = nomAlmacen;
    }
        
    @XmlElement(name="mserie")
    public Integer getLserie() {
        return lserie;
    }

    public void setLserie(Integer lserie) {
        this.lserie = lserie;
    }        

    public List<ItemSerie> getSeries() {
        return series;
    }

    public void setSeries(List<ItemSerie> series) {
        this.series = series;
    }

    public List<ItemPlan> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<ItemPlan> equipos) {
        this.equipos = equipos;
    }
    
    
    
    @XmlElement(name="vigencia")
    public Integer getVigencia() {
        return vigencia;
    }

    public void setVigencia(Integer vigencia) {
        this.vigencia = vigencia;
    }
    
    @XmlElement(name="anulado")
    public Integer getEstadoAnulacion() {
        return estadoAnulacion;
    }

    public void setEstadoAnulacion(Integer estadoAnulacion) {
        this.estadoAnulacion = estadoAnulacion;
    }
    
    @XmlElement(name="idprecioitem")
    public Integer getIdPrecioItem() {
        return idPrecioItem;
    }

    public void setIdPrecioItem(Integer idPrecioItem) {
        this.idPrecioItem = idPrecioItem;
    }
    
    @XmlElement(name="idlista")
    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }       
    
    @XmlElement(name="nomCliente")
    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }
    
    @XmlElement(name="rucCliente")
    public String getRucCliente() {
        return rucCliente;
    }

    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }        
    
    @XmlElement(name="iddetalleventa")
    public String getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(String idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }        
    
    @XmlElement(name="fecha")
    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    
    @XmlElement(name="cdocu")
    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }
    
    @XmlElement(name="ndocu")
    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }
    
    @XmlElement(name="tfact")
    public String getTipoFacturacion() {
        return tipoFacturacion;
    }

    public void setTipoFacturacion(String tipoFacturacion) {
        this.tipoFacturacion = tipoFacturacion;
    }
    
    @XmlElement(name="codcli")
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    
    @XmlElement(name="tcam")
    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
    
    @XmlElement(name="mone")
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }        
    
    @XmlElement(name="moneitm")
    public String getIdMonedaItem() {
        return idMonedaItem;
    }

    public void setIdMonedaItem(String idMonedaItem) {
        this.idMonedaItem = idMonedaItem;
    }
    
    @XmlElement(name="aigv")
    public String getAfectoIgv() {
        return afectoIgv;
    }

    public void setAfectoIgv(String afectoIgv) {
        this.afectoIgv = afectoIgv;
    }
    
    @XmlElement(name="item")
    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }
    
    @XmlElement(name="codi")
    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }
        
    @XmlElement(name="codf")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    @XmlElement(name="marc")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    @XmlElement(name="umed")
    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
    @XmlElement(name="descr")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @XmlElement(name="cost")
    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
    
    @XmlElement(name="cant")
    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
    
    @XmlElement(name="preu")
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }  
    
    @XmlElement(name="dsct")
    public BigDecimal getPorcDescuento() {
        return porcDescuento;
    }

    public void setPorcDescuento(BigDecimal porcDescuento) {
        this.porcDescuento = porcDescuento;
    }
    
    @XmlElement(name="totn")
    public BigDecimal getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(BigDecimal totalNeto) {
        this.totalNeto = totalNeto;
    }
    
    @XmlElement(name="codalm")
    public String getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(String idAlmacen) {
        this.idAlmacen = idAlmacen;
    }
    
    @XmlElement(name="codvta")
    public String getIdTipoVenta() {
        return idTipoVenta;
    }

    public void setIdTipoVenta(String idTipoVenta) {
        this.idTipoVenta = idTipoVenta;
    }
    
    @XmlElement(name="msto")
    public String getManejaStock() {
        return manejaStock;
    }

    public void setManejaStock(String manejaStock) {
        this.manejaStock = manejaStock;
    }
    
    @XmlElement(name="ucom")
    public String getUnidadCom() {
        return unidadCom;
    }

    public void setUnidadCom(String unidadCom) {
        this.unidadCom = unidadCom;
    }
    
    @XmlElement(name="valorventa")
    public BigDecimal getValorVenta() {
        return ValorVenta;
    }

    public void setValorVenta(BigDecimal ValorVenta) {
        this.ValorVenta = ValorVenta;
    }
    
    @XmlElement(name="totalventa")
    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
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
    
    @XmlElement(name="ctaventa")
    public String getCtaVenta() {
        return ctaVenta;
    }

    public void setCtaVenta(String ctaVenta) {
        this.ctaVenta = ctaVenta;
    }
    
    @XmlElement(name="percepcion")
    public Double getAfectoPercepcion() {
        return afectoPercepcion;
    }

    public void setAfectoPercepcion(Double afectoPercepcion) {
        this.afectoPercepcion = afectoPercepcion;
    }
    
    @XmlElement(name="montopercepcion")
    public BigDecimal getMontoPercepcion() {
        return montoPercepcion;
    }

    public void setMontoPercepcion(BigDecimal montoPercepcion) {
        this.montoPercepcion = montoPercepcion;
    }

    @XmlElement(name="precio")
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    @XmlElement(name="tota")
    public BigDecimal getMontoAfecto() {
        return montoAfecto;
    }

    public void setMontoAfecto(BigDecimal montoAfecto) {
        this.montoAfecto = montoAfecto;
    }
    
    @XmlElement(name="montoigv")
    public BigDecimal getMontoIgv() {
        return montoIgv;
    }

    public void setMontoIgv(BigDecimal montoIgv) {
        this.montoIgv = montoIgv;
    }
    
    @XmlElement(name="montonoafecto")
    public BigDecimal getMontoNoAfecto() {
        return montoNoAfecto;
    }

    public void setMontoNoAfecto(BigDecimal montoNoAfecto) {
        this.montoNoAfecto = montoNoAfecto;
    }
    
    @XmlElement(name="precioplan")
    public BigDecimal getPrecioPlan() {
        return precioPlan;
    }

    public void setPrecioPlan(BigDecimal precioPlan) {
        this.precioPlan = precioPlan;
    }     
    
    @XmlElement(name="codven")
    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
    
    @XmlElement(name="codcdv")
    public String getIdCondicionVenta() {
        return idCondicionVenta;
    }

    public void setIdCondicionVenta(String idCondicionVenta) {
        this.idCondicionVenta = idCondicionVenta;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.idDetalleVenta);
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
        final DetalleVenta other = (DetalleVenta) obj;
        if (!Objects.equals(this.idDetalleVenta, other.idDetalleVenta)) {
            return false;
        }
        return true;
    }
    
    
    
}
