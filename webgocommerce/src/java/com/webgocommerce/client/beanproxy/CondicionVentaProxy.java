/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.CondicionVenta;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorCondicionVenta;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=CondicionVenta.class,locator=LocatorCondicionVenta.class)
public interface CondicionVentaProxy extends EntityProxy{
     public String getIdCondicionVenta();

    public void setIdCondicionVenta(String idCondicionVenta);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public Integer getDiasVencimiento();

    public void setDiasVencimiento(Integer diasVencimiento);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
