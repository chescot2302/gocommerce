/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.locator;

import com.gocommerce.server.model.beans.TipoCambio;
import com.google.web.bindery.requestfactory.shared.Locator;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public class LocatorTipoCambio extends Locator<TipoCambio,Date>{   

    @Override
    public TipoCambio create(Class<? extends TipoCambio> clazz) {
        return new TipoCambio();
    }

    @Override
    public TipoCambio find(Class<? extends TipoCambio> clazz, Date id) {
        return null;
    }

    @Override
    public Class<TipoCambio> getDomainType() {
        return TipoCambio.class;
    }

    @Override
    public Date getId(TipoCambio domainObject) {
        return domainObject.getFecha();
    }

    @Override
    public Class<Date> getIdType() {
        return Date.class;
    }

    @Override
    public Object getVersion(TipoCambio domainObject) {
        return domainObject.getVersion()==null?0:domainObject.getVersion();
    }
    
}
