/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomesucursal;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uimantsucursal.UIMantSucursalImpl;
import com.webgocommerce.client.view.uisucursal.UISucursalImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeSucursal  extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UISucursalImpl uiSucursal;    
    private UIMantSucursalImpl uiMantSucursal;

    public UIHomeSucursal() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);
        pnlContenedor.add(container);
        uiSucursal = new UISucursalImpl(this);   
        uiMantSucursal=new UIMantSucursalImpl(this);
        container.add(uiSucursal);        
        container.add(uiMantSucursal);        
        container.showWidget(0);
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UISucursalImpl getUiSucursal() {
        return uiSucursal;
    }

   public UIMantSucursalImpl getUiMantSucursal() {
        return uiMantSucursal;
    }

    

}
