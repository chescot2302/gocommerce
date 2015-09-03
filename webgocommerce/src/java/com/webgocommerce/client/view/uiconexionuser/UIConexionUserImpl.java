/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uiconexionuser;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.DataSesionProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoBdUsuario;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.service.ServiceGestionSeguridad;
import com.webgocommerce.client.service.ServiceGestionSeguridadAsync;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomebdusuario.UIHomeBdUsuario;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIConexionUserImpl extends UIConexionUser{ 
    private final ServiceGestionSeguridadAsync servicio = GWT.create(ServiceGestionSeguridad.class);
    private UIHomeBdUsuario uiHomeBdUsuario;
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();    
            
    public UIConexionUserImpl(UIHomeBdUsuario uiHomeBdUsuario){
        this.uiHomeBdUsuario = uiHomeBdUsuario;
    }
    
    @Override
    public void goToUIBdUsuario() {
        uiHomeBdUsuario.getContainer().showWidget(0);
        uiHomeBdUsuario.getUiBdUsuario().loadTable();
    }

    @Override
    public void loadTable() {
        if (bean != null) {
            ContextMantenimientoBdUsuario context = FACTORY.contextMantenimientoBdUsuario();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            Request<List<DataSesionProxy>> request = context.getConexionUser(keyPublic,bean.getIdBdUsuario(),bean.getUsuarioBd()).with("beanGo");
            request.fire(new Receiver<List<DataSesionProxy>>() {

                @Override
                public void onSuccess(List<DataSesionProxy> response) {                 
                    grid.getSelectionModel().clear();
                    grid.setData(response);
                }

                @Override
                public void onFailure(ServerFailure error) {                    
                    Notification not = new Notification(Notification.WARNING, error.getMessage());
                    not.showPopup();
                }
            });
        }else {
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    @Override
    public void killSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
}
