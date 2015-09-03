/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Pais;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorPais;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Pais.class,locator=LocatorPais.class)
public interface PaisProxy extends EntityProxy{
    public String getIdPais();

    public void setIdPais(String idPais);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
