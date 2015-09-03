/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Mesa;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorMesa;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Mesa.class,locator=LocatorMesa.class)
public interface MesaProxy extends EntityProxy{
    public Integer getIdMesa();

    public void setIdMesa(Integer idMesa);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
    
    public Integer getIdCoordinador();

    public void setIdCoordinador(Integer idCoordinador);

    public Integer getIdGerenteZonal();

    public void setIdGerenteZonal(Integer idGerenteZonal);

    public Integer getIdSupervisor();

    public void setIdSupervisor(Integer idSupervisor);

    public String getIdSucursal();

    public void setIdSucursal(String idSucursal);

    public String getEstado();

    public void setEstado(String estado);

    public String getNomCoordinador();

    public void setNomCoordinador(String nomCoordinador);

    public String getNomGerenteZonal();

    public void setNomGerenteZonal(String nomGerenteZonal);

    public String getNomSupervisor();

    public void setNomSupervisor(String nomSupervisor);

    public String getNomSucursal();

    public void setNomSucursal(String nomSucursal);
    
    public Date getFechaIni();

    public void setFechaIni(Date fechaIni);

    public Date getFechaFin();

    public void setFechaFin(Date fechaFin);
    
    public Integer getIdSuperVen();

    public void setIdSuperVen(Integer idSuperVen);
    
    public String getIdVendedor();

    public void setIdVendedor(String idVendedor);
}
