/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.webgocommerce.client.resource.MyResource;

/**
 *
 * @author SISTEMAS
 */
public class UIInfoTC extends Composite{
    private HorizontalPanel pnlContenedor;
    public static Image lblTipoCambio;
    private UILabel lblTcCompra;
    public static UILabel txtTcCompra;
    private UILabel lblTcVenta;
    public static UILabel txtTcVenta;
    private UILabel lblTcMercado;
    public static UILabel txtTcMercado;
    
    public UIInfoTC(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new HorizontalPanel();
        lblTipoCambio=new Image(MyResource.INSTANCE.getImgTipoCambio32());
        lblTipoCambio.setTitle("Tipo de cambio");
        lblTcCompra=new UILabel("Compra:");
        txtTcCompra=new UILabel(null);         
        lblTcVenta=new UILabel("Venta:");
        txtTcVenta=new UILabel(null);           
        lblTcMercado=new UILabel("Mercado:");
        txtTcMercado=new UILabel(null);       
        pnlContenedor.add(lblTipoCambio);
        pnlContenedor.add(lblTcCompra);
        pnlContenedor.add(txtTcCompra);
        pnlContenedor.add(lblTcVenta);
        pnlContenedor.add(txtTcVenta);
        pnlContenedor.add(lblTcMercado);
        pnlContenedor.add(txtTcMercado);
        this.initWidget(pnlContenedor);
    }
    
    private void initStyle(){
        pnlContenedor.getElement().getStyle().setMargin(5, Style.Unit.PX);
        txtTcCompra.setWidth("50px");
        txtTcVenta.setWidth("50px");
        txtTcMercado.setWidth("50px");
        /*txtTcCompra.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        txtTcVenta.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        txtTcMercado.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        txtTcCompra.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
        txtTcVenta.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
        txtTcMercado.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);*/
    }
}
