/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uibdempresa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.BdEmpresaProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.requestfactory.ContextMantenimientoBdEmpresa;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.service.ServiceExportar;
import com.webgocommerce.client.service.ServiceExportarAsync;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.view.uihomebdempresa.UIHomeBdEmpresa;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIBdEmpresaImpl extends UIBdEmpresa {
    PopupProgress popup = new PopupProgress();
    private final ServiceExportarAsync servicioExportar = GWT.create(ServiceExportar.class);
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeBdEmpresa uiHomeBdEmpresa;

    public UIBdEmpresaImpl(UIHomeBdEmpresa uiHomeBdEmpresa) {
        this.uiHomeBdEmpresa=uiHomeBdEmpresa;
        loadTable();
    }

    @Override
    public void loadTable() {
        popup.showPopup();
        ContextMantenimientoBdEmpresa context = FACTORY.contextMantenimientoBdEmpresa();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<BdEmpresaProxy>> request = context.listar(keyPublic);
        request.fire(new Receiver<List<BdEmpresaProxy>>() {

            @Override
            public void onSuccess(List<BdEmpresaProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                popup.hidePopup();
                //lstBdEmpresa.setData(grid.getData());
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
        uiHomeBdEmpresa.getContainer().showWidget(1);
        uiHomeBdEmpresa.getUiMantBdEmpresa().setModo(UIMantenimiento.MODOINSERTAR);
        uiHomeBdEmpresa.getUiMantBdEmpresa().setBean(null);        
        uiHomeBdEmpresa.getUiMantBdEmpresa().cleanForm();
        uiHomeBdEmpresa.getUiMantBdEmpresa().loadFields();        
        uiHomeBdEmpresa.getUiMantBdEmpresa().scrollPanel.refresh();
    }   
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        BdEmpresaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeBdEmpresa.getContainer().showWidget(1);
        uiHomeBdEmpresa.getUiMantBdEmpresa().setModo(UIMantenimiento.MODOUPDATE);
        uiHomeBdEmpresa.getUiMantBdEmpresa().setBean(bean);
        uiHomeBdEmpresa.getUiMantBdEmpresa().loadFields();
        uiHomeBdEmpresa.getUiMantBdEmpresa().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        BdEmpresaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeBdEmpresa.getContainer().showWidget(1);
        uiHomeBdEmpresa.getUiMantBdEmpresa().setModo(UIMantenimiento.MODODELETE);
        uiHomeBdEmpresa.getUiMantBdEmpresa().setBean(bean);
        uiHomeBdEmpresa.getUiMantBdEmpresa().loadFields();
        uiHomeBdEmpresa.getUiMantBdEmpresa().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        BdEmpresaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeBdEmpresa.getContainer().showWidget(1);
        uiHomeBdEmpresa.getUiMantBdEmpresa().setModo(UIMantenimiento.MODODETALLE);
        uiHomeBdEmpresa.getUiMantBdEmpresa().setBean(bean);
        uiHomeBdEmpresa.getUiMantBdEmpresa().loadFields();
        uiHomeBdEmpresa.getUiMantBdEmpresa().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    
    
    @Override
    public void exportarData() {
        int row=0;
        List<BdEmpresaProxy> lista;
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
        flex.setText(0, 1, "EMPRESA");           
        flex.setText(0, 2, "HOST");           
        flex.setText(0, 3, "PUERTO");           
        flex.setText(0, 4, "ESQUEMA");           
        flex.setText(0, 5, "USUSARIO BD");           
        flex.setText(0, 6, "CLAVE BD");           
        flex.setText(0, 7, "ESTADO");           
        for(int j=0;j<8;j++){
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");            
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }        
        int fila=1;
        for(int i=0;i<row;i++){           
            BdEmpresaProxy bean=lista.get(i);
            flex.setText(fila, 0, bean.getIdBdEmpresa().toString());            
            flex.setText(fila, 1, bean.getNombre());
            flex.setText(fila, 2, bean.getIpHost());
            flex.setText(fila, 3, bean.getPuerto().toString());
            flex.setText(fila, 4, bean.getSchema());
            flex.setText(fila, 5, bean.getUserPrincipal());
            flex.setText(fila, 6, bean.getClavePrincipal());
            flex.setText(fila, 7, bean.getEstadoActivacion());
            fila=fila+1;
        }
        TableToExcel.save(flex, "BdEmpresa"+(new Date()).getTime());        
        /*List<BdEmpresaProxy> lista=grid.getData();
        Iterator<BdEmpresaProxy> iterador=lista.iterator();
        int contador=0;
        String id="";         
        GjsonArray array=new GjsonArray();
        while(iterador.hasNext()){
            BdEmpresaProxy bean=iterador.next();
            GjsonObject object=new GjsonObject(TypeGjson.COMPLEX,bean.getClassName());
            id=String.valueOf(contador);
            object.putElement(new GjsonKey(id,String.class.getSimpleName(),"className"), bean.getClassName());
            object.putElement(new GjsonKey(id,String.class.getSimpleName(),"clavePrincipal"), bean.getClavePrincipal());
            object.putElement(new GjsonKey(id,String.class.getSimpleName(),"estadoActivacion"), bean.getEstadoActivacion());
            object.putElement(new GjsonKey(id,String.class.getSimpleName(),"ipHost"), bean.getIpHost());
            object.putElement(new GjsonKey(id,String.class.getSimpleName(),"nombre"), bean.getNombre());
            object.putElement(new GjsonKey(id,String.class.getSimpleName(),"schema"), bean.getSchema());
            object.putElement(new GjsonKey(id,String.class.getSimpleName(),"userPrincipal"), bean.getUserPrincipal());
            object.putElement(new GjsonKey(id,Integer.class.getSimpleName(),"idBdEmpresa"), bean.getIdBdEmpresa());
            object.putElement(new GjsonKey(id,Integer.class.getSimpleName(),"puerto"), bean.getPuerto());
            object.putElement(new GjsonKey(id,Integer.class.getSimpleName(),"version"), bean.getVersion());
            object.putElement(new GjsonKey(id,String.class.getSimpleName(),"operacion"), bean.getOperacion());            
            array.putElement(object);
            contador=contador+1;                        
        }                    
        GjsonArray.buildJsonArrayFormat(array);       
        System.out.println(array.getGjsonArray());
        Window.alert(array.getGjsonArray().toString());
        servicioExportar.exportar(new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                GWT.log(caught.getMessage(), caught);
            }

            @Override
            public void onSuccess(Void result) {
               
            }
        });*/
    }
    
    

}

