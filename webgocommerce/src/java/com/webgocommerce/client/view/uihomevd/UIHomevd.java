/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomevd;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uivdentrada.UIvdEntradaImpl;
import com.webgocommerce.client.view.uisearchaddcliente.UISearchAddClienteImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomevd extends Composite {
    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UIvdEntradaImpl uiVentaDirecta;    
    private UISearchAddClienteImpl uiSeachAddCliente;

    public UIHomevd() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);
        pnlContenedor.add(container);
        uiVentaDirecta = new UIvdEntradaImpl(this);   
        uiSeachAddCliente=new UISearchAddClienteImpl(this);
        container.add(uiVentaDirecta);        
        container.add(uiSeachAddCliente);        
        container.showWidget(0);
    }

    public DeckPanel getContainer() {
        return container;
   }   

    public UIvdEntradaImpl getUiVentaDirecta() {
        return uiVentaDirecta;
    }

    public UISearchAddClienteImpl getUiSeachAddCliente() {
        return uiSeachAddCliente;
    }
    
    

}
