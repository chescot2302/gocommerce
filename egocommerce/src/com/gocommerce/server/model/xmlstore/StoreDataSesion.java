/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.xmlstore;

import com.gocommerce.server.model.beans.DataSesion;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@XmlRootElement(name = "storedatasesion")
public class StoreDataSesion implements Serializable{    
    private Integer idStore;
    private List<DataSesion> lista;

    public Integer getIdStore() {
        return idStore;
    }

    public void setIdStore(Integer idStore) {
        this.idStore = idStore;
    } 
    
    @XmlElement(name = "datasesion")
    public List<DataSesion> getLista() {
        return lista;
    }

    public void setLista(List<DataSesion> lista) {
        this.lista = lista;
    }
}
