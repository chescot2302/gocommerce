/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.gjson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */

public class GjsonObject implements Serializable{ 
    private TypeGjson tipo;
    private String className;
    public final static String LIMITLEFT="{";
    public final static String LIMITRIGHT="}";
    private Map<GjsonKey,Object> map=new HashMap();
    private List<Map<GjsonKey,GjsonObject>> hijos=new ArrayList();
    private List<Map<GjsonKey,GjsonArray>> arrays=new ArrayList();
    private StringBuilder gjsonObject=new StringBuilder(); 
    
    public GjsonObject(TypeGjson tipo,String className){        
        this.tipo=tipo;
        this.className=className;
    }
    
    public void putElement(GjsonKey key,Object value){            
        map.put(key, value);
    }

    public Map<GjsonKey, Object> getMap() {
        return map;
    }

    public void setMap(Map<GjsonKey, Object> map) {
        this.map = map;
    }

    public List<Map<GjsonKey,GjsonObject>> getHijos() {
        return hijos;
    }

    public void setHijos(List<Map<GjsonKey,GjsonObject>> hijos) {
        this.hijos = hijos;
    }        
    
    public void addHijo(Map<GjsonKey,GjsonObject> bean){
        hijos.add(bean);
    }

    public List<Map<GjsonKey,GjsonArray>> getArrays() {
        return arrays;
    }

    public void setArrays(List<Map<GjsonKey,GjsonArray>> arrays) {
        this.arrays = arrays;
    }
    
    public void addArray(Map<GjsonKey,GjsonArray> array){
        arrays.add(array);
    }
    
    public void removeHijo(GjsonObject bean){
        hijos.remove(bean);
    }

    public StringBuilder getGjsonObject() {
        return gjsonObject;
    }

    public void setGjsonObject(StringBuilder gjsonObject) {
        this.gjsonObject = gjsonObject;
    }

    public TypeGjson getTipo() {
        return tipo;
    }

    public void setTipo(TypeGjson tipo) {
        this.tipo = tipo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }   
    
    
            
    public static void buildGjsonObject(GjsonObject objPadre){    
        cleanStrGjsonObject(objPadre);
        Set<GjsonKey> keys=objPadre.getMap().keySet();
        if(keys.isEmpty() && objPadre.getHijos().isEmpty()){
            return;
        }else{
            objPadre.getGjsonObject().append(LIMITLEFT);
        }
        Iterator<GjsonKey> iterador=keys.iterator();        
        while(iterador.hasNext()){
            GjsonKey bean=iterador.next();
            objPadre.getGjsonObject().append(bean.getKey());
            objPadre.getGjsonObject().append(":");
            objPadre.getGjsonObject().append(objPadre.getMap().get(bean));
            objPadre.getGjsonObject().append(",");
        }        
        for(int i=0;i<objPadre.getArrays().size();i++){    
            Map<GjsonKey,GjsonArray> mapArray=objPadre.getArrays().get(i);
            Set<GjsonKey> keysArray=mapArray.keySet();
            Iterator<GjsonKey> iterArray=keysArray.iterator();   
            while(iterArray.hasNext()){
                GjsonKey beanKeyArray=iterArray.next();
                objPadre.getGjsonObject().append(beanKeyArray.getKey());
                objPadre.getGjsonObject().append(":");
                objPadre.getGjsonObject().append(mapArray.get(beanKeyArray).getGjsonArray());                
                objPadre.getGjsonObject().append(",");
            }
        }        
        if(objPadre.getHijos().isEmpty()){
            objPadre.getGjsonObject().deleteCharAt(objPadre.getGjsonObject().lastIndexOf(","));
        }else{
            for(int i=0;i<objPadre.getHijos().size();i++){
                Map<GjsonKey,GjsonObject> mapObjects=objPadre.getHijos().get(i);
                Set<GjsonKey> keysObjects=mapObjects.keySet();
                Iterator<GjsonKey> iterObject=keysObjects.iterator();   
                while(iterObject.hasNext()){
                    GjsonKey beanKeyObject=iterObject.next();
                    objPadre.getGjsonObject().append(beanKeyObject.getKey());
                    objPadre.getGjsonObject().append(":");
                    buildGjsonObject(mapObjects.get(beanKeyObject));
                    objPadre.getGjsonObject().append(mapObjects.get(beanKeyObject).getGjsonObject());
                    objPadre.getGjsonObject().append(",");
                }
            }
            objPadre.getGjsonObject().deleteCharAt(objPadre.getGjsonObject().lastIndexOf(","));
        }        
        objPadre.getGjsonObject().append(LIMITRIGHT);        
    }    
    
    
    public static void buildGjsonObjectFormat(GjsonObject objPadre){    
        cleanStrGjsonObject(objPadre);
        Set<GjsonKey> keys=objPadre.getMap().keySet();
        if(keys.isEmpty() && objPadre.getHijos().isEmpty()){
            return;
        }else{
            objPadre.getGjsonObject().append(LIMITLEFT);
        }
        Iterator<GjsonKey> iterador=keys.iterator();        
        while(iterador.hasNext()){
            GjsonKey bean=iterador.next();
            objPadre.getGjsonObject().append(bean.getKey());
            objPadre.getGjsonObject().append(":");
            objPadre.getGjsonObject().append(objPadre.getMap().get(bean));
            objPadre.getGjsonObject().append(",");
            objPadre.getGjsonObject().append("\n");
        }
        for(int i=0;i<objPadre.getArrays().size();i++){    
            Map<GjsonKey,GjsonArray> mapArray=objPadre.getArrays().get(i);
            Set<GjsonKey> keysArray=mapArray.keySet();
            Iterator<GjsonKey> iterArray=keysArray.iterator();   
            while(iterArray.hasNext()){
                GjsonKey beanKeyArray=iterArray.next();
                objPadre.getGjsonObject().append(beanKeyArray.getKey());
                objPadre.getGjsonObject().append(":");
                objPadre.getGjsonObject().append(mapArray.get(beanKeyArray).getGjsonArray());                
                objPadre.getGjsonObject().append(",");
                objPadre.getGjsonObject().append("\n");
            }
        }        
        if(objPadre.getHijos().isEmpty()){
            objPadre.getGjsonObject().deleteCharAt(objPadre.getGjsonObject().lastIndexOf(","));
        }else{
            for(int i=0;i<objPadre.getHijos().size();i++){
                Map<GjsonKey,GjsonObject> mapObjects=objPadre.getHijos().get(i);
                Set<GjsonKey> keysObjects=mapObjects.keySet();
                Iterator<GjsonKey> iterObject=keysObjects.iterator();   
                while(iterObject.hasNext()){
                    GjsonKey beanKeyObject=iterObject.next();
                    objPadre.getGjsonObject().append(beanKeyObject.getKey());
                    objPadre.getGjsonObject().append(":");
                    buildGjsonObjectFormat(mapObjects.get(beanKeyObject));
                    objPadre.getGjsonObject().append(mapObjects.get(beanKeyObject).getGjsonObject());
                    objPadre.getGjsonObject().append(",");
                    objPadre.getGjsonObject().append("\n");
                }
            }            
            objPadre.getGjsonObject().deleteCharAt(objPadre.getGjsonObject().lastIndexOf(","));
        }        
        objPadre.getGjsonObject().append(LIMITRIGHT);        
    }
    
    public static void buildJsonObject(GjsonObject objPadre){    
        cleanStrGjsonObject(objPadre);
        Set<GjsonKey> keys=objPadre.getMap().keySet();
        if(keys.isEmpty() && objPadre.getHijos().isEmpty()){
            return;
        }else{
            if(objPadre.getTipo().equals(TypeGjson.COMPLEX)){
            objPadre.getGjsonObject().append(LIMITLEFT);
            }
        }
        Iterator<GjsonKey> iterador=keys.iterator();        
        while(iterador.hasNext()){
            GjsonKey bean=iterador.next();            
            if(objPadre.getTipo().equals(TypeGjson.COMPLEX)){
            objPadre.getGjsonObject().append("\"");
            objPadre.getGjsonObject().append(bean.getIdentificador());
            objPadre.getGjsonObject().append("\"");
            objPadre.getGjsonObject().append(":");
            }
            if(bean.getTipoDato().equalsIgnoreCase("String")){
                objPadre.getGjsonObject().append("\"");
                objPadre.getGjsonObject().append(objPadre.getMap().get(bean));
                objPadre.getGjsonObject().append("\"");
            }else{
                objPadre.getGjsonObject().append(objPadre.getMap().get(bean));
            }            
            objPadre.getGjsonObject().append(",");
        }
        for(int i=0;i<objPadre.getArrays().size();i++){    
            Map<GjsonKey,GjsonArray> mapArray=objPadre.getArrays().get(i);
            Set<GjsonKey> keysArray=mapArray.keySet();
            Iterator<GjsonKey> iterArray=keysArray.iterator();   
            while(iterArray.hasNext()){
                GjsonKey beanKeyArray=iterArray.next();
                objPadre.getGjsonObject().append("\"");
                objPadre.getGjsonObject().append(beanKeyArray.getIdentificador());
                objPadre.getGjsonObject().append("\"");
                objPadre.getGjsonObject().append(":");
                objPadre.getGjsonObject().append(mapArray.get(beanKeyArray).getGjsonArray());                
                objPadre.getGjsonObject().append(",");
            }
        }  
        if(objPadre.getHijos().isEmpty()){
            objPadre.getGjsonObject().deleteCharAt(objPadre.getGjsonObject().lastIndexOf(","));
        }else{
            for(int i=0;i<objPadre.getHijos().size();i++){
                Map<GjsonKey,GjsonObject> mapObjects=objPadre.getHijos().get(i);
                Set<GjsonKey> keysObjects=mapObjects.keySet();
                Iterator<GjsonKey> iterObject=keysObjects.iterator();   
                while(iterObject.hasNext()){
                    GjsonKey beanKeyObject=iterObject.next();
                    objPadre.getGjsonObject().append("\"");
                    objPadre.getGjsonObject().append(beanKeyObject.getIdentificador());
                    objPadre.getGjsonObject().append("\"");
                    objPadre.getGjsonObject().append(":");
                    buildJsonObject(mapObjects.get(beanKeyObject));
                    objPadre.getGjsonObject().append(mapObjects.get(beanKeyObject).getGjsonObject());
                    objPadre.getGjsonObject().append(",");
                }
            }            
            objPadre.getGjsonObject().deleteCharAt(objPadre.getGjsonObject().lastIndexOf(","));
        }        
        if(objPadre.getTipo().equals(TypeGjson.COMPLEX)){
        objPadre.getGjsonObject().append(LIMITRIGHT);    
        }
    }
    
    public static void buildJsonObjectFormat(GjsonObject objPadre){    
        cleanStrGjsonObject(objPadre);
        Set<GjsonKey> keys=objPadre.getMap().keySet();
        if(keys.isEmpty() && objPadre.getHijos().isEmpty()){
            return;
        }else{
            if(objPadre.getTipo().equals(TypeGjson.COMPLEX)){
            objPadre.getGjsonObject().append(LIMITLEFT);
            }
        }
        Iterator<GjsonKey> iterador=keys.iterator();        
        while(iterador.hasNext()){
            GjsonKey bean=iterador.next();            
            if(objPadre.getTipo().equals(TypeGjson.COMPLEX)){
            objPadre.getGjsonObject().append("\"");
            objPadre.getGjsonObject().append(bean.getIdentificador());
            objPadre.getGjsonObject().append("\"");
            objPadre.getGjsonObject().append(":");
            }
            if(bean.getTipoDato().equalsIgnoreCase("String")){
                objPadre.getGjsonObject().append("\"");
                objPadre.getGjsonObject().append(objPadre.getMap().get(bean));
                objPadre.getGjsonObject().append("\"");
            }else{
                objPadre.getGjsonObject().append(objPadre.getMap().get(bean));
            }            
            objPadre.getGjsonObject().append(",");
            objPadre.getGjsonObject().append("\n");
        }
        for(int i=0;i<objPadre.getArrays().size();i++){    
            Map<GjsonKey,GjsonArray> mapArray=objPadre.getArrays().get(i);
            Set<GjsonKey> keysArray=mapArray.keySet();
            Iterator<GjsonKey> iterArray=keysArray.iterator();   
            while(iterArray.hasNext()){
                GjsonKey beanKeyArray=iterArray.next();
                objPadre.getGjsonObject().append("\"");
                objPadre.getGjsonObject().append(beanKeyArray.getIdentificador());
                objPadre.getGjsonObject().append("\"");
                objPadre.getGjsonObject().append(":");
                objPadre.getGjsonObject().append(mapArray.get(beanKeyArray).getGjsonArray());                
                objPadre.getGjsonObject().append(",");
                objPadre.getGjsonObject().append("\n");
            }
        }          
        if(objPadre.getHijos().isEmpty()){
            objPadre.getGjsonObject().deleteCharAt(objPadre.getGjsonObject().lastIndexOf(","));
        }else{
            for(int i=0;i<objPadre.getHijos().size();i++){
                Map<GjsonKey,GjsonObject> mapObjects=objPadre.getHijos().get(i);
                Set<GjsonKey> keysObjects=mapObjects.keySet();
                Iterator<GjsonKey> iterObject=keysObjects.iterator();   
                while(iterObject.hasNext()){
                    GjsonKey beanKeyObject=iterObject.next();
                    objPadre.getGjsonObject().append("\"");
                    objPadre.getGjsonObject().append(beanKeyObject.getIdentificador());
                    objPadre.getGjsonObject().append("\"");
                    objPadre.getGjsonObject().append(":");
                    buildJsonObjectFormat(mapObjects.get(beanKeyObject));
                    objPadre.getGjsonObject().append(mapObjects.get(beanKeyObject).getGjsonObject());
                    objPadre.getGjsonObject().append(",");
                    objPadre.getGjsonObject().append("\n");
                }
            }            
            objPadre.getGjsonObject().deleteCharAt(objPadre.getGjsonObject().lastIndexOf(","));
        }        
        if(objPadre.getTipo().equals(TypeGjson.COMPLEX)){
        objPadre.getGjsonObject().append(LIMITRIGHT);    
        }
    }
    
    public static void cleanStrGjsonObject(GjsonObject objPadre){
        objPadre.getGjsonObject().delete(0, objPadre.getGjsonObject().length());
    }
    
    
        
}

