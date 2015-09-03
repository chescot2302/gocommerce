/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.AlmacenProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridAlmacen extends DataGrid<AlmacenProxy> {
    private List<AlmacenProxy> data = new ArrayList<AlmacenProxy>();
    private final SingleSelectionModel<AlmacenProxy> selectionModel = new SingleSelectionModel<AlmacenProxy>();
    private FilteredListDataProvider<AlmacenProxy> dataProvider = new FilteredListDataProvider<AlmacenProxy>(new IFilter<AlmacenProxy>() {

        @Override
        public boolean isValid(AlmacenProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getId().toLowerCase()+" "+value.getDescripcion().toLowerCase()+" "+value.getStockItemAlm().toString().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridAlmacen() {
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
        this.addColumn(estado, "");
        this.addColumn(descripcion, "ALMACEN");
        this.addColumn(stockItemAlm, "DISPONIBLE"); 
        this.setColumnWidth(estado, 60,Unit.PX);
    }
    
    private void initStyle(){           
        MyResource.INSTANCE.getStlGridData().ensureInjected();	
        this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
    }
    
    private Column<AlmacenProxy, ImageResource> estado
            = new Column<AlmacenProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(AlmacenProxy object) {
                    if(object.getId().equalsIgnoreCase(UISesion.beanUsuario.getIdAlmacen())){
                        return MyResource.INSTANCE.getImgGreen20();
                    }else{
                        return MyResource.INSTANCE.getImgYellow20();
                    }
                }

            };

    private Column<AlmacenProxy, String> descripcion
            = new Column<AlmacenProxy, String>(new TextCell()) {

                @Override
                public String getValue(AlmacenProxy object) {
                    return object.getDescripcion();
                }

            };
    
   private Column<AlmacenProxy, Number> stockItemAlm
            = new Column<AlmacenProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(AlmacenProxy object) {
                    return object.getStockItemAlm();
                }
            };      

    public void setData(List<AlmacenProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        //this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<AlmacenProxy> getData() {
        return data;
    }        

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<AlmacenProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<AlmacenProxy> getDataProvider() {
        return dataProvider;
    }
}
