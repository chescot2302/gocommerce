/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uiloginsuperadmin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webgocommerce.client.service.ServiceGestionSeguridad;
import com.webgocommerce.client.service.ServiceGestionSeguridadAsync;
import com.webgocommerce.client.uiutil.Notification;

/**
 *
 * @author SISTEMAS
 */
public class DUILoginSuperAdminImpl extends DUILoginSuperAdmin {

    private final ServiceGestionSeguridadAsync servicio = GWT.create(ServiceGestionSeguridad.class);

    @Override
    public void loginSuperAdmin() {
        String ipHost = txtIpHost.getText();
        String nombreBd = txtNombreBd.getText();
        Integer puertoBd = Integer.parseInt(txtPuertoBd.getText());
        String usuarioBd = txtUsuarioBd.getText();
        String claveBd = txtClaveBd.getText();
        String miClavePublica = txtClavePublica.getText();
        servicio.loginSuperAdmin(ipHost, nombreBd, puertoBd, usuarioBd, claveBd, miClavePublica, new AsyncCallback<String>() {           

            @Override
            public void onSuccess(String result) {
                //Window.alert("Fecha: "+new Date());
                Notification not=new Notification(Notification.INFORMATION,result);
                not.showPopup();
                goToSesion();
            }
            
            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getLocalizedMessage());
                Notification not=new Notification(Notification.WARNING,caught.getLocalizedMessage());
                not.showPopup();
            }
            
        });
    }

    @Override
    public void goToSesion() {
        String url = Window.Location.getHref();
        url = url.replaceAll("indexsuperadmin", "sesion");        
        Window.Location.assign(url);
    }
}
