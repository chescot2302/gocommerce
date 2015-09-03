/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantmesa;

/**
 *
 * @author SISTEMAS
 */
public interface InterUIMantMesa {
    void loadFields();
    void cleanForm();
    void goToUIMesa();
    boolean isValidData();
    void loadSucursal();
    void loadCoordinador();
    void loadGerenteZonal();
    void loadSupervisor();
    void processDesactivar();
}
