/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantcategorialista;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import com.webgocommerce.client.beanproxy.CategoriaListaProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.shared.FieldVerifier;

/**
 *
 * @author SISTEMAS
 */
public class UIMantCategoriaLista extends UIMantenimiento implements InterUIMantCategoriaLista, KeyUpHandler {

    protected TextBox txtId;
    protected TextBox txtDescripcion;
    protected CategoriaListaProxy bean;

    public UIMantCategoriaLista() {
        initComponents();
        initListener();
        reCalcularWindows();
    }

    private void initComponents() {
        txtId = new TextBox();
        txtDescripcion = new TextBox();
        //this.addWidget("ID", txtId);
        this.addWidget("DESCRIPCION (*)", txtDescripcion);
        txtDescripcion.getElement().setAttribute("required", "required");
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
            txtId.setText(this.bean.getId().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(true);
            txtDescripcion.setFocus(true);
            txtDescripcion.selectAll();
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getId().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODETALLE)) {
            txtId.setText(this.bean.getId().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            this.btnOperacion.setDisabled(true);
        } else {
            txtId.setEnabled(false);
            txtDescripcion.setFocus(true);
            txtDescripcion.setEnabled(true);
            txtDescripcion.selectAll();
            this.btnOperacion.setDisabled(false);
        }
    }

    public void setBean(CategoriaListaProxy bean) {
        this.bean = bean;
    }

    @Override
    public void cleanForm() {
        txtId.setText(null);
        txtDescripcion.setText(null);
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
    public void goToUICategoriaLista() {
        cleanForm();        
    }

    @Override
    public boolean isValidData() {
        if (FieldVerifier.isEmpty(txtDescripcion.getText())) {
            //Window.alert("Descripcion es un campo obligatorio");
            Notification not=new Notification(Notification.INFORMATION,"Descripcion es un campo obligatorio");
            not.showPopup();
            txtDescripcion.setFocus(true);
            return false;
        }
        return true;
    }

}
