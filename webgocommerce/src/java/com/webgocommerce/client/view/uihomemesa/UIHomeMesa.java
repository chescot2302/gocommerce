/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomemesa;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uiaddconsultor.UIAddConsultorImpl;
import com.webgocommerce.client.view.uieditconsultor.UIEditConsultorImpl;
import com.webgocommerce.client.view.uimantmesa.UIMantMesaImpl;
import com.webgocommerce.client.view.uimesa.UIMesaImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeMesa extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UIMesaImpl uiMesa;    
    private UIMantMesaImpl uiMantMesa;
    private UIEditConsultorImpl uiEditConsultor;
    private UIAddConsultorImpl uiAddConsultor;

    public UIHomeMesa() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);
        pnlContenedor.add(container);
        uiMesa = new UIMesaImpl(this);   
        uiMantMesa=new UIMantMesaImpl(this);
        uiEditConsultor=new UIEditConsultorImpl(this);
        uiAddConsultor=new UIAddConsultorImpl(this);
        container.add(uiMesa);        
        container.add(uiMantMesa);        
        container.add(uiEditConsultor);        
        container.add(uiAddConsultor);        
        container.showWidget(0);
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UIMesaImpl getUiMesa() {
        return uiMesa;
    }

   public UIMantMesaImpl getUiMantMesa() {
        return uiMantMesa;
    }

    public UIEditConsultorImpl getUiEditConsultor() {
        return uiEditConsultor;
    }

    public UIAddConsultorImpl getUiAddConsultor() {
        return uiAddConsultor;
    }

    

    

}
