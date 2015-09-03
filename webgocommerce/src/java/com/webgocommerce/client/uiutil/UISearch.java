/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.webgocommerce.client.view.listmodel.ListModelFamilia;

/**
 *
 * @author SISTEMAS
 */
public class UISearch extends Composite{
    private FlexTable pnlContenedor;
    private UILabel lblBuscar;
    public ListModelFamilia lstFamilia;
    public TextBox txtBuscar;
    public Button btnRefrescar;
    
    public UISearch(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new FlexTable();
        lblBuscar=new UILabel("Buscar");
        lstFamilia=new ListModelFamilia();
        txtBuscar=new TextBox();        
        btnRefrescar=new Button("refrescar");
        pnlContenedor.setWidget(0, 0, lblBuscar);
        pnlContenedor.setWidget(0, 1, lstFamilia);     
        pnlContenedor.setWidget(0, 2, txtBuscar);     
        pnlContenedor.setWidget(0, 3, (new UISeparador()).isSpace());     
        pnlContenedor.setWidget(0, 4, btnRefrescar);     
        this.initWidget(pnlContenedor);
    }
    
    private void initStyle(){
        txtBuscar.getElement().getStyle().setProperty("placeholder", "escribe aqui");
    }


        
}
