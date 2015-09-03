/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.model.dao;

import com.gocommerce.server.model.beans.ClienteCallCenter;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.UnknownException;
import java.util.Collection;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 *
 * @author SISTEMAS
 */
public class DaoClienteCallCenter {

    private PersistenceManager pm;

    public DaoClienteCallCenter(PersistenceManager pm) {
        this.pm = pm;
    }

    public boolean mantenimiento(BeanParametro parametro)
            throws UnknownException {
        Querys query = new Querys(this.pm);
        return query.mantenimiento(parametro);
    }

    public Object getBean(Integer id) throws UnknownException {
        Querys query = new Querys(this.pm);
        return query.getBean(ClienteCallCenter.class, id);
    }

    public Object getBean(String dni) throws UnknownException {
        Query query = pm.newQuery(ClienteCallCenter.class);
        Collection<ClienteCallCenter> lista;
        query.setFilter("dni==paramDni");
        query.declareParameters("String paramDni");
        lista = (Collection<ClienteCallCenter>) query.execute(dni);
        return lista;
    }

    @SuppressWarnings("unchecked")
    public Collection<ClienteCallCenter> getListarBean() throws UnknownException {
        Querys query = new Querys(this.pm);
        Collection<ClienteCallCenter> lista = (Collection<ClienteCallCenter>) query
                .getListaBean(ClienteCallCenter.class);
        return lista;
    }
}
