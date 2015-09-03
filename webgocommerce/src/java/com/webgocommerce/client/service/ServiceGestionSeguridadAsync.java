/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webgocommerce.server.beans.DataSesionRPC;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public interface ServiceGestionSeguridadAsync {
    public void loginSuperAdmin(String ipHost,String nombreBd,Integer puertoBd,String usuarioBd,String claveBd,String miClavePublica,AsyncCallback<String> callback);
    public void loginAdmin(String correo,String clave,String nombreBd,String clavePublicaSuper,String miClavePublica,AsyncCallback<String> callback);
    public void loginUser(String correo,String clave,String clavePublicaAdmin,String miClavePrivada,AsyncCallback<String> callback);
    public void getKeyPublic(AsyncCallback<List> callback);
    public void logOut(AsyncCallback<String> callback);
    public void getPassword(String keyPublic,String esquema,String nick,AsyncCallback<String> callback);
    public void killSesion(List<DataSesionRPC> array,AsyncCallback<String> callback);
}
