/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.webgocommerce.client.beanproxy.PrecioItemProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoPrecioItem;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.CheckCellHead;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import com.webgocommerce.client.view.uisesion.UISesionImpl;
import com.webgocommerce.shared.FieldVerifier;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridPrecioItem extends DataGrid<PrecioItemProxy> {

    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    public final static String E = "E";
    public final static String L = "L";
    private String modo;
    private final FactoryGestion FACTORY = com.google.gwt.core.client.GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private List<PrecioItemProxy> data = new ArrayList<PrecioItemProxy>();
    private HashSet<Integer> indexUpdates = new HashSet();
    private final MultiSelectionModel<PrecioItemProxy> selectionModel = new MultiSelectionModel<PrecioItemProxy>();
    private FilteredListDataProvider<PrecioItemProxy> dataProvider = new FilteredListDataProvider<PrecioItemProxy>(new IFilter<PrecioItemProxy>() {

        @Override
        public boolean isValid(PrecioItemProxy value, String filter) {
            if (filter == null || value == null) {
                return true;
            } else {
                String values = value.getId().toString().toLowerCase() + " " + value.getCodigo().toLowerCase() + " " + value.getMarca().toLowerCase() + " " + value.getDescripcion().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridPrecioItem() {
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
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(this);
        pager.setVisible(true);
        this.setSelectionModel(selectionModel, DefaultSelectionEventManager.<PrecioItemProxy>createCheckboxManager());
    }

    private void initColumns() {
        this.addColumn(checkColumn, headCheckAll);
        this.addColumn(estado, "EST");
        this.addColumn(codigo, "CODIGO");
        this.addColumn(descripcion, "DESCRIPCION");
        this.addColumn(marca, "MARCA");
        this.addColumn(precioSd, "PRECIOSD");
        this.addColumn(descuento, "DESCUENTO");
        this.addColumn(precioVenta, "P. VENTA");
        this.addColumn(valorVenta, "VALOR VENTA");
        this.addColumn(igv, "IGV");
        this.addColumn(fechaIni, "FECHA INICIO");
        this.addColumn(fechaFin, "FECHA FIN");
        this.setColumnWidth(checkColumn, 5, Unit.EM);
        this.setColumnWidth(estado, 5, Unit.EM);
        this.setColumnWidth(codigo, 12, Unit.EM);
        this.setColumnWidth(marca, 13, Unit.EM);
        this.setColumnWidth(descripcion, 26, Unit.EM);
        this.setColumnWidth(precioSd, 8, Unit.EM);
        this.setColumnWidth(descuento, 8, Unit.EM);
        this.setColumnWidth(precioVenta, 8, Unit.EM);
        this.setColumnWidth(valorVenta, 8, Unit.EM);
        this.setColumnWidth(igv, 8, Unit.EM);
        this.setColumnWidth(fechaIni, 10, Unit.EM);
        this.setColumnWidth(fechaFin, 10, Unit.EM);
    }

    private Column<PrecioItemProxy, Date> fechaIni
            = new Column<PrecioItemProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(PrecioItemProxy object) {
                    return object.getFechaIni();
                }
            };

    private Column<PrecioItemProxy, Date> fechaFin
            = new Column<PrecioItemProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(PrecioItemProxy object) {
                    return object.getFechaFin();
                }
            };

    private void calcularPrecioSd(int index, PrecioItemProxy object, String value) {
        if (!FieldVerifier.notIsNumberPositivo(value, "Precio sin descuento")) {
            ContextMantenimientoPrecioItem context = FACTORY.contextMantenimientoPrecioItem();
            FACTORY.initialize(EVENTBUS);
            PrecioItemProxy beanEdit = context.edit(object);
            BigDecimal valprecioSd = BigDecimal.valueOf(Double.parseDouble(value));
            BigDecimal valDescuento = object.getDescuento();
            BigDecimal valPrecioVenta = valprecioSd.subtract(valDescuento);
            BigDecimal valValorVenta = valPrecioVenta.divide(UISesionImpl.beanInitParam.getIgv().divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP).add(BigDecimal.ONE), 5, RoundingMode.HALF_UP);
            BigDecimal igv = valPrecioVenta.subtract(valValorVenta);
            beanEdit.setPrecioSD(valprecioSd);
            beanEdit.setDescuento(valDescuento);
            beanEdit.setPrecioVenta(valPrecioVenta);
            beanEdit.setValorVenta(valValorVenta);
            beanEdit.setIgv(igv);
            //GWT.log("Index Vista: " + index);
            //Window.alert("inicio"+index);
            //index = dataProvider.getList().indexOf(object);
            //Window.alert("fin"+index);
            int indexModel = data.indexOf(object);
            //GWT.log("Index Modelo: " + indexModel);
            data.set(indexModel, beanEdit);
            indexUpdates.add(indexModel);
            context.fire();
            dataProvider.refresh();
            // Update the row and subrow.
            setKeyboardSelectedRow(index, 0, true);
            // Update the column index.
            setKeyboardSelectedColumn(5, true);
        }
    }

    private void calcularDescuento(int index, PrecioItemProxy object, String value) {
        if (!FieldVerifier.notIsNumberPositivo(value, "Descuento")) {
            ContextMantenimientoPrecioItem context = FACTORY.contextMantenimientoPrecioItem();
            FACTORY.initialize(EVENTBUS);
            PrecioItemProxy beanEdit = context.edit(object);
            BigDecimal valPrecioSd = object.getPrecioSD();
            BigDecimal valDescuento = BigDecimal.valueOf(Double.parseDouble(value));
            BigDecimal valPrecioVenta = object.getPrecioSD().subtract(valDescuento);
            BigDecimal valValorVenta = valPrecioVenta.divide(UISesionImpl.beanInitParam.getIgv().divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP).add(BigDecimal.ONE), 5, RoundingMode.HALF_UP);
            BigDecimal igv = valPrecioVenta.subtract(valValorVenta);
            beanEdit.setPrecioSD(valPrecioSd);
            beanEdit.setDescuento(valDescuento);
            beanEdit.setPrecioVenta(valPrecioVenta);
            beanEdit.setValorVenta(valValorVenta);
            beanEdit.setIgv(igv);
            //GWT.log("Index Vista: " + index);
            //index = dataProvider.getList().indexOf(object);
            int indexModel = data.indexOf(object);
            //GWT.log("Index Modelo: " + indexModel);
            data.set(indexModel, beanEdit);
            indexUpdates.add(indexModel);
            context.fire();
            dataProvider.refresh();
            // Update the row and subrow.
            setKeyboardSelectedRow(index, 0, true);
            // Update the column index.
            setKeyboardSelectedColumn(6, true);
        } else {

        }
    }

    private void initEditColumns() {

        precioSd.setFieldUpdater(new FieldUpdater<PrecioItemProxy, String>() {

            @Override
            public void update(int index, PrecioItemProxy object, String value) {
                if (FieldVerifier.notIsNumberPositivo(value, "Precio sin descuento")) {
                    value = "0.0";
                }
                calcularPrecioSd(index, object, value);
            }
        });

        descuento.setFieldUpdater(
                new FieldUpdater<PrecioItemProxy, String>() {

                    @Override
                    public void update(int index, PrecioItemProxy object, String value
                    ) {
                        if (FieldVerifier.notIsNumberPositivo(value, "Descuento")) {
                            value = "0.0";
                        }
                        calcularDescuento(index, object, value);
                    }
                }
        );
    }

    public CheckCellHead checkAll = new CheckCellHead();
    Header<Boolean> headCheckAll = new Header<Boolean>(checkAll) {

        @Override
        public Boolean getValue() {
            return checkAll.isIsSelected();
        }

        @Override
        public void onBrowserEvent(Cell.Context context,
                Element elem,
                NativeEvent event) {
            if (checkAll.isIsSelected()) {
                checkAll.setIsSelected(false);
                selection(false);
            } else {
                checkAll.setIsSelected(true);
                selection(true);
            }

        }
    };

    private void selection(Boolean select) {
        if (dataProvider.getFilter() != null && !dataProvider.getFilter().isEmpty()) {
            selectionModel.clear();
            for (int i = 0; i < dataProvider.resulted.size(); i++) {
                selectionModel.setSelected(dataProvider.resulted.get(i), select);
            }
        } else {
            selectionModel.clear();
            for (int i = 0; i < data.size(); i++) {
                selectionModel.setSelected(data.get(i), select);
            }
        }
    }

    Column<PrecioItemProxy, Boolean> checkColumn
            = new Column<PrecioItemProxy, Boolean>(new CheckboxCell(true, false)) {
                @Override
                public Boolean getValue(PrecioItemProxy object) {
                    // Get the value from the selection model.
                    return selectionModel.isSelected(object);
                }
            };

    private Column<PrecioItemProxy, Number> valorVenta
            = new Column<PrecioItemProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(PrecioItemProxy object) {
                    return object.getValorVenta();
                }
            };

    private Column<PrecioItemProxy, Number> igv
            = new Column<PrecioItemProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(PrecioItemProxy object) {
                    return object.getIgv();
                }
            };

    private Column<PrecioItemProxy, String> precioSd
            = new Column<PrecioItemProxy, String>(new TextInputCell() {
                @Override
                public void render(Cell.Context context, String value, SafeHtmlBuilder sb) {
                    // Get the view data.
                    Object key = context.getKey();
                    TextInputCell.ViewData viewData = getViewData(key);
                    if (viewData != null && viewData.getCurrentValue().equals(value)) {
                        clearViewData(key);
                        viewData = null;
                    }

                    String s = (viewData != null) ? viewData.getCurrentValue() : value;
                    if (s != null) {
                        if (modo.equalsIgnoreCase(GridPrecioItem.E)) {
                            PrecioItemProxy object = (PrecioItemProxy) key;
                            if (object.getEstadoColor().equalsIgnoreCase("A1")) {
                                sb.appendHtmlConstant("<input type=\"text\" " + "value=\"" + value + "\" " + " tabindex=\"-1\"></input>");
                            } else {
                                sb.appendHtmlConstant("<input type=\"text\" " + "value=\"" + value + "\" " + " tabindex=\"-1\" DISABLED></input>");
                            }
                        } else if (modo.equalsIgnoreCase(GridPrecioItem.L)) {
                            sb.appendHtmlConstant("<input type=\"text\" " + "value=\"" + value + "\" " + " tabindex=\"-1\" DISABLED></input>");
                        }
                    }
                }
            }) {

                @Override
                public String getValue(PrecioItemProxy object) {
                    return object.getPrecioSD().toString();
                }
            };

    private Column<PrecioItemProxy, String> descuento
            = new Column<PrecioItemProxy, String>(new TextInputCell() {
                @Override
                public void render(Cell.Context context, String value, SafeHtmlBuilder sb) {
                    // Get the view data.
                    Object key = context.getKey();
                    TextInputCell.ViewData viewData = getViewData(key);
                    if (viewData != null && viewData.getCurrentValue().equals(value)) {
                        clearViewData(key);
                        viewData = null;
                    }

                    String s = (viewData != null) ? viewData.getCurrentValue() : value;
                    if (s != null) {
                        if (modo.equalsIgnoreCase(GridPrecioItem.E)) {
                            PrecioItemProxy object = (PrecioItemProxy) key;
                            if (object.getEstadoColor().equalsIgnoreCase("A1")) {
                                sb.appendHtmlConstant("<input type=\"text\" " + "value=\"" + value + "\" " + " tabindex=\"-1\"></input>");
                            } else {
                                sb.appendHtmlConstant("<input type=\"text\" " + "value=\"" + value + "\" " + " tabindex=\"-1\" DISABLED></input>");
                            }
                        } else if (modo.equalsIgnoreCase(GridPrecioItem.L)) {
                            sb.appendHtmlConstant("<input type=\"text\" " + "value=\"" + value + "\" " + " tabindex=\"-1\" DISABLED></input>");
                        }
                    }
                }
            }) {

                @Override
                public String getValue(PrecioItemProxy object) {
                    return object.getDescuento().toString();
                }
            };

    private Column<PrecioItemProxy, Number> precioVenta
            = new Column<PrecioItemProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(PrecioItemProxy object) {
                    return object.getPrecioVenta();
                }
            };

    private Column<PrecioItemProxy, ImageResource> estado
            = new Column<PrecioItemProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(PrecioItemProxy object) {
                    if (object.getEstadoColor().equalsIgnoreCase("A1")) {
                        return MyResource.INSTANCE.getImgGreen20();
                    } else if (object.getEstadoColor().equalsIgnoreCase("A0")) {
                        return MyResource.INSTANCE.getImgOrange20();
                    } else {
                        return MyResource.INSTANCE.getImgGray20();
                    }
                }

            };

    private Column<PrecioItemProxy, String> codigo
            = new Column<PrecioItemProxy, String>(new TextCell()) {

                @Override
                public String getValue(PrecioItemProxy object) {
                    return object.getCodigo();
                }

            };

    private Column<PrecioItemProxy, String> marca
            = new Column<PrecioItemProxy, String>(new TextCell()) {

                @Override
                public String getValue(PrecioItemProxy object) {
                    return object.getMarca();
                }

            };

    private Column<PrecioItemProxy, String> descripcion
            = new Column<PrecioItemProxy, String>(new TextCell()) {

                @Override
                public String getValue(PrecioItemProxy object) {
                    return object.getDescripcion();
                }

            };

    private void initStyle() {
        //MyResource.INSTANCE.getStlGridData().ensureInjected();
        //this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
        this.getColumn(0).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(1).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(2).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(3).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(4).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(5).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(6).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(7).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(8).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(9).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(10).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(11).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    }

    public void actualizarGrid() {
        this.setVisibleRangeAndClearData(this.getVisibleRange(), true);
        selectionModel.clear();
    }

    public void setData(List<PrecioItemProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
        indexUpdates.clear();
        //redraw();
    }

    public List<PrecioItemProxy> getData() {
        return data;
    }

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public MultiSelectionModel<PrecioItemProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<PrecioItemProxy> getDataProvider() {
        return dataProvider;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public HashSet<Integer> getIndexUpdates() {
        return indexUpdates;
    }

}
