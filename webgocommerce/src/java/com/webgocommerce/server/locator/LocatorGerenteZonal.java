/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.GerenteZonal;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorGerenteZonal extends Locator<GerenteZonal,Integer>{

    @Override
    public GerenteZonal create(Class<? extends GerenteZonal> clazz) {
        return new GerenteZonal();
    }

    @Override
    public GerenteZonal find(Class<? extends GerenteZonal> clazz, Integer id) {        
        return null;
    }

    @Override
    public Class<GerenteZonal> getDomainType() {
        return GerenteZonal.class;
    }

    @Override
    public Integer getId(GerenteZonal domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(GerenteZonal domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
