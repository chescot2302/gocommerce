/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.ClienteProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataItem;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridCliente extends DataGrid<ClienteProxy> {
    private List<ClienteProxy> data = new ArrayList<ClienteProxy>();
    private final SingleSelectionModel<ClienteProxy> selectionModel = new SingleSelectionModel<ClienteProxy>();
    private FilteredListDataProvider<ClienteProxy> dataProvider = new FilteredListDataProvider<ClienteProxy>(new IFilter<ClienteProxy>() {

        @Override
        public boolean isValid(ClienteProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getNombres().toLowerCase()+" "+value.getRuc().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridCliente() {
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
        this.addColumn(codigo, "CODIGO");
        this.addColumn(nombres, "NOMBRE");
        this.addColumn(documento, "RUC");
        this.addColumn(direccion, "DIRECCION");
        this.addColumn(telefono, "TELEFONO");
        this.addColumn(email, "EMAIL");
        this.setColumnWidth(codigo, 8, com.google.gwt.dom.client.Style.Unit.EM);    
        this.setColumnWidth(nombres, 30, com.google.gwt.dom.client.Style.Unit.EM);    
        this.setColumnWidth(documento, 15, com.google.gwt.dom.client.Style.Unit.EM);    
        this.setColumnWidth(direccion, 30, com.google.gwt.dom.client.Style.Unit.EM);    
        this.setColumnWidth(telefono, 10, com.google.gwt.dom.client.Style.Unit.EM);    
        this.setColumnWidth(email, 20, com.google.gwt.dom.client.Style.Unit.EM);    
    }
    
    private void initStyle(){           
        //MyResource.INSTANCE.getStlGridData().ensureInjected();	
        //this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
        this.getColumn(0).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(1).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(2).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(3).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(4).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(5).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    }
    
    private Column<ClienteProxy, String> codigo
            = new Column<ClienteProxy, String>(new TextCell()) {

                @Override
                public String getValue(ClienteProxy object) {
                    return object.getId();
                }

            };    
    
    private Column<ClienteProxy, String> direccion
            = new Column<ClienteProxy, String>(new TextCell()) {

                @Override
                public String getValue(ClienteProxy object) {
                    return object.getDireccion();
                }

            };
    
    private Column<ClienteProxy, String> telefono
            = new Column<ClienteProxy, String>(new TextCell()) {

                @Override
                public String getValue(ClienteProxy object) {
                    return object.getTelefono();
                }

            };
    
    private Column<ClienteProxy, String> email
            = new Column<ClienteProxy, String>(new TextCell()) {

                @Override
                public String getValue(ClienteProxy object) {
                    return object.getEmail();
                }

            };
   

    private Column<ClienteProxy, String> documento
            = new Column<ClienteProxy, String>(new TextCell()) {

                @Override
                public String getValue(ClienteProxy object) {
                    return object.getRuc();
                }

            };       
    
    private Column<ClienteProxy, String> nombres
            = new Column<ClienteProxy, String>(new TextCell()) {

                @Override
                public String getValue(ClienteProxy object) {
                    return object.getNombres();
                }

            };  

    public void setData(List<ClienteProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<ClienteProxy> getData() {
        return data;
    }        

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<ClienteProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<ClienteProxy> getDataProvider() {
        return dataProvider;
    }
}
