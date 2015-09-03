/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.uiutil;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.webgocommerce.client.beanproxy.CoordinadorProxy;
import com.webgocommerce.client.beanproxy.GerenteZonalProxy;
import com.webgocommerce.client.beanproxy.SupervisorProxy;
import com.webgocommerce.client.beanproxy.VendedorProxy;
import com.webgocommerce.client.view.uicoordinador.UICoordinador;
import com.webgocommerce.client.view.uigerzonal.UIGerZonal;
import com.webgocommerce.client.view.uisesion.UISesion;
import com.webgocommerce.client.view.uisupervisor.UISupervisor;
import com.webgocommerce.client.view.uivendedor.UIVendedor;

/**
 *
 * @author SISTEMAS
 */
public class UIActDes extends PopupPanel implements ClickHandler {

    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateBox.DefaultFormat defaultFormat = new DateBox.DefaultFormat(dateFormat);
    private Label lblFecha;
    private DateBox dbFecha;
    private Button btnProcess;
    private String modo;
    private HorizontalPanel pnlContenedor;
    private UIVendedor frmVen;
    private UISupervisor frmSup;
    private UIGerZonal frmGer;
    private UICoordinador frmCoor;
    private VendedorProxy beanVen;
    private SupervisorProxy beanSup;
    private GerenteZonalProxy beanGer;
    private CoordinadorProxy beanCoor;

    public UIActDes(UIVendedor frm, String modo, VendedorProxy bean) {
        this.frmVen = frm;
        this.modo = modo;
        this.beanVen = bean;
        initComponents();
        initListener();
        initStyle();
    }

    public UIActDes(UISupervisor frm, String modo, SupervisorProxy bean) {
        this.frmSup = frm;
        this.modo = modo;
        this.beanSup = bean;
        initComponents();
        initListener();
        initStyle();
    }
    
    public UIActDes(UIGerZonal frm, String modo, GerenteZonalProxy bean) {
        this.frmGer = frm;
        this.modo = modo;
        this.beanGer = bean;
        initComponents();
        initListener();
        initStyle();
    }
    
    public UIActDes(UICoordinador frm, String modo, CoordinadorProxy bean) {
        this.frmCoor = frm;
        this.modo = modo;
        this.beanCoor = bean;
        initComponents();
        initListener();
        initStyle();
    }

    private void initComponents() {
        pnlContenedor = new HorizontalPanel();
        lblFecha = new Label("FECHA");
        dbFecha = new DateBox(new DatePicker(), UISesion.beanInitParam.getFechaServer(), defaultFormat);
        btnProcess = new Button("PROCESO");
        pnlContenedor.add(lblFecha);
        pnlContenedor.add(new UISeparador().isSpace());
        pnlContenedor.add(dbFecha);
        pnlContenedor.add(new UISeparador().isSpace());
        pnlContenedor.add(btnProcess);
        this.add(pnlContenedor);
        this.setGlassEnabled(true);
        this.setAnimationEnabled(true);
        this.setModal(true);
        this.setAutoHideEnabled(true);
        this.setSize("100px", "30px");
        this.center();
    }

    private void initListener() {
        btnProcess.addClickHandler(this);
    }

    private void initStyle() {
        if (this.modo.equalsIgnoreCase("Activar")) {
            lblFecha.setText("INCORPORADO");
            btnProcess.setText("ACTIVAR");
        } else if (this.modo.equalsIgnoreCase("Desactivar")) {
            lblFecha.setText("CESADO");
            btnProcess.setText("DESACTIVAR");
        }
    }

    @Override
    public void onClick(ClickEvent event) {
        if (beanVen != null) {
            if (this.modo.equalsIgnoreCase("Activar")) {
                frmVen.processActivar(beanVen, dbFecha.getValue());
                this.hide();
            } else if (this.modo.equalsIgnoreCase("Desactivar")) {
                frmVen.processDesactivar(beanVen, dbFecha.getValue());
                this.hide();
            }
        } else if (beanSup != null) {
            if (this.modo.equalsIgnoreCase("Activar")) {
                frmSup.processActivar(beanSup, dbFecha.getValue());
                this.hide();
            } else if (this.modo.equalsIgnoreCase("Desactivar")) {
                frmSup.processDesactivar(beanSup, dbFecha.getValue());
                this.hide();
            }
        } else if (beanGer != null) {
            if (this.modo.equalsIgnoreCase("Activar")) {
                frmGer.processActivar(beanGer, dbFecha.getValue());
                this.hide();
            } else if (this.modo.equalsIgnoreCase("Desactivar")) {
                frmGer.processDesactivar(beanGer, dbFecha.getValue());
                this.hide();
            }
        } else if (beanCoor != null) {
            if (this.modo.equalsIgnoreCase("Activar")) {
                frmCoor.processActivar(beanCoor, dbFecha.getValue());
                this.hide();
            } else if (this.modo.equalsIgnoreCase("Desactivar")) {
                frmCoor.processDesactivar(beanCoor, dbFecha.getValue());
                this.hide();
            }
        }
    }

}
