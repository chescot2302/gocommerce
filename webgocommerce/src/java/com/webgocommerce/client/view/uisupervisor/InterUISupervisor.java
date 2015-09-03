/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uisupervisor;

import com.webgocommerce.client.beanproxy.SupervisorProxy;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public interface InterUISupervisor {
    void exportarData();
    void activar();
    void desactivar();
    void processActivar(SupervisorProxy bean,Date fechaIncorporacion);
    void processDesactivar(SupervisorProxy bean,Date fechaIncorporacion);
}
