/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.ActivaBdUsuario;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorActivaBdUsuario extends Locator<ActivaBdUsuario,Integer> {

    @Override
    public ActivaBdUsuario create(Class<? extends ActivaBdUsuario> clazz) {
        return new ActivaBdUsuario();
    }

    @Override
    public ActivaBdUsuario find(Class<? extends ActivaBdUsuario> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<ActivaBdUsuario> getDomainType() {
        return ActivaBdUsuario.class;
    }

    @Override
    public Integer getId(ActivaBdUsuario domainObject) {
        return domainObject.getIdActivaBdUsuario();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(ActivaBdUsuario domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
