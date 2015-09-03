/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantclientecall;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.ClienteCallCenterProxy;
import com.webgocommerce.client.requestfactory.ContextGestionClienteCall;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomeclientecall.UIHomeClienteCall;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public class UIMantClienteCallImpl extends UIMantClienteCall {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeClienteCall uiHomeClienteCall;

    public UIMantClienteCallImpl(UIHomeClienteCall uiHomeClienteCall) {
        this.uiHomeClienteCall = uiHomeClienteCall;
    }

    @Override
    public void processInsertar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextGestionClienteCall context = FACTORY.contextGestionClienteCall();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            ClienteCallCenterProxy bean = context.create(ClienteCallCenterProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setDni(txtDni.getText().toUpperCase());
            bean.setNombres(txtNombres.getText().toUpperCase());
            bean.setApellidos(txtApellidos.getText().toUpperCase());
            bean.setObservacion(txtObservaciones.getText());
            bean.setFecha(new Date());
            if (btnAceptado.isDown()) {
                bean.setEstado("ACEPTADO");
            } else if (btnRechazado.isDown()) {
                bean.setEstado("RECHAZADO");
            }
            bean.setIdUsuario(UISesion.beanUsuario.getId());
            bean.setOperacion("I");
            Request<String> request = context.insertClienteCallCenter(bean, keyPublic);
            request.fire(new Receiver<String>() {

                @Override
                public void onSuccess(String response) {
                    cleanForm();
                    //Window.alert("Insertado correctamente");
                    Notification not = new Notification(Notification.INFORMATION, response);
                    not.showPopup();
                }
            });
        }
    }

    @Override
    public void processActualizar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextGestionClienteCall context = FACTORY.contextGestionClienteCall();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            ClienteCallCenterProxy bean = context.create(ClienteCallCenterProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("A");
            bean.setId(this.bean.getId());
            bean.setDni(txtDni.getText().toUpperCase());
            bean.setNombres(txtNombres.getText().toUpperCase());
            bean.setApellidos(txtApellidos.getText().toUpperCase());
            bean.setObservacion(txtObservaciones.getText());
            bean.setFecha(new Date());
            if (btnAceptado.isDown()) {
                bean.setEstado("ACEPTADO");
            } else if (btnRechazado.isDown()) {
                bean.setEstado("RECHAZADO");
            }
            bean.setIdUsuario(UISesion.beanUsuario.getId());
            Request<Boolean> request = context.updateClienteCallCenter(bean, keyPublic);
            request.fire(new Receiver<Boolean>() {

                @Override
                public void onSuccess(Boolean response) {
                    //Window.alert("Actualizado correctamente");
                    Notification not = new Notification(Notification.INFORMATION, "Actualizado correctamente");
                    not.showPopup();
                    goToUIClienteCall();
                }
            });
        }
    }

    @Override
    public void processEliminar() {
        Date fecha = new Date();
        ContextGestionClienteCall context = FACTORY.contextGestionClienteCall();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        ClienteCallCenterProxy bean = context.create(ClienteCallCenterProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setId(this.bean.getId());
        Request<Boolean> request = context.deleteClienteCallCenterBD(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                //Window.alert("Eliminado correctamente");
                Notification not = new Notification(Notification.INFORMATION, "Eliminado correctamente");
                not.showPopup();
                goToUIClienteCall();
            }
        });
    }

    @Override
    public void goToBack() {
        uiHomeClienteCall.getContainer().showWidget(0);
        uiHomeClienteCall.getUiClienteCall().loadTable();
        cleanForm();
    }

    @Override
    public void goToUIClienteCall() {
        cleanForm();
        uiHomeClienteCall.getContainer().showWidget(0);
        uiHomeClienteCall.getUiClienteCall().loadTable();
    }

}
