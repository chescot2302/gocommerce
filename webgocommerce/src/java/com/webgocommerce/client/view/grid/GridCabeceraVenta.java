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
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.CabeceraVentaProxy;
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
public class GridCabeceraVenta extends DataGrid<CabeceraVentaProxy> {
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private List<CabeceraVentaProxy> data = new ArrayList<CabeceraVentaProxy>();
    private final SingleSelectionModel<CabeceraVentaProxy> selectionModel = new SingleSelectionModel<CabeceraVentaProxy>();
    private FilteredListDataProvider<CabeceraVentaProxy> dataProvider = new FilteredListDataProvider<CabeceraVentaProxy>(new IFilter<CabeceraVentaProxy>() {        

        @Override
        public boolean isValid(CabeceraVentaProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= dateFormat.format(value.getFechaEmision()).toLowerCase()+" "+value.getNomDoc().toLowerCase()+" "+value.getCorrelativo().toLowerCase()+" "+value.getRucCliente().toLowerCase()+" "+value.getNombreCliente().toLowerCase()+" "+value.getTipoCambio().toString()+" "+value.getTotalAfecto().toString()+" "+value.getTotalIgv().toString()+" "+value.getTotalNeto().toString()+" "+value.getNomVendedor().toLowerCase()+" "+value.getPuntoEmsion().toLowerCase()+" "+value.getCodProy().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridCabeceraVenta() {
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
        this.addColumn(fechaEmision, "FECHA");
        this.addColumn(estadoAct,"ESTADO ACTUAL");        
        this.addColumn(codProy,"SEC/SOT/PROYECTO"); 
        this.addColumn(nomdoc, "TIPO DOC");
        this.addColumn(serie, "SERIE");  
        this.addColumn(preImpreso, "PREIMPRESO");  
        this.addColumn(rucCliente, "RUC/DNI");  
        this.addColumn(nombreCliente, "CLIENTE");  
        this.addColumn(nomCondicionVenta, "CONDICION");  
        this.addColumn(fechaVencimiento, "F. VENC");  
        this.addColumn(idMoneda, "MONEDA");  
        this.addColumn(tipoCambio, "TIPO CAMBIO");  
        this.addColumn(totalAfecto,"TOTAL AFECTO");  
        this.addColumn(totalIgv, "TOTAL IGV");  
        this.addColumn(totalNeto, "TOTAL NETO"); 
        this.addColumn(totalPlan, "TOTAL PLAN"); 
        this.addColumn(dniVendedor, "DNI VENDEDOR");          
        this.addColumn(nomVendedor, "VENDEDOR");                  
        this.addColumn(puntoEmision, "PTO. EMISION");                  
        this.addColumn(tipoVenta, "TIPO VENTA");   
        //this.addColumn(observacion, "OBSERVACION");   
        this.setColumnWidth(estado, 5, com.google.gwt.dom.client.Style.Unit.EM);
        this.setColumnWidth(fechaEmision, 9, com.google.gwt.dom.client.Style.Unit.EM);    
        this.setColumnWidth(nomdoc, 9, com.google.gwt.dom.client.Style.Unit.EM);    
        this.setColumnWidth(serie, 5, com.google.gwt.dom.client.Style.Unit.EM);    
        this.setColumnWidth(preImpreso, 9, com.google.gwt.dom.client.Style.Unit.EM);    
        this.setColumnWidth(rucCliente, 9, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(nombreCliente, 25, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(nomCondicionVenta, 15, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(fechaVencimiento, 9, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(idMoneda, 9, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(tipoCambio, 9, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(totalAfecto, 10, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(totalIgv, 10, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(totalNeto, 10, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(totalPlan, 10, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(dniVendedor, 10, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(nomVendedor, 25, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(puntoEmision, 15, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(tipoVenta, 15, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(estadoAct, 25, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(codProy, 25, com.google.gwt.dom.client.Style.Unit.EM); 
        this.setColumnWidth(observacion, 100, com.google.gwt.dom.client.Style.Unit.EM); 
    }
    
    
    
    public Column<CabeceraVentaProxy, ImageResource> estado
            = new Column<CabeceraVentaProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(CabeceraVentaProxy object) {
                    if (object.getFlag().trim().equalsIgnoreCase("*")) {
                        return MyResource.INSTANCE.getImgRed20();                    
                    } else {
                        return MyResource.INSTANCE.getImgGreen20();
                    }
                }

            };
    
    private void initStyle(){ 
        for(int i=0;i<21;i++){
        this.getColumn(i).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);        
        }
    }
    
    public Column<CabeceraVentaProxy, Number> tipoCambio
            = new Column<CabeceraVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(CabeceraVentaProxy object) {
                    return object.getTipoCambio();
                }
            };
    
    public Column<CabeceraVentaProxy, Number> totalAfecto
            = new Column<CabeceraVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(CabeceraVentaProxy object) {
                    return object.getTotalAfecto();
                }
            };
    
    public Column<CabeceraVentaProxy, Number> totalIgv
            = new Column<CabeceraVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(CabeceraVentaProxy object) {
                    return object.getTotalIgv();
                }
            };
    
    public Column<CabeceraVentaProxy, Number> totalNeto
            = new Column<CabeceraVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(CabeceraVentaProxy object) {
                    return object.getTotalNeto();
                }
            };
    
    public Column<CabeceraVentaProxy, Number> totalPlan
            = new Column<CabeceraVentaProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(CabeceraVentaProxy object) {
                    return object.getTotalPlan();
                }
            };
    
    public Column<CabeceraVentaProxy, Date> fechaEmision
            = new Column<CabeceraVentaProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(CabeceraVentaProxy object) {                    
                    return object.getFechaEmision();
                }
            };
    
    public Column<CabeceraVentaProxy, Date> fechaVencimiento
            = new Column<CabeceraVentaProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(CabeceraVentaProxy object) {                    
                    return object.getFechaVencimiento();
                }
            };
    
    public Column<CabeceraVentaProxy, String> observacion
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getObservacion();
                }
            };

    
    public Column<CabeceraVentaProxy, String> nomdoc
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getNomDoc();
                }
            };
    
    public Column<CabeceraVentaProxy, String> serie
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getSerie();
                }

            };    

    public Column<CabeceraVentaProxy, String> preImpreso
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getPreimpreso();
                }

            };            
    
    public Column<CabeceraVentaProxy, String> rucCliente
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getRucCliente();
                }

            };
    
    public Column<CabeceraVentaProxy, String> nombreCliente
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getNombreCliente();
                }

            };
    
    public Column<CabeceraVentaProxy, String> nomCondicionVenta
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getNomCondicionVenta();
                }

            };
    
     public Column<CabeceraVentaProxy, String> idMoneda
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getIdMoneda().equals("D")?"DOLARES":"SOLES";
                }

            };
     
     public Column<CabeceraVentaProxy, String> dniVendedor
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getDniVendedor();
                }

            };
     
     public Column<CabeceraVentaProxy, String> nomVendedor
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getNomVendedor();
                }

            };
     
     public Column<CabeceraVentaProxy, String> puntoEmision
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getPuntoEmsion();
                }

            };
     
     public Column<CabeceraVentaProxy, String> tipoVenta
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getTipoVenta();
                }

            };
     
     public Column<CabeceraVentaProxy, String> dniSupervisor
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getDniSupervisor();
                }

            };
     
     public Column<CabeceraVentaProxy, String> nomSupervisor
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getNomSupervisor();
                }

            };
     
     public Column<CabeceraVentaProxy, String> dniGerenteZonal
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getDniGerenteZonal();
                }

            };
     
     public Column<CabeceraVentaProxy, String> nomGerenteZonal
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getNomGerenteZonal();
                }

            };
     
     public Column<CabeceraVentaProxy, String> dniCoordinador
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getDniCoordinador();
                }

            };
     
     public Column<CabeceraVentaProxy, String> nomCoordinador
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getNomCoordinador();
                }

            };
     
     public Column<CabeceraVentaProxy, String> nomMesa
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getNomMesa();
                }

            };
     
     public Column<CabeceraVentaProxy, String> auditor
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getUsuarioReg();
                }

            };
     
     public Column<CabeceraVentaProxy, String> estadoAct
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getEstadoActual();
                }

            };
     
     public Column<CabeceraVentaProxy, String> codProy
            = new Column<CabeceraVentaProxy, String>(new TextCell()) {

                @Override
                public String getValue(CabeceraVentaProxy object) {
                    return object.getCodProy();
                }

            };

    public void setData(List<CabeceraVentaProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<CabeceraVentaProxy> getData() {
        return data;
    }        

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<CabeceraVentaProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<CabeceraVentaProxy> getDataProvider() {
        return dataProvider;
    }
}
