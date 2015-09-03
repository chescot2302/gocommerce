/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.BdUsuario;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorBdUsuario extends Locator<BdUsuario,Integer>{

    @Override
    public BdUsuario create(Class<? extends BdUsuario> clazz) {
        return new BdUsuario();
    }

    @Override
    public BdUsuario find(Class<? extends BdUsuario> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<BdUsuario> getDomainType() {
        return BdUsuario.class;
    }

    @Override
    public Integer getId(BdUsuario domainObject) {
        return domainObject.getIdBdUsuario();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(BdUsuario domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
