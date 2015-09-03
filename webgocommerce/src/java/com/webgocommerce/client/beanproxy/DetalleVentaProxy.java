/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.DetalleVenta;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorDetalleVenta;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=DetalleVenta.class,locator=LocatorDetalleVenta.class)
public interface DetalleVentaProxy extends EntityProxy{
    public String getUsuarioReg();

    public void setUsuarioReg(String usuarioReg);
    
    public String getCategoriaVenta();

    public void setCategoriaVenta(String categoriaVenta);
    
    public String getPlan();

    public void setPlan(String plan);

    public String getCondPlan();

    public void setCondPlan(String condPlan);

    public String getNomAlmacen();

    public void setNomAlmacen(String nomAlmacen);
    
    public String getIdDetalleVenta();

    public void setIdDetalleVenta(String idDetalleVenta);
    
     public Date getFechaEmision();

    public void setFechaEmision(Date fechaEmision);

    public String getTipoDoc();

    public void setTipoDoc(String tipoDoc);

    public String getCorrelativo();

    public void setCorrelativo(String correlativo);

    public String getTipoFacturacion();

    public void setTipoFacturacion(String tipoFacturacion);

    public String getIdCliente();

    public void setIdCliente(String idCliente);

    public BigDecimal getTipoCambio();

    public void setTipoCambio(BigDecimal tipoCambio);
    
    public String getMoneda();

    public void setMoneda(String moneda);

    public String getIdMonedaItem();

    public void setIdMonedaItem(String idMonedaItem);

    public String getAfectoIgv();

    public void setAfectoIgv(String afectoIgv);

    public Integer getSecuencia();

    public void setSecuencia(Integer secuencia);

    public String getIdItem();

    public void setIdItem(String idItem);

    public String getCodigo();

    public void setCodigo(String codigo);

    public String getMarca();

    public void setMarca(String marca);

    public String getUnidadMedida();

    public void setUnidadMedida(String unidadMedida);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public BigDecimal getCosto();

    public void setCosto(BigDecimal costo);

    public BigDecimal getCantidad();

    public void setCantidad(BigDecimal cantidad);

    public BigDecimal getPrecioUnitario();

    public void setPrecioUnitario(BigDecimal precioUnitario);
    
    public BigDecimal getPrecio();

    public void setPrecio(BigDecimal precio);

    public BigDecimal getMontoAfecto();

    public void setMontoAfecto(BigDecimal montoAfecto);

    public BigDecimal getMontoIgv();

    public void setMontoIgv(BigDecimal montoIgv);

    public BigDecimal getMontoNoAfecto();

    public void setMontoNoAfecto(BigDecimal montoNoAfecto);

    public BigDecimal getPorcDescuento();

    public void setPorcDescuento(BigDecimal porcDescuento);

    public BigDecimal getTotalNeto();

    public void setTotalNeto(BigDecimal totalNeto);

    public String getIdAlmacen();

    public void setIdAlmacen(String idAlmacen);

    public String getIdTipoVenta();

    public void setIdTipoVenta(String idTipoVenta);

    public String getManejaStock();

    public void setManejaStock(String manejaStock);

    public String getUnidadCom();

    public void setUnidadCom(String unidadCom);

    public BigDecimal getValorVenta();

    public void setValorVenta(BigDecimal ValorVenta);

    public BigDecimal getTotalVenta();

    public void setTotalVenta(BigDecimal totalVenta);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);

    public String getCtaVenta();

    public void setCtaVenta(String ctaVenta);

    public Double getAfectoPercepcion();

    public void setAfectoPercepcion(Double afectoPercepcion);
    
    public BigDecimal getMontoPercepcion();

    public void setMontoPercepcion(BigDecimal montoPercepcion);
    
    public BigDecimal getPrecioPlan();

    public void setPrecioPlan(BigDecimal precioPlan);
    
    public String getIdVendedor();

    public void setIdVendedor(String idVendedor);

    public String getIdCondicionVenta();

    public void setIdCondicionVenta(String idCondicionVenta);
        
    public String getNomCliente();

    public void setNomCliente(String nomCliente);
       
    public String getRucCliente();

    public void setRucCliente(String rucCliente);
    
    public Integer getVigencia();

    public void setVigencia(Integer vigencia);

    public Integer getEstadoAnulacion();

    public void setEstadoAnulacion(Integer estadoAnulacion);

    public Integer getIdPrecioItem();

    public void setIdPrecioItem(Integer idPrecioItem);

    public Integer getIdLista();

    public void setIdLista(Integer idLista);
    
    public List<ItemSerieProxy> getSeries();

    public void setSeries(List<ItemSerieProxy> series);
    
    public Integer getLserie();

    public void setLserie(Integer lserie);
    
    public List<ItemPlanProxy> getEquipos();

    public void setEquipos(List<ItemPlanProxy> equipos);
}
