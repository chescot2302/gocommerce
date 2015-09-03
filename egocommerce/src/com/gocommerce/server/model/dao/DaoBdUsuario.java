package com.gocommerce.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.gocommerce.server.model.beans.BdUsuario;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.Parametro;
import com.gocommerce.server.util.UnknownException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoBdUsuario {

    private PersistenceManager pm;

    public DaoBdUsuario(PersistenceManager pm) {
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
        return query.getBean(BdUsuario.class, id);
    }

    @SuppressWarnings("unchecked")
    public Collection<BdUsuario> getListarBean() throws UnknownException {
        Querys query = new Querys(this.pm);
        Collection<BdUsuario> lista = (Collection<BdUsuario>) query
                .getListaBean(BdUsuario.class);
        return lista;
    }

    @SuppressWarnings("unchecked")
    public Collection<BdUsuario> getListarBean(String nivel, String bd, String usuario, String clave) throws UnknownException {
        Query query = pm.newQuery(BdUsuario.class);
        query.declareParameters("String paramNivel,String paramBd,String paramUsuario,String paramClave,String paramEstado");
        query.setFilter("nivel==paramNivel && schema==paramBd &&correo==paramUsuario && clave==paramClave && estadoActivacion==paramEstado");
        Collection<BdUsuario> lista = (Collection<BdUsuario>) query.executeWithArray(nivel, bd, usuario, clave, "A");
        return lista;
    }

    @SuppressWarnings("unchecked")
    public Collection<BdUsuario> getListarBean(String estado)
            throws UnknownException {
        Query query = pm.newQuery(BdUsuario.class);
        query.setOrdering("idBdEmpresa desc");
        Collection<BdUsuario> lista;
        switch (estado) {
            case "T":
                lista = (Collection<BdUsuario>) query.execute();
                break;
            case "A":
            case "D":
                query.setFilter("estadoactivacion==paramEstado");
                query.declareParameters("String paramEstado");
                lista = (Collection<BdUsuario>) query.execute(estado);
                break;
            default:
                query.setFilter("estadoactivacion==paramEstado");
                query.declareParameters("String paramEstado");
                lista = (Collection<BdUsuario>) query.execute("A");
                break;
        }
        return lista;
    }

    public ArrayList insertBean(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call insertBdUsuario(?,?,?,?,?,?,?,?,?)}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoBdUsuario.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList getBean(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call getBdUsuario(?,?,?,?)}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoBdUsuario.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
