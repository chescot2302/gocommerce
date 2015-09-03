/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.TipoVenta;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorTipoVenta;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=TipoVenta.class,locator=LocatorTipoVenta.class)
public interface TipoVentaProxy extends EntityProxy{
    public String getIdTipoVenta();

    public void setIdTipoVenta(String idTipoVenta);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public String getCuenta();

    public void setCuenta(String cuenta);

    public Integer getManejaStock();

    public void setManejaStock(Integer manejaStock);

    public Integer getCobroEnCaja();

    public void setCobroEnCaja(Integer cobroEnCaja);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
