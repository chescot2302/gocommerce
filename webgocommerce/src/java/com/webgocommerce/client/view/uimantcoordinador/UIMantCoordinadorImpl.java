/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantcoordinador;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.CoordinadorProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoCoordinador;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomecoordinador.UIHomeCoordinador;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public class UIMantCoordinadorImpl extends UIMantCoordinador {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeCoordinador uiHomeCoordinador;

    public UIMantCoordinadorImpl(UIHomeCoordinador uiHomeCoordinador) {
        this.uiHomeCoordinador = uiHomeCoordinador;
    }    


    @Override
    public void processInsertar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoCoordinador context = FACTORY.contextMantenimientoCoordinador();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            CoordinadorProxy bean = context.create(CoordinadorProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("I");
            bean.setDni(txtDni.getText().toUpperCase());
            bean.setNombres(txtNombres.getText().toUpperCase());
            bean.setApellidos(txtApellidos.getText().toUpperCase());
            bean.setEstado(1);
            bean.setCorreo(txtCorreo.getText());
            bean.setCelular(txtCelular.getText());
            bean.setFechaInc(dtFechaIncorporacion.getValue());
            Request<String> request = context.insertarCoordinador(bean,keyPublic);
            request.fire(new Receiver<String>() {

                @Override
                public void onSuccess(String response) {
                    cleanForm();
                    //Window.alert("Insertado correctamente");
                    Notification not=new Notification(Notification.INFORMATION,response);
                    not.showPopup();
                }
            });
        }
    }

    @Override
    public void processActualizar() {
        if (isValidData()) {
        Date fecha = new Date();
        ContextMantenimientoCoordinador context = FACTORY.contextMantenimientoCoordinador();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        CoordinadorProxy bean = context.create(CoordinadorProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setNombres(txtNombres.getText().toUpperCase());
        bean.setApellidos(txtApellidos.getText().toUpperCase());
        bean.setId(this.bean.getId());
        bean.setEstado(1);
        bean.setCorreo(txtCorreo.getText());
        bean.setCelular(txtCelular.getText());        
        Request<String> request = context.actualizarCoordinador(keyPublic,bean);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                //Window.alert("Actualizado correctamente");
                Notification not=new Notification(Notification.INFORMATION,response);
                not.showPopup();
                goToUICoordinador();
            }
        });
        }
    }

    @Override
    public void processEliminar() {
        Date fecha = new Date();
        ContextMantenimientoCoordinador context = FACTORY.contextMantenimientoCoordinador();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        CoordinadorProxy bean = context.create(CoordinadorProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setId(this.bean.getId());
        Request<String> request = context.eliminarCoordinador(keyPublic,bean);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                //Window.alert("Eliminado correctamente");
                Notification not=new Notification(Notification.INFORMATION,response);
                not.showPopup();
                goToUICoordinador();
            }
        });
    }

    @Override
    public void goToBack() {
        uiHomeCoordinador.getContainer().showWidget(0);
        uiHomeCoordinador.getUiCoordinador().loadTable();
    }

    @Override
    public void goToUICoordinador() {
        cleanForm();
        uiHomeCoordinador.getContainer().showWidget(0);
        uiHomeCoordinador.getUiCoordinador().loadTable();
    }

}
