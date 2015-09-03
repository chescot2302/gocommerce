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
import com.webgocommerce.client.beanproxy.UsuarioCorrelativoProxy;
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
public class GridUsuarioCorrelativo extends DataGrid<UsuarioCorrelativoProxy> {
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private List<UsuarioCorrelativoProxy> data = new ArrayList<UsuarioCorrelativoProxy>();
    private final SingleSelectionModel<UsuarioCorrelativoProxy> selectionModel = new SingleSelectionModel<UsuarioCorrelativoProxy>();
    private FilteredListDataProvider<UsuarioCorrelativoProxy> dataProvider = new FilteredListDataProvider<UsuarioCorrelativoProxy>(new IFilter<UsuarioCorrelativoProxy>() {

        @Override
        public boolean isValid(UsuarioCorrelativoProxy value, String filter) {
            if (filter == null || value == null) {
                return true;
            } else {
                String values = value.getId().toString().toLowerCase()+" "+value.getNomDocumento().toLowerCase()+" "+value.getSerie().toLowerCase()+" "+value.getNomAcceso().toLowerCase()+" "+value.getNomUsuario().toLowerCase()+" "+value.getNomPtoEmision().toLowerCase()+" "+value.getNomTienda().toLowerCase()+" "+value.getNomSucursal().toLowerCase()+" "+value.getEstado().toLowerCase()+" "+dateFormat.format(value.getFechaIni());
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridUsuarioCorrelativo() {
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
        //this.setSelectionModel(selectionModel, DefaultSelectionEventManager.<UsuarioCorrelativoProxy>createCheckboxManager());
    }

    private void initColumns() {  
        this.addColumn(img, "");
        this.addColumn(codigo, "COD");
        this.addColumn(documento, "DOCUMENTO");
        this.addColumn(serie, "SERIE");
        this.addColumn(usuario, "USUARIO");
        this.addColumn(trabajador, "TRABAJADOR");
        this.addColumn(ptoEmision, "PTO. EMISION");
        this.addColumn(tienda, "TIENDA");
        this.addColumn(sucursal, "SUCURSAL");     
        this.addColumn(estado, "EST.");
        this.addColumn(fechaIni, "FECHA INI");
        this.addColumn(fechaFin, "FECHA FIN");
        this.setColumnWidth(img, 5, Unit.EM);
        this.setColumnWidth(codigo, 5, Unit.EM);
        this.setColumnWidth(documento, 15, Unit.EM);
        this.setColumnWidth(serie, 8, Unit.EM);
        this.setColumnWidth(usuario, 10, Unit.EM);
        this.setColumnWidth(trabajador, 20, Unit.EM);
        this.setColumnWidth(ptoEmision, 20, Unit.EM);
        this.setColumnWidth(tienda, 20, Unit.EM);
        this.setColumnWidth(sucursal, 20, Unit.EM);
        this.setColumnWidth(estado, 5, Unit.EM);
        this.setColumnWidth(fechaIni, 10, Unit.EM);
        this.setColumnWidth(fechaFin, 10, Unit.EM);        
    }
    
    private Column<UsuarioCorrelativoProxy, ImageResource> img
            = new Column<UsuarioCorrelativoProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(UsuarioCorrelativoProxy object) {
                    if (object.getEstado().equalsIgnoreCase("A")) {
                        return MyResource.INSTANCE.getImgGreen20();
                    } else if (object.getEstado().equalsIgnoreCase("D")) {
                        return MyResource.INSTANCE.getImgGray20();
                    } else {
                        return null;
                    }
                }

            };
    
    private Column<UsuarioCorrelativoProxy, Number> codigo
            = new Column<UsuarioCorrelativoProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(UsuarioCorrelativoProxy object) {
                    return object.getId();
                }

            };
    
    private Column<UsuarioCorrelativoProxy, String> estado
            = new Column<UsuarioCorrelativoProxy, String>(new TextCell()) {

                @Override
                public String getValue(UsuarioCorrelativoProxy object) {
                    return object.getEstado();
                }

            };
    
     private Column<UsuarioCorrelativoProxy, Date> fechaIni
            = new Column<UsuarioCorrelativoProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(UsuarioCorrelativoProxy object) {                    
                    return object.getFechaIni();
                }
            };
     
     private Column<UsuarioCorrelativoProxy, Date> fechaFin
            = new Column<UsuarioCorrelativoProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(UsuarioCorrelativoProxy object) {
                    return object.getFechaFin();
                }
            };
    
    private Column<UsuarioCorrelativoProxy, String> documento
            = new Column<UsuarioCorrelativoProxy, String>(new TextCell()) {

                @Override
                public String getValue(UsuarioCorrelativoProxy object) {
                    return object.getNomDocumento();
                }

            };
    
    private Column<UsuarioCorrelativoProxy, String> serie
            = new Column<UsuarioCorrelativoProxy, String>(new TextCell()) {

                @Override
                public String getValue(UsuarioCorrelativoProxy object) {
                    return object.getSerie();
                }

            };
    
    private Column<UsuarioCorrelativoProxy, String> usuario
            = new Column<UsuarioCorrelativoProxy, String>(new TextCell()) {

                @Override
                public String getValue(UsuarioCorrelativoProxy object) {
                    return object.getNomAcceso();
                }

            };
    
    private Column<UsuarioCorrelativoProxy, String> trabajador
            = new Column<UsuarioCorrelativoProxy, String>(new TextCell()) {

                @Override
                public String getValue(UsuarioCorrelativoProxy object) {
                    return object.getNomUsuario();
                }

            };
    
    private Column<UsuarioCorrelativoProxy, String> ptoEmision
            = new Column<UsuarioCorrelativoProxy, String>(new TextCell()) {

                @Override
                public String getValue(UsuarioCorrelativoProxy object) {
                    return object.getNomPtoEmision();
                }

            };
    
    private Column<UsuarioCorrelativoProxy, String> tienda
            = new Column<UsuarioCorrelativoProxy, String>(new TextCell()) {

                @Override
                public String getValue(UsuarioCorrelativoProxy object) {
                    return object.getNomTienda();
                }

            };
    
    private Column<UsuarioCorrelativoProxy, String> sucursal
            = new Column<UsuarioCorrelativoProxy, String>(new TextCell()) {

                @Override
                public String getValue(UsuarioCorrelativoProxy object) {
                    return object.getNomSucursal();
                }

            };

    private void initStyle() {
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

    public void setData(List<UsuarioCorrelativoProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<UsuarioCorrelativoProxy> getData() {
        return data;
    }

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<UsuarioCorrelativoProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<UsuarioCorrelativoProxy> getDataProvider() {
        return dataProvider;
    }

}
