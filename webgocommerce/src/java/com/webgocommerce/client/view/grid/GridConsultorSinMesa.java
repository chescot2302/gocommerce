/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.webgocommerce.client.beanproxy.SuperVenProxy;
import com.webgocommerce.client.uiutil.CheckCellHead;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GridConsultorSinMesa extends DataGrid<SuperVenProxy> {

    private List<SuperVenProxy> data = new ArrayList<SuperVenProxy>();
    private final MultiSelectionModel<SuperVenProxy> selectionModel = new MultiSelectionModel<SuperVenProxy>();
    private FilteredListDataProvider<SuperVenProxy> dataProvider = new FilteredListDataProvider<SuperVenProxy>(new IFilter<SuperVenProxy>() {

        @Override
        public boolean isValid(SuperVenProxy value, String filter) {
            if (filter == null || value == null) {
                return true;
            } else {
                String values = value.getIdVendedor().toString().toLowerCase()+" "+value.getDni().toLowerCase()+" "+value.getNomConsultor().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridConsultorSinMesa() {
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
        //this.setSelectionModel(selectionModel);
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(this);
        pager.setVisible(true);
        this.setSelectionModel(selectionModel, DefaultSelectionEventManager.<SuperVenProxy>createCheckboxManager());
    }

    private void initColumns() {
        this.addColumn(checkColumn, headCheckAll);
        this.addColumn(codigo, "DNI");        
        this.addColumn(descripcion, "CONSULTOR");
        this.setColumnWidth(codigo, 15, Unit.PCT);        
        this.setColumnWidth(checkColumn, 6, Unit.PCT);
    }

    private void initStyle() {
        //MyResource.INSTANCE.getStlGridData().ensureInjected();
        //this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
        this.getColumn(0).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(1).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(2).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);        
    }

    public CheckCellHead checkAll = new CheckCellHead();
    Header<Boolean> headCheckAll = new Header<Boolean>(checkAll) {

        @Override
        public Boolean getValue() {
            return checkAll.isIsSelected();
        }

        @Override
        public void onBrowserEvent(Cell.Context context,
                Element elem,
                NativeEvent event) {
            if (checkAll.isIsSelected()) {
                checkAll.setIsSelected(false);
                selection(false);
            } else {
                checkAll.setIsSelected(true);
                selection(true);
            }

        }
    };

    private void selection(Boolean select) {
        if (dataProvider.getFilter()!=null && !dataProvider.getFilter().isEmpty()) {
            selectionModel.clear();
            for (int i = 0; i < dataProvider.resulted.size(); i++) {
                selectionModel.setSelected(dataProvider.resulted.get(i), select);
            }
        } else {
            selectionModel.clear();
            for (int i = 0; i < data.size(); i++) {
                selectionModel.setSelected(data.get(i), select);
            }
        }
    }

    Column<SuperVenProxy, Boolean> checkColumn
            = new Column<SuperVenProxy, Boolean>(new CheckboxCell(true, false)) {
                @Override
                public Boolean getValue(SuperVenProxy object) {
                    // Get the value from the selection model.
                    return selectionModel.isSelected(object);
                }
            };

    private Column<SuperVenProxy, String> codigo
            = new Column<SuperVenProxy, String>(new TextCell()) {

                @Override
                public String getValue(SuperVenProxy object) {
                    return object.getDni();
                }

            };


    private Column<SuperVenProxy, String> descripcion
            = new Column<SuperVenProxy, String>(new TextCell()) {

                @Override
                public String getValue(SuperVenProxy object) {
                    return object.getNomConsultor();
                }

            };

    public void setData(List<SuperVenProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<SuperVenProxy> getData() {
        return data;
    }

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public MultiSelectionModel<SuperVenProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<SuperVenProxy> getDataProvider() {
        return dataProvider;
    }
    
    public void actualizarGrid() {
        this.setVisibleRangeAndClearData(this.getVisibleRange(), true);
        selectionModel.clear();
    }

}
