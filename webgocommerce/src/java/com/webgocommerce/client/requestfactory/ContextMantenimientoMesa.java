/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoMesa;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.MesaProxy;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=MantenimientoMesa.class)
public interface ContextMantenimientoMesa extends RequestContext {
    Request<Boolean> insertMesa(MesaProxy bean,String clavePublica);
    Request<Boolean> updateMesa(MesaProxy bean,String clavePublica);
    Request<Boolean> deleteMesaBD(MesaProxy bean,String clavePublica);
    Request<List<MesaProxy>> listarMesa(String clavePublica);
    Request<List<MesaProxy>> listar(String clavePublica);
    Request<MesaProxy> getMesaxVendedor(String clavePublica,String idVendedor);
    Request<String> insertarMesa(String clavePublica,MesaProxy bean);
    Request<String> actualizarMesa(String clavePublica,MesaProxy bean);   
    Request<String> eliminarMesa(String clavePublica,MesaProxy bean);
    Request<String> desactivarMesa(String clavePublica,MesaProxy bean);
}
