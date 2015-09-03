/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoCategoriaLista;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.CategoriaListaProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoCategoriaLista.class)
public interface ContextMantenimientoCategoriaLista extends RequestContext{
    Request<Boolean> insertarCategoriaLista(CategoriaListaProxy bean, String clavePublica);
    Request<Boolean> actualizarCategoriaLista(CategoriaListaProxy bean, String clavePublica);
    Request<Boolean> eliminarCategoriaLista(CategoriaListaProxy bean, String clavePublica);
    Request<List<CategoriaListaProxy>> listar(String clavePublica);
}
