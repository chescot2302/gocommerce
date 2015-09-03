/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomeclientecall;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uiclientecall.UIClienteCallImpl;
import com.webgocommerce.client.view.uimantclientecall.UIMantClienteCallImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeClienteCall extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UIClienteCallImpl uiClienteCall;    
    private UIMantClienteCallImpl uiMantClienteCall;

    public UIHomeClienteCall() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);
        pnlContenedor.add(container);
        uiClienteCall = new UIClienteCallImpl(this);   
        uiMantClienteCall=new UIMantClienteCallImpl(this);
        container.add(uiClienteCall);        
        container.add(uiMantClienteCall);        
        container.showWidget(0);
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UIClienteCallImpl getUiClienteCall() {
        return uiClienteCall;
    }

   public UIMantClienteCallImpl getUiMantClienteCall() {
        return uiMantClienteCall;
    }

    

}
