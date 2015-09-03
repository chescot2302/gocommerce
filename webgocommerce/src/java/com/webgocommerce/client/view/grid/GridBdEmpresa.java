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
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.BdEmpresaProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridBdEmpresa extends DataGrid<BdEmpresaProxy> {

    private List<BdEmpresaProxy> data = new ArrayList<BdEmpresaProxy>();
    private final SingleSelectionModel<BdEmpresaProxy> selectionModel = new SingleSelectionModel<BdEmpresaProxy>();
    private FilteredListDataProvider<BdEmpresaProxy> dataProvider = new FilteredListDataProvider<BdEmpresaProxy>(new IFilter<BdEmpresaProxy>() {

        @Override
        public boolean isValid(BdEmpresaProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getIdBdEmpresa().toString().toLowerCase()+" "+value.getNombre().toLowerCase()+" "+value.getIpHost().toLowerCase()+" "+value.getPuerto().toString()+" "+value.getSchema().toLowerCase()+" "+value.getUserPrincipal().toLowerCase()+" "+value.getClavePrincipal().toLowerCase()+" "+value.getEstadoActivacion().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridBdEmpresa() {
        initComponents();
        //initStyle();
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
        MyResource.INSTANCE.getStlGridData().ensureInjected();	
        this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
    }

    private void initColumns() {
        this.addColumn(id, "ID");
        this.addColumn(nombre, "NOMBRE");
        this.addColumn(ipHost, "IPHOST");
        this.addColumn(puerto, "PUERTO");
        this.addColumn(schema, "ESQUEMA");
        this.addColumn(usuario, "USUARIO BD");
        this.addColumn(clave, "CLAVE BD");
        this.addColumn(estado, "ESTADO");
        this.setColumnWidth(id, 15, Unit.PCT);
        this.setColumnWidth(nombre, 35, Unit.PCT);
        this.setColumnWidth(ipHost, 25, Unit.PCT);
        this.setColumnWidth(puerto, 15, Unit.PCT);
        this.setColumnWidth(schema, 25, Unit.PCT);
        this.setColumnWidth(usuario, 35, Unit.PCT);
        this.setColumnWidth(clave, 20, Unit.PCT);
        this.setColumnWidth(estado, 15, Unit.PCT);
    }

    private Column<BdEmpresaProxy, Number> id
            = new Column<BdEmpresaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(BdEmpresaProxy object) {
                    return object.getIdBdEmpresa();
                }
            };

    private Column<BdEmpresaProxy, String> nombre
            = new Column<BdEmpresaProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdEmpresaProxy object) {
                    return object.getNombre();
                }

            };
    
    private Column<BdEmpresaProxy, String> ipHost
            = new Column<BdEmpresaProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdEmpresaProxy object) {
                    return object.getIpHost();
                }

            };
    
    private Column<BdEmpresaProxy, Number> puerto
            = new Column<BdEmpresaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(BdEmpresaProxy object) {
                    return object.getPuerto();
                }
            };
    
    private Column<BdEmpresaProxy, String> schema
            = new Column<BdEmpresaProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdEmpresaProxy object) {
                    return object.getSchema();
                }

            };
    
    private Column<BdEmpresaProxy, String> usuario
            = new Column<BdEmpresaProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdEmpresaProxy object) {
                    return object.getUserPrincipal();
                }

            };
    
    private Column<BdEmpresaProxy, String> clave
            = new Column<BdEmpresaProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdEmpresaProxy object) {
                    return object.getClavePrincipal();
                }

            };
    
    private Column<BdEmpresaProxy, String> estado
            = new Column<BdEmpresaProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdEmpresaProxy object) {
                    return object.getEstadoActivacion();
                }

            };       

    public void setData(List<BdEmpresaProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<BdEmpresaProxy> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<BdEmpresaProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<BdEmpresaProxy> getDataProvider() {
        return dataProvider;
    }
}
