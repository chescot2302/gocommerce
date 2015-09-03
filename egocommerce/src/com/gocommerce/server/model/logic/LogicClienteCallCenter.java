/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.ClienteCallCenter;
import com.gocommerce.server.model.dao.DaoClienteCallCenter;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.UnknownException;
import java.util.Collection;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class LogicClienteCallCenter {
    private PersistenceManager pm;
    
    public LogicClienteCallCenter(PersistenceManager pm) {
        this.pm=pm;
    }

    public boolean mantenimiento(BeanParametro parametro)
            throws UnknownException {
        DaoClienteCallCenter dao = new DaoClienteCallCenter(this.pm);
        return dao.mantenimiento(parametro);
    }

    public Object getBean(Integer id) throws UnknownException {
        DaoClienteCallCenter dao = new DaoClienteCallCenter(this.pm);
        return dao.getBean(id);
    }    
    
    public Object getBean(String dni) throws UnknownException {
        DaoClienteCallCenter dao = new DaoClienteCallCenter(this.pm);
        return dao.getBean(dni);
    } 

    public Collection<ClienteCallCenter> getListarBean() throws UnknownException {
        DaoClienteCallCenter dao = new DaoClienteCallCenter(this.pm);
        Collection<ClienteCallCenter> lista = dao.getListarBean();
        return lista;
    }
}
