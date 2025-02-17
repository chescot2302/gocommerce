/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.listmodel;

import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;
import com.webgocommerce.client.beanproxy.SupervisorProxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class ListModelSupervisor extends ListBox {

    private SupervisorProxy selectedItem;
    private ArrayList<SupervisorProxy> data = new ArrayList();

    public ListModelSupervisor() {
    }

    public ListModelSupervisor(ArrayList<SupervisorProxy> datos) {
        data = datos;
    }

    public void setData(List<SupervisorProxy> datos) {
        if(data.isEmpty()){
        data.addAll(datos);        
        }else{
            clear();
            data.addAll(datos);            
        }
        for (int i = 0; i < data.size(); i++) {
            insertItem(data.get(i).getApellidos()+" "+data.get(i).getNombres(), data.get(i).getIdSupervisor().toString(), i);
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

    public SupervisorProxy getElement(int index) throws ArrayIndexOutOfBoundsException {
        try {
            return data.get(index);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    public SupervisorProxy getElement(String codigo) {        
        Iterator<SupervisorProxy> i = data.iterator();
        while (i.hasNext()) {
            SupervisorProxy bean = (SupervisorProxy) i.next();
            if (bean.getIdSupervisor().equals(codigo)) {                
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
    
    public void removeAllItem(){
        selectedItem=null;
        data.clear();
        this.clear();
    }
        
    public void setSelectedItem(String item) {  
        int index=-1;
        for(int i=0;i<data.size();i++){
            SupervisorProxy bean = data.get(i);
            if (bean.getIdSupervisor().toString().equals(item)) {                
                index=i;
                break;
            }
        }
        if (index >= 0) {
        getSelectElement().setSelectedIndex(index);
        selectedItem=data.get(getSelectElement().getSelectedIndex());
        }
    }
    
    @Override
    public int getSelectedIndex() {        
    return getSelectElement().getSelectedIndex();
    }
    
    public SupervisorProxy getSelectedItem() {
        selectedItem=data.get(getSelectElement().getSelectedIndex());
        return selectedItem;
    }

}
