/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.BdEmpresa;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorBdEmpresa extends Locator<BdEmpresa,Integer>{

    @Override
    public BdEmpresa create(Class<? extends BdEmpresa> clazz) {
        return new BdEmpresa();
    }

    @Override
    public BdEmpresa find(Class<? extends BdEmpresa> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<BdEmpresa> getDomainType() {
        return BdEmpresa.class;
    }

    @Override
    public Integer getId(BdEmpresa domainObject) {
        return domainObject.getIdBdEmpresa();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(BdEmpresa domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
