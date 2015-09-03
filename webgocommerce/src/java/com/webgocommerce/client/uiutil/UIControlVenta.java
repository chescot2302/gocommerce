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

/**
 *
 * @author SISTEMAS
 */
public class UIControlVenta extends Composite{
    private FlexTable pnlContenedor;
    private UILabel lblCantidad;
    private UILabel lblPrecio;
    public TextBox txtCantidad;
    public UIInputPrecio txtPrecio;
    public Button btnAgregar;
    public Button btnQuitar;
    public Button btnGenerar;
    public Button btnNuevo;
    
    
    public UIControlVenta(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new FlexTable();
        lblCantidad=new UILabel("Cantidad");
        txtCantidad=new TextBox();
        lblPrecio=new UILabel("Precio");
        txtPrecio=new UIInputPrecio();        
        btnAgregar=new Button("(F2) Agregar");
        btnQuitar=new Button("(Shift) Quitar");
        btnGenerar=new Button("(F4) Generar");
        btnNuevo=new Button("(F8) Nuevo");
        pnlContenedor.setWidget(0, 0, lblCantidad);
        pnlContenedor.setWidget(0, 1, txtCantidad);
        pnlContenedor.setWidget(0, 2, lblPrecio);
        pnlContenedor.setWidget(0, 3, txtPrecio);
        //pnlContenedor.setWidget(0, 4, txtPrecio);
        pnlContenedor.setWidget(0, 4, (new UISeparador()).isSpace());
        pnlContenedor.setWidget(0, 5, btnAgregar);
        pnlContenedor.setWidget(0, 6, (new UISeparador()).isSpace());
        pnlContenedor.setWidget(0, 7, btnQuitar);
        pnlContenedor.setWidget(0, 8, (new UISeparador()).isSpace());
        pnlContenedor.setWidget(0, 9, btnGenerar);
        pnlContenedor.setWidget(0, 10, (new UISeparador()).isSpace());
        pnlContenedor.setWidget(0, 11, btnNuevo);
        this.initWidget(pnlContenedor);
    }
    
    private void initStyle(){       
        txtCantidad.setWidth("100px");
        txtPrecio.setWidth("100px");
        btnAgregar.setWidth("100px");
        btnQuitar.setWidth("100px");
        btnGenerar.setWidth("100px");
        btnNuevo.setWidth("100px");
    }
}
