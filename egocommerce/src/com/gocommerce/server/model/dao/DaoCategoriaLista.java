/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.dao;

import com.gocommerce.server.model.beans.CategoriaLista;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.UnknownException;
import java.util.Collection;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class DaoCategoriaLista {
        private PersistenceManager pm;

	public DaoCategoriaLista(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Integer id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(CategoriaLista.class, id);
	}        

	@SuppressWarnings("unchecked")
	public Collection<CategoriaLista> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<CategoriaLista> lista = (Collection<CategoriaLista>) query
				.getListaBean(CategoriaLista.class);
		return lista;
	}
}
