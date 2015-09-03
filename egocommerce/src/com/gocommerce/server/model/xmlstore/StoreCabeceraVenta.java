/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.xmlstore;

import com.gocommerce.server.model.beans.CabeceraVenta;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@XmlRootElement(name = "storecabeceraventa")
public class StoreCabeceraVenta  implements Serializable{    
    private Integer idStore;
    private List<CabeceraVenta> lista;

    public Integer getIdStore() {
        return idStore;
    }

    public void setIdStore(Integer idStore) {
        this.idStore = idStore;
    } 
    
    @XmlElement(name = "cabeceraventa")
    public List<CabeceraVenta> getLista() {
        return lista;
    }

    public void setLista(List<CabeceraVenta> lista) {
        this.lista = lista;
    }
}
