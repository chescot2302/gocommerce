/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoTipoCambio;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.TipoCambioProxy;

/**
 *
 * @author SISTEMAS
 */
@Service(value = MantenimientoTipoCambio.class)
public interface ContextMantenimientoTipoCambio extends RequestContext {
    Request<TipoCambioProxy> getTipoCambioNow(String clavePublica);
}
