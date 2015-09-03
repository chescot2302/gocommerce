/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoBdUsuario;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.BdUsuarioProxy;
import com.webgocommerce.client.beanproxy.DataSesionProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoBdUsuario.class)
public interface ContextMantenimientoBdUsuario extends RequestContext{
    Request<List<DataSesionProxy>> getConexionUser(String clavePublica,Integer idBdUsuario,String loginName);
     Request<List<BdUsuarioProxy>> listar(String clavePublica);
     Request<Boolean> insertarBdUsuario(BdUsuarioProxy bean, String clavePublica);
}
