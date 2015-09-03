/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomesupervisor;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uimantsupervisor.UIMantSupervisorImpl;
import com.webgocommerce.client.view.uisupervisor.UISupervisorImpl;
/**
 *
 * @author SISTEMAS
 */
public class UIHomeSupervisor extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UISupervisorImpl uiSupervisor;    
    private UIMantSupervisorImpl uiMantSupervisor;

    public UIHomeSupervisor() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);
        pnlContenedor.add(container);
        uiSupervisor = new UISupervisorImpl(this);   
        uiMantSupervisor=new UIMantSupervisorImpl(this);
        container.add(uiSupervisor);        
        container.add(uiMantSupervisor);        
        container.showWidget(0);
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UISupervisorImpl getUiSupervisor() {
        return uiSupervisor;
    }

   public UIMantSupervisorImpl getUiMantSupervisor() {
        return uiMantSupervisor;
    }

    

}
