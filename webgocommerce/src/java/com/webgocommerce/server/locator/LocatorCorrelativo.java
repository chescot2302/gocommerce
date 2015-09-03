/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Correlativo;
import com.gocommerce.server.model.beans.CorrelativoKey;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorCorrelativo extends Locator<Correlativo,String>{   

    @Override
    public Correlativo create(Class<? extends Correlativo> clazz) {
        return new Correlativo();
    }

    @Override
    public Correlativo find(Class<? extends Correlativo> clazz, String id) {
        return null;
    }

    @Override
    public Class<Correlativo> getDomainType() {
        return Correlativo.class;
    }

    @Override
    public String getId(Correlativo domainObject) {
        CorrelativoKey key=new CorrelativoKey(domainObject.getClass().getName()+"::"+domainObject.getIdDocumento()+"::"+domainObject.getIdPuntoEmision());
        return key.toString();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Correlativo domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
