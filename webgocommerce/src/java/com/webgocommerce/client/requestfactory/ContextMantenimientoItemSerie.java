/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoItemSerie;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.ItemSerieProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoItemSerie.class)
public interface ContextMantenimientoItemSerie extends RequestContext{
    Request<List<ItemSerieProxy>> listar(String clavePublica,String codi,String codAlm,String tipo);
    Request<List<ItemSerieProxy>> getSeriesVenta(String clavePublica,String tipoDoc,String correlativo,String codi);
}
