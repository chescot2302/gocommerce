/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.ImageResourceCell;
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
import com.webgocommerce.client.beanproxy.EstProyProxy;
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
public class GridEstProy extends DataGrid<EstProyProxy> {
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private List<EstProyProxy> data = new ArrayList<EstProyProxy>();
    private final SingleSelectionModel<EstProyProxy> selectionModel = new SingleSelectionModel<EstProyProxy>();
    private FilteredListDataProvider<EstProyProxy> dataProvider = new FilteredListDataProvider<EstProyProxy>(new IFilter<EstProyProxy>() {

        @Override
        public boolean isValid(EstProyProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getIdEstadoProy().toString();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridEstProy() {
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
    }

    private void initColumns() {
        this.addColumn(img, "");
        this.addColumn(estadoActual, "ESTADO");
        this.addColumn(fechaIni, "FECHA INICIO");        
        this.addColumn(fechaFin, "FECHA FIN");                
        this.addColumn(comentario, "COMENTARIO");                
        this.setColumnWidth(img, 5, Unit.EM);
        this.setColumnWidth(estadoActual, 15, Unit.EM);
        this.setColumnWidth(fechaIni, 10, Unit.EM);
        this.setColumnWidth(fechaFin, 10, Unit.EM);                                               
        this.setColumnWidth(comentario, 50, Unit.EM);                                               
    }
    
    private Column<EstProyProxy, String> comentario
            = new Column<EstProyProxy, String>(new TextCell()) {

                @Override
                public String getValue(EstProyProxy object) {
                    return object.getObservacion();
                }

            }; 
    
    private Column<EstProyProxy, Date> fechaIni
            = new Column<EstProyProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(EstProyProxy object) {
                    return object.getFechaInc();
                }
            };

    private Column<EstProyProxy, Date> fechaFin
            = new Column<EstProyProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(EstProyProxy object) {
                    return object.getFechaCese();
                }
            };
  
    
    private Column<EstProyProxy, ImageResource> img
            = new Column<EstProyProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(EstProyProxy object) {
                    if (object.getEstado()==1) {
                        return MyResource.INSTANCE.getImgGreen20();
                    } else if (object.getEstado()==0) {
                        return MyResource.INSTANCE.getImgGray20();
                    } else {
                        return null;
                    }
                }

            };                
          


    private Column<EstProyProxy, String> estadoActual
            = new Column<EstProyProxy, String>(new TextCell()) {

                @Override
                public String getValue(EstProyProxy object) {
                    return object.getEstadoActual();
                }

            };                        

    public void setData(List<EstProyProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<EstProyProxy> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<EstProyProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<EstProyProxy> getDataProvider() {
        return dataProvider;
    }
}
