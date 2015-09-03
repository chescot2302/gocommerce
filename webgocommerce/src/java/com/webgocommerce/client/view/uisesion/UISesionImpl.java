/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uisesion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.InitParamProxy;
import com.webgocommerce.client.beanproxy.ParamProxy;
import com.webgocommerce.client.beanproxy.TipoCambioProxy;
import com.webgocommerce.client.model.UIScreenSesion;
import com.webgocommerce.client.requestfactory.ContextMantenimientoInitParam;
import com.webgocommerce.client.requestfactory.ContextMantenimientoParam;
import com.webgocommerce.client.requestfactory.ContextMantenimientoTipoCambio;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.service.ServiceGestionSeguridad;
import com.webgocommerce.client.service.ServiceGestionSeguridadAsync;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.UIInfoTC;
import com.webgocommerce.client.uiutil.UIInfoUbicacion;
import com.webgocommerce.client.uiutil.UIInfoUsuario;
import com.webgocommerce.client.view.uimenubar.UIMenuBarImpl;
import com.webgocommerce.server.beans.Usuario;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UISesionImpl extends UISesion {

    private final ServiceGestionSeguridadAsync servicio = GWT.create(ServiceGestionSeguridad.class);
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();

    public UISesionImpl() {
        getKeyPublic();
    }

    @Override
    public void getKeyPublic() {
        servicio.getKeyPublic(new AsyncCallback<List>() {

            @Override
            public void onSuccess(List result) {
                UISesion.keyPublic = result.get(0).toString();
                if (!result.get(1).toString().equalsIgnoreCase("superadmin")) {
                    loadInitParam();
                    loadParam();
                    loadTipoCambioNow();
                    beanUsuario = (Usuario) result.get(3);
                    loadUsuario();
                    loadUbicacion();
                    loadMenu();
                    if (result.get(1).toString().equalsIgnoreCase("admin")) {
                        //mbSystem.mbSeguridad.setVisible(true);
                    } else {
                        //mbSystem.mbSeguridad.setVisible(false);
                    }
                } else {
                    beanUsuario = (Usuario) result.get(3);
                    loadUsuario();
                    //mbSystem.mbSeguridad.setVisible(true);
                    //mbSystem.mbVentas.setVisible(false);
                    //mbSystem.mbMantenimiento.setVisible(false);
                    loadMenu();
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                //Window.alert(caught.getMessage());
                Notification not = new Notification(Notification.WARNING, caught.getMessage());
                not.showPopup();
            }
        });
    }

    @Override
    public void loadUsuario() {
        UIInfoUsuario.txtUsuario.setText(beanUsuario.getNombres());
        //Window.alert("idUsuario: "+beanUsuario.getIdBdUsuario());
    }

    @Override
    public void loadUbicacion() {
        UIInfoUbicacion.lblUbicacion.setText(beanUsuario.getNomLocalidad() + "/" + beanUsuario.getNomSucursal().trim() + " / " + beanUsuario.getNomTienda().trim() + " / " + beanUsuario.getNomPtoEmision());        
    }

    @Override
    public void loadGui() {
        loadInitParam();
        loadParam();
    }

    @Override
    public void loadInitParam() {
        ContextMantenimientoInitParam context = FACTORY.contextMantenimientoInitParam();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<InitParamProxy>> request = context.listar(keyPublic);
        request.fire(new Receiver<List<InitParamProxy>>() {

            @Override
            public void onSuccess(List<InitParamProxy> response) {
                beanInitParam = response.get(0);
                //Window.alert(beanInitParam.getAgentePercepcion()+"-"+beanInitParam.getHabilitaPercepcion());
                //Window.alert("AUTODESPACHO: "+beanInitParam.getAutoDespacho());
                Notification not = new Notification(Notification.INFORMATION, beanInitParam.getFechaServer().toString());
                not.showPopup();
            }
        });
    }

    @Override
    public void loadParam() {
        ContextMantenimientoParam context = FACTORY.contextMantenimientoParam();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<ParamProxy>> request = context.listarParam(keyPublic);
        request.fire(new Receiver<List<ParamProxy>>() {

            @Override
            public void onSuccess(List<ParamProxy> response) {
                for (int i = 0; i < response.size(); i++) {
                    UISesion.param.put(response.get(i).getAbreviatura(), response.get(i).getValor());
                    //Window.alert(response.get(i).getValor());
                }
                //Window.alert(beanInitParam.getAgentePercepcion()+"-"+beanInitParam.getHabilitaPercepcion());
                //Window.alert("AUTODESPACHO: "+beanInitParam.getAutoDespacho());
                Notification not = new Notification(Notification.INFORMATION, beanInitParam.getFechaServer().toString());
                not.showPopup();
            }
        });
    }

    @Override
    public void loadTipoCambioNow() {
        ContextMantenimientoTipoCambio context = FACTORY.contextMantenimientoTipoCambio();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<TipoCambioProxy> request = context.getTipoCambioNow(keyPublic);
        request.fire(new Receiver<TipoCambioProxy>() {

            @Override
            public void onSuccess(TipoCambioProxy response) {
                beanTipoCambio = response;
                UIInfoTC.txtTcVenta.setText(response.getTcVenta().toString());
                UIInfoTC.txtTcCompra.setText(response.getTcCompra().toString());
                UIInfoTC.txtTcMercado.setText(response.getTcMercado().toString());
            }
        });
    }

    @Override
    public void logout() {
        servicio.logOut(new AsyncCallback<String>() {

            @Override
            public void onSuccess(String result) {
                //Window.alert(result);
                UISesion.keyPublic = null;
                UISesion.beanInitParam = null;
                UISesion.beanUsuario = null;
                UISesion.beanTipoCambio = null;
                RootPanel.get().clear();
                Notification not = new Notification(Notification.INFORMATION, result);
                not.showPopup();
                String url = Window.Location.getHref();
                url = url.replaceAll("sesion", "indexuser");
                Window.Location.assign(url);
            }

            @Override
            public void onFailure(Throwable caught) {
                //Window.alert(caught.getMessage());                
                Notification not = new Notification(Notification.WARNING, caught.getMessage());
                not.showPopup();
            }
        });
    }

    @Override
    public void loadMenu() {
        mbSystem = new UIMenuBarImpl();
        this.setComponent(UIScreenSesion.MENU, mbSystem);
    }
}
