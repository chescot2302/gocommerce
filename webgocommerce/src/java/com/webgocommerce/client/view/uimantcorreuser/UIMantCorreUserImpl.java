/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantcorreuser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.CorrelativoProxy;
import com.webgocommerce.client.beanproxy.PuntoEmisionProxy;
import com.webgocommerce.client.beanproxy.SucursalProxy;
import com.webgocommerce.client.beanproxy.TiendaProxy;
import com.webgocommerce.client.beanproxy.UsuarioCorrelativoProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoCorrelativo;
import com.webgocommerce.client.requestfactory.ContextMantenimientoPtoEmision;
import com.webgocommerce.client.requestfactory.ContextMantenimientoSucursal;
import com.webgocommerce.client.requestfactory.ContextMantenimientoTienda;
import com.webgocommerce.client.requestfactory.ContextMantenimientoUsuarioCorrelativo;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomecorreuser.UIHomeCorreUser;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIMantCorreUserImpl extends UIMantCorreUser {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeCorreUser uiHomeCorreUser;

    public UIMantCorreUserImpl(UIHomeCorreUser uiHomeCorreUser) {
        this.uiHomeCorreUser = uiHomeCorreUser;
        loadSucursal();
    }

    @Override
    public void loadSucursal() {
        ContextMantenimientoSucursal context = FACTORY.contextMantenimientoSucursal();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<SucursalProxy>> request = context.listar(keyPublic);
        request.fire(new Receiver<List<SucursalProxy>>() {

            @Override
            public void onSuccess(List<SucursalProxy> response) {
                txtSerie.setText(null);
                if (!response.isEmpty()) {
                    lstSucursal.setData(response);
                    if (bean != null) {
                        lstSucursal.setSelectedItem(bean.getNomSucursal());
                    }
                    loadTienda();
                } else {
                    lstSucursal.clear();
                    lstTienda.clear();
                    lstPtoEmision.clear();
                    lstCorrelativo.clear();
                }
            }
        });
    }

    @Override
    public void loadTienda() {
        ContextMantenimientoTienda context = FACTORY.contextMantenimientoTienda();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<TiendaProxy>> request = context.listarTiendaxSucursal(keyPublic, lstSucursal.getElement(lstSucursal.getSelectedIndex()).getId());
        request.fire(new Receiver<List<TiendaProxy>>() {

            @Override
            public void onSuccess(List<TiendaProxy> response) {
                txtSerie.setText(null);
                if (!response.isEmpty()) {
                    lstTienda.setData(response);
                    if (bean != null) {
                        lstTienda.setSelectedItem(bean.getNomTienda());
                    }
                    loadPuntoEmision();
                } else {
                    lstTienda.clear();
                    lstPtoEmision.clear();
                    lstCorrelativo.clear();
                }
            }
        });
    }

    @Override
    public void loadPuntoEmision() {
        ContextMantenimientoPtoEmision context = FACTORY.contextMantenimientoPtoEmision();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<PuntoEmisionProxy>> request = context.listarPtoEmisionxTienda(keyPublic, lstTienda.getElement(lstTienda.getSelectedIndex()).getId());
        request.fire(new Receiver<List<PuntoEmisionProxy>>() {

            @Override
            public void onSuccess(List<PuntoEmisionProxy> response) {
                txtSerie.setText(null);
                if (!response.isEmpty()) {
                    lstPtoEmision.setData(response);
                    if (bean != null) {
                        lstPtoEmision.setSelectedItem(bean.getNomPtoEmision());
                    }
                    loadCorrelativo();
                } else {
                    lstPtoEmision.clear();
                    lstCorrelativo.clear();
                }
            }
        });
    }

    @Override
    public void loadCorrelativo() {
        ContextMantenimientoCorrelativo context = FACTORY.contextMantenimientoCorrelativo();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<CorrelativoProxy>> request = context.listarCorrelativoxPtoEmision(keyPublic, lstPtoEmision.getElement(lstPtoEmision.getSelectedIndex()).getId());
        request.fire(new Receiver<List<CorrelativoProxy>>() {

            @Override
            public void onSuccess(List<CorrelativoProxy> response) {
                txtSerie.setText(null);
                if (!response.isEmpty()) {
                    lstCorrelativo.setData(response);
                    if (bean != null) {
                        lstCorrelativo.setSelectedItem(bean.getNomDocumento());
                    }
                    txtSerie.setText(lstCorrelativo.getSelectedItem().getNroInicio());
                }
            }
        });
    }

    @Override
    public void goToBack() {
        uiHomeCorreUser.getContainer().showWidget(0);
        uiHomeCorreUser.getUiCorreUser().loadTable();
    }

    @Override
    public void goToUICorreUser() {
        cleanForm();
        uiHomeCorreUser.getContainer().showWidget(0);
        uiHomeCorreUser.getUiCorreUser().loadTable();
    }

    @Override
    public void gotoUISelectUser() {
        uiHomeCorreUser.getContainer().showWidget(2);
        uiHomeCorreUser.getUiSelectUser().setIdPuntoEmision(lstPtoEmision.getSelectedItem().getId());
        uiHomeCorreUser.getUiSelectUser().setIdDocumento(lstCorrelativo.getSelectedItem().getIdDocumento());
        uiHomeCorreUser.getUiSelectUser().loadTable();

    }

    @Override
    public void processInsertar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoUsuarioCorrelativo context = FACTORY.contextMantenimientoUsuarioCorrelativo();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            UsuarioCorrelativoProxy bean = context.create(UsuarioCorrelativoProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setIdUsuario(beanUsuario.getId());
            bean.setIdBdUsuario(beanUsuario.getIdBdUsuario());
            bean.setIdDocumento(lstCorrelativo.getSelectedItem().getIdDocumento());
            bean.setIdPuntoEmision(lstPtoEmision.getSelectedItem().getId());
            bean.setSerie(txtSerie.getText());
            bean.setEstado("A");
            bean.setFechaIni(fecha);
            bean.setOperacion("I");
            Request<String> request = context.insertarUsuarioCorrelativo(keyPublic, bean);
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
        ContextMantenimientoUsuarioCorrelativo context = FACTORY.contextMantenimientoUsuarioCorrelativo();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        UsuarioCorrelativoProxy bean = context.create(UsuarioCorrelativoProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setId(this.bean.getId());
        bean.setIdUsuario(beanUsuario.getId());
        bean.setIdBdUsuario(beanUsuario.getIdBdUsuario());
        bean.setIdDocumento(lstCorrelativo.getSelectedItem().getIdDocumento());
        bean.setIdPuntoEmision(lstPtoEmision.getSelectedItem().getId());
        bean.setSerie(txtSerie.getText());
        bean.setOperacion("A");
        Request<String> request = context.actualizarUsuarioCorrelativo(keyPublic, bean);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                cleanForm();
                //Window.alert(response);
                Notification not=new Notification(Notification.INFORMATION,response);
                not.showPopup();
                goToUICorreUser();
            }
        });
        }
    }

    @Override
    public void processEliminar() {
        Date fecha = new Date();
        ContextMantenimientoUsuarioCorrelativo context = FACTORY.contextMantenimientoUsuarioCorrelativo();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        UsuarioCorrelativoProxy bean = context.create(UsuarioCorrelativoProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setId(this.bean.getId());
        bean.setOperacion("E");
        Request<String> request = context.eliminarUsuarioCorrelativo(keyPublic, bean);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                cleanForm();
                //Window.alert(response);
                Notification not=new Notification(Notification.INFORMATION,response);
                not.showPopup();
                goToUICorreUser();
            }
        });
    }

    @Override
    public void processDesactivar() {
        Date fecha = new Date();
        ContextMantenimientoUsuarioCorrelativo context = FACTORY.contextMantenimientoUsuarioCorrelativo();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        UsuarioCorrelativoProxy bean = context.create(UsuarioCorrelativoProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setId(this.bean.getId());
        bean.setOperacion("A");
        Request<String> request = context.desactivarUsuarioCorrelativo(keyPublic, bean);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                cleanForm();
                //Window.alert(response);
                Notification not=new Notification(Notification.INFORMATION,response);
                not.showPopup();
                goToUICorreUser();
            }
        });
    }

}
