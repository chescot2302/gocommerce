/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoSupervisor;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.SupervisorProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoSupervisor.class)
public interface ContextMantenimientoSupervisor extends RequestContext{
    Request<Boolean> insertSupervisor(SupervisorProxy bean,String clavePublica);
    Request<Boolean> updateSupervisor(SupervisorProxy bean,String clavePublica);
    Request<Boolean> deleteSupervisorBD(SupervisorProxy bean,String clavePublica);
    Request<List<SupervisorProxy>> listarSupervisor(String clavePublica,String estado);
    Request<List<SupervisorProxy>> listarSupervisorCl(String clavePublica);
    Request<String> insertarSupervisor(SupervisorProxy bean, String clavePublica);  
    Request<String> eliminarSupervisor(String clavePublica,SupervisorProxy bean);  
    Request<String> actualizarSupervisor(String clavePublica,SupervisorProxy bean);  
    Request<String> actDesSupervisor(String clavePublica,SupervisorProxy bean);  
}
