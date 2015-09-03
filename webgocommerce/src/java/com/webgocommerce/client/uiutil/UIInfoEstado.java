/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author SISTEMAS
 */
public class UIInfoEstado extends Composite{
    private FlexTable pnlContenedor;
    public UIInfoTC infoTc;
    public UIInfoUbicacion infoUbicacion;
    public UIInfoUsuario infoUsuario;
    
    public UIInfoEstado(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new FlexTable();
        infoUbicacion = new UIInfoUbicacion();
        infoUsuario=new UIInfoUsuario();
        infoTc=new UIInfoTC();
        pnlContenedor.setWidget(0, 0, infoUsuario);
        pnlContenedor.setWidget(0, 1, infoUbicacion);
        pnlContenedor.setWidget(0, 2, infoTc);
        pnlContenedor.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
        pnlContenedor.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
        pnlContenedor.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
        this.initWidget(pnlContenedor);
    }
    
    private void initStyle(){
        pnlContenedor.setWidth("100%");
    }
}
