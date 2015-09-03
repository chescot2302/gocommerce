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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.LocalidadProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridLocalidad  extends DataGrid<LocalidadProxy> {

    private List<LocalidadProxy> data = new ArrayList<LocalidadProxy>();
    private final SingleSelectionModel<LocalidadProxy> selectionModel = new SingleSelectionModel<LocalidadProxy>();
    private FilteredListDataProvider<LocalidadProxy> dataProvider = new FilteredListDataProvider<LocalidadProxy>(new IFilter<LocalidadProxy>() {

        @Override
        public boolean isValid(LocalidadProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getIdLocalidad().toString().toLowerCase()+" "+value.getDescripcion().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridLocalidad() {
        initComponents();
        initStyle();
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
    
    private void initStyle(){           
        //MyResource.INSTANCE.getStlGridData().ensureInjected();	
        //this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
        this.getColumn(0).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(1).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void initColumns() {
        this.addColumn(id, "ID");
        this.addColumn(nombre, "DESCRIPCION");        
        this.setColumnWidth(id, 5, Unit.EM);        
    }

    private Column<LocalidadProxy, Number> id
            = new Column<LocalidadProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(LocalidadProxy object) {
                    return object.getIdLocalidad();
                }
            };

    private Column<LocalidadProxy, String> nombre
            = new Column<LocalidadProxy, String>(new TextCell()) {

                @Override
                public String getValue(LocalidadProxy object) {
                    return object.getDescripcion();
                }

            };        

    public void setData(List<LocalidadProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<LocalidadProxy> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<LocalidadProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<LocalidadProxy> getDataProvider() {
        return dataProvider;
    }
}
