/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoUsuarioCorrelativo;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.UsuarioCorrelativoProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */

@Service(value = MantenimientoUsuarioCorrelativo.class)
public interface ContextMantenimientoUsuarioCorrelativo  extends RequestContext {
    Request<List<UsuarioCorrelativoProxy>> listar(String clavePublica);
    Request<String> insertarUsuarioCorrelativo(String clavePublica,UsuarioCorrelativoProxy bean);
    Request<String> actualizarUsuarioCorrelativo(String clavePublica,UsuarioCorrelativoProxy bean);
    Request<String> desactivarUsuarioCorrelativo(String clavePublica,UsuarioCorrelativoProxy bean);
    Request<String> eliminarUsuarioCorrelativo(String clavePublica,UsuarioCorrelativoProxy bean);
}
