/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantsucursal;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import com.webgocommerce.client.beanproxy.SucursalProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import static com.webgocommerce.client.model.UIMantenimiento.MODODELETE;
import static com.webgocommerce.client.model.UIMantenimiento.MODOINSERTAR;
import static com.webgocommerce.client.model.UIMantenimiento.MODOUPDATE;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.listmodel.ListModelLocalidad;
import com.webgocommerce.shared.FieldVerifier;

/**
 *
 * @author SISTEMAS
 */
public class UIMantSucursal extends UIMantenimiento implements KeyUpHandler, InterUIMantSucursal {

    protected TextBox txtId;
    protected TextBox txtDescripcion;
    protected SucursalProxy bean;
    protected ListModelLocalidad lstLocalidad;

    public UIMantSucursal() {
        initComponents();
        initStyle();
        initListener();
        reCalcularWindows();
    }

    private void initComponents() {
        txtId = new TextBox();
        lstLocalidad = new ListModelLocalidad();
        txtDescripcion = new TextBox();        
        txtDescripcion.getElement().setAttribute("required", "required");
        //this.addWidget("ID", txtId);
        this.addWidget("CATEGORIA (*)", lstLocalidad);
        this.addWidget("DESCRIPCION (*)", txtDescripcion);        
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }

    private void initStyle() {
        lstLocalidad.setWidth("100%");
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
            lstLocalidad.setEnabled(true);
            lstLocalidad.setFocus(true);
            txtDescripcion.setEnabled(true);
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
            this.btnOperacion.setDisabled(this.modo.equals(UIMantenimiento.MODODETALLE) ? true : false);
        } else {
            txtId.setEnabled(false);
            lstLocalidad.setEnabled(true);
            lstLocalidad.setFocus(true);
            txtDescripcion.setEnabled(true);            
            this.btnOperacion.setDisabled(false);
        }
    }

    public void setBean(SucursalProxy bean) {
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
    public void goToUISucursal() {
        cleanForm();
    }

    @Override
    public void loadListBox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
        if (event.getSource().equals(btnOperacion)) {
            if (modo.equalsIgnoreCase(MODOINSERTAR)) {                
                processInsertar();
            } else if (modo.equalsIgnoreCase(MODOUPDATE)) {                
                processActualizar();
            } else if (modo.equalsIgnoreCase(MODODELETE)) {
                processEliminar();
            } else {
                //Window.alert("Operación no contemplada");
                Notification not=new Notification(Notification.ALERT,"Operación no contemplada");
                not.showPopup();
            }
        }
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
