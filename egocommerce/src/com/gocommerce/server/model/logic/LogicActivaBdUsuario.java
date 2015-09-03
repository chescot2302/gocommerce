package com.gocommerce.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.gocommerce.server.model.beans.ActivaBdUsuario;
import com.gocommerce.server.model.dao.DaoActivaBdUsuario;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.UnknownException;

public class LogicActivaBdUsuario {
	private PersistenceManager pm;

	public LogicActivaBdUsuario(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoActivaBdUsuario dao = new DaoActivaBdUsuario(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(Integer id) throws UnknownException {
		DaoActivaBdUsuario dao = new DaoActivaBdUsuario(this.pm);
		return dao.getBean(id);
	}

	public Collection<ActivaBdUsuario> getListarBean() throws UnknownException {
		DaoActivaBdUsuario dao = new DaoActivaBdUsuario(this.pm);
		Collection<ActivaBdUsuario> lista = dao.getListarBean();
		return lista;
	}

	public Collection<ActivaBdUsuario> getListarBean(String estado)
			throws UnknownException {
		DaoActivaBdUsuario dao = new DaoActivaBdUsuario(this.pm);
		Collection<ActivaBdUsuario> lista = dao.getListarBean(estado);
		return lista;
	}
}
