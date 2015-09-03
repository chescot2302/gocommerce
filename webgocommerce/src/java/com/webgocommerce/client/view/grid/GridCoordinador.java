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
import com.webgocommerce.client.beanproxy.CoordinadorProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridCoordinador extends DataGrid<CoordinadorProxy> {

    private List<CoordinadorProxy> data = new ArrayList<CoordinadorProxy>();
    private final SingleSelectionModel<CoordinadorProxy> selectionModel = new SingleSelectionModel<CoordinadorProxy>();
    private FilteredListDataProvider<CoordinadorProxy> dataProvider = new FilteredListDataProvider<CoordinadorProxy>(new IFilter<CoordinadorProxy>() {

        @Override
        public boolean isValid(CoordinadorProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getId().toString().toLowerCase()+" "+value.getDni().toLowerCase()+" "+value.getNombres().toLowerCase()+" "+value.getApellidos().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridCoordinador() {
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
    
    private Column<CoordinadorProxy, String> correo
            = new Column<CoordinadorProxy, String>(new TextCell()) {

                @Override
                public String getValue(CoordinadorProxy object) {
                    return object.getCorreo();
                }

            };
    
    private Column<CoordinadorProxy, String> celular
            = new Column<CoordinadorProxy, String>(new TextCell()) {

                @Override
                public String getValue(CoordinadorProxy object) {
                    return object.getCelular();
                }

            };    
    
    private Column<CoordinadorProxy, ImageResource> img
            = new Column<CoordinadorProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(CoordinadorProxy object) {
                    if (object.getEstado()==1) {
                        return MyResource.INSTANCE.getImgGreen20();
                    } else if (object.getEstado()==0) {
                        return MyResource.INSTANCE.getImgGray20();
                    } else {
                        return null;
                    }
                }

            };                
          

    private Column<CoordinadorProxy, Number> id
            = new Column<CoordinadorProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(CoordinadorProxy object) {
                    return object.getId();
                }
            };

    private Column<CoordinadorProxy, String> dni
            = new Column<CoordinadorProxy, String>(new TextCell()) {

                @Override
                public String getValue(CoordinadorProxy object) {
                    return object.getDni();
                }

            };        
    
    private Column<CoordinadorProxy, String> nombres
            = new Column<CoordinadorProxy, String>(new TextCell()) {

                @Override
                public String getValue(CoordinadorProxy object) {
                    return object.getNombres();
                }

            };        
    
    private Column<CoordinadorProxy, String> apellidos
            = new Column<CoordinadorProxy, String>(new TextCell()) {

                @Override
                public String getValue(CoordinadorProxy object) {
                    return object.getApellidos();
                }

            };        

    public void setData(List<CoordinadorProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<CoordinadorProxy> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<CoordinadorProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<CoordinadorProxy> getDataProvider() {
        return dataProvider;
    }
}
