/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.Supervisor;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicSupervisor;
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
public class MantenimientoSupervisor {
    public static Boolean insertSupervisor(Supervisor bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            //tx.setIsolationLevel("repeatable-read");
            tx.begin();
            LogicSupervisor logic = new LogicSupervisor(pm);
            Date fechaServer = new Date();
            if (bean.getOperacion().equalsIgnoreCase("I") &&                
                bean.getIdSupervisor()==null ) {                           	
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
    
    public static Boolean updateSupervisor(Supervisor bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            LogicSupervisor logic = new LogicSupervisor(pm);
            Date fechaServer = new Date();
            if (bean.getOperacion().equalsIgnoreCase("A")                                        
                && !bean.getIdSupervisor().toString().isEmpty()
                && bean.getIdSupervisor() != null) {
                Supervisor beanUpdate=(Supervisor)logic.getBean(bean.getIdSupervisor());
                beanUpdate.setOperacion("A");
                beanUpdate.setNombres(bean.getNombres());                                
                beanUpdate.setApellidos(bean.getApellidos());   
                beanUpdate.setIdPtoEmision(bean.getIdPtoEmision());
                beanUpdate.setCodigoAlterno(bean.getCodigoAlterno());                
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
        
    
    public static Boolean deleteSupervisorBD(Supervisor bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            LogicSupervisor logic = new LogicSupervisor(pm);            
            if (
                    bean.getOperacion().equalsIgnoreCase("E")
                    && !bean.getIdSupervisor().toString().isEmpty()
                    && bean.getIdSupervisor() != null
                    ) {     
                parametro.setId(bean.getIdSupervisor());
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
    
    public static List<Supervisor> listar(String clavePublica)throws UnknownException{
        PersistenceManager pm = null;
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            LogicSupervisor logic = new LogicSupervisor(pm);            
            return (List)logic.getListarBean();
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    } 
    
    public static List<Supervisor> listarSupervisor(String clavePublica,String estado) throws UnknownException{
        PersistenceManager pm = null;
        Connection cnx=null;
        try{
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicSupervisor logic = new LogicSupervisor(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                List<Supervisor> lista=(List)logic.getListarBean(cnx,estado);  
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
            if (cnx != null) {
                try {
                    if (!cnx.isClosed()) {
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MantenimientoSupervisor.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static List<Supervisor> listarSupervisorCl(String clavePublica) throws UnknownException{
        PersistenceManager pm = null;
        Connection cnx=null;
        try{
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicSupervisor logic = new LogicSupervisor(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                List<Supervisor> lista=(List)logic.getListarSupervisorCl(cnx);  
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
            if (cnx != null) {
                try {
                    if (!cnx.isClosed()) {
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MantenimientoSupervisor.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static String insertarSupervisor(Supervisor bean, String clavePublica) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicSupervisor logic = new LogicSupervisor(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.insertarSupervisor(cnx,bean);                   
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
                    Logger.getLogger(MantenimientoSupervisor.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static String actualizarSupervisor(String clavePublica,Supervisor bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicSupervisor logic = new LogicSupervisor(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();                
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.actualizarSupervisor(cnx,bean);                   
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
                    Logger.getLogger(MantenimientoSupervisor.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static String actDesSupervisor(String clavePublica,Supervisor bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicSupervisor logic = new LogicSupervisor(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();                
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.actDesSupervisor(cnx,bean);                   
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
                    Logger.getLogger(MantenimientoSupervisor.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    
    public static String eliminarSupervisor(String clavePublica,Supervisor bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicSupervisor logic = new LogicSupervisor(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.eliminarSupervisor(cnx,bean);                   
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
                    Logger.getLogger(MantenimientoSupervisor.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    /** Fin de Mantenimiento Supervisor*/
}
