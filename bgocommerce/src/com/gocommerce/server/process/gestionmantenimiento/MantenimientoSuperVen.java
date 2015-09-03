/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.SuperVen;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicSuperVen;
import com.gocommerce.server.model.xmlstore.StoreSuperVen;
import com.gocommerce.server.util.StringHex;
import com.gocommerce.server.util.UnknownException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.datastore.JDOConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

/**
 *
 * @author SISTEMAS
 */
public class MantenimientoSuperVen {
    public static List<SuperVen> listar(String clavePublica, Integer idMesa, String estado) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicSuperVen logic = new LogicSuperVen(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                List<SuperVen> lista = (List) logic.getListarBean(cnx, idMesa, estado);
                cnx.close();
                return lista;
            } else {
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }
        } catch (UnknownException ex) {
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
                    Logger.getLogger(MantenimientoSuperVen.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }
    
    public static List<SuperVen> consultorSinMesa(String clavePublica) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicSuperVen logic = new LogicSuperVen(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                List<SuperVen> lista = (List) logic.consultorSinMesa(cnx);
                cnx.close();
                return lista;
            } else {
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }
        } catch (UnknownException ex) {
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
                    Logger.getLogger(MantenimientoSuperVen.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }
    
    public static Boolean insertarSuperVen(String clavePublica, Set<SuperVen> lista) throws UnknownException, PropertyException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            StoreSuperVen store = new StoreSuperVen();
            store.setLista(lista);
            JAXBContext context = JAXBContext.newInstance(StoreSuperVen.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.marshal(store, System.out);
            Writer writer = new StringWriter();
            m.marshal(store, writer);
            String data = writer.toString();
            writer.close();
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicSuperVen logic = new LogicSuperVen(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                cnx.setAutoCommit(false);
                List<String> array = (List) logic.insertarSuperVen(cnx, data);
                System.out.println(array.get(0));
                cnx.commit();
                cnx.close();
            } else {
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }
            return true;
        } catch (JAXBException ex) {
            Logger.getLogger(MantenimientoSuperVen.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(MantenimientoSuperVen.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        } catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if (cnx != null) {
                try {
                    if (!cnx.isClosed()) {
                        cnx.rollback();
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MantenimientoSuperVen.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }
    
    public static Boolean desactivarSuperVen(String clavePublica, Set<SuperVen> lista) throws UnknownException, PropertyException {
        PersistenceManager pm = null;
        Connection cnx=null;
        try {
            StoreSuperVen store = new StoreSuperVen();
            store.setLista(lista);
            JAXBContext context = JAXBContext.newInstance(StoreSuperVen.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //m.marshal(store, System.out);
            Writer writer = new StringWriter();
            m.marshal(store, writer);
            String data = writer.toString();
            writer.close();
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicSuperVen logic = new LogicSuperVen(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                cnx.setAutoCommit(false);
                List<String> array = (List) logic.desactivarSuperVen(cnx, data);
                System.out.println(array.get(0));
                cnx.commit();
                cnx.close();
            } else {
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }
            return true;
        } catch (JAXBException ex) {
            Logger.getLogger(MantenimientoPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(MantenimientoPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        } catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
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
    
    public static Boolean eliminarSuperVen(String clavePublica, Set<SuperVen> lista) throws UnknownException, PropertyException {
        PersistenceManager pm = null;
        Connection cnx=null;
        try {
            StoreSuperVen store = new StoreSuperVen();
            store.setLista(lista);
            JAXBContext context = JAXBContext.newInstance(StoreSuperVen.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //m.marshal(store, System.out);
            Writer writer = new StringWriter();
            m.marshal(store, writer);
            String data = writer.toString();
            writer.close();
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicSuperVen logic = new LogicSuperVen(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                cnx.setAutoCommit(false);
                List<String> array = (List) logic.eliminarSuperVen(cnx, data);
                System.out.println(array.get(0));
                cnx.commit();
                cnx.close();
            } else {
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }
            return true;
        } catch (JAXBException ex) {
            Logger.getLogger(MantenimientoPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(MantenimientoPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        } catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
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
}
