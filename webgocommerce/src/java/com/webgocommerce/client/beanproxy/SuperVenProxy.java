/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.SuperVen;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorSuperVen;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=SuperVen.class,locator=LocatorSuperVen.class)
public interface SuperVenProxy extends EntityProxy{
    public Integer getIdSuperVen();
    public void setIdSuperVen(Integer idSuperVen);
    public Integer getIdMesa();
    public void setIdMesa(Integer idMesa);
    public String getIdVendedor();
    public void setIdVendedor(String idVendedor);
    public Date getFechaIni();
    public void setFechaIni(Date fechaIni);
    public Date getFechaFin();
    public void setFechaFin(Date fechaFin);
    public String getEstado();
    public void setEstado(String estado);
    public String getDni();
    public void setDni(String dni);
    public String getNomConsultor();
    public void setNomConsultor(String nomConsultor);
    public Long getVersion();
    public void setVersion(Long version);
    public String getOperacion();
    public void setOperacion(String operacion);
}
