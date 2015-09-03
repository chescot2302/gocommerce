/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uisupervisor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.SupervisorProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.requestfactory.ContextMantenimientoSupervisor;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.uiutil.UIActDes;
import com.webgocommerce.client.view.uihomesupervisor.UIHomeSupervisor;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UISupervisorImpl extends UISupervisor {
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeSupervisor uiHomeSupervisor;

    public UISupervisorImpl(UIHomeSupervisor uiHomeSupervisor) {
        this.uiHomeSupervisor=uiHomeSupervisor;
        loadTable();
    }

    @Override
    public void loadTable() {
        popup.showPopup();
        ContextMantenimientoSupervisor context = FACTORY.contextMantenimientoSupervisor();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<SupervisorProxy>> request = context.listarSupervisor(keyPublic,"T");
        request.fire(new Receiver<List<SupervisorProxy>>() {

            @Override
            public void onSuccess(List<SupervisorProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstSupervisor.setData(grid.getData());
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
        uiHomeSupervisor.getContainer().showWidget(1);
        uiHomeSupervisor.getUiMantSupervisor().setModo(UIMantenimiento.MODOINSERTAR);
        uiHomeSupervisor.getUiMantSupervisor().setBean(null);                
        uiHomeSupervisor.getUiMantSupervisor().loadSucursal();
        uiHomeSupervisor.getUiMantSupervisor().loadFields();        
        uiHomeSupervisor.getUiMantSupervisor().cleanForm();
        uiHomeSupervisor.getUiMantSupervisor().scrollPanel.refresh();
    }   
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        SupervisorProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeSupervisor.getContainer().showWidget(1);
        uiHomeSupervisor.getUiMantSupervisor().setModo(UIMantenimiento.MODOUPDATE);
        uiHomeSupervisor.getUiMantSupervisor().setBean(bean);
        uiHomeSupervisor.getUiMantSupervisor().loadSucursal();
        uiHomeSupervisor.getUiMantSupervisor().loadFields();
        uiHomeSupervisor.getUiMantSupervisor().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        SupervisorProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeSupervisor.getContainer().showWidget(1);
        uiHomeSupervisor.getUiMantSupervisor().setModo(UIMantenimiento.MODODELETE);
        uiHomeSupervisor.getUiMantSupervisor().setBean(bean);
        uiHomeSupervisor.getUiMantSupervisor().loadSucursal();
        uiHomeSupervisor.getUiMantSupervisor().loadFields();
        uiHomeSupervisor.getUiMantSupervisor().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        SupervisorProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeSupervisor.getContainer().showWidget(1);
        uiHomeSupervisor.getUiMantSupervisor().setModo(UIMantenimiento.MODODETALLE);
        uiHomeSupervisor.getUiMantSupervisor().setBean(bean);
        uiHomeSupervisor.getUiMantSupervisor().loadSucursal();
        uiHomeSupervisor.getUiMantSupervisor().loadFields();
        uiHomeSupervisor.getUiMantSupervisor().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    @Override
    public void exportarData() {
        int row=0;
        List<SupervisorProxy> lista;
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
        flex.setText(0, 1, "CANAL");
        flex.setText(0, 2, "SUCURSAL");
        flex.setText(0, 3, "TIENDA");
        flex.setText(0, 4, "PTO. EMISION");
        flex.setText(0, 5, "DNI");        
        flex.setText(0, 6, "NOMBRES");        
        flex.setText(0, 7, "APELLIDOS");    
        flex.setText(0, 8, "COD. ALTERNO");
        flex.setText(0, 9, "CORREO");
        flex.setText(0, 10, "CELULAR");
        flex.setText(0, 11, "MESA");
        for(int j=0;j<12;j++){
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");            
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }        
        int fila=1;
        for(int i=0;i<row;i++){           
            SupervisorProxy bean=lista.get(i);
            flex.setText(fila, 0, bean.getIdSupervisor().toString());
            flex.setText(fila, 1, bean.getCanal());
            flex.setText(fila, 2, bean.getNomSucursal());
            flex.setText(fila, 3, bean.getNomTienda());
            flex.setText(fila, 4, bean.getNomPtoEmision());
            flex.setText(fila, 5, bean.getDni());            
            flex.setText(fila, 6, bean.getNombres());            
            flex.setText(fila, 7, bean.getApellidos());            
            flex.setText(fila, 8, bean.getCodigoAlterno());            
            flex.setText(fila, 9, bean.getCorreo());            
            flex.setText(fila, 10, bean.getCelular());            
            flex.setText(fila, 11, bean.getNomMesa());            
            fila=fila+1;
        }
        TableToExcel.save(flex, "Supervisor"+(new Date()).getTime());
    }
    
    @Override
    public void activar() {
        SupervisorProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            if (bean.getEstado() == 0) {
                UIActDes popup = new UIActDes(this, "Activar", bean);
                popup.show();
            } else {
                Notification not = new Notification(Notification.ALERT, "Supervisor ya esta activado");
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
        SupervisorProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            if (bean.getEstado() == 1) {
                UIActDes popup = new UIActDes(this, "Desactivar", bean);
                popup.show();
            } else {
                Notification not = new Notification(Notification.ALERT, "Supervisor ya esta desactivado");
                not.showPopup();
            }
        } else {
            //Window.alert("Seleccione un registro de la tabla");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    @Override
    public void processActivar(SupervisorProxy bean, Date fechaIncorporacion) {
        Date fecha = new Date();
        ContextMantenimientoSupervisor context = FACTORY.contextMantenimientoSupervisor();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        SupervisorProxy beanNew = context.create(SupervisorProxy.class);
        beanNew.setVersion(fecha.getTime());
        beanNew.setOperacion("A");
        beanNew.setIdSupervisor(bean.getIdSupervisor());
        beanNew.setEstado(1);
        beanNew.setFechaInc(fechaIncorporacion);
        Request<String> request = context.actDesSupervisor(keyPublic, beanNew);
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
    public void processDesactivar(SupervisorProxy bean, Date fechaCese) {
        Date fecha = new Date();
        ContextMantenimientoSupervisor context = FACTORY.contextMantenimientoSupervisor();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        SupervisorProxy beanNew = context.create(SupervisorProxy.class);
        beanNew.setVersion(fecha.getTime());
        beanNew.setOperacion("A");
        beanNew.setIdSupervisor(bean.getIdSupervisor());
        beanNew.setEstado(0);
        beanNew.setFechaInc(fechaCese);
        Request<String> request = context.actDesSupervisor(keyPublic, beanNew);
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
