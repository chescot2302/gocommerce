/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Localidad;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorLocalidad extends Locator<Localidad,Integer>{

    @Override
    public Localidad create(Class<? extends Localidad> clazz) {
        return new Localidad();
    }

    @Override
    public Localidad find(Class<? extends Localidad> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<Localidad> getDomainType() {
        return Localidad.class;
    }

    @Override
    public Integer getId(Localidad domainObject) {
        return domainObject.getIdLocalidad();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(Localidad domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
