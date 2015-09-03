/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.VendedorProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author SISTEMAS
 */
public class GridVendedor  extends DataGrid<VendedorProxy> {
    private List<VendedorProxy> data = new ArrayList<VendedorProxy>();
    private final SingleSelectionModel<VendedorProxy> selectionModel = new SingleSelectionModel<VendedorProxy>();
    private FilteredListDataProvider<VendedorProxy> dataProvider = new FilteredListDataProvider<VendedorProxy>(new IFilter<VendedorProxy>() {

        @Override
        public boolean isValid(VendedorProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getCanal().toLowerCase()+" "+value.getIdVendedor().toLowerCase()+" "+value.getNomSucursal().toLowerCase()+" "+value.getNomTienda().toLowerCase()+" "+value.getNomPtoEmision().toLowerCase()+" "+value.getDni().toLowerCase()+" "+value.getPrimerNombre().toLowerCase()+" "+value.getSegundoNombre().toLowerCase()+" "+value.getApellidoPaterno().toLowerCase()+" "+value.getApellidoMaterno().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridVendedor() {
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
        this.addColumn(img, "");
        this.addColumn(canal, "CANAL");
        this.addColumn(codigo, "CODIGO");
        this.addColumn(sucursal, "SUCURSAL");
        this.addColumn(tienda, "TIENDA");
        this.addColumn(ptoEmision, "PTO.EMISION");                
        this.addColumn(dni, "DNI");
        this.addColumn(descripcion, "VENDEDOR");
        this.addColumn(codAlterno, "COD. ALTERNO");        
        this.addColumn(correo, "CORREO");
        this.addColumn(celular, "CELULAR");
        this.addColumn(nomMesa, "MESA ACTUAL");
        this.setColumnWidth(img, 5, Unit.EM);
        this.setColumnWidth(canal, 15, Unit.EM);
        this.setColumnWidth(codigo, 8, Unit.EM);
        this.setColumnWidth(sucursal, 17, Unit.EM);
        this.setColumnWidth(tienda, 17, Unit.EM);
        this.setColumnWidth(ptoEmision, 17, Unit.EM);
        this.setColumnWidth(dni, 10, Unit.EM);
        this.setColumnWidth(descripcion, 30, Unit.EM);                        
        this.setColumnWidth(codAlterno, 15, Unit.EM);                        
        this.setColumnWidth(correo, 25, Unit.EM);                        
        this.setColumnWidth(celular, 15, Unit.EM);                        
        this.setColumnWidth(nomMesa, 15, Unit.EM);                        
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
    }
    
    private Column<VendedorProxy, ImageResource> img
            = new Column<VendedorProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(VendedorProxy object) {
                    if (object.getEstado()==1) {
                        return MyResource.INSTANCE.getImgGreen20();
                    } else if (object.getEstado()==0) {
                        return MyResource.INSTANCE.getImgGray20();
                    } else {
                        return null;
                    }
                }

            };
    
    private Column<VendedorProxy, String> canal
            = new Column<VendedorProxy, String>(new TextCell()) {

                @Override
                public String getValue(VendedorProxy object) {
                    return object.getCanal();
                }

            };
    
    private Column<VendedorProxy, String> correo
            = new Column<VendedorProxy, String>(new TextCell()) {

                @Override
                public String getValue(VendedorProxy object) {
                    return object.getCorreo();
                }

            };
    
    private Column<VendedorProxy, String> celular
            = new Column<VendedorProxy, String>(new TextCell()) {

                @Override
                public String getValue(VendedorProxy object) {
                    return object.getCelular();
                }

            };
    
    private Column<VendedorProxy, String> nomMesa
            = new Column<VendedorProxy, String>(new TextCell()) {

                @Override
                public String getValue(VendedorProxy object) {
                    return object.getNomMesa();
                }

            }; 
    
    private Column<VendedorProxy, String> codigo
            = new Column<VendedorProxy, String>(new TextCell()) {

                @Override
                public String getValue(VendedorProxy object) {
                    return object.getIdVendedor();
                }

            };      
    
    private Column<VendedorProxy, String> sucursal
            = new Column<VendedorProxy, String>(new TextCell()) {

                @Override
                public String getValue(VendedorProxy object) {
                    return object.getNomSucursal();
                }

            };      
    
    private Column<VendedorProxy, String> tienda
            = new Column<VendedorProxy, String>(new TextCell()) {

                @Override
                public String getValue(VendedorProxy object) {
                    return object.getNomTienda();
                }

            };      
    
    
    private Column<VendedorProxy, String> ptoEmision
            = new Column<VendedorProxy, String>(new TextCell()) {

                @Override
                public String getValue(VendedorProxy object) {
                    return object.getNomPtoEmision();
                }

            };     
    
    private Column<VendedorProxy, String> dni
            = new Column<VendedorProxy, String>(new TextCell()) {

                @Override
                public String getValue(VendedorProxy object) {
                    return object.getDni();
                }

            };     
    
     private Column<VendedorProxy, String> codAlterno
            = new Column<VendedorProxy, String>(new TextCell()) {

                @Override
                public String getValue(VendedorProxy object) {
                    return object.getCodigoAlterno();
                }

            };  

    private Column<VendedorProxy, String> descripcion
            = new Column<VendedorProxy, String>(new TextCell()) {

                @Override
                public String getValue(VendedorProxy object) {
                    if(object.getPrimerNombre()==null){
                        return object.getNomVendedor();
                    }else{
                    return object.getPrimerNombre()+" "+object.getSegundoNombre()+" "+object.getApellidoPaterno()+" "+object.getApellidoMaterno();
                    }
                }

            };      

    public void setData(List<VendedorProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);        
        dataProvider.refresh();
    }

    public List<VendedorProxy> getData() {
        return data;
    }        

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<VendedorProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<VendedorProxy> getDataProvider() {
        return dataProvider;
    }
}
