/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Sucursal;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorSucursal;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Sucursal.class,locator=LocatorSucursal.class)
public interface SucursalProxy extends EntityProxy{
    public String getId();

    public void setId(String id);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public String getIdLista();

    public void setIdLista(String idLista);
    
    public Integer getIdLocalidad();

    public void setIdLocalidad(Integer idLocalidad);
    
    public String getNomLocalidad();

    public void setNomLocalidad(String nomLocalidad);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
