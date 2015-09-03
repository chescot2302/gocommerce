/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Localidad;
import com.gocommerce.server.model.dao.DaoLocalidad;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.UnknownException;
import java.util.Collection;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class LogicLocalidad {
    private PersistenceManager pm;
    
    public LogicLocalidad(PersistenceManager pm) {
        this.pm=pm;
    }

    public boolean mantenimiento(BeanParametro parametro)
            throws UnknownException {
        DaoLocalidad dao = new DaoLocalidad(this.pm);
        return dao.mantenimiento(parametro);
    }

    public Object getBean(Integer id) throws UnknownException {
        DaoLocalidad dao = new DaoLocalidad(this.pm);
        return dao.getBean(id);
    }        

    public Collection<Localidad> getListarBean() throws UnknownException {
        DaoLocalidad dao = new DaoLocalidad(this.pm);
        Collection<Localidad> lista = dao.getListarBean();
        return lista;
    }
}
