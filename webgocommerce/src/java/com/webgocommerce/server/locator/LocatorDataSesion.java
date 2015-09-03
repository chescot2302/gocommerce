/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.DataSesion;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorDataSesion extends Locator<DataSesion,Integer>{

    @Override
    public DataSesion create(Class<? extends DataSesion> clazz) {
        return new DataSesion();
    }

    @Override
    public DataSesion find(Class<? extends DataSesion> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<DataSesion> getDomainType() {
        return DataSesion.class;
    }

    @Override
    public Integer getId(DataSesion domainObject) {
        return domainObject.getIdDataSesion();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(DataSesion domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
