/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantmesa;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.CoordinadorProxy;
import com.webgocommerce.client.beanproxy.GerenteZonalProxy;
import com.webgocommerce.client.beanproxy.MesaProxy;
import com.webgocommerce.client.beanproxy.SucursalProxy;
import com.webgocommerce.client.beanproxy.SupervisorProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoCoordinador;
import com.webgocommerce.client.requestfactory.ContextMantenimientoGerZonal;
import com.webgocommerce.client.requestfactory.ContextMantenimientoMesa;
import com.webgocommerce.client.requestfactory.ContextMantenimientoSucursal;
import com.webgocommerce.client.requestfactory.ContextMantenimientoSupervisor;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomemesa.UIHomeMesa;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIMantMesaImpl extends UIMantMesa {
    
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeMesa uiHomeMesa;
    
    public UIMantMesaImpl(UIHomeMesa uiHomeMesa) {
        this.uiHomeMesa = uiHomeMesa;
        loadCoordinador();
        loadGerenteZonal();
        loadSupervisor();
        loadSucursal();
    }
    
    @Override
    public void loadSucursal() {
        ContextMantenimientoSucursal context = FACTORY.contextMantenimientoSucursal();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<SucursalProxy>> request = context.listarSucursalCla(keyPublic);
        request.fire(new Receiver<List<SucursalProxy>>() {
            
            @Override
            public void onSuccess(List<SucursalProxy> response) {
                if (!response.isEmpty()) {
                    lstSucursal.setData(response);
                    if (bean != null) {
                        lstSucursal.setSelectedItem(bean.getNomSucursal());
                    }
                }
            }
        });
    }
    
    @Override
    public void loadCoordinador() {
        ContextMantenimientoCoordinador context = FACTORY.contextMantenimientoCoordinador();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<CoordinadorProxy>> request = context.listarCoordinador(keyPublic, "A");
        request.fire(new Receiver<List<CoordinadorProxy>>() {
            
            @Override
            public void onSuccess(List<CoordinadorProxy> response) {
                if (!response.isEmpty()) {
                    lstCoordinador.setData(response);
                    if (bean != null) {
                        lstCoordinador.setSelectedItem(bean.getIdCoordinador().toString());
                    }
                }
            }
            
            @Override
            public void onFailure(ServerFailure error) {
                //Window.alert(error.getMessage());                    
                Notification not = new Notification(Notification.WARNING, error.getMessage());
                not.showPopup();
            }
        });
    }
    
    @Override
    public void loadGerenteZonal() {
        ContextMantenimientoGerZonal context = FACTORY.contextMantenimientoGerZonal();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<GerenteZonalProxy>> request = context.listarGerenteZonal(keyPublic, "A");
        request.fire(new Receiver<List<GerenteZonalProxy>>() {
            
            @Override
            public void onSuccess(List<GerenteZonalProxy> response) {
                if (!response.isEmpty()) {
                    lstGerenteZonal.setData(response);
                    if (bean != null) {
                        lstGerenteZonal.setSelectedItem(bean.getIdGerenteZonal().toString());
                    }
                }
            }
            
            @Override
            public void onFailure(ServerFailure error) {
                Notification not = new Notification(Notification.WARNING, error.getMessage());
                not.showPopup();
            }
        });
    }
    
    @Override
    public void loadSupervisor() {
        ContextMantenimientoSupervisor context = FACTORY.contextMantenimientoSupervisor();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<SupervisorProxy>> request = context.listarSupervisorCl(keyPublic);
        request.fire(new Receiver<List<SupervisorProxy>>() {
            
            @Override
            public void onSuccess(List<SupervisorProxy> response) {
                if (!response.isEmpty()) {
                    lstSupervisor.setData(response);
                    if (bean != null) {
                        lstSupervisor.setSelectedItem(bean.getIdSupervisor().toString());
                    }
                } else {
                    lstSupervisor.removeAllItem();
                }
            }
            
            @Override
            public void onFailure(ServerFailure error) {
                Notification not = new Notification(Notification.WARNING, error.getMessage());
                not.showPopup();
            }
        });
    }
    
    @Override
    public void processInsertar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoMesa context = FACTORY.contextMantenimientoMesa();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            MesaProxy bean = context.create(MesaProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("I");
            bean.setDescripcion(txtDescripcion.getText().toUpperCase());
            bean.setIdCoordinador(lstCoordinador.getSelectedItem().getId());
            bean.setIdGerenteZonal(lstGerenteZonal.getSelectedItem().getId());
            bean.setIdSupervisor(lstSupervisor.getSelectedItem().getIdSupervisor());
            bean.setIdSucursal(lstSucursal.getSelectedItem().getId());
            Request<String> request = context.insertarMesa(keyPublic, bean);
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
            ContextMantenimientoMesa context = FACTORY.contextMantenimientoMesa();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            MesaProxy bean = context.create(MesaProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("A");
            bean.setDescripcion(txtDescripcion.getText().toUpperCase());
            bean.setIdMesa(this.bean.getIdMesa());
            bean.setIdCoordinador(lstCoordinador.getSelectedItem().getId());
            bean.setIdGerenteZonal(lstGerenteZonal.getSelectedItem().getId());
            bean.setIdSupervisor(lstSupervisor.getSelectedItem().getIdSupervisor());
            bean.setIdSucursal(lstSucursal.getSelectedItem().getId());
            Request<String> request = context.actualizarMesa(keyPublic, bean);
            request.fire(new Receiver<String>() {
                
                @Override
                public void onSuccess(String response) {
                    //Window.alert("Actualizado correctamente");
                    Notification not = new Notification(Notification.INFORMATION, response);
                    not.showPopup();
                    if (response.equalsIgnoreCase("ACTUALIZADO CORRECTAMENTE")) {
                        goToUIMesa();
                    }
                }
            });
        }
    }
    
    @Override
    public void processEliminar() {
        Date fecha = new Date();
        ContextMantenimientoMesa context = FACTORY.contextMantenimientoMesa();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        MesaProxy bean = context.create(MesaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setIdMesa(this.bean.getIdMesa());
        Request<String> request = context.eliminarMesa(keyPublic,bean);
        request.fire(new Receiver<String>() {
            
            @Override
            public void onSuccess(String response) {
                //Window.alert("Eliminado correctamente");
                Notification not = new Notification(Notification.INFORMATION, response);
                not.showPopup();
                goToUIMesa();
            }
        });
    }
    
    @Override
    public void goToBack() {
        uiHomeMesa.getContainer().showWidget(0);
        uiHomeMesa.getUiMesa().loadTable();
    }
    
    @Override
    public void goToUIMesa() {
        cleanForm();
        uiHomeMesa.getContainer().showWidget(0);
        uiHomeMesa.getUiMesa().loadTable();
    }
    
    @Override
    public void processDesactivar() {
        Date fecha = new Date();
        ContextMantenimientoMesa context = FACTORY.contextMantenimientoMesa();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        MesaProxy bean = context.create(MesaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setIdMesa(this.bean.getIdMesa());
        Request<String> request = context.desactivarMesa(keyPublic,bean);
        request.fire(new Receiver<String>() {

            @Override
            public void onSuccess(String response) {
                //Window.alert("Desactivado correctamente");
                Notification not=new Notification(Notification.INFORMATION,response);
                not.showPopup();
                goToUIMesa();
            }
        });
    }
    
}
