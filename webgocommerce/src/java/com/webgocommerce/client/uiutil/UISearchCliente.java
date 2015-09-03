/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.webgocommerce.client.resource.MyResource;

/**
 *
 * @author SISTEMAS
 */
public class UISearchCliente extends Composite{
    private FlexTable pnlContenedor;
    private UILabel lblClienteFacturacion;
    public TextBox txtRucFacturacion;
    public TextBox txtDescripcionFacturacion;
    private UILabel lblClienteSustituto;
    public TextBox txtRucSustituto;
    public ListBox lstDescripcionSustituto;
    public PushButton btnBuscar;
    private Image imgCliente;
    
    public UISearchCliente(boolean value){
        initComponents();
        initStyle();
        sustituto(value);
    }
    
    private void initComponents(){
        pnlContenedor=new FlexTable();
        lblClienteFacturacion=new UILabel("Cliente");
        lblClienteSustituto=new UILabel("Sustituto");
        txtRucFacturacion=new TextBox();
        txtRucFacturacion.setWidth("130px");
        txtRucFacturacion.getElement().setPropertyString("placeholder","RUC o DNI");
        txtRucFacturacion.setMaxLength(11);       
        txtRucFacturacion.getElement().setPropertyString("type","number");
        txtRucSustituto=new TextBox();
        txtRucSustituto.setWidth("130px");
        txtRucSustituto.getElement().setPropertyString("placeholder","RUC o DNI");
        txtRucSustituto.setEnabled(false);
        txtRucSustituto.setMaxLength(11);
        txtRucSustituto.getElement().setPropertyString("type","number");
        txtDescripcionFacturacion=new TextBox();
        txtDescripcionFacturacion.getElement().setPropertyString("placeholder","Nombres o Razón social");        
        txtDescripcionFacturacion.setEnabled(false);
        lstDescripcionSustituto=new ListBox();
        lstDescripcionSustituto.getElement().setPropertyString("placeholder","Nombres o Razón social");        
        pnlContenedor.setWidget(0, 0, lblClienteFacturacion);
        pnlContenedor.setWidget(0, 1, txtRucFacturacion);        
        pnlContenedor.setWidget(0, 2, txtDescripcionFacturacion);        
        pnlContenedor.setWidget(1, 0, lblClienteSustituto);
        pnlContenedor.setWidget(1, 1, txtRucSustituto);        
        pnlContenedor.setWidget(1, 2, lstDescripcionSustituto);      
        imgCliente=new Image(MyResource.INSTANCE.getImgPersonAdd32());
        btnBuscar=new PushButton(imgCliente);
        btnBuscar.setTitle("Buscar Cliente");        
        pnlContenedor.setWidget(0, 3, btnBuscar);        
        this.initWidget(pnlContenedor);
    }
    
    private void initStyle(){
        txtDescripcionFacturacion.setWidth("400px");
        lstDescripcionSustituto.setWidth("400px");
        txtRucFacturacion.getElement().getStyle().setMargin(5, Style.Unit.PX);
        txtRucSustituto.getElement().getStyle().setMargin(5, Style.Unit.PX);
        txtDescripcionFacturacion.getElement().getStyle().setMargin(5, Style.Unit.PX);
        lstDescripcionSustituto.getElement().getStyle().setMargin(5, Style.Unit.PX);
        imgCliente.setWidth("20px");
        imgCliente.setHeight("20px");
        btnBuscar.setWidth("16px");
        btnBuscar.setHeight("16px");
    }    
    
    private void sustituto(boolean value){
        lblClienteSustituto.setVisible(value);
        txtRucSustituto.setVisible(value);
        lstDescripcionSustituto.setVisible(value);
    }
}
