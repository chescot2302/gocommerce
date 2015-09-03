/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.ItemSerie;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorItemSerie;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=ItemSerie.class,locator=LocatorItemSerie.class)
public interface ItemSerieProxy extends EntityProxy{
    public Integer getItem();

    public void setItem(Integer item);

    public String getCodi();

    public void setCodi(String codi);

    public String getSerie();

    public void setSerie(String serie);

    public String getCdocui();

    public void setCdocui(String cdocui);

    public String getNdocui();

    public void setNdocui(String ndocui);

    public String getCdocus();

    public void setCdocus(String cdocus);

    public String getNdocus();

    public void setNdocus(String ndocus);

    public String getFlag();

    public void setFlag(String flag);

    public String getCodpro();

    public void setCodpro(String codpro);

    public String getCdocusp();

    public void setCdocusp(String cdocusp);

    public String getNdocusp();

    public void setNdocusp(String ndocusp);

    public String getCdocuip();

    public void setCdocuip(String cdocuip);

    public String getNdocuip();

    public void setNdocuip(String ndocuip);

    public String getCodAlm();

    public void setCodAlm(String codAlm);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
    
    public String getCdocu();

    public void setCdocu(String cdocu);
        
    public String getNdocu();

    public void setNdocu(String ndocu);
    
    public String getTelefono();

    public void setTelefono(String telefono);
    
    public Boolean isSeleccionado();

    public void setSeleccionado(Boolean seleccionado);
}
