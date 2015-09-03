/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.UsuarioCorrelativo;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorUsuarioCorrelativo extends Locator<UsuarioCorrelativo,Integer>{   

    @Override
    public UsuarioCorrelativo create(Class<? extends UsuarioCorrelativo> clazz) {
        return new UsuarioCorrelativo();
    }

    @Override
    public UsuarioCorrelativo find(Class<? extends UsuarioCorrelativo> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<UsuarioCorrelativo> getDomainType() {
        return UsuarioCorrelativo.class;
    }

    @Override
    public Integer getId(UsuarioCorrelativo domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(UsuarioCorrelativo domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
