/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantcorreuser;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.webgocommerce.client.beanproxy.UsuarioCorrelativoProxy;
import com.webgocommerce.client.beanproxy.UsuarioProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import static com.webgocommerce.client.model.UIMantenimiento.MODODELETE;
import static com.webgocommerce.client.model.UIMantenimiento.MODOINSERTAR;
import static com.webgocommerce.client.model.UIMantenimiento.MODOUPDATE;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.listmodel.ListModelCorrelativo;
import com.webgocommerce.client.view.listmodel.ListModelPuntoEmision;
import com.webgocommerce.client.view.listmodel.ListModelSucursal;
import com.webgocommerce.client.view.listmodel.ListModelTienda;
import com.webgocommerce.shared.FieldVerifier;

/**
 *
 * @author SISTEMAS
 */
public class UIMantCorreUser extends UIMantenimiento implements InterUIMantCorreUser, ChangeHandler, BlurHandler {

    protected TextBox txtId;
    protected ListModelSucursal lstSucursal;
    protected ListModelTienda lstTienda;
    protected ListModelPuntoEmision lstPtoEmision;
    protected ListModelCorrelativo lstCorrelativo;
    protected TextBox txtSerie;
    protected TextBox txtAcceso;
    protected TextBox txtUsuario;
    protected PushButton btnAddUsuario;
    private FlowPanel pnlTxtCorrelativo;
    private FlowPanel pnlTxtUsuario;
    private FlexTable flexCorrelativo;
    private FlexTable flexUsuario;
    protected UsuarioCorrelativoProxy bean;
    protected UsuarioProxy beanUsuario;

    public UIMantCorreUser() {
        initComponents();
        initStyle();
        initListener();
        reCalcularWindows();
    }

    private void initComponents() {
        txtId = new TextBox();
        txtId.setReadOnly(true);
        lstSucursal = new ListModelSucursal();
        lstTienda = new ListModelTienda();
        lstPtoEmision = new ListModelPuntoEmision();
        lstCorrelativo = new ListModelCorrelativo();
        txtSerie = new TextBox();        
        txtSerie.setReadOnly(true);
        txtSerie.getElement().setAttribute("required", "required");
        txtAcceso = new TextBox();
        txtAcceso.setReadOnly(true);
        txtAcceso.getElement().setAttribute("required", "required");
        txtUsuario = new TextBox();
        txtUsuario.setReadOnly(true);
        txtUsuario.getElement().setAttribute("required", "required");
        btnAddUsuario = new PushButton(new Image(MyResource.INSTANCE.getImgPersonAdd32()));
        //this.addWidget("ID", txtId);
        this.addWidget("SUCURSAL (*)", lstSucursal);
        this.addWidget("TIENDA (*)", lstTienda);
        this.addWidget("PTO. EMISION (*)", lstPtoEmision);
        pnlTxtCorrelativo = new FlowPanel();
        flexCorrelativo = new FlexTable();
        pnlTxtCorrelativo.add(flexCorrelativo);
        flexCorrelativo.setWidget(0, 0, lstCorrelativo);
        flexCorrelativo.setWidget(0, 1, txtSerie);
        this.addWidget("CORRELATIVO (*)", pnlTxtCorrelativo);
        pnlTxtUsuario = new FlowPanel();
        flexUsuario = new FlexTable();
        pnlTxtUsuario.add(flexUsuario);
        flexUsuario.setWidget(0, 0, txtAcceso);
        flexUsuario.setWidget(0, 1, txtUsuario);
        flexUsuario.setWidget(0, 2, btnAddUsuario);
        this.addWidget("USUARIO (*)", pnlTxtUsuario);

        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }

    private void initListener() {
        lstSucursal.addChangeHandler(this);
        lstSucursal.addBlurHandler(this);
        lstTienda.addChangeHandler(this);
        lstTienda.addBlurHandler(this);
        lstPtoEmision.addChangeHandler(this);
        lstPtoEmision.addBlurHandler(this);
        lstCorrelativo.addChangeHandler(this);
        lstCorrelativo.addBlurHandler(this);
        btnAddUsuario.addClickHandler(clickHandler);
    }

    ClickHandler clickHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            if (event.getSource().equals(btnAddUsuario)) {
                gotoUISelectUser();
            }
        }

    };

    private void initStyle() {
        btnAddUsuario.setHeight("25px");
        lstSucursal.setWidth("100%");
        lstTienda.setWidth("100%");
        lstPtoEmision.setWidth("100%");
        lstCorrelativo.setWidth("100%");
        pnlTxtCorrelativo.setWidth("100%");
        flexCorrelativo.setWidth("100%");
        pnlTxtUsuario.setWidth("100%");
        flexUsuario.setWidth("100%");
        FlexCellFormatter formatFlexUsuario = flexUsuario.getFlexCellFormatter();
        formatFlexUsuario.setWidth(0, 1, "70%");
        formatFlexUsuario.setWidth(0, 2, "40");
        FlexCellFormatter formatFlexCorrelativo = flexCorrelativo.getFlexCellFormatter();
        formatFlexCorrelativo.setWidth(0, 0, "70%");
        MyResource.INSTANCE.getStlModel().ensureInjected();
        txtUsuario.addStyleName(MyResource.INSTANCE.getStlModel().mTextBoxWhite());       
        txtSerie.addStyleName(MyResource.INSTANCE.getStlModel().mTextBoxWhite());
        txtAcceso.addStyleName(MyResource.INSTANCE.getStlModel().mTextBoxWhite());        
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 220;
        this.scrollPanel.setHeight(alto + "px");
    }

    @Override
    public void loadFields() {
        if (this.modo.equals(UIMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getId().toString());
            txtSerie.setText(this.bean.getSerie());
            txtAcceso.setText(this.bean.getNomAcceso());
            txtUsuario.setText(this.bean.getNomUsuario());
            lstSucursal.setEnabled(true);
            lstTienda.setEnabled(true);
            lstPtoEmision.setEnabled(true);
            lstCorrelativo.setEnabled(true);
            btnAddUsuario.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getId().toString());
            txtId.setEnabled(false);
            txtSerie.setText(this.bean.getSerie());
            txtAcceso.setText(this.bean.getNomAcceso());
            txtUsuario.setText(this.bean.getNomUsuario());
            lstSucursal.setEnabled(false);
            lstTienda.setEnabled(false);
            lstPtoEmision.setEnabled(false);
            lstCorrelativo.setEnabled(false);
            btnAddUsuario.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODETALLE)) {
            txtId.setText(this.bean.getId().toString());
            txtId.setEnabled(false);
            txtSerie.setText(this.bean.getSerie());
            txtAcceso.setText(this.bean.getNomAcceso());
            txtUsuario.setText(this.bean.getNomUsuario());
            lstSucursal.setEnabled(false);
            lstTienda.setEnabled(false);
            lstPtoEmision.setEnabled(false);
            lstCorrelativo.setEnabled(false);
            btnAddUsuario.setEnabled(false);
            this.btnOperacion.setDisabled(true);
        } else if (this.modo.equals("DESACTIVAR")) {
            txtId.setText(this.bean.getId().toString());
            txtId.setEnabled(false);
            txtSerie.setText(this.bean.getSerie());
            txtAcceso.setText(this.bean.getNomAcceso());
            txtUsuario.setText(this.bean.getNomUsuario());
            lstSucursal.setEnabled(false);
            lstTienda.setEnabled(false);
            lstPtoEmision.setEnabled(false);
            lstCorrelativo.setEnabled(false);
            btnAddUsuario.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else {
            txtId.setEnabled(false);
            lstSucursal.setEnabled(true);
            lstTienda.setEnabled(true);
            lstPtoEmision.setEnabled(true);
            lstCorrelativo.setEnabled(true);
            btnAddUsuario.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        }
    }

    @Override
    public void cleanForm() {
        txtId.setText(null);
        clearUsuario();
    }

    @Override
    public void goToUICorreUser() {
        cleanForm();
    }

    public void setBean(UsuarioCorrelativoProxy bean) {
        this.bean = bean;
    }

    @Override
    public void loadSucursal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadTienda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadPuntoEmision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadCorrelativo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onChange(ChangeEvent event) {
        if (event.getSource().equals(lstSucursal)) {
            loadTienda();
            clearUsuario();
        } else if (event.getSource().equals(lstTienda)) {
            loadPuntoEmision();
            clearUsuario();
        } else if (event.getSource().equals(lstPtoEmision)) {
            loadCorrelativo();
            clearUsuario();
        } else if (event.getSource().equals(lstCorrelativo)) {
            loadSerieCorrelativo();
        }
    }

    @Override
    public void onBlur(BlurEvent event) {
        if (event.getSource().equals(lstSucursal)) {
            loadTienda();
            clearUsuario();
        } else if (event.getSource().equals(lstTienda)) {
            loadPuntoEmision();
            clearUsuario();
        } else if (event.getSource().equals(lstPtoEmision)) {
            loadCorrelativo();
        } else if (event.getSource().equals(lstCorrelativo)) {
            loadSerieCorrelativo();
        }
    }

    @Override
    public void loadSerieCorrelativo() {
        if (!lstCorrelativo.getData().isEmpty()) {
            txtSerie.setText(lstCorrelativo.getSelectedItem().getNroInicio());
        }
    }

    @Override
    public void gotoUISelectUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBeanUsuario(UsuarioProxy beanUsuario) {
        this.beanUsuario = beanUsuario;
        txtAcceso.setText(this.beanUsuario.getNick());
        txtUsuario.setText(this.beanUsuario.getNombres());
    }

    @Override
    public void clearUsuario() {
        txtAcceso.setText(null);
        txtUsuario.setText(null);
        if (beanUsuario != null) {
            beanUsuario = null;
        }
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
                Notification not=new Notification(Notification.ALERT,"Operación no contemplada");
                not.showPopup();
            }
        }
    }

    @Override
    public void processDesactivar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean isValidData() {
        if (FieldVerifier.isEmpty(txtSerie.getText())) {
            //Window.alert("Serie es un campo obligatorio");
            Notification not=new Notification(Notification.ALERT,"Serie es un campo obligatorio");
            not.showPopup();
            txtSerie.setFocus(true);
            return false;
        } else if (FieldVerifier.isEmpty(txtAcceso.getText())) {
            //Window.alert("Acceso es un campo obligatorio");
            Notification not=new Notification(Notification.ALERT,"Acceso es un campo obligatorio");
            not.showPopup();
            txtAcceso.setFocus(true);
            return false;
        } else if (FieldVerifier.isEmpty(txtUsuario.getText())) {
            //Window.alert("Usuario es un campo obligatorio");
            Notification not=new Notification(Notification.ALERT,"Usuario es un campo obligatorio");
            not.showPopup();
            txtUsuario.setFocus(true);
            return false;
        } 
        return true;
    }

    public TextBox getTxtSerie() {
        return txtSerie;
    }
    
    
}
