/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Almacen;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorAlmacen;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Almacen.class,locator=LocatorAlmacen.class)
public interface AlmacenProxy extends EntityProxy{
    public String getId();

    public void setId(String id);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public Double getStockItemAlm();

    public void setStockItemAlm(Double stockItemAlm);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
