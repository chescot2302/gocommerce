/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uivendedor;

import com.webgocommerce.client.beanproxy.VendedorProxy;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public interface InterUIVendedor {
    void exportarData();
    void activar();
    void desactivar();
    void processActivar(VendedorProxy bean,Date fechaIncorporacion);
    void processDesactivar(VendedorProxy bean,Date fechaIncorporacion);
}
