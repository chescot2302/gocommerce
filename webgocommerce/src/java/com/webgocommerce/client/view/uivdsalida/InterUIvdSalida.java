/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uivdsalida;

/**
 *
 * @author SISTEMAS
 */
public interface InterUIvdSalida {
    void cancelarOperacion();
    void loadField();
    void loadVendedor();
    void loadCondicionVenta();
    void loadTipoVenta();
    void calcularVuelto();
    void generarDocumento();
    boolean isValidData();
    void cleanForm();
}
