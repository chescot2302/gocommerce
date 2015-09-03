/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.ActivaBdUsuario;
import com.gocommerce.server.model.beans.BdEmpresa;
import com.gocommerce.server.model.beans.BdUsuario;
import com.gocommerce.server.model.beans.DataSesion;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicBdEmpresa;
import com.gocommerce.server.model.logic.LogicBdUsuario;
import com.gocommerce.server.model.logic.LogicDataSesion;
import com.gocommerce.server.model.xmlstore.StoreDataSesion;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.StringHex;
import com.gocommerce.server.util.UnknownException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import javax.jdo.datastore.JDOConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author SISTEMAS
 */
public class MantenimientoBdUsuario {

    public static List<DataSesion> getConexionUser(String clavePublica, Integer idBdUsuario,String loginName) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            HashMap<String, DataSesion> map = PMF.getClassPMF().getMapSesion();
            Set<String> keys = map.keySet();
            Iterator<String> i = keys.iterator();
            List<DataSesion> data = new ArrayList();
            while (i.hasNext()) {
                DataSesion beanSesion = map.get(i.next());
                if (beanSesion.getIdUsuarioBd().equals(idBdUsuario)) {
                    data.add(beanSesion);
                }
            }
            StoreDataSesion store = new StoreDataSesion();
            store.setLista(data);
            JAXBContext context = JAXBContext.newInstance(StoreDataSesion.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
            m.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
            m.marshal(store, System.out);
            Writer writer = new StringWriter();
            m.marshal(store, writer);
            String strSesion = writer.toString();
            writer.close();
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicDataSesion logic = new LogicDataSesion(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                List<DataSesion> lista = (List) logic.listarDataSesion(cnx,strSesion,loginName);
                cnx.close();
                return lista;
            } else {
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }
        } catch (JAXBException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (IOException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        }finally {
            if (cnx != null) {
                try {
                    if (!cnx.isClosed()) {
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MantenimientoBdUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }

    public static Boolean insertarBdUsuario(BdUsuario bean, String clavePublica) throws UnknownException {
        if (bean.getOperacion().equalsIgnoreCase("I") && bean.getIdBdUsuario() == null) {
            BeanParametro parametro = new BeanParametro();
            PersistenceManager pm1 = null;
            PersistenceManager pm2 = null;
            Transaction tx1 = null;
            Connection cnx = null;
            try {
                String keypublic = StringHex.convertHexToString(clavePublica);
                pm1 = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
                pm2 = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
                if (!pm1.isClosed() && !pm2.isClosed()) {
                    tx1 = pm1.currentTransaction();
                    tx1.begin();
                    Date fechaServer = new Date();
                    LogicBdEmpresa logic3 = new LogicBdEmpresa(pm1);
                    BdEmpresa beanEmpresa = (BdEmpresa) logic3.getBean(bean.getIdBdEmpresa());
                    bean.setVersion(fechaServer.getTime());
                    bean.setBeanBdEmpresa(beanEmpresa);
                    ActivaBdUsuario beanActiva = new ActivaBdUsuario();
                    beanActiva.setEstadoActivacion("A");
                    beanActiva.setFechaIni(fechaServer);
                    beanActiva.setVersion(fechaServer.getTime());
                    beanActiva.setBeanBdUsuario(bean);
                    bean.getListActivaBdUsuario().add(beanActiva);
                    beanEmpresa.getListBdUsuario().add(bean);
                    parametro.setBean(bean);
                    parametro.setTipoOperacion("A");
                    LogicBdUsuario logic1 = new LogicBdUsuario(pm1);
                    BdUsuario beanU = (BdUsuario) logic1.backOperBean(parametro);
                    Boolean resultado1 = false;
                    Boolean resultado2 = false;
                    if (beanU != null) {
                        resultado1 = true;
                        JDOConnection cnxJDO = pm2.getDataStoreConnection();
                        cnx = (Connection) cnxJDO.getNativeConnection();
                        cnx.setAutoCommit(false);
                        LogicBdUsuario logic2 = new LogicBdUsuario(pm2);
                        resultado2 = logic2.insertBean(cnx, beanU.getIdBdUsuario(), bean.getIdBdEmpresa(), bean.getSchema(), bean.getNivel(), bean.getCorreo(), bean.getClave(), bean.getUsuarioBd(), bean.getClaveBd(), new java.sql.Date(fechaServer.getTime()));
                    }
                    if (resultado1 && resultado2) {
                        tx1.commit();
                        cnx.commit();
                        cnx.close();
                        pm1.close();
                        pm2.close();
                        return true;
                    } else {
                        tx1.rollback();
                        cnx.rollback();
                        cnx.close();
                        pm1.close();
                        pm2.close();
                        return false;
                    }
                } else {
                    throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                throw new UnknownException(ex.getMessage());
            } finally {
                if (cnx != null) {
                    try {
                        if (!cnx.isClosed()) {
                            cnx.rollback();
                            cnx.close();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MantenimientoBdUsuario.class.getName()).log(Level.SEVERE, null, ex);
                        throw new UnknownException(ex.getMessage());
                    }
                }
                if (!pm1.isClosed()) {
                    if (tx1.isActive()) {
                        tx1.rollback();
                    }
                    pm1.close();
                }
                if (!pm2.isClosed()) {
                    pm2.close();
                }
            }
        } else {
            throw new UnknownException("Verifique Catalogo de Servicio");
        }
    }

    public static List<BdUsuario> listar(String clavePublica) throws UnknownException {
        PersistenceManager pm = null;
        Transaction tx = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            PMF.getClassPMF().getPMF(keypublic).getFetchGroup(BdUsuario.class, "BdUsuarioGroup").addMember("beanBdEmpresa");
            pm.getFetchPlan().addGroup("BdUsuarioGroup");
            pm.getFetchPlan().setMaxFetchDepth(1);
            tx = pm.currentTransaction();
            tx.begin();
            pm.setDetachAllOnCommit(true);
            LogicBdUsuario logic = new LogicBdUsuario(pm);
            List<BdUsuario> result = (List<BdUsuario>) pm.detachCopyAll(logic.getListarBean());
            tx.commit();
            return result;
        } catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (Exception ex) {
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

    public static BdUsuario getBdUsuario(String clavePublica, String schema, String usuarioBd, String nivel, String usuarioLog) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicBdUsuario logic = new LogicBdUsuario(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                BdUsuario bean = logic.getBean(cnx, schema, usuarioBd, nivel, usuarioLog);
                cnx.close();
                return bean;
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
                    Logger.getLogger(MantenimientoBdUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }
}
