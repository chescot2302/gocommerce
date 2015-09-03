/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.webgocommerce.client.beanproxy.ItemSerieProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoItemSerie;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.CheckCellHead;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridItemSerie extends DataGrid<ItemSerieProxy> {

    private final FactoryGestion FACTORY = com.google.gwt.core.client.GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private List<ItemSerieProxy> data = new ArrayList<ItemSerieProxy>();
    private final MultiSelectionModel<ItemSerieProxy> selectionModel = new MultiSelectionModel<ItemSerieProxy>();
    private FilteredListDataProvider<ItemSerieProxy> dataProvider = new FilteredListDataProvider<ItemSerieProxy>(new IFilter<ItemSerieProxy>() {

        @Override
        public boolean isValid(ItemSerieProxy value, String filter) {
            if (filter == null || value == null) {
                return true;
            } else {
                String values = value.getSerie().toString().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridItemSerie() {
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
        this.setSelectionModel(selectionModel, DefaultSelectionEventManager.<ItemSerieProxy>createCheckboxManager());
    }

    private void initColumns() {
        this.addColumn(checkColumn, headCheckAll);
        this.addColumn(serie, "SERIE");
        this.addColumn(telefono, "TELEFONO");
        this.setColumnWidth(checkColumn, 10, Unit.PCT);
    }

    private void initEditColumns() {
        telefono.setFieldUpdater(new FieldUpdater<ItemSerieProxy, String>() {

            @Override
            public void update(int index, ItemSerieProxy object, String value) {
                updatePhone(index,object,value);
            }
        });
    }

    private void updatePhone(int index, ItemSerieProxy object, String value) {
        ContextMantenimientoItemSerie context = FACTORY.contextMantenimientoItemSerie();
        FACTORY.initialize(EVENTBUS);
        ItemSerieProxy beanEdit = context.edit(object);
        beanEdit.setTelefono(value);
        int indexModel = data.indexOf(object);
        data.set(indexModel, beanEdit);
        context.fire();
        dataProvider.refresh();
        setKeyboardSelectedRow(index, 0, true);
        setKeyboardSelectedColumn(2, true); 
        selectionModel.setSelected(object, false);
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

    public Column<ItemSerieProxy, Boolean> checkColumn
            = new Column<ItemSerieProxy, Boolean>(new CheckboxCell(true, false)) {
                @Override
                public Boolean getValue(ItemSerieProxy object) {
                    // Get the value from the selection model.   
                    return selectionModel.isSelected(object);

                }
            };

    private Column<ItemSerieProxy, String> serie
            = new Column<ItemSerieProxy, String>(new TextCell()) {

                @Override
                public String getValue(ItemSerieProxy object) {
                    return object.getSerie();
                }

            };

    private Column<ItemSerieProxy, String> telefono
            = new Column<ItemSerieProxy, String>(new EditTextCell()) {

                @Override
                public String getValue(ItemSerieProxy object) {
                    return object.getTelefono();
                }
            };

    public void setData(List<ItemSerieProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<ItemSerieProxy> getData() {
        return data;
    }

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public MultiSelectionModel<ItemSerieProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<ItemSerieProxy> getDataProvider() {
        return dataProvider;
    }

    public void actualizarGrid() {
        this.setVisibleRangeAndClearData(this.getVisibleRange(), true);
        selectionModel.clear();
    }

}
