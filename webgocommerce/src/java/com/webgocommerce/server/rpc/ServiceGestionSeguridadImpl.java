/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.server.rpc;

import com.gocommerce.server.model.beans.BdUsuario;
import com.gocommerce.server.model.beans.CredencialAdmin;
import com.gocommerce.server.model.beans.CredencialSuperAdmin;
import com.gocommerce.server.model.beans.CredencialUser;
import com.gocommerce.server.model.beans.DataSesion;
import com.gocommerce.server.process.gestionmantenimiento.MantenimientoBdUsuario;
import com.gocommerce.server.process.gestionmantenimiento.MantenimientoUsuario;
import com.gocommerce.server.process.gestionseguridad.LogOutUser;
import com.gocommerce.server.process.gestionseguridad.LoginAdmin;
import com.gocommerce.server.process.gestionseguridad.LoginSuperAdmin;
import com.gocommerce.server.process.gestionseguridad.LoginUser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webgocommerce.client.service.ServiceGestionSeguridad;
import com.webgocommerce.server.beans.DataSesionRPC;
import com.webgocommerce.server.beans.Usuario;
import com.webgocommerce.shared.UnknownException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SISTEMAS
 */
public class ServiceGestionSeguridadImpl extends RemoteServiceServlet implements ServiceGestionSeguridad {

    @Override
    public String loginSuperAdmin(String ipHost, String nombreBd, Integer puertoBd, String usuarioBd, String claveBd, String miClavePublica){
        try {
            CredencialSuperAdmin bean = new CredencialSuperAdmin();
            bean.setIp(ipHost);
            bean.setBd(nombreBd);
            bean.setPuerto(puertoBd);
            bean.setUsuario(usuarioBd);
            bean.setClave(claveBd);
            bean.setClavePublica(miClavePublica);
            HashMap<String,Object> map = LoginSuperAdmin.loginSuperAdmin(bean);
            if (!map.get("keypublic").toString().equalsIgnoreCase("ERROR")) {
                HttpSession sesion = this.getThreadLocalRequest().getSession(true);
                String idSesion = sesion.getId();
                bean.setIdSesion(idSesion);
                sesion.setAttribute("nivel", "superadmin");
                sesion.setAttribute("spid", map.get("spid"));
                sesion.setAttribute("keypublic", map.get("keypublic"));
                sesion.setAttribute("idsession", idSesion);
                sesion.setAttribute("usuario", usuarioBd);
                sesion.setAttribute("schemabd", map.get("schemabd"));
                sesion.setAttribute("usuariobd", map.get("usuariobd"));
                DataSesion beanSesion=new DataSesion();
                TimeZone timeZone = TimeZone.getDefault();
                beanSesion.setNivel("superadmin");
                beanSesion.setMyToken(miClavePublica);
                beanSesion.setIdSession(idSesion);
                beanSesion.setUsuario(usuarioBd);
                beanSesion.setUsuariobd(map.get("usuariobd").toString());
                beanSesion.setSchemabd(map.get("schemabd").toString());
                beanSesion.setRemoteAddr(this.getThreadLocalRequest().getRemoteAddr());
                beanSesion.setRemoteHost(this.getThreadLocalRequest().getRemoteHost());
                beanSesion.setRemotePort(this.getThreadLocalRequest().getRemotePort());
                beanSesion.setLocalName(this.getThreadLocalRequest().getLocalName());
                beanSesion.setLocalAddr(this.getThreadLocalRequest().getLocalAddr());
                beanSesion.setLocalPort(this.getThreadLocalRequest().getLocalPort());
                beanSesion.setAccept(this.getThreadLocalRequest().getHeader("Accept"));
                beanSesion.setConnection(this.getThreadLocalRequest().getHeader("Connection"));
                beanSesion.setReferer(this.getThreadLocalRequest().getHeader("Referer"));
                beanSesion.setPragma(this.getThreadLocalRequest().getHeader("Pragma"));
                beanSesion.setAcceptEncoding(this.getThreadLocalRequest().getHeader("Accept-Encoding"));
                beanSesion.setCacheControl(this.getThreadLocalRequest().getHeader("Cache-Control"));
                beanSesion.setxGwtModuleBase(this.getThreadLocalRequest().getHeader("X-GWT-Module-Base"));
                beanSesion.setUserAgent(this.getThreadLocalRequest().getHeader("User-Agent"));
                beanSesion.setContentType(this.getThreadLocalRequest().getHeader("Content-Type"));
                beanSesion.setAcceptLanguage(this.getThreadLocalRequest().getHeader("Accept-Language"));
                beanSesion.setContentLength(this.getThreadLocalRequest().getHeader("Content-Length"));
                beanSesion.setCookie(this.getThreadLocalRequest().getHeader("Cookie"));
                beanSesion.setHost(this.getThreadLocalRequest().getHeader("Host"));
                beanSesion.setCreationTimeSesion(new Date(sesion.getCreationTime()/*-timeZone.getOffset(System.currentTimeMillis())*/));
                beanSesion.setTimeSession(sesion.getCreationTime());
                beanSesion.setFechaIni(new Date(new Date().getTime()/*-timeZone.getOffset(System.currentTimeMillis())*/));  
                //TimeZone timeZone = TimeZone.getDefault();
                //System.out.println(timeZone.getDisplayName());
                //System.out.println(timeZone.getID());
                //System.out.println(timeZone.getOffset( System.currentTimeMillis()));
                //System.out.println(beanSesion.getFechaIni());
                //System.out.println(beanSesion.getCreationTimeSesion());
                beanSesion.setSessionIdSQL(Integer.parseInt(map.get("spid").toString()));
                List<DataSesion> lista=new ArrayList();
                lista.add(beanSesion);
                LoginSuperAdmin.insertDataSesion(map.get("keypublic").toString(), lista,beanSesion.getSessionIdSQL(),beanSesion.getSchemabd(),beanSesion.getNivel(),beanSesion.getUsuario(),beanSesion.getUsuariobd(),beanSesion);
                sesion.setAttribute("beandatasesion", beanSesion);
                sesion.setAttribute("iddatasesion", beanSesion.getIdDataSesion());
                return "Bienvenido";
            }else{
                return "Error al autenticar";
            }            
        } catch (com.gocommerce.server.util.UnknownException ex) {
            Logger.getLogger(ServiceGestionSeguridadImpl.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    @Override
    public String loginAdmin(String correo, String clave, String nombreBd, String clavePublicaSuper, String miClavePublica){
        try {
            CredencialAdmin bean = new CredencialAdmin();
            bean.setUsuario(correo);
            bean.setClave(clave);
            bean.setBd(nombreBd);
            bean.setClavePublica(clavePublicaSuper);
            bean.setMiClavePublica(miClavePublica);
            HashMap<String,Object> map = LoginAdmin.loginAdmin(bean);
            if (!map.get("keypublic").toString().equalsIgnoreCase("ERROR")) {
                HttpSession sesion = this.getThreadLocalRequest().getSession(true);
                String idSesion = sesion.getId();
                bean.setIdSesion(idSesion);
                sesion.setAttribute("nivel", "admin");
                sesion.setAttribute("spid", map.get("spid"));
                sesion.setAttribute("keypublic", map.get("keypublic"));
                sesion.setAttribute("idsession", idSesion);
                sesion.setAttribute("usuario", correo);
                sesion.setAttribute("schemabd", map.get("schemabd"));
                sesion.setAttribute("usuariobd", map.get("usuariobd"));
                DataSesion beanSesion=new DataSesion();
                TimeZone timeZone = TimeZone.getDefault();
                beanSesion.setNivel("admin");
                beanSesion.setTokenGo(clavePublicaSuper);
                beanSesion.setMyToken(miClavePublica);
                beanSesion.setIdSession(idSesion);
                beanSesion.setUsuario(correo);
                beanSesion.setUsuariobd(map.get("usuariobd").toString());
                beanSesion.setSchemabd(map.get("schemabd").toString());
                beanSesion.setRemoteAddr(this.getThreadLocalRequest().getRemoteAddr());
                beanSesion.setRemoteHost(this.getThreadLocalRequest().getRemoteHost());
                beanSesion.setRemotePort(this.getThreadLocalRequest().getRemotePort());
                beanSesion.setLocalName(this.getThreadLocalRequest().getLocalName());
                beanSesion.setLocalAddr(this.getThreadLocalRequest().getLocalAddr());
                beanSesion.setLocalPort(this.getThreadLocalRequest().getLocalPort());
                beanSesion.setAccept(this.getThreadLocalRequest().getHeader("Accept"));
                beanSesion.setConnection(this.getThreadLocalRequest().getHeader("Connection"));
                beanSesion.setReferer(this.getThreadLocalRequest().getHeader("Referer"));
                beanSesion.setPragma(this.getThreadLocalRequest().getHeader("Pragma"));
                beanSesion.setAcceptEncoding(this.getThreadLocalRequest().getHeader("Accept-Encoding"));
                beanSesion.setCacheControl(this.getThreadLocalRequest().getHeader("Cache-Control"));
                beanSesion.setxGwtModuleBase(this.getThreadLocalRequest().getHeader("X-GWT-Module-Base"));
                beanSesion.setUserAgent(this.getThreadLocalRequest().getHeader("User-Agent"));
                beanSesion.setContentType(this.getThreadLocalRequest().getHeader("Content-Type"));
                beanSesion.setAcceptLanguage(this.getThreadLocalRequest().getHeader("Accept-Language"));
                beanSesion.setContentLength(this.getThreadLocalRequest().getHeader("Content-Length"));
                beanSesion.setCookie(this.getThreadLocalRequest().getHeader("Cookie"));
                beanSesion.setHost(this.getThreadLocalRequest().getHeader("Host"));
                beanSesion.setCreationTimeSesion(new Date(sesion.getCreationTime()-timeZone.getOffset(System.currentTimeMillis())));
                beanSesion.setTimeSession(sesion.getCreationTime());
                beanSesion.setFechaIni(new Date(new Date().getTime()-timeZone.getOffset(System.currentTimeMillis())));  
                beanSesion.setSessionIdSQL(Integer.parseInt(map.get("spid").toString()));
                List<DataSesion> lista=new ArrayList();
                lista.add(beanSesion);
                LoginAdmin.insertDataSesion(clavePublicaSuper, map.get("keypublic").toString(),lista,beanSesion.getSessionIdSQL(),beanSesion.getSchemabd(),beanSesion.getNivel(),beanSesion.getUsuario(),beanSesion.getUsuariobd(),beanSesion);                
                sesion.setAttribute("beandatasesion", beanSesion);
                sesion.setAttribute("iddatasesion", beanSesion.getIdDataSesion());
                return "Bienvenido";
            }else{
                return "Error al autenticar";
            }
            
        } catch (com.gocommerce.server.util.UnknownException ex) {
            Logger.getLogger(ServiceGestionSeguridadImpl.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    @Override
    public String loginUser(String correo, String clave, String clavePublicaAdmin, String clavePrivada){
        try {
            CredencialUser bean = new CredencialUser();
            bean.setUsuario(correo);
            bean.setClave(clave);
            bean.setClavePublica(clavePublicaAdmin);
            bean.setMiClavePrivada(clavePrivada);
            HashMap<String,Object> map = LoginUser.loginUser(bean);
            if (!map.get("keypublic").toString().equalsIgnoreCase("ERROR")) {
                HttpSession sesion = this.getThreadLocalRequest().getSession(true);
                String idSesion = sesion.getId();
                bean.setIdSesion(idSesion);
                sesion.setAttribute("nivel", "user");
                sesion.setAttribute("spid", map.get("spid"));
                sesion.setAttribute("keypublic", map.get("keypublic"));
                sesion.setAttribute("idsession", idSesion);
                sesion.setAttribute("usuario", correo);      
                sesion.setAttribute("schemabd", map.get("schemabd"));
                sesion.setAttribute("usuariobd", map.get("usuariobd"));
                DataSesion beanSesion=new DataSesion();
                TimeZone timeZone = TimeZone.getDefault();
                beanSesion.setNivel("user");
                beanSesion.setTokenGo(clavePublicaAdmin);
                beanSesion.setMyToken(clavePrivada);
                beanSesion.setIdSession(idSesion);
                beanSesion.setUsuario(correo);
                beanSesion.setUsuariobd(map.get("usuariobd").toString());
                beanSesion.setSchemabd(map.get("schemabd").toString());
                beanSesion.setRemoteAddr(this.getThreadLocalRequest().getRemoteAddr());
                beanSesion.setRemoteHost(this.getThreadLocalRequest().getRemoteHost());
                beanSesion.setRemotePort(this.getThreadLocalRequest().getRemotePort());
                beanSesion.setLocalName(this.getThreadLocalRequest().getLocalName());
                beanSesion.setLocalAddr(this.getThreadLocalRequest().getLocalAddr());
                beanSesion.setLocalPort(this.getThreadLocalRequest().getLocalPort());
                beanSesion.setAccept(this.getThreadLocalRequest().getHeader("Accept"));
                beanSesion.setConnection(this.getThreadLocalRequest().getHeader("Connection"));
                beanSesion.setReferer(this.getThreadLocalRequest().getHeader("Referer"));
                beanSesion.setPragma(this.getThreadLocalRequest().getHeader("Pragma"));
                beanSesion.setAcceptEncoding(this.getThreadLocalRequest().getHeader("Accept-Encoding"));
                beanSesion.setCacheControl(this.getThreadLocalRequest().getHeader("Cache-Control"));
                beanSesion.setxGwtModuleBase(this.getThreadLocalRequest().getHeader("X-GWT-Module-Base"));
                beanSesion.setUserAgent(this.getThreadLocalRequest().getHeader("User-Agent"));
                beanSesion.setContentType(this.getThreadLocalRequest().getHeader("Content-Type"));
                beanSesion.setAcceptLanguage(this.getThreadLocalRequest().getHeader("Accept-Language"));
                beanSesion.setContentLength(this.getThreadLocalRequest().getHeader("Content-Length"));
                beanSesion.setCookie(this.getThreadLocalRequest().getHeader("Cookie"));
                beanSesion.setHost(this.getThreadLocalRequest().getHeader("Host"));
                beanSesion.setCreationTimeSesion(new Date(sesion.getCreationTime()-timeZone.getOffset(System.currentTimeMillis())));
                beanSesion.setTimeSession(sesion.getCreationTime());
                beanSesion.setFechaIni(new Date(new Date().getTime()-timeZone.getOffset(System.currentTimeMillis())));  
                beanSesion.setSessionIdSQL(Integer.parseInt(map.get("spid").toString()));
                List<DataSesion> lista=new ArrayList();
                lista.add(beanSesion);
                LoginUser.insertDataSesion(clavePublicaAdmin, map.get("keypublic").toString(),lista,beanSesion.getSessionIdSQL(),beanSesion.getSchemabd(),beanSesion.getNivel(),beanSesion.getUsuario(),beanSesion.getUsuariobd(),beanSesion);                
                sesion.setAttribute("beandatasesion", beanSesion);
                sesion.setAttribute("iddatasesion", beanSesion.getIdDataSesion());
                return "Bienvenido";
            }else{
                return "Error al autenticar";
            }           
        } catch (com.gocommerce.server.util.UnknownException ex) {
            Logger.getLogger(ServiceGestionSeguridadImpl.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    @Override
    public List getKeyPublic() throws UnknownException {
        try {
            HttpSession sesion = this.getThreadLocalRequest().getSession(false);
            List arrayValue = new ArrayList();
            String keyPublic = sesion.getAttribute("keypublic").toString();
            String nivel = sesion.getAttribute("nivel").toString();
            String usuario = sesion.getAttribute("usuario").toString();
            String usuarioBd=sesion.getAttribute("usuariobd").toString();
            String schemaBd=sesion.getAttribute("schemabd").toString();
            arrayValue.add(keyPublic);
            arrayValue.add(nivel);
            arrayValue.add(usuario);
            if (nivel.equalsIgnoreCase("user") || nivel.equalsIgnoreCase("admin")) {
                com.gocommerce.server.model.beans.Usuario beanModel = MantenimientoUsuario.getUsuarioNick(keyPublic, usuario);
                BdUsuario beanBdUsuario=MantenimientoBdUsuario.getBdUsuario(keyPublic, schemaBd, usuarioBd, nivel,usuario);
                Usuario bean = new Usuario();
                bean.setIdBdUsuario(beanBdUsuario.getIdBdUsuario());
                bean.setId(beanModel.getId());
                bean.setNick(beanModel.getNick());
                bean.setNombres(beanModel.getNombres());
                bean.setIdPuntoEmision(beanModel.getIdPuntoEmision());
                bean.setIdAlmacen(beanModel.getIdAlmacen());
                bean.setNomPtoEmision(beanModel.getNomPtoEmision());
                bean.setIdTienda(beanModel.getIdTienda());
                bean.setNomTienda(beanModel.getNomTienda());
                bean.setIdSucursal(beanModel.getIdSucursal());
                bean.setNomSucursal(beanModel.getNomSucursal());
                bean.setIdLocalidad(beanModel.getIdLocalidad());
                bean.setNomLocalidad(beanModel.getNomLocalidad());
                bean.setEsquema(schemaBd);
                arrayValue.add(bean);
            } else {   
                BdUsuario beanBdUsuario=MantenimientoBdUsuario.getBdUsuario(keyPublic, schemaBd, usuarioBd, nivel,usuario);
                Usuario bean = new Usuario();   
                bean.setEsquema(schemaBd);
                bean.setIdBdUsuario(beanBdUsuario.getIdBdUsuario());                
                bean.setNombres(usuario);
                arrayValue.add(bean);
            }
            return arrayValue;
        } catch (Exception ex) {
            Logger.getLogger(ServiceGestionSeguridadImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }

    @Override
    public String logOut(){
        try{
        String mensaje = "Error al cerrar Sesion";
        HttpSession sesion = this.getThreadLocalRequest().getSession();                
        //if (sesion.getAttribute("nivel").toString().equalsIgnoreCase("user")) {
            if (LogOutUser.logOut(sesion.getAttribute("nivel").toString(),sesion.getAttribute("keypublic").toString(),sesion.getId(),(Integer)sesion.getAttribute("spid"),(Integer)sesion.getAttribute("iddatasesion"),new Date(sesion.getLastAccessedTime()),(DataSesion)sesion.getAttribute("beandatasesion"))) {
                mensaje = "Vuelve Pronto";
            }
        //}else{
            //mensaje = "Vuelve Pronto";
        //}
        sesion.removeAttribute("nivel");
        sesion.removeAttribute("spid");
        sesion.removeAttribute("keypublic");
        sesion.removeAttribute("idsession");
        sesion.removeAttribute("usuario");
        sesion.removeAttribute("schemabd");
        sesion.removeAttribute("usuariobd");
        sesion.removeAttribute("beanDataSesion");
        sesion.removeAttribute("iddatasesion");
        sesion.invalidate();
        return mensaje;
        }catch(Exception ex){
            Logger.getLogger(ServiceGestionSeguridadImpl.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    @Override
    public String getPassword(String keyPublic,String esquema,String nick) {
        try {
            return MantenimientoUsuario.getPassword(keyPublic, esquema, nick);
        } catch (com.gocommerce.server.util.UnknownException ex) {
            Logger.getLogger(ServiceGestionSeguridadImpl.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getLocalizedMessage();
        }
    }

    @Override
    public String killSesion(List<DataSesionRPC> array) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
