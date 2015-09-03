/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;

/**
 *
 * @author SISTEMAS
 */
public class UIInfoMoneda extends Composite{
    private FlexTable pnlContenedor;
    public UILabel lblMoneda;
    public ListBox lstMoneda;
    
    public UIInfoMoneda(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new FlexTable();
        lblMoneda=new UILabel("Moneda");
        lstMoneda=new ListBox();        
        lstMoneda.addItem("Nuevos Soles", "S");
        lstMoneda.addItem("Dolares", "D");
        pnlContenedor.setWidget(0, 0, lblMoneda);
        pnlContenedor.setWidget(0, 1, lstMoneda);
        this.initWidget(pnlContenedor);
    }
    
    private void initStyle(){
        lstMoneda.getElement().getStyle().setMargin(5, Style.Unit.PX);        
    }
}
