/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Familia;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorFamilia extends Locator<Familia,String>{   

    @Override
    public Familia create(Class<? extends Familia> clazz) {
        return new Familia();
    }

    @Override
    public Familia find(Class<? extends Familia> clazz, String id) {
        return null;
    }

    @Override
    public Class<Familia> getDomainType() {
        return Familia.class;
    }

    @Override
    public String getId(Familia domainObject) {
        return domainObject.getCodFam();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Familia domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
