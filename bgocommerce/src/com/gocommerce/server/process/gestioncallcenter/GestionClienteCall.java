/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.process.gestioncallcenter;

import com.gocommerce.server.model.beans.ClienteCallCenter;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicClienteCallCenter;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.StringHex;
import com.gocommerce.server.util.UnknownException;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

/**
 *
 * @author SISTEMAS
 */
public class GestionClienteCall {
    public static String insertClienteCallCenter(ClienteCallCenter bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();            
            tx = pm.currentTransaction();
            //tx.setIsolationLevel("repeatable-read");
            tx.begin();            
            LogicClienteCallCenter logic = new LogicClienteCallCenter(pm);
            List<ClienteCallCenter> lista=(List)logic.getBean(bean.getDni());
            if(lista.size()==0){
            Date fechaServer = new Date();
            if (bean.getOperacion().equalsIgnoreCase("I") &&                
                bean.getId()==null ) {                           	
                bean.setVersion(fechaServer.getTime());
                parametro.setBean(bean);
                parametro.setTipoOperacion(bean.getOperacion());
            }else{
                throw new UnknownException("Configuracion corrupta");
            }
            Boolean resultado = logic.mantenimiento(parametro);
            if (resultado) {
                tx.commit();
                return "REGISTRADO CORRECTAMENTE";
            } else {
                tx.rollback();                
                return "ERROR AL REGISTRAR";
            }
            }else{
                return "DNI YA FUE REGISTRADA ANTES";
            }
        }catch (UnknownException ex) {           
            throw new UnknownException(ex.getMessage());
        } finally {
            if (!pm.isClosed()) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                pm.close();
            }
        }
    }
    
    public static Boolean updateClienteCallCenter(ClienteCallCenter bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            LogicClienteCallCenter logic = new LogicClienteCallCenter(pm);
            Date fechaServer = new Date();
            if (bean.getOperacion().equalsIgnoreCase("A")                                        
                && !bean.getId().toString().isEmpty()
                && bean.getId() != null) {
                ClienteCallCenter beanUpdate=(ClienteCallCenter)logic.getBean(bean.getId());
                beanUpdate.setOperacion("A");
                beanUpdate.setApellidos(bean.getApellidos());                                
                beanUpdate.setDni(bean.getDni());
                beanUpdate.setEstado(bean.getEstado());
                beanUpdate.setFecha(bean.getFecha());
                beanUpdate.setIdUsuario(bean.getIdUsuario());
                beanUpdate.setNombres(bean.getNombres());
                beanUpdate.setObservacion(bean.getObservacion());
                beanUpdate.setVersion(fechaServer.getTime());
                parametro.setBean(beanUpdate);
                parametro.setTipoOperacion(beanUpdate.getOperacion());
            }else{
                throw new UnknownException("Configuracion corrupta");
            }
            Boolean resultado = logic.mantenimiento(parametro);
            if (resultado) {
                tx.commit();
                return true;
            } else {
                tx.rollback();                
                return false;
            }
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if (!pm.isClosed()) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                pm.close();
            }
        }
    }
        
    
    public static Boolean deleteClienteCallCenterBD(ClienteCallCenter bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            LogicClienteCallCenter logic = new LogicClienteCallCenter(pm);            
            if (
                    bean.getOperacion().equalsIgnoreCase("E")
                    && !bean.getId().toString().isEmpty()
                    && bean.getId() != null
                    ) {     
                parametro.setId(bean.getId());
                parametro.setBean(bean);
                parametro.setTipoOperacion(bean.getOperacion());
            }else {
                throw new UnknownException("Configuracion corrupta");
            }
            Boolean resultado = logic.mantenimiento(parametro);
            if (resultado) {
                tx.commit();
                return true;
            } else {
                tx.rollback();                
                return false;
            }
        }catch (UnknownException ex) {            
            throw new UnknownException(ex.getMessage());
        } finally {
            if (!pm.isClosed()) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                pm.close();
            }
        }
    }
    
    public static List<ClienteCallCenter> listarClienteCallCenter(String clavePublica)throws UnknownException{
        PersistenceManager pm = null;
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            LogicClienteCallCenter logic = new LogicClienteCallCenter(pm);            
            return (List)logic.getListarBean();
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }       
    
    /** Fin de Mantenimiento ClienteCallCenter*/
}
