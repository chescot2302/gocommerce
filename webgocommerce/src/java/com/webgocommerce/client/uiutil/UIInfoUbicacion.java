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
public class UIInfoUbicacion extends Composite{
    private HorizontalPanel pnlContenedor;
    private Image imgUbicacion;
    public static UILabel lblUbicacion;
    
    public UIInfoUbicacion(){
        initComponents();
    }
    
    private void initComponents(){
        pnlContenedor=new HorizontalPanel();
        imgUbicacion=new Image(MyResource.INSTANCE.getImgUbicacion32());
        imgUbicacion.setTitle("Ubicación");
        lblUbicacion=new UILabel("");
        pnlContenedor.add(imgUbicacion);
        pnlContenedor.add(lblUbicacion);
        this.initWidget(pnlContenedor);
    }
}
