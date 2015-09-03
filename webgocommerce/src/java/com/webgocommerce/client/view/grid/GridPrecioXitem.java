/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.PrecioItemProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridPrecioXitem extends DataGrid<PrecioItemProxy> {
    private List<PrecioItemProxy> data = new ArrayList<PrecioItemProxy>();
    private HashSet<Integer> indexUpdates = new HashSet();
    private final SingleSelectionModel<PrecioItemProxy> selectionModel = new SingleSelectionModel<PrecioItemProxy>();
    private FilteredListDataProvider<PrecioItemProxy> dataProvider = new FilteredListDataProvider<PrecioItemProxy>(new IFilter<PrecioItemProxy>() {

        @Override
        public boolean isValid(PrecioItemProxy value, String filter) {
            if (filter == null || value == null) {
                return true;
            } else {
                String values = value.getDescripcion().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridPrecioXitem() {
        initComponents();
    }

    private void initComponents() {
        this.setWidth("100%");
        this.setHeight("90%");
        initColumns();
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
        this.addColumn(descripcion, "L. PRECIO");
        this.addColumn(vigencia, "VIG.");
        this.addColumn(pagoMensual, "P. MES");
        this.addColumn(precioVenta, "P. VENTA");
        this.setColumnWidth(descripcion, 50, Unit.PCT);
        this.setColumnWidth(vigencia, 10, Unit.PCT);
        this.setColumnWidth(pagoMensual, 20, Unit.PCT);
        this.setColumnWidth(precioVenta, 20, Unit.PCT);
    }          


    private Column<PrecioItemProxy, Number> precioVenta
            = new Column<PrecioItemProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(PrecioItemProxy object) {
                    return object.getPrecioVenta();
                }
            };
    
    private Column<PrecioItemProxy, Number> vigencia
            = new Column<PrecioItemProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(PrecioItemProxy object) {
                    return object.getVigencia();
                }
            };
    
    private Column<PrecioItemProxy, Number> pagoMensual
            = new Column<PrecioItemProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(PrecioItemProxy object) {
                    return object.getPagoMensual();
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
        MyResource.INSTANCE.getStlGridData().ensureInjected();
        this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
    }

    public void setData(List<PrecioItemProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
        //redraw();
    }

    public List<PrecioItemProxy> getData() {
        return data;
    }

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<PrecioItemProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<PrecioItemProxy> getDataProvider() {
        return dataProvider;
    }

    public HashSet<Integer> getIndexUpdates() {
        return indexUpdates;
    }
       
}
