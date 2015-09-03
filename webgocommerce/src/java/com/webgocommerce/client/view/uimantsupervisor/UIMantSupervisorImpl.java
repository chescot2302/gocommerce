/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantsupervisor;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.MesaProxy;
import com.webgocommerce.client.beanproxy.PuntoEmisionProxy;
import com.webgocommerce.client.beanproxy.SucursalProxy;
import com.webgocommerce.client.beanproxy.SupervisorProxy;
import com.webgocommerce.client.beanproxy.TiendaProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoMesa;
import com.webgocommerce.client.requestfactory.ContextMantenimientoPtoEmision;
import com.webgocommerce.client.requestfactory.ContextMantenimientoSucursal;
import com.webgocommerce.client.requestfactory.ContextMantenimientoSupervisor;
import com.webgocommerce.client.requestfactory.ContextMantenimientoTienda;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomesupervisor.UIHomeSupervisor;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIMantSupervisorImpl extends UIMantSupervisor {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeSupervisor uiHomeSupervisor;

    public UIMantSupervisorImpl(UIHomeSupervisor uiHomeSupervisor) {
        this.uiHomeSupervisor = uiHomeSupervisor;
        loadSucursal();
    }
    
    @Override
    public void loadMesa() {
        ContextMantenimientoMesa context = FACTORY.contextMantenimientoMesa();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<MesaProxy>> request = context.listarMesa(keyPublic);
        request.fire(new Receiver<List<MesaProxy>>() {

            @Override
            public void onSuccess(List<MesaProxy> response) {
                if (!response.isEmpty()) {
                    lstMesa.setData(response);
                    if (bean != null) {
                        lstMesa.setSelectedItem(bean.getNomMesa());
                    }                 
                }
            }
        });
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
                if (!response.isEmpty()) {
                    lstSucursal.setData(response);
                    if (bean != null) {
                        lstSucursal.setSelectedItem(bean.getNomSucursal());
                    }
                    loadTienda();
                } else {
                    lstTienda.clear();
                    lstPtoEmision.clear();
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
                if (!response.isEmpty()) {
                    lstTienda.setData(response);
                    if (bean != null) {
                        lstTienda.setSelectedItem(bean.getNomTienda());
                    }
                    loadPuntoEmision();
                } else {
                    lstPtoEmision.clear();
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
                if (!response.isEmpty()) {
                    lstPtoEmision.setData(response);
                    if (bean != null) {
                        lstPtoEmision.setSelectedItem(bean.getNomPtoEmision());
                    }
                } else {
                    lstPtoEmision.clear();
                }
            }
        });
    }

    @Override
    public void processInsertar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoSupervisor context = FACTORY.contextMantenimientoSupervisor();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            SupervisorProxy bean = context.create(SupervisorProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("I");
            bean.setDni(txtDni.getText().toUpperCase());
            bean.setNombres(txtNombres.getText().toUpperCase());
            bean.setApellidos(txtApellidos.getText().toUpperCase());
            bean.setEstado(1);
            bean.setIdPtoEmision(lstPtoEmision.getSelectedItem().getId());
            bean.setCodigoAlterno(txtAlterno.getText().toUpperCase());
            bean.setCanal(lstCanal.getItemText(lstCanal.getSelectedIndex()));
            bean.setCorreo(txtCorreo.getText());
            bean.setCelular(txtCelular.getText());
            bean.setFechaInc(dtFechaIncorporacion.getValue());
            Request<String> request = context.insertarSupervisor(bean,keyPublic);
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
        ContextMantenimientoSupervisor context = FACTORY.contextMantenimientoSupervisor();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        SupervisorProxy bean = context.create(SupervisorProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setNombres(txtNombres.getText().toUpperCase());
        bean.setApellidos(txtApellidos.getText().toUpperCase());
        bean.setIdSupervisor(this.bean.getIdSupervisor());
        bean.setIdPtoEmision(lstPtoEmision.getSelectedItem().getId());
        bean.setCodigoAlterno(txtAlterno.getText().toUpperCase());
        bean.setEstado(1);
        bean.setCorreo(txtCorreo.getText());
        bean.setCelular(txtCelular.getText());
        bean.setCanal(lstCanal.getItemText(lstCanal.getSelectedIndex()));
        Request<String> request = context.actualizarSupervisor(keyPublic,bean);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                //Window.alert("Actualizado correctamente");
                Notification not=new Notification(Notification.INFORMATION,response);
                not.showPopup();
                goToUISupervisor();
            }
        });
        }
    }

    @Override
    public void processEliminar() {
        Date fecha = new Date();
        ContextMantenimientoSupervisor context = FACTORY.contextMantenimientoSupervisor();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        SupervisorProxy bean = context.create(SupervisorProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setIdSupervisor(this.bean.getIdSupervisor());
        Request<String> request = context.eliminarSupervisor(keyPublic,bean);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                //Window.alert("Eliminado correctamente");
                Notification not=new Notification(Notification.INFORMATION,response);
                not.showPopup();
                goToUISupervisor();
            }
        });
    }

    @Override
    public void goToBack() {
        uiHomeSupervisor.getContainer().showWidget(0);
        uiHomeSupervisor.getUiSupervisor().loadTable();
    }

    @Override
    public void goToUISupervisor() {
        cleanForm();
        uiHomeSupervisor.getContainer().showWidget(0);
        uiHomeSupervisor.getUiSupervisor().loadTable();
    }

}
