/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.model.dao;

import com.gocommerce.server.model.beans.Param;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.UnknownException;
import java.util.Collection;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 *
 * @author SISTEMAS
 */
public class DaoParam {

    private PersistenceManager pm;

    public DaoParam(PersistenceManager pm) {
        this.pm = pm;
    }

    public boolean mantenimiento(BeanParametro parametro)
            throws UnknownException {
        Querys query = new Querys(this.pm);
        return query.mantenimiento(parametro);
    }

    public Object getBean(Integer id) throws UnknownException {
        Querys query = new Querys(this.pm);
        return query.getBean(Param.class, id);
    }

    public Object getBean(String abreviatura) throws UnknownException {
        Query query = pm.newQuery(Param.class);
        Param bean;
        query.setFilter("abreviatura==paramAbreviatura");
        query.declareParameters("String paramAbreviatura");
        bean = (Param) query.execute(abreviatura);
        return bean;
    }

    @SuppressWarnings("unchecked")
    public Collection<Param> getListarBean() throws UnknownException {
        Querys query = new Querys(this.pm);
        Collection<Param> lista = (Collection<Param>) query
                .getListaBean(Param.class);
        return lista;
    }
}
