/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.CategoriaLista;
import com.gocommerce.server.model.dao.DaoCategoriaLista;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.UnknownException;
import java.util.Collection;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class LogicCategoriaLista {
    private PersistenceManager pm;
    
    public LogicCategoriaLista(PersistenceManager pm) {
        this.pm=pm;
    }

    public boolean mantenimiento(BeanParametro parametro)
            throws UnknownException {
        DaoCategoriaLista dao = new DaoCategoriaLista(this.pm);
        return dao.mantenimiento(parametro);
    }

    public Object getBean(Integer id) throws UnknownException {
        DaoCategoriaLista dao = new DaoCategoriaLista(this.pm);
        return dao.getBean(id);
    }        

    public Collection<CategoriaLista> getListarBean() throws UnknownException {
        DaoCategoriaLista dao = new DaoCategoriaLista(this.pm);
        Collection<CategoriaLista> lista = dao.getListarBean();
        return lista;
    }    
}
