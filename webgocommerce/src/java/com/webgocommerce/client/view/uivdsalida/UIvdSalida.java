/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uivdsalida;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.webgocommerce.client.model.BorderLayout;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.UIInfoDoc;
import com.webgocommerce.client.uiutil.UIInfoMoneda;
import com.webgocommerce.client.uiutil.UISearchCliente;
import com.webgocommerce.client.view.listmodel.ListModelCondicionVenta;
import com.webgocommerce.client.view.listmodel.ListModelTipoVenta;
import com.webgocommerce.client.view.listmodel.ListModelVendedor;
import com.webgocommerce.client.view.uisesion.UISesion;
import com.webgocommerce.shared.FieldVerifier;
import java.math.BigDecimal;

/**
 *
 * @author SISTEMAS
 */
public class UIvdSalida extends PopupPanel implements InterUIvdSalida, ClickHandler, KeyUpHandler {
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
    protected ListModelTipoVenta lstTipoVenta;
    protected ListModelCondicionVenta lstCondVenta;
    protected TextBox txtAcobrar;
    protected ListBox lstMonCancela;
    protected ListBox lstCategoriaVenta;
    protected TextBox txtRecibido;
    protected TextBox txtVuelto;
    private Button btnGenerar;
    private Button btnCancelar;
    private FlexTable flexBoton;
    protected BigDecimal vuelto = BigDecimal.ZERO;
    protected BigDecimal recibido = BigDecimal.ZERO;    

    public UIvdSalida() {
        initComponents();
        initListener();
        initStyle();
    }

    private void initComponents() {
        lstCategoriaVenta=new ListBox();
        lstCategoriaVenta.addItem("VENTA NUEVA");
        lstCategoriaVenta.addItem("PORTABILIDAD");
        lstCategoriaVenta.addItem("RENOVACION");
        dtFechaEmsion=new DateBox(new DatePicker(),UISesion.beanInitParam.getFechaServer(),defaultFormat);        
        FlexTable monDoc = new FlexTable();
        contentForm = new FlexTable();
        lstVendedor = new ListModelVendedor();
        lstTipoVenta = new ListModelTipoVenta();
        lstCondVenta = new ListModelCondicionVenta();
        lstMonCancela = new ListBox();
        lstMonCancela.addItem("Nuevos Soles", "S");
        lstMonCancela.addItem("Dolares", "D");
        lstMonCancela.setEnabled(false);
        txtRecibido = new TextBox();
        txtAcobrar = new TextBox();
        txtAcobrar.setEnabled(false);
        txtVuelto = new TextBox();
        txtVuelto.setEnabled(false);
        contentForm.setWidget(0, 0, new Label("FECHA EMISION"));
        contentForm.setWidget(0, 1, dtFechaEmsion);
        contentForm.setWidget(1, 0, new Label("VENDEDOR"));
        contentForm.setWidget(1, 1, lstVendedor);
        contentForm.setWidget(2, 0, new Label("TIPO DE VENTA"));
        contentForm.setWidget(2, 1, lstTipoVenta);
        contentForm.setWidget(3, 0, new Label("CATEGORIA DE VENTA"));
        contentForm.setWidget(3, 1, lstCategoriaVenta);
        contentForm.setWidget(4, 0, new Label("CONDICION VENTA"));
        contentForm.setWidget(4, 1, lstCondVenta);
        contentForm.setWidget(5, 0, new Label("CANCELA EN"));
        contentForm.setWidget(5, 1, lstMonCancela);
        contentForm.setWidget(6, 0, new Label("RECIBIDO"));
        contentForm.setWidget(6, 1, txtRecibido);
        contentForm.setWidget(7, 0, new Label("A COBRAR"));
        contentForm.setWidget(7, 1, txtAcobrar);
        contentForm.setWidget(8, 0, new Label("VUELTO"));
        contentForm.setWidget(8, 1, txtVuelto);
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
        txtRecibido.addKeyUpHandler(this);
    }

    private void initStyle() {
        contentForm.setCellSpacing(10);
        contentForm.setCellPadding(10);
        contentForm.setWidth("600px");
        lstVendedor.setWidth("100%");
        lstCategoriaVenta.setWidth("100%");
        lstTipoVenta.setWidth("100%");
        lstCondVenta.setWidth("100%");
        txtAcobrar.setWidth("100%");
        txtAcobrar.setHeight("32px");
        txtAcobrar.getElement().getStyle().setFontSize(20, Style.Unit.PX);
        lstMonCancela.setWidth("100%");
        txtRecibido.setWidth("100%");
        txtRecibido.setHeight("32px");
        txtRecibido.getElement().getStyle().setFontSize(20, Style.Unit.PX);
        txtVuelto.setWidth("100%");
        txtVuelto.setHeight("32px");
        txtVuelto.getElement().getStyle().setFontSize(20, Style.Unit.PX);
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
    public void loadCondicionVenta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if(event.getSource().equals(txtRecibido)){
            calcularVuelto();
        }
    }

    @Override
    protected void onPreviewNativeEvent(NativePreviewEvent event) {
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
    public void loadTipoVenta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void calcularVuelto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        }else if(lstTipoVenta.getSelectedIndex()==-1){
            //Window.alert("Seleccione tipo de venta");
            Notification not=new Notification(Notification.ALERT,"Seleccione tipo de venta");
            not.showPopup();
            return false;
        }else if(lstCondVenta.getSelectedIndex()==-1){
            //Window.alert("Seleccione condicion de venta");
            Notification not=new Notification(Notification.ALERT,"Seleccione condicion de venta");
            not.showPopup();
            return false;
        }else if(lstCondVenta.getSelectedIndex()==-1){
            //Window.alert("Seleccione condicion de venta");
            Notification not=new Notification(Notification.ALERT,"Seleccione condicion de venta");
            not.showPopup();
            return false;
        }else if(FieldVerifier.notIsNumberPositivo(txtRecibido.getText(), "Monto recibido")){
            return false;
        }else if(recibido.compareTo(BigDecimal.ZERO)==0){
            //Window.alert("Monto a recibir debe ser mayor a cero");
            Notification not=new Notification(Notification.ALERT,"Monto a recibir debe ser mayor a cero");
            not.showPopup();
            return false;
        }else if(vuelto.compareTo(BigDecimal.ZERO)==-1){
            //Window.alert("Monto recibido debe ser igual o mayor a monto a cobrar");
            Notification not=new Notification(Notification.ALERT,"Monto recibido debe ser igual o mayor a monto a cobrar");
            not.showPopup();
            return false;
        }
        return true;
    }

    @Override
    public void cleanForm() {
        vuelto = BigDecimal.ZERO;
        recibido = BigDecimal.ZERO; 
        txtAcobrar.setText(null);
        txtRecibido.setText(null);
        txtVuelto.setText(null);
        uiSearchCliente.txtRucFacturacion.setText(null);
        uiSearchCliente.txtDescripcionFacturacion.setText(null);
        uiInfoDoc.txtPreImpreso.setText(null);
        uiInfoDoc.lstTipoDoc.clear();
        uiInfoDoc.lstSerieCorre.clear();
    }

}
