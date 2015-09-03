/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.SucursalProxy;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridSucursal  extends DataGrid<SucursalProxy> {    
    private List<SucursalProxy> data = new ArrayList<SucursalProxy>();
    private final SingleSelectionModel<SucursalProxy> selectionModel = new SingleSelectionModel<SucursalProxy>();
    private FilteredListDataProvider<SucursalProxy> dataProvider = new FilteredListDataProvider<SucursalProxy>(new IFilter<SucursalProxy>() {        

        @Override
        public boolean isValid(SucursalProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getId().toLowerCase()+" "+" "+value.getDescripcion().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridSucursal() {
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

    private void initColumns() {
        this.addColumn(id, "ID");
        this.addColumn(localidad, "LOCALIDAD");
        this.addColumn(descripcion, "SUCURSAL");  
        this.setColumnWidth(id, 5, com.google.gwt.dom.client.Style.Unit.EM);    
        this.setColumnWidth(localidad, 20, com.google.gwt.dom.client.Style.Unit.EM);    
    }
    
    private void initStyle(){           
        //MyResource.INSTANCE.getStlGridData().ensureInjected();	
        //this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
        this.getColumn(0).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(1).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(2).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    }
    
    private Column<SucursalProxy, String> id
            = new Column<SucursalProxy, String>(new TextCell()) {

                @Override
                public String getValue(SucursalProxy object) {
                    return object.getId();
                }
            };
    
    private Column<SucursalProxy, String> localidad
            = new Column<SucursalProxy, String>(new TextCell()) {

                @Override
                public String getValue(SucursalProxy object) {
                    return object.getNomLocalidad();
                }

            };    

    private Column<SucursalProxy, String> descripcion
            = new Column<SucursalProxy, String>(new TextCell()) {

                @Override
                public String getValue(SucursalProxy object) {
                    return object.getDescripcion();
                }

            };            

    public void setData(List<SucursalProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<SucursalProxy> getData() {
        return data;
    }        

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<SucursalProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<SucursalProxy> getDataProvider() {
        return dataProvider;
    }
}
