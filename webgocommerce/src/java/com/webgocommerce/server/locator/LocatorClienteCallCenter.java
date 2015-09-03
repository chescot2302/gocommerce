/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.ClienteCallCenter;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorClienteCallCenter extends Locator<ClienteCallCenter,Integer>{

    @Override
    public ClienteCallCenter create(Class<? extends ClienteCallCenter> clazz) {
        return new ClienteCallCenter();
    }

    @Override
    public ClienteCallCenter find(Class<? extends ClienteCallCenter> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<ClienteCallCenter> getDomainType() {
        return ClienteCallCenter.class;
    }

    @Override
    public Integer getId(ClienteCallCenter domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(ClienteCallCenter domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
