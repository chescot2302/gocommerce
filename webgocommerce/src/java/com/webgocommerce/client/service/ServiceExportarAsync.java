/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public interface ServiceExportarAsync {
    public void exportar(String keyPublic,AsyncCallback<Void> callback);
}
