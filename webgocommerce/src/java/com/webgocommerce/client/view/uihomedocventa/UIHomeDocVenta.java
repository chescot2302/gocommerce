/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomedocventa;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uidocventa.UIDocVentaImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeDocVenta extends Composite {
    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UIDocVentaImpl uiDocVenta;
    
    public UIHomeDocVenta() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);        
        pnlContenedor.add(container);
        uiDocVenta=new UIDocVentaImpl(this);
        container.add(uiDocVenta);
        container.showWidget(0);        
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UIDocVentaImpl getUiDocVenta() {
        return uiDocVenta;
    }    

}
