/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.gocommerce.server.process.gestionventas.GestionFacturacion;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.webgocommerce.client.beanproxy.CabeceraVentaProxy;
import com.webgocommerce.client.beanproxy.DetalleVentaProxy;
import com.webgocommerce.client.beanproxy.ItemPlanProxy;
import com.webgocommerce.client.beanproxy.ItemSerieProxy;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
@Service(value=GestionFacturacion.class)
public interface ContextGestionFacturacion  extends RequestContext{
    Request<String> generarDocumento(String clavePublica,List<DetalleVentaProxy> detalles,List<CabeceraVentaProxy> cabecera,List<ItemSerieProxy> series,Integer conDespacho,Integer conAsiento,Date fechaEmision,String idPuntoEmision);
    Request<String> registrarVentaCe(String clavePublica,List<DetalleVentaProxy> detalles,List<CabeceraVentaProxy> cabecera,List<ItemPlanProxy> equipos,Date fechaEmision,String idPuntoEmision);
    Request<List<CabeceraVentaProxy>> listarVentas(String clavePublica,Date fechaIni,Date fechaFin,String rucCliente,String descrCliente,String docSeries,String correlativo,String tas,String excluirFecha);
    Request<List<CabeceraVentaProxy>> listarVentasCe(String clavePublica,Date fechaIni,Date fechaFin,String rucCliente,String descrCliente,String docSeries,String correlativo,String tas,String excluirFecha);
    Request<List<DetalleVentaProxy>> getDetalleVenta(String clavePublica,String tipoDoc,String correlativo);
    Request<List<DetalleVentaProxy>> getDetalleVentaCe(String clavePublica,String tipoDoc,String correlativo);
}
