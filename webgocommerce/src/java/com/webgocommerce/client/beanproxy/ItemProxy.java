/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Item;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorItem;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Item.class,locator=LocatorItem.class)
public interface ItemProxy extends EntityProxy{
    public String getId();

    public void setId(String id);

    public String getCodigo();

    public void setCodigo(String codigo);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public String getMarca();

    public void setMarca(String marca);

    public String getUnidad();

    public void setUnidad(String unidad);

    public Double getPercepcion();

    public void setPercepcion(Double percepcion);

    public String getUbica();

    public void setUbica(String ubica);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
    
    public String getCtaVenta();

    public void setCtaVenta(String ctaVenta);
    
    public String getMoneda();

    public void setMoneda(String moneda);
    
    public String getUcom();

    public void setUcom(String ucom);
    
    public BigDecimal getCostoSoles();

    public void setCostoSoles(BigDecimal costoSoles);
    
    public String getAfectoIgv();

    public void setAfectoIgv(String afectoIgv);
    
    public String getManejaStock();

    public void setManejaStock(String manejaStock);
    
    public Integer getLserie();

    public void setLserie(Integer lserie);
    
    public List<ItemSerieProxy> getSeries();

    public void setSeries(List<ItemSerieProxy> series);
}
