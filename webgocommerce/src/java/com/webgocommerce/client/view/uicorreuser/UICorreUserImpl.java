/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uicorreuser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.UsuarioCorrelativoProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.requestfactory.ContextMantenimientoUsuarioCorrelativo;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.view.uihomecorreuser.UIHomeCorreUser;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UICorreUserImpl extends UICorreUser{
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeCorreUser uiHomeCorreUser;   
    
    public UICorreUserImpl(UIHomeCorreUser uiHomeCorreUser){
        this.uiHomeCorreUser=uiHomeCorreUser;
        loadTable();
    }
    
    @Override
    public void loadTable() {
        popup.showPopup();
        ContextMantenimientoUsuarioCorrelativo context = FACTORY.contextMantenimientoUsuarioCorrelativo();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<UsuarioCorrelativoProxy>> request = context.listar(keyPublic);
        request.fire(new Receiver<List<UsuarioCorrelativoProxy>>() {

            @Override
            public void onSuccess(List<UsuarioCorrelativoProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);    
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
        // TODO Auto-generated method stub
        uiHomeCorreUser.getContainer().showWidget(1);
        uiHomeCorreUser.getUiMantCorreUser().setModo(UIMantenimiento.MODOINSERTAR);
        uiHomeCorreUser.getUiMantCorreUser().setBean(null);
        //uiHomeCorreUser.getUiMantCorreUser().loadSucursal();
        uiHomeCorreUser.getUiMantCorreUser().loadFields();
        uiHomeCorreUser.getUiMantCorreUser().cleanForm();
        uiHomeCorreUser.getUiMantCorreUser().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        UsuarioCorrelativoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null && bean.getEstado().equalsIgnoreCase("A")){        
        uiHomeCorreUser.getContainer().showWidget(1);        
        uiHomeCorreUser.getUiMantCorreUser().setModo(UIMantenimiento.MODOUPDATE);        
        uiHomeCorreUser.getUiMantCorreUser().setBean(bean);       
        uiHomeCorreUser.getUiMantCorreUser().loadSucursal();        
        uiHomeCorreUser.getUiMantCorreUser().loadFields();                
        uiHomeCorreUser.getUiMantCorreUser().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla\ncon estado activo");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla</br>con estado activo");
            not.showPopup();
        }
    }
    
    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        UsuarioCorrelativoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null && bean.getEstado().equalsIgnoreCase("A")){        
        uiHomeCorreUser.getContainer().showWidget(1);        
        uiHomeCorreUser.getUiMantCorreUser().setModo(UIMantenimiento.MODODELETE);        
        uiHomeCorreUser.getUiMantCorreUser().setBean(bean);       
        uiHomeCorreUser.getUiMantCorreUser().loadSucursal();        
        uiHomeCorreUser.getUiMantCorreUser().loadFields();                
        uiHomeCorreUser.getUiMantCorreUser().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla\ncon estado activo");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla</br>con estado activo");
            not.showPopup();
        }
    }
    
    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        UsuarioCorrelativoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){        
        uiHomeCorreUser.getContainer().showWidget(1);        
        uiHomeCorreUser.getUiMantCorreUser().setModo(UIMantenimiento.MODODETALLE);        
        uiHomeCorreUser.getUiMantCorreUser().setBean(bean);       
        uiHomeCorreUser.getUiMantCorreUser().loadSucursal();        
        uiHomeCorreUser.getUiMantCorreUser().loadFields();                
        uiHomeCorreUser.getUiMantCorreUser().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    @Override
    public void showUiDesactivar() {
        UsuarioCorrelativoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null && bean.getEstado().equalsIgnoreCase("A")){        
        uiHomeCorreUser.getContainer().showWidget(1);        
        uiHomeCorreUser.getUiMantCorreUser().setModo("DESACTIVAR");        
        uiHomeCorreUser.getUiMantCorreUser().setBean(bean);       
        uiHomeCorreUser.getUiMantCorreUser().loadSucursal();        
        uiHomeCorreUser.getUiMantCorreUser().loadFields();                
        uiHomeCorreUser.getUiMantCorreUser().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla\ncon estado activo");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla</br>con estado activo");
            not.showPopup();
        }
    }
    
    @Override
    public void exportarData() {          
        int row=0;
        List<UsuarioCorrelativoProxy> lista;
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
        flex.setText(0, 0, "COD");
        flex.setText(0, 1, "DOCUMENTO");
        flex.setText(0, 2, "SERIE");
        flex.setText(0, 3, "USUARIO");
        flex.setText(0, 4, "TRABAJADOR");        
        flex.setText(0, 5, "PTO. EMISION");        
        flex.setText(0, 6, "TIENDA");        
        flex.setText(0, 7, "SUCURSAL");        
        flex.setText(0, 8, "EST.");        
        flex.setText(0, 9, "FECHA INI");        
        flex.setText(0, 10, "FECHA FIN");                
        for(int j=0;j<11;j++){
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");            
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }        
        int fila=1;
        for(int i=0;i<row;i++){           
            UsuarioCorrelativoProxy bean=lista.get(i);
            flex.setText(fila, 0, bean.getId().toString());
            flex.setText(fila, 1, bean.getNomDocumento());
            flex.setText(fila, 2, bean.getSerie());
            flex.setText(fila, 3, bean.getNomAcceso());
            flex.setText(fila, 4, bean.getNomUsuario());
            flex.setText(fila, 5, bean.getNomPtoEmision());            
            flex.setText(fila, 6, bean.getNomTienda());            
            flex.setText(fila, 7, bean.getNomSucursal());            
            flex.setText(fila, 8, bean.getEstado());            
            flex.setText(fila, 9, dateFormat.format(bean.getFechaIni()));            
            flex.setText(fila, 10, bean.getFechaFin()!=null?dateFormat.format(bean.getFechaFin()):"");                        
            fila=fila+1;
        }
        TableToExcel.save(flex, "UserCorre"+(new Date()).getTime());
    }
}
