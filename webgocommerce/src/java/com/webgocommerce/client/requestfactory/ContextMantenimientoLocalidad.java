/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoLocalidad;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.LocalidadProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoLocalidad.class)
public interface ContextMantenimientoLocalidad  extends RequestContext{
    Request<Boolean> insertLocalidad(LocalidadProxy bean,String clavePublica);
    Request<Boolean> updateLocalidad(LocalidadProxy bean,String clavePublica);
    Request<Boolean> deleteLocalidadBD(LocalidadProxy bean,String clavePublica);
    Request<List<LocalidadProxy>> listarLocalidad(String clavePublica);
}
