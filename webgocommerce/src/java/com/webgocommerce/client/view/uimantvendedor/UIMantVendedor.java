/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantvendedor;

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
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.webgocommerce.client.beanproxy.VendedorProxy;
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
public class UIMantVendedor extends UIMantenimiento implements InterUIMantVendedor, KeyUpHandler, ChangeHandler, BlurHandler {
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateBox.DefaultFormat defaultFormat=new DateBox.DefaultFormat(dateFormat);
    protected TextBox txtId;
    protected TextBox txtAlterno;
    protected ListModelMesa lstMesa;
    protected ListModelSucursal lstSucursal;
    protected ListModelTienda lstTienda;
    protected ListModelPuntoEmision lstPtoEmision;
    protected TextBox txtDni;
    protected TextBox txtPrimerNom;
    protected TextBox txtSegundoNom;
    protected TextBox txtApPaterno;
    protected TextBox txtApMaterno;    
    protected TextBox txtDescripcion;    
    protected TextBox txtCorreo;    
    protected TextBox txtCelular;    
    protected ListBox lstCanal;
    protected DateBox dtFechaIncorporacion;
    protected VendedorProxy bean;

    public UIMantVendedor() {
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
        txtDescripcion = new TextBox();
        txtDescripcion.getElement().setAttribute("required", "required");
        txtDni= new TextBox();
        txtDni.getElement().setAttribute("required", "required");
        txtDni.setMaxLength(8);
        txtPrimerNom=new TextBox();
        txtPrimerNom.getElement().setAttribute("required", "required");
        txtSegundoNom=new TextBox();
        txtApPaterno=new TextBox();
        txtApPaterno.getElement().setAttribute("required", "required");
        txtApMaterno=new TextBox();
        txtCorreo=new TextBox();
        //this.addWidget("ID", txtId);
        //this.addWidget("MESA (*)", lstMesa);
        this.addWidget("INCORPORACION (*)", dtFechaIncorporacion);
        this.addWidget("CANAL (*)", lstCanal);
        this.addWidget("SUCURSAL (*)", lstSucursal);
        this.addWidget("TIENDA (*)", lstTienda);
        this.addWidget("PTO. EMISION (*)", lstPtoEmision);
        this.addWidget("CODIGO ALTERNO ", txtAlterno);
        this.addWidget("DNI (*)", txtDni);        
        this.addWidget("PRIMER NOMBRE (*)", txtPrimerNom);
        this.addWidget("SEGUNDO NOMBRE ", txtSegundoNom);
        this.addWidget("APELLIDO PATERNO (*)", txtApPaterno);
        this.addWidget("APELLIDO MATERNO ", txtApMaterno);
        this.addWidget("CORREO ELECTRONICO ", txtCorreo);
        this.addWidget("CELULAR ", txtCelular);
        //this.addWidget("DESC. NAVA (*)", txtDescripcion);
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
        txtDni.addKeyUpHandler(this);
    }
    
    private void initStyle(){
        lstCanal.setWidth("100%");
        lstMesa.setWidth("100%");
        lstSucursal.setWidth("100%");
        lstTienda.setWidth("100%");
        lstPtoEmision.setWidth("100%");
        dtFechaIncorporacion.setWidth("100%");
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 220;
        this.scrollPanel.setHeight(alto + "px");
    }

    @Override
    public void loadFields() {
        if (this.modo.equals(UIMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getIdVendedor());
            txtDescripcion.setText(this.bean.getNomVendedor());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtAlterno.setText(this.bean.getCodigoAlterno());
            txtAlterno.setEnabled(true);
            txtDni.setText(this.bean.getDni());
            txtDni.setEnabled(false);
            txtPrimerNom.setText(this.bean.getPrimerNombre());
            txtPrimerNom.setEnabled(true);
            txtSegundoNom.setText(this.bean.getSegundoNombre());
            txtSegundoNom.setEnabled(true);
            txtApPaterno.setText(this.bean.getApellidoPaterno());
            txtApPaterno.setEnabled(true);
            txtApMaterno.setText(this.bean.getApellidoMaterno());
            txtApMaterno.setEnabled(true);            
            txtCorreo.setText(this.bean.getCorreo());
            txtCorreo.setEnabled(true);   
            txtCelular.setText(this.bean.getCelular());
            txtCelular.setEnabled(true);   
            lstCanal.setSelectedIndex(this.bean.getCanal()==null?-1:this.bean.getCanal().equalsIgnoreCase("CANAL PERSONAS Y EMPRESAS")?0:this.bean.getCanal().equalsIgnoreCase("CANAL PERSONAS")?1:this.bean.getCanal().equalsIgnoreCase("CANAL EMPRESAS")?2:-1);
            lstMesa.setEnabled(false);
            lstCanal.setEnabled(true);
            lstSucursal.setEnabled(true);
            lstTienda.setEnabled(true);
            lstPtoEmision.setEnabled(true);
            dtFechaIncorporacion.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getIdVendedor());
            txtDescripcion.setText(this.bean.getNomVendedor());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtAlterno.setText(this.bean.getCodigoAlterno());
            txtAlterno.setEnabled(false);
            txtDni.setText(this.bean.getDni());
            txtDni.setEnabled(false);
            txtPrimerNom.setText(this.bean.getPrimerNombre());
            txtPrimerNom.setEnabled(false);
            txtSegundoNom.setText(this.bean.getSegundoNombre());
            txtSegundoNom.setEnabled(false);
            txtApPaterno.setText(this.bean.getApellidoPaterno());
            txtApPaterno.setEnabled(false);
            txtApMaterno.setText(this.bean.getApellidoMaterno());
            txtApMaterno.setEnabled(false);
            txtCorreo.setText(this.bean.getCorreo());
            txtCorreo.setEnabled(false);   
            txtCelular.setText(this.bean.getCelular());
            txtCelular.setEnabled(false);
            lstMesa.setEnabled(false);
            lstCanal.setEnabled(false);
            lstSucursal.setEnabled(false);
            lstTienda.setEnabled(false);
            lstPtoEmision.setEnabled(false);
            dtFechaIncorporacion.setEnabled(false);
            this.btnOperacion.setDisabled(false);            
        } else if (this.modo.equals(UIMantenimiento.MODODETALLE)) {
            txtId.setText(this.bean.getIdVendedor());
            txtDescripcion.setText(this.bean.getNomVendedor());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtAlterno.setText(this.bean.getCodigoAlterno());
            txtAlterno.setEnabled(false);
            txtDni.setText(this.bean.getDni());
            txtDni.setEnabled(false);
            txtPrimerNom.setText(this.bean.getPrimerNombre());
            txtPrimerNom.setEnabled(false);
            txtSegundoNom.setText(this.bean.getSegundoNombre());
            txtSegundoNom.setEnabled(false);
            txtApPaterno.setText(this.bean.getApellidoPaterno());
            txtApPaterno.setEnabled(false);
            txtApMaterno.setText(this.bean.getApellidoMaterno());
            txtApMaterno.setEnabled(false);
            txtCorreo.setText(this.bean.getCorreo());
            txtCorreo.setEnabled(false);   
            txtCelular.setText(this.bean.getCelular());
            txtCelular.setEnabled(false);
            lstMesa.setEnabled(false);
            lstCanal.setEnabled(false);
            lstSucursal.setEnabled(false);
            lstTienda.setEnabled(false);
            lstPtoEmision.setEnabled(false);
            dtFechaIncorporacion.setEnabled(false);
            this.btnOperacion.setDisabled(true);
        } else {
            txtId.setEnabled(false);           
            txtDescripcion.setEnabled(true);            
            txtAlterno.setEnabled(true);           
            txtDni.setEnabled(true);            
            txtPrimerNom.setEnabled(true);            
            txtSegundoNom.setEnabled(true);            
            txtApPaterno.setEnabled(true);            
            txtApMaterno.setEnabled(true);
            txtCorreo.setEnabled(true);            
            txtCelular.setEnabled(true);
            lstMesa.setEnabled(false);
            lstCanal.setEnabled(true);
            lstSucursal.setEnabled(true);
            lstTienda.setEnabled(true);
            lstPtoEmision.setEnabled(true);
            dtFechaIncorporacion.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        }
    }

    public void setBean(VendedorProxy bean) {
        this.bean = bean;
    }

    @Override
    public void cleanForm() {
        txtId.setText(null);
        txtDescripcion.setText(null);
        txtAlterno.setText(null);
        txtDni.setText(null);
        txtPrimerNom.setText(null);
        txtSegundoNom.setText(null);
        txtApPaterno.setText(null);
        txtApMaterno.setText(null);
        txtCorreo.setText(null);
        txtCelular.setText(null);
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getSource().equals(txtApMaterno)) {
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
        }else if(event.getSource().equals(txtDni)){
            if(!FieldVerifier.isCadenaNumberEntero(txtDni.getText())){
                txtDni.setText(null);
            }
        }
    }

    @Override
    public void goToUIVendedor() {
        cleanForm();        
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
    
    @Override
    public boolean isValidData() {
        if (!FieldVerifier.isValidDNI(txtDni.getText())) {
            //Window.alert("DNI debe tener 8 digitos");
            Notification not=new Notification(Notification.ALERT,"DNI debe tener 8 digitos");
            not.showPopup();
            txtDni.setText(null);
            txtDni.setFocus(true);
            return false;
        } else if (FieldVerifier.isEmpty(txtPrimerNom.getText())) {
            //Window.alert("Primer nombre es un campo obligatorio");
            Notification not=new Notification(Notification.ALERT,"Primer nombre es un campo obligatorio");
            not.showPopup();
            txtPrimerNom.setFocus(true);
            return false;
        } else if (FieldVerifier.isEmpty(txtApPaterno.getText())) {
            //Window.alert("Apellido paterno es un campo obligatorio");
            Notification not=new Notification(Notification.ALERT,"Apellido paterno es un campo obligatorio");
            not.showPopup();
            txtApPaterno.setFocus(true);
            return false;
        }
        return true;
    }

    @Override
    public void loadMesa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
