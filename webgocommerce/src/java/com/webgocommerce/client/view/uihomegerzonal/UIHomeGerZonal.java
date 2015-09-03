/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomegerzonal;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uigerzonal.UIGerZonalImpl;
import com.webgocommerce.client.view.uimantgerzonal.UIMantGerZonalImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeGerZonal extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UIGerZonalImpl uiGerZonal;    
    private UIMantGerZonalImpl uiMantGerZonal;

    public UIHomeGerZonal() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);
        pnlContenedor.add(container);
        uiGerZonal = new UIGerZonalImpl(this);   
        uiMantGerZonal=new UIMantGerZonalImpl(this);
        container.add(uiGerZonal);        
        container.add(uiMantGerZonal);        
        container.showWidget(0);
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UIGerZonalImpl getUiGerZonal() {
        return uiGerZonal;
    }

    public UIMantGerZonalImpl getUiMantGerZonal() {
        return uiMantGerZonal;
    }

    

    

}
