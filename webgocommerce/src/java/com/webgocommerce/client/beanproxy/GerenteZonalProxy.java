/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.GerenteZonal;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorGerenteZonal;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=GerenteZonal.class,locator=LocatorGerenteZonal.class)
public interface GerenteZonalProxy extends EntityProxy{
    public Integer getId();
    public void setId(Integer id);
    public String getDni();
    public void setDni(String dni);
    public String getNombres();
    public void setNombres(String nombres);
    public String getApellidos();
    public void setApellidos(String apellidos);
    public String getCorreo();
    public void setCorreo(String correo);
    public String getCelular();
    public void setCelular(String celular);
    public Long getVersion();
    public void setVersion(Long version);
    public String getOperacion();
    public void setOperacion(String operacion);
    public Integer getEstado();
    public void setEstado(Integer estado);
    public Date getFechaInc();
    public void setFechaInc(Date fechaInc);
    public Date getFechaCese();
    public void setFechaCese(Date fechaCese);
}
