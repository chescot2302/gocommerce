/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoItemPlan;
import com.gocommerce.server.process.gestionmantenimiento.MantenimientoItemPlan;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.ItemPlanProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoItemPlan.class)
public interface ContextMantenimientoItemPlan extends RequestContext{
    Request<List<ItemPlanProxy>> listar(String clavePublica);
    Request<List<ItemPlanProxy>> getEquiposVenta(String clavePublica,String tipoDoc,String correlativo,String codiPlan,Integer idLista);
}
