/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.Mesa;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicMesa;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.StringHex;
import com.gocommerce.server.util.UnknownException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import javax.jdo.datastore.JDOConnection;

/**
 *
 * @author SISTEMAS
 */
public class MantenimientoMesa {
    public static Boolean insertMesa(Mesa bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            //tx.setIsolationLevel("repeatable-read");
            tx.begin();
            LogicMesa logic = new LogicMesa(pm);
            Date fechaServer = new Date();
            if (bean.getOperacion().equalsIgnoreCase("I") &&                
                bean.getIdMesa()==null ) {                           	
                bean.setVersion(fechaServer.getTime());
                parametro.setBean(bean);
                parametro.setTipoOperacion(bean.getOperacion());
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
    
    public static Boolean updateMesa(Mesa bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            LogicMesa logic = new LogicMesa(pm);
            Date fechaServer = new Date();
            if (bean.getOperacion().equalsIgnoreCase("A")                                        
                && !bean.getIdMesa().toString().isEmpty()
                && bean.getIdMesa() != null) {
                Mesa beanUpdate=(Mesa)logic.getBean(bean.getIdMesa());
                beanUpdate.setOperacion("A");
                beanUpdate.setDescripcion(bean.getDescripcion());                                
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
        
    
    public static Boolean deleteMesaBD(Mesa bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            LogicMesa logic = new LogicMesa(pm);            
            if (
                    bean.getOperacion().equalsIgnoreCase("E")
                    && !bean.getIdMesa().toString().isEmpty()
                    && bean.getIdMesa() != null
                    ) {     
                parametro.setId(bean.getIdMesa());
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
    
    public static List<Mesa> listarMesa(String clavePublica)throws UnknownException{
        PersistenceManager pm = null;
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            LogicMesa logic = new LogicMesa(pm);            
            return (List)logic.getListarBean();
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }       
    
    public static List<Mesa> listar(String clavePublica) throws UnknownException{
        PersistenceManager pm = null;
        Connection cnx=null;
        try{
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicMesa logic = new LogicMesa(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();                
                cnx =(Connection)cnxJDO.getNativeConnection();                
                List<Mesa> lista=(List)logic.getListarBean(cnx);  
                cnx.close();
                return lista;
            }else{
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }                                   
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if(cnx!=null){
                try {
                    if(!cnx.isClosed()){
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MantenimientoMesa.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static Mesa getMesaxVendedor(String clavePublica,String idVendedor) throws UnknownException{
        PersistenceManager pm = null;
        Connection cnx=null;
        try{
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicMesa logic = new LogicMesa(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();                
                cnx =(Connection)cnxJDO.getNativeConnection();                
                Mesa bean=logic.getMesaxVendedor(cnx,idVendedor);  
                cnx.close();
                return bean;
            }else{
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }                                   
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if(cnx!=null){
                try {
                    if(!cnx.isClosed()){
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MantenimientoMesa.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static String insertarMesa(String clavePublica,Mesa bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicMesa logic = new LogicMesa(pm);   
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);                
                List<String> array=(List)logic.insertarMesa(cnx,bean);                   
                cnx.commit();
                cnx.close();                
                return array.get(0);
            }else{
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }             
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (SQLException ex) {
            throw new UnknownException(ex.getMessage());
        }finally {
            if (cnx != null) {
                try {
                    if (!cnx.isClosed()) {
                        cnx.rollback();
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MantenimientoPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static String actualizarMesa(String clavePublica,Mesa bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicMesa logic = new LogicMesa(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.actualizarMesa(cnx,bean);                   
                cnx.commit();
                cnx.close();                
                return array.get(0);
            }else{
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }             
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (SQLException ex) {
            throw new UnknownException(ex.getMessage());
        }finally {
            if (cnx != null) {
                try {
                    if (!cnx.isClosed()) {
                        cnx.rollback();
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MantenimientoPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    } 
    
    public static String eliminarMesa(String clavePublica,Mesa bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicMesa logic = new LogicMesa(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.eliminarMesa(cnx,bean);                   
                cnx.commit();
                cnx.close();                
                return array.get(0);
            }else{
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }             
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (SQLException ex) {
            throw new UnknownException(ex.getMessage());
        }finally {
            if (cnx != null) {
                try {
                    if (!cnx.isClosed()) {
                        cnx.rollback();
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MantenimientoPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static String desactivarMesa(String clavePublica,Mesa bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicMesa logic = new LogicMesa(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.desactivarMesa(cnx,bean);                   
                cnx.commit();
                cnx.close();                
                return array.get(0);
            }else{
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }             
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (SQLException ex) {
            throw new UnknownException(ex.getMessage());
        }finally {
            if (cnx != null) {
                try {
                    if (!cnx.isClosed()) {
                        cnx.rollback();
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MantenimientoPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    /** Fin de Mantenimiento Mesa*/
}
