/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uiestproy;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.EstProyProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoEstProy;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uidocventa.UIRegVentaCeImpl;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIEstProyImpl extends UIEstProy{ 
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();    
    private UIRegVentaCeImpl ui;
            
    public UIEstProyImpl(UIRegVentaCeImpl ui){
        this.ui=ui;
    }

    @Override
    public void loadTable() {
        ContextMantenimientoEstProy context = FACTORY.contextMantenimientoEstProy();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<EstProyProxy>> request = context.listar(keyPublic,bean.getTipoDoc(),bean.getCorrelativo());
        request.fire(new Receiver<List<EstProyProxy>>() {

            @Override
            public void onSuccess(List<EstProyProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
            }
            
            @Override
                public void onFailure(ServerFailure error) {                    
                    Notification not=new Notification(Notification.WARNING,error.getMessage());
                    not.showPopup();
                }
        });
    }
    
    @Override
    public void processChangeState() {
        Date fecha = new Date();
            ContextMantenimientoEstProy context = FACTORY.contextMantenimientoEstProy();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            EstProyProxy beanUpdate = context.create(EstProyProxy.class);
            beanUpdate.setVersion(fecha.getTime());
            beanUpdate.setOperacion("A");            
            beanUpdate.setTipoDoc(bean.getTipoDoc());
            beanUpdate.setCorrelativo(bean.getCorrelativo());
            beanUpdate.setEstadoActual(lstEstadoActual.getItemText(lstEstadoActual.getSelectedIndex()));
            beanUpdate.setObservacion(txtComentario.getText());
            Request<String> request = context.actualizarEstProy(keyPublic, beanUpdate);
            request.fire(new Receiver<String>() {

                @Override
                public void onSuccess(String response) {
                    loadTable();
                    txtComentario.setText(null);
                    Notification not=new Notification(Notification.INFORMATION,response);
                    not.showPopup();                    
                }
            });                                   
    }
    
    @Override
    public void loadTableFather() {
        this.ui.loadTable();
    }
    
}
