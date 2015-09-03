/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoCondicionVenta;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.CondicionVentaProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoCondicionVenta.class)
public interface ContextMantenimientoCondicionVenta extends RequestContext{
    Request<List<CondicionVentaProxy>> listar(String clavePublica);
    Request<List<CondicionVentaProxy>> listarxVigenciaCredito(String clavePublica,Integer vigenciaCredito);
}
