/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Cliente;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorCliente extends Locator<Cliente,String>{

    @Override
    public Cliente create(Class<? extends Cliente> clazz) {
        return new Cliente();
    }

    @Override
    public Cliente find(Class<? extends Cliente> clazz, String id) {
        return null;
    }

    @Override
    public Class<Cliente> getDomainType() {
        return Cliente.class;
    }

    @Override
    public String getId(Cliente domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Cliente domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
