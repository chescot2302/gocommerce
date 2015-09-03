/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantbdusuario;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.webgocommerce.client.beanproxy.BdUsuarioProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.view.listmodel.ListModelBdEmpresa;

/**
 *
 * @author SISTEMAS
 */
public class UIMantBdUsuario extends UIMantenimiento implements InterUIMantBdUsuario,ChangeHandler,BlurHandler{
    protected TextBox txtId;
    protected ListModelBdEmpresa lstEmpresa;
    protected TextBox txtEsquema;
    protected ListBox lstNivel;
    protected TextBox txtUsuarioLog;
    protected TextBox txtClaveLog;
    protected TextBox txtUsuarioBd;
    protected PasswordTextBox txtClaveBd;
    protected TextBox txtEstado;
    protected BdUsuarioProxy bean;
    private FlowPanel pnlTxtEmpresaEsquema;
    private FlexTable flexEmpresa;
    
    public UIMantBdUsuario(){
        initComponents();
        initListener();
        initStyle();
    }
    
    private void initComponents(){
        txtId=new TextBox();
        lstEmpresa=new ListModelBdEmpresa();
        txtEsquema=new TextBox();
        txtEsquema.getElement().setAttribute("required", "required");
        lstNivel=new ListBox();
        lstNivel.addItem("superadmin");
        lstNivel.addItem("admin");
        lstNivel.addItem("user");
        txtUsuarioLog=new TextBox();
        txtUsuarioLog.getElement().setAttribute("required", "required");
        txtUsuarioLog.addBlurHandler(this);
        txtClaveLog=new TextBox();
        txtClaveLog.getElement().setAttribute("required", "required");
        txtUsuarioBd=new TextBox();
        txtUsuarioBd.getElement().setAttribute("required", "required");
        txtClaveBd=new PasswordTextBox();
        txtClaveBd.getElement().setAttribute("required", "required");
        txtEstado=new  TextBox();
        txtEstado.getElement().setAttribute("required", "required");
        pnlTxtEmpresaEsquema=new FlowPanel();
        flexEmpresa=new FlexTable();
        pnlTxtEmpresaEsquema.add(flexEmpresa);
        flexEmpresa.setWidget(0, 0, lstEmpresa);
        flexEmpresa.setWidget(0, 1, txtEsquema);
        //this.addWidget("ID", txtId);
        this.addWidget("EMPRESA / ESQUEMA (*)", pnlTxtEmpresaEsquema);
        this.addWidget("NIVEL (*)", lstNivel);
        this.addWidget("USUARIO LOGICO (*)", txtUsuarioLog);
        this.addWidget("CLAVE LOGICA (*)", txtClaveLog);
        this.addWidget("USUARIO BD (*)", txtUsuarioBd);
        this.addWidget("CLAVE BD (*)", txtClaveBd);
        this.addWidget("ESTADO (*)", txtEstado);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }
    
    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 220;
        this.scrollPanel.setHeight(alto + "px");
    }
    
    
    private void initListener(){
        lstEmpresa.addChangeHandler(this);
        lstEmpresa.addBlurHandler(this);
    }   
    
    private void initStyle(){
        lstEmpresa.setWidth("100%");
        lstNivel.setWidth("100%");
        pnlTxtEmpresaEsquema.setWidth("100%");     
        flexEmpresa.setWidth("100%");     
        FlexTable.FlexCellFormatter formatFlexCorrelativo = flexEmpresa.getFlexCellFormatter();
		formatFlexCorrelativo.setWidth(0, 0, "70%");
        MyResource.INSTANCE.getStlModel().ensureInjected();
        txtEsquema.addStyleName(MyResource.INSTANCE.getStlModel().mTextBoxWhite());
    }

    @Override
    public void loadFields() {
        if (this.modo.equals(UIMantenimiento.MODOUPDATE)) {
            txtId.setEnabled(false);
            txtId.setText(this.bean.getIdBdUsuario().toString());         
            txtEsquema.setText(bean.getSchema());
            txtEsquema.setEnabled(false);
            txtEstado.setText(bean.getEstadoActivacion());
            txtEstado.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODELETE)) {
            txtId.setEnabled(false);
            txtId.setText(this.bean.getIdBdUsuario().toString());           
            txtEsquema.setText(bean.getSchema());
            txtEsquema.setEnabled(false);            
            txtEstado.setText(bean.getEstadoActivacion());
            txtEstado.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODETALLE)) {
            txtId.setEnabled(false);
            txtId.setText(this.bean.getIdBdUsuario().toString());            
            txtEsquema.setText(bean.getSchema());
            txtEsquema.setEnabled(false);            
            txtEstado.setText(bean.getEstadoActivacion());
            txtEstado.setEnabled(false);
            this.btnOperacion.setDisabled(true);
        } else {
            txtId.setEnabled(false);                       
            txtEsquema.setEnabled(false);                       
            txtEstado.setEnabled(false);
            txtEstado.setText("A");
            this.btnOperacion.setDisabled(false);
        }
        //txtClaveLog.setEnabled(false);
    }

    @Override
    public void cleanForm() {
        txtId.setText(null);
        //txtEsquema.setText(null);
        txtUsuarioLog.setText(null);
        txtClaveLog.setText(null);
        txtEsquema.setText(null);
        txtUsuarioBd.setText(null);
        txtClaveBd.setText(null);
        //txtEstado.setText(null);
    }

    @Override
    public void goToUIBdUsuario() {
        cleanForm();
    }

    public void setBean(BdUsuarioProxy bean) {
        this.bean = bean;
    }

    @Override
    public void loadListBox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onChange(ChangeEvent event) {
        if(event.getSource().equals(lstEmpresa)){
            txtEsquema.setText(lstEmpresa.getSelectedItem().getSchema());
        }
    }

    @Override
    public void onBlur(BlurEvent event) {
        if(event.getSource().equals(lstEmpresa)){
            txtEsquema.setText(lstEmpresa.getSelectedItem().getSchema());
        }else if(event.getSource().equals(txtUsuarioLog)){
            getPassword();
        }
    }

    @Override
    public void loadPwNava() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getPassword() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
