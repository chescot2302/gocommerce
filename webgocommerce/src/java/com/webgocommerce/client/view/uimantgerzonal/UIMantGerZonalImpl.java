/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantgerzonal;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.GerenteZonalProxy;
import com.webgocommerce.client.beanproxy.SupervisorProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoGerZonal;
import com.webgocommerce.client.requestfactory.ContextMantenimientoSupervisor;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomegerzonal.UIHomeGerZonal;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public class UIMantGerZonalImpl extends UIMantGerZonal {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeGerZonal uiHomeGerZonal;

    public UIMantGerZonalImpl(UIHomeGerZonal uiHomeGerZonal) {
        this.uiHomeGerZonal = uiHomeGerZonal;
    }    


    @Override
    public void processInsertar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoGerZonal context = FACTORY.contextMantenimientoGerZonal();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            GerenteZonalProxy bean = context.create(GerenteZonalProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("I");
            bean.setDni(txtDni.getText().toUpperCase());
            bean.setNombres(txtNombres.getText().toUpperCase());
            bean.setApellidos(txtApellidos.getText().toUpperCase());
            bean.setEstado(1);
            bean.setCorreo(txtCorreo.getText());
            bean.setCelular(txtCelular.getText());
            bean.setFechaInc(dtFechaIncorporacion.getValue());
            Request<String> request = context.insertarGerenteZonal(bean,keyPublic);
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
        ContextMantenimientoGerZonal context = FACTORY.contextMantenimientoGerZonal();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        GerenteZonalProxy bean = context.create(GerenteZonalProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setNombres(txtNombres.getText().toUpperCase());
        bean.setApellidos(txtApellidos.getText().toUpperCase());
        bean.setId(this.bean.getId());
        bean.setEstado(1);
        bean.setCorreo(txtCorreo.getText());
        bean.setCelular(txtCelular.getText());        
        Request<String> request = context.actualizarGerenteZonal(keyPublic,bean);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                //Window.alert("Actualizado correctamente");
                Notification not=new Notification(Notification.INFORMATION,response);
                not.showPopup();
                goToUIGerZonal();
            }
        });
        }
    }

    @Override
    public void processEliminar() {
        Date fecha = new Date();
        ContextMantenimientoGerZonal context = FACTORY.contextMantenimientoGerZonal();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        GerenteZonalProxy bean = context.create(GerenteZonalProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setId(this.bean.getId());
        Request<String> request = context.eliminarGerenteZonal(keyPublic,bean);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                //Window.alert("Eliminado correctamente");
                Notification not=new Notification(Notification.INFORMATION,response);
                not.showPopup();
                goToUIGerZonal();
            }
        });
    }

    @Override
    public void goToBack() {
        uiHomeGerZonal.getContainer().showWidget(0);
        uiHomeGerZonal.getUiGerZonal().loadTable();
    }

    @Override
    public void goToUIGerZonal() {
        cleanForm();
        uiHomeGerZonal.getContainer().showWidget(0);
        uiHomeGerZonal.getUiGerZonal().loadTable();
    }

}
