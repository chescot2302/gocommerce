/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Provincia;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorProvincia;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Provincia.class,locator=LocatorProvincia.class)
public interface ProvinciaProxy extends EntityProxy{
    public String getIdProvincia();

    public void setIdProvincia(String idProvincia);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public String getIdPais();

    public void setIdPais(String idPais);

    public String getIdDepartamento();

    public void setIdDepartamento(String idDepartamento);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
