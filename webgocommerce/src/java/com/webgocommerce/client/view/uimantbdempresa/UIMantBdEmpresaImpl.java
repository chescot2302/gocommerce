/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantbdempresa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.BdEmpresaProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoBdEmpresa;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomebdempresa.UIHomeBdEmpresa;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public class UIMantBdEmpresaImpl extends UIMantBdEmpresa{
    
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeBdEmpresa uiHomeBdEmpresa;

    public UIMantBdEmpresaImpl(UIHomeBdEmpresa uiHomeBdEmpresa) {  
        this.uiHomeBdEmpresa=uiHomeBdEmpresa;
    }
    
    @Override
    public void processInsertar() {
        Date fecha = new Date();
        ContextMantenimientoBdEmpresa context = FACTORY.contextMantenimientoBdEmpresa();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        BdEmpresaProxy bean = context.create(BdEmpresaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");
        bean.setNombre(txtEmpresa.getText());
        bean.setIpHost(txtIpHost.getText());
        bean.setPuerto(Integer.parseInt(txtPuerto.getText()));
        bean.setSchema(txtEsquema.getText());
        bean.setUserPrincipal(txtUsuario.getText());
        bean.setClavePrincipal(txtClave.getValue());
        bean.setEstadoActivacion("A");
        Request<Boolean> request = context.insertarBdEmpresa(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                cleanForm();
                Notification not=new Notification(Notification.INFORMATION,"Insertado correctamente");
                not.showPopup();
                //Window.alert("Insertado correctamente");                
            }
        });
    }
    
    @Override
    public void processActualizar() {
        Date fecha = new Date();
        ContextMantenimientoBdEmpresa context = FACTORY.contextMantenimientoBdEmpresa();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        BdEmpresaProxy bean = context.create(BdEmpresaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setNombre(txtEmpresa.getText());
        bean.setIpHost(txtIpHost.getText());
        bean.setPuerto(Integer.parseInt(txtPuerto.getText()));
        bean.setSchema(txtEsquema.getText());
        bean.setUserPrincipal(txtUsuario.getText());
        bean.setClavePrincipal(txtClave.getValue());        
        bean.setIdBdEmpresa(this.bean.getIdBdEmpresa());
        Request<Boolean> request = context.actualizarBdEmpresa(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {                
                //Window.alert("Actualizado correctamente");
                Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                not.showPopup();
                goToUIBdEmpresa();
            }
        });
    }
    
    
    @Override
    public void processEliminar() {
        Date fecha = new Date();
        ContextMantenimientoBdEmpresa context = FACTORY.contextMantenimientoBdEmpresa();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        BdEmpresaProxy bean = context.create(BdEmpresaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setIdBdEmpresa(this.bean.getIdBdEmpresa());
        Request<Boolean> request = context.eliminarBdEmpresa(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {                
                //Window.alert("Eliminado correctamente");
                Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                not.showPopup();
                goToUIBdEmpresa();
            }
        });
    }
    
    @Override
    public void goToBack() {
        uiHomeBdEmpresa.getContainer().showWidget(0);        
        uiHomeBdEmpresa.getUiBdEmpresa().loadTable();
    }
    
    @Override
    public void goToUIBdEmpresa() {
        cleanForm();        
        uiHomeBdEmpresa.getContainer().showWidget(0);
        uiHomeBdEmpresa.getUiBdEmpresa().loadTable();
    }
    

}
