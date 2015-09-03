/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Documento;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorDocumento;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Documento.class,locator=LocatorDocumento.class)
public interface DocumentoProxy extends EntityProxy{
    public String getId();

    public void setId(String id);

    public String getNombre();

    public void setNombre(String nombre);

    public Integer getRegCom();

    public void setRegCom(Integer regCom);

    public String getCodDocSnt();

    public void setCodDocSnt(String codDocSnt);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
