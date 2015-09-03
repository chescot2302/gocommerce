/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.MenuBar;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicMenuBar;
import com.gocommerce.server.model.xmlstore.StoreMenuBar;
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

/**
 *
 * @author SISTEMAS
 */
public class MantenimientoMenuBar {

    public static List<MenuBar> listar(String clavePublica) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicMenuBar logic = new LogicMenuBar(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                List<MenuBar> lista = (List) logic.getListarBean(cnx);
                //MenuBar root=createTree(lista.get(0),lista);
                /*for (int i = 0; i < lista.size(); i++) {
                 MenuBar beanPadre = lista.get(i);
                 int cont = 0;
                 for (int j = 0; j < lista.size(); j++) {
                 MenuBar beanHijo = lista.get(j);
                 if (beanPadre.getIdMenuBar() == beanHijo.getIdMenuPadre()) {
                 beanPadre.setHijo(beanHijo);
                 cont = cont + 1;
                 }
                 if (cont == beanPadre.getNumSubMenu()) {
                 break;
                 }
                 }
                 }*/
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
                    Logger.getLogger(MantenimientoMenuBar.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }

    /*public static MenuBar createTree(MenuBar beanPadre, List<MenuBar> lista) {
     int cont = 0;
     for (int j = 0; j < lista.size(); j++) {
     MenuBar beanHijo = lista.get(j);            
     if (beanPadre.getIdMenuBar() == beanHijo.getIdMenuPadre()) {
     beanPadre.setHijo(beanHijo);
     createTree(beanHijo, lista);
     cont = cont + 1;
     }
     if (cont == beanPadre.getNumSubMenu()) {
     break;
     }
     }
     return beanPadre;

     }*/
    public static List<MenuBar> listarXusuario(String clavePublica, Integer idBdUsuario,String esquema) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicMenuBar logic = new LogicMenuBar(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                cnx.setAutoCommit(false);
                List<MenuBar> lista = (List) logic.getListarBeanXusuario(cnx, idBdUsuario,esquema);
                cnx.commit();
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
                        cnx.rollback();
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MantenimientoMenuBar.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }

    public static Boolean updateGrantMenusuario(String clavePublica, Set<MenuBar> lista,String esquema) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            StoreMenuBar store = new StoreMenuBar();
            store.setLista(lista);
            JAXBContext context = JAXBContext.newInstance(StoreMenuBar.class);
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
                LogicMenuBar logic = new LogicMenuBar(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                cnx.setAutoCommit(false);
                List<String> array = (List) logic.InsertGrantMenusuario(cnx, data,esquema);
                System.out.println(array.get(0));
                cnx.commit();
                cnx.close();
            } else {
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }
            return true;
        } catch (JAXBException ex) {
            Logger.getLogger(MantenimientoMenuBar.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(MantenimientoMenuBar.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(MantenimientoMenuBar.class.getName()).log(Level.SEVERE, null, ex);
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
