/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Documento;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorDocumento  extends Locator<Documento,String>{

    @Override
    public Documento create(Class<? extends Documento> clazz) {
        return new Documento();
    }

    @Override
    public Documento find(Class<? extends Documento> clazz, String id) {
        return null;
    }

    @Override
    public Class<Documento> getDomainType() {
        return Documento.class;
    }

    @Override
    public String getId(Documento domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Documento domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
