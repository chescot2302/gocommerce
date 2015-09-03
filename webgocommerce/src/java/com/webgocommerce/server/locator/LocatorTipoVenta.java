/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.TipoVenta;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorTipoVenta  extends Locator<TipoVenta,String>{   

    @Override
    public TipoVenta create(Class<? extends TipoVenta> clazz) {
        return new TipoVenta();
    }

    @Override
    public TipoVenta find(Class<? extends TipoVenta> clazz, String id) {
        return null;
    }

    @Override
    public Class<TipoVenta> getDomainType() {
        return TipoVenta.class;
    }

    @Override
    public String getId(TipoVenta domainObject) {
        return domainObject.getIdTipoVenta();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(TipoVenta domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
