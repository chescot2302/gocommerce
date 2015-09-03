/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uihomecategorialista;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.view.uicategorialista.UICategoriaListaImpl;
import com.webgocommerce.client.view.uimantcategorialista.UIMantCategoriaListaImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeCategoriaLista extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UICategoriaListaImpl uiCategoriaLista;
    private UIMantCategoriaListaImpl uiMantCategoriaLista;
    

    public UIHomeCategoriaLista() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);        
        pnlContenedor.add(container);
        uiCategoriaLista=new UICategoriaListaImpl(this);
        uiMantCategoriaLista=new UIMantCategoriaListaImpl(this);
        container.add(uiCategoriaLista);
        container.add(uiMantCategoriaLista);
        container.showWidget(0);        
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UICategoriaListaImpl getUiCategoriaLista() {
        return uiCategoriaLista;
    }

    public UIMantCategoriaListaImpl getUiMantCategoriaLista() {
        return uiMantCategoriaLista;
    }
    
    
    

}
