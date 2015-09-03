/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoProvincia;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.ProvinciaProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoProvincia.class)
public interface ContextMantenimientoProvincia extends RequestContext{
    Request<List<ProvinciaProxy>> listar(String clavePublica,String idPais,String idDepartamento);
}
