/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.ListaPrecio;
import com.gocommerce.server.model.dao.DaoListaPrecio;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.UnknownException;
import java.util.Collection;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class LogicListaPrecio {
    private PersistenceManager pm;
    
    public LogicListaPrecio(PersistenceManager pm) {
        this.pm=pm;
    }

    public boolean mantenimiento(BeanParametro parametro)
            throws UnknownException {
        DaoListaPrecio dao = new DaoListaPrecio(this.pm);
        return dao.mantenimiento(parametro);
    }

    public Object getBean(Integer id) throws UnknownException {
        DaoListaPrecio dao = new DaoListaPrecio(this.pm);
        return dao.getBean(id);
    }        

    public Collection<ListaPrecio> getListarBean() throws UnknownException {
        DaoListaPrecio dao = new DaoListaPrecio(this.pm);
        Collection<ListaPrecio> lista = dao.getListarBean();
        return lista;
    }    
}
