/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantcategorialista;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.CategoriaListaProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoCategoriaLista;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomecategorialista.UIHomeCategoriaLista;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public class UIMantCategoriaListaImpl extends UIMantCategoriaLista {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeCategoriaLista uiHomeCategoriaLista;

    public UIMantCategoriaListaImpl(UIHomeCategoriaLista uiHomeCategoriaLista) {
        this.uiHomeCategoriaLista = uiHomeCategoriaLista;
    }

    @Override
    public void processInsertar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoCategoriaLista context = FACTORY.contextMantenimientoCategoriaLista();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            CategoriaListaProxy bean = context.create(CategoriaListaProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("I");
            bean.setDescripcion(txtDescripcion.getText().toUpperCase());
            Request<Boolean> request = context.insertarCategoriaLista(bean, keyPublic);
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
        ContextMantenimientoCategoriaLista context = FACTORY.contextMantenimientoCategoriaLista();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        CategoriaListaProxy bean = context.create(CategoriaListaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setId(this.bean.getId());
        Request<Boolean> request = context.actualizarCategoriaLista(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                //Window.alert("Actualizado correctamente");
                Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                not.showPopup();
                goToUICategoriaLista();
            }
        });
        }
    }

    @Override
    public void processEliminar() {
        Date fecha = new Date();
        ContextMantenimientoCategoriaLista context = FACTORY.contextMantenimientoCategoriaLista();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        CategoriaListaProxy bean = context.create(CategoriaListaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setId(this.bean.getId());
        Request<Boolean> request = context.eliminarCategoriaLista(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                //Window.alert("Eliminado correctamente");
                Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                not.showPopup();
                goToUICategoriaLista();
            }
        });
    }

    @Override
    public void goToBack() {
        uiHomeCategoriaLista.getContainer().showWidget(0);
        uiHomeCategoriaLista.getUiCategoriaLista().loadTable();
    }

    @Override
    public void goToUICategoriaLista() {
        cleanForm();
        uiHomeCategoriaLista.getContainer().showWidget(0);
        uiHomeCategoriaLista.getUiCategoriaLista().loadTable();
    }

}
