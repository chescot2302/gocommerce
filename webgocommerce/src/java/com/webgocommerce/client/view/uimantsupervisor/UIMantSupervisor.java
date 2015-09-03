/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantsupervisor;

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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.webgocommerce.client.beanproxy.SupervisorProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import static com.webgocommerce.client.model.UIMantenimiento.MODOINSERTAR;
import static com.webgocommerce.client.model.UIMantenimiento.MODOUPDATE;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.listmodel.ListModelMesa;
import com.webgocommerce.client.view.listmodel.ListModelPuntoEmision;
import com.webgocommerce.client.view.listmodel.ListModelSucursal;
import com.webgocommerce.client.view.listmodel.ListModelTienda;
import com.webgocommerce.client.view.uisesion.UISesion;
import com.webgocommerce.shared.FieldVerifier;

/**
 *
 * @author SISTEMAS
 */
public class UIMantSupervisor extends UIMantenimiento implements InterUIMantSupervisor, KeyUpHandler, ChangeHandler, BlurHandler{
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateBox.DefaultFormat defaultFormat=new DateBox.DefaultFormat(dateFormat);
    protected TextBox txtId;
    protected TextBox txtAlterno;
    protected ListModelMesa lstMesa;
    protected ListModelSucursal lstSucursal;
    protected ListModelTienda lstTienda;
    protected ListModelPuntoEmision lstPtoEmision;
    protected TextBox txtDni;
    protected TextBox txtNombres;
    protected TextBox txtApellidos;
    protected TextBox txtCorreo;    
    protected TextBox txtCelular;    
    protected ListBox lstCanal;
    protected DateBox dtFechaIncorporacion;
    protected SupervisorProxy bean;

    public UIMantSupervisor() {
        initComponents();
        initListener();
        initStyle();
        reCalcularWindows();
    }

    private void initComponents() {
        dtFechaIncorporacion=new DateBox(new DatePicker(),UISesion.beanInitParam.getFechaServer(),defaultFormat);        
        txtCelular=new TextBox();
        lstCanal=new ListBox();
        lstCanal.addItem("CANAL PERSONAS Y EMPRESAS");        
        lstCanal.addItem("CANAL PERSONAS");
        lstCanal.addItem("CANAL EMPRESAS");        
        lstMesa=new ListModelMesa();
        lstSucursal = new ListModelSucursal();
        lstTienda = new ListModelTienda();
        lstPtoEmision = new ListModelPuntoEmision();
        txtId = new TextBox();
        txtAlterno= new TextBox();        
        txtDni = new TextBox();
        txtDni.getElement().setAttribute("required", "required");
        txtNombres = new TextBox();
        txtNombres.getElement().setAttribute("required", "required");
        txtApellidos = new TextBox();
        txtApellidos.getElement().setAttribute("required", "required");
        txtCorreo=new TextBox();
        //this.addWidget("ID", txtId);
        this.addWidget("INCORPORACION (*)", dtFechaIncorporacion);
        this.addWidget("CANAL (*)", lstCanal);
        this.addWidget("SUCURSAL (*)", lstSucursal);
        this.addWidget("TIENDA (*)", lstTienda);
        this.addWidget("PTO. EMISION (*)", lstPtoEmision);
        this.addWidget("CODIGO ALTERNO ", txtAlterno);
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
        lstMesa.setWidth("100%");
        lstSucursal.setWidth("100%");
        lstTienda.setWidth("100%");
        lstPtoEmision.setWidth("100%");
    }

    private void initListener() {
        lstSucursal.addChangeHandler(this);
        lstSucursal.addBlurHandler(this);
        lstTienda.addChangeHandler(this);
        lstTienda.addBlurHandler(this);   
        txtApellidos.addKeyUpHandler(this);
        lstCanal.setWidth("100%");
        dtFechaIncorporacion.setWidth("100%");
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 220;
        this.scrollPanel.setHeight(alto + "px");
    }

    @Override
    public void loadFields() {
        if (this.modo.equals(UIMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getIdSupervisor().toString());
            txtDni.setText(this.bean.getDni());
            txtNombres.setText(this.bean.getNombres());
            txtApellidos.setText(this.bean.getApellidos());
            txtId.setEnabled(false);
            txtDni.setEnabled(false);
            txtNombres.setEnabled(true);
            txtApellidos.setEnabled(true);
            txtAlterno.setText(this.bean.getCodigoAlterno());
            txtAlterno.setEnabled(true);
            lstMesa.setEnabled(false);
            lstSucursal.setEnabled(true);
            lstTienda.setEnabled(true);
            lstPtoEmision.setEnabled(true);            
            txtCorreo.setText(this.bean.getCorreo());
            txtCorreo.setEnabled(true);   
            txtCelular.setText(this.bean.getCelular());
            txtCelular.setEnabled(true);   
            lstCanal.setSelectedIndex(this.bean.getCanal()==null?-1:this.bean.getCanal().equalsIgnoreCase("CANAL PERSONAS Y EMPRESAS")?0:this.bean.getCanal().equalsIgnoreCase("CANAL PERSONAS")?1:this.bean.getCanal().equalsIgnoreCase("CANAL EMPRESAS")?2:-1);
            lstCanal.setEnabled(true);
            dtFechaIncorporacion.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getIdSupervisor().toString());
            txtDni.setText(this.bean.getDni());
            txtNombres.setText(this.bean.getNombres());
            txtApellidos.setText(this.bean.getApellidos());
            txtId.setEnabled(false);
            txtDni.setEnabled(false);
            txtNombres.setEnabled(false);
            txtApellidos.setEnabled(false);
            txtAlterno.setText(this.bean.getCodigoAlterno());
            txtAlterno.setEnabled(false);
            lstMesa.setEnabled(false);
            lstSucursal.setEnabled(false);
            lstTienda.setEnabled(false);
            lstPtoEmision.setEnabled(false);            
            txtCorreo.setText(this.bean.getCorreo());
            txtCorreo.setEnabled(false);   
            txtCelular.setText(this.bean.getCelular());
            txtCelular.setEnabled(false);
            lstCanal.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODETALLE)) {
            txtId.setText(this.bean.getIdSupervisor().toString());
            txtDni.setText(this.bean.getDni());
            txtNombres.setText(this.bean.getNombres());
            txtApellidos.setText(this.bean.getApellidos());
            txtId.setEnabled(false);
            txtDni.setEnabled(false);
            txtNombres.setEnabled(false);
            txtApellidos.setEnabled(false);
            txtAlterno.setText(this.bean.getCodigoAlterno());
            txtAlterno.setEnabled(false);
            lstMesa.setEnabled(false);
            lstSucursal.setEnabled(false);
            lstTienda.setEnabled(false);
            lstPtoEmision.setEnabled(false);
            txtCorreo.setText(this.bean.getCorreo());
            txtCorreo.setEnabled(false);   
            txtCelular.setText(this.bean.getCelular());
            txtCelular.setEnabled(false);            
            lstCanal.setEnabled(false);            
            this.btnOperacion.setDisabled(true);
        } else {
            txtId.setEnabled(false);
            txtAlterno.setFocus(true);
            txtDni.setEnabled(true);
            txtDni.selectAll();
            txtDni.setEnabled(true);
            txtNombres.setEnabled(true);
            txtApellidos.setEnabled(true);
            txtAlterno.setEnabled(true);  
            lstMesa.setEnabled(false);
            lstSucursal.setEnabled(true);
            lstTienda.setEnabled(true);
            lstPtoEmision.setEnabled(true);
            txtCorreo.setEnabled(true);   
            txtCelular.setEnabled(true);
            lstCanal.setEnabled(true);
            dtFechaIncorporacion.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        }
    }

    public void setBean(SupervisorProxy bean) {
        this.bean = bean;
    }

    @Override
    public void cleanForm() {
        txtId.setText(null);
        txtDni.setText(null);
        txtNombres.setText(null);
        txtApellidos.setText(null);
        txtAlterno.setText(null);
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
    public void goToUISupervisor() {
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
    public void loadMesa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void onChange(ChangeEvent event) {
        if (event.getSource().equals(lstSucursal)) {
            loadTienda();
        } else if (event.getSource().equals(lstTienda)) {
            loadPuntoEmision();
        }
    }

    @Override
    public void onBlur(BlurEvent event) {
        if (event.getSource().equals(lstSucursal)) {
            loadTienda();
        } else if (event.getSource().equals(lstTienda)) {
            loadPuntoEmision();
        } 
    }
}
