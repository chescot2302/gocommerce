/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uisearchaddcliente;

/**
 *
 * @author SISTEMAS
 */
public interface InterUISearchAddCliente {
    void goToBack();
    void loadPais();
    void loadDepartamento();
    void loadProvincia();
    void loadDistrito();
    boolean isValidData();
    void processInsertar();
    void cleanForm();
    void goToUIvdEntrada(String docCliente);
    void selectClient();
    //void loadTable();
}
