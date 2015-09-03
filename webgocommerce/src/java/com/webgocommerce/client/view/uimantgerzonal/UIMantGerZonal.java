/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantgerzonal;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.webgocommerce.client.beanproxy.GerenteZonalProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import static com.webgocommerce.client.model.UIMantenimiento.MODOINSERTAR;
import static com.webgocommerce.client.model.UIMantenimiento.MODOUPDATE;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uisesion.UISesion;
import com.webgocommerce.shared.FieldVerifier;

/**
 *
 * @author SISTEMAS
 */
public class UIMantGerZonal extends UIMantenimiento implements InterUIMantGerZonal, KeyUpHandler, ChangeHandler, BlurHandler{
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateBox.DefaultFormat defaultFormat=new DateBox.DefaultFormat(dateFormat);
    protected TextBox txtId;
    protected TextBox txtDni;
    protected TextBox txtNombres;
    protected TextBox txtApellidos;
    protected TextBox txtCorreo;    
    protected TextBox txtCelular;    
    protected DateBox dtFechaIncorporacion;
    protected GerenteZonalProxy bean;

    public UIMantGerZonal() {
        initComponents();
        initListener();
        initStyle();
        reCalcularWindows();
    }

    private void initComponents() {
        dtFechaIncorporacion=new DateBox(new DatePicker(),UISesion.beanInitParam.getFechaServer(),defaultFormat);        
        txtCelular=new TextBox();
        txtId = new TextBox();     
        txtDni = new TextBox();
        txtDni.getElement().setAttribute("required", "required");
        txtNombres = new TextBox();
        txtNombres.getElement().setAttribute("required", "required");
        txtApellidos = new TextBox();
        txtApellidos.getElement().setAttribute("required", "required");
        txtCorreo=new TextBox();
        //this.addWidget("ID", txtId);
        this.addWidget("INCORPORACION (*)", dtFechaIncorporacion);
        this.addWidget("DNI (*)", txtDni);
        this.addWidget("NOMBRES (*)", txtNombres);
        this.addWidget("APELLIDOS (*)", txtApellidos);
        this.addWidget("CORREO ELECTRONICO ", txtCorreo);
        this.addWidget("CELULAR ", txtCelular);
        Window.addResizeHandler(new ResizeHandler(){

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
            
        });
    }
    
    private void initStyle(){
    }

    private void initListener() {
        txtApellidos.addKeyUpHandler(this);
        dtFechaIncorporacion.setWidth("100%");
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 220;
        this.scrollPanel.setHeight(alto + "px");
    }

    @Override
    public void loadFields() {
        if (this.modo.equals(UIMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getId().toString());
            txtDni.setText(this.bean.getDni());
            txtNombres.setText(this.bean.getNombres());
            txtApellidos.setText(this.bean.getApellidos());
            txtId.setEnabled(false);
            txtDni.setEnabled(false);
            txtNombres.setEnabled(true);
            txtApellidos.setEnabled(true);                      
            txtCorreo.setText(this.bean.getCorreo());
            txtCorreo.setEnabled(true);   
            txtCelular.setText(this.bean.getCelular());
            txtCelular.setEnabled(true);               
            dtFechaIncorporacion.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getId().toString());
            txtDni.setText(this.bean.getDni());
            txtNombres.setText(this.bean.getNombres());
            txtApellidos.setText(this.bean.getApellidos());
            txtId.setEnabled(false);
            txtDni.setEnabled(false);
            txtNombres.setEnabled(false);
            txtApellidos.setEnabled(false);                        
            txtCorreo.setText(this.bean.getCorreo());
            txtCorreo.setEnabled(false);   
            txtCelular.setText(this.bean.getCelular());
            txtCelular.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODETALLE)) {
            txtId.setText(this.bean.getId().toString());
            txtDni.setText(this.bean.getDni());
            txtNombres.setText(this.bean.getNombres());
            txtApellidos.setText(this.bean.getApellidos());
            txtId.setEnabled(false);
            txtDni.setEnabled(false);
            txtNombres.setEnabled(false);
            txtApellidos.setEnabled(false);            
            txtCorreo.setText(this.bean.getCorreo());
            txtCorreo.setEnabled(false);   
            txtCelular.setText(this.bean.getCelular());
            txtCelular.setEnabled(false);                     
            this.btnOperacion.setDisabled(true);
        } else {
            txtId.setEnabled(false);
            txtDni.setEnabled(true);
            txtDni.selectAll();
            txtDni.setEnabled(true);
            txtNombres.setEnabled(true);
            txtApellidos.setEnabled(true);
            txtCorreo.setEnabled(true);   
            txtCelular.setEnabled(true);
            dtFechaIncorporacion.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        }
    }

    public void setBean(GerenteZonalProxy bean) {
        this.bean = bean;
    }

    @Override
    public void cleanForm() {
        txtId.setText(null);
        txtDni.setText(null);
        txtNombres.setText(null);
        txtApellidos.setText(null);
        txtCorreo.setText(null);
        txtCelular.setText(null);
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getSource().equals(txtApellidos)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                if (modo.equalsIgnoreCase(MODOINSERTAR)) {
                    processInsertar();
                } else if (modo.equalsIgnoreCase(MODOUPDATE)) {
                    processActualizar();
                } else {
                    //Window.alert("Operación no contemplada");
                    Notification not=new Notification(Notification.ALERT,"Operación no contemplada");
                    not.showPopup();
                }
            }
        }
    }

    @Override
    public void goToUIGerZonal() {
        cleanForm();        
    }
    
    @Override
    public boolean isValidData() {
        if (FieldVerifier.isEmpty(txtDni.getText())) {
            //Window.alert("Descripcion es un campo obligatorio");
            Notification not=new Notification(Notification.ALERT,"DNI es un campo obligatorio");
            not.showPopup();
            txtDni.setFocus(true);
            return false;
        }else if (FieldVerifier.isEmpty(txtNombres.getText())) {
            //Window.alert("Descripcion es un campo obligatorio");
            Notification not=new Notification(Notification.ALERT,"Nombres es un campo obligatorio");
            not.showPopup();
            txtNombres.setFocus(true);
            return false;
        }else if (FieldVerifier.isEmpty(txtApellidos.getText())) {
            //Window.alert("Descripcion es un campo obligatorio");
            Notification not=new Notification(Notification.ALERT,"Apellidos es un campo obligatorio");
            not.showPopup();
            txtApellidos.setFocus(true);
            return false;
        }
        return true;
    }   

    @Override
    public void onChange(ChangeEvent event) {        
    }

    @Override
    public void onBlur(BlurEvent event) {
    }
}
