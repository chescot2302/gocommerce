/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.GerenteZonal;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicGerenteZonal;
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
public class MantenimientoGerZonal {
    public static List<GerenteZonal> listarGerenteZonal(String clavePublica,String estado) throws UnknownException{
        PersistenceManager pm = null;
        Connection cnx=null;
        try{
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicGerenteZonal logic = new LogicGerenteZonal(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                List<GerenteZonal> lista=(List)logic.getListarBean(cnx,estado);  
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
                    Logger.getLogger(MantenimientoGerZonal.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static String insertarGerenteZonal(GerenteZonal bean, String clavePublica) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicGerenteZonal logic = new LogicGerenteZonal(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.insertarGerenteZonal(cnx,bean);                   
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
                    Logger.getLogger(MantenimientoGerZonal.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static String actualizarGerenteZonal(String clavePublica,GerenteZonal bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicGerenteZonal logic = new LogicGerenteZonal(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();                
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.actualizarGerenteZonal(cnx,bean);                   
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
                    Logger.getLogger(MantenimientoGerZonal.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static String actDesGerenteZonal(String clavePublica,GerenteZonal bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicGerenteZonal logic = new LogicGerenteZonal(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();                
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.actDesGerenteZonal(cnx,bean);                   
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
                    Logger.getLogger(MantenimientoGerZonal.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    
    public static String eliminarGerenteZonal(String clavePublica,GerenteZonal bean)throws UnknownException{        
        PersistenceManager pm = null;
        Connection cnx=null;
        try {         
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicGerenteZonal logic = new LogicGerenteZonal(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.eliminarGerenteZonal(cnx,bean);                   
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
                    Logger.getLogger(MantenimientoGerZonal.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    /** Fin de Mantenimiento GerenteZonal*/
}
