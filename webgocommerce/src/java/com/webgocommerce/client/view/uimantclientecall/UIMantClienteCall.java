/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantclientecall;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.webgocommerce.client.beanproxy.ClienteCallCenterProxy;
import com.webgocommerce.client.beanproxy.LocalidadProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import static com.webgocommerce.client.model.UIMantenimiento.MODOINSERTAR;
import static com.webgocommerce.client.model.UIMantenimiento.MODOUPDATE;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.shared.FieldVerifier;

/**
 *
 * @author SISTEMAS
 */
public class UIMantClienteCall extends UIMantenimiento implements InterUIMantClienteCall, KeyUpHandler {

    protected TextBox txtId;
    protected TextBox txtDni;
    protected TextBox txtNombres;
    protected TextBox txtApellidos;
    protected TextArea txtObservaciones;
    protected ToggleButton btnAceptado;
    protected ToggleButton btnRechazado;
    protected ClienteCallCenterProxy bean;

    public UIMantClienteCall() {
        initComponents();
        initListener();
        reCalcularWindows();
        initStyle();
    }

    private void initComponents() {
        btnAceptado = new ToggleButton("ACEPTADO");
        btnRechazado = new ToggleButton("RECHAZADO");
        btnRechazado.setDown(true);
        HorizontalPanel pnlEstado = new HorizontalPanel();
        pnlEstado.add(btnAceptado);
        pnlEstado.add(btnRechazado);
        pnlEstado.setWidth("100%");
        txtId = new TextBox();
        txtDni = new TextBox();
        txtDni.getElement().setAttribute("required", "required");
        txtNombres = new TextBox();
        txtNombres.getElement().setAttribute("required", "required");
        txtApellidos = new TextBox();
        txtApellidos.getElement().setAttribute("required", "required");
        txtObservaciones = new TextArea();
        //txtObservaciones.getElement().setAttribute("required", "required");
        //this.addWidget("ID", txtId);
        this.addWidget("DNI (*)", txtDni);
        this.addWidget("NOMBRES (*)", txtNombres);
        this.addWidget("APELLIDOS (*)", txtApellidos);
        this.addWidget("OBSERVACIONES", txtObservaciones);
        this.addWidget("ESTADO (*)", pnlEstado);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }

    private void initListener() {
        btnAceptado.addClickHandler(clickHandler);
        btnRechazado.addClickHandler(clickHandler);
        txtDni.addKeyUpHandler(this);
    }

    ClickHandler clickHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            if (event.getSource().equals(btnAceptado)) {
                btnAceptado.setDown(true);
                btnAceptado.getElement().getStyle().setProperty("background", "#11BA37");
                btnAceptado.getElement().getStyle().setProperty("color", "#fff");
                btnAceptado.getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);
                btnRechazado.setDown(false);
                btnRechazado.getElement().getStyle().setProperty("background", "");
                btnRechazado.getElement().getStyle().setProperty("color", "#000");
            } else if (event.getSource().equals(btnRechazado)) {
                btnAceptado.setDown(false);
                btnAceptado.getElement().getStyle().setProperty("background", "");
                btnAceptado.getElement().getStyle().setProperty("color", "#000");
                btnRechazado.setDown(true);
                btnRechazado.getElement().getStyle().setProperty("background", "#D9231C");
               btnRechazado.getElement().getStyle().setProperty("color", "#fff");
                btnRechazado.getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);
            }
        }

    };

    private void initStyle() {
        txtObservaciones.setWidth("100%");
        btnAceptado.setWidth("95%");
        btnAceptado.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
        btnRechazado.setWidth("95%");
        btnRechazado.getElement().getStyle().setProperty("background", "#D9231C");
        btnRechazado.getElement().getStyle().setProperty("color", "#fff");
        btnRechazado.getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);
        btnRechazado.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 220;
        this.scrollPanel.setHeight(alto + "px");
    }

    @Override
    public void loadFields() {
        if (this.modo.equals(UIMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getId().toString());
            txtId.setEnabled(false);
            txtDni.setText(this.bean.getDni());
            txtDni.setEnabled(true);
            txtDni.setFocus(true);
            txtDni.selectAll();
            txtNombres.setText(this.bean.getNombres());
            txtApellidos.setText(this.bean.getApellidos());
            txtObservaciones.setText(this.bean.getObservacion());
            if (this.bean.getEstado().equalsIgnoreCase("ACEPTADO")) {
                btnAceptado.setDown(true);
                btnAceptado.getElement().getStyle().setProperty("background", "#0BFF23");
                btnRechazado.setDown(false);
                btnRechazado.getElement().getStyle().setProperty("background", "");
            } else if (this.bean.getEstado().equalsIgnoreCase("RECHAZADO")) {
                btnAceptado.setDown(false);
                btnAceptado.getElement().getStyle().setProperty("background", "");
                btnRechazado.setDown(true);
                btnRechazado.getElement().getStyle().setProperty("background", "#D9231C");
            }
            txtNombres.setEnabled(true);
            txtApellidos.setEnabled(true);
            txtObservaciones.setEnabled(true);
            btnAceptado.setEnabled(true);
            btnRechazado.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getId().toString());
            txtDni.setText(this.bean.getDni());
            txtNombres.setText(this.bean.getNombres());
            txtApellidos.setText(this.bean.getApellidos());
            txtObservaciones.setText(this.bean.getObservacion());
            if (this.bean.getEstado().equalsIgnoreCase("ACEPTADO")) {
                btnAceptado.setDown(true);
                btnAceptado.getElement().getStyle().setProperty("background", "#0BFF23");
                btnRechazado.setDown(false);
                btnRechazado.getElement().getStyle().setProperty("background", "");
            } else if (this.bean.getEstado().equalsIgnoreCase("RECHAZADO")) {
                btnAceptado.setDown(false);
                btnAceptado.getElement().getStyle().setProperty("background", "");
                btnRechazado.setDown(true);
                btnRechazado.getElement().getStyle().setProperty("background", "#FF0000");
            }
            txtId.setEnabled(false);
            txtDni.setEnabled(false);
            txtNombres.setEnabled(false);
            txtApellidos.setEnabled(false);
            txtObservaciones.setEnabled(false);
            btnAceptado.setEnabled(false);
            btnRechazado.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODETALLE)) {
            txtId.setText(this.bean.getId().toString());
            txtDni.setText(this.bean.getDni());
            txtNombres.setText(this.bean.getNombres());
            txtApellidos.setText(this.bean.getApellidos());
            txtObservaciones.setText(this.bean.getObservacion());
            if (this.bean.getEstado().equalsIgnoreCase("ACEPTADO")) {
                btnAceptado.setDown(true);
                btnAceptado.getElement().getStyle().setProperty("background", "#0BFF23");
                btnRechazado.setDown(false);
                btnRechazado.getElement().getStyle().setProperty("background", "");
            } else if (this.bean.getEstado().equalsIgnoreCase("RECHAZADO")) {
                btnAceptado.setDown(false);
                btnAceptado.getElement().getStyle().setProperty("background", "");
                btnRechazado.setDown(true);
                btnRechazado.getElement().getStyle().setProperty("background", "#FF0000");
            }
            txtId.setEnabled(false);
            txtDni.setEnabled(false);
            txtNombres.setEnabled(false);
            txtApellidos.setEnabled(false);
            txtObservaciones.setEnabled(false);
            btnAceptado.setEnabled(false);
            btnRechazado.setEnabled(false);
            this.btnOperacion.setDisabled(true);
        } else {
            txtId.setEnabled(false);
            txtDni.setFocus(true);
            txtDni.setEnabled(true);
            txtDni.selectAll();
            txtNombres.setEnabled(true);
            txtApellidos.setEnabled(true);
            txtObservaciones.setEnabled(true);
            btnAceptado.setEnabled(true);
            btnRechazado.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        }
    }

    public void setBean(ClienteCallCenterProxy bean) {
        this.bean = bean;
    }

    @Override
    public void cleanForm() {
        txtId.setText(null);
        txtDni.setText(null);
        txtNombres.setText(null);
        txtApellidos.setText(null);
        txtObservaciones.setText(null);
        btnAceptado.setDown(false);
        btnRechazado.setDown(true);
        btnRechazado.getElement().getStyle().setProperty("background", "#FF0000");
        btnAceptado.getElement().getStyle().setProperty("background", "");
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getSource().equals(txtDni)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                if (modo.equalsIgnoreCase(MODOINSERTAR)) {
                    processInsertar();
                } else if (modo.equalsIgnoreCase(MODOUPDATE)) {
                    processActualizar();
                } else {
                    //Window.alert("Operación no contemplada");
                    Notification not = new Notification(Notification.ALERT, "Operación no contemplada");
                    not.showPopup();
                }
            }
        }
    }

    @Override
    public void goToUIClienteCall() {
        cleanForm();
    }

    @Override
    public boolean isValidData() {
        if (!FieldVerifier.isValidDNI(txtDni.getText())) {
            //Window.alert("Descripcion es un campo obligatorio");
            txtDni.setFocus(true);
            return false;
        }else if (FieldVerifier.isEmpty(txtNombres.getText())) {
            //Window.alert("Descripcion es un campo obligatorio");
            Notification not = new Notification(Notification.ALERT, "Nombres es un campo obligatorio");
            not.showPopup();
            txtNombres.setFocus(true);
            return false;
        }else if (FieldVerifier.isEmpty(txtApellidos.getText())) {
            //Window.alert("Descripcion es un campo obligatorio");
            Notification not = new Notification(Notification.ALERT, "Apellidos es un campo obligatorio");
            not.showPopup();
            txtNombres.setFocus(true);
            return false;
        }
        return true;
    }
}
