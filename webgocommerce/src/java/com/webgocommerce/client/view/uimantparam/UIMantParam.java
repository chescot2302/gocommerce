/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantparam;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import com.webgocommerce.client.beanproxy.ParamProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import static com.webgocommerce.client.model.UIMantenimiento.MODOINSERTAR;
import static com.webgocommerce.client.model.UIMantenimiento.MODOUPDATE;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.shared.FieldVerifier;

/**
 *
 * @author SISTEMAS
 */
public class UIMantParam extends UIMantenimiento implements InterUIMantParam, KeyUpHandler {

    protected TextBox txtId;
    protected TextBox txtAbreviatura;
    protected TextBox txtDescripcion;
    protected TextBox txtValor;
    protected ParamProxy bean;

    public UIMantParam() {
        initComponents();
        initListener();
        reCalcularWindows();
    }

    private void initComponents() {
        txtId = new TextBox();
        txtAbreviatura=new TextBox();
        txtAbreviatura.getElement().setAttribute("required", "required");
        txtDescripcion = new TextBox();
        txtDescripcion.getElement().setAttribute("required", "required");
        txtValor = new TextBox();
        txtValor.getElement().setAttribute("required", "required");
        //this.addWidget("ID", txtId);
        this.addWidget("ABREVIATURA (*)", txtAbreviatura);
        this.addWidget("DESCRIPCION (*)", txtDescripcion);
        this.addWidget("VALOR (*)", txtValor);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }

    private void initListener() {
        txtDescripcion.addKeyUpHandler(this);
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 220;
        this.scrollPanel.setHeight(alto + "px");
    }

    @Override
    public void loadFields() {
        if (this.modo.equals(UIMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getIdParam().toString());
            txtAbreviatura.setText(this.bean.getAbreviatura());
            txtDescripcion.setText(this.bean.getDescripcion());            
            txtValor.setText(this.bean.getValor());
            txtId.setEnabled(false);
            txtAbreviatura.setEnabled(true);
            txtDescripcion.setEnabled(true);
            txtValor.setEnabled(true);
            txtAbreviatura.setFocus(true);
            txtAbreviatura.selectAll();
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getIdParam().toString());
            txtAbreviatura.setText(this.bean.getAbreviatura());
            txtDescripcion.setText(this.bean.getDescripcion());            
            txtValor.setText(this.bean.getValor());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODETALLE)) {
            txtId.setText(this.bean.getIdParam().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtAbreviatura.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtValor.setEnabled(false);
            this.btnOperacion.setDisabled(true);
        } else {
            txtId.setEnabled(false);
            txtAbreviatura.setFocus(true);
            txtAbreviatura.setEnabled(true);
            txtAbreviatura.selectAll();
            txtDescripcion.setEnabled(true);
            txtValor.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        }
    }

    public void setBean(ParamProxy bean) {
        this.bean = bean;
    }

    @Override
    public void cleanForm() {
        txtId.setText(null);
        txtAbreviatura.setText(null);
        txtDescripcion.setText(null);
        txtValor.setText(null);
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getSource().equals(txtDescripcion)) {
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
    public void goToUIParam() {
        cleanForm();        
    }
    
    @Override
    public boolean isValidData() {
        if (FieldVerifier.isEmpty(txtDescripcion.getText())) {
            //Window.alert("Descripcion es un campo obligatorio");
            Notification not=new Notification(Notification.ALERT,"Descripcion es un campo obligatorio");
            not.showPopup();
            txtDescripcion.setFocus(true);
            return false;
        }
        return true;
    }
}
