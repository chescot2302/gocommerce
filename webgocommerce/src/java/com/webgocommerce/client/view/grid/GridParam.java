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
import com.webgocommerce.client.beanproxy.ParamProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridParam extends DataGrid<ParamProxy> {

    private List<ParamProxy> data = new ArrayList<ParamProxy>();
    private final SingleSelectionModel<ParamProxy> selectionModel = new SingleSelectionModel<ParamProxy>();
    private FilteredListDataProvider<ParamProxy> dataProvider = new FilteredListDataProvider<ParamProxy>(new IFilter<ParamProxy>() {

        @Override
        public boolean isValid(ParamProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getIdParam().toString().toLowerCase()+" "+value.getDescripcion().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridParam() {
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
        this.getColumn(2).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(3).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void initColumns() {
        this.addColumn(id, "ID");
        this.addColumn(abreviatura, "ABREVIATURA");        
        this.addColumn(nombre, "DESCRIPCION");        
        this.addColumn(valor, "VALOR");        
        this.setColumnWidth(id, 15, Unit.PCT);     
    }

    private Column<ParamProxy, Number> id
            = new Column<ParamProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(ParamProxy object) {
                    return object.getIdParam();
                }
            };
    
    private Column<ParamProxy, String> abreviatura
            = new Column<ParamProxy, String>(new TextCell()) {

                @Override
                public String getValue(ParamProxy object) {
                    return object.getAbreviatura();
                }

            };

    private Column<ParamProxy, String> nombre
            = new Column<ParamProxy, String>(new TextCell()) {

                @Override
                public String getValue(ParamProxy object) {
                    return object.getDescripcion();
                }

            };  
    
    private Column<ParamProxy, String> valor
            = new Column<ParamProxy, String>(new TextCell()) {

                @Override
                public String getValue(ParamProxy object) {
                    return object.getValor();
                }

            };

    public void setData(List<ParamProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<ParamProxy> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<ParamProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<ParamProxy> getDataProvider() {
        return dataProvider;
    }
}
