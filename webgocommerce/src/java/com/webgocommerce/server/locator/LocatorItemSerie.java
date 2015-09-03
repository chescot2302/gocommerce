/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.ItemSerie;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorItemSerie extends Locator<ItemSerie,String>{

    @Override
    public ItemSerie create(Class<? extends ItemSerie> clazz) {
        return new ItemSerie();
    }

    @Override
    public ItemSerie find(Class<? extends ItemSerie> clazz, String id) {
        return null;
    }

    @Override
    public Class<ItemSerie> getDomainType() {
        return ItemSerie.class;
    }

    @Override
    public String getId(ItemSerie domainObject) {
        return domainObject.getSerie();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(ItemSerie domainObject) {
       return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
