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
import com.webgocommerce.client.beanproxy.ListaPrecioProxy;
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
public class GridListaPrecio extends DataGrid<ListaPrecioProxy> {
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private List<ListaPrecioProxy> data = new ArrayList<ListaPrecioProxy>();
    private final SingleSelectionModel<ListaPrecioProxy> selectionModel = new SingleSelectionModel<ListaPrecioProxy>();
    private FilteredListDataProvider<ListaPrecioProxy> dataProvider = new FilteredListDataProvider<ListaPrecioProxy>(new IFilter<ListaPrecioProxy>() {        

        @Override
        public boolean isValid(ListaPrecioProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getCanal().toLowerCase()+" "+value.getId().toString().toLowerCase()+" "+value.getBeanCategoriaLista().getDescripcion().toLowerCase()+" "+value.getDescripcion().toLowerCase()+" "+value.getCondicion().toLowerCase()+" "+value.getPagoMensual().toString().toLowerCase()+" "+value.getVigencia().toString().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridListaPrecio() {
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
        this.addColumn(estado, "EST");
        this.addColumn(id, "ID");
        this.addColumn(canal, "CANAL");
        this.addColumn(categoria, "CATEGORIA");
        this.addColumn(descripcion, "DESCRIPCION");
        this.addColumn(condicion, "CONDICION");
        this.addColumn(pagoMensual, "PAGO(S/.)");
        this.addColumn(vigencia, "# MESES");
        this.addColumn(fechaIni, "FECHA INICIO");
        this.addColumn(fechaFin, "FECHA FIN");
        this.addColumn(estadoActiva, "ESTADO");
        this.setColumnWidth(estado, 5, Unit.EM);
        this.setColumnWidth(id, 5, Unit.EM);
        this.setColumnWidth(canal, 15, Unit.EM);
        this.setColumnWidth(categoria, 25, Unit.EM);
        this.setColumnWidth(descripcion, 30, Unit.EM);
        this.setColumnWidth(condicion, 10, Unit.EM);
        this.setColumnWidth(pagoMensual, 10, Unit.EM);
        this.setColumnWidth(vigencia, 10, Unit.EM);
        this.setColumnWidth(fechaIni, 10, Unit.EM);
        this.setColumnWidth(fechaFin, 10, Unit.EM);
        this.setColumnWidth(estadoActiva, 10, Unit.EM);        
        
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
        this.getColumn(7).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(8).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(9).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    }
    
    private Column<ListaPrecioProxy, ImageResource> estado
            = new Column<ListaPrecioProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(ListaPrecioProxy object) {
                    if (object.getEstadoActiva().equalsIgnoreCase("A")) {
                        return MyResource.INSTANCE.getImgGreen20();
                    } else if (object.getEstadoActiva().equalsIgnoreCase("D")) {
                        return MyResource.INSTANCE.getImgGray20();
                    } else {
                        return MyResource.INSTANCE.getImgGray20();
                    }
                }

            };
    
    private Column<ListaPrecioProxy, String> canal
            = new Column<ListaPrecioProxy, String>(new TextCell()) {

                @Override
                public String getValue(ListaPrecioProxy object) {
                    return object.getCanal();
                }

            };
    
    private Column<ListaPrecioProxy, Number> id
            = new Column<ListaPrecioProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(ListaPrecioProxy object) {
                    return object.getId();
                }
            };
    
    private Column<ListaPrecioProxy, String> categoria
            = new Column<ListaPrecioProxy, String>(new TextCell()) {

                @Override
                public String getValue(ListaPrecioProxy object) {
                    return object.getBeanCategoriaLista().getDescripcion();
                }

            };    

    private Column<ListaPrecioProxy, String> descripcion
            = new Column<ListaPrecioProxy, String>(new TextCell()) {

                @Override
                public String getValue(ListaPrecioProxy object) {
                    return object.getDescripcion();
                }

            };    
    
    private Column<ListaPrecioProxy, String> condicion
            = new Column<ListaPrecioProxy, String>(new TextCell()) {

                @Override
                public String getValue(ListaPrecioProxy object) {
                    return object.getCondicion();
                }

            };  
    
    private Column<ListaPrecioProxy, Number> pagoMensual
            = new Column<ListaPrecioProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(ListaPrecioProxy object) {
                    return object.getPagoMensual();
                }
            };
    
    private Column<ListaPrecioProxy, Number> vigencia
            = new Column<ListaPrecioProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(ListaPrecioProxy object) {
                    return object.getVigencia();
                }
            };
    
    private Column<ListaPrecioProxy, Date> fechaIni
            = new Column<ListaPrecioProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(ListaPrecioProxy object) {                    
                    return object.getFechaIni();
                }
            };
    
    private Column<ListaPrecioProxy, Date> fechaFin
            = new Column<ListaPrecioProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(ListaPrecioProxy object) {
                    return object.getFechaFin();
                }
            };
    
    private Column<ListaPrecioProxy, String> estadoActiva
            = new Column<ListaPrecioProxy, String>(new TextCell()) {

                @Override
                public String getValue(ListaPrecioProxy object) {
                    return object.getEstadoActiva();
                }

            };  

    public void setData(List<ListaPrecioProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<ListaPrecioProxy> getData() {
        return data;
    }        

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<ListaPrecioProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<ListaPrecioProxy> getDataProvider() {
        return dataProvider;
    }
}
