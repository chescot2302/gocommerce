/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uilocalidad;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.LocalidadProxy;
import com.webgocommerce.client.beanproxy.UsuarioCorrelativoProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.requestfactory.ContextMantenimientoLocalidad;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.view.uihomelocalidad.UIHomeLocalidad;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UILocalidadImpl  extends UILocalidad {
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeLocalidad uiHomeLocalidad;

    public UILocalidadImpl(UIHomeLocalidad uiHomeLocalidad) {
        this.uiHomeLocalidad=uiHomeLocalidad;
        loadTable();
    }

    @Override
    public void loadTable() {
        popup.showPopup();
        ContextMantenimientoLocalidad context = FACTORY.contextMantenimientoLocalidad();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<LocalidadProxy>> request = context.listarLocalidad(keyPublic);
        request.fire(new Receiver<List<LocalidadProxy>>() {

            @Override
            public void onSuccess(List<LocalidadProxy> response) {
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
        uiHomeLocalidad.getContainer().showWidget(1);
        uiHomeLocalidad.getUiMantLocalidad().setModo(UIMantenimiento.MODOINSERTAR);
        uiHomeLocalidad.getUiMantLocalidad().setBean(null);        
        uiHomeLocalidad.getUiMantLocalidad().cleanForm();
        uiHomeLocalidad.getUiMantLocalidad().loadFields();        
        uiHomeLocalidad.getUiMantLocalidad().scrollPanel.refresh();
    }   
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        LocalidadProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeLocalidad.getContainer().showWidget(1);
        uiHomeLocalidad.getUiMantLocalidad().setModo(UIMantenimiento.MODOUPDATE);
        uiHomeLocalidad.getUiMantLocalidad().setBean(bean);
        uiHomeLocalidad.getUiMantLocalidad().loadFields();
        uiHomeLocalidad.getUiMantLocalidad().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        LocalidadProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeLocalidad.getContainer().showWidget(1);
        uiHomeLocalidad.getUiMantLocalidad().setModo(UIMantenimiento.MODODELETE);
        uiHomeLocalidad.getUiMantLocalidad().setBean(bean);
        uiHomeLocalidad.getUiMantLocalidad().loadFields();
        uiHomeLocalidad.getUiMantLocalidad().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        LocalidadProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeLocalidad.getContainer().showWidget(1);
        uiHomeLocalidad.getUiMantLocalidad().setModo(UIMantenimiento.MODODETALLE);
        uiHomeLocalidad.getUiMantLocalidad().setBean(bean);
        uiHomeLocalidad.getUiMantLocalidad().loadFields();
        uiHomeLocalidad.getUiMantLocalidad().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    @Override
    public void exportarData() {
        int row=0;
        List<LocalidadProxy> lista;
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
        flex.setText(0, 1, "DESCRIPCION");        
        for(int j=0;j<2;j++){
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");            
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }        
        int fila=1;
        for(int i=0;i<row;i++){           
            LocalidadProxy bean=lista.get(i);
            flex.setText(fila, 0, bean.getIdLocalidad().toString());
            flex.setText(fila, 1, bean.getDescripcion());            
            fila=fila+1;
        }
        TableToExcel.save(flex, "localidad"+(new Date()).getTime());
    }

}
