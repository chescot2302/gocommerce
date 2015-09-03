/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Almacen;
import com.gocommerce.server.model.dao.DaoAlmacen;
import com.gocommerce.server.util.Parametro;
import com.gocommerce.server.util.UnknownException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class LogicAlmacen {
    private PersistenceManager pm;
    
    public LogicAlmacen(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<Almacen> getListarBean(Connection cnx,String idItem,String ubicacion) throws UnknownException{
        try {
            DaoAlmacen ado = new DaoAlmacen(pm);
            ArrayList<Parametro> parametros = new ArrayList();   
            Parametro param1=new Parametro("IN",idItem);
            if(!ubicacion.isEmpty() || ubicacion !=null){
            ubicacion=ubicacion.substring(1, ubicacion.length()-1);
            }
            Parametro param2=new Parametro("IN",ubicacion);            
            parametros.add(param1);
            parametros.add(param2);
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Almacen> lista = new ArrayList();
            while (rs.next()) {
                Almacen bean = new Almacen();
                bean.setId(rs.getString("codalm").trim());     
                bean.setDescripcion(rs.getString("Nomalm").trim());
                bean.setStockItemAlm(rs.getDouble("stock"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<Almacen> listarAlmXLocalidad(Connection cnx,String idItem,String idLocalidad,String idAlmacen) throws UnknownException{
        try {
            DaoAlmacen ado = new DaoAlmacen(pm);
            ArrayList<Parametro> parametros = new ArrayList();   
            Parametro param1=new Parametro("IN",idItem);
            Parametro param2=new Parametro("IN",idLocalidad);            
            Parametro param3=new Parametro("IN",idAlmacen);            
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            ArrayList objetos = ado.listarAlmXLocalidad(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Almacen> lista = new ArrayList();
            while (rs.next()) {
                Almacen bean = new Almacen();
                bean.setId(rs.getString("codalm").trim());     
                bean.setDescripcion(rs.getString("Nomalm").trim());
                bean.setStockItemAlm(rs.getDouble("stock"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
