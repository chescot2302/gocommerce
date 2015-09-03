/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantmesa;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import com.webgocommerce.client.beanproxy.MesaProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import static com.webgocommerce.client.model.UIMantenimiento.MODODELETE;
import static com.webgocommerce.client.model.UIMantenimiento.MODOINSERTAR;
import static com.webgocommerce.client.model.UIMantenimiento.MODOUPDATE;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.listmodel.ListModelCoordinador;
import com.webgocommerce.client.view.listmodel.ListModelGerenteZonal;
import com.webgocommerce.client.view.listmodel.ListModelSucursal;
import com.webgocommerce.client.view.listmodel.ListModelSupervisor;
import com.webgocommerce.shared.FieldVerifier;

/**
 *
 * @author SISTEMAS
 */
public class UIMantMesa extends UIMantenimiento implements InterUIMantMesa, KeyUpHandler {
    protected ListModelCoordinador lstCoordinador;
    protected ListModelGerenteZonal lstGerenteZonal;
    protected ListModelSupervisor lstSupervisor; 
    protected ListModelSucursal lstSucursal;
    protected TextBox txtId;
    protected TextBox txtDescripcion;
    protected MesaProxy bean;

    public UIMantMesa() {
        initComponents();
        initStyle();
        initListener();
        reCalcularWindows();
    }

    private void initComponents() {
        lstCoordinador=new ListModelCoordinador();
        lstGerenteZonal=new ListModelGerenteZonal();
        lstSupervisor=new ListModelSupervisor();
        lstSucursal=new ListModelSucursal();
        txtId = new TextBox();
        txtDescripcion = new TextBox();
        txtDescripcion.getElement().setAttribute("required", "required");
        //this.addWidget("ID", txtId);
        this.addWidget("COORDINADOR (*)", lstCoordinador);
        this.addWidget("GER. ZONAL (*)", lstGerenteZonal);
        this.addWidget("SUPERVISOR (*)", lstSupervisor);
        this.addWidget("SUCURSAL (*)", lstSucursal);
        this.addWidget("DESCRIPCION (*)", txtDescripcion);
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
    
    private void initStyle(){
        lstCoordinador.setWidth("100%");
        lstGerenteZonal.setWidth("100%");
        lstSupervisor.setWidth("100%");
        lstSucursal.setWidth("100%");
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 220;
        this.scrollPanel.setHeight(alto + "px");
    }

    @Override
    public void loadFields() {
        if (this.modo.equals(UIMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getIdMesa().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(true);
            txtDescripcion.setFocus(true);
            txtDescripcion.selectAll();
            lstCoordinador.setEnabled(true);
            lstGerenteZonal.setEnabled(true);
            lstSupervisor.setEnabled(true);
            lstSucursal.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getIdMesa().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            lstCoordinador.setEnabled(false);
            lstGerenteZonal.setEnabled(false);
            lstSupervisor.setEnabled(false);
            lstSucursal.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODETALLE) || this.modo.equals("DESACTIVAR")) {
            txtId.setText(this.bean.getIdMesa().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            lstCoordinador.setEnabled(false);
            lstGerenteZonal.setEnabled(false);
            lstSupervisor.setEnabled(false);
            lstSucursal.setEnabled(false);
            //this.btnOperacion.setDisabled(true);
            this.btnOperacion.setDisabled(this.modo.equals(UIMantenimiento.MODODETALLE) ? true : false);
        } else {
            txtId.setEnabled(false);
            txtDescripcion.setFocus(true);
            txtDescripcion.setEnabled(true);
            txtDescripcion.selectAll();
            lstCoordinador.setEnabled(true);
            lstGerenteZonal.setEnabled(true);
            lstSupervisor.setEnabled(true);
            lstSucursal.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        }
    }

    public void setBean(MesaProxy bean) {
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
    public void goToUIMesa() {
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

    @Override
    public void loadSucursal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadCoordinador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadGerenteZonal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadSupervisor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processDesactivar() {
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
            } else if (modo.equalsIgnoreCase("DESACTIVAR")) {
                processDesactivar();
            } else {
                //Window.alert("Operación no contemplada");
                Notification not=new Notification(Notification.ALERT,"Operacion no contemplada");
                not.showPopup();
            }
        }
    }
}
