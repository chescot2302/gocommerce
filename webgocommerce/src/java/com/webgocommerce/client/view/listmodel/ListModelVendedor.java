/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.listmodel;

import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;
import com.webgocommerce.client.beanproxy.VendedorProxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class ListModelVendedor extends ListBox {

    private VendedorProxy selectedItem;
    private ArrayList<VendedorProxy> data = new ArrayList();

    public ListModelVendedor() {
    }

    public ListModelVendedor(ArrayList<VendedorProxy> datos) {
        data = datos;
    }

    public void setData(List<VendedorProxy> datos) {
        if(data.isEmpty()){
        data.addAll(datos);        
        }else{
            clear();
            data.addAll(datos);            
        }
        for (int i = 0; i < data.size(); i++) {
            insertItem(data.get(i).getApellidoPaterno()+" "+data.get(i).getApellidoMaterno()+" "+data.get(i).getPrimerNombre()+" "+data.get(i).getSegundoNombre(), data.get(i).getIdVendedor().toString(), i);
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

    public VendedorProxy getElement(int index) throws ArrayIndexOutOfBoundsException {
        try {
            return data.get(index);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    public VendedorProxy getElement(String codigo) {        
        Iterator<VendedorProxy> i = data.iterator();
        while (i.hasNext()) {
            VendedorProxy bean = (VendedorProxy) i.next();
            if (bean.getIdVendedor().equals(codigo)) {                
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
            VendedorProxy bean = data.get(i);
            if (bean.getNomVendedor().toString().equals(item)) {                
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
    
    public VendedorProxy getSelectedItem() {
        selectedItem=data.get(getSelectElement().getSelectedIndex());
        return selectedItem;
    }

}
