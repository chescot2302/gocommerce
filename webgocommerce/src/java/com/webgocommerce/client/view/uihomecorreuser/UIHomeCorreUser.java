/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uihomecorreuser;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.uicorreuser.UICorreUserImpl;
import com.webgocommerce.client.view.uimantcorreuser.UIMantCorreUserImpl;
import com.webgocommerce.client.view.uiselectuser.UISelectUserImpl;

/**
 *
 * @author SISTEMAS
 */
public class UIHomeCorreUser  extends Composite {

    private FlowPanel pnlContenedor;
    private DeckPanel container;
    private UICorreUserImpl uiCorreUser;
    private UIMantCorreUserImpl uiMantCorreUser;
    private UISelectUserImpl uiSelectUser;
    

    public UIHomeCorreUser() {
        initComponents();
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        initWidget(pnlContenedor);
        container = new DeckPanel();
        container.setAnimationEnabled(true);        
        pnlContenedor.add(container);
        uiCorreUser=new UICorreUserImpl(this);
        uiMantCorreUser=new UIMantCorreUserImpl(this);
        uiSelectUser=new UISelectUserImpl(this);
        container.add(uiCorreUser);
        container.add(uiMantCorreUser);
        container.add(uiSelectUser);
        container.showWidget(0);        
    }

    public DeckPanel getContainer() {
        return container;
    }

    public UICorreUserImpl getUiCorreUser() {
        return uiCorreUser;
    }

    public UIMantCorreUserImpl getUiMantCorreUser() {
        return uiMantCorreUser;
    }  

    public UISelectUserImpl getUiSelectUser() {
        return uiSelectUser;
    }
    
    

}
