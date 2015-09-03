/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uiloginsuperadmin;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.form.Form;
import com.googlecode.mgwt.ui.client.widget.form.FormEntry;
import com.webgocommerce.client.model.ContentForm;
import com.webgocommerce.client.model.FBorderLayout;
import com.webgocommerce.client.model.HeaderMenu;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.shared.FieldVerifier;

/**
 *
 * @author SISTEMAS
 */
public class DUILoginSuperAdmin extends FBorderLayout implements InterUILoginSuperAdmin, TouchEndHandler, KeyUpHandler {

    private HeaderMenu header;
    private Label lblTitulo;
    protected TextBox txtIpHost;
    protected TextBox txtNombreBd;
    protected TextBox txtPuertoBd;
    protected TextBox txtUsuarioBd;
    protected PasswordTextBox txtClaveBd;
    protected TextBox txtClavePublica;
    private Button btnIniciar;
    private ContentForm form;

    public DUILoginSuperAdmin() {
        initComponents();
        initListener();
        initStyle();
    }

    private void initComponents() {
        header = new HeaderMenu();
        lblTitulo = new Label("INICIAR SESIÓN - SUPER GOADMIN");
        header.setCenterWidget(lblTitulo);
        txtIpHost = new TextBox();
        txtIpHost.getElement().setAttribute("required", "required");
        txtNombreBd = new TextBox();
        txtNombreBd.getElement().setAttribute("required", "required");
        txtPuertoBd = new TextBox();
        txtPuertoBd.getElement().setAttribute("required", "required");
        txtUsuarioBd = new TextBox();
        txtUsuarioBd.getElement().setAttribute("required", "required");
        txtClaveBd = new PasswordTextBox();
        txtClaveBd.getElement().setAttribute("required", "required");
        txtClavePublica = new TextBox();
        txtClavePublica.getElement().setAttribute("required", "required");
        btnIniciar = new Button("INICIAR SESIÓN");
        btnIniciar.setConfirm(true);
        form = new ContentForm();
        form.addWidget("IP/HOST", txtIpHost);
        form.addWidget("NOMBRE DE BD", txtNombreBd);
        form.addWidget("PUERTO DE BD", txtPuertoBd);
        form.addWidget("USUARIO DE BD", txtUsuarioBd);
        form.addWidget("CLAVE DE BD", txtClaveBd);
        form.addWidget("MI CLAVE PUBLICA", txtClavePublica);
        /*form.add(new FormEntry("IP/HOST",txtIpHost));
         form.add(new FormEntry("NOMBRE DE BD",txtNombreBd));
         form.add(new FormEntry("PUERTO DE BD",txtPuertoBd));        
         form.add(new FormEntry("USUARIO DE BD",txtUsuarioBd));
         form.add(new FormEntry("CLAVE DE BD",txtClaveBd));        
         form.add(new FormEntry("MI CLAVE PUBLICA",txtClavePublica));        */
        this.add(FBorderLayout.NORTE, header);
        this.add(FBorderLayout.CENTRO, form);
        this.add(FBorderLayout.SUR, btnIniciar);
    }

    private void initListener() {
        btnIniciar.addTouchEndHandler(this);
        txtClavePublica.addKeyUpHandler(this);
    }

    private void initStyle() {
        header.setHeight("3em");
        lblTitulo.getElement().getStyle().setFontSize(1.5, Style.Unit.EM);
        btnIniciar.setWidth("97%");
        this.pnlSur.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
    }

    @Override
    public void loginSuperAdmin() {

    }

    @Override
    public boolean isValidData() {
        if(FieldVerifier.isEmpty(txtIpHost.getText())){
            Notification not=new Notification(Notification.ALERT,"Host es un campo obligatorio");
            not.showPopup();
            txtIpHost.setFocus(true);
            return false;
        }else if (FieldVerifier.isEmpty(txtNombreBd.getText())) {
            Notification not=new Notification(Notification.ALERT,"Nombre de Base de datos es un campo obligatorio");
            not.showPopup();
            txtNombreBd.setFocus(true);
            return false;
        }else if (FieldVerifier.notIsEnteroPositivo(txtPuertoBd.getText(), "Puerto")) {
            return false;
        }else if (FieldVerifier.isEmpty(txtPuertoBd.getText())) {
            Notification not=new Notification(Notification.ALERT,"Puerto de Base de datos es un campo obligatorio");
            not.showPopup();
            txtPuertoBd.setFocus(true);
            return false;
        }else if (FieldVerifier.isEmpty(txtUsuarioBd.getText())) {
            Notification not=new Notification(Notification.ALERT,"Usuario de Base de datos es un campo obligatorio");
            not.showPopup();
            txtUsuarioBd.setFocus(true);
            return false;
        }else if (FieldVerifier.isEmpty(txtClaveBd.getText())) {
            Notification not=new Notification(Notification.ALERT,"Clave de Base de datos es un campo obligatorio");
            not.showPopup();
            txtClaveBd.setFocus(true);
            return false;
        }else if (FieldVerifier.isEmpty(txtClavePublica.getText())) {
            Notification not=new Notification(Notification.ALERT,"Clave publica es un campo obligatorio");
            not.showPopup();
            txtClavePublica.setFocus(true);
            return false;
        }
        return true;
    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
        if (event.getSource().equals(btnIniciar)) {
            if (isValidData()) {
                loginSuperAdmin();
            }
        }
    }

    @Override
    public void goToSesion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getSource().equals(txtClavePublica)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                if (isValidData()) {
                    loginSuperAdmin();
                }
            }
        }
    }

}
