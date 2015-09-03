/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.ItemProxy;
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
public class GridItemSingleSelection extends DataGrid<ItemProxy>{
    private List<ItemProxy> data = new ArrayList<ItemProxy>();
    private final SingleSelectionModel<ItemProxy> selectionModel = new SingleSelectionModel<ItemProxy>();
    private FilteredListDataItem<ItemProxy> dataProvider = new FilteredListDataItem<ItemProxy>(new IFilter<ItemProxy>() {

        @Override
        public boolean isValid(ItemProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getId().toString().toLowerCase()+" "+value.getCodigo().toLowerCase()+" "+value.getMarca().toLowerCase()+" "+value.getDescripcion().toLowerCase();                
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridItemSingleSelection() {
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

    private void initColumns() {
        this.addColumn(codigo, "CODIGO");
        this.addColumn(marca, "MARCA");
        this.addColumn(descripcion, "DESCRIPCION DE ITEM");
        this.addColumn(percepcion, "PERC.");
        this.addColumn(serie, "SERIE");
        this.setColumnWidth(codigo, 15, Unit.PCT);
        this.setColumnWidth(marca, 20, Unit.PCT);        
        this.setColumnWidth(percepcion, 15, Unit.PCT);
        this.setColumnWidth(serie, 15, Unit.PCT);
    }
    
    
    private void initStyle(){           
        MyResource.INSTANCE.getStlGridData().ensureInjected();	
        this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
    }
    
    private Column<ItemProxy, String> serie
            = new Column<ItemProxy, String>(new TextCell()) {

                @Override
                public String getValue(ItemProxy object) {
                    return Integer.parseInt(object.getLserie().toString())==1?"S":"N";
                }

            };

    private Column<ItemProxy, String> codigo
            = new Column<ItemProxy, String>(new TextCell()) {

                @Override
                public String getValue(ItemProxy object) {
                    return object.getCodigo();
                }

            };
    
    private Column<ItemProxy, String> marca
            = new Column<ItemProxy, String>(new TextCell()) {

                @Override
                public String getValue(ItemProxy object) {
                    return object.getMarca();
                }

            };    
    
    public Column<ItemProxy, String> descripcion
            = new Column<ItemProxy, String>(new TextCell()) {

                @Override
                public String getValue(ItemProxy object) {
                    return object.getDescripcion();
                }

            };
    
    private Column<ItemProxy, String> percepcion
            = new Column<ItemProxy, String>(new TextCell()) {

                @Override
                public String getValue(ItemProxy object) {
                    if(object.getPercepcion()==0){
                        return "N";
                    }else{
                        return "S";
                    }
                        
                }

            };                 

    public void setData(List<ItemProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<ItemProxy> getData() {
        return data;
    }        

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<ItemProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataItem<ItemProxy> getDataProvider() {
        return dataProvider;
    }
   
}
