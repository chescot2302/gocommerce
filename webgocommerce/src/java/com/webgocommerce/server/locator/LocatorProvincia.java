/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Provincia;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorProvincia extends Locator<Provincia,String>{

    @Override
    public Provincia create(Class<? extends Provincia> clazz) {
        return new Provincia();
    }

    @Override
    public Provincia find(Class<? extends Provincia> clazz, String id) {
        return null;
    }

    @Override
    public Class<Provincia> getDomainType() {
        return Provincia.class;
    }

    @Override
    public String getId(Provincia domainObject) {
        return domainObject.getIdProvincia();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Provincia domainObject) {
       return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
