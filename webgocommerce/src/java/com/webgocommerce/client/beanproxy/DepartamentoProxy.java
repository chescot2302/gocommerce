/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Departamento;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorDepartamento;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Departamento.class,locator=LocatorDepartamento.class)
public interface DepartamentoProxy extends EntityProxy{
    public String getIdDepartamento();

    public void setIdDepartamento(String idDepartamento);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public String getIdPais();

    public void setIdPais(String idPais);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
