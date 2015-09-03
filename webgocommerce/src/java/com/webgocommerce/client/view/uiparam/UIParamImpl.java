/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uiparam;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.LocalidadProxy;
import com.webgocommerce.client.beanproxy.ParamProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.requestfactory.ContextMantenimientoParam;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.view.uihomeparam.UIHomeParam;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIParamImpl extends UIParam {
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeParam uiHomeParam;

    public UIParamImpl(UIHomeParam uiHomeParam) {
        this.uiHomeParam=uiHomeParam;
        loadTable();
    }

    @Override
    public void loadTable() {
        popup.showPopup();
        ContextMantenimientoParam context = FACTORY.contextMantenimientoParam();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<ParamProxy>> request = context.listarParam(keyPublic);
        request.fire(new Receiver<List<ParamProxy>>() {

            @Override
            public void onSuccess(List<ParamProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstParam.setData(grid.getData());
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
        uiHomeParam.getContainer().showWidget(1);
        uiHomeParam.getUiMantParam().setModo(UIMantenimiento.MODOINSERTAR);
        uiHomeParam.getUiMantParam().setBean(null);        
        uiHomeParam.getUiMantParam().cleanForm();
        uiHomeParam.getUiMantParam().loadFields();        
        uiHomeParam.getUiMantParam().scrollPanel.refresh();
    }   
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        ParamProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeParam.getContainer().showWidget(1);
        uiHomeParam.getUiMantParam().setModo(UIMantenimiento.MODOUPDATE);
        uiHomeParam.getUiMantParam().setBean(bean);
        uiHomeParam.getUiMantParam().loadFields();
        uiHomeParam.getUiMantParam().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        ParamProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeParam.getContainer().showWidget(1);
        uiHomeParam.getUiMantParam().setModo(UIMantenimiento.MODODELETE);
        uiHomeParam.getUiMantParam().setBean(bean);
        uiHomeParam.getUiMantParam().loadFields();
        uiHomeParam.getUiMantParam().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        ParamProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeParam.getContainer().showWidget(1);
        uiHomeParam.getUiMantParam().setModo(UIMantenimiento.MODODETALLE);
        uiHomeParam.getUiMantParam().setBean(bean);
        uiHomeParam.getUiMantParam().loadFields();
        uiHomeParam.getUiMantParam().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    @Override
    public void exportarData() {  
        int row=0;
        List<ParamProxy> lista;
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
        flex.setText(0, 1, "ABREVIATURA");        
        flex.setText(0, 2, "DESCRIPCION");        
        flex.setText(0, 3, "VALOR");        
        for(int j=0;j<4;j++){
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");            
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }        
        int fila=1;
        for(int i=0;i<row;i++){           
            ParamProxy bean=lista.get(i);
            flex.setText(fila, 0, bean.getIdParam().toString());
            flex.setText(fila, 1, bean.getAbreviatura());            
            flex.setText(fila, 2, bean.getDescripcion());            
            flex.setText(fila, 3, bean.getValor());            
            fila=fila+1;
        }
        TableToExcel.save(flex, "parametro"+(new Date()).getTime());
    }

}
