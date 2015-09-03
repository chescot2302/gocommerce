/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantsupervisor;

/**
 *
 * @author SISTEMAS
 */
public interface InterUIMantSupervisor {
    void loadFields();
    void cleanForm();
    void goToUISupervisor();
    void loadMesa();
    void loadSucursal();
    void loadTienda();
    void loadPuntoEmision();
    boolean isValidData();
}
