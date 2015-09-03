/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uiaddconsultor;

import com.google.gwt.core.client.GWT;
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
import com.webgocommerce.client.view.uihomemesa.UIHomeMesa;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
public class UIAddConsultorImpl extends UIAddConsultor {
    PopupProgress popup=new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeMesa uiHomeMesa;

    public UIAddConsultorImpl(UIHomeMesa uiHomeMesa) {
        this.uiHomeMesa = uiHomeMesa;
    }    

    @Override
    public void loadTable() {        
        popup.showPopup();
        ContextMantenimientoSuperVen context = FACTORY.contextMantenimientoSuperVen();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<SuperVenProxy>> request = context.consultorSinMesa(keyPublic);
        request.fire(new Receiver<List<SuperVenProxy>>() {

            @Override
            public void onSuccess(List<SuperVenProxy> response) {                
                txtBuscar.setText(null);
                grid.getDataProvider().resetFilter();
                grid.setData(response);
                grid.getSelectionModel().clear();
                grid.checkAll.setIsSelected(Boolean.FALSE);
                grid.actualizarGrid();
                popup.hidePopup();
            }

            @Override
            public void onFailure(ServerFailure error) {                
                    popup.hidePopup(); 
                    Notification not=new Notification(Notification.WARNING,error.getMessage());
                    not.showPopup();
            }
        });
    }

    @Override
    public void goToUIEditConsultor() {
        uiHomeMesa.getContainer().showWidget(2);
        uiHomeMesa.getUiEditConsultor().loadTable();
    }

    @Override
    public void addItemLista() {        
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
            bean.setIdMesa(beanMesa.getIdMesa());
            bean.setVersion(beanEdit.getVersion());
            lista.add(bean);
        }
        Request<Boolean> request = context.insertarSuperVen(keyPublic, lista);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                if (response) {
                    popup.hidePopup();                    
                    Notification not=new Notification(Notification.INFORMATION,"Consultores agregados a lista");
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
    }
}
