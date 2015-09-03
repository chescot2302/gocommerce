/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Pais;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorPais extends Locator<Pais,String>{

    @Override
    public Pais create(Class<? extends Pais> clazz) {
        return new Pais();
    }

    @Override
    public Pais find(Class<? extends Pais> clazz, String id) {
        return null;
    }

    @Override
    public Class<Pais> getDomainType() {
        return Pais.class;
    }

    @Override
    public String getId(Pais domainObject) {
        return domainObject.getIdPais();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Pais domainObject) {
       return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
