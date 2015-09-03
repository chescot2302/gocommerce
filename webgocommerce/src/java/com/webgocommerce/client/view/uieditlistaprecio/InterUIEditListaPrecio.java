/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uieditlistaprecio;

/**
 *
 * @author SISTEMAS
 */
public interface InterUIEditListaPrecio {
    void goToUIListaPrecio();
    void loadFields();
    void loadTableEstado();
    void updatePrices();
    void modoGrid(String modo);
    void exportarData();
}
