/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.PuntoEmision;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorPuntoEmision;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=PuntoEmision.class,locator=LocatorPuntoEmision.class)
public interface PuntoEmisionProxy extends EntityProxy{
    public String getId();

    public void setId(String id);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public String getIdSucursal();

    public void setIdSucursal(String idSucursal);

    public String getIdTienda();

    public void setIdTienda(String idTienda);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
