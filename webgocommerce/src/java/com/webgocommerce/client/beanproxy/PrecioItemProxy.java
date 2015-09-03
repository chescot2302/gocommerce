/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.PrecioItem;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorPrecioItem;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=PrecioItem.class,locator=LocatorPrecioItem.class)
public interface PrecioItemProxy extends EntityProxy{
    public Integer getId();

    public void setId(Integer id);

    public Integer getIdListaPrecio();

    public void setIdListaPrecio(Integer idListaPrecio);

    public String getIdItem();

    public void setIdItem(String idItem);
    
    public String getCodigo();

    public void setCodigo(String codigo);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public String getMarca();

    public void setMarca(String marca);
    
    public BigDecimal getValorVenta();

    public void setValorVenta(BigDecimal ValorVenta);

    public BigDecimal getIgv();

    public void setIgv(BigDecimal igv);

    public BigDecimal getPrecioSD();

    public void setPrecioSD(BigDecimal precioSD);

    public BigDecimal getDescuento();

    public void setDescuento(BigDecimal descuento);

    public BigDecimal getPrecioVenta();

    public void setPrecioVenta(BigDecimal precioVenta);

    public String getIdMoneda();

    public void setIdMoneda(String idMoneda);

    public Date getFechaIni();

    public void setFechaIni(Date fechaIni);

    public Date getFechaFin();

    public void setFechaFin(Date fechaFin);

    public String getEstadoActiva();

    public void setEstadoActiva(String estadoActiva);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
    
    public Integer getVigencia();

    public void setVigencia(Integer vigencia);

    public BigDecimal getPagoMensual();

    public void setPagoMensual(BigDecimal pagoMensual);
    
    public Integer getIsEditable();

    public void setIsEditable(Integer isEditable);
    
    public String getEstadoColor();

    public void setEstadoColor(String estadoColor);
}
