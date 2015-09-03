/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.Usuario;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorUsuario extends Locator<Usuario,String>{   

    @Override
    public Usuario create(Class<? extends Usuario> clazz) {
        return new Usuario();
    }

    @Override
    public Usuario find(Class<? extends Usuario> clazz, String id) {
        return null;
    }

    @Override
    public Class<Usuario> getDomainType() {
        return Usuario.class;
    }

    @Override
    public String getId(Usuario domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<String> getIdType() {
        return String.class;
    }

    @Override
    public Object getVersion(Usuario domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
