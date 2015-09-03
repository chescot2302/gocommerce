/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.CategoriaLista;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorCategoriaLista;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=CategoriaLista.class,locator=LocatorCategoriaLista.class)
public interface CategoriaListaProxy extends EntityProxy{
    public Integer getId();

    public void setId(Integer id);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);

    public List<ListaPrecioProxy> getListaPrecio();

    public void setListaPrecio(List<ListaPrecioProxy> listaPrecio);
}
