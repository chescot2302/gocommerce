/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.ListaPrecio;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorListaPrecio extends Locator<ListaPrecio,Integer>{

    @Override
    public ListaPrecio create(Class<? extends ListaPrecio> clazz) {
        return new ListaPrecio();
    }

    @Override
    public ListaPrecio find(Class<? extends ListaPrecio> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<ListaPrecio> getDomainType() {
        return ListaPrecio.class;
    }

    @Override
    public Integer getId(ListaPrecio domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(ListaPrecio domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
