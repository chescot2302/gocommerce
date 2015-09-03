/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.Localidad;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicLocalidad;
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
public class MantenimientoLocalidad {
    public static Boolean insertLocalidad(Localidad bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            //tx.setIsolationLevel("repeatable-read");
            tx.begin();
            LogicLocalidad logic = new LogicLocalidad(pm);
            Date fechaServer = new Date();
            if (bean.getOperacion().equalsIgnoreCase("I") &&                
                bean.getIdLocalidad()==null ) {                           	
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
    
    public static Boolean updateLocalidad(Localidad bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            LogicLocalidad logic = new LogicLocalidad(pm);
            Date fechaServer = new Date();
            if (bean.getOperacion().equalsIgnoreCase("A")                                        
                && !bean.getIdLocalidad().toString().isEmpty()
                && bean.getIdLocalidad() != null) {
                Localidad beanUpdate=(Localidad)logic.getBean(bean.getIdLocalidad());
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
        
    
    public static Boolean deleteLocalidadBD(Localidad bean,String clavePublica) throws UnknownException{
        BeanParametro parametro = new BeanParametro();
        PersistenceManager pm = null;
        Transaction tx = null;        
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            LogicLocalidad logic = new LogicLocalidad(pm);            
            if (
                    bean.getOperacion().equalsIgnoreCase("E")
                    && !bean.getIdLocalidad().toString().isEmpty()
                    && bean.getIdLocalidad() != null
                    ) {     
                parametro.setId(bean.getIdLocalidad());
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
    
    public static List<Localidad> listarLocalidad(String clavePublica)throws UnknownException{
        PersistenceManager pm = null;
        try{
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            LogicLocalidad logic = new LogicLocalidad(pm);            
            return (List)logic.getListarBean();
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }       
    
    /** Fin de Mantenimiento Localidad*/
}
