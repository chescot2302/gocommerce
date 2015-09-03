/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uirvsalida;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.webgocommerce.client.beanproxy.MesaProxy;
import com.webgocommerce.client.model.BorderLayout;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.UIInfoDoc;
import com.webgocommerce.client.uiutil.UIInfoMoneda;
import com.webgocommerce.client.uiutil.UISearchCliente;
import com.webgocommerce.client.view.listmodel.ListModelVendedor;
import com.webgocommerce.client.view.uisesion.UISesion;
import com.webgocommerce.shared.FieldVerifier;

/**
 *
 * @author SISTEMAS
 */
public class UIrvSalida extends PopupPanel implements InterUIrvSalida, ClickHandler, KeyUpHandler,ChangeHandler {
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateBox.DefaultFormat defaultFormat=new DateBox.DefaultFormat(dateFormat);
    private BorderLayout contenedor;
    private VerticalPanel pnlHead;
    protected UISearchCliente uiSearchCliente;
    protected UIInfoDoc uiInfoDoc;
    protected UIInfoMoneda uiInfoMoneda;
    private FlexTable contentForm;
    protected ListModelVendedor lstVendedor;
    protected DateBox dtFechaEmsion;
    protected TextBox txtPlan;
    protected TextBox txtEquipos;
    protected ListBox lstMonCancela;
    protected ListBox lstCategoriaVenta;
    protected ListBox lstEstadoActual;
    protected TextBox txtProy;
    protected TextBox txtSupervisor;
    private Button btnGenerar;
    private Button btnCancelar;
    private FlexTable flexBoton; 
    protected MesaProxy beanMesa;

    public UIrvSalida() {
        initComponents();
        initListener();
        initStyle();
    }

    private void initComponents() {
        txtSupervisor=new TextBox();  
        txtSupervisor.setEnabled(false);
        txtProy=new TextBox();
        lstEstadoActual=new ListBox();
        lstEstadoActual.addItem("APROBADO");
        lstEstadoActual.addItem("ENVIADO A CREDITOS");
        lstEstadoActual.addItem("OBSERVADO");
        lstEstadoActual.addItem("OBSERVADO POR ACTIVACIONES");
        lstEstadoActual.addItem("PENDIENTE SUBSANACION");
        lstEstadoActual.addItem("APROBADO Y ENVIADO A ACTIVACIONES");
        lstEstadoActual.addItem("ENVIADO A POOL DE PORTABILIDAD");
        lstEstadoActual.addItem("APROB X ACTIVACIONES Y ENVIADO A DESPACHO");
        lstEstadoActual.addItem("APROB X DESPACHOS Y ENVIADO A ALMACEN");
        lstEstadoActual.addItem("ACTIVACION Y DESPACHO DE PORTABILIDAD");
        lstEstadoActual.addItem("RECHAZADO");
        lstEstadoActual.addItem("ANULADO");
        lstEstadoActual.addItem("PENDIENTE EN EJECUCION");
        lstEstadoActual.addItem("ATENDIDOS FIJA");
        lstCategoriaVenta=new ListBox();
        lstCategoriaVenta.addItem("ALTA NUEVA CLIENTE NUEVO");
        lstCategoriaVenta.addItem("ALTA NUEVA CLIENTE ACTUAL");
        lstCategoriaVenta.addItem("ADICIONAL/ UP GRADE");
        lstCategoriaVenta.addItem("PORTABILIDAD ENTEL");
        lstCategoriaVenta.addItem("PORTABILIDAD MOVISTAR");
        lstCategoriaVenta.addItem("PORTABILIDAD BITEL");
        lstCategoriaVenta.addItem("SERVICIOS ADICIONALES");
        dtFechaEmsion=new DateBox(new DatePicker(),UISesion.beanInitParam.getFechaServer(),defaultFormat);        
        FlexTable monDoc = new FlexTable();
        contentForm = new FlexTable();
        lstVendedor = new ListModelVendedor();
        lstMonCancela = new ListBox();
        lstMonCancela.addItem("Nuevos Soles", "S");
        lstMonCancela.addItem("Dolares", "D");
        lstMonCancela.setEnabled(false);
        txtPlan = new TextBox();
        txtPlan.setEnabled(false);
        txtEquipos = new TextBox();
        txtEquipos.setEnabled(false);
        contentForm.setWidget(0, 0, new Label("PROY / SEC"));
        contentForm.setWidget(0, 1, txtProy);
        contentForm.setWidget(1, 0, new Label("FECHA EMISION"));
        contentForm.setWidget(1, 1, dtFechaEmsion);        
        contentForm.setWidget(2, 0, new Label("ESTADO PROY."));
        contentForm.setWidget(2, 1, lstEstadoActual);        
        contentForm.setWidget(3, 0, new Label("VENDEDOR"));
        contentForm.setWidget(3, 1, lstVendedor);
        contentForm.setWidget(4, 0, new Label("SUPERVISOR"));
        contentForm.setWidget(4, 1, txtSupervisor);
        contentForm.setWidget(5, 0, new Label("TIPO DE VENTA"));
        contentForm.setWidget(5, 1, lstCategoriaVenta);
        contentForm.setWidget(6, 0, new Label("MONEDA"));
        contentForm.setWidget(6, 1, lstMonCancela);
        contentForm.setWidget(7, 0, new Label("TOTAL PLAN"));
        contentForm.setWidget(7, 1, txtPlan);
        contentForm.setWidget(8, 0, new Label("TOTAL EQUIPOS"));
        contentForm.setWidget(8, 1, txtEquipos);
        flexBoton = new FlexTable();
        btnGenerar = new Button("Generar");
        btnCancelar = new Button("Cancelar");
        flexBoton.setWidget(0, 0, btnGenerar);
        flexBoton.setWidget(0, 1, btnCancelar);
        contenedor = new BorderLayout();
        pnlHead = new VerticalPanel();
        uiSearchCliente = new UISearchCliente(false);
        uiSearchCliente.btnBuscar.setVisible(false);
        uiSearchCliente.txtRucFacturacion.setEnabled(false);
        uiInfoDoc = new UIInfoDoc();
        uiInfoDoc.lstSerieCorre.setEnabled(false);
        uiInfoDoc.lstTipoDoc.setEnabled(false);
        uiInfoDoc.txtPreImpreso.setEnabled(false);
        uiInfoDoc.lblDocumento.setVisible(false);
        uiInfoMoneda = new UIInfoMoneda();
        uiInfoMoneda.lstMoneda.setEnabled(false);
        pnlHead.add(uiSearchCliente);
        monDoc.setWidget(0, 1, uiInfoDoc);
        monDoc.setWidget(0, 2, uiInfoMoneda);
        pnlHead.add(monDoc);
        //pnlHead.add(uiInfoDoc);
        //pnlHead.add(uiInfoMoneda);
        contenedor.add(BorderLayout.NORTE, pnlHead);
        contenedor.add(BorderLayout.CENTRO, contentForm);
        contenedor.add(BorderLayout.SUR, flexBoton);
        this.add(contenedor);
        this.setGlassEnabled(true);
        this.setAnimationEnabled(true);
        this.setModal(true);
        this.setAutoHideEnabled(true);
        this.setSize("600px", "460px");
        this.center();
    }

    private void initListener() {
        btnGenerar.addClickHandler(this);
        btnCancelar.addClickHandler(this);
        lstVendedor.addChangeHandler(this);
    }

    private void initStyle() {
        contentForm.setCellSpacing(10);
        contentForm.setCellPadding(10);
        contentForm.setWidth("600px");
        lstVendedor.setWidth("100%");
        lstCategoriaVenta.setWidth("100%");
        lstEstadoActual.setWidth("100%");
        txtPlan.setWidth("100%");
        txtPlan.setHeight("32px");
        txtPlan.getElement().getStyle().setFontSize(20, Style.Unit.PX);
        txtEquipos.setWidth("100%");
        txtEquipos.setHeight("32px");
        txtEquipos.getElement().getStyle().setFontSize(20, Style.Unit.PX);
        lstMonCancela.setWidth("100%");        
        contentForm.getColumnFormatter().setWidth(0, "120px");
        flexBoton.setCellSpacing(10);
        flexBoton.setCellPadding(10);
        flexBoton.setWidth("600px");
        btnGenerar.setWidth("100%");
        btnCancelar.setWidth("100%");
    }

    @Override
    public void cancelarOperacion() {
        this.hide();
    }

    @Override
    public void onClick(ClickEvent event) {
        if (event.getSource().equals(btnCancelar)) {
            cancelarOperacion();
        } else if (event.getSource().equals(btnGenerar)) {
            generarDocumento();
        }
    }

    @Override
    public void loadField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadVendedor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
    }

    @Override
    protected void onPreviewNativeEvent(Event.NativePreviewEvent event) {
        super.onPreviewNativeEvent(event);
        switch (event.getTypeInt()) {
            case Event.ONKEYDOWN:
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    hide();
                }
                break;
        }        
    }


   

    @Override
    public void generarDocumento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isValidData() {
        if(lstVendedor.getSelectedIndex()==-1){
            //Window.alert("Seleccione vendedor");
            Notification not=new Notification(Notification.ALERT,"Seleccione vendedor");
            not.showPopup();
            return false;
        }else if(FieldVerifier.isEmpty(txtProy.getText())){
            Notification not=new Notification(Notification.ALERT,"Escriba codigo de proyecto");
            not.showPopup();
            return false;
        }else if(beanMesa==null){
            Notification not=new Notification(Notification.ALERT,"No existe supervisor");
            not.showPopup();
            return false;
        }
        return true;
    }

    @Override
    public void cleanForm() {
        txtPlan.setText(null);
        txtEquipos.setText(null);
        uiSearchCliente.txtRucFacturacion.setText(null);
        uiSearchCliente.txtDescripcionFacturacion.setText(null);
        uiInfoDoc.txtPreImpreso.setText(null);
        uiInfoDoc.lstTipoDoc.clear();
        uiInfoDoc.lstSerieCorre.clear();
    }

    @Override
    public void onChange(ChangeEvent event) {
        if(event.getSource().equals(lstVendedor)){
            getMesa();
        }
    }

    @Override
    public void getMesa() {        
    }

}
