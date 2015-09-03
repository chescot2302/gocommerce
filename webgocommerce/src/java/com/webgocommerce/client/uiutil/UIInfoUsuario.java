/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.webgocommerce.client.resource.MyResource;

/**
 *
 * @author SISTEMAS
 */
public class UIInfoUsuario extends Composite{
    private HorizontalPanel pnlContenedor;
    private Image lblUsuario;
    public static UILabel txtUsuario;
    
    public UIInfoUsuario(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new HorizontalPanel();
        lblUsuario=new Image(MyResource.INSTANCE.getImgPerson32());
        lblUsuario.setTitle("Usuario de sistema");
        txtUsuario=new UILabel(null);        
        pnlContenedor.add(lblUsuario);
        pnlContenedor.add(txtUsuario);
        this.initWidget(pnlContenedor);
    }
    
    private void initStyle(){
        txtUsuario.setWidth("300px");
    }
}
