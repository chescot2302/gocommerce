/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.model.dao;

import com.gocommerce.server.model.beans.BdEmpresa;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.Parametro;
import com.gocommerce.server.util.UnknownException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 *
 * @author SISTEMAS
 */
public class DaoBdEmpresa {

    private PersistenceManager pm;

    public DaoBdEmpresa(PersistenceManager pm) {
        this.pm = pm;
    }

    public boolean mantenimiento(BeanParametro parametro)
            throws UnknownException {
        Querys query = new Querys(this.pm);
        return query.mantenimiento(parametro);
    }
    
    public Object backOperBean(BeanParametro parametro)
            throws UnknownException {
        Querys query = new Querys(this.pm);
        return query.backOperBean(parametro);
    }

    public Object getBean(Integer id) throws UnknownException {
        Querys query = new Querys(this.pm);
        return query.getBean(BdEmpresa.class, id);
    }

    @SuppressWarnings("unchecked")
    public Collection<BdEmpresa> getListarBean() throws UnknownException {
        Querys query = new Querys(this.pm);
        Collection<BdEmpresa> lista = (Collection<BdEmpresa>) query
                .getListaBean(BdEmpresa.class);
        return lista;
    }

    public Collection<BdEmpresa> getListarBean(String estado) throws UnknownException {
        Query query = pm.newQuery(BdEmpresa.class);
        query.setOrdering("idBdEmpresa desc");
        Collection<BdEmpresa> lista;
        switch (estado) {
            case "T":
                lista = (Collection<BdEmpresa>) query.execute();
                break;
            case "A":
            case "D":
                query.setFilter("estadoactivacion==paramEstado");
                query.declareParameters("String paramEstado");
                lista = (Collection<BdEmpresa>) query.execute(estado);
                break;
            default:
                query.setFilter("estadoactivacion==paramEstado");
                query.declareParameters("String paramEstado");
                lista = (Collection<BdEmpresa>) query.execute("A");
                break;
        }
        return lista;
    }

    public ArrayList insertBean(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call insertBdEmpresa(?,?,?,?,?,?,?)}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    
    public ArrayList updateBean(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call updateBdEmpresa(?,?,?,?,?,?)}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }

}
