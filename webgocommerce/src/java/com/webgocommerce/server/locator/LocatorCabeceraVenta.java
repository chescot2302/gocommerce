/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.CabeceraVenta;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorCabeceraVenta extends Locator<CabeceraVenta,String>{

    @Override
    public CabeceraVenta create(Class<? extends CabeceraVenta> clazz) {
        return new CabeceraVenta();
        
    }

    @Override
    public CabeceraVenta find(Class<? extends CabeceraVenta> clazz, String id) {
        return null;
    }

    @Override
    public Class<CabeceraVenta> getDomainType() {
        return CabeceraVenta.class;
    }

    @Override
    public String getId(CabeceraVenta domainObject) {
        return domainObject.getIdCabecera();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(CabeceraVenta domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
