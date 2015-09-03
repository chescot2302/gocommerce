/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantbdempresa;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.mgwt.ui.client.widget.input.MIntegerBox;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.webgocommerce.client.beanproxy.BdEmpresaProxy;
import com.webgocommerce.client.model.UIMantenimiento;

/**
 *
 * @author SISTEMAS
 */
public class UIMantBdEmpresa extends UIMantenimiento implements InterUIMantBdEmpresa {

    protected TextBox txtId;
    protected TextBox txtEmpresa;
    protected TextBox txtIpHost;
    protected TextBox txtPuerto;
    protected TextBox txtEsquema;
    protected TextBox txtUsuario;
    protected PasswordTextBox txtClave;
    protected TextBox txtEstado;
    protected BdEmpresaProxy bean;

    public UIMantBdEmpresa() {
        initComponents();
        initListener();
        initStyle();
        reCalcularWindows();
    }

    private void initComponents() {
        txtId = new TextBox();
        txtEmpresa = new TextBox();
        txtEmpresa.getElement().setAttribute("required", "required");
        txtIpHost = new TextBox();
        txtIpHost.getElement().setAttribute("required", "required");
        txtPuerto = new TextBox();
        txtPuerto.getElement().setAttribute("required", "required");
        txtEsquema = new TextBox();
        txtEsquema.getElement().setAttribute("required", "required");
        txtUsuario = new TextBox();
        txtUsuario.getElement().setAttribute("required", "required");
        txtClave = new PasswordTextBox();
        txtClave.getElement().setAttribute("required", "required");
        txtEstado = new TextBox();
        txtEstado.getElement().setAttribute("required", "required");
        //this.addWidget("ID", txtId);
        this.addWidget("EMPRESA (*)", txtEmpresa);
        this.addWidget("IP/HOST (*)", txtIpHost);
        this.addWidget("PUERTO (*)", txtPuerto);
        this.addWidget("ESQUEMA BD (*)", txtEsquema);
        this.addWidget("USUARIO BD (*)", txtUsuario);
        this.addWidget("CLAVE BD (*)", txtClave);
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

    private void initListener() {

    }

    private void initStyle() {
    }

    @Override
    public void goToUIBdEmpresa() {
        cleanForm();
    }

    @Override
    public void cleanForm() {
        txtId.setText(null);
        txtEmpresa.setText(null);
        txtIpHost.setText(null);
        txtPuerto.setText(null);
        txtEsquema.setText(null);
        txtUsuario.setText(null);
        txtClave.setText(null);
        txtEstado.setText(null);
    }

    public void setBean(BdEmpresaProxy bean) {
        this.bean = bean;
    }

    @Override
    public void loadFields() {
        if (this.modo.equals(UIMantenimiento.MODOUPDATE)) {
            txtId.setEnabled(false);
            txtId.setText(this.bean.getIdBdEmpresa().toString());
            txtEmpresa.setText(this.bean.getNombre());
            txtEmpresa.setEnabled(true);
            txtEmpresa.setFocus(true);
            txtEmpresa.selectAll();
            txtIpHost.setText(bean.getIpHost());
            txtIpHost.setEnabled(false);
            txtPuerto.setText(bean.getPuerto().toString());
            txtPuerto.setEnabled(false);
            txtEsquema.setText(bean.getSchema());
            txtEsquema.setEnabled(false);
            txtUsuario.setText(bean.getUserPrincipal());
            txtUsuario.setEnabled(true);
            txtClave.setText(bean.getClavePrincipal());
            txtClave.setEnabled(true);
            txtEstado.setText(bean.getEstadoActivacion());
            txtEstado.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODELETE)) {
            txtId.setEnabled(false);
            txtId.setText(this.bean.getIdBdEmpresa().toString());
            txtEmpresa.setText(this.bean.getNombre());
            txtEmpresa.setEnabled(false);
            txtIpHost.setText(bean.getIpHost());
            txtIpHost.setEnabled(false);
            txtPuerto.setText(bean.getPuerto().toString());
            txtPuerto.setEnabled(false);
            txtEsquema.setText(bean.getSchema());
            txtEsquema.setEnabled(false);
            txtUsuario.setText(bean.getUserPrincipal());
            txtUsuario.setEnabled(false);
            txtClave.setText(bean.getClavePrincipal());
            txtClave.setEnabled(false);
            txtEstado.setText(bean.getEstadoActivacion());
            txtEstado.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODETALLE)) {
            txtId.setEnabled(false);
            txtId.setText(this.bean.getIdBdEmpresa().toString());
            txtEmpresa.setText(this.bean.getNombre());
            txtEmpresa.setEnabled(false);
            txtIpHost.setText(bean.getIpHost());
            txtIpHost.setEnabled(false);
            txtPuerto.setText(bean.getPuerto().toString());
            txtPuerto.setEnabled(false);
            txtEsquema.setText(bean.getSchema());
            txtEsquema.setEnabled(false);
            txtUsuario.setText(bean.getUserPrincipal());
            txtUsuario.setEnabled(false);
            txtClave.setText(bean.getClavePrincipal());
            txtClave.setEnabled(false);
            txtEstado.setText(bean.getEstadoActivacion());
            txtEstado.setEnabled(false);
            this.btnOperacion.setDisabled(true);
        } else {
            txtId.setEnabled(false);
            txtEmpresa.setFocus(true);
            txtEmpresa.setEnabled(true);
            txtEmpresa.selectAll();            
            txtIpHost.setEnabled(true);            
            txtPuerto.setEnabled(true);           
            txtEsquema.setEnabled(true);           
            txtUsuario.setEnabled(true);           
            txtClave.setEnabled(true);            
            txtEstado.setEnabled(false);
            txtEstado.setText("A");
            this.btnOperacion.setDisabled(false);
        }
    }

}
