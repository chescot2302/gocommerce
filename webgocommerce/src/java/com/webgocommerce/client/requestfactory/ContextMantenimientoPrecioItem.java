/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionmantenimiento.MantenimientoPrecioItem;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.PrecioItemProxy;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
@Service(value = MantenimientoPrecioItem.class)
public interface ContextMantenimientoPrecioItem extends RequestContext {

    Request<List<PrecioItemProxy>> listar(String clavePublica, Integer idLista, String estado);
    
    Request<List<PrecioItemProxy>> listaPrecioxItem(String clavePublica,String idItem,String idMoneda,String canal);

    Request<List<PrecioItemProxy>> itemSinLista(String clavePublica, Integer idLista);
    
    Request<List<PrecioItemProxy>> itemSinListaCategoria(String clavePublica, Integer idLista,String codFam,String descripcion);

    Request<Boolean> insertarPrecioItem(String clavePublica, Set<PrecioItemProxy> lista);
    
    Request<Boolean> eliminarPrecioItem(String clavePublica, Set<PrecioItemProxy> lista);
    
    Request<Boolean> desactivarPrecioItem(String clavePublica, Set<PrecioItemProxy> lista);
    
    Request<Boolean> actualizarPrecioItem(String clavePublica, Set<PrecioItemProxy> lista);
}
