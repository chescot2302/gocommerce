/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantparam;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.ParamProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoParam;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomeparam.UIHomeParam;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public class UIMantParamImpl extends UIMantParam {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeParam uiHomeParam;

    public UIMantParamImpl(UIHomeParam uiHomeParam) {
        this.uiHomeParam = uiHomeParam;
    }

    @Override
    public void processInsertar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoParam context = FACTORY.contextMantenimientoParam();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            ParamProxy bean = context.create(ParamProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("I");
            bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
            bean.setDescripcion(txtDescripcion.getText().toUpperCase());
            bean.setValor(txtValor.getText().toUpperCase());
            Request<Boolean> request = context.insertParam(bean, keyPublic);
            request.fire(new Receiver<Boolean>() {

                @Override
                public void onSuccess(Boolean response) {
                    cleanForm();
                    //Window.alert("Insertado correctamente");
                    Notification not = new Notification(Notification.INFORMATION, "Insertado correctamente");
                    not.showPopup();
                }
            });
        }
    }

    @Override
    public void processActualizar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoParam context = FACTORY.contextMantenimientoParam();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            ParamProxy bean = context.create(ParamProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("A");
            bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
            bean.setDescripcion(txtDescripcion.getText().toUpperCase());
            bean.setValor(txtValor.getText().toUpperCase());
            bean.setIdParam(this.bean.getIdParam());
            Request<Boolean> request = context.updateParam(bean, keyPublic);
            request.fire(new Receiver<Boolean>() {

                @Override
                public void onSuccess(Boolean response) {
                    //Window.alert("Actualizado correctamente");
                    Notification not = new Notification(Notification.INFORMATION, "Actualizado correctamente");
                    not.showPopup();
                    goToUIParam();
                }
            });
        }
    }

    @Override
    public void processEliminar() {
        Date fecha = new Date();
        ContextMantenimientoParam context = FACTORY.contextMantenimientoParam();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        ParamProxy bean = context.create(ParamProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setIdParam(this.bean.getIdParam());
        Request<Boolean> request = context.deleteParamBD(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                //Window.alert("Eliminado correctamente");
                Notification not = new Notification(Notification.INFORMATION, "Eliminado correctamente");
                not.showPopup();
                goToUIParam();
            }
        });
    }

    @Override
    public void goToBack() {
        uiHomeParam.getContainer().showWidget(0);
        uiHomeParam.getUiParam().loadTable();
    }

    @Override
    public void goToUIParam() {
        cleanForm();
        uiHomeParam.getContainer().showWidget(0);
        uiHomeParam.getUiParam().loadTable();
    }

}
