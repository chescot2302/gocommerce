/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.webgocommerce.client.model.ContentForm;
import com.webgocommerce.client.view.listmodel.ListModelDepartamento;
import com.webgocommerce.client.view.listmodel.ListModelDistrito;
import com.webgocommerce.client.view.listmodel.ListModelPais;
import com.webgocommerce.client.view.listmodel.ListModelProvincia;

/**
 *
 * @author SISTEMAS
 */
public class UIFormCliente extends Composite{    
    private FlowPanel pnlContenedor;
    public ScrollPanel scrollPanel;
    protected VerticalPanel contenido;
    protected FlowPanel pnlForm;
    private ContentForm form;
    public ListBox lstTipoDoc;
    public TextBox txtDocumento;
    public TextBox txtNombres;
    //protected TextBox txtCapcha;
    public ListModelPais lstPais;
    public ListModelDepartamento lstDepartamento;
    public ListModelProvincia lstProvincia;
    public ListModelDistrito lstDistrito;
    public TextBox txtDireccion;
    public TextBox txtTelefono;
    public TextBox txtEmail;
    public Button btnGuardar;
    
    public UIFormCliente(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new FlowPanel();
        form=new ContentForm();   
        contenido = new VerticalPanel();
        scrollPanel = new ScrollPanel();
        scrollPanel.setScrollingEnabledY(true);
        scrollPanel.setScrollingEnabledX(false);
        scrollPanel.setAutoHandleResize(true);        
        lstTipoDoc=new ListBox();
        lstTipoDoc.addItem("RUC");
        lstTipoDoc.addItem("DNI");
        lstTipoDoc.addItem("CE");
        txtDocumento=new TextBox();
        txtDocumento.getElement().setAttribute("required", "required");
        txtNombres=new TextBox();
        txtNombres.getElement().setAttribute("required", "required");
        //txtCapcha=new TextBox();
        lstPais=new ListModelPais();
        lstDepartamento=new ListModelDepartamento();
        lstProvincia=new ListModelProvincia();
        lstDistrito=new ListModelDistrito();
        txtDireccion=new TextBox();
        txtDireccion.getElement().setAttribute("required", "required");
        txtTelefono=new TextBox();
        txtEmail=new TextBox();
        //txtEmail.getElement().setAttribute("required", "required");
        form.addWidget("TIPO DOCUMENTO (*)", lstTipoDoc);
        form.addWidget("DOCUMENTO (*)", txtDocumento);
        //form.addWidget("CAPCHA (*)", new HTML());
        form.addWidget("NOMBRES O RAZON SOCIAL (*)", txtNombres);        
        form.addWidget("PAIS (*)", lstPais);
        form.addWidget("DEPARTAMENTO (*)", lstDepartamento);
        form.addWidget("PROVINCIA (*)", lstProvincia);
        form.addWidget("DISTRITO (*)", lstDistrito);
        form.addWidget("DIRECCION (*)", txtDireccion);
        form.addWidget("TELEFONO ", txtTelefono);
        form.addWidget("CORREO ELECTRONICO", txtEmail);        
        btnGuardar=new Button("GUARDAR");
        form.add(btnGuardar);
        pnlForm = new FlowPanel();
        contenido.add(pnlForm);   
        pnlForm.add(form);
        scrollPanel.setWidget(contenido);   
        pnlContenedor.add(scrollPanel);
        this.initWidget(pnlContenedor);
    }
    
    private void initStyle(){
        lstTipoDoc.setWidth("100%");
        lstDepartamento.setWidth("100%");
        lstProvincia.setWidth("100%");
        lstDistrito.setWidth("100%");
        lstPais.setWidth("100%");
        btnGuardar.setWidth("100%");
        btnGuardar.getElement().getStyle().setMarginTop(5, Style.Unit.PX);
        btnGuardar.setHeight("40px");
        btnGuardar.getElement().getStyle().setProperty("background","rgb(115, 157, 1)");
        btnGuardar.getElement().getStyle().setColor("#fff");
        btnGuardar.getElement().getStyle().setProperty("border","1px solid rgb(64, 78, 0)");   
        contenido.setWidth("100%");                                
        pnlForm.getElement().getStyle().setPadding(10, Style.Unit.PX);       
    }
}

