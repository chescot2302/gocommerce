/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uirventrada;

import com.webgocommerce.client.beanproxy.DetalleVentaProxy;
import com.webgocommerce.client.beanproxy.ItemPlanProxy;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
public interface InterUIrvEntrada {
    void loadFamilia();
    void loadItem();
    void loadDocVenta();
    void searchCliente();
    void selectTipoDoc();
    void loadCorrelativo();    
    void selectCorrelativo();
    void loadPreciosItem();
    void cleanPrecioCantidad();
    void addItem();
    void removeItem(DetalleVentaProxy bean);
    void showUIvdSalida();
    void loadDetalleItem(DetalleVentaProxy bean,String modo);
    void calcularMontoTotales();
    void resetMontoTotales();
    void cleanFormData();
    void goUISearchAddCliente();
    boolean isValidData();
    void removeItemSerie();
    void addVentaDetalle();
    void removeVentaDetalle();
    void addEquipos(Set<ItemPlanProxy> equipos,DetalleVentaProxy object);
    void removeEquipos(List<ItemPlanProxy> equipos, DetalleVentaProxy object);
}
