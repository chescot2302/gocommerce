/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uiclientecall;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.ClienteCallCenterProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.requestfactory.ContextGestionClienteCall;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.view.uihomeclientecall.UIHomeClienteCall;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIClienteCallImpl extends UIClienteCall {
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeClienteCall uiHomeClienteCall;

    public UIClienteCallImpl(UIHomeClienteCall uiHomeClienteCall) {
        this.uiHomeClienteCall=uiHomeClienteCall;
        loadTable();
    }

    @Override
    public void loadTable() {
        popup.showPopup();
        ContextGestionClienteCall context = FACTORY.contextGestionClienteCall();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<ClienteCallCenterProxy>> request = context.listarClienteCallCenter(keyPublic);
        request.fire(new Receiver<List<ClienteCallCenterProxy>>() {

            @Override
            public void onSuccess(List<ClienteCallCenterProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstLocalidad.setData(grid.getData());
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
        uiHomeClienteCall.getContainer().showWidget(1);
        uiHomeClienteCall.getUiMantClienteCall().setModo(UIMantenimiento.MODOINSERTAR);
        uiHomeClienteCall.getUiMantClienteCall().setBean(null);        
        uiHomeClienteCall.getUiMantClienteCall().cleanForm();
        uiHomeClienteCall.getUiMantClienteCall().loadFields();        
        uiHomeClienteCall.getUiMantClienteCall().scrollPanel.refresh();
    }   
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        ClienteCallCenterProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeClienteCall.getContainer().showWidget(1);
        uiHomeClienteCall.getUiMantClienteCall().setModo(UIMantenimiento.MODOUPDATE);
        uiHomeClienteCall.getUiMantClienteCall().setBean(bean);
        uiHomeClienteCall.getUiMantClienteCall().loadFields();
        uiHomeClienteCall.getUiMantClienteCall().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        ClienteCallCenterProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeClienteCall.getContainer().showWidget(1);
        uiHomeClienteCall.getUiMantClienteCall().setModo(UIMantenimiento.MODODELETE);
        uiHomeClienteCall.getUiMantClienteCall().setBean(bean);
        uiHomeClienteCall.getUiMantClienteCall().loadFields();
        uiHomeClienteCall.getUiMantClienteCall().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        ClienteCallCenterProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeClienteCall.getContainer().showWidget(1);
        uiHomeClienteCall.getUiMantClienteCall().setModo(UIMantenimiento.MODODETALLE);
        uiHomeClienteCall.getUiMantClienteCall().setBean(bean);
        uiHomeClienteCall.getUiMantClienteCall().loadFields();
        uiHomeClienteCall.getUiMantClienteCall().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    @Override
    public void exportarData() { 
        int row=0;
        List<ClienteCallCenterProxy> lista;
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
        flex.setText(0, 0, "ESTADO");
        flex.setText(0, 1, "DNI");
        flex.setText(0, 2, "NOMBRES");
        flex.setText(0, 3, "APELLIDOS");
        flex.setText(0, 4, "FECHA");
        flex.setText(0, 5, "OBSERVACIONES");        
        for(int j=0;j<6;j++){
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");            
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }        
        flex.getFlexCellFormatter().getElement(0, 5).getStyle().setWidth(100, Style.Unit.EM);
        int fila=1;
        for(int i=0;i<row;i++){           
            ClienteCallCenterProxy bean=lista.get(i);
            flex.setText(fila, 0, bean.getEstado());
            flex.setText(fila, 1, bean.getDni());
            flex.setText(fila, 2, bean.getNombres());
            flex.setText(fila, 3, bean.getApellidos());
            flex.setText(fila, 4, dateFormat.format(bean.getFecha()));
            flex.setText(fila, 5, bean.getObservacion());            
            fila=fila+1;
        }
        TableToExcel.save(flex, "clientecall"+(new Date()).getTime());
    }

}
