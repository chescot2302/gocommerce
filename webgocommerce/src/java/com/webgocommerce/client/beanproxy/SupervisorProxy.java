/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Supervisor;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorSupervisor;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Supervisor.class,locator=LocatorSupervisor.class)
public interface SupervisorProxy extends EntityProxy{
    public Integer getIdSupervisor();

    public void setIdSupervisor(Integer idSupervisor);

    public String getDni();

    public void setDni(String dni);

    public String getNombres();

    public void setNombres(String nombres);

    public String getApellidos();

    public void setApellidos(String apellidos);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
    
    public String getIdPtoEmision();

    public void setIdPtoEmision(String idPtoEmision);

    public Integer getIdMesa();

    public void setIdMesa(Integer idMesa);

    public String getNomMesa();

    public void setNomMesa(String nomMesa);

    public String getNomPtoEmision();

    public void setNomPtoEmision(String nomPtoEmision);

    public String getIdTienda();

    public void setIdTienda(String idTienda);

    public String getNomTienda();

    public void setNomTienda(String nomTienda);

    public String getIdSucursal();

    public void setIdSucursal(String idSucursal);

    public String getNomSucursal();

    public void setNomSucursal(String nomSucursal);

    public String getIdLocalidad();

    public void setIdLocalidad(String idLocalidad);

    public String getNomLocalidad();

    public void setNomLocalidad(String nomLocalidad);

    public Integer getEstado();

    public void setEstado(Integer estado);

    public String getCodigoAlterno();

    public void setCodigoAlterno(String codigoAlterno);
    
    public String getCanal();

    public void setCanal(String canal);

    public String getCorreo();

    public void setCorreo(String correo);

    public String getCelular();

    public void setCelular(String celular);

    public Date getFechaInc();

    public void setFechaInc(Date fechaInc);

    public Date getFechaCese();

    public void setFechaCese(Date fechaCese);
        
}
