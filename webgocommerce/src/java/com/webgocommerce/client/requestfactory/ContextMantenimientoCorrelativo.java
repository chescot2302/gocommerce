/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoCorrelativo;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.CorrelativoProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value = MantenimientoCorrelativo.class)
public interface ContextMantenimientoCorrelativo extends RequestContext {
    Request<List<CorrelativoProxy>> listarCorrelativoxPtoEmision(String clavePublica,String idPuntoEmision);
    Request<List<CorrelativoProxy>> getCorrelativoActual(String clavePublica,String idUsuario,String idPuntoEmision,String idDocumento,String incluyePto);
    Request<List<CorrelativoProxy>> getSerieUserVenta(String clavePublica,String idUsuario,String idPuntoEmision);
    Request<List<CorrelativoProxy>> getSerieUserVentaCe(String clavePublica,String idUsuario,String idPuntoEmision);
}
