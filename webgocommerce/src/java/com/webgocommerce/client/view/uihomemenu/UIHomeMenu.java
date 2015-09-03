/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomemenu;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uimantmenu.UIMantMenuImpl;
import com.webgocommerce.client.view.uimenu.UIMenuImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeMenu extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UIMenuImpl uiMenu;    
    private UIMantMenuImpl uiMantMenu;

    public UIHomeMenu() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);
        pnlContenedor.add(container);
        uiMenu = new UIMenuImpl(this);   
        //uiMantMenu=new UIMantMenuImpl(this);
        container.add(uiMenu);        
        //container.add(uiMantMenu);        
        container.showWidget(0);
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UIMenuImpl getUiMenu() {
        return uiMenu;
    }

   public UIMantMenuImpl getUiMantMenu() {
        return uiMantMenu;
    }

    

}
