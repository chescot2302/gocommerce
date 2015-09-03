/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.requestfactory;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

/**
 *
 * @author SISTEMAS
 */
public interface FactoryGestion extends RequestFactory{
    ContextMantenimientoBdEmpresa contextMantenimientoBdEmpresa();
    ContextMantenimientoBdUsuario contextMantenimientoBdUsuario();
    ContextMantenimientoItem contextMantenimientoItem();
    ContextMantenimientoInitParam contextMantenimientoInitParam();
    ContextMantenimientoAlmacen contextMantenimientoAlmacen();
    ContextMantenimientoCategoriaLista contextMantenimientoCategoriaLista();
    ContextMantenimientoListaPrecio contextMantenimientoListaPrecio();
    ContextMantenimientoPrecioItem contextMantenimientoPrecioItem();
    ContextMantenimientoCliente contextMantenimientoCliente();
    ContextMantenimientoDocumento contextMantenimientoDocumento();
    ContextMantenimientoTipoCambio contextMantenimientoTipoCambio();
    ContextMantenimientoUsuario contextMantenimientoUsuario();
    ContextMantenimientoSucursal contextMantenimientoSucursal();
    ContextMantenimientoTienda contextMantenimientoTienda();
    ContextMantenimientoPtoEmision contextMantenimientoPtoEmision();
    ContextMantenimientoCorrelativo contextMantenimientoCorrelativo();
    ContextMantenimientoUsuarioCorrelativo contextMantenimientoUsuarioCorrelativo();
    ContextMantenimientoLocalidad contextMantenimientoLocalidad();
    ContextMantenimientoSupervisor contextMantenimientoSupervisor();
    ContextMantenimientoMesa contextMantenimientoMesa();
    ContextGestionFacturacion contextGestionFacturacion();
    ContextMantenimientoVendedor contextMantenimientoVendedor();
    ContextMantenimientoCondicionVenta contextMantenimientoCondicionVenta();
    ContextMantenimientoTipoVenta contextMantenimientoTipoVenta();
    ContextMantenimientoPais contextMantenimientoPais();
    ContextMantenimientoDepartamento contextMantenimientoDepartamento();
    ContextMantenimientoProvincia contextMantenimientoProvincia();
    ContextMantenimientoDistrito contextMantenimientoDistrito();
    ContextMantenimientoMenuBar contextMantenimientoMenuBar();
    ContextMantenimientoItemSerie contextMantenimientoItemSerie();
    ContextMantenimientoItemPlan contextMantenimientoItemPlan();
    ContextMantenimientoParam contextMantenimientoParam();
    ContextGestionClienteCall contextGestionClienteCall();
    ContextMantenimientoFamilia contextMantenimientoFamilia();
    ContextMantenimientoGerZonal contextMantenimientoGerZonal();
    ContextMantenimientoCoordinador contextMantenimientoCoordinador();
    ContextMantenimientoSuperVen contextMantenimientoSuperVen();
    ContextMantenimientoEstProy contextMantenimientoEstProy();
}
