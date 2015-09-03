/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.EstProy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorEstProy;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=EstProy.class,locator=LocatorEstProy.class)
public interface EstProyProxy extends EntityProxy{
    public Integer getIdEstadoProy();

    public void setIdEstadoProy(Integer idEstadoProy);

    public Date getFechaInc();

    public void setFechaInc(Date fechaInc);

    public Date getFechaCese();

    public void setFechaCese(Date FechaCese);

    public Integer getEstado();

    public void setEstado(Integer estado);

    public String getEstadoActual();

    public void setEstadoActual(String estadoActual);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
    
    public String getTipoDoc();

    public void setTipoDoc(String tipoDoc);

    public String getCorrelativo();

    public void setCorrelativo(String correlativo);
    
    public String getObservacion();

    public void setObservacion(String observacion);
}
