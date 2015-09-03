/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.DataSesion;
import com.gocommerce.server.model.dao.DaoDataSesion;
import com.gocommerce.server.util.Parametro;
import com.gocommerce.server.util.UnknownException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class LogicDataSesion {
    private PersistenceManager pm;
    
    public LogicDataSesion(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public void insertDataSesion(Connection cnx,String xmlData,Integer sesionIdSql,String schemaBd,String nivel,String usuario,String usuarioBd,DataSesion bean) throws UnknownException{
        try {
            DaoDataSesion ado = new DaoDataSesion(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",xmlData);
            Parametro param2=new Parametro("IN",sesionIdSql);            
            Parametro param3=new Parametro("IN",schemaBd);
            Parametro param4=new Parametro("IN",nivel);
            Parametro param5=new Parametro("IN",usuario);
            Parametro param6=new Parametro("IN",usuarioBd);            
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);
            parametros.add(param6);           
            ArrayList objetos = ado.insertDataSesion(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);      
            while (rs.next()) {                
                bean.setIdDataSesion(rs.getInt("IDDATASESION"));
                bean.setIdUsuarioBd(rs.getInt("IDBDUSUARIO"));
                bean.setLoginName(rs.getString("LOGINNAME"));
                bean.setHostName(rs.getString("HOSTNAME"));
                bean.setDbName(rs.getString("DBNAME"));
                bean.setProgramName(rs.getString("PROGRAMNAME"));
                bean.setLoginTime(rs.getDate("LOGINTIME"));
                bean.setHostProcessId(rs.getInt("HOSTPROCESSID"));
                bean.setBdId(rs.getInt("BDID"));
            }
            rs.close();
            cst.close();
            //return bean;
        } catch (Exception ex) {
            Logger.getLogger(LogicDataSesion.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public String updateDataSesion(Connection cnx,Integer idDataSesion,Date fechaDestroySesion) throws UnknownException{
        try {
            DaoDataSesion ado = new DaoDataSesion(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",idDataSesion);
            Parametro param2=new Parametro("IN",new java.sql.Date(fechaDestroySesion.getTime()));                       
            parametros.add(param1);
            parametros.add(param2);          
            ArrayList objetos = ado.updateDataSesion(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);      
            String msg="error";
            while (rs.next()) {                
                msg=rs.getString("msg");
            }
            rs.close();
            cst.close();
            return msg;
        } catch (Exception ex) {
            Logger.getLogger(LogicDataSesion.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public List<DataSesion> listarDataSesion(Connection cnx,String xmlData,String loginName) throws UnknownException{
        try {
            DaoDataSesion ado = new DaoDataSesion(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",xmlData);
            Parametro param2=new Parametro("IN",loginName);                      
            parametros.add(param1);
            parametros.add(param2);         
            ArrayList objetos = ado.listarDataSesion(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);                  
            List<DataSesion> lista=new ArrayList();
            Integer contador=-1;
            TimeZone timeZone = TimeZone.getDefault();
            while (rs.next()) {   
                DataSesion bean=new DataSesion();
                bean.setEstado(rs.getString("ESTADO"));
                bean.setNivel(rs.getString("NIVEL"));
                bean.setTokenGo(rs.getString("TOKENGO"));
                bean.setMyToken(rs.getString("MYTOKEN"));
                bean.setIdSession(rs.getString("IDSESSION"));
                bean.setUsuario(rs.getString("USUARIO"));
                bean.setUsuariobd(rs.getString("USUARIOBD"));
                bean.setSchemabd(rs.getString("SCHEMABD"));
                bean.setRemoteAddr(rs.getString("REMOTEADDR"));
                bean.setRemoteHost(rs.getString("REMOTEHOST"));
                bean.setRemotePort(rs.getInt("REMOTEPORT"));
                bean.setLocalName(rs.getString("LOCALNAME"));
                bean.setLocalAddr(rs.getString("LOCALADDR"));
                bean.setLocalPort(rs.getInt("LOCALPORT"));
                bean.setAccept(rs.getString("ACCEPT"));
                bean.setConnection(rs.getString("CONNECTION"));
                bean.setReferer(rs.getString("REFERER"));
                bean.setPragma(rs.getString("PRAGMA"));
                bean.setAcceptEncoding(rs.getString("ACCEPTENCODING"));
                bean.setCacheControl(rs.getString("CACHECONTROL"));
                bean.setxGwtModuleBase(rs.getString("XGWTMODULEBASE"));
                bean.setUserAgent(rs.getString("USERAGENT"));
                bean.setContentType(rs.getString("CONTENTTYPE"));
                bean.setAcceptLanguage(rs.getString("ACCEPTLANGUAGE"));
                bean.setContentLength(rs.getString("CONTENTLENGTH"));
                bean.setCookie(rs.getString("COOKIE"));
                bean.setHost(rs.getString("HOST"));                
                bean.setCreationTimeSesion(new Date(rs.getLong("CREATIONSESION")-timeZone.getOffset(System.currentTimeMillis())));
                bean.setLastTimeSesion(rs.getDate("LASTSESION"));
                bean.setFechaIni(rs.getDate("FECHAINI"));
                bean.setFechaFin(rs.getDate("FECHAFIN"));                                
                bean.setIdDataSesion(rs.getInt("IDDATASESION"));
                bean.setIdUsuarioBd(rs.getInt("IDBDUSUARIO"));
                bean.setIdUsuarioBdGO(rs.getInt("IDBDUSUARIOGO"));
                bean.setSessionIdSQL(rs.getInt("SESIONIDSQL"));
                bean.setLoginName(rs.getString("LOGINNAME"));
                bean.setHostName(rs.getString("HOSTNAME"));
                bean.setDbName(rs.getString("DBNAME"));
                bean.setProgramName(rs.getString("PROGRAMNAME"));
                bean.setLoginTime(new Date(rs.getLong("LOGINTIME")-timeZone.getOffset(System.currentTimeMillis())));
                bean.setHostProcessId(rs.getInt("HOSTPROCESSID"));
                bean.setBdId(rs.getInt("BDID"));
                bean.setVersion(Long.parseLong("1"));
                if(bean.getIdDataSesion()==0){
                    bean.setIdDataSesion(contador);
                }
                contador=contador-1;
                lista.add(bean);                
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicDataSesion.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
