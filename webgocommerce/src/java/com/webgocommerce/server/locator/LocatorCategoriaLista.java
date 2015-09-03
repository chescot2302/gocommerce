/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;


import com.gocommerce.server.model.beans.CategoriaLista;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorCategoriaLista extends Locator<CategoriaLista,Integer>{

    @Override
    public CategoriaLista create(Class<? extends CategoriaLista> clazz) {
        return new CategoriaLista();
    }

    @Override
    public CategoriaLista find(Class<? extends CategoriaLista> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<CategoriaLista> getDomainType() {
        return CategoriaLista.class;
    }

    @Override
    public Integer getId(CategoriaLista domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(CategoriaLista domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
