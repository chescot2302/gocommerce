/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.Param;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicParam;
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
public class MantenimientoParam {
    public static Boolean insertParam(Param bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            //tx.setIsolationLevel("repeatable-read");
            tx.begin();
            LogicParam logic = new LogicParam(pm);
            Date fechaServer = new Date();
            if (bean.getOperacion().equalsIgnoreCase("I") &&                
                bean.getIdParam()==null ) {                           	
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
    
    public static Boolean updateParam(Param bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            LogicParam logic = new LogicParam(pm);
            Date fechaServer = new Date();
            if (bean.getOperacion().equalsIgnoreCase("A")                                        
                && !bean.getIdParam().toString().isEmpty()
                && bean.getIdParam() != null) {
                Param beanUpdate=(Param)logic.getBean(bean.getIdParam());
                beanUpdate.setOperacion("A");
                beanUpdate.setAbreviatura(bean.getAbreviatura());
                beanUpdate.setDescripcion(bean.getDescripcion());                                
                beanUpdate.setValor(bean.getValor());
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
        
    
    public static Boolean deleteParamBD(Param bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            LogicParam logic = new LogicParam(pm);            
            if (
                    bean.getOperacion().equalsIgnoreCase("E")
                    && !bean.getIdParam().toString().isEmpty()
                    && bean.getIdParam() != null
                    ) {     
                parametro.setId(bean.getIdParam());
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
    
    public static Param getBean(String abreviatura,String clavePublica)throws UnknownException{
        PersistenceManager pm = null;
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            LogicParam logic = new LogicParam(pm);            
            return (Param)logic.getBean(abreviatura);
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static List<Param> listarParam(String clavePublica)throws UnknownException{
        PersistenceManager pm = null;
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            LogicParam logic = new LogicParam(pm);            
            return (List)logic.getListarBean();
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }       
    
    /** Fin de Mantenimiento Param*/
}
