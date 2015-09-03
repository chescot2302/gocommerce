/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.ClienteCallCenterProxy;
import com.webgocommerce.client.beanproxy.PrecioItemProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridClienteCall extends DataGrid<ClienteCallCenterProxy> {
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private List<ClienteCallCenterProxy> data = new ArrayList<ClienteCallCenterProxy>();
    private final SingleSelectionModel<ClienteCallCenterProxy> selectionModel = new SingleSelectionModel<ClienteCallCenterProxy>();
    private FilteredListDataProvider<ClienteCallCenterProxy> dataProvider = new FilteredListDataProvider<ClienteCallCenterProxy>(new IFilter<ClienteCallCenterProxy>() {

        @Override
        public boolean isValid(ClienteCallCenterProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getEstado().toLowerCase()+" "+value.getDni().toLowerCase()+" "+value.getNombres().toLowerCase()+" "+value.getApellidos().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridClienteCall() {
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
        this.getColumn(1).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);        
        this.getColumn(2).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(3).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(4).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(5).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(6).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void initColumns() {
        this.addColumn(img, "");
        this.addColumn(estado, "ESTADO");
        this.addColumn(dni, "DNI");        
        this.addColumn(nombres, "NOMBRES");        
        this.addColumn(apellidos, "APELLIDOS");                
        this.addColumn(fecha, "FECHA");   
        this.addColumn(observacion, "OBSERVACIONES");        
        this.setColumnWidth(img, 5, Unit.EM);
        this.setColumnWidth(estado, 10, Unit.EM);
        this.setColumnWidth(dni, 10, Unit.EM);
        this.setColumnWidth(nombres, 20, Unit.EM);
        this.setColumnWidth(apellidos, 20, Unit.EM);
        this.setColumnWidth(observacion, 100, Unit.EM);
        this.setColumnWidth(fecha, 10, Unit.EM);
    }
    
    private Column<ClienteCallCenterProxy, ImageResource> img
            = new Column<ClienteCallCenterProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(ClienteCallCenterProxy object) {
                    if (object.getEstado().equalsIgnoreCase("ACEPTADO")) {
                        return MyResource.INSTANCE.getImgGreen20();
                    } else if (object.getEstado().equalsIgnoreCase("RECHAZADO")) {
                        return MyResource.INSTANCE.getImgRed20();
                    } else {
                        return null;
                    }
                }

            };
    
    private Column<ClienteCallCenterProxy, String> estado
            = new Column<ClienteCallCenterProxy, String>(new TextCell()) {

                @Override
                public String getValue(ClienteCallCenterProxy object) {
                    return object.getEstado();
                }

            };

    private Column<ClienteCallCenterProxy, String> dni
            = new Column<ClienteCallCenterProxy, String>(new TextCell()) {

                @Override
                public String getValue(ClienteCallCenterProxy object) {
                    return object.getDni();
                }

            };     
    
    private Column<ClienteCallCenterProxy, String> nombres
            = new Column<ClienteCallCenterProxy, String>(new TextCell()) {

                @Override
                public String getValue(ClienteCallCenterProxy object) {
                    return object.getNombres();
                }

            }; 
    
    private Column<ClienteCallCenterProxy, String> apellidos
            = new Column<ClienteCallCenterProxy, String>(new TextCell()) {

                @Override
                public String getValue(ClienteCallCenterProxy object) {
                    return object.getApellidos();
                }

            }; 
    
    private Column<ClienteCallCenterProxy, String> observacion
            = new Column<ClienteCallCenterProxy, String>(new TextCell()) {

                @Override
                public String getValue(ClienteCallCenterProxy object) {
                    return object.getObservacion();
                }

            }; 
    
    private Column<ClienteCallCenterProxy, Date> fecha
            = new Column<ClienteCallCenterProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(ClienteCallCenterProxy object) {                    
                    return object.getFecha();
                }
            };

    public void setData(List<ClienteCallCenterProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<ClienteCallCenterProxy> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<ClienteCallCenterProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<ClienteCallCenterProxy> getDataProvider() {
        return dataProvider;
    }
}
