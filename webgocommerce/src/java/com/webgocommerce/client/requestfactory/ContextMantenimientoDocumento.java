/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoDocumento;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.DocumentoProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoDocumento.class)
public interface ContextMantenimientoDocumento  extends RequestContext{
    Request<List<DocumentoProxy>> listaDocVenta(String clavePublica);
    Request<List<DocumentoProxy>> listaDocVentaCe(String clavePublica);
    Request<List<DocumentoProxy>> listaDocDespacho(String clavePublica);
}
