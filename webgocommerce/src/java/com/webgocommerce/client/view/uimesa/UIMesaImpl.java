/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimesa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.MesaProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.requestfactory.ContextMantenimientoMesa;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.view.grid.GridPrecioItem;
import com.webgocommerce.client.view.uihomemesa.UIHomeMesa;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIMesaImpl extends UIMesa {
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeMesa uiHomeMesa;

    public UIMesaImpl(UIHomeMesa uiHomeMesa) {
        this.uiHomeMesa = uiHomeMesa;
        loadTable();
    }

    @Override
    public void loadTable() {
        popup.showPopup();
        ContextMantenimientoMesa context = FACTORY.contextMantenimientoMesa();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<MesaProxy>> request = context.listar(keyPublic);
        request.fire(new Receiver<List<MesaProxy>>() {

            @Override
            public void onSuccess(List<MesaProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstMesa.setData(grid.getData());
                popup.hidePopup();
            }

            @Override
            public void onFailure(ServerFailure error) {
                popup.hidePopup();
                //Window.alert(error.getMessage());                    
                Notification not = new Notification(Notification.WARNING, error.getMessage());
                not.showPopup();
            }
        });
    }

    @Override
    public void showUIOper1() {
        uiHomeMesa.getContainer().showWidget(1);
        uiHomeMesa.getUiMantMesa().setModo(UIMantenimiento.MODOINSERTAR);
        uiHomeMesa.getUiMantMesa().setBean(null);
        uiHomeMesa.getUiMantMesa().cleanForm();
        uiHomeMesa.getUiMantMesa().loadFields();
        uiHomeMesa.getUiMantMesa().loadSupervisor();
        uiHomeMesa.getUiMantMesa().loadGerenteZonal();
        uiHomeMesa.getUiMantMesa().loadCoordinador();
        uiHomeMesa.getUiMantMesa().loadSucursal();
        uiHomeMesa.getUiMantMesa().scrollPanel.refresh();
    }

    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        MesaProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            uiHomeMesa.getContainer().showWidget(1);
            uiHomeMesa.getUiMantMesa().setModo(UIMantenimiento.MODOUPDATE);
            uiHomeMesa.getUiMantMesa().setBean(bean);
            uiHomeMesa.getUiMantMesa().loadFields();
            uiHomeMesa.getUiMantMesa().loadSupervisor();
            uiHomeMesa.getUiMantMesa().loadGerenteZonal();
            uiHomeMesa.getUiMantMesa().loadCoordinador();
            uiHomeMesa.getUiMantMesa().loadSucursal();
            uiHomeMesa.getUiMantMesa().scrollPanel.refresh();
        } else {
            //Window.alert("Seleccione un registro de la tabla");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        MesaProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            uiHomeMesa.getContainer().showWidget(1);
            uiHomeMesa.getUiMantMesa().setModo(UIMantenimiento.MODODELETE);
            uiHomeMesa.getUiMantMesa().setBean(bean);
            uiHomeMesa.getUiMantMesa().loadFields();
            uiHomeMesa.getUiMantMesa().loadSupervisor();
            uiHomeMesa.getUiMantMesa().loadGerenteZonal();
            uiHomeMesa.getUiMantMesa().loadCoordinador();
            uiHomeMesa.getUiMantMesa().loadSucursal();
            uiHomeMesa.getUiMantMesa().scrollPanel.refresh();
        } else {
            //Window.alert("Seleccione un registro de la tabla");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        MesaProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            uiHomeMesa.getContainer().showWidget(1);
            uiHomeMesa.getUiMantMesa().setModo(UIMantenimiento.MODODETALLE);
            uiHomeMesa.getUiMantMesa().setBean(bean);
            uiHomeMesa.getUiMantMesa().loadFields();
            uiHomeMesa.getUiMantMesa().loadSupervisor();
            uiHomeMesa.getUiMantMesa().loadGerenteZonal();
            uiHomeMesa.getUiMantMesa().loadCoordinador();
            uiHomeMesa.getUiMantMesa().loadSucursal();
            uiHomeMesa.getUiMantMesa().scrollPanel.refresh();
        } else {
            //Window.alert("Seleccione un registro de la tabla");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void exportarData() {
        int row = 0;
        List<MesaProxy> lista;
        if (!grid.getDataProvider().hasFilter()) {
            row = grid.getData().size();
            lista = grid.getData();
        } else {
            row = grid.getDataProvider().resulted.size();
            lista = grid.getDataProvider().resulted;
        }
        if (row == 0) {
            Notification not = new Notification(Notification.ALERT, "Grid sin datos");
            not.showPopup();
            return;
        }
        FlexTable flex = new FlexTable();
        flex.setText(0, 0, "ID");
        flex.setText(0, 1, "SUCURSAL");
        flex.setText(0, 2, "MESA");
        flex.setText(0, 3, "SUPERVISOR");
        flex.setText(0, 4, "GERENTE ZONAL");
        flex.setText(0, 5, "COORDINADOR");
        flex.setText(0, 6, "ESTADO");
        flex.setText(0, 7, "FECHA INICIO");
        flex.setText(0, 8, "FECHA FIN");
        for (int j = 0; j < 9; j++) {
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }
        int fila = 1;
        for (int i = 0; i < row; i++) {
            MesaProxy bean = lista.get(i);
            flex.setText(fila, 0, bean.getIdMesa().toString());
            flex.setText(fila, 1, bean.getNomSucursal());
            flex.setText(fila, 2, bean.getDescripcion());
            flex.setText(fila, 3, bean.getNomSupervisor());
            flex.setText(fila, 4, bean.getNomGerenteZonal());
            flex.setText(fila, 5, bean.getNomCoordinador());
            flex.setText(fila, 6, bean.getEstado());
            flex.setText(fila, 7, bean.getFechaIni()!=null?dateFormat.format(bean.getFechaIni()):"");
            flex.setText(fila, 8, bean.getFechaFin()!=null?dateFormat.format(bean.getFechaFin()):"");
            fila = fila + 1;
        }
        TableToExcel.save(flex, "mesa" + (new Date()).getTime());
    }

    @Override
    public void showUIDesactivar() {
        MesaProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null && bean.getEstado().equalsIgnoreCase("A")) {
            uiHomeMesa.getContainer().showWidget(1);
            uiHomeMesa.getUiMantMesa().setModo("DESACTIVAR");
            uiHomeMesa.getUiMantMesa().setBean(bean);
            uiHomeMesa.getUiMantMesa().loadFields();
            uiHomeMesa.getUiMantMesa().loadSupervisor();
            uiHomeMesa.getUiMantMesa().loadGerenteZonal();
            uiHomeMesa.getUiMantMesa().loadCoordinador();
            uiHomeMesa.getUiMantMesa().loadSucursal();
            uiHomeMesa.getUiMantMesa().scrollPanel.refresh();
        } else {
            //Window.alert("Seleccione un registro de la tabla\ncon estado activo");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla</br>con estado activo");
            not.showPopup();
        }
    }
    
    @Override
    public void showUIEditConsultor() {
        MesaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeMesa.getContainer().showWidget(2);                
        uiHomeMesa.getUiEditConsultor().modoGrid(bean.getEstado().equalsIgnoreCase("D")?GridPrecioItem.L:GridPrecioItem.E);
        uiHomeMesa.getUiEditConsultor().setBeanMesa(bean);   
        uiHomeMesa.getUiEditConsultor().loadFields();
        uiHomeMesa.getUiEditConsultor().loadTable();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }        
    }

}
