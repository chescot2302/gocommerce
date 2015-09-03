/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Departamento;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorDepartamento extends Locator<Departamento,String>{

    @Override
    public Departamento create(Class<? extends Departamento> clazz) {
        return new Departamento();
    }

    @Override
    public Departamento find(Class<? extends Departamento> clazz, String id) {
        return null;
    }

    @Override
    public Class<Departamento> getDomainType() {
        return Departamento.class;
    }

    @Override
    public String getId(Departamento domainObject) {
        return domainObject.getIdDepartamento();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Departamento domainObject) {
       return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
