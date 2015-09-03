/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Localidad;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorLocalidad;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Localidad.class,locator=LocatorLocalidad.class)
public interface LocalidadProxy extends EntityProxy{
    public Integer getIdLocalidad();

    public void setIdLocalidad(Integer idLocalidad);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
