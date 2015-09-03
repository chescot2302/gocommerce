/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.PrecioItem;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorPrecioItem extends Locator<PrecioItem,Integer>{   

    @Override
    public PrecioItem create(Class<? extends PrecioItem> clazz) {
        return new PrecioItem();
    }

    @Override
    public PrecioItem find(Class<? extends PrecioItem> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<PrecioItem> getDomainType() {
        return PrecioItem.class;
    }

    @Override
    public Integer getId(PrecioItem domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(PrecioItem domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
