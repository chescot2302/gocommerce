/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Tienda;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorTienda extends Locator<Tienda,String>{   

    @Override
    public Tienda create(Class<? extends Tienda> clazz) {
        return new Tienda();
    }

    @Override
    public Tienda find(Class<? extends Tienda> clazz, String id) {
        return null;
    }

    @Override
    public Class<Tienda> getDomainType() {
        return Tienda.class;        
    }

    @Override
    public String getId(Tienda domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Tienda domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
