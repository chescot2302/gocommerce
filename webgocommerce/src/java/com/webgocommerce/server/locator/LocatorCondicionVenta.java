/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.CondicionVenta;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorCondicionVenta extends Locator<CondicionVenta,String>{
     @Override
    public CondicionVenta create(Class<? extends CondicionVenta> clazz) {
        return new CondicionVenta();
    }

    @Override
    public CondicionVenta find(Class<? extends CondicionVenta> clazz, String id) {
        return null;
    }

    @Override
    public Class<CondicionVenta> getDomainType() {
        return CondicionVenta.class;
    }

    @Override
    public String getId(CondicionVenta domainObject) {
        return domainObject.getIdCondicionVenta();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(CondicionVenta domainObject) {
       return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
