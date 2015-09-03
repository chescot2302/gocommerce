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
import com.webgocommerce.client.beanproxy.SupervisorProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridSupervisor extends DataGrid<SupervisorProxy> {

    private List<SupervisorProxy> data = new ArrayList<SupervisorProxy>();
    private final SingleSelectionModel<SupervisorProxy> selectionModel = new SingleSelectionModel<SupervisorProxy>();
    private FilteredListDataProvider<SupervisorProxy> dataProvider = new FilteredListDataProvider<SupervisorProxy>(new IFilter<SupervisorProxy>() {

        @Override
        public boolean isValid(SupervisorProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getCanal().toLowerCase()+" "+value.getIdSupervisor().toString().toLowerCase()+" "+value.getNomSucursal().toLowerCase()+" "+value.getNomTienda().toLowerCase()+" "+value.getNomPtoEmision().toLowerCase()+" "+value.getDni().toLowerCase()+" "+value.getNombres().toLowerCase()+" "+value.getApellidos().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridSupervisor() {
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
        this.getColumn(7).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(8).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(9).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(10).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(11).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(12).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void initColumns() {
        this.addColumn(img, "");
        this.addColumn(canal, "CANAL");
        this.addColumn(id, "ID");
        this.addColumn(dni, "DNI");        
        this.addColumn(nombres, "NOMBRES");        
        this.addColumn(apellidos, "APELLIDOS");  
        this.addColumn(codAlterno, "COD. ALTERNO");
        this.addColumn(correo, "CORREO");
        this.addColumn(celular, "CELULAR");
        this.addColumn(nomMesa, "MESA ACTUAL");
        this.addColumn(sucursal, "SUCURSAL");
        this.addColumn(tienda, "TIENDA");
        this.addColumn(ptoEmision, "PTO.EMISION");                
        this.setColumnWidth(img, 5, Unit.EM);
        this.setColumnWidth(canal, 15, Unit.EM);
        this.setColumnWidth(id, 8, Unit.EM);
        this.setColumnWidth(sucursal, 17, Unit.EM);
        this.setColumnWidth(tienda, 17, Unit.EM);
        this.setColumnWidth(ptoEmision, 17, Unit.EM);
        this.setColumnWidth(dni, 10, Unit.EM);
        this.setColumnWidth(nombres, 17, Unit.EM);                        
        this.setColumnWidth(apellidos, 17, Unit.EM);                        
        this.setColumnWidth(codAlterno, 15, Unit.EM);  
        this.setColumnWidth(correo, 25, Unit.EM);                        
        this.setColumnWidth(celular, 15, Unit.EM);                        
        this.setColumnWidth(nomMesa, 15, Unit.EM); 
    }
    
    private Column<SupervisorProxy, String> canal
            = new Column<SupervisorProxy, String>(new TextCell()) {

                @Override
                public String getValue(SupervisorProxy object) {
                    return object.getCanal();
                }

            };
    
    private Column<SupervisorProxy, String> correo
            = new Column<SupervisorProxy, String>(new TextCell()) {

                @Override
                public String getValue(SupervisorProxy object) {
                    return object.getCorreo();
                }

            };
    
    private Column<SupervisorProxy, String> celular
            = new Column<SupervisorProxy, String>(new TextCell()) {

                @Override
                public String getValue(SupervisorProxy object) {
                    return object.getCelular();
                }

            };
    
    private Column<SupervisorProxy, String> codAlterno
            = new Column<SupervisorProxy, String>(new TextCell()) {

                @Override
                public String getValue(SupervisorProxy object) {
                    return object.getCodigoAlterno();
                }

            };
    
    private Column<SupervisorProxy, ImageResource> img
            = new Column<SupervisorProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(SupervisorProxy object) {
                    if (object.getEstado()==1) {
                        return MyResource.INSTANCE.getImgGreen20();
                    } else if (object.getEstado()==0) {
                        return MyResource.INSTANCE.getImgGray20();
                    } else {
                        return null;
                    }
                }

            };
    
    private Column<SupervisorProxy, String> nomMesa
            = new Column<SupervisorProxy, String>(new TextCell()) {

                @Override
                public String getValue(SupervisorProxy object) {
                    return object.getNomMesa();
                }

            };
    
    private Column<SupervisorProxy, String> sucursal
            = new Column<SupervisorProxy, String>(new TextCell()) {

                @Override
                public String getValue(SupervisorProxy object) {
                    return object.getNomSucursal();
                }

            };      
    
    private Column<SupervisorProxy, String> tienda
            = new Column<SupervisorProxy, String>(new TextCell()) {

                @Override
                public String getValue(SupervisorProxy object) {
                    return object.getNomTienda();
                }

            };      
    
    
    private Column<SupervisorProxy, String> ptoEmision
            = new Column<SupervisorProxy, String>(new TextCell()) {

                @Override
                public String getValue(SupervisorProxy object) {
                    return object.getNomPtoEmision();
                }

            };     

    private Column<SupervisorProxy, Number> id
            = new Column<SupervisorProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(SupervisorProxy object) {
                    return object.getIdSupervisor();
                }
            };

    private Column<SupervisorProxy, String> dni
            = new Column<SupervisorProxy, String>(new TextCell()) {

                @Override
                public String getValue(SupervisorProxy object) {
                    return object.getDni();
                }

            };        
    
    private Column<SupervisorProxy, String> nombres
            = new Column<SupervisorProxy, String>(new TextCell()) {

                @Override
                public String getValue(SupervisorProxy object) {
                    return object.getNombres();
                }

            };        
    
    private Column<SupervisorProxy, String> apellidos
            = new Column<SupervisorProxy, String>(new TextCell()) {

                @Override
                public String getValue(SupervisorProxy object) {
                    return object.getApellidos();
                }

            };        

    public void setData(List<SupervisorProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<SupervisorProxy> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<SupervisorProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<SupervisorProxy> getDataProvider() {
        return dataProvider;
    }
}
