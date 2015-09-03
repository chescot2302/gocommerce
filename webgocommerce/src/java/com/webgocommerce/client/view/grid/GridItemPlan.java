/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.webgocommerce.client.beanproxy.ItemPlanProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoItemPlan;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.CheckCellHead;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import com.webgocommerce.shared.FieldVerifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridItemPlan extends DataGrid<ItemPlanProxy> {
    private HashSet<Integer> indexUpdates = new HashSet();
    public final static String E = "E";
    public final static String L = "L";
    private String modo;
    private final FactoryGestion FACTORY = com.google.gwt.core.client.GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private List<ItemPlanProxy> data = new ArrayList<ItemPlanProxy>();
    private final MultiSelectionModel<ItemPlanProxy> selectionModel = new MultiSelectionModel<ItemPlanProxy>();
    private FilteredListDataProvider<ItemPlanProxy> dataProvider = new FilteredListDataProvider<ItemPlanProxy>(new IFilter<ItemPlanProxy>() {

        @Override
        public boolean isValid(ItemPlanProxy value, String filter) {
            if (filter == null || value == null) {
                return true;
            } else {
                String values = value.getDescripcion().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridItemPlan() {
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
        //this.setSelectionModel(selectionModel);
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(this);
        pager.setVisible(true);
        this.setSelectionModel(selectionModel, DefaultSelectionEventManager.<ItemPlanProxy>createCheckboxManager());
    }

    private void initColumns() {
        this.addColumn(checkColumn, headCheckAll);
        this.addColumn(descripcion, "EQUIPO");
        this.addColumn(cantidad, "CANTIDAD");
        this.addColumn(precio, "PRECIO");
        this.addColumn(total, "TOTAL");
        this.setColumnWidth(checkColumn, 10, Unit.PCT);
        this.setColumnWidth(descripcion, 50, Unit.PCT);
    }

    private void initEditColumns() {
        precio.setFieldUpdater(new FieldUpdater<ItemPlanProxy, String>() {

            @Override
            public void update(int index, ItemPlanProxy object, String value) {
                if (FieldVerifier.notIsNumberPositivo(value, "Precio")) {
                    value = "0.0";
                }
                updateEquipo(index, object, value,"precio");
                selectionModel.setSelected(object, false);
            }
            
        });
        
        cantidad.setFieldUpdater(new FieldUpdater<ItemPlanProxy, String>() {

            @Override
            public void update(int index, ItemPlanProxy object, String value) {
                if (FieldVerifier.notIsNumberPositivo(value, "Cantidad")) {
                    value = "0.0";
                }
                updateEquipo(index, object, value,"cantidad");
                selectionModel.setSelected(object, false);
            }
        });
    }

    private void updateEquipo(int index, ItemPlanProxy object, String value,String dato) {
        if (!FieldVerifier.notIsNumberPositivo(value, dato)) {
            ContextMantenimientoItemPlan context = FACTORY.contextMantenimientoItemPlan();
            FACTORY.initialize(EVENTBUS);
            ItemPlanProxy beanEdit = context.edit(object);
            BigDecimal valprecio=BigDecimal.ZERO;
            BigDecimal cantidad=BigDecimal.ZERO;
            BigDecimal total=BigDecimal.ZERO;
            if(dato.equalsIgnoreCase("precio")){
                valprecio = BigDecimal.valueOf(Double.parseDouble(value));            
                beanEdit.setPrecio(valprecio);
                total=  valprecio.multiply(beanEdit.getCantidad());
            }else if(dato.equalsIgnoreCase("cantidad")){
                cantidad = BigDecimal.valueOf(Double.parseDouble(value));            
                beanEdit.setCantidad(cantidad);
                total=cantidad.multiply(beanEdit.getPrecio());
            }                        
            beanEdit.setTotal(total);
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
            setKeyboardSelectedColumn(3, true);
        }
    }

    private void initStyle() {
        MyResource.INSTANCE.getStlGridData().ensureInjected();
        this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
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

    public void selection(Boolean select) {
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

    public Column<ItemPlanProxy, Boolean> checkColumn
            = new Column<ItemPlanProxy, Boolean>(new CheckboxCell(true, false)) {
                @Override
                public Boolean getValue(ItemPlanProxy object) {
                    // Get the value from the selection model.   
                    return selectionModel.isSelected(object);

                }
            };

    private Column<ItemPlanProxy, String> descripcion
            = new Column<ItemPlanProxy, String>(new TextCell()) {

                @Override
                public String getValue(ItemPlanProxy object) {
                    return object.getDescripcion();
                }

            };

    private Column<ItemPlanProxy, String> precio
            = new Column<ItemPlanProxy, String>(new TextInputCell() {
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
                        if (modo.equalsIgnoreCase(GridItemPlan.E)) {
                            sb.appendHtmlConstant("<input type=\"text\" " + "value=\"" + value + "\" " + " tabindex=\"-1\"></input>");
                        } else if (modo.equalsIgnoreCase(GridItemPlan.L)) {
                            sb.appendHtmlConstant("<input type=\"text\" " + "value=\"" + value + "\" " + " tabindex=\"-1\" DISABLED></input>");
                        }
                    }
                }
            }) {

                @Override
                public String getValue(ItemPlanProxy object) {
                    return object.getPrecio().toString();
                }
            };

    private Column<ItemPlanProxy, String> cantidad
            = new Column<ItemPlanProxy, String>(new TextInputCell() {
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
                        if (modo.equalsIgnoreCase(GridItemPlan.E)) {                            
                            sb.appendHtmlConstant("<input type=\"text\" " + "value=\"" + value + "\" " + " tabindex=\"-1\"></input>");
                        } else if (modo.equalsIgnoreCase(GridItemPlan.L)) {
                            sb.appendHtmlConstant("<input type=\"text\" " + "value=\"" + value + "\" " + " tabindex=\"-1\" DISABLED></input>");
                        }
                    }
                }
            }) {

                @Override
                public String getValue(ItemPlanProxy object) {
                    return object.getCantidad().toString();
                }
            };

    private Column<ItemPlanProxy, Number> total
            = new Column<ItemPlanProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(ItemPlanProxy object) {
                    return object.getTotal();
                }
            };

    public void setData(List<ItemPlanProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<ItemPlanProxy> getData() {
        return data;
    }

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public MultiSelectionModel<ItemPlanProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<ItemPlanProxy> getDataProvider() {
        return dataProvider;
    }

    public void actualizarGrid() {
        this.setVisibleRangeAndClearData(this.getVisibleRange(), true);
        selectionModel.clear();
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

}
