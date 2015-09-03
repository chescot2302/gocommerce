/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantsucursal;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.LocalidadProxy;
import com.webgocommerce.client.beanproxy.SucursalProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoLocalidad;
import com.webgocommerce.client.requestfactory.ContextMantenimientoSucursal;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomesucursal.UIHomeSucursal;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIMantSucursalImpl extends UIMantSucursal {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeSucursal uiHomeSucursal;

    public UIMantSucursalImpl(UIHomeSucursal uiHomeSucursal) {
        this.uiHomeSucursal = uiHomeSucursal;
        loadListBox();
    }

    @Override
    public void loadListBox() {
        ContextMantenimientoLocalidad context = FACTORY.contextMantenimientoLocalidad();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<LocalidadProxy>> request = context.listarLocalidad(keyPublic);
        request.fire(new Receiver<List<LocalidadProxy>>() {

            @Override
            public void onSuccess(List<LocalidadProxy> response) {
                lstLocalidad.setData(response);
                if (bean != null && bean.getNomLocalidad() != null) {
                    lstLocalidad.setSelectedItem(bean.getNomLocalidad());
                }
            }
        });
    }

    @Override
    public void processInsertar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoSucursal context = FACTORY.contextMantenimientoSucursal();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            SucursalProxy bean = context.create(SucursalProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("I");
            bean.setIdLocalidad(lstLocalidad.getSelectedItem().getIdLocalidad());
            bean.setDescripcion(txtDescripcion.getText().toUpperCase());
            Request<String> request = context.insertarSucursal(keyPublic, bean);
            request.fire(new Receiver<String>() {

                @Override
                public void onSuccess(String response) {
                    cleanForm();
                    //Window.alert(response);
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
            ContextMantenimientoSucursal context = FACTORY.contextMantenimientoSucursal();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            SucursalProxy bean = context.create(SucursalProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("A");
            bean.setIdLocalidad(lstLocalidad.getSelectedItem().getIdLocalidad());
            bean.setDescripcion(txtDescripcion.getText().toUpperCase());
            bean.setId(this.bean.getId());
            Request<String> request = context.actualizarSucursal(keyPublic, bean);
            request.fire(new Receiver<String>() {

                @Override
                public void onSuccess(String response) {
                    //Window.alert(response);
                    Notification not=new Notification(Notification.INFORMATION,response);
                    not.showPopup();
                    goToUISucursal();
                }
            });
        }
    }

    @Override
    public void processEliminar() {
        Date fecha = new Date();
        ContextMantenimientoSucursal context = FACTORY.contextMantenimientoSucursal();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        SucursalProxy bean = context.create(SucursalProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setId(this.bean.getId());
        Request<String> request = context.eliminarSucursal(keyPublic, bean);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                //Window.alert(response);
                Notification not=new Notification(Notification.INFORMATION,response);
                not.showPopup();
                goToUISucursal();
            }
        });
    }

    @Override
    public void goToBack() {
        uiHomeSucursal.getContainer().showWidget(0);
        uiHomeSucursal.getUiSucursal().loadTable();
    }

    @Override
    public void goToUISucursal() {
        cleanForm();
        uiHomeSucursal.getContainer().showWidget(0);
        uiHomeSucursal.getUiSucursal().loadTable();
    }

}
