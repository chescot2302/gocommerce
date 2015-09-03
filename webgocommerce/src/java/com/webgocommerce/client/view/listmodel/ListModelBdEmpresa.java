/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.listmodel;

import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;
import com.webgocommerce.client.beanproxy.BdEmpresaProxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class ListModelBdEmpresa extends ListBox {

    private BdEmpresaProxy selectedItem;
    private ArrayList<BdEmpresaProxy> data = new ArrayList();

    public ListModelBdEmpresa() {
    }

    public ListModelBdEmpresa(ArrayList<BdEmpresaProxy> datos) {
        data = datos;
    }

    public void setData(List<BdEmpresaProxy> datos) {
        if(data.isEmpty()){
        data.addAll(datos);        
        }else{
            clear();
            data.addAll(datos);            
        }
        for (int i = 0; i < data.size(); i++) {
            insertItem(data.get(i).getNombre(), data.get(i).getIdBdEmpresa().toString(), i);
        }
        if(!data.isEmpty()){
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

    public BdEmpresaProxy getElement(int index) throws ArrayIndexOutOfBoundsException {
        try {
            return data.get(index);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    public BdEmpresaProxy getElement(String codigo) {        
        Iterator<BdEmpresaProxy> i = data.iterator();
        while (i.hasNext()) {
            BdEmpresaProxy bean = (BdEmpresaProxy) i.next();
            if (bean.getIdBdEmpresa().toString().equals(codigo)) {                
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
    
    @Override
    public int getSelectedIndex() {        
    return getSelectElement().getSelectedIndex();
    }
    
    public BdEmpresaProxy getSelectedItem() {
        selectedItem=data.get(getSelectElement().getSelectedIndex());
        return selectedItem;
    }

}
