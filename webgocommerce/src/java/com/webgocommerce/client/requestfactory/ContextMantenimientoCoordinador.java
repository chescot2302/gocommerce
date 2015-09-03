/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoCoordinador;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.CoordinadorProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoCoordinador.class)
public interface ContextMantenimientoCoordinador extends RequestContext{
    Request<List<CoordinadorProxy>> listarCoordinador(String clavePublica,String estado);
    Request<String> insertarCoordinador(CoordinadorProxy bean, String clavePublica);  
    Request<String> eliminarCoordinador(String clavePublica,CoordinadorProxy bean);  
    Request<String> actualizarCoordinador(String clavePublica,CoordinadorProxy bean);  
    Request<String> actDesCoordinador(String clavePublica,CoordinadorProxy bean);  
}
