/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Tienda;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorTienda;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Tienda.class,locator=LocatorTienda.class)
public interface TiendaProxy extends EntityProxy{
    public String getId();

    public void setId(String id);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public String getDireccion();

    public void setDireccion(String direccion);

    public String getTelefono();

    public void setTelefono(String telefono);

    public String getUbigeo();

    public void setUbigeo(String ubigeo);

    public String getIdSucursal();

    public void setIdSucursal(String idSucursal);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
