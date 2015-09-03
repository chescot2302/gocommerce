/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uisesion;

/**
 *
 * @author SISTEMAS
 */
public interface InterUISesion {
    void loadInitParam();
    void loadParam();
    void loadTipoCambioNow();
    void getKeyPublic();
    void loadUsuario();
    void loadUbicacion();
    void logout();
    void loadMenu();
    void loadGui();
}
