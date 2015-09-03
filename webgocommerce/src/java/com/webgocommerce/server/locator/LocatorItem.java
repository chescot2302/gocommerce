/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Item;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorItem extends Locator<Item,String>{

    @Override
    public Item create(Class<? extends Item> clazz) {
        return new Item();
    }

    @Override
    public Item find(Class<? extends Item> clazz, String id) {
        return null;
    }

    @Override
    public Class<Item> getDomainType() {
        return Item.class;
    }

    @Override
    public String getId(Item domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Item domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
