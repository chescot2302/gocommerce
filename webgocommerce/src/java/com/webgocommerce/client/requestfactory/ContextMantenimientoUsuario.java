/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoUsuario;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.UsuarioProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value = MantenimientoUsuario.class)
public interface ContextMantenimientoUsuario extends RequestContext {
    Request<UsuarioProxy> getUsuarioNick(String clavePublica,String nick);
    Request<List<UsuarioProxy>> getListarUsuarioPto(String clavePublica,String idPuntoEmision,String idDocumento,String serie);
}
