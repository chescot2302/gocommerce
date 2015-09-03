/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.BdEmpresa;
import com.gocommerce.server.model.beans.CredencialSuperAdmin;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicBdEmpresa;
import com.gocommerce.server.process.gestionseguridad.LoginSuperAdmin;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.StringHex;
import com.gocommerce.server.util.UnknownException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
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
public class MantenimientoBdEmpresa {

    public static Boolean insertarBdEmpresa(BdEmpresa bean, String clavePublica) throws UnknownException {
        if (bean.getOperacion().equalsIgnoreCase("I") && bean.getIdBdEmpresa() == null) {
            BeanParametro parametro = new BeanParametro();
            PersistenceManager pm1 = null;
            PersistenceManager pm2 = null;
            Connection cnx = null;
            Transaction tx1 = null;
            CredencialSuperAdmin credencial = new CredencialSuperAdmin();
            credencial.setIp(bean.getIpHost());
            credencial.setBd(bean.getSchema());
            credencial.setPuerto(bean.getPuerto());
            credencial.setUsuario(bean.getUserPrincipal());
            credencial.setClave(bean.getClavePrincipal());
            credencial.setClavePublica(java.util.UUID.randomUUID().toString());
             HashMap<String,Object> map= LoginSuperAdmin.loginSuperAdmin(credencial);
             String keyPublicBd=map.get("keypublic").toString();
            keyPublicBd = StringHex.convertHexToString(keyPublicBd);
            PersistenceManager pm3 = PMF.getClassPMF().getPMF(keyPublicBd).getPersistenceManager();
            if (!pm3.isClosed()) {
                pm3.close();
                PMF.getClassPMF().destroyPMF(keyPublicBd);
                try {
                    String keypublic = StringHex.convertHexToString(clavePublica);
                    pm1 = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
                    pm2 = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
                    if (!pm1.isClosed() && !pm2.isClosed()) {
                        tx1 = pm1.currentTransaction();
                        tx1.begin();
                        LogicBdEmpresa logic1 = new LogicBdEmpresa(pm1);
                        Date fechaServer = new Date();
                        bean.setVersion(fechaServer.getTime());
                        parametro.setBean(bean);
                        parametro.setTipoOperacion(bean.getOperacion());
                        BdEmpresa beanE = (BdEmpresa) logic1.backOperBean(parametro);
                        Boolean resultado1 = false;
                        Boolean resultado2 = false;
                        if (beanE != null) {
                            resultado1 = true;
                            JDOConnection cnxJDO = pm2.getDataStoreConnection();
                            cnx = (Connection) cnxJDO.getNativeConnection();
                            cnx.setAutoCommit(false);
                            LogicBdEmpresa logic2 = new LogicBdEmpresa(pm2);
                            resultado2 = logic2.insertBean(cnx,beanE.getIdBdEmpresa(), bean.getNombre(), bean.getIpHost(), bean.getPuerto(), bean.getSchema(), bean.getUserPrincipal(), bean.getClavePrincipal());
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
                            Logger.getLogger(MantenimientoBdEmpresa.class.getName()).log(Level.SEVERE, null, ex);
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
                throw new UnknownException("Error en las credenciales");
            }

        } else {
            throw new UnknownException("Verifique Catalogo de Servicio");
        }
    }

    public static Boolean actualizarBdEmpresa(BdEmpresa bean, String clavePublica)
            throws UnknownException {
        if (bean.getOperacion().equalsIgnoreCase("A") && bean.getIdBdEmpresa() != null) {
            BeanParametro parametro = new BeanParametro();
            PersistenceManager pm = null;
            PersistenceManager pm2 = null;
            Connection cnx = null;
            Transaction tx = null;
            CredencialSuperAdmin credencial = new CredencialSuperAdmin();
            credencial.setIp(bean.getIpHost());
            credencial.setBd(bean.getSchema());
            credencial.setPuerto(bean.getPuerto());
            credencial.setUsuario(bean.getUserPrincipal());
            credencial.setClave(bean.getClavePrincipal());
            credencial.setClavePublica(java.util.UUID.randomUUID().toString());            
            HashMap<String,Object> map= LoginSuperAdmin.loginSuperAdmin(credencial);
             String keyPublicBd=map.get("keypublic").toString();
            keyPublicBd = StringHex.convertHexToString(keyPublicBd);
            PersistenceManager pm3 = PMF.getClassPMF().getPMF(keyPublicBd).getPersistenceManager();
            if (!pm3.isClosed()) {
                pm3.close();
                PMF.getClassPMF().destroyPMF(keyPublicBd);
                try {
                    String keypublic = StringHex.convertHexToString(clavePublica);
                    pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
                    pm2 = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
                    if (!pm.isClosed() && !pm2.isClosed()) {
                        tx = pm.currentTransaction();
                        tx.begin();
                        JDOConnection cnxJDO = pm2.getDataStoreConnection();
                        cnx = (Connection) cnxJDO.getNativeConnection();
                        cnx.setAutoCommit(false);
                        LogicBdEmpresa logic2 = new LogicBdEmpresa(pm2);
                        Boolean resultado2 = logic2.updateBean(cnx, bean.getNombre(), bean.getIpHost(), bean.getPuerto(), bean.getSchema(), bean.getUserPrincipal(), bean.getClavePrincipal());
                        Date fechaServer = new Date();
                        LogicBdEmpresa logic = new LogicBdEmpresa(pm);
                        BdEmpresa beanUpdate = (BdEmpresa) logic.getBean(bean.getIdBdEmpresa());
                        beanUpdate.setVersion(fechaServer.getTime());
                        beanUpdate.setOperacion(bean.getOperacion());
                        beanUpdate.setNombre(bean.getNombre());
                        beanUpdate.setIpHost(bean.getIpHost());
                        beanUpdate.setPuerto(bean.getPuerto());
                        beanUpdate.setSchema(bean.getSchema());
                        beanUpdate.setUserPrincipal(bean.getUserPrincipal());
                        beanUpdate.setClavePrincipal(bean.getClavePrincipal());
                        parametro.setBean(beanUpdate);
                        parametro.setTipoOperacion(beanUpdate.getOperacion());
                        Boolean resultado = logic.mantenimiento(parametro);
                        if (resultado && resultado2) {
                            tx.commit();
                            cnx.commit();
                            cnx.close();
                            pm.close();
                            pm2.close();
                            return true;
                        } else {
                            tx.rollback();
                            cnx.rollback();
                            cnx.close();
                            pm.close();
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
                            Logger.getLogger(MantenimientoBdEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                            throw new UnknownException(ex.getMessage());
                        }
                    }
                    if (!pm.isClosed()) {
                        if (tx.isActive()) {
                            tx.rollback();
                        }
                        pm.close();
                    }
                    if (!pm2.isClosed()) {
                        pm2.close();
                    }
                }
            } else {
                throw new UnknownException("Error en las credenciales");
            }
        } else {
            throw new UnknownException("Verifique Catalogo de Servicio");
        }
    }

    public static Boolean eliminarBdEmpresa(BdEmpresa bean, String clavePublica) throws UnknownException {
        if (bean.getOperacion().equalsIgnoreCase("E") && bean.getIdBdEmpresa() != null) {
            BeanParametro parametro = new BeanParametro();
            PersistenceManager pm = null;
            Transaction tx = null;
            try {
                String keypublic = StringHex.convertHexToString(clavePublica);
                pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
                if (!pm.isClosed()) {
                    tx = pm.currentTransaction();
                    tx.begin();
                    LogicBdEmpresa logic = new LogicBdEmpresa(pm);
                    parametro.setId(bean.getIdBdEmpresa());
                    parametro.setBean(bean);
                    parametro.setTipoOperacion(bean.getOperacion());
                    Boolean resultado = logic.mantenimiento(parametro);
                    if (resultado) {
                        tx.commit();
                        pm.close();
                        return true;
                    } else {
                        tx.rollback();
                        pm.close();
                        return false;
                    }
                } else {
                    throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                throw new UnknownException(ex.getMessage());
            } finally {
                if (!pm.isClosed()) {
                    if (tx.isActive()) {
                        tx.rollback();
                    }
                    pm.close();
                }
            }
        } else {
            throw new UnknownException("Verifique Catalogo de Servicio");
        }
    }

    public static List<BdEmpresa> listar(String clavePublica) throws UnknownException {
        PersistenceManager pm = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            LogicBdEmpresa logic = new LogicBdEmpresa(pm);
            return (List) logic.getListarBean();
        } catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }
}
