/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.BdEmpresa;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorBdEmpresa;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=BdEmpresa.class,locator=LocatorBdEmpresa.class)
public interface BdEmpresaProxy extends EntityProxy{
    public Integer getIdBdEmpresa();

    public void setIdBdEmpresa(Integer idBdEmpresa);

    public String getNombre();

    public void setNombre(String nombre);

    public String getSchema();

    public void setSchema(String schema);

    public String getUserPrincipal();

    public void setUserPrincipal(String userPrincipal);

    public String getClavePrincipal();

    public void setClavePrincipal(String clavePrincipal);

    public String getEstadoActivacion();

    public void setEstadoActivacion(String estadoActivacion);

    public String getIpHost();

    public void setIpHost(String IpHost);

    public Integer getPuerto();

    public void setPuerto(Integer puerto);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);

    public List<BdUsuarioProxy> getListBdUsuario();

    public void setListBdUsuario(List<BdUsuarioProxy> listBdUsuario);
    
    public String getClassName();
}
