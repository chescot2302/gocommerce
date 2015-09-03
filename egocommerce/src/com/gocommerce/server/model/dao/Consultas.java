/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.model.dao;

import com.gocommerce.server.util.Parametro;
import com.gocommerce.server.util.UnknownException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC-Docente
 */
public class Consultas {
    
    
    public ArrayList funcion(
            String cadenaProcFun,
            ArrayList<Parametro> param,
            Connection cnx
            ) throws SQLException, Exception{
        ArrayList objetos=new ArrayList();
        CallableStatement cst;
        cst=cnx.prepareCall(cadenaProcFun);
        if(numeroParametros(cadenaProcFun)==param.size()){
            for(int i=0;i<param.size();i++){                
                if(param.get(i).getInOrOut().equalsIgnoreCase("OUT")){                    
                    try{
                        System.out.println(param.get(i).getValor());
                    int out=Integer.parseInt(String.valueOf(param.get(i).getValor()));
                    System.out.println(out);
                    cst.registerOutParameter(i+1, out);
                    }catch(Exception ex){
                        throw new UnknownException("Error - Los parametros de salida son solo tipo Integer/n"+ex.getMessage());
                    }                    
                }else if(param.get(i).getInOrOut().equalsIgnoreCase("IN")){
                    Object in=(Object)param.get(i).getValor();
                    cst.setObject(i+1, in);
                }else{
                    throw new UnknownException("Por favor define tus parametros de entrada(IN) o salida(OUT)/n");
                }
            }              
            cst.execute();
            for(int i=0;i<param.size();i++){
                if(param.get(i).getInOrOut().equalsIgnoreCase("OUT")){
                    int j=i+1;
                    Object objeto=cst.getObject(j);
                    System.out.println(cst.getString(j));
                    objetos.add(objeto);
                }
            }
            objetos.add(cst);            
            return objetos;
        }else{
            throw new Exception("Error - Nùmero de signos ? diferente del nùmero de parametros");
        }
    }     
    
    public ArrayList funcionSQLServerResultSet(
            String cadenaProcFun,
            ArrayList<Parametro> param,
            Connection cnx
            ) throws SQLException, Exception{
        ArrayList objetos=new ArrayList();
        CallableStatement cst;
        cst=cnx.prepareCall(cadenaProcFun);
        if(numeroParametros(cadenaProcFun)==param.size()){
            for(int i=0;i<param.size();i++){                
                if(param.get(i).getInOrOut().equalsIgnoreCase("OUT")){                    
                    try{
                        System.out.println(param.get(i).getValor());
                    int out=Integer.parseInt(String.valueOf(param.get(i).getValor()));
                    System.out.println(out);
                    cst.registerOutParameter(i+1, out);
                    }catch(Exception ex){
                        throw new UnknownException("Error - Los parametros de salida son solo tipo Integer/n"+ex.getMessage());
                    }                    
                }else if(param.get(i).getInOrOut().equalsIgnoreCase("IN")){
                    Object in=(Object)param.get(i).getValor();
                    cst.setObject(i+1, in);
                }else{
                    throw new UnknownException("Por favor define tus parametros de entrada(IN) o salida(OUT)/n");
                }
            }              
            ResultSet rs=cst.executeQuery();
            for(int i=0;i<param.size();i++){
                if(param.get(i).getInOrOut().equalsIgnoreCase("OUT")){
                    int j=i+1;
                    Object objeto=cst.getObject(j);
                    System.out.println(cst.getString(j));
                    objetos.add(objeto);
                }
            }
            objetos.add(rs);            
            objetos.add(cst);            
            return objetos;
        }else{
            throw new Exception("Error - Nùmero de signos ? diferente del nùmero de parametros");
        }
    }
    
    private int numeroParametros(String consulta){
        int contador=0;
        for(int i=0;i<consulta.length();i++){
            if(String.valueOf(consulta.charAt(i)).equalsIgnoreCase("?")){
                contador=contador+1;
            }
        }
        return contador;
    }
}
