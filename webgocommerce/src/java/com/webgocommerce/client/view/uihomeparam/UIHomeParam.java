/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomeparam;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uimantparam.UIMantParamImpl;
import com.webgocommerce.client.view.uiparam.UIParamImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeParam extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UIParamImpl uiParam;    
    private UIMantParamImpl uiMantParam;

    public UIHomeParam() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);
        pnlContenedor.add(container);
        uiParam = new UIParamImpl(this);   
        uiMantParam=new UIMantParamImpl(this);
        container.add(uiParam);        
        container.add(uiMantParam);        
        container.showWidget(0);
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UIParamImpl getUiParam() {
        return uiParam;
    }

   public UIMantParamImpl getUiMantParam() {
        return uiMantParam;
    }

    

}
