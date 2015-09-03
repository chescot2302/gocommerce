/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.process.gestionventas;

import com.gocommerce.server.model.beans.CabeceraVenta;
import com.gocommerce.server.model.beans.DetalleVenta;
import com.gocommerce.server.model.beans.ItemPlan;
import com.gocommerce.server.model.beans.ItemSerie;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicCabeceraVenta;
import com.gocommerce.server.model.logic.LogicDetalleVenta;
import com.gocommerce.server.model.xmlstore.StoreCabeceraVenta;
import com.gocommerce.server.model.xmlstore.StoreDetalleVenta;
import com.gocommerce.server.model.xmlstore.StoreItemPlan;
import com.gocommerce.server.model.xmlstore.StoreItemSerie;
import com.gocommerce.server.util.StringHex;
import com.gocommerce.server.util.UnknownException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.datastore.JDOConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author SISTEMAS
 */
public class GestionFacturacion {
    
    public static String generarDocumento(String clavePublica,List<DetalleVenta> detalles,List<CabeceraVenta> cabecera,List<ItemSerie> series,Integer conDespacho,Integer conAsiento,Date fechaEmision,String idPuntoEmision)throws UnknownException{                
        PersistenceManager pm = null;
        Connection cnx=null;
        String resultado="ERROR";
        try {
            StoreDetalleVenta store=new StoreDetalleVenta();
            StoreCabeceraVenta storehead=new StoreCabeceraVenta();
            StoreItemSerie storeSerie=new StoreItemSerie();
            store.setLista(detalles);
            storehead.setLista(cabecera);            
            JAXBContext context = JAXBContext.newInstance(StoreDetalleVenta.class);
            JAXBContext context1 = JAXBContext.newInstance(StoreCabeceraVenta.class);            
            Marshaller m = context.createMarshaller();
            Marshaller m1 = context1.createMarshaller();            
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
            m.setProperty(Marshaller.JAXB_ENCODING , "ISO-8859-1");                        
            m.marshal(store, System.out);
            m1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m1.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
            m1.setProperty(Marshaller.JAXB_ENCODING , "ISO-8859-1");                        
            m1.marshal(storehead, System.out);                                                
            Writer writer = new StringWriter();
            Writer writer1 = new StringWriter();            
            m.marshal(store, writer);
            String strDetalles = writer.toString(); 
            writer.close();             
            m1.marshal(storehead, writer1);
            String strCabecera=writer1.toString();
            writer1.close(); 
            String strSeries="";
            if(series.size()>0){
            storeSerie.setLista(series);
            JAXBContext context2 = JAXBContext.newInstance(StoreItemSerie.class);
            Marshaller m2 = context2.createMarshaller();            
            m2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m2.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
            m2.setProperty(Marshaller.JAXB_ENCODING , "ISO-8859-1");                        
            m2.marshal(storeSerie, System.out);
            Writer writer2 = new StringWriter();
            m2.marshal(storeSerie, writer2);
            strSeries=writer2.toString();
            writer2.close();
            }            
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicCabeceraVenta logic = new LogicCabeceraVenta(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.generarDocumentoVenta(cnx,strDetalles,strCabecera,strSeries,conDespacho,conAsiento,fechaEmision,idPuntoEmision);   
                resultado=array.get(0);
                if(resultado.equalsIgnoreCase("CORRECTO")){
                cnx.commit();
                cnx.close();                
                }                
            }else{
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }             
            return resultado;
        } catch (JAXBException ex) {            
            throw new UnknownException(ex.getMessage());
        } catch (IOException ex) {            
            throw new UnknownException(ex.getMessage());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        }finally{
            if(cnx!=null){
                try {
                    if(!cnx.isClosed()){
                        cnx.rollback();
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GestionFacturacion.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }            
        }
    }
    
    public static String registrarVentaCe(String clavePublica,List<DetalleVenta> detalles,List<CabeceraVenta> cabecera,List<ItemPlan> equipos,Date fechaEmision,String idPuntoEmision)throws UnknownException{                
        PersistenceManager pm = null;
        Connection cnx=null;
        String resultado="ERROR";
        try {
            StoreDetalleVenta store=new StoreDetalleVenta();
            StoreCabeceraVenta storehead=new StoreCabeceraVenta();            
            StoreItemPlan storeItemPlan=new StoreItemPlan();
            store.setLista(detalles);
            storehead.setLista(cabecera);            
            JAXBContext context = JAXBContext.newInstance(StoreDetalleVenta.class);
            JAXBContext context1 = JAXBContext.newInstance(StoreCabeceraVenta.class);            
            Marshaller m = context.createMarshaller();
            Marshaller m1 = context1.createMarshaller();            
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
            m.setProperty(Marshaller.JAXB_ENCODING , "ISO-8859-1");                        
            m.marshal(store, System.out);
            m1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m1.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
            m1.setProperty(Marshaller.JAXB_ENCODING , "ISO-8859-1");                        
            m1.marshal(storehead, System.out);                                                
            Writer writer = new StringWriter();
            Writer writer1 = new StringWriter();            
            m.marshal(store, writer);
            String strDetalles = writer.toString(); 
            writer.close();             
            m1.marshal(storehead, writer1);
            String strCabecera=writer1.toString();
            writer1.close();   
            String strEquipos="";
            if(equipos.size()>0){
            storeItemPlan.setLista(equipos);
            JAXBContext context2 = JAXBContext.newInstance(StoreItemPlan.class);
            Marshaller m2 = context2.createMarshaller();            
            m2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m2.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
            m2.setProperty(Marshaller.JAXB_ENCODING , "ISO-8859-1");                        
            m2.marshal(storeItemPlan, System.out);
            Writer writer2 = new StringWriter();
            m2.marshal(storeItemPlan, writer2);
            strEquipos=writer2.toString();
            writer2.close();
            }
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicCabeceraVenta logic = new LogicCabeceraVenta(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                cnx.setAutoCommit(false);
                List<String> array=(List)logic.registrarVentaCe(cnx,strDetalles,strCabecera,fechaEmision,idPuntoEmision,strEquipos);   
                resultado=array.get(0);
                if(resultado.equalsIgnoreCase("CORRECTO")){
                cnx.commit();
                cnx.close();                
                }                
            }else{
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }             
            return resultado;
        } catch (JAXBException ex) {            
            throw new UnknownException(ex.getMessage());
        } catch (IOException ex) {            
            throw new UnknownException(ex.getMessage());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        }finally{
            if(cnx!=null){
                try {
                    if(!cnx.isClosed()){
                        cnx.rollback();
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GestionFacturacion.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }            
        }
    }
    
    public static List<CabeceraVenta> listarVentas(
            String clavePublica,
            Date fechaIni,
            Date fechaFin,
            String rucCliente,
            String descrCliente,
            String docSeries,
            String correlativo,            
            String tas,
            String excluirFecha
    ) throws UnknownException{
        PersistenceManager pm = null;
        Connection cnx=null;
        try{
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicCabeceraVenta logic = new LogicCabeceraVenta(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                List<CabeceraVenta> lista=(List)logic.listarVentas(cnx, fechaIni, fechaFin, rucCliente, descrCliente, docSeries, correlativo, tas, excluirFecha);  
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
                    Logger.getLogger(GestionFacturacion.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static List<CabeceraVenta> listarVentasCe(
            String clavePublica,
            Date fechaIni,
            Date fechaFin,
            String rucCliente,
            String descrCliente,
            String docSeries,
            String correlativo,            
            String tas,
            String excluirFecha
    ) throws UnknownException{
        PersistenceManager pm = null;
        Connection cnx=null;
        try{
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicCabeceraVenta logic = new LogicCabeceraVenta(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                List<CabeceraVenta> lista=(List)logic.listarVentasCe(cnx, fechaIni, fechaFin, rucCliente, descrCliente, docSeries, correlativo, tas, excluirFecha);  
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
                    Logger.getLogger(GestionFacturacion.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static List<DetalleVenta> getDetalleVenta(
            String clavePublica,
            String tipoDoc,
            String correlativo
    ) throws UnknownException{
        PersistenceManager pm = null;
        Connection cnx=null;
        try{
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicDetalleVenta logic = new LogicDetalleVenta(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                List<DetalleVenta> lista=(List)logic.getDetalleVenta(cnx, tipoDoc, correlativo);
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
                    Logger.getLogger(GestionFacturacion.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
    
    public static List<DetalleVenta> getDetalleVentaCe(
            String clavePublica,
            String tipoDoc,
            String correlativo
    ) throws UnknownException{
        PersistenceManager pm = null;
        Connection cnx=null;
        try{
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicDetalleVenta logic = new LogicDetalleVenta(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx =(Connection)cnxJDO.getNativeConnection();                
                List<DetalleVenta> lista=(List)logic.getDetalleVentaCe(cnx, tipoDoc, correlativo);
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
                    Logger.getLogger(GestionFacturacion.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
}
