/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uivdentrada;

import com.webgocommerce.client.beanproxy.DetalleVentaProxy;
import com.webgocommerce.client.beanproxy.ItemSerieProxy;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
public interface InterUIvdEntrada {
    void loadFamilia();
    void loadItem();
    void loadAlmacen();
    void loadDocVenta();
    void cleanAlmacen();
    void searchCliente();
    void selectTipoDoc();
    void loadCorrelativo();    
    void selectCorrelativo();
    void loadPreciosItem();
    void cleanPrecioCantidad();
    void addItem(Set<ItemSerieProxy> series);
    void removeItem(List<ItemSerieProxy> series,DetalleVentaProxy bean);
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
}
