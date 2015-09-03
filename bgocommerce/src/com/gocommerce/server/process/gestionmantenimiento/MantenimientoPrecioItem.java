/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.PrecioItem;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicPrecioItem;
import com.gocommerce.server.model.xmlstore.StorePrecioItem;
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
public class MantenimientoPrecioItem {

    public static List<PrecioItem> listar(String clavePublica, Integer idLista, String estado) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicPrecioItem logic = new LogicPrecioItem(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                List<PrecioItem> lista = (List) logic.getListarBean(cnx, idLista, estado);
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
                    Logger.getLogger(MantenimientoPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }

    public static List<PrecioItem> listaPrecioxItem(String clavePublica, String idItem, String idMoneda,String canal) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicPrecioItem logic = new LogicPrecioItem(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                List<PrecioItem> lista = (List) logic.listaPrecioxItem(cnx, idItem, idMoneda,canal);
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
                    Logger.getLogger(MantenimientoPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }

    public static List<PrecioItem> itemSinLista(String clavePublica, Integer idLista) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicPrecioItem logic = new LogicPrecioItem(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                List<PrecioItem> lista = (List) logic.getItemSinLista(cnx, idLista);
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
                    Logger.getLogger(MantenimientoPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }
    
    public static List<PrecioItem> itemSinListaCategoria(String clavePublica, Integer idLista,String codFam,String descripcion) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicPrecioItem logic = new LogicPrecioItem(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                List<PrecioItem> lista = (List) logic.getItemSinListaCategoria(cnx, idLista,codFam,descripcion);
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
                    Logger.getLogger(MantenimientoPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }
    

    public static Boolean insertarPrecioItem(String clavePublica, Set<PrecioItem> lista) throws UnknownException, PropertyException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            StorePrecioItem store = new StorePrecioItem();
            store.setLista(lista);
            JAXBContext context = JAXBContext.newInstance(StorePrecioItem.class);
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
                LogicPrecioItem logic = new LogicPrecioItem(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                cnx.setAutoCommit(false);
                List<String> array = (List) logic.insertarPrecioItem(cnx, data);
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

    public static Boolean eliminarPrecioItem(String clavePublica, Set<PrecioItem> lista) throws UnknownException, PropertyException {
        PersistenceManager pm = null;
        Connection cnx=null;
        try {
            StorePrecioItem store = new StorePrecioItem();
            store.setLista(lista);
            JAXBContext context = JAXBContext.newInstance(StorePrecioItem.class);
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
                LogicPrecioItem logic = new LogicPrecioItem(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                cnx.setAutoCommit(false);
                List<String> array = (List) logic.eliminarPrecioItem(cnx, data);
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

    public static Boolean desactivarPrecioItem(String clavePublica, Set<PrecioItem> lista) throws UnknownException, PropertyException {
        PersistenceManager pm = null;
        Connection cnx=null;
        try {
            StorePrecioItem store = new StorePrecioItem();
            store.setLista(lista);
            JAXBContext context = JAXBContext.newInstance(StorePrecioItem.class);
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
                LogicPrecioItem logic = new LogicPrecioItem(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                cnx.setAutoCommit(false);
                List<String> array = (List) logic.desactivarPrecioItem(cnx, data);
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

    public static Boolean actualizarPrecioItem(String clavePublica, Set<PrecioItem> lista) throws UnknownException, PropertyException {
        PersistenceManager pm = null;
        Connection cnx=null;
        try {
            StorePrecioItem store = new StorePrecioItem();
            store.setLista(lista);
            JAXBContext context = JAXBContext.newInstance(StorePrecioItem.class);
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
                LogicPrecioItem logic = new LogicPrecioItem(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                cnx.setAutoCommit(false);
                List<String> array = (List) logic.actualizarPrecioItem(cnx, data);
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
