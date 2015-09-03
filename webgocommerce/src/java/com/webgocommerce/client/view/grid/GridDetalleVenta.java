/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.webgocommerce.client.beanproxy.DetalleVentaProxy;
import com.webgocommerce.client.requestfactory.ContextGestionFacturacion;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uiitemserie.UIItemSerie;
import com.webgocommerce.client.view.uisesion.UISesionImpl;
import com.webgocommerce.client.view.uivdentrada.UIvdEntrada;
import com.webgocommerce.shared.FieldVerifier;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridDetalleVenta extends DataGrid<DetalleVentaProxy> {

    private UIvdEntrada uiPadre;
    private final FactoryGestion FACTORY = com.google.gwt.core.client.GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private List<DetalleVentaProxy> data = new ArrayList<DetalleVentaProxy>();
    private final SingleSelectionModel<DetalleVentaProxy> selectionModel = new SingleSelectionModel<DetalleVentaProxy>();
    private FilteredListDataProvider<DetalleVentaProxy> dataProvider = new FilteredListDataProvider<DetalleVentaProxy>(new IFilter<DetalleVentaProxy>() {

        @Override
        public boolean isValid(DetalleVentaProxy value, String filter) {
            if (filter == null || value == null) {
                return true;
            } else {
                String values = value.getSecuencia().toString();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridDetalleVenta(UIvdEntrada ui) {
        uiPadre = ui;
        initComponents();
        //initStyle();
    }

    private void initComponents() {
        this.setWidth("100%");
        this.setHeight("90%");
        initColumns();
        initEditColumns();
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        dataProvider.setList(data);
        dataProvider.addDataDisplay(this);
        this.setVisible(true);
        this.setSelectionModel(selectionModel);
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(this);
        pager.setVisible(true);
    }

    private void initColumns() {
        //this.addColumn(secuencia, "#");
        //this.addColumn(ver, "");
        this.addColumn(codigo, "CODIGO");
        this.addColumn(marca, "MARCA");
        this.addColumn(descripcion, "DESCRIPCION DE ITEM");
        this.addColumn(cantidad, "CANT.");
        this.addColumn(unidadMedida, "U.M");
        this.addColumn(precio, "P.UNIT");
        this.addColumn(porcDescuento, "Dscto%");
        this.addColumn(valorVenta, "V.VENTA");
        this.addColumn(totalVenta, "T.VENTA");
        this.addColumn(plan, "PLAN");
        this.addColumn(precioPlan, "P. PLAN");
        this.addColumn(vigencia, "VIGENCIA");
        this.setColumnWidth(ver, 4, Unit.EM);
        this.setColumnWidth(cantidad, 9, Unit.EM);
        this.setColumnWidth(precio, 10, Unit.EM);
        this.setColumnWidth(valorVenta, 10, Unit.EM);
        this.setColumnWidth(plan, 30, Unit.EM);
        this.setColumnWidth(precioPlan, 10, Unit.EM);
        this.setColumnWidth(vigencia, 9, Unit.EM);
        this.setColumnWidth(totalVenta, 10, Unit.EM);
        this.setColumnWidth(porcDescuento, 9, Unit.EM);
        this.setColumnWidth(unidadMedida, 6, Unit.EM);
        this.setColumnWidth(codigo, 10, Unit.EM);
        this.setColumnWidth(marca, 15, Unit.EM);
        this.setColumnWidth(descripcion, 25, Unit.EM);
    }

    private void initEditColumns() {
        ver.setFieldUpdater(new FieldUpdater<DetalleVentaProxy, String>() {

            @Override
            public void update(int index, DetalleVentaProxy object, String value) {
                if (object.getLserie() == 1) {
                    //Window.alert("Tamaño: "+object.getSeries().size());
                    if (object.getSeries().size() > 0) {
                        UIItemSerie ui = new UIItemSerie();
                        ui.getGrid().setData(object.getSeries());
                        ui.getBtnOperacion().setVisible(false);
                        ui.show();
                    }
                } else {
                    Notification not = new Notification(Notification.ALERT, "Item no maneja series");
                    not.showPopup();
                }
            }
        });

        precio.setFieldUpdater(new FieldUpdater<DetalleVentaProxy, String>() {

            @Override
            public void update(int index, DetalleVentaProxy object, String value) {
                if (FieldVerifier.notIsNumberPositivo(value, "Precio")) {
                    value = "0.0";
                }
                calcularMontos(index, object, value);
            }
        });

    }

    private void calcularMontos(int index, DetalleVentaProxy object, String value) {
        if (!FieldVerifier.notIsNumberPositivo(value, "Precio")) {
            ContextGestionFacturacion context = FACTORY.contextGestionFacturacion();
            FACTORY.initialize(EVENTBUS);
            DetalleVentaProxy beanEdit = context.edit(object);
            BigDecimal valPrecioVenta = BigDecimal.valueOf(Double.parseDouble(value));
            BigDecimal valorVenta = valPrecioVenta.divide(UISesionImpl.beanInitParam.getIgv().divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP).add(BigDecimal.ONE), 5, RoundingMode.HALF_UP);
            beanEdit.setPrecio(valPrecioVenta);
            beanEdit.setPrecioUnitario(valorVenta);
            BigDecimal totalVenta = beanEdit.getCantidad().multiply(valPrecioVenta);
            BigDecimal valValorVenta = BigDecimal.ZERO;
            if (/*UISesion.beanInitParam.getFrontera()==0*/uiPadre.btnAfectoIgv.isDown()) {
                valValorVenta = totalVenta.divide(UISesionImpl.beanInitParam.getIgv().divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP).add(BigDecimal.ONE), 5, RoundingMode.HALF_UP);
            } else if (/*UISesion.beanInitParam.getFrontera()==1*/uiPadre.btnNoAfectoIgv.isDown()) {
                valValorVenta = totalVenta;
            } else {
                Window.alert("Error: Valor de frontera no comteplado");
                return;
            }
            beanEdit.setValorVenta(valValorVenta);
            beanEdit.setTotalVenta(totalVenta);
            beanEdit.setMontoAfecto(valValorVenta);
            beanEdit.setPorcDescuento(BigDecimal.ZERO);
            beanEdit.setTotalNeto(totalVenta);
            beanEdit.setMontoPercepcion(BigDecimal.ZERO);
            BigDecimal montoIgv = totalVenta.subtract(valValorVenta);
            beanEdit.setMontoIgv(montoIgv);
            beanEdit.setMontoNoAfecto(BigDecimal.ZERO);

            int indexModel = data.indexOf(object);
            //GWT.log("Index Modelo: " + indexModel);
            data.set(indexModel, beanEdit);
            context.fire();
            dataProvider.refresh();
            // Update the row and subrow.
            setKeyboardSelectedRow(index, 0, true);
            // Update the column index.
            setKeyboardSelectedColumn(5, true);
            uiPadre.calcularMontoTotales();
        }
    }

    private void initStyle() {
        MyResource.INSTANCE.getStlGridData().ensureInjected();
        this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
    }

    private Column<DetalleVentaProxy, String> ver
            = new Column<DetalleVentaProxy, String>(new ButtonCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    if (object.getLserie() == 1) {
                        return "Ver";
                    } else {
                        return null;
                    }
                }

            };
    
    private Column<DetalleVentaProxy, String> plan
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getPlan();
                }

            };

    private Column<DetalleVentaProxy, String> codigo
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getCodigo();
                }

            };

    private Column<DetalleVentaProxy, String> marca
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getMarca();
                }

            };

    private Column<DetalleVentaProxy, String> descripcion
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getDescripcion();
                }

            };

    private Column<DetalleVentaProxy, Number> secuencia
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getSecuencia();
                }
            };

    private Column<DetalleVentaProxy, Number> cantidad
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getCantidad();
                }
            };
    
    private Column<DetalleVentaProxy, Number> precioPlan
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getPrecioPlan();
                }
            };
    
    private Column<DetalleVentaProxy, Number> vigencia
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getVigencia();
                }
            };

    /*private Column<DetalleVentaProxy, Number> precio
     = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

     @Override
     public Number getValue(DetalleVentaProxy object) {
     return object.getPrecio();
     }
     };*/
    private Column<DetalleVentaProxy, String> precio
            = new Column<DetalleVentaProxy, String>(new TextInputCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getPrecio().toString();
                }

            };

    private Column<DetalleVentaProxy, Number> porcDescuento
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getPorcDescuento();
                }
            };

    private Column<DetalleVentaProxy, Number> valorVenta
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getValorVenta();
                }
            };

    private Column<DetalleVentaProxy, Number> totalVenta
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getTotalVenta();
                }
            };

    private Column<DetalleVentaProxy, String> unidadMedida
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getUnidadMedida();
                }

            };

    public List<DetalleVentaProxy> getData() {
        return data;
    }

    public boolean addItem(DetalleVentaProxy bean) {
        bean.setSecuencia(data.size() + 1);
        data.add(bean);
        dataProvider.refresh();
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        return true;
    }

    public boolean removeItem(DetalleVentaProxy bean) {
        data.remove(bean);
        dataProvider.refresh();
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        return true;
    }

    public void actualizarGrid() {
        this.setVisibleRangeAndClearData(this.getVisibleRange(), true);
        selectionModel.clear();
    }

    public int existe(String idItem) {
        int val = -1;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getIdItem().equalsIgnoreCase(idItem)) {
                return i;
            }
        }
        return val;
    }

    public void setData(List<DetalleVentaProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<DetalleVentaProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<DetalleVentaProxy> getDataProvider() {
        return dataProvider;
    }

    public Column<DetalleVentaProxy, String> getVer() {
        return ver;
    }

}
