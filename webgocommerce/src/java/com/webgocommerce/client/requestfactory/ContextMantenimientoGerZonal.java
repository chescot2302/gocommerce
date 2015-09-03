/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoGerZonal;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.GerenteZonalProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoGerZonal.class)
public interface ContextMantenimientoGerZonal extends RequestContext{
    Request<List<GerenteZonalProxy>> listarGerenteZonal(String clavePublica,String estado);
    Request<String> insertarGerenteZonal(GerenteZonalProxy bean, String clavePublica);  
    Request<String> eliminarGerenteZonal(String clavePublica,GerenteZonalProxy bean);  
    Request<String> actualizarGerenteZonal(String clavePublica,GerenteZonalProxy bean);  
    Request<String> actDesGerenteZonal(String clavePublica,GerenteZonalProxy bean);  
}
