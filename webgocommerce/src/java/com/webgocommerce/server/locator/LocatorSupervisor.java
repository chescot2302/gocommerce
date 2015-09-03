/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Supervisor;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorSupervisor extends Locator<Supervisor,Integer>{

    @Override
    public Supervisor create(Class<? extends Supervisor> clazz) {
        return new Supervisor();
    }

    @Override
    public Supervisor find(Class<? extends Supervisor> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<Supervisor> getDomainType() {
        return Supervisor.class;
    }

    @Override
    public Integer getId(Supervisor domainObject) {
        return domainObject.getIdSupervisor();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(Supervisor domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
