/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Vendedor;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorVendedor;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Vendedor.class,locator=LocatorVendedor.class)
public interface VendedorProxy extends EntityProxy{
    public String getNomConsultor();

    public void setNomConsultor(String nomConsultor);
    
    public Integer getIdMesa();

    public void setIdMesa(Integer idMesa);

    public String getNomMesa();

    public void setNomMesa(String nomMesa);
    
    public String getCodigoAlterno();

    public void setCodigoAlterno(String codigoAlterno);
    
    public Integer getEstado();

    public void setEstado(Integer estado);
    
    public String getIdVendedor();

    public void setIdVendedor(String idVendedor);

    public String getNomVendedor();

    public void setNomVendedor(String nomVendedor);

    public String getPrimerNombre();

    public void setPrimerNombre(String primerNombre);

    public String getSegundoNombre();

    public void setSegundoNombre(String segundoNombre);

    public String getApellidoPaterno();

    public void setApellidoPaterno(String apellidoPaterno);

    public String getApellidoMaterno();

    public void setApellidoMaterno(String apellidoMaterno);

    public String getIdPtoEmision();

    public void setIdPtoEmision(String idPtoEmision);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);

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
    
    public String getDni();

    public void setDni(String dni);
    
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
