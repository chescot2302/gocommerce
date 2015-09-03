/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.ClienteCallCenter;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorClienteCallCenter;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=ClienteCallCenter.class,locator=LocatorClienteCallCenter.class)
public interface ClienteCallCenterProxy extends EntityProxy{
    public Integer getId();

    public void setId(Integer id);

    public String getDni();

    public void setDni(String dni);

    public String getNombres();

    public void setNombres(String nombres);

    public String getApellidos();

    public void setApellidos(String apellidos);

    public String getObservacion();

    public void setObservacion(String observacion);

    public String getEstado();

    public void setEstado(String estado);

    public Date getFecha();

    public void setFecha(Date fecha);

    public String getIdUsuario();

    public void setIdUsuario(String idUsuario);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
