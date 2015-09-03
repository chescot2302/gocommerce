/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Usuario;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorUsuario;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Usuario.class,locator=LocatorUsuario.class)
public interface UsuarioProxy extends EntityProxy{
    public String getId();

    public void setId(String id);

    public String getNombres();

    public void setNombres(String nombres);

    public String getNick();

    public void setNick(String nick);

    public String getClave();

    public void setClave(String clave);

    public Date getFechaIngreso();

    public void setFechaIngreso(Date fechaIngreso);

    public String getNivel();

    public void setNivel(String nivel);

    public String getIdPuntoEmision();

    public void setIdPuntoEmision(String idPuntoEmision);

    public String getIdGrupo();

    public void setIdGrupo(String idGrupo);

    public String getIdVendedor();

    public void setIdVendedor(String idVendedor);

    public String getIdAlmacen();

    public void setIdAlmacen(String idAlmacen);

    public String getIdDocumento();

    public void setIdDocumento(String idDocumento);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
    
    public Integer getIdBdUsuario();

    public void setIdBdUsuario(Integer idBdUsuario);
    
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
}
