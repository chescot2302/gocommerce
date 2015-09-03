/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;

/**
 *
 * @author SISTEMAS
 */
public class UIDocMonto extends Composite{
    private FlexTable pnlContenedor;
    public FlexTable pnlLeft;
    public FlexTable pnlRight;
    private UILabel lblAfecto;
    private UILabel lblNoAfecto;
    private UILabel lblIgv;
    private UILabel lblPercepcion;
    private UILabel lblTotal;
    private UILabel lblTotalPlan;
    public TextBox txtAfecto;
    public TextBox txtNoAfecto;
    public TextBox txtIgv;
    public TextBox txtPercepcion;
    public TextBox txtTotal;
    public TextBox txtTotalPlan;
    
    public UIDocMonto(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new FlexTable();
        pnlLeft=new FlexTable();
        pnlRight=new FlexTable();
        pnlContenedor.setWidget(0, 0, pnlLeft);
        pnlContenedor.setWidget(0, 1, pnlRight);
        lblAfecto=new UILabel("Afecto");
        lblNoAfecto=new UILabel("No Afecto");
        lblIgv=new UILabel("IGV");
        lblPercepcion=new UILabel("Perc");
        lblTotal=new UILabel("Total Venta");
        lblTotalPlan=new UILabel("Total Plan");
        txtAfecto=new TextBox();
        txtAfecto.setEnabled(false);
        txtNoAfecto=new TextBox();
        txtNoAfecto.setEnabled(false);
        txtIgv=new TextBox();
        txtIgv.setEnabled(false);
        txtPercepcion=new TextBox();
        txtPercepcion.setEnabled(false);
        txtTotal=new TextBox();
        txtTotal.setEnabled(false);
        txtTotalPlan=new TextBox();
        txtTotalPlan.setEnabled(false);
        pnlLeft.setWidget(0, 0, lblAfecto);
        pnlLeft.setWidget(0, 1, txtAfecto);
        pnlLeft.setWidget(0, 2, lblNoAfecto);
        pnlLeft.setWidget(0, 3, txtNoAfecto);
        pnlLeft.setWidget(0, 4, lblIgv);
        pnlLeft.setWidget(0, 5, txtIgv);
        pnlLeft.setWidget(0, 6, lblPercepcion);
        pnlLeft.setWidget(0, 7, txtPercepcion);        
        pnlRight.setWidget(0, 0, lblTotal);
        pnlRight.setWidget(0, 1, txtTotal);
        pnlRight.setWidget(0, 2, lblTotalPlan);
        pnlRight.setWidget(0, 3, txtTotalPlan);
        this.initWidget(pnlContenedor);
    }
    
    private void initStyle(){
        txtAfecto.setWidth("80px");
        txtNoAfecto.setWidth("80px");
        txtIgv.setWidth("80px");
        txtPercepcion.setWidth("80px");
        txtTotal.setWidth("80px");
        txtTotalPlan.setWidth("80px");
    }
    
}

