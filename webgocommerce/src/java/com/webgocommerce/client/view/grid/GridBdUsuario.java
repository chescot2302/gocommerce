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
import com.webgocommerce.client.beanproxy.BdUsuarioProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridBdUsuario extends DataGrid<BdUsuarioProxy> {
    private List<BdUsuarioProxy> data = new ArrayList<BdUsuarioProxy>();
    private final SingleSelectionModel<BdUsuarioProxy> selectionModel = new SingleSelectionModel<BdUsuarioProxy>();
    private FilteredListDataProvider<BdUsuarioProxy> dataProvider = new FilteredListDataProvider<BdUsuarioProxy>(new IFilter<BdUsuarioProxy>() {

        @Override
        public boolean isValid(BdUsuarioProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getIdBdUsuario().toString()+" "+value.getBeanBdEmpresa().getNombre().toLowerCase()+" "+value.getSchema().toLowerCase()+" "+value.getNivel().toLowerCase()+" "+value.getCorreo().toLowerCase()+" "+value.getClave().toLowerCase()+" "+value.getUsuarioBd().toLowerCase()+" "+value.getClaveBd().toLowerCase()+" "+value.getEstadoActivacion().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridBdUsuario() {
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
        this.addColumn(nombreEmpresa, "EMPRESA");
        this.addColumn(esquema, "ESQUEMA");
        this.addColumn(nivel, "NIVEL");
        this.addColumn(correo, "USUARIO LOG");
        this.addColumn(clave, "CLAVE LOG");
        this.addColumn(usuarioBd, "USUARIO BD");
        this.addColumn(claveBd, "CLAVE BD");        
        this.addColumn(estado, "ESTADO");
        this.setColumnWidth(id, 15, Unit.PCT);
        this.setColumnWidth(nombreEmpresa, 35, Unit.PCT);
        this.setColumnWidth(esquema, 25, Unit.PCT);
        this.setColumnWidth(nivel, 20, Unit.PCT);
        this.setColumnWidth(correo, 35, Unit.PCT);
        this.setColumnWidth(clave, 20, Unit.PCT);
        this.setColumnWidth(usuarioBd, 25, Unit.PCT);
        this.setColumnWidth(claveBd, 20, Unit.PCT);
        this.setColumnWidth(estado, 15, Unit.PCT);
    }

    private Column<BdUsuarioProxy, Number> id
            = new Column<BdUsuarioProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(BdUsuarioProxy object) {
                    return object.getIdBdUsuario();
                }
            };

    private Column<BdUsuarioProxy, String> nombreEmpresa
            = new Column<BdUsuarioProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdUsuarioProxy object) {
                    return object.getBeanBdEmpresa().getNombre();
                }

            };
    
    private Column<BdUsuarioProxy, String> esquema
            = new Column<BdUsuarioProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdUsuarioProxy object) {
                    return object.getSchema();
                }

            };    
    
    private Column<BdUsuarioProxy, String> nivel
            = new Column<BdUsuarioProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdUsuarioProxy object) {
                    return object.getNivel();
                }

            };
    
    private Column<BdUsuarioProxy, String> correo
            = new Column<BdUsuarioProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdUsuarioProxy object) {
                    return object.getCorreo();
                }

            };
    
    private Column<BdUsuarioProxy, String> clave
            = new Column<BdUsuarioProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdUsuarioProxy object) {
                    return object.getClave();
                }

            };
    
    private Column<BdUsuarioProxy, String> usuarioBd
            = new Column<BdUsuarioProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdUsuarioProxy object) {
                    return object.getUsuarioBd();
                }

            };
    
    private Column<BdUsuarioProxy, String> claveBd
            = new Column<BdUsuarioProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdUsuarioProxy object) {
                    return object.getClaveBd();
                }

            };
    
    private Column<BdUsuarioProxy, String> estado
            = new Column<BdUsuarioProxy, String>(new TextCell()) {

                @Override
                public String getValue(BdUsuarioProxy object) {
                    return object.getEstadoActivacion();
                }

            };       

    public void setData(List<BdUsuarioProxy> data) {
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
    public SingleSelectionModel<BdUsuarioProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<BdUsuarioProxy> getDataProvider() {
        return dataProvider;
    }

    public List<BdUsuarioProxy> getData() {
        return data;
    }
    
    
}
