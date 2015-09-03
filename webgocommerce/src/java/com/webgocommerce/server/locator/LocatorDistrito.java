/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Distrito;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorDistrito extends Locator<Distrito,String>{

    @Override
    public Distrito create(Class<? extends Distrito> clazz) {
        return new Distrito();
    }

    @Override
    public Distrito find(Class<? extends Distrito> clazz, String id) {
        return null;
    }

    @Override
    public Class<Distrito> getDomainType() {
        return Distrito.class;
    }

    @Override
    public String getId(Distrito domainObject) {
        return domainObject.getIdDistrito();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Distrito domainObject) {
       return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
