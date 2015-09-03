/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uilistaprecio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.ListaPrecioProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.requestfactory.ContextMantenimientoListaPrecio;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.view.grid.GridPrecioItem;
import com.webgocommerce.client.view.uihomelistaprecio.UIHomeListaPrecio;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIListaPrecioImpl extends UIListaPrecio{
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeListaPrecio uiHomeListaPrecio;


    public UIListaPrecioImpl(UIHomeListaPrecio uiHomeListaPrecio) {
        this.uiHomeListaPrecio=uiHomeListaPrecio;
        loadTable();
    }

    @Override
    public void loadTable() {
        popup.showPopup();
        ContextMantenimientoListaPrecio context = FACTORY.contextMantenimientoListaPrecio();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<ListaPrecioProxy>> request = context.listar(keyPublic).with("beanCategoriaLista");;
        request.fire(new Receiver<List<ListaPrecioProxy>>() {

            @Override
            public void onSuccess(List<ListaPrecioProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstBdEmpresa.setData(grid.getData());
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
        uiHomeListaPrecio.getContainer().showWidget(1);
        uiHomeListaPrecio.getUiMantListaPrecio().setModo(UIMantenimiento.MODOINSERTAR);
        uiHomeListaPrecio.getUiMantListaPrecio().setBean(null);        
        uiHomeListaPrecio.getUiMantListaPrecio().loadFields();
        uiHomeListaPrecio.getUiMantListaPrecio().cleanForm();
        uiHomeListaPrecio.getUiMantListaPrecio().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        ListaPrecioProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null && bean.getEstadoActiva().equalsIgnoreCase("A")){
        uiHomeListaPrecio.getContainer().showWidget(1);
        uiHomeListaPrecio.getUiMantListaPrecio().setModo(UIMantenimiento.MODOUPDATE);
        uiHomeListaPrecio.getUiMantListaPrecio().setBean(bean);
        uiHomeListaPrecio.getUiMantListaPrecio().loadFields();
        uiHomeListaPrecio.getUiMantListaPrecio().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla\ncon estado activo");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla</br>con estado activo");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        ListaPrecioProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeListaPrecio.getContainer().showWidget(1);
        uiHomeListaPrecio.getUiMantListaPrecio().setModo(UIMantenimiento.MODODELETE);
        uiHomeListaPrecio.getUiMantListaPrecio().setBean(bean);
        uiHomeListaPrecio.getUiMantListaPrecio().loadFields();
        uiHomeListaPrecio.getUiMantListaPrecio().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        ListaPrecioProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeListaPrecio.getContainer().showWidget(1);
        uiHomeListaPrecio.getUiMantListaPrecio().setModo(UIMantenimiento.MODODETALLE);
        uiHomeListaPrecio.getUiMantListaPrecio().setBean(bean);
        uiHomeListaPrecio.getUiMantListaPrecio().loadFields();
        uiHomeListaPrecio.getUiMantListaPrecio().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    @Override
    public void showUIDesactivar() {
        ListaPrecioProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null && bean.getEstadoActiva().equalsIgnoreCase("A")){
        uiHomeListaPrecio.getContainer().showWidget(1);
        uiHomeListaPrecio.getUiMantListaPrecio().setModo("DESACTIVAR");
        uiHomeListaPrecio.getUiMantListaPrecio().setBean(bean);
        uiHomeListaPrecio.getUiMantListaPrecio().loadFields();
        uiHomeListaPrecio.getUiMantListaPrecio().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla\ncon estado activo");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla</br>con estado activo");
            not.showPopup();
        }
    }
    
    @Override
    public void showUIEditListaPrecio() {
        ListaPrecioProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeListaPrecio.getContainer().showWidget(2);                
        uiHomeListaPrecio.getUiEditListaPrecio().modoGrid(bean.getEstadoActiva().equalsIgnoreCase("D")?GridPrecioItem.L:GridPrecioItem.E);
        uiHomeListaPrecio.getUiEditListaPrecio().setBeanListaPrecio(bean);   
        uiHomeListaPrecio.getUiEditListaPrecio().loadFields();
        uiHomeListaPrecio.getUiEditListaPrecio().loadTable();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }        
    }
    
    @Override
    public void exportarData() {  
        int row=0;
        List<ListaPrecioProxy> lista;
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
        flex.setText(0, 2, "CATEGORIA");           
        flex.setText(0, 3, "DESCRIPCION");           
        flex.setText(0, 4, "CONDICION");           
        flex.setText(0, 5, "PAGO");           
        flex.setText(0, 6, "MESES");           
        flex.setText(0, 7, "FECHA INI");           
        flex.setText(0, 8, "FECHA FIN");           
        flex.setText(0, 9, "ESTADO");           
        for(int j=0;j<10;j++){
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");            
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }        
        int fila=1;
        for(int i=0;i<row;i++){           
            ListaPrecioProxy bean=lista.get(i);
            flex.setText(fila, 0, bean.getId().toString());            
            flex.setText(fila, 1, bean.getCanal());            
            flex.setText(fila, 2, bean.getBeanCategoriaLista().getDescripcion());                       
            flex.setText(fila, 3, bean.getDescripcion());                       
            flex.setText(fila, 4, bean.getCondicion());                       
            flex.setText(fila, 5, bean.getPagoMensual().toString());                       
            flex.setText(fila, 6, bean.getVigencia().toString());
            flex.setText(fila, 7, bean.getFechaIni()!=null?dateFormat.format(bean.getFechaIni()):"");
            flex.setText(fila, 8, bean.getFechaFin()!=null?dateFormat.format(bean.getFechaFin()):"");
            flex.setText(fila, 9, bean.getEstadoActiva());
            fila=fila+1;
        }
        TableToExcel.save(flex, "listaprecio"+(new Date()).getTime());
    }
    
}
