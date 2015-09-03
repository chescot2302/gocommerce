/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.BdUsuario;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorBdUsuario;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=BdUsuario.class,locator=LocatorBdUsuario.class)
public interface BdUsuarioProxy extends EntityProxy{
    public Integer getIdBdUsuario();

    public void setIdBdUsuario(Integer idBdUsuario);

    public String getUsuarioBd();

    public void setUsuarioBd(String usuarioBd);

    public String getClaveBd();

    public void setClaveBd(String claveBd);

    public String getSchema();

    public void setSchema(String schema);

    public String getCorreo();

    public void setCorreo(String correo);

    public String getClave();

    public void setClave(String clave);

    public String getNivel();

    public void setNivel(String nivel);

    public BdEmpresaProxy getBeanBdEmpresa();

    public void setBeanBdEmpresa(BdEmpresaProxy beanBdEmpresa);

    public String getEstadoActivacion();

    public void setEstadoActivacion(String estadoActivacion);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);

    public List<ActivaBdUsuarioProxy> getListActivaBdUsuario();

    public void setListActivaBdUsuario(List<ActivaBdUsuarioProxy> listActivaBdUsuario);
    
    public Integer getIdBdEmpresa();

    public void setIdBdEmpresa(Integer idBdEmpresa);
}
