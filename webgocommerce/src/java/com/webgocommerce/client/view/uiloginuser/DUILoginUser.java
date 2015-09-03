/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uiloginuser;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
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
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public class DUILoginUser extends FBorderLayout implements InterUILoginUser, TouchEndHandler, KeyUpHandler {

    private HeaderMenu header;
    private Label lblTitulo;
    protected TextBox txtCorreo;
    protected PasswordTextBox txtClave;
    protected TextBox txtClavePublicaAdmin;
    protected PasswordTextBox txtMiClavePrivada;
    private Button btnIniciar;
    private ContentForm form;

    public DUILoginUser() {
        initComponents();
        initListener();
        initStyle();
    }

    private void initComponents() {
        header = new HeaderMenu();
        lblTitulo = new Label("INICIAR SESIÓN - GOUSER");
        header.setCenterWidget(lblTitulo);
        txtCorreo = new TextBox();
        txtCorreo.getElement().setAttribute("required", "required");
        txtClave = new PasswordTextBox();
        txtClave.getElement().setAttribute("required", "required");
        txtClavePublicaAdmin = new TextBox();
        txtClavePublicaAdmin.setText("DIMOSAC");
        txtClavePublicaAdmin.setEnabled(false);
        txtClavePublicaAdmin.getElement().setAttribute("required", "required");
        txtMiClavePrivada = new PasswordTextBox();
        txtMiClavePrivada.getElement().setAttribute("required", "required");
        btnIniciar = new Button("INICIAR SESIÓN");
        btnIniciar.setConfirm(true);
        form = new ContentForm();
        form.addWidget("USUARIO LOGICO", txtCorreo);
        form.addWidget("CLAVE LOGICO", txtClave);
        form.addWidget("CLAVE PUBLICA DE ADMIN", txtClavePublicaAdmin);
        //form.addWidget("CLAVE DE NO REPUDIO", txtMiClavePrivada);
        /*form.add(new FormEntry("USUARIO LOGICO",txtCorreo));
         form.add(new FormEntry("CLAVE",txtClave));        
         form.add(new FormEntry("CLAVE PUBLICA DE ADMIN",txtClavePublicaAdmin));*/
        //form.add(new FormEntry("MI CLAVE PRIVADA",txtMiClavePrivada));
        txtMiClavePrivada.setText(String.valueOf((new Date()).getTime()));
        this.add(FBorderLayout.NORTE, header);
        this.add(FBorderLayout.CENTRO, form);
        this.add(FBorderLayout.SUR, btnIniciar);
    }

    private void initListener() {
        btnIniciar.addTouchEndHandler(this);
        txtClave.addKeyUpHandler(this);
    }

    private void initStyle() {
        header.setHeight("3em");
        lblTitulo.getElement().getStyle().setFontSize(1.5, Style.Unit.EM);
        btnIniciar.setWidth("97%");
        this.pnlSur.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
    }

    @Override
    public void loginUser() {
    }

    @Override
    public boolean isValidData() {
        if(FieldVerifier.isEmpty(txtCorreo.getText())){
            Notification not=new Notification(Notification.ALERT,"Usuario es un campo obligatorio");
            not.showPopup();
            txtCorreo.setFocus(true);
            return false;
        }else if(FieldVerifier.isEmpty(txtClave.getText())){
            Notification not=new Notification(Notification.ALERT,"Clave es un campo obligatorio");
            not.showPopup();
            txtClave.setFocus(true);
            return false;
        }
        return true;
    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
        if (event.getSource().equals(btnIniciar)) {
            if (isValidData()) {
                loginUser();
            }
        }
    }

    @Override
    public void goToSesion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getSource().equals(txtClave)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                if (isValidData()) {
                    loginUser();
                }
            }
        }
    }

}
