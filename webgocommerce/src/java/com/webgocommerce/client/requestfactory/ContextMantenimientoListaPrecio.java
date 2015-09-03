/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoListaPrecio;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.ListaPrecioProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoListaPrecio.class)
public interface ContextMantenimientoListaPrecio extends RequestContext{
    Request<Boolean> insertarListaPrecio(ListaPrecioProxy bean, String clavePublica);
    Request<Boolean> actualizarListaPrecio(ListaPrecioProxy bean, String clavePublica);
    Request<Boolean> desactivarListaPrecio(ListaPrecioProxy bean, String clavePublica);
    Request<Boolean> eliminarListaPrecio(ListaPrecioProxy bean, String clavePublica);
    Request<List<ListaPrecioProxy>> listar(String clavePublica);
}
