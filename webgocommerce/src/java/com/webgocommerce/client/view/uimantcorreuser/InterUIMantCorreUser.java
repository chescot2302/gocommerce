/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantcorreuser;

/**
 *
 * @author SISTEMAS
 */
public interface InterUIMantCorreUser {
    void loadFields();
    void cleanForm();
    void goToUICorreUser();
    void loadSucursal();
    void loadTienda();
    void loadPuntoEmision();
    void loadCorrelativo();
    void loadSerieCorrelativo();
    void gotoUISelectUser();
    void clearUsuario();
    void processDesactivar();
    boolean isValidData();
}
