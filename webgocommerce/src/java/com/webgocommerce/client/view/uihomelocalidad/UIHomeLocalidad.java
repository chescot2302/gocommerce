/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomelocalidad;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uilocalidad.UILocalidadImpl;
import com.webgocommerce.client.view.uimantlocalidad.UIMantLocalidadImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeLocalidad  extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UILocalidadImpl uiLocalidad;    
    private UIMantLocalidadImpl uiMantLocalidad;

    public UIHomeLocalidad() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);
        pnlContenedor.add(container);
        uiLocalidad = new UILocalidadImpl(this);   
        uiMantLocalidad=new UIMantLocalidadImpl(this);
        container.add(uiLocalidad);        
        container.add(uiMantLocalidad);        
        container.showWidget(0);
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UILocalidadImpl getUiLocalidad() {
        return uiLocalidad;
    }

   public UIMantLocalidadImpl getUiMantLocalidad() {
        return uiMantLocalidad;
    }

    

}
