/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.CategoriaLista;
import com.gocommerce.server.model.beans.ListaPrecio;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicCategoriaLista;
import com.gocommerce.server.model.logic.LogicListaPrecio;
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
public class MantenimientoListaPrecio {
    public static Boolean insertarListaPrecio(ListaPrecio bean, String clavePublica) throws UnknownException {
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
                    LogicCategoriaLista logicCategoria=new LogicCategoriaLista(pm);                    
                    Date fechaServer = new Date();
                    bean.setVersion(fechaServer.getTime());
                    bean.setFechaIni(fechaServer);
                    bean.setEstadoActiva("A");
                    bean.setBeanCategoriaLista((CategoriaLista)logicCategoria.getBean(bean.getIdCategoriaLista()));
                    parametro.setBean(bean);
                    parametro.setTipoOperacion(bean.getOperacion());
                    LogicListaPrecio logic = new LogicListaPrecio(pm);
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

    public static Boolean actualizarListaPrecio(ListaPrecio bean, String clavePublica)
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
                    LogicCategoriaLista logicCategoria=new LogicCategoriaLista(pm);                    
                    Date fechaServer = new Date();
                    LogicListaPrecio logic = new LogicListaPrecio(pm);
                    ListaPrecio beanUpdate = (ListaPrecio) logic.getBean(bean.getId());
                    beanUpdate.setVersion(fechaServer.getTime());
                    beanUpdate.setDescripcion(bean.getDescripcion());                    
                    beanUpdate.setCondicion(bean.getCondicion());
                    beanUpdate.setPagoMensual(bean.getPagoMensual());
                    beanUpdate.setVigencia(bean.getVigencia());
                    beanUpdate.setOperacion(bean.getOperacion());                    
                    beanUpdate.setBeanCategoriaLista((CategoriaLista)logicCategoria.getBean(bean.getIdCategoriaLista()));
                    beanUpdate.setCanal(bean.getCanal());
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
    
    public static Boolean desactivarListaPrecio(ListaPrecio bean, String clavePublica)
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
                    LogicListaPrecio logic = new LogicListaPrecio(pm);
                    ListaPrecio beanUpdate = (ListaPrecio) logic.getBean(bean.getId());
                    beanUpdate.setVersion(fechaServer.getTime());                    
                    beanUpdate.setEstadoActiva("D");
                    beanUpdate.setFechaFin(fechaServer);
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

    public static Boolean eliminarListaPrecio(ListaPrecio bean, String clavePublica) throws UnknownException {
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
                    LogicListaPrecio logic = new LogicListaPrecio(pm);
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

    public static List<ListaPrecio> listar(String clavePublica) throws UnknownException {
        PersistenceManager pm = null;
        Transaction tx = null;
        try {
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            PMF.getClassPMF().getPMF(keypublic).getFetchGroup(ListaPrecio.class, "ListaPrecioGroup").addMember("beanCategoriaLista");
            pm.getFetchPlan().addGroup("ListaPrecioGroup");
            pm.getFetchPlan().setMaxFetchDepth(1);
            tx = pm.currentTransaction();
            tx.begin();
            pm.setDetachAllOnCommit(true);
            if (!pm.isClosed()) {
                LogicListaPrecio logic = new LogicListaPrecio(pm);
                List<ListaPrecio> lista = (List<ListaPrecio>) pm.detachCopyAll( logic.getListarBean());                                
                tx.commit();
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
