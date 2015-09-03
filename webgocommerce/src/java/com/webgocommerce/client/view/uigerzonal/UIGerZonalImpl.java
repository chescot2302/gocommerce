/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uigerzonal;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.GerenteZonalProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.requestfactory.ContextMantenimientoGerZonal;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.uiutil.UIActDes;
import com.webgocommerce.client.view.uihomegerzonal.UIHomeGerZonal;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIGerZonalImpl extends UIGerZonal{
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeGerZonal uiHomeGerZonal;

    public UIGerZonalImpl(UIHomeGerZonal uiHomeGerZonal) {
        this.uiHomeGerZonal=uiHomeGerZonal;
        loadTable();
    }

    @Override
    public void loadTable() {
        popup.showPopup();
        ContextMantenimientoGerZonal context = FACTORY.contextMantenimientoGerZonal();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<GerenteZonalProxy>> request = context.listarGerenteZonal(keyPublic,"T");
        request.fire(new Receiver<List<GerenteZonalProxy>>() {

            @Override
            public void onSuccess(List<GerenteZonalProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstGerenteZonal.setData(grid.getData());
                popup.hidePopup();
            }
            
            @Override
                public void onFailure(ServerFailure error) {
                    popup.hidePopup();
                    //Window.alert(error.getMessage());                    
                    Notification not=new Notification(Notification.WARNING,error.getMessage());
                    not.showPopup();
                }
        });
    }

    @Override
    public void showUIOper1() {	
        uiHomeGerZonal.getContainer().showWidget(1);
        uiHomeGerZonal.getUiMantGerZonal().setModo(UIMantenimiento.MODOINSERTAR);
        uiHomeGerZonal.getUiMantGerZonal().setBean(null);                
        uiHomeGerZonal.getUiMantGerZonal().loadFields();        
        uiHomeGerZonal.getUiMantGerZonal().cleanForm();
        uiHomeGerZonal.getUiMantGerZonal().scrollPanel.refresh();
    }   
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        GerenteZonalProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeGerZonal.getContainer().showWidget(1);
        uiHomeGerZonal.getUiMantGerZonal().setModo(UIMantenimiento.MODOUPDATE);
        uiHomeGerZonal.getUiMantGerZonal().setBean(bean);
        uiHomeGerZonal.getUiMantGerZonal().loadFields();
        uiHomeGerZonal.getUiMantGerZonal().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        GerenteZonalProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeGerZonal.getContainer().showWidget(1);
        uiHomeGerZonal.getUiMantGerZonal().setModo(UIMantenimiento.MODODELETE);
        uiHomeGerZonal.getUiMantGerZonal().setBean(bean);
        uiHomeGerZonal.getUiMantGerZonal().loadFields();
        uiHomeGerZonal.getUiMantGerZonal().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        GerenteZonalProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeGerZonal.getContainer().showWidget(1);
        uiHomeGerZonal.getUiMantGerZonal().setModo(UIMantenimiento.MODODETALLE);
        uiHomeGerZonal.getUiMantGerZonal().setBean(bean);
        uiHomeGerZonal.getUiMantGerZonal().loadFields();
        uiHomeGerZonal.getUiMantGerZonal().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    @Override
    public void exportarData() {
        int row=0;
        List<GerenteZonalProxy> lista;
        if(!grid.getDataProvider().hasFilter()){
            row=grid.getData().size();
            lista=grid.getData();
        }else{
            row=grid.getDataProvider().resulted.size();  
            lista=grid.getDataProvider().resulted;
        }
        if(row==0){
            Notification not=new Notification(Notification.ALERT,"Grid sin datos");
            not.showPopup();
            return;
        }
        FlexTable flex=new FlexTable();
        flex.setText(0, 0, "ID");
        flex.setText(0, 1, "DNI");        
        flex.setText(0, 2, "NOMBRES");        
        flex.setText(0, 3, "APELLIDOS");    
        flex.setText(0, 4, "CORREO");
        flex.setText(0, 5, "CELULAR");
        for(int j=0;j<6;j++){
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");            
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }        
        int fila=1;
        for(int i=0;i<row;i++){           
            GerenteZonalProxy bean=lista.get(i);
            flex.setText(fila, 0, bean.getId().toString());
            flex.setText(fila, 1, bean.getDni());            
            flex.setText(fila, 2, bean.getNombres());            
            flex.setText(fila, 3, bean.getApellidos());                       
            flex.setText(fila, 4, bean.getCorreo());            
            flex.setText(fila, 5, bean.getCelular());                       
            fila=fila+1;
        }
        TableToExcel.save(flex, "GerenteZonal"+(new Date()).getTime());
    }
    
    @Override
    public void activar() {
        GerenteZonalProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            if (bean.getEstado() == 0) {
                UIActDes popup = new UIActDes(this, "Activar", bean);
                popup.show();
            } else {
                Notification not = new Notification(Notification.ALERT, "GerenteZonal ya esta activado");
                not.showPopup();
            }
        } else {
            //Window.alert("Seleccione un registro de la tabla");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    @Override
    public void desactivar() {
        GerenteZonalProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            if (bean.getEstado() == 1) {
                UIActDes popup = new UIActDes(this, "Desactivar", bean);
                popup.show();
            } else {
                Notification not = new Notification(Notification.ALERT, "GerenteZonal ya esta desactivado");
                not.showPopup();
            }
        } else {
            //Window.alert("Seleccione un registro de la tabla");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    @Override
    public void processActivar(GerenteZonalProxy bean, Date fechaIncorporacion) {
        Date fecha = new Date();
        ContextMantenimientoGerZonal context = FACTORY.contextMantenimientoGerZonal();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        GerenteZonalProxy beanNew = context.create(GerenteZonalProxy.class);
        beanNew.setVersion(fecha.getTime());
        beanNew.setOperacion("A");
        beanNew.setId(bean.getId());
        beanNew.setEstado(1);
        beanNew.setFechaInc(fechaIncorporacion);
        Request<String> request = context.actDesGerenteZonal(keyPublic, beanNew);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                //Window.alert(response);
                loadTable();
                Notification not = new Notification(Notification.INFORMATION, response);
                not.showPopup();
            }
        });
    }
    
    @Override
    public void processDesactivar(GerenteZonalProxy bean, Date fechaCese) {
        Date fecha = new Date();
        ContextMantenimientoGerZonal context = FACTORY.contextMantenimientoGerZonal();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        GerenteZonalProxy beanNew = context.create(GerenteZonalProxy.class);
        beanNew.setVersion(fecha.getTime());
        beanNew.setOperacion("A");
        beanNew.setId(bean.getId());
        beanNew.setEstado(0);
        beanNew.setFechaInc(fechaCese);
        Request<String> request = context.actDesGerenteZonal(keyPublic, beanNew);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                //Window.alert(response);
                loadTable();
                Notification not = new Notification(Notification.INFORMATION, response);
                not.showPopup();
            }
        });
    }

}
