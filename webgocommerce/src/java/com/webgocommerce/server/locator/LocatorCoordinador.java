/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Coordinador;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorCoordinador extends Locator<Coordinador,Integer>{

    @Override
    public Coordinador create(Class<? extends Coordinador> clazz) {
        return new Coordinador();
    }

    @Override
    public Coordinador find(Class<? extends Coordinador> clazz, Integer id) {        
        return null;
    }

    @Override
    public Class<Coordinador> getDomainType() {
        return Coordinador.class;
    }

    @Override
    public Integer getId(Coordinador domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(Coordinador domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
