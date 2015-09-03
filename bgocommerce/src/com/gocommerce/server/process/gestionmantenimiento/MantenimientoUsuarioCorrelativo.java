/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.UsuarioCorrelativo;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicUsuarioCorrelativo;
import com.gocommerce.server.util.StringHex;
import com.gocommerce.server.util.UnknownException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.datastore.JDOConnection;

/**
 *
 * @author SISTEMAS
 */
public class MantenimientoUsuarioCorrelativo {
    public static List<UsuarioCorrelativo> listar(String clavePublica) throws UnknownException{
        PersistenceManager pm = null;
        Connection cnx=null;
        try{
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicUsuarioCorrelativo logic = new LogicUsuarioCorrelativo(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                List<UsuarioCorrelativo> lista=(List)logic.getListarBean(cnx);  
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
                    Logger.getLogger(MantenimientoUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static String insertarUsuarioCorrelativo(String clavePublica,UsuarioCorrelativo bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicUsuarioCorrelativo logic = new LogicUsuarioCorrelativo(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.insertarUsuarioCorrelativo(cnx,bean);                   
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
                    Logger.getLogger(MantenimientoUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static String actualizarUsuarioCorrelativo(String clavePublica,UsuarioCorrelativo bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicUsuarioCorrelativo logic = new LogicUsuarioCorrelativo(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.actualizarUsuarioCorrelativo(cnx,bean);                   
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
                    Logger.getLogger(MantenimientoUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static String desactivarUsuarioCorrelativo(String clavePublica,UsuarioCorrelativo bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicUsuarioCorrelativo logic = new LogicUsuarioCorrelativo(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.desactivarUsuarioCorrelativo(cnx,bean);                   
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
                    Logger.getLogger(MantenimientoUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    
    public static String eliminarUsuarioCorrelativo(String clavePublica,UsuarioCorrelativo bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicUsuarioCorrelativo logic = new LogicUsuarioCorrelativo(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.eliminarUsuarioCorrelativo(cnx,bean);                   
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
                    Logger.getLogger(MantenimientoUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
}
