/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoVendedor;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.VendedorProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoVendedor.class)
public interface ContextMantenimientoVendedor  extends RequestContext{
    Request<String> insertarVendedor(VendedorProxy bean, String clavePublica);  
    Request<String> eliminarVendedor(String clavePublica,VendedorProxy bean);  
    Request<String> actualizarVendedor(String clavePublica,VendedorProxy bean);  
    Request<String> actDesVendedor(String clavePublica,VendedorProxy bean);  
    Request<List<VendedorProxy>> listar(String clavePublica);
    Request<List<VendedorProxy>> listarVendedor(String clavePublica);
    Request<List<VendedorProxy>> listarVendedorXpto(String clavePublica,String idPuntoEmision);
    Request<List<VendedorProxy>> listarVendedorXptoCe(String clavePublica,String idPuntoEmision);
}
