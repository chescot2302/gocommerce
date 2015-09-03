/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoSuperVen;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.SuperVenProxy;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
@Service(value = MantenimientoSuperVen.class)
public interface ContextMantenimientoSuperVen extends RequestContext {
    Request<List<SuperVenProxy>> listar(String clavePublica, Integer idMesa, String estado);
    Request<List<SuperVenProxy>> consultorSinMesa(String clavePublica);
    Request<Boolean> insertarSuperVen(String clavePublica, Set<SuperVenProxy> lista);
    Request<Boolean> desactivarSuperVen(String clavePublica, Set<SuperVenProxy> lista);
    Request<Boolean> eliminarSuperVen(String clavePublica, Set<SuperVenProxy> lista);
}
