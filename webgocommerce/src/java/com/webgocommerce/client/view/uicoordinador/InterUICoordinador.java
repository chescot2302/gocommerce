/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uicoordinador;

import com.webgocommerce.client.beanproxy.CoordinadorProxy;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public interface InterUICoordinador {
    void exportarData();
    void activar();
    void desactivar();
    void processActivar(CoordinadorProxy bean,Date fechaIncorporacion);
    void processDesactivar(CoordinadorProxy bean,Date fechaIncorporacion);
}
