/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.gjson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class GjsonArray implements Serializable{
    public final static String LIMITLEFT="[";
    public final static String LIMITRIGHT="]";
    private List<GjsonObject> array=new ArrayList();
    private List<GjsonArray> hijos=new ArrayList();
    private StringBuilder gjsonArray=new StringBuilder();
    
    public void putElement(GjsonObject element){
        array.add(element);
    }

    public List<GjsonObject> getArray() {
        return array;
    }

    public void setArray(List<GjsonObject> array) {
        this.array = array;
    }

    public List<GjsonArray> getHijos() {
        return hijos;
    }

    public void setHijos(List<GjsonArray> hijos) {
        this.hijos = hijos;
    }
    
    public void addHijo(GjsonArray bean){
        hijos.add(bean);
    }
    
    public void removeHijo(GjsonArray bean){
        hijos.remove(bean);
    }

    
    public StringBuilder getGjsonArray() {
        return gjsonArray;
    }

    public void setGjsonArray(StringBuilder gjsonArray) {
        this.gjsonArray = gjsonArray;
    }           
    
    public static void buildGjsonArray(GjsonArray objPadre){
        cleanStrGjsonArray(objPadre);
        if(objPadre.getArray().isEmpty() && objPadre.getHijos().isEmpty()){
            return;
        }else{
            objPadre.getGjsonArray().append(LIMITLEFT);
        }
        Iterator<GjsonObject> iterador=objPadre.getArray().iterator();        
        while(iterador.hasNext()){
            GjsonObject bean=iterador.next();
            GjsonObject.buildGjsonObject(bean);
            objPadre.getGjsonArray().append(bean.getGjsonObject());
            objPadre.getGjsonArray().append(",");
        }
        if(objPadre.getHijos().isEmpty()){
            objPadre.getGjsonArray().deleteCharAt(objPadre.getGjsonArray().lastIndexOf(","));
        }else{
            for(int i=0;i<objPadre.getHijos().size();i++){
                buildGjsonArray(objPadre.getHijos().get(i));
                objPadre.getGjsonArray().append(objPadre.getHijos().get(i).getGjsonArray());
                objPadre.getGjsonArray().append(",");
            }
            objPadre.getGjsonArray().deleteCharAt(objPadre.getGjsonArray().lastIndexOf(","));
        }        
        objPadre.getGjsonArray().append(LIMITRIGHT);
    }        
    
    public static void buildGjsonArrayFormat(GjsonArray objPadre){
        cleanStrGjsonArray(objPadre);
        if(objPadre.getArray().isEmpty() && objPadre.getHijos().isEmpty()){
            return;
        }else{
            objPadre.getGjsonArray().append(LIMITLEFT);
        }
        Iterator<GjsonObject> iterador=objPadre.getArray().iterator();        
        while(iterador.hasNext()){
            GjsonObject bean=iterador.next();
            GjsonObject.buildGjsonObjectFormat(bean);
            objPadre.getGjsonArray().append(bean.getGjsonObject());
            objPadre.getGjsonArray().append(",");
            objPadre.getGjsonArray().append("\n");
        }
        if(objPadre.getHijos().isEmpty()){
            objPadre.getGjsonArray().deleteCharAt(objPadre.getGjsonArray().lastIndexOf(","));
        }else{
            for(int i=0;i<objPadre.getHijos().size();i++){
                buildGjsonArrayFormat(objPadre.getHijos().get(i));
                objPadre.getGjsonArray().append(objPadre.getHijos().get(i).getGjsonArray());
                objPadre.getGjsonArray().append(",");
                objPadre.getGjsonArray().append("\n");
            }
            objPadre.getGjsonArray().deleteCharAt(objPadre.getGjsonArray().lastIndexOf(","));
        }        
        objPadre.getGjsonArray().append(LIMITRIGHT);
    }
    
    public static void buildJsonArray(GjsonArray objPadre){
        cleanStrGjsonArray(objPadre);
        if(objPadre.getArray().isEmpty() && objPadre.getHijos().isEmpty()){
            return;
        }else{
            objPadre.getGjsonArray().append(LIMITLEFT);
        }
        Iterator<GjsonObject> iterador=objPadre.getArray().iterator();        
        while(iterador.hasNext()){
            GjsonObject bean=iterador.next();
            GjsonObject.buildJsonObject(bean);
            objPadre.getGjsonArray().append(bean.getGjsonObject());
            objPadre.getGjsonArray().append(",");
        }
        if(objPadre.getHijos().isEmpty()){
            objPadre.getGjsonArray().deleteCharAt(objPadre.getGjsonArray().lastIndexOf(","));
        }else{
            for(int i=0;i<objPadre.getHijos().size();i++){
                buildJsonArray(objPadre.getHijos().get(i));
                objPadre.getGjsonArray().append(objPadre.getHijos().get(i).getGjsonArray());
                objPadre.getGjsonArray().append(",");
            }
            objPadre.getGjsonArray().deleteCharAt(objPadre.getGjsonArray().lastIndexOf(","));
        }        
        objPadre.getGjsonArray().append(LIMITRIGHT);
    }
    
    
    public static void buildJsonArrayFormat(GjsonArray objPadre){
        cleanStrGjsonArray(objPadre);
        if(objPadre.getArray().isEmpty() && objPadre.getHijos().isEmpty()){
            return;
        }else{
            objPadre.getGjsonArray().append(LIMITLEFT);
        }
        Iterator<GjsonObject> iterador=objPadre.getArray().iterator();        
        while(iterador.hasNext()){
            GjsonObject bean=iterador.next();
            GjsonObject.buildJsonObjectFormat(bean);
            objPadre.getGjsonArray().append(bean.getGjsonObject());
            objPadre.getGjsonArray().append(",");
            objPadre.getGjsonArray().append("\n");
        }
        if(objPadre.getHijos().isEmpty()){
            objPadre.getGjsonArray().deleteCharAt(objPadre.getGjsonArray().lastIndexOf(","));
        }else{
            for(int i=0;i<objPadre.getHijos().size();i++){
                buildJsonArrayFormat(objPadre.getHijos().get(i));
                objPadre.getGjsonArray().append(objPadre.getHijos().get(i).getGjsonArray());
                objPadre.getGjsonArray().append(",");
                objPadre.getGjsonArray().append("\n");
            }
            objPadre.getGjsonArray().deleteCharAt(objPadre.getGjsonArray().lastIndexOf(","));
        }        
        objPadre.getGjsonArray().append(LIMITRIGHT);
    }
    
    public static void cleanStrGjsonArray(GjsonArray objPadre){       
        objPadre.getGjsonArray().delete(0, objPadre.getGjsonArray().length());
    }    
}
