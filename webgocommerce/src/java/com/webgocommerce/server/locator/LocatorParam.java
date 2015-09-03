/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Param;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorParam extends Locator<Param,Integer>{

    @Override
    public Param create(Class<? extends Param> clazz) {
        return new Param();
    }

    @Override
    public Param find(Class<? extends Param> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<Param> getDomainType() {
        return Param.class;
    }

    @Override
    public Integer getId(Param domainObject) {
        return domainObject.getIdParam();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(Param domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
