/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestioncallcenter.GestionClienteCall;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.ClienteCallCenterProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=GestionClienteCall.class)
public interface ContextGestionClienteCall  extends RequestContext{
    Request<String> insertClienteCallCenter(ClienteCallCenterProxy bean,String clavePublica);
    Request<Boolean> updateClienteCallCenter(ClienteCallCenterProxy bean,String clavePublica);
    Request<Boolean> deleteClienteCallCenterBD(ClienteCallCenterProxy bean,String clavePublica);
    Request<List<ClienteCallCenterProxy>> listarClienteCallCenter(String clavePublica);
}
