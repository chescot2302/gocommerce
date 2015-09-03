/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomelistaprecio;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.view.uiadditemlista.UIAddItemListaImpl;
import com.webgocommerce.client.view.uieditlistaprecio.UIEditListaPrecioImpl;
import com.webgocommerce.client.view.uilistaprecio.UIListaPrecioImpl;
import com.webgocommerce.client.view.uimantlistaprecio.UIMantListaPrecioImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeListaPrecio extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UIListaPrecioImpl uiListaPrecio;
    private UIMantListaPrecioImpl uiMantListaPrecio;
    private UIEditListaPrecioImpl uiEditListaPrecio;
    private UIAddItemListaImpl uiAddItemLista;
    

    public UIHomeListaPrecio() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);        
        pnlContenedor.add(container);
        uiListaPrecio=new UIListaPrecioImpl(this);
        uiMantListaPrecio=new UIMantListaPrecioImpl(this);
        uiEditListaPrecio=new UIEditListaPrecioImpl(this);
        uiAddItemLista=new UIAddItemListaImpl(this);
        container.add(uiListaPrecio);
        container.add(uiMantListaPrecio);
        container.add(uiEditListaPrecio);
        container.add(uiAddItemLista);
        container.showWidget(0);        
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UIListaPrecioImpl getUiListaPrecio() {
        return uiListaPrecio;
    }

    public UIMantListaPrecioImpl getUiMantListaPrecio() {
        return uiMantListaPrecio;
    }

    public UIEditListaPrecioImpl getUiEditListaPrecio() {
        return uiEditListaPrecio;
    }

    public UIAddItemListaImpl getUiAddItemLista() {
        return uiAddItemLista;
    }
    
    
          
}
