/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.ListaPrecio;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorListaPrecio;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=ListaPrecio.class,locator=LocatorListaPrecio.class)
public interface ListaPrecioProxy extends EntityProxy{
     public Integer getId();

    public void setId(Integer id);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public String getCondicion();

    public void setCondicion(String condicion);

    public Double getPagoMensual();

    public void setPagoMensual(Double pagoMensual);

    public Integer getVigencia();

    public void setVigencia(Integer vigencia);

    public Date getFechaIni();

    public void setFechaIni(Date fechaIni);

    public Date getFechaFin();

    public void setFechaFin(Date fechaFin);

    public String getEstadoActiva();

    public void setEstadoActiva(String estadoActiva);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);

    public CategoriaListaProxy getBeanCategoriaLista();

    public void setBeanCategoriaLista(CategoriaListaProxy beanCategoriaLista);
    
    public Integer getIdCategoriaLista();

    public void setIdCategoriaLista(Integer idCategoriaLista);
    
    public String getCanal();

    public void setCanal(String canal);
}
