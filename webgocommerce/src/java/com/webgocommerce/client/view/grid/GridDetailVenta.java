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
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.DetalleVentaProxy;
import com.webgocommerce.client.beanproxy.ItemPlanProxy;
import com.webgocommerce.client.beanproxy.ItemSerieProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoItemPlan;
import com.webgocommerce.client.requestfactory.ContextMantenimientoItemSerie;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uiitemplan.UIItemPlan;
import com.webgocommerce.client.view.uiitemserie.UIItemSerie;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridDetailVenta extends DataGrid<DetalleVentaProxy> {

    private UIItemSerie ui;
    private UIItemPlan uiItemPlan; 
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
                String values = "";
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridDetailVenta() {
        initComponents();
        initStyle();
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
        this.addColumn(ver, "SERIE");
        this.addColumn(equipos, "EQUIPOS");
        this.addColumn(secuencia, "");
        this.addColumn(codigo, "CODIGO");
        this.addColumn(marca, "MARCA");
        this.addColumn(item, "ITEM");
        this.addColumn(umed, "UMED");
        this.addColumn(cantidad, "CANT");
        this.addColumn(precioUnit, "P. UNIT");
        this.addColumn(totalVenta, "TOTAL");
        this.addColumn(plan, "PLAN");
        this.addColumn(condPlan, "CONDICION");
        this.addColumn(precioPlan, "P. PLAN");
        this.addColumn(vigencia, "VIGENCIA");
        this.addColumn(codAlm, "CODALM");
        this.addColumn(almacen, "ALMACEN");
        this.setColumnWidth(ver, 4, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(equipos, 6, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(secuencia, 5, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(codigo, 9, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(marca, 12, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(item, 25, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(umed, 7, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(cantidad, 7, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(precioUnit, 10, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(totalVenta, 10, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(plan, 25, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(condPlan, 9, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(precioPlan, 7, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(vigencia, 7, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(codAlm, 7, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(almacen, 15, com.google.gwt.dom.client.Style.Unit.EM);
    }

    private void initEditColumns() {
        ver.setFieldUpdater(new FieldUpdater<DetalleVentaProxy, String>() {

            @Override
            public void update(int index, DetalleVentaProxy object, String value) {
                if (object.getLserie() == 1) {
                    ui = new UIItemSerie();
                    ui.changeTituloForm("SERIES");
                    ContextMantenimientoItemSerie context = FACTORY.contextMantenimientoItemSerie();
                    FACTORY.initialize(EVENTBUS);
                    String keyPublic = UISesion.keyPublic;
                    Request<List<ItemSerieProxy>> request = context.getSeriesVenta(keyPublic, object.getTipoDoc(), object.getCorrelativo(), object.getIdItem());
                    request.fire(new Receiver<List<ItemSerieProxy>>() {

                        @Override
                        public void onSuccess(List<ItemSerieProxy> response) {
                            ui.getGrid().getSelectionModel().clear();
                            ui.getGrid().setData(response);
                        }

                        @Override
                        public void onFailure(ServerFailure error) {
                            Notification not = new Notification(Notification.WARNING, error.getMessage());
                            not.showPopup();
                        }
                    });
                    ui.getBtnOperacion().setVisible(false);
                    ui.show();
                } else {
                    Notification not = new Notification(Notification.ALERT, "Item no maneja series");
                    not.showPopup();
                }
            }
        });

        equipos.setFieldUpdater(new FieldUpdater<DetalleVentaProxy, String>() {

            @Override
            public void update(int index, DetalleVentaProxy object, String value) {
                uiItemPlan= new UIItemPlan();
                uiItemPlan.setModo("", "L");
                ContextMantenimientoItemPlan context = FACTORY.contextMantenimientoItemPlan();
                FACTORY.initialize(EVENTBUS);
                String keyPublic = UISesion.keyPublic;
                Request<List<ItemPlanProxy>> request = context.getEquiposVenta(keyPublic, object.getTipoDoc(), object.getCorrelativo(), object.getIdItem(), object.getIdLista());
                request.fire(new Receiver<List<ItemPlanProxy>>() {

                    @Override
                    public void onSuccess(List<ItemPlanProxy> response) {
                        uiItemPlan.getGrid().getSelectionModel().clear();
                        uiItemPlan.getGrid().setData(response);
                    }

                    @Override
                    public void onFailure(ServerFailure error) {
                        Notification not = new Notification(Notification.WARNING, error.getMessage());
                        not.showPopup();
                    }
                });                
                uiItemPlan.show();
            }
        });
    }

    private void initStyle() {
        for (int i = 0; i < 15; i++) {
            this.getColumn(i).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        }
    }

    public Column<DetalleVentaProxy, String> ver
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

    public Column<DetalleVentaProxy, String> equipos
            = new Column<DetalleVentaProxy, String>(new ButtonCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return "equipos";
                }

            };

    public Column<DetalleVentaProxy, Number> precioPlan
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getPrecioPlan();
                }
            };

    public Column<DetalleVentaProxy, Number> totalVenta
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getTotalVenta();
                }
            };

    public Column<DetalleVentaProxy, Number> precioUnit
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getPrecioUnitario();
                }
            };

    public Column<DetalleVentaProxy, Number> cantidad
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getCantidad();
                }
            };

    public Column<DetalleVentaProxy, Number> vigencia
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getVigencia();
                }
            };

    public Column<DetalleVentaProxy, Number> secuencia
            = new Column<DetalleVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DetalleVentaProxy object) {
                    return object.getSecuencia();
                }
            };

    public Column<DetalleVentaProxy, String> codigo
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getCodigo();
                }
            };

    public Column<DetalleVentaProxy, String> marca
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getMarca();
                }

            };

    public Column<DetalleVentaProxy, String> item
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getDescripcion();
                }

            };

    public Column<DetalleVentaProxy, String> rucCliente
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getRucCliente();
                }

            };

    public Column<DetalleVentaProxy, String> umed
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getUnidadMedida();
                }

            };

    public Column<DetalleVentaProxy, String> plan
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getPlan();
                }

            };

    public Column<DetalleVentaProxy, String> condPlan
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getCondPlan();
                }

            };

    public Column<DetalleVentaProxy, String> codAlm
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getIdAlmacen();
                }

            };

    public Column<DetalleVentaProxy, String> almacen
            = new Column<DetalleVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(DetalleVentaProxy object) {
                    return object.getNomAlmacen();
                }

            };

    public void setData(List<DetalleVentaProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<DetalleVentaProxy> getData() {
        return data;
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
}
