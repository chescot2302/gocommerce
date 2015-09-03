/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uigerzonal;

import com.webgocommerce.client.beanproxy.GerenteZonalProxy;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public interface InterUIGerZonal {
    void exportarData();
    void activar();
    void desactivar();
    void processActivar(GerenteZonalProxy bean,Date fechaIncorporacion);
    void processDesactivar(GerenteZonalProxy bean,Date fechaIncorporacion);
}
