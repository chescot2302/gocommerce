/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Sucursal;
import com.gocommerce.server.model.dao.DaoSucursal;
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
public class LogicSucursal {
    private PersistenceManager pm;

    public LogicSucursal(PersistenceManager pm) {
        this.pm = pm;
    }
    

    public ArrayList<Sucursal> getListarBean(Connection cnx) throws UnknownException {
        try {
            DaoSucursal ado = new DaoSucursal(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Sucursal> lista = new ArrayList();
            while (rs.next()) {
                Sucursal bean = new Sucursal();
                bean.setId(rs.getString("CodSuc").trim());                
                bean.setDescripcion(rs.getString("NomSuc").trim());
                bean.setIdLocalidad(rs.getInt("idlocalidad"));   
                bean.setNomLocalidad(rs.getString("nomLocalidad"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicSucursal.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<Sucursal> getListarSucursalCla(Connection cnx) throws UnknownException {
        try {
            DaoSucursal ado = new DaoSucursal(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            ArrayList objetos = ado.getListarSucursalCla(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Sucursal> lista = new ArrayList();
            while (rs.next()) {
                Sucursal bean = new Sucursal();
                bean.setId(rs.getString("CodSuc").trim());                
                bean.setDescripcion(rs.getString("NomSuc").trim());
                bean.setIdLocalidad(rs.getInt("idlocalidad"));   
                bean.setNomLocalidad(rs.getString("nomLocalidad"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicSucursal.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    
    public ArrayList<String> insertarSucursal(Connection cnx,Sucursal bean) throws UnknownException{
        try {
            DaoSucursal ado = new DaoSucursal(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param2=new Parametro("IN",bean.getDescripcion());
            Parametro param3=new Parametro("IN",bean.getIdLocalidad());            
            parametros.add(param2);
            parametros.add(param3);            
            ArrayList objetos = ado.insertarSucursal(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<String> lista = new ArrayList();
            while (rs.next()) {                
                lista.add(rs.getString(1));
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicSucursal.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> actualizarSucursal(Connection cnx,Sucursal bean) throws UnknownException{
        try {
            DaoSucursal ado = new DaoSucursal(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",bean.getId());
            Parametro param1=new Parametro("IN",bean.getDescripcion());
            Parametro param2=new Parametro("IN",bean.getIdLocalidad());            
            parametros.add(param0);
            parametros.add(param1);
            parametros.add(param2);            
            ArrayList objetos = ado.actualizarSucursal(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<String> lista = new ArrayList();
            while (rs.next()) {                
                lista.add(rs.getString(1));
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicSucursal.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }        
    
    public ArrayList<String> eliminarSucursal(Connection cnx,Sucursal bean) throws UnknownException{
        try {
            DaoSucursal ado = new DaoSucursal(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getId());            
            parametros.add(param1);            
            ArrayList objetos = ado.eliminarSucursal(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<String> lista = new ArrayList();
            while (rs.next()) {                
                lista.add(rs.getString(1));
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicSucursal.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
}
