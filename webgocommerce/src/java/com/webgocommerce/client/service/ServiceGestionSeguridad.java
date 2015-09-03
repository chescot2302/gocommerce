/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.webgocommerce.server.beans.DataSesionRPC;
import com.webgocommerce.shared.UnknownException;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@RemoteServiceRelativePath("servicegestionseguridad")
public interface ServiceGestionSeguridad extends RemoteService {
    public String loginSuperAdmin(String ipHost,String nombreBd,Integer puertoBd,String usuarioBd,String claveBd,String miClavePublica);
    public String loginAdmin(String correo,String clave,String nombreBd,String clavePublicaSuper,String miClavePublica);
    public String loginUser(String correo,String clave, String clavePublicaAdmin,String clavePrivada);
    public List getKeyPublic()throws UnknownException;
    public String logOut();
    public String getPassword(String keyPublic,String esquema,String nick);
    public String killSesion(List<DataSesionRPC> array);
}
