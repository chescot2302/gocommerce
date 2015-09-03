/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Param;
import com.gocommerce.server.model.dao.DaoParam;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.UnknownException;
import java.util.Collection;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class LogicParam {
    private PersistenceManager pm;
    
    public LogicParam(PersistenceManager pm) {
        this.pm=pm;
    }

    public boolean mantenimiento(BeanParametro parametro)
            throws UnknownException {
        DaoParam dao = new DaoParam(this.pm);
        return dao.mantenimiento(parametro);
    }

    public Object getBean(Integer id) throws UnknownException {
        DaoParam dao = new DaoParam(this.pm);
        return dao.getBean(id);
    }   
    
    public Object getBean(String abreviatura) throws UnknownException {
        DaoParam dao = new DaoParam(this.pm);
        return dao.getBean(abreviatura);
    }   

    public Collection<Param> getListarBean() throws UnknownException {
        DaoParam dao = new DaoParam(this.pm);
        Collection<Param> lista = dao.getListarBean();
        return lista;
    }
}
