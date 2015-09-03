package com.gocommerce.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.gocommerce.server.model.beans.ActivaBdUsuario;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.UnknownException;

public class DaoActivaBdUsuario {
	private PersistenceManager pm;

	public DaoActivaBdUsuario(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Integer id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(ActivaBdUsuario.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ActivaBdUsuario> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<ActivaBdUsuario> lista = (Collection<ActivaBdUsuario>) query
				.getListaBean(ActivaBdUsuario.class);
		return lista;
	}

	@SuppressWarnings("unchecked")
	public Collection<ActivaBdUsuario> getListarBean(String estado)
			throws UnknownException {
		Query query = pm.newQuery(ActivaBdUsuario.class);                
		query.setOrdering("idBdEmpresa desc");
		Collection<ActivaBdUsuario> lista;
		switch (estado) {
		case "T":
			lista = (Collection<ActivaBdUsuario>) query.execute();
			break;
		case "A":
		case "D":
			query.setFilter("estadoactivacion==paramEstado");
			query.declareParameters("String paramEstado");
			lista = (Collection<ActivaBdUsuario>) query.execute(estado);
			break;
		default:
			query.setFilter("estadoactivacion==paramEstado");
			query.declareParameters("String paramEstado");
			lista = (Collection<ActivaBdUsuario>) query.execute("A");
			break;
		}
		return lista;
	}
}
