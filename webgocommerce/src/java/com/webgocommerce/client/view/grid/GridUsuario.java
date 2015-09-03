/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.webgocommerce.client.beanproxy.UsuarioProxy;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridUsuario  extends DataGrid<UsuarioProxy> {
    private List<UsuarioProxy> data = new ArrayList<UsuarioProxy>();
    private final SingleSelectionModel<UsuarioProxy> selectionModel = new SingleSelectionModel<UsuarioProxy>();
    private FilteredListDataProvider<UsuarioProxy> dataProvider = new FilteredListDataProvider<UsuarioProxy>(new IFilter<UsuarioProxy>() {

        @Override
        public boolean isValid(UsuarioProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getId().toLowerCase()+" "+value.getNick().toLowerCase()+" "+value.getNombres().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridUsuario() {
        initComponents();
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
        this.addColumn(nick, "USUARIO");
        this.addColumn(nombres, "TRABAJADOR");
        
    }
    
    private Column<UsuarioProxy, String> nick
            = new Column<UsuarioProxy, String>(new TextCell()) {

                @Override
                public String getValue(UsuarioProxy object) {
                    return object.getNick();
                }

            };
    
    private Column<UsuarioProxy, String> nombres
            = new Column<UsuarioProxy, String>(new TextCell()) {

                @Override
                public String getValue(UsuarioProxy object) {
                    return object.getNombres();
                }

            };

    public void setData(List<UsuarioProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }
    
    public void actualizarGrid(){
        this.setVisibleRangeAndClearData(this.getVisibleRange(), true);
        selectionModel.clear();
    }

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<UsuarioProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<UsuarioProxy> getDataProvider() {
        return dataProvider;
    }
}
