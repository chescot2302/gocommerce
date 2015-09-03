/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.listmodel;

import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;
import com.webgocommerce.client.beanproxy.DocumentoProxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class ListModelDocVenta extends ListBox {

    private DocumentoProxy selectedItem;
    private ArrayList<DocumentoProxy> data = new ArrayList();

    public ListModelDocVenta() {
    }

    public ListModelDocVenta(ArrayList<DocumentoProxy> datos) {
        data = datos;
    }

    public void setData(List<DocumentoProxy> datos) {
        if(data.isEmpty()){
        data.addAll(datos);        
        }else{
            clear();
            data.addAll(datos);            
        }
        for (int i = 0; i < data.size(); i++) {
            insertItem(data.get(i).getNombre(), data.get(i).getId(), i);
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

    public DocumentoProxy getElement(int index) throws ArrayIndexOutOfBoundsException {
        try {
            return data.get(index);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    public DocumentoProxy getElement(String codigo) {        
        Iterator<DocumentoProxy> i = data.iterator();
        while (i.hasNext()) {
            DocumentoProxy bean = (DocumentoProxy) i.next();
            if (bean.getId().equals(codigo)) {                
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
        
    public void setSelectedItem(String nombre) {  
        int index=-1;
        for(int i=0;i<data.size();i++){
            DocumentoProxy bean = data.get(i);
            if (bean.getNombre().equals(nombre)) {                
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
    
    public DocumentoProxy getSelectedItem() {
        selectedItem=data.get(getSelectElement().getSelectedIndex());
        return selectedItem;
    }

}
