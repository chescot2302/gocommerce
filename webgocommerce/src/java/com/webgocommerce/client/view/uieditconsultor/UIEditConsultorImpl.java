/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uieditconsultor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.SuperVenProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoSuperVen;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.view.uihomemesa.UIHomeMesa;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
public class UIEditConsultorImpl extends UIEditConsultor {

    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeMesa uiHomeMesa;
    
    public UIEditConsultorImpl(UIHomeMesa uiHomeMesa) {
        this.uiHomeMesa = uiHomeMesa;
    }
    
    @Override
    public void goToUIMesa() {
        uiHomeMesa.getContainer().showWidget(0);
        uiHomeMesa.getUiMesa().loadTable();
    }
    
    @Override
    public void loadTable() {
        cargarTabla("A");
    }
    
    @Override
    public void loadTableEstado() {
        String estado = lstEstado.getValue(lstEstado.getSelectedIndex());
        cargarTabla(estado);
    }
    
    private void cargarTabla(String estado) {
        popup.showPopup();
        ContextMantenimientoSuperVen context = FACTORY.contextMantenimientoSuperVen();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<SuperVenProxy>> request = context.listar(keyPublic, beanMesa.getIdMesa(), estado);
        request.fire(new Receiver<List<SuperVenProxy>>() {
            
            @Override
            public void onSuccess(List<SuperVenProxy> response) {
                txtBuscar.setText(null);
                grid.getDataProvider().resetFilter();
                grid.setData(response);
                grid.getSelectionModel().clear();
                grid.checkAll.setIsSelected(Boolean.FALSE);
                grid.actualizarGrid();
                //lstBdEmpresa.setData(grid.getData());
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
        uiHomeMesa.getContainer().showWidget(3);
        uiHomeMesa.getUiAddConsultor().setBeanMesa(beanMesa);
        uiHomeMesa.getUiAddConsultor().loadFields();        
        uiHomeMesa.getUiAddConsultor().loadTable();
    }
    
    @Override
    public void showUIOper3() {
        if (grid.getSelectionModel().getSelectedSet().size() > 0) {
         popup.showPopup();
         ContextMantenimientoSuperVen context = FACTORY.contextMantenimientoSuperVen();
         FACTORY.initialize(EVENTBUS);
         String keyPublic = UISesion.keyPublic;
         Set<SuperVenProxy> select = grid.getSelectionModel().getSelectedSet();
         Set<SuperVenProxy> lista = new HashSet<SuperVenProxy>();
         Iterator<SuperVenProxy> i = select.iterator();
         while (i.hasNext()) {
         SuperVenProxy bean = context.create(SuperVenProxy.class);
         SuperVenProxy beanEdit = i.next();
         bean.setIdSuperVen(beanEdit.getIdSuperVen());
         bean.setIdVendedor(beanEdit.getIdVendedor());
         bean.setEstado(beanEdit.getEstado());
         bean.setVersion(beanEdit.getVersion());
         lista.add(bean);
         }
         Request<Boolean> request = context.eliminarSuperVen(keyPublic, lista);
         request.fire(new Receiver<Boolean>() {

         @Override
         public void onSuccess(Boolean response) {
         if (response) {
         popup.hidePopup();                        
         Notification not=new Notification(Notification.INFORMATION,"Items removidos de lista");
         not.showPopup();
         loadTable();
         }
         }

         @Override
         public void onFailure(ServerFailure error) {
         popup.hidePopup();                    
         Notification not=new Notification(Notification.WARNING,error.getMessage());
         not.showPopup();
         }

         });
         } else {            
         Notification not=new Notification(Notification.WARNING,"Seleccione registros de la tabla");
         not.showPopup();
         }
    }
    
    @Override
    public void showUIOper4() {
        if (grid.getSelectionModel().getSelectedSet().size() > 0) {
            popup.showPopup();
            ContextMantenimientoSuperVen context = FACTORY.contextMantenimientoSuperVen();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            Set<SuperVenProxy> select = grid.getSelectionModel().getSelectedSet();
            Set<SuperVenProxy> lista = new HashSet<SuperVenProxy>();
            Iterator<SuperVenProxy> i = select.iterator();
            while (i.hasNext()) {                
                SuperVenProxy beanEdit = i.next();
                if (beanEdit.getEstado().equalsIgnoreCase("A")) {
                    SuperVenProxy bean = context.create(SuperVenProxy.class);                    
                    bean.setIdSuperVen(beanEdit.getIdSuperVen());
                    bean.setIdVendedor(beanEdit.getIdVendedor());
                    bean.setVersion(beanEdit.getVersion());
                    lista.add(bean);
                }
            }
            Request<Boolean> request = context.desactivarSuperVen(keyPublic, lista);
            request.fire(new Receiver<Boolean>() {
                
                @Override
                public void onSuccess(Boolean response) {
                    if (response) {
                        popup.hidePopup();                        
                        Notification not = new Notification(Notification.INFORMATION, "Items desactivados de lista");
                        not.showPopup();
                        loadTable();
                    }
                }
                
                @Override
                public void onFailure(ServerFailure error) {
                    popup.hidePopup();                    
                    Notification not = new Notification(Notification.WARNING, error.getMessage());
                    not.showPopup();
                }
            });
        } else {            
            Notification not = new Notification(Notification.ALERT, "Seleccione registros de la tabla");
            not.showPopup();
        }
    }    
    
    @Override
    public void exportarData() {        
        int row = 0;
        List<SuperVenProxy> lista;
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
        flex.setText(0, 0, "DNI");        
        flex.setText(0, 1, "CONSULTOR");        
        flex.setText(0, 2, "ESTADO");        
        flex.setText(0, 3, "FECHA INI");        
        flex.setText(0, 4, "FECHA FIN");        
        for (int j = 0; j < 5; j++) {
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");            
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }        
        int fila = 1;
        for (int i = 0; i < row; i++) {            
            SuperVenProxy bean = lista.get(i);
            flex.setText(fila, 0, bean.getDni());            
            flex.setText(fila, 1, bean.getNomConsultor());            
            flex.setText(fila, 2, bean.getEstado());            
            flex.setText(fila, 3, bean.getFechaIni() != null ? dateFormat.format(bean.getFechaIni()) : "");
            flex.setText(fila, 4, bean.getFechaFin() != null ? dateFormat.format(bean.getFechaFin()) : "");            
            fila = fila + 1;
        }
        TableToExcel.save(flex, "consultores" + (new Date()).getTime());
    }
    
}
