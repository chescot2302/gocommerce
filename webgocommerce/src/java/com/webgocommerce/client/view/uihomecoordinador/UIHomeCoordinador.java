/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomecoordinador;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uicoordinador.UICoordinadorImpl;
import com.webgocommerce.client.view.uimantcoordinador.UIMantCoordinadorImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeCoordinador extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UICoordinadorImpl uiCoordinador;    
    private UIMantCoordinadorImpl uiMantCoordinador;

    public UIHomeCoordinador() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);
        pnlContenedor.add(container);
        uiCoordinador = new UICoordinadorImpl(this);   
        uiMantCoordinador=new UIMantCoordinadorImpl(this);
        container.add(uiCoordinador);        
        container.add(uiMantCoordinador);        
        container.showWidget(0);
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UICoordinadorImpl getUiCoordinador() {
        return uiCoordinador;
    }

    public UIMantCoordinadorImpl getUiMantCoordinador() {
        return uiMantCoordinador;
    }

    

    

}
