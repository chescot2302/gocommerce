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
import com.webgocommerce.client.beanproxy.MenuBarProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridMenu  extends DataGrid<MenuBarProxy> {

    private List<MenuBarProxy> data = new ArrayList<MenuBarProxy>();
    private final SingleSelectionModel<MenuBarProxy> selectionModel = new SingleSelectionModel<MenuBarProxy>();
    private FilteredListDataProvider<MenuBarProxy> dataProvider = new FilteredListDataProvider<MenuBarProxy>(new IFilter<MenuBarProxy>() {

        @Override
        public boolean isValid(MenuBarProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getIdMenuBar().toString().toLowerCase()+" "+value.getDescripcion().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridMenu() {
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
        this.addColumn(tipo, "TIPO");
        this.addColumn(variable, "VARIABLE");
        this.addColumn(nombre, "DESCRIPCION");  
        this.addColumn(nivel, "NIVEL");
        this.addColumn(orden, "ORDEN");
        this.addColumn(grupo, "GRUPO");
        this.addColumn(numSubMenu, "NUM SUBMENU");
        //this.setColumnWidth(id, 15, Unit.PCT);
        //this.setColumnWidth(nombre, 35, Unit.PCT);        
    }

    private Column<MenuBarProxy, Number> id
            = new Column<MenuBarProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(MenuBarProxy object) {
                    return object.getIdMenuBar();
                }
            };
    
    private Column<MenuBarProxy, Number> nivel
            = new Column<MenuBarProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(MenuBarProxy object) {
                    return object.getNivel();
                }
            };
    
    private Column<MenuBarProxy, Number> orden
            = new Column<MenuBarProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(MenuBarProxy object) {
                    return object.getOrden();
                }
            };
    
    private Column<MenuBarProxy, Number> grupo
            = new Column<MenuBarProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(MenuBarProxy object) {
                    return object.getGrupo();
                }
            };
    
    private Column<MenuBarProxy, Number> numSubMenu
            = new Column<MenuBarProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(MenuBarProxy object) {
                    return object.getNumSubMenu();
                }
            };
    
    private Column<MenuBarProxy, String> tipo
            = new Column<MenuBarProxy, String>(new TextCell()) {

                @Override
                public String getValue(MenuBarProxy object) {
                    return object.getTipo();
                }

            };
    
    private Column<MenuBarProxy, String> variable
            = new Column<MenuBarProxy, String>(new TextCell()) {

                @Override
                public String getValue(MenuBarProxy object) {
                    return object.getVariable();
                }

            };

    private Column<MenuBarProxy, String> nombre
            = new Column<MenuBarProxy, String>(new TextCell()) {

                @Override
                public String getValue(MenuBarProxy object) {
                    return object.getDescripcion();
                }

            };        

    public void setData(List<MenuBarProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<MenuBarProxy> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<MenuBarProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<MenuBarProxy> getDataProvider() {
        return dataProvider;
    }
    
    
}
