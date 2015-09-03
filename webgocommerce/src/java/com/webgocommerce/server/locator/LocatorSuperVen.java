/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.SuperVen;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorSuperVen extends Locator<SuperVen,Integer>{

    @Override
    public SuperVen create(Class<? extends SuperVen> clazz) {
        return new SuperVen();
    }

    @Override
    public SuperVen find(Class<? extends SuperVen> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<SuperVen> getDomainType() {
        return SuperVen.class;
    }

    @Override
    public Integer getId(SuperVen domainObject) {
        return domainObject.getIdSuperVen();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(SuperVen domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
