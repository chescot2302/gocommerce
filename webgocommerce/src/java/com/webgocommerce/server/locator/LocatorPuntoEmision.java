/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.PuntoEmision;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorPuntoEmision extends Locator<PuntoEmision,String>{  

    @Override
    public PuntoEmision create(Class<? extends PuntoEmision> clazz) {
        return new PuntoEmision();
    }

    @Override
    public PuntoEmision find(Class<? extends PuntoEmision> clazz, String id) {
        return null;
    }

    @Override
    public Class<PuntoEmision> getDomainType() {
        return PuntoEmision.class;
    }

    @Override
    public String getId(PuntoEmision domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(PuntoEmision domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
