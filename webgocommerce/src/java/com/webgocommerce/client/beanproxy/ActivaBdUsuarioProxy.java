/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.ActivaBdUsuario;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorActivaBdUsuario;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=ActivaBdUsuario.class,locator=LocatorActivaBdUsuario.class)
public interface ActivaBdUsuarioProxy extends EntityProxy{
    public Integer getIdActivaBdUsuario();

    public void setIdActivaBdUsuario(Integer idActivaBdUsuario);

    public Date getFechaIni();

    public void setFechaIni(Date fechaIni);

    public Date getFechaFin();

    public void setFechaFin(Date fechaFin);

    public String getEstadoActivacion();

    public void setEstadoActivacion(String estadoActivacion);

    public BdUsuarioProxy getBeanBdUsuario();

    public void setBeanBdUsuario(BdUsuarioProxy beanBdUsuario);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
