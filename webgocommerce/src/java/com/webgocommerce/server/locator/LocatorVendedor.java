/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Vendedor;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorVendedor extends Locator<Vendedor,String>{

    @Override
    public Vendedor create(Class<? extends Vendedor> clazz) {
        return new Vendedor();
    }

    @Override
    public Vendedor find(Class<? extends Vendedor> clazz, String id) {
        return null;
    }

    @Override
    public Class<Vendedor> getDomainType() {
        return Vendedor.class;
    }

    @Override
    public String getId(Vendedor domainObject) {
        return domainObject.getIdVendedor();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Vendedor domainObject) {
       return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
