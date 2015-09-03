/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.ItemPlan;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorItemPlan extends Locator<ItemPlan,Integer>{

    @Override
    public ItemPlan create(Class<? extends ItemPlan> clazz) {
        return new ItemPlan();
    }

    @Override
    public ItemPlan find(Class<? extends ItemPlan> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<ItemPlan> getDomainType() {
        return ItemPlan.class;
    }

    @Override
    public Integer getId(ItemPlan domainObject) {
        return domainObject.getIdEquipPlan();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(ItemPlan domainObject) {
       return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
