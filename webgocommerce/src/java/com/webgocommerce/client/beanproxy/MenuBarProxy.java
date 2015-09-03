/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.MenuBar;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorMenuBar;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */

@ProxyFor(value=MenuBar.class,locator=LocatorMenuBar.class)
public interface MenuBarProxy extends EntityProxy{
    public Integer getIdMenuBar();

    public void setIdMenuBar(Integer idMenuBar);

    public String getVariable();

    public void setVariable(String variable);

    public String getDescripcion();

    public void setDescripcion(String descripcion);

    public String getTipo();

    public void setTipo(String tipo);

    public Integer getOrden();

    public void setOrden(Integer orden);

    public Integer getNivel();

    public void setNivel(Integer nivel);

    public Integer getIdMenuPadre();

    public void setIdMenuPadre(Integer idMenuPadre);

    public Integer getGrupo();

    public void setGrupo(Integer grupo);

    public Integer getNumSubMenu();

    public void setNumSubMenu(Integer numSubMenu);

    public List<MenuBarProxy> getHijos();

    public void setHijos(List<MenuBarProxy> hijos);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
    
    public void setHijo(MenuBarProxy bean);
    
    public Integer getIdBdUsuario();

    public void setIdBdUsuario(Integer idBdUsuario);

    public String getEstado();

    public void setEstado(String estado);        

    public String getComando();

    public void setComando(String comando);
}
