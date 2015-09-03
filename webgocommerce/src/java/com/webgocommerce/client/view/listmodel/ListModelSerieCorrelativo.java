/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.listmodel;

import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;
import com.webgocommerce.client.beanproxy.CorrelativoProxy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class ListModelSerieCorrelativo  extends ListBox {

    private CorrelativoProxy selectedItem;
    private ArrayList<CorrelativoProxy> data = new ArrayList();

    public ListModelSerieCorrelativo() {
    }

    public ListModelSerieCorrelativo(ArrayList<CorrelativoProxy> datos) {
        data = datos;
    }

    public void setData(List<CorrelativoProxy> datos) {        
        if(data.isEmpty()){            
        data.addAll(datos);        
        }else{
            clear();
            data.addAll(datos);            
        }        
        for (int i = 0; i < data.size(); i++) {
            insertItem(data.get(i).getSerie(), "com.gocommerce.server.model.beans.Correlativo"+"::"+data.get(i).getIdDocumento()+"::"+data.get(i).getIdPuntoEmision(), i);
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

    public CorrelativoProxy getElement(int index) throws ArrayIndexOutOfBoundsException {
        try {
            return data.get(index);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    @Override
    public void setSelectedIndex(int index) {        
        getSelectElement().setSelectedIndex(index);
        selectedItem=data.get(getSelectElement().getSelectedIndex());
    }
        
    public void setSelectedItem(String item) {  
        if(item!=null){
        int index=-1;
        for(int i=0;i<data.size();i++){
            CorrelativoProxy bean = data.get(i);
            if (bean.getNombreDocumento().toString().equals(item)) {                
                index=i;
            }
        }
        if (index >= 0) {
        getSelectElement().setSelectedIndex(index);
        selectedItem=data.get(getSelectElement().getSelectedIndex());
        }
        }
    }
    
    @Override
    public int getSelectedIndex() {        
    return getSelectElement().getSelectedIndex();
    }
    
    public CorrelativoProxy getSelectedItem() {
        selectedItem=data.get(getSelectElement().getSelectedIndex());
        return selectedItem;
    }

    public ArrayList<CorrelativoProxy> getData() {
        return data;
    }        

}
