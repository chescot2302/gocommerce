/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.CategoriaLista;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicCategoriaLista;
import com.gocommerce.server.util.AESencrypt;
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
public class MantenimientoCategoriaLista {

    public static Boolean insertarCategoriaLista(CategoriaLista bean, String clavePublica) throws UnknownException {
        if (bean.getOperacion().equalsIgnoreCase("I") && bean.getId() == null) {
            BeanParametro parametro = new BeanParametro();
            PersistenceManager pm = null;
            Transaction tx = null;
            try {
                String keypublic = StringHex.convertHexToString(clavePublica);
                pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
                if (!pm.isClosed()) {
                    tx = pm.currentTransaction();
                    tx.begin();
                    Date fechaServer = new Date();
                    bean.setVersion(fechaServer.getTime());
                    parametro.setBean(bean);
                    parametro.setTipoOperacion(bean.getOperacion());
                    LogicCategoriaLista logic = new LogicCategoriaLista(pm);
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

    public static Boolean actualizarCategoriaLista(CategoriaLista bean, String clavePublica)
            throws UnknownException {
        if (bean.getOperacion().equalsIgnoreCase("A") && bean.getId() != null) {
            BeanParametro parametro = new BeanParametro();
            PersistenceManager pm = null;
            Transaction tx = null;
            try {
                String keypublic = StringHex.convertHexToString(clavePublica);
                pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
                if (!pm.isClosed()) {
                    tx = pm.currentTransaction();
                    tx.begin();
                    Date fechaServer = new Date();
                    LogicCategoriaLista logic = new LogicCategoriaLista(pm);
                    CategoriaLista beanUpdate = (CategoriaLista) logic.getBean(bean.getId());
                    beanUpdate.setVersion(fechaServer.getTime());
                    beanUpdate.setDescripcion(bean.getDescripcion());
                    beanUpdate.setOperacion(bean.getOperacion());
                    parametro.setBean(beanUpdate);
                    parametro.setTipoOperacion(beanUpdate.getOperacion());
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

    public static Boolean eliminarCategoriaLista(CategoriaLista bean, String clavePublica) throws UnknownException {
        if (bean.getOperacion().equalsIgnoreCase("E") && bean.getId() != null) {
            BeanParametro parametro = new BeanParametro();
            PersistenceManager pm = null;
            Transaction tx = null;
            try {
                String keypublic = StringHex.convertHexToString(clavePublica);
                pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
                if (!pm.isClosed()) {
                    tx = pm.currentTransaction();
                    tx.begin();
                    LogicCategoriaLista logic = new LogicCategoriaLista(pm);
                    parametro.setId(bean.getId());
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

    public static List<CategoriaLista> listar(String clavePublica) throws UnknownException {
        PersistenceManager pm = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicCategoriaLista logic = new LogicCategoriaLista(pm);
                List<CategoriaLista> lista = (List) logic.getListarBean();
                return lista;
            } else {
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }
        } catch (UnknownException ex) {
            System.err.println(ex.getMessage());
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
