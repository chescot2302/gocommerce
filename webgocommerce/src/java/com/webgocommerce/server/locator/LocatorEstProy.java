/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.EstProy;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorEstProy extends Locator<EstProy,Integer>{

    @Override
    public EstProy create(Class<? extends EstProy> clazz) {
        return new EstProy();
    }

    @Override
    public EstProy find(Class<? extends EstProy> clazz, Integer id) {        
        return null;
    }

    @Override
    public Class<EstProy> getDomainType() {
        return EstProy.class;
    }

    @Override
    public Integer getId(EstProy domainObject) {
        return domainObject.getIdEstadoProy();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(EstProy domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
