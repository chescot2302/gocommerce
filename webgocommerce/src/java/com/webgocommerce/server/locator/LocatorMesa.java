/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Mesa;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorMesa extends Locator<Mesa,Integer>{

    @Override
    public Mesa create(Class<? extends Mesa> clazz) {
        return new Mesa();
    }

    @Override
    public Mesa find(Class<? extends Mesa> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<Mesa> getDomainType() {
        return Mesa.class;
    }

    @Override
    public Integer getId(Mesa domainObject) {
        return domainObject.getIdMesa();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(Mesa domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
