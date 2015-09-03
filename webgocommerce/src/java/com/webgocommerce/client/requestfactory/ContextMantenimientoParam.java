/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoParam;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.ParamProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoParam.class)
public interface ContextMantenimientoParam extends RequestContext{
    Request<Boolean> insertParam(ParamProxy bean,String clavePublica);
    Request<Boolean> updateParam(ParamProxy bean,String clavePublica);
    Request<Boolean> deleteParamBD(ParamProxy bean,String clavePublica);
    Request<List<ParamProxy>> listarParam(String clavePublica);
    Request<ParamProxy> getBean(String abreviatura,String clavePublica);
}
