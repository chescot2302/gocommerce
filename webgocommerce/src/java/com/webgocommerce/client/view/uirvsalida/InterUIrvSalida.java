/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uirvsalida;

/**
 *
 * @author SISTEMAS
 */
public interface InterUIrvSalida {
    void cancelarOperacion();
    void loadField();
    void loadVendedor();
    void generarDocumento();
    boolean isValidData();
    void cleanForm();
    void getMesa();
}
