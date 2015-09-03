/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoBdEmpresa;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.BdEmpresaProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoBdEmpresa.class)
public interface ContextMantenimientoBdEmpresa extends RequestContext{
    Request<List<BdEmpresaProxy>> listar(String clavePublica);
    Request<Boolean> insertarBdEmpresa(BdEmpresaProxy bean, String clavePublica);
    Request<Boolean> actualizarBdEmpresa(BdEmpresaProxy bean, String clavePublica);
    Request<Boolean> eliminarBdEmpresa(BdEmpresaProxy bean, String clavePublica);
}
