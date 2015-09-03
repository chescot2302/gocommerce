/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.xmlstore;

import com.gocommerce.server.model.beans.SuperVen;
import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@XmlRootElement(name = "storesuperven")
public class StoreSuperVen implements Serializable{
    private Integer idStore;
    private Set<SuperVen> lista;

    public Integer getIdStore() {
        return idStore;
    }

    public void setIdStore(Integer idStore) {
        this.idStore = idStore;
    } 
    
    @XmlElement(name = "superven")
    public Set<SuperVen> getLista() {
        return lista;
    }

    public void setLista(Set<SuperVen> lista) {
        this.lista = lista;
    }
                   
}
