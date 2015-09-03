/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.util;

/**
 *
 * @author SISTEMAS
 */
public class NavaEncrypt {
    public static String encript(String value) throws Exception{
        String resultado="";
        for(int i=0;i<value.length();i++){                        
            char chr=value.charAt(i);              
            if(chr=='5'){
                resultado=resultado+String.valueOf('ž');         
                continue;
            }
            
            if(chr=='%'){
                resultado=resultado+String.valueOf('Ž');         
                continue;
            }
            
            if(chr==','){
                resultado=resultado+String.valueOf('•');         
                continue;
            }
            
            if(chr=='ñ' || chr=='Ñ' || chr=='°' || chr=='¬' || chr=='¡' || chr=='¿'  ){
                throw new Exception(String.valueOf(chr)+" es un caracter invalido");
            }
            int nuevoValor=chr+(210/2);
            char val=(char) nuevoValor;
            resultado=resultado+String.valueOf(val);            
        }
        return resultado;        
    }
    
    public static String decript(String value) throws Exception{
        String resultado="";
        for(int i=0;i<value.length();i++){                        
            char chr=value.charAt(i);
            if(chr==' '){
                resultado=resultado+String.valueOf('4');         
                continue;
            }
            
            if(chr=='ž'){
                resultado=resultado+String.valueOf('5');         
                continue;
            }
            
            if(chr=='Ž'){
                resultado=resultado+String.valueOf('%');         
                continue;
            }
            
            if(chr=='•'){
                resultado=resultado+String.valueOf(',');         
                continue;
            }
            
            if(chr=='š'){
                resultado=resultado+String.valueOf('1');         
                continue;
            }
            
            if(chr=='œ'){
                resultado=resultado+String.valueOf('3');         
                continue;
            }
            
            if(chr=='›'){
                resultado=resultado+String.valueOf('2');         
                continue;
            }
            
            if(chr=='™'){
                resultado=resultado+String.valueOf('0');         
                continue;
            }
            
            if(chr=='“'){
                resultado=resultado+String.valueOf('*');         
                continue;
            }
            
            if(chr=='Ÿ'){
                resultado=resultado+String.valueOf('6');         
                continue;
            }
            
            
            
            /*if(chr=='ñ' || chr=='Ñ' || chr=='°' || chr=='¬' || chr=='¡' || chr=='¿'  ){
                throw new Exception(String.valueOf(chr)+" es un caracter invalido");
            }*/
            int nuevoValor=chr-(210/2);
            char val=(char) nuevoValor;
            resultado=resultado+String.valueOf(val);            
        }
        return resultado;        
    }
}
