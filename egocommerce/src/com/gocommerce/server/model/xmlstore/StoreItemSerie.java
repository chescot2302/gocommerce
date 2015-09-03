/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.xmlstore;

import com.gocommerce.server.model.beans.ItemSerie;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@XmlRootElement(name = "storeitemserie")
public class StoreItemSerie implements Serializable{
    private Integer idStore;
    private List<ItemSerie> lista;

    public Integer getIdStore() {
        return idStore;
    }

    public void setIdStore(Integer idStore) {
        this.idStore = idStore;
    } 
    
    @XmlElement(name = "itemserie")
    public List<ItemSerie> getLista() {
        return lista;
    }

    public void setLista(List<ItemSerie> lista) {
        this.lista = lista;
    }
}
