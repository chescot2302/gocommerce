/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uiloginadmin;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webgocommerce.client.service.ServiceGestionSeguridad;
import com.webgocommerce.client.service.ServiceGestionSeguridadAsync;
import com.webgocommerce.client.uiutil.Notification;

/**
 *
 * @author SISTEMAS
 */
public class DUILoginAdminImpl extends DUILoginAdmin{
    private final ServiceGestionSeguridadAsync servicio=GWT.create(ServiceGestionSeguridad.class);
    
    @Override
    public void loginAdmin() {                
        String miClavePublica=txtClavePublica.getText();
        String correo=txtCorreo.getText();
        String clave=txtClave.getText();
        String nombreBd=txtNombreBd.getText();
        String clavePublicaSuper=txtClavePublicaSuper.getText();
        servicio.loginAdmin(correo, clave, nombreBd, clavePublicaSuper, miClavePublica, new AsyncCallback<String>(){            

            @Override
            public void onSuccess(String result) {
                //Window.alert(result);
                Notification not=new Notification(Notification.INFORMATION,result);
                not.showPopup();
                goToSesion();
            }
            
            @Override
            public void onFailure(Throwable caught) {
                //Window.alert(caught.getLocalizedMessage());
                Notification not=new Notification(Notification.WARNING,caught.getLocalizedMessage());
                not.showPopup();
            }
        });
    }
    
    @Override
    public void goToSesion() {
        String url = Window.Location.getHref();
        url = url.replaceAll("indexadmin", "sesion");        
        Window.Location.assign(url);
    }
}
