/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoTienda;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.TiendaProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value = MantenimientoTienda.class)
public interface ContextMantenimientoTienda extends RequestContext {
    Request<List<TiendaProxy>> listarTiendaxSucursal(String clavePublica,String idSucursal);
}
