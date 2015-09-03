/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantlocalidad;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.LocalidadProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoLocalidad;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomelocalidad.UIHomeLocalidad;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public class UIMantLocalidadImpl extends UIMantLocalidad {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeLocalidad uiHomeLocalidad;

    public UIMantLocalidadImpl(UIHomeLocalidad uiHomeLocalidad) {
        this.uiHomeLocalidad = uiHomeLocalidad;
    }

    @Override
    public void processInsertar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoLocalidad context = FACTORY.contextMantenimientoLocalidad();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            LocalidadProxy bean = context.create(LocalidadProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("I");
            bean.setDescripcion(txtDescripcion.getText().toUpperCase());
            Request<Boolean> request = context.insertLocalidad(bean, keyPublic);
            request.fire(new Receiver<Boolean>() {

                @Override
                public void onSuccess(Boolean response) {
                    cleanForm();
                    //Window.alert("Insertado correctamente");
                    Notification not=new Notification(Notification.INFORMATION,"Insertado correctamente");
                    not.showPopup();
                }
            });
        }
    }

    @Override
    public void processActualizar() {
        if (isValidData()) {
        Date fecha = new Date();
        ContextMantenimientoLocalidad context = FACTORY.contextMantenimientoLocalidad();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        LocalidadProxy bean = context.create(LocalidadProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setIdLocalidad(this.bean.getIdLocalidad());
        Request<Boolean> request = context.updateLocalidad(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                //Window.alert("Actualizado correctamente");
                Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                not.showPopup();
                goToUILocalidad();
            }
        });
        }
    }

    @Override
    public void processEliminar() {
        Date fecha = new Date();
        ContextMantenimientoLocalidad context = FACTORY.contextMantenimientoLocalidad();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        LocalidadProxy bean = context.create(LocalidadProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setIdLocalidad(this.bean.getIdLocalidad());
        Request<Boolean> request = context.deleteLocalidadBD(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                //Window.alert("Eliminado correctamente");
                Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                not.showPopup();
                goToUILocalidad();
            }
        });
    }

    @Override
    public void goToBack() {
        uiHomeLocalidad.getContainer().showWidget(0);
        uiHomeLocalidad.getUiLocalidad().loadTable();
    }

    @Override
    public void goToUILocalidad() {
        cleanForm();
        uiHomeLocalidad.getContainer().showWidget(0);
        uiHomeLocalidad.getUiLocalidad().loadTable();
    }

}
