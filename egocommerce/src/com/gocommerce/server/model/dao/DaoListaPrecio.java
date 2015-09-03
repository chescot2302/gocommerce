/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.dao;

import com.gocommerce.server.model.beans.ListaPrecio;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.UnknownException;
import java.util.Collection;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class DaoListaPrecio {
    private PersistenceManager pm;

	public DaoListaPrecio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Integer id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(ListaPrecio.class, id);
	}        

	@SuppressWarnings("unchecked")
	public Collection<ListaPrecio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<ListaPrecio> lista = (Collection<ListaPrecio>) query
				.getListaBean(ListaPrecio.class);
		return lista;
	}
}
