/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.listmodel;

import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;
import com.webgocommerce.client.beanproxy.CategoriaListaProxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class ListModelCategoriaLista extends ListBox {

    private CategoriaListaProxy selectedItem;
    private ArrayList<CategoriaListaProxy> data = new ArrayList();

    public ListModelCategoriaLista() {
    }

    public ListModelCategoriaLista(ArrayList<CategoriaListaProxy> datos) {
        data = datos;
    }

    public void setData(List<CategoriaListaProxy> datos) {
        if(data.isEmpty()){
        data.addAll(datos);        
        }else{
            clear();
            data.addAll(datos);            
        }
        for (int i = 0; i < data.size(); i++) {
            insertItem(data.get(i).getDescripcion(), data.get(i).getId().toString(), i);
        }
        if(!data.isEmpty()){
            selectedItem=data.get(0);
            setSelectedIndex(0);
        }
    }

    @Override
    public int getItemCount() {
        try {
            return data.size();
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    @Override
    public void clear() {
        data.clear();
        getSelectElement().clear();
    }

    private SelectElement getSelectElement() {
        return getElement().cast();
    }

    public CategoriaListaProxy getElement(int index) throws ArrayIndexOutOfBoundsException {
        try {
            return data.get(index);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    public CategoriaListaProxy getElement(String codigo) {        
        Iterator<CategoriaListaProxy> i = data.iterator();
        while (i.hasNext()) {
            CategoriaListaProxy bean = (CategoriaListaProxy) i.next();
            if (bean.getId().toString().equals(codigo)) {                
                return bean;
            }
        }
        return null;
    }

    @Override
    public void setSelectedIndex(int index) {        
        getSelectElement().setSelectedIndex(index);
        selectedItem=data.get(getSelectElement().getSelectedIndex());
    }
        
    public void setSelectedItem(String item) {  
        int index=-1;
        for(int i=0;i<data.size();i++){
            CategoriaListaProxy bean = data.get(i);
            if (bean.getDescripcion().toString().equals(item)) {                
                index=i;
            }
        }
        getSelectElement().setSelectedIndex(index);
        selectedItem=data.get(getSelectElement().getSelectedIndex());
    }
    
    @Override
    public int getSelectedIndex() {        
    return getSelectElement().getSelectedIndex();
    }
    
    public CategoriaListaProxy getSelectedItem() {
        selectedItem=data.get(getSelectElement().getSelectedIndex());
        return selectedItem;
    }

}
