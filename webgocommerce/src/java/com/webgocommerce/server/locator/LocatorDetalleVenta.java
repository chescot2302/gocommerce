/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.DetalleVenta;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorDetalleVenta extends Locator<DetalleVenta,String>{

    @Override
    public DetalleVenta create(Class<? extends DetalleVenta> clazz) {
        return new DetalleVenta();
    }

    @Override
    public DetalleVenta find(Class<? extends DetalleVenta> clazz, String id) {
        return null;
    }

    @Override
    public Class<DetalleVenta> getDomainType() {
        return DetalleVenta.class;
    }

    @Override
    public String getId(DetalleVenta domainObject) {
        return domainObject.getIdDetalleVenta();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(DetalleVenta domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
