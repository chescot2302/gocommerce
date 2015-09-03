/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Almacen;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorAlmacen extends Locator<Almacen,String>{

    @Override
    public Almacen create(Class<? extends Almacen> clazz) {
        return new Almacen();
    }

    @Override
    public Almacen find(Class<? extends Almacen> clazz, String id) {
        return null;
    }

    @Override
    public Class<Almacen> getDomainType() {
        return Almacen.class;
    }

    @Override
    public String getId(Almacen domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Almacen domainObject) {
       return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
