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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.GerenteZonalProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridGerenteZonal extends DataGrid<GerenteZonalProxy> {

    private List<GerenteZonalProxy> data = new ArrayList<GerenteZonalProxy>();
    private final SingleSelectionModel<GerenteZonalProxy> selectionModel = new SingleSelectionModel<GerenteZonalProxy>();
    private FilteredListDataProvider<GerenteZonalProxy> dataProvider = new FilteredListDataProvider<GerenteZonalProxy>(new IFilter<GerenteZonalProxy>() {

        @Override
        public boolean isValid(GerenteZonalProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getId().toString().toLowerCase()+" "+value.getDni().toLowerCase()+" "+value.getNombres().toLowerCase()+" "+value.getApellidos().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridGerenteZonal() {
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
        this.getColumn(4).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(5).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(6).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);        
    }

    private void initColumns() {
        this.addColumn(img, "");
        this.addColumn(id, "ID");
        this.addColumn(dni, "DNI");        
        this.addColumn(nombres, "NOMBRES");        
        this.addColumn(apellidos, "APELLIDOS");  
        this.addColumn(correo, "CORREO");
        this.addColumn(celular, "CELULAR");
        this.setColumnWidth(img, 5, Unit.EM);
        this.setColumnWidth(id, 8, Unit.EM);
        this.setColumnWidth(dni, 10, Unit.EM);
        this.setColumnWidth(nombres, 17, Unit.EM);                        
        this.setColumnWidth(apellidos, 17, Unit.EM);                         
        this.setColumnWidth(correo, 25,Unit.EM);                        
        this.setColumnWidth(celular, 15,Unit.EM);                        
    }    
    
    private Column<GerenteZonalProxy, String> correo
            = new Column<GerenteZonalProxy, String>(new TextCell()) {

                @Override
                public String getValue(GerenteZonalProxy object) {
                    return object.getCorreo();
                }

            };
    
    private Column<GerenteZonalProxy, String> celular
            = new Column<GerenteZonalProxy, String>(new TextCell()) {

                @Override
                public String getValue(GerenteZonalProxy object) {
                    return object.getCelular();
                }

            };    
    
    private Column<GerenteZonalProxy, ImageResource> img
            = new Column<GerenteZonalProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(GerenteZonalProxy object) {
                    if (object.getEstado()==1) {
                        return MyResource.INSTANCE.getImgGreen20();
                    } else if (object.getEstado()==0) {
                        return MyResource.INSTANCE.getImgGray20();
                    } else {
                        return null;
                    }
                }

            };                
          

    private Column<GerenteZonalProxy, Number> id
            = new Column<GerenteZonalProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(GerenteZonalProxy object) {
                    return object.getId();
                }
            };

    private Column<GerenteZonalProxy, String> dni
            = new Column<GerenteZonalProxy, String>(new TextCell()) {

                @Override
                public String getValue(GerenteZonalProxy object) {
                    return object.getDni();
                }

            };        
    
    private Column<GerenteZonalProxy, String> nombres
            = new Column<GerenteZonalProxy, String>(new TextCell()) {

                @Override
                public String getValue(GerenteZonalProxy object) {
                    return object.getNombres();
                }

            };        
    
    private Column<GerenteZonalProxy, String> apellidos
            = new Column<GerenteZonalProxy, String>(new TextCell()) {

                @Override
                public String getValue(GerenteZonalProxy object) {
                    return object.getApellidos();
                }

            };        

    public void setData(List<GerenteZonalProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<GerenteZonalProxy> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<GerenteZonalProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<GerenteZonalProxy> getDataProvider() {
        return dataProvider;
    }
}
