/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.InitParam;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorInitParam extends Locator<InitParam,Integer>{

    @Override
    public InitParam create(Class<? extends InitParam> clazz) {
        return new InitParam();
    }

    @Override
    public InitParam find(Class<? extends InitParam> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<InitParam> getDomainType() {
        return InitParam.class;
    }

    @Override
    public Integer getId(InitParam domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(InitParam domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
