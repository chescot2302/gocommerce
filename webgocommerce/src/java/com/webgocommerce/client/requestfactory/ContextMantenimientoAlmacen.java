/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoAlmacen;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.AlmacenProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoAlmacen.class)
public interface ContextMantenimientoAlmacen extends RequestContext{
    Request<List<AlmacenProxy>> listar(String clavePublica,String idItem,String ubicacion);
    Request<List<AlmacenProxy>> listarAlmXLocalidad(String clavePublica,String idItem,String idLocalidad,String idAlmacen);
}
