/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoCliente;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.ClienteProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoCliente.class)
public interface ContextMantenimientoCliente extends RequestContext{
    Request<ClienteProxy> getCliente(String clavePublica,String documento);
    Request<List<ClienteProxy>> getListarCliente(String clavePublica,Integer indexFiltro,String filtro);
    Request<String> insertarCliente(String clavePublica,ClienteProxy bean);
}
