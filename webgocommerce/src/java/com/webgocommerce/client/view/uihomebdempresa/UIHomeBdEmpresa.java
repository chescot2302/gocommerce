/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uihomebdempresa;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uibdempresa.UIBdEmpresaImpl;
import com.webgocommerce.client.view.uimantbdempresa.UIMantBdEmpresaImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeBdEmpresa extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UIBdEmpresaImpl uiBdEmpresa;    
    private UIMantBdEmpresaImpl uiMantBdEmpresa;

    public UIHomeBdEmpresa() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);
        pnlContenedor.add(container);
        uiBdEmpresa = new UIBdEmpresaImpl(this);   
        uiMantBdEmpresa=new UIMantBdEmpresaImpl(this);
        container.add(uiBdEmpresa);        
        container.add(uiMantBdEmpresa);        
        container.showWidget(0);
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UIBdEmpresaImpl getUiBdEmpresa() {
        return uiBdEmpresa;
    }

   public UIMantBdEmpresaImpl getUiMantBdEmpresa() {
        return uiMantBdEmpresa;
    }

    

}
