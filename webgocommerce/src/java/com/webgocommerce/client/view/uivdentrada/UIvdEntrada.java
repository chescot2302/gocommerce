/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uivdentrada;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.webgocommerce.client.beanproxy.AlmacenProxy;
import com.webgocommerce.client.beanproxy.ClienteProxy;
import com.webgocommerce.client.beanproxy.CorrelativoProxy;
import com.webgocommerce.client.beanproxy.DetalleVentaProxy;
import com.webgocommerce.client.beanproxy.ItemProxy;
import com.webgocommerce.client.beanproxy.ItemSerieProxy;
import com.webgocommerce.client.beanproxy.PrecioItemProxy;
import com.webgocommerce.client.model.BorderLayout;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.UIControlVenta;
import com.webgocommerce.client.uiutil.UIDocMonto;
import com.webgocommerce.client.uiutil.UIGridDetalleVenta;
import com.webgocommerce.client.uiutil.UIInfoDoc;
import com.webgocommerce.client.uiutil.UIInfoMoneda;
import com.webgocommerce.client.uiutil.UILabel;
import com.webgocommerce.client.uiutil.UISearch;
import com.webgocommerce.client.uiutil.UISearchCliente;
import com.webgocommerce.client.uiutil.UISeparador;
import com.webgocommerce.client.uiutil.UIStockItemAlmacen;
import com.webgocommerce.client.view.uisesion.UISesion;
import com.webgocommerce.shared.FieldVerifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
public class UIvdEntrada extends BorderLayout implements InterUIvdEntrada, KeyUpHandler, ClickHandler, ChangeHandler, BlurHandler {    
    private VerticalPanel pnlHead;
    private FlexTable pnlInfoDocumento;
    private FlexTable pnlSearchItem;
    private HorizontalPanel pnlStatus;
    protected UISearchCliente uiSearchCliente;
    protected UIInfoDoc uiInfoDoc;
    protected UIInfoMoneda uiInfoMoneda;
    protected UISearch uiSearch;
    protected UIDocMonto uiDocMonto;
    private VerticalPanel pnlTable;
    protected UIStockItemAlmacen uiStockItemAlm;
    protected UIGridDetalleVenta uiGridDetalleVenta;
    protected UIControlVenta uiControlVenta;
    protected CorrelativoProxy beanCorrelativo;
    protected CorrelativoProxy beanCorreDespacho;
    protected ClienteProxy beanCliente;
    protected ItemProxy beanItem;
    protected PrecioItemProxy beanPrecioItem;
    protected AlmacenProxy beanAlmacen;
    protected BigDecimal montoTotalAfecto = BigDecimal.ZERO;
    protected BigDecimal montoTotalNoAfecto = BigDecimal.ZERO;
    protected BigDecimal montoTotalIgv = BigDecimal.ZERO;
    protected BigDecimal montoTotalPercepcion = BigDecimal.ZERO;
    protected BigDecimal montoTotalaPagar = BigDecimal.ZERO;
    public ToggleButton btnAfectoIgv;
    public ToggleButton btnNoAfectoIgv;
    protected CheckBox chkDesAuto;

    public UIvdEntrada() {
        initComponents();
        initListener();
        initStyle();
    }

    private void initComponents() {
        chkDesAuto=new CheckBox(" Despacho Automatico");
        chkDesAuto.setValue(Boolean.TRUE);
        btnAfectoIgv=new ToggleButton("CON IGV");
        btnAfectoIgv.setDown(true);
        btnNoAfectoIgv=new ToggleButton("SIN IGV");
        pnlHead = new VerticalPanel();
        pnlInfoDocumento = new FlexTable();
        pnlSearchItem = new FlexTable();
        uiSearch = new UISearch();
        pnlStatus = new HorizontalPanel();
        uiDocMonto = new UIDocMonto();
        uiInfoMoneda = new UIInfoMoneda();
        uiInfoMoneda.lstMoneda.setEnabled(false);
        uiStockItemAlm = new UIStockItemAlmacen();
        uiSearchCliente = new UISearchCliente(false);
        pnlInfoDocumento.setWidget(0, 2, uiSearchCliente);
        pnlInfoDocumento.setWidget(0, 3, new UISeparador());
        uiInfoDoc = new UIInfoDoc();
        pnlInfoDocumento.setWidget(0, 4, uiInfoDoc);
        pnlSearchItem.setWidget(0, 0, uiInfoMoneda);
        pnlSearchItem.setWidget(0, 1, new UISeparador());
        pnlSearchItem.setWidget(0, 2, uiSearch);
        pnlSearchItem.setWidget(0, 3, new UISeparador());
        pnlSearchItem.setWidget(0, 4, new UILabel("VENTA: "));
        pnlSearchItem.setWidget(0, 5, btnAfectoIgv);
        pnlSearchItem.setWidget(0, 6, btnNoAfectoIgv);
        pnlSearchItem.setWidget(0, 7, new UISeparador());
        pnlSearchItem.setWidget(0, 8, chkDesAuto);
        pnlHead.add(pnlInfoDocumento);
        pnlHead.add(pnlSearchItem);        
        pnlStatus.add(uiDocMonto);
        pnlTable = new VerticalPanel();
        uiGridDetalleVenta = new UIGridDetalleVenta(this);
        uiControlVenta = new UIControlVenta();
        this.add(BorderLayout.NORTE, pnlHead);
        this.add(BorderLayout.CENTRO, pnlTable);
        pnlTable.add(uiStockItemAlm);
        pnlTable.add(uiControlVenta);
        pnlTable.add(uiGridDetalleVenta);
        this.add(BorderLayout.SUR, pnlStatus);
    }

    private void initStyle() {        
        btnAfectoIgv.getElement().getStyle().setProperty("background", "#0BFF23");
        uiDocMonto.setWidth("100%");
        pnlHead.getElement().setId("pnlHead");
        pnlStatus.getElement().setId("pnlStatus");
        pnlSur.getElement().getStyle().setPosition(Style.Position.RELATIVE);
        MyResource.INSTANCE.getStlUIVdEntrada().ensureInjected();
    }

    private void initListener() {
        btnAfectoIgv.addClickHandler(this);
        btnNoAfectoIgv.addClickHandler(this);
        uiSearchCliente.btnBuscar.addClickHandler(this);
        uiControlVenta.btnGenerar.addKeyUpHandler(this);
        uiControlVenta.btnGenerar.addClickHandler(this);
        uiControlVenta.btnAgregar.addKeyUpHandler(this);
        uiControlVenta.btnAgregar.addClickHandler(this);
        uiControlVenta.btnQuitar.addKeyUpHandler(this);
        uiControlVenta.btnNuevo.addKeyUpHandler(this);
        uiControlVenta.btnQuitar.addClickHandler(this);
        uiControlVenta.btnNuevo.addClickHandler(this);
        uiInfoDoc.lstTipoDoc.addChangeHandler(this);
        uiInfoDoc.lstTipoDoc.addBlurHandler(this);
        uiInfoDoc.lstTipoDoc.addKeyUpHandler(this);
        uiInfoDoc.lstSerieCorre.addChangeHandler(this);
        uiInfoDoc.lstSerieCorre.addKeyUpHandler(this);
        uiInfoDoc.lstSerieCorre.addBlurHandler(this);
        uiInfoMoneda.lstMoneda.addKeyUpHandler(this);
        uiSearchCliente.txtRucFacturacion.addKeyUpHandler(this);
        uiSearchCliente.txtRucFacturacion.addBlurHandler(this);
        uiControlVenta.txtPrecio.txtInputPrecio.addKeyUpHandler(this);
        uiControlVenta.txtPrecio.btnCombo.addClickHandler(this);
        uiControlVenta.txtCantidad.addKeyUpHandler(this);
        uiSearch.txtBuscar.addKeyUpHandler(this);
        uiSearch.btnRefrescar.addClickHandler(this);
        //uiStockItemAlm.getGridItem().addHandler(this, ClickEvent.getType());
        uiStockItemAlm.getGridItem().addHandler(this, KeyUpEvent.getType());
        uiGridDetalleVenta.grid.addHandler(this, KeyUpEvent.getType());
        uiControlVenta.txtPrecio.gridPrecioItem.addHandler(this, KeyUpEvent.getType());
        uiStockItemAlm.getGridAlmacen().addHandler(this, KeyUpEvent.getType());
        uiStockItemAlm.getGridItem().getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                if (uiStockItemAlm.getGridItem().getSelectionModel().getSelectedObject() != null || !uiStockItemAlm.getGridItem().getSelectionModel().getSelectedObject().getUbica().isEmpty() || uiStockItemAlm.getGridItem().getSelectionModel().getSelectedObject().getUbica() != null) {
                    beanItem = uiStockItemAlm.getGridItem().getSelectionModel().getSelectedObject();
                    loadAlmacen();
                    cleanPrecioCantidad();
                }
            }
        });
        uiStockItemAlm.getGridAlmacen().getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                if (uiStockItemAlm.getGridAlmacen().getSelectionModel().getSelectedObject() != null) {
                    beanAlmacen = uiStockItemAlm.getGridAlmacen().getSelectionModel().getSelectedObject();
                    uiControlVenta.txtCantidad.setFocus(true);
                    uiControlVenta.txtCantidad.selectAll();
                }
            }
        });
        uiControlVenta.txtPrecio.gridPrecioItem.getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                if (uiControlVenta.txtPrecio.gridPrecioItem.getSelectionModel().getSelectedObject() != null) {
                    beanPrecioItem = uiControlVenta.txtPrecio.gridPrecioItem.getSelectionModel().getSelectedObject();
                    uiControlVenta.txtPrecio.txtInputPrecio.setText(beanPrecioItem.getDescripcion() + " | " + NumberFormat.getFormat("#########.##").format(beanPrecioItem.getPrecioVenta()));
                    uiControlVenta.txtPrecio.hidePopup();
                    uiControlVenta.txtPrecio.txtInputPrecio.setFocus(true);
                }
            }
        });

    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getSource().equals(uiSearch.txtBuscar)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                uiStockItemAlm.getGridItem().setFocus(true);
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL) {
                cleanDataPostItem();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {                    
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            } else {
                uiStockItemAlm.getGridItem().getDataProvider().setFilter(uiSearch.txtBuscar.getText());
                uiStockItemAlm.getGridItem().getDataProvider().refresh();
            }
        } else if (event.getSource().equals(uiStockItemAlm.getGridItem())) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER || event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SPACE) {
                itemSelected();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL) {
                cleanDataPostItem();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            }
        } else if (event.getSource().equals(uiGridDetalleVenta.grid)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL) {
                cleanDataPostItem();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            }
        } else if (event.getSource().equals(uiStockItemAlm.getGridAlmacen())) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                if (uiStockItemAlm.getGridAlmacen().getDataProvider().hasFilter()) {
                    uiStockItemAlm.getGridAlmacen().getSelectionModel().setSelected(uiStockItemAlm.getGridAlmacen().getDataProvider().resulted.get(uiStockItemAlm.getGridAlmacen().getKeyboardSelectedRow()), true);
                    //beanAlmacen=uiStockItemAlm.getGridAlmacen().getSelectionModel().getSelectedObject();
                } else {
                    uiStockItemAlm.getGridAlmacen().getSelectionModel().setSelected(uiStockItemAlm.getGridAlmacen().getData().get(uiStockItemAlm.getGridAlmacen().getKeyboardSelectedRow()), true);
                    //beanAlmacen=uiStockItemAlm.getGridAlmacen().getSelectionModel().getSelectedObject();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL) {
                cleanDataPostItem();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            }
        } else if (event.getSource().equals(uiControlVenta.txtPrecio.gridPrecioItem)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER || event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SPACE) {
                if (uiControlVenta.txtPrecio.gridPrecioItem.getDataProvider().hasFilter()) {
                    uiControlVenta.txtPrecio.gridPrecioItem.getSelectionModel().setSelected(uiControlVenta.txtPrecio.gridPrecioItem.getDataProvider().resulted.get(uiControlVenta.txtPrecio.gridPrecioItem.getKeyboardSelectedRow()), true);
                    //beanPrecioItem=uiControlVenta.txtPrecio.gridPrecioItem.getSelectionModel().getSelectedObject();
                } else {
                    uiControlVenta.txtPrecio.gridPrecioItem.getSelectionModel().setSelected(uiControlVenta.txtPrecio.gridPrecioItem.getData().get(uiControlVenta.txtPrecio.gridPrecioItem.getKeyboardSelectedRow()), true);
                    //beanPrecioItem=uiControlVenta.txtPrecio.gridPrecioItem.getSelectionModel().getSelectedObject();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                uiControlVenta.txtPrecio.hidePopup();
                uiControlVenta.txtPrecio.txtInputPrecio.setFocus(true);
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL) {
                cleanDataPostItem();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            }
        } else if (event.getSource().equals(uiControlVenta.txtCantidad)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                uiControlVenta.txtPrecio.txtInputPrecio.setFocus(true);
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL) {
                cleanDataPostItem();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            }
        } else if (event.getSource().equals(uiSearchCliente.txtRucFacturacion)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                if (!FieldVerifier.isEmpty(uiSearchCliente.txtRucFacturacion.getText())) {
                    searchCliente();
                    selectTipoDoc();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL) {
                cleanDataPostItem();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            } else {
                if (!FieldVerifier.isCadenaNumberEntero(uiSearchCliente.txtRucFacturacion.getText())) {
                    uiSearchCliente.txtRucFacturacion.setText(null);
                }
            }
        } else if (event.getSource().equals(uiControlVenta.txtPrecio.txtInputPrecio)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_DOWN) {
                loadPreciosItem();
                uiControlVenta.txtPrecio.showPopup();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                uiControlVenta.txtPrecio.hidePopup();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL) {
                cleanDataPostItem();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            } else {
                if (!FieldVerifier.isCadenaNumberEntero(uiControlVenta.txtPrecio.txtInputPrecio.getText())) {
                    uiControlVenta.txtPrecio.txtInputPrecio.setText(null);
                }
            }
        } else if (event.getSource().equals(uiInfoDoc.lstTipoDoc)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                uiInfoDoc.lstSerieCorre.setFocus(true);
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            }
        } else if (event.getSource().equals(uiInfoDoc.lstSerieCorre)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                uiInfoMoneda.lstMoneda.setFocus(true);
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            }
        } else if (event.getSource().equals(uiInfoMoneda.lstMoneda)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                uiSearch.txtBuscar.setFocus(true);
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            }
        } else if (event.getSource().equals(uiControlVenta.btnQuitar)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL) {
                cleanDataPostItem();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            }
        } else if (event.getSource().equals(uiControlVenta.btnNuevo)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL) {
                cleanDataPostItem();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            }
        } else if (event.getSource().equals(uiControlVenta.btnAgregar)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL) {
                cleanDataPostItem();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            }
        } else if (event.getSource().equals(uiControlVenta.btnGenerar)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL) {
                cleanDataPostItem();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT) {
                removeVentaDetalle();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2) {
                if (isValidData()) {
                    addVentaDetalle();
                }
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4) {
                showUIvdSalida();
            } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8) {
                cleanFormData();
            }
        } else {

        }
    }

    private void itemSelected() {
        if (uiStockItemAlm.getGridItem().getDataProvider().hasFilter()) {
            beanItem = uiStockItemAlm.getGridItem().getDataProvider().resulted.get(uiStockItemAlm.getGridItem().getKeyboardSelectedRow());
            uiStockItemAlm.getGridItem().getSelectionModel().setSelected(beanItem, true);
            //beanItem=uiStockItemAlm.getGridItem().getSelectionModel().getSelectedObject();
            //uiControlVenta.txtPrecio.setIdItem(beanItem.getId());
            //uiControlVenta.txtPrecio.setIdMoneda(uiInfoMoneda.lstMoneda.getValue(uiInfoMoneda.lstMoneda.getSelectedIndex())); ;                    
        } else {
            beanItem = uiStockItemAlm.getGridItem().getData().get(uiStockItemAlm.getGridItem().getKeyboardSelectedRow());
            uiStockItemAlm.getGridItem().getSelectionModel().setSelected(beanItem, true);
            //beanItem=uiStockItemAlm.getGridItem().getSelectionModel().getSelectedObject();
            //uiControlVenta.txtPrecio.setIdItem(beanItem.getId());
            //uiControlVenta.txtPrecio.setIdMoneda(uiInfoMoneda.lstMoneda.getValue(uiInfoMoneda.lstMoneda.getSelectedIndex())); ;                    
        }
    }

    @Override
    public void onClick(ClickEvent event) {
        if (event.getSource().equals(uiSearch.btnRefrescar)) {
            loadItem();
        } else if (event.getSource().equals(uiControlVenta.txtPrecio.btnCombo)) {
            loadPreciosItem();
            uiControlVenta.txtPrecio.showPopup();
        } else if (event.getSource().equals(uiControlVenta.btnQuitar)) {
            removeVentaDetalle();
        } else if (event.getSource().equals(uiControlVenta.btnNuevo)) {
            cleanFormData();
        } else if (event.getSource().equals(uiControlVenta.btnAgregar)) {
            if (isValidData()) {
                    addVentaDetalle();
                }
        } else if (event.getSource().equals(uiControlVenta.btnGenerar)) {
            showUIvdSalida();
        } else if (event.getSource().equals(uiSearchCliente.btnBuscar)) {
            goUISearchAddCliente();
        } else if(event.getSource().equals(btnAfectoIgv)) {
            btnAfectoIgv.setDown(true);
            btnAfectoIgv.getElement().getStyle().setProperty("background", "#0BFF23");
            btnNoAfectoIgv.setDown(false);
            btnNoAfectoIgv.getElement().getStyle().setProperty("background", "");
        } else if(event.getSource().equals(btnNoAfectoIgv)) {
            btnAfectoIgv.setDown(false);
            btnAfectoIgv.getElement().getStyle().setProperty("background", "");
            btnNoAfectoIgv.setDown(true);
            btnNoAfectoIgv.getElement().getStyle().setProperty("background", "#0BFF23");
        }
    }

    @Override
    public void loadItem() {

    }

    @Override
    public void loadAlmacen() {
    }

    @Override
    public void cleanAlmacen() {
    }

    @Override
    public void searchCliente() {
    }

    public ClienteProxy getBeanCliente() {
        return beanCliente;
    }

    public void setBeanCliente(ClienteProxy beanCliente) {
        if (beanCliente != null) {
            this.beanCliente = beanCliente;
            uiSearchCliente.txtRucFacturacion.setText(this.beanCliente.getDni().isEmpty() ? this.beanCliente.getRuc() : this.beanCliente.getDni());
            uiSearchCliente.txtDescripcionFacturacion.setText(this.beanCliente.getNombres());
            uiInfoDoc.lstTipoDoc.setFocus(true);
        } else {
            this.beanCliente = null;
            uiSearchCliente.txtRucFacturacion.setText(null);
            uiSearchCliente.txtDescripcionFacturacion.setText(null);
            uiSearchCliente.txtDescripcionFacturacion.setFocus(true);
        }
    }

    public CorrelativoProxy getBeanCorrelativo() {
        return beanCorrelativo;
    }

    public void setBeanCorrelativo(CorrelativoProxy beanCorrelativo) {
        this.beanCorrelativo = beanCorrelativo;
    }

    @Override
    public void loadDocVenta() {
    }

    @Override
    public void selectTipoDoc() {

    }

    @Override
    public void onChange(ChangeEvent event) {
        if (event.getSource().equals(uiInfoDoc.lstTipoDoc)) {
            selectTipoDoc();
        } else if (event.getSource().equals(uiInfoDoc.lstSerieCorre)) {
            selectCorrelativo();
        }
    }

    @Override
    public void onBlur(BlurEvent event) {
        if (event.getSource().equals(uiInfoDoc.lstTipoDoc)) {
            selectTipoDoc();
        } else if (event.getSource().equals(uiInfoDoc.lstSerieCorre)) {
            selectCorrelativo();
        } else if (event.getSource().equals(uiSearchCliente.txtRucFacturacion)) {
            searchCliente();
            selectTipoDoc();
        }
    }

    @Override
    public void loadCorrelativo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void selectCorrelativo() {
        uiInfoDoc.txtPreImpreso.setText(uiInfoDoc.lstSerieCorre.getSelectedItem().getPreimpreso());
        beanCorrelativo = uiInfoDoc.lstSerieCorre.getSelectedItem();
    }

    @Override
    public void loadPreciosItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cleanPrecioCantidad() {
        uiControlVenta.txtPrecio.txtInputPrecio.setText(null);
        uiControlVenta.txtCantidad.setText("0.0");
        beanPrecioItem = null;
        uiControlVenta.txtPrecio.gridPrecioItem.getSelectionModel().clear();
    }

    @Override
    public void addItem(Set<ItemSerieProxy> series) {
    }

    protected boolean validDataPreAddItem() {
        if (this.beanCliente == null) {
            //Window.alert("Seleccione Cliente");
            Notification not = new Notification(Notification.ALERT, "Seleccione Cliente");
            not.showPopup();
            uiSearchCliente.txtRucFacturacion.setFocus(true);
            return false;
        } else if (uiInfoDoc.lstSerieCorre.getSelectedIndex() == -1) {
            //Window.alert("Seleccione correlativo a usar");
            Notification not = new Notification(Notification.ALERT, "Seleccione correlativo a usar");
            not.showPopup();
            uiInfoDoc.lstSerieCorre.setFocus(true);
            return false;
        } else if (uiStockItemAlm.getGridItem().getSelectionModel().getSelectedObject() == null) {
            //Window.alert("Seleccione item a ser agregado");
            Notification not = new Notification(Notification.ALERT, "Seleccione item a ser agregado");
            not.showPopup();
            //uiStockItemAlm.getGridItem().setFocus(true);
            return false;
        } else if (uiStockItemAlm.getGridAlmacen().getSelectionModel().getSelectedObject() == null) {
            //Window.alert("Seleccione almacen");
            Notification not = new Notification(Notification.ALERT, "Seleccione almacen");
            not.showPopup();
            //uiStockItemAlm.getGridAlmacen().setFocus(true);
            return false;
        } else if (Double.parseDouble(uiControlVenta.txtCantidad.getText()) <= 0) {
            //Window.alert("Digite una cantidad mayor a cero");
            Notification not = new Notification(Notification.ALERT, "Digite una cantidad mayor a cero");
            not.showPopup();
            uiControlVenta.txtCantidad.setFocus(true);
            uiControlVenta.txtCantidad.selectAll();
            return false;
        } else if (this.beanPrecioItem == null) {
            //Window.alert("Seleccione precio de lista");
            Notification not = new Notification(Notification.ALERT, "Seleccione precio de lista");
            not.showPopup();
            return false;
        } else if(!(UISesion.beanTipoCambio.getTcVenta().compareTo(BigDecimal.ZERO)==1)){
            Notification not = new Notification(Notification.ALERT, "Tipo de Cambio debe ser mayor a cero");
            not.showPopup();
            return false;
        }
        return true;
    }

    protected boolean maxDetalleValid() {
        boolean resultado = false;
        if (uiInfoDoc.lstTipoDoc.getSelectedItem().getId().equalsIgnoreCase("01")) {
            if (UISesion.beanInitParam.getMaxDetalleFac() > uiGridDetalleVenta.grid.getData().size()) {
                resultado = true;
            } else {
                //Window.alert("No se puede agregar el item\nEl maximo numero de detalles es: " + UISesion.beanInitParam.getMaxDetalleFac());
                Notification not = new Notification(Notification.ALERT, "No se puede agregar el item</br>El maximo numero de detalles es: " + UISesion.beanInitParam.getMaxDetalleFac());
                not.showPopup();
                return resultado;
            }
        } else if (uiInfoDoc.lstTipoDoc.getSelectedItem().getId().equalsIgnoreCase("03")) {
            if (UISesion.beanInitParam.getMaxDetalleBol() > uiGridDetalleVenta.grid.getData().size()) {
                resultado = true;
            } else {
                //Window.alert("No se puede agregar el item\nEl maximo numero de detalles es: " + UISesion.beanInitParam.getMaxDetalleBol());
                Notification not = new Notification(Notification.ALERT, "No se puede agregar el item</br>El maximo numero de detalles es: " + UISesion.beanInitParam.getMaxDetalleBol());
                not.showPopup();
                return resultado;
            }
        } else if (uiInfoDoc.lstTipoDoc.getSelectedItem().getId().equalsIgnoreCase("31")) {
            if (UISesion.beanInitParam.getMaxDetalleCot() > uiGridDetalleVenta.grid.getData().size()) {
                resultado = true;
            } else {
                //Window.alert("No se puede agregar el item\nEl maximo numero de detalles es: " + UISesion.beanInitParam.getMaxDetalleCot());
                Notification not = new Notification(Notification.ALERT, "No se puede agregar el item</br>El maximo numero de detalles es: " + UISesion.beanInitParam.getMaxDetalleCot());
                not.showPopup();
                return resultado;
            }
        }
        return resultado;
    }

    protected boolean existeCtaVentaItem() {
        if (this.beanItem.getCtaVenta().equalsIgnoreCase("SC")) {
            //Window.alert("Configurar cuenta 70 para Item");
            Notification not = new Notification(Notification.ALERT, "Configurar cuenta 70 para Item");
            not.showPopup();
            return false;
        }
        return true;
    }

    protected boolean almacenCorrecto() {
        if (UISesion.beanUsuario.getIdAlmacen().equalsIgnoreCase(this.beanAlmacen.getId())) {
            return true;
        }
        //Window.alert("Seleccione almacen asignado\n(Verde) Almacen apto para vender\n(Amarillo) Almacen de consulta");
        Notification not = new Notification(Notification.ALERT, "Seleccione almacen asignado</br>(Verde) Almacen apto para vender</br>(Amarillo) Almacen de consulta");
        not.showPopup();
        return false;
    }

    protected boolean existeStock() {
        if (Double.parseDouble(uiControlVenta.txtCantidad.getText()) > beanAlmacen.getStockItemAlm()) {
            //Window.alert("Cantidad solicitada sobrepasa\nel stock disponible de: " + beanAlmacen.getStockItemAlm());
            Notification not = new Notification(Notification.ALERT, "Cantidad solicitada sobrepasa</br>el stock disponible de: " + beanAlmacen.getStockItemAlm());
            not.showPopup();
            return false;
        }
        return true;
    }

    protected boolean esPrecioMayorACosto() {
        if (beanPrecioItem.getPrecioVenta().compareTo(beanItem.getCostoSoles()) == -11) {
            //Window.alert("El precio de lista de " + beanPrecioItem.getPrecioVenta() + "\nes menor al costo de " + beanItem.getCostoSoles());
            Notification not = new Notification(Notification.ALERT, "El precio de lista de " + beanPrecioItem.getPrecioVenta() + "</br>es menor al costo de " + beanItem.getCostoSoles());
            not.showPopup();
            return false;
        }
        return true;
    }

    public void cleanDataPostItem() {
        uiStockItemAlm.getGridAlmacen().getSelectionModel().clear();
        uiStockItemAlm.getGridAlmacen().setData(new ArrayList<AlmacenProxy>());
        uiGridDetalleVenta.grid.getSelectionModel().clear();
        cleanPrecioCantidad();
        uiSearch.txtBuscar.setText(null);
        uiSearch.txtBuscar.setFocus(true);
        uiStockItemAlm.getGridItem().getSelectionModel().clear();
        uiStockItemAlm.getGridItem().getDataProvider().resetFilter();
    }

    @Override
    public void removeItem(List<ItemSerieProxy> series,DetalleVentaProxy bean){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showUIvdSalida() {
        //Window.alert("Mostrar pantalla para seleccionar vendedor y forma de pago");
        Notification not = new Notification(Notification.ALERT, "Mostrar pantalla para seleccionar vendedor y forma de pago");
        not.showPopup();
    }

    @Override
    public void loadDetalleItem(DetalleVentaProxy bean,String modo) {

    }

    @Override
    public void calcularMontoTotales() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resetMontoTotales() {
        montoTotalAfecto = BigDecimal.ZERO;
        montoTotalNoAfecto = BigDecimal.ZERO;
        montoTotalIgv = BigDecimal.ZERO;
        montoTotalPercepcion = BigDecimal.ZERO;
        montoTotalaPagar = BigDecimal.ZERO;
        uiDocMonto.txtAfecto.setText(null);
        uiDocMonto.txtNoAfecto.setText(null);
        uiDocMonto.txtIgv.setText(null);
        uiDocMonto.txtPercepcion.setText(null);
        uiDocMonto.txtTotal.setText(null);
    }

    public UIInfoMoneda getUiInfoMoneda() {
        return uiInfoMoneda;
    }

    public BigDecimal getMontoTotalAfecto() {
        return montoTotalAfecto;
    }

    public BigDecimal getMontoTotalNoAfecto() {
        return montoTotalNoAfecto;
    }

    public BigDecimal getMontoTotalIgv() {
        return montoTotalIgv;
    }

    public BigDecimal getMontoTotalPercepcion() {
        return montoTotalPercepcion;
    }

    public BigDecimal getMontoTotalaPagar() {
        return montoTotalaPagar;
    }

    public UIGridDetalleVenta getUiGridDetalleVenta() {
        return uiGridDetalleVenta;
    }

    @Override
    public void cleanFormData() {
        beanCorrelativo = null;
        beanCliente = null;
        beanItem = null;
        beanAlmacen = null;
        uiSearchCliente.txtRucFacturacion.setText(null);
        uiSearchCliente.txtDescripcionFacturacion.setText(null);
        uiGridDetalleVenta.grid.setData(new ArrayList<DetalleVentaProxy>());
        uiGridDetalleVenta.grid.getSelectionModel().clear();
        uiGridDetalleVenta.grid.actualizarGrid();
        resetMontoTotales();
        cleanDataPostItem();
        loadCorrelativo();
    }

    @Override
    public void goUISearchAddCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isValidData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeItemSerie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addVentaDetalle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public UIControlVenta getUiControlVenta() {
        return uiControlVenta;
    }

    public ItemProxy getBeanItem() {
        return beanItem;
    }

    @Override
    public void removeVentaDetalle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CorrelativoProxy getBeanCorreDespacho() {
        return beanCorreDespacho;
    }

    public void setBeanCorreDespacho(CorrelativoProxy beanCorreDespacho) {
        this.beanCorreDespacho = beanCorreDespacho;
    }

    public ToggleButton getBtnAfectoIgv() {
        return btnAfectoIgv;
    }

    public void setBtnAfectoIgv(ToggleButton btnAfectoIgv) {
        this.btnAfectoIgv = btnAfectoIgv;
    }

    public ToggleButton getBtnNoAfectoIgv() {
        return btnNoAfectoIgv;
    }

    public void setBtnNoAfectoIgv(ToggleButton btnNoAfectoIgv) {
        this.btnNoAfectoIgv = btnNoAfectoIgv;
    }

    @Override
    public void loadFamilia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CheckBox getChkDesAuto() {
        return chkDesAuto;
    }

    public UISearchCliente getUiSearchCliente() {
        return uiSearchCliente;
    }

    public UIInfoDoc getUiInfoDoc() {
        return uiInfoDoc;
    }
    
    

}
