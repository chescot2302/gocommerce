/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Correlativo;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorCorrelativo;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Correlativo.class,locator=LocatorCorrelativo.class)
public interface CorrelativoProxy  extends EntityProxy{
    public String getIdDocumento();

    public void setIdDocumento(String idDocumento);

    public String getIdPuntoEmision();

    public void setIdPuntoEmision(String idPuntoEmision);

    public String getNombreDocumento();

    public void setNombreDocumento(String nombreDocumento);

    public String getNroInicio();

    public void setNroInicio(String nroInicio);

    public String getNroFin();

    public void setNroFin(String nroFin);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
    
    public String getPreimpreso();

    public void setPreimpreso(String preimpreso);

    public String getSerie();

    public void setSerie(String serie);
    
    public String getDocSerie();

    public void setDocSerie(String docSerie);
}
