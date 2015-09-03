/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.MenuBar;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 *
 * @author SISTEMAS
 */
public class LocatorMenuBar extends Locator<MenuBar,Integer>{   

    @Override
    public MenuBar create(Class<? extends MenuBar> clazz) {
        return new MenuBar();
    }

    @Override
    public MenuBar find(Class<? extends MenuBar> clazz, Integer id) {
        return null;
    }

    @Override
    public Class<MenuBar> getDomainType() {
        return MenuBar.class;
    }

    @Override
    public Integer getId(MenuBar domainObject) {
        return domainObject.getIdMenuBar();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(MenuBar domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
