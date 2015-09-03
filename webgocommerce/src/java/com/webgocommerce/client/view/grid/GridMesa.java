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
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.MesaProxy;
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
public class GridMesa extends DataGrid<MesaProxy> {
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private List<MesaProxy> data = new ArrayList<MesaProxy>();
    private final SingleSelectionModel<MesaProxy> selectionModel = new SingleSelectionModel<MesaProxy>();
    private FilteredListDataProvider<MesaProxy> dataProvider = new FilteredListDataProvider<MesaProxy>(new IFilter<MesaProxy>() {

        @Override
        public boolean isValid(MesaProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getIdMesa().toString().toLowerCase()+" "+value.getDescripcion().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridMesa() {
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
    }

    private void initColumns() {
        this.addColumn(est, "EST");
        this.addColumn(id, "ID");
        this.addColumn(sucursal, "SUCURSAL");        
        this.addColumn(nombre, "MESA");        
        this.addColumn(supervisor, "SUPERVISOR");        
        this.addColumn(gerenteZonal, "GERENTE ZONAL");        
        this.addColumn(coordinador, "COORDINADOR");        
        this.addColumn(fechaIni, "FECHA INI");        
        this.addColumn(fechaFin, "FECHA FIN");        
        this.addColumn(estado, "ESTADO");        
        this.setColumnWidth(est, 5, Unit.EM);        
        this.setColumnWidth(id, 5, Unit.EM);        
        this.setColumnWidth(sucursal, 20, Unit.EM);        
        this.setColumnWidth(nombre, 20, Unit.EM);        
        this.setColumnWidth(supervisor, 25, Unit.EM);        
        this.setColumnWidth(gerenteZonal, 25, Unit.EM);        
        this.setColumnWidth(coordinador, 25, Unit.EM);        
        this.setColumnWidth(fechaIni, 10, Unit.EM);        
        this.setColumnWidth(fechaFin, 10, Unit.EM);        
        this.setColumnWidth(estado, 10, Unit.EM);        
    }
    
    private Column<MesaProxy, ImageResource> est
            = new Column<MesaProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(MesaProxy object) {
                    if (object.getEstado().equalsIgnoreCase("A")) {
                        return MyResource.INSTANCE.getImgGreen20();
                    } else if (object.getEstado().equalsIgnoreCase("D")) {
                        return MyResource.INSTANCE.getImgGray20();
                    } else {
                        return MyResource.INSTANCE.getImgGray20();
                    }
                }

            };
    
    private Column<MesaProxy, Date> fechaIni
            = new Column<MesaProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(MesaProxy object) {                    
                    return object.getFechaIni();
                }
            };
    
    private Column<MesaProxy, Date> fechaFin
            = new Column<MesaProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(MesaProxy object) {
                    return object.getFechaFin();
                }
            };
    
    private Column<MesaProxy, String> estado
            = new Column<MesaProxy, String>(new TextCell()) {

                @Override
                public String getValue(MesaProxy object) {
                    return object.getEstado();
                }

            };  
    
    private Column<MesaProxy, String> coordinador
            = new Column<MesaProxy, String>(new TextCell()) {

                @Override
                public String getValue(MesaProxy object) {
                    return object.getNomCoordinador();
                }

            };
    
    private Column<MesaProxy, String> gerenteZonal
            = new Column<MesaProxy, String>(new TextCell()) {

                @Override
                public String getValue(MesaProxy object) {
                    return object.getNomGerenteZonal();
                }

            };
    
    private Column<MesaProxy, String> supervisor
            = new Column<MesaProxy, String>(new TextCell()) {

                @Override
                public String getValue(MesaProxy object) {
                    return object.getNomSupervisor();
                }

            };
    
    private Column<MesaProxy, String> sucursal
            = new Column<MesaProxy, String>(new TextCell()) {

                @Override
                public String getValue(MesaProxy object) {
                    return object.getNomSucursal();
                }

            };

    private Column<MesaProxy, Number> id
            = new Column<MesaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(MesaProxy object) {
                    return object.getIdMesa();
                }
            };

    private Column<MesaProxy, String> nombre
            = new Column<MesaProxy, String>(new TextCell()) {

                @Override
                public String getValue(MesaProxy object) {
                    return object.getDescripcion();
                }

            };        

    public void setData(List<MesaProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<MesaProxy> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<MesaProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<MesaProxy> getDataProvider() {
        return dataProvider;
    }
}
