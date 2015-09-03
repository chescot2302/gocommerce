/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoMenuBar;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.MenuBarProxy;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
@Service(value = MantenimientoMenuBar.class)
public interface ContextMantenimientoMenuBar extends RequestContext {
    Request<List<MenuBarProxy>> listar(String clavePublica);
    Request<List<MenuBarProxy>> listarXusuario(String clavePublica,Integer idBdUsuario,String esquema);
    Request<Boolean> updateGrantMenusuario(String clavePublica, Set<MenuBarProxy> lista,String esquema);
}
