/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoSucursal;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.SucursalProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value = MantenimientoSucursal.class)
public interface ContextMantenimientoSucursal extends RequestContext {
    Request<List<SucursalProxy>> listar(String clavePublica);
    Request<List<SucursalProxy>> listarSucursalCla(String clavePublica);    
    Request<String> insertarSucursal(String clavePublica,SucursalProxy bean);
    Request<String> actualizarSucursal(String clavePublica,SucursalProxy bean);   
    Request<String> eliminarSucursal(String clavePublica,SucursalProxy bean);
}
