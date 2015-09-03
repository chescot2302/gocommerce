/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Param;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorParam;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Param.class,locator=LocatorParam.class)
public interface ParamProxy extends EntityProxy{
    public Integer getIdParam();

    public void setIdParam(Integer idParam);

    public String getAbreviatura();

    public void setAbreviatura(String abreviatura);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public String getValor();

    public void setValor(String valor);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
