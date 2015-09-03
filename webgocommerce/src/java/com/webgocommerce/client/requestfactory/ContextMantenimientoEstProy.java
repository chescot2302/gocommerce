/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoEstProy;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.EstProyProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value = MantenimientoEstProy.class)
public interface ContextMantenimientoEstProy  extends RequestContext {
    Request<List<EstProyProxy>> listar(String clavePublica,String cDocu,String nDocu);
    Request<String> actualizarEstProy(String clavePublica,EstProyProxy bean);   
}
