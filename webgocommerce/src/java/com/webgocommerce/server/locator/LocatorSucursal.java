/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Sucursal;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorSucursal extends Locator<Sucursal,String>{   

    @Override
    public Sucursal create(Class<? extends Sucursal> clazz) {
        return new Sucursal();
    }

    @Override
    public Sucursal find(Class<? extends Sucursal> clazz, String id) {
        return null;
    }

    @Override
    public Class<Sucursal> getDomainType() {
        return Sucursal.class;
    }

    @Override
    public String getId(Sucursal domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Sucursal domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
