/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomevendedor;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uimantvendedor.UIMantVendedorImpl;
import com.webgocommerce.client.view.uivendedor.UIVendedorImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeVendedor extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UIVendedorImpl uiVendedor;
    private UIMantVendedorImpl uiMantVendedor;
    

    public UIHomeVendedor() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);        
        pnlContenedor.add(container);
        uiVendedor=new UIVendedorImpl(this);
        uiMantVendedor=new UIMantVendedorImpl(this);
        container.add(uiVendedor);
        container.add(uiMantVendedor);
        container.showWidget(0);        
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UIVendedorImpl getUiVendedor() {
        return uiVendedor;
    }

    public UIMantVendedorImpl getUiMantVendedor() {
        return uiMantVendedor;
    }
    
    
    

}
