/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.GerenteZonal;
import com.gocommerce.server.model.dao.DaoGerenteZonal;
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
public class LogicGerenteZonal {
    private PersistenceManager pm;
    
    public LogicGerenteZonal(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<GerenteZonal> getListarBean(Connection cnx,String estado) throws UnknownException{
        try {
            DaoGerenteZonal ado = new DaoGerenteZonal(pm);
            ArrayList<Parametro> parametros = new ArrayList();            
            Parametro param1=new Parametro("IN",estado);
            parametros.add(param1);
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<GerenteZonal> lista = new ArrayList();
            while (rs.next()) {
                GerenteZonal bean = new GerenteZonal();
                bean.setId(rs.getInt("IDGERENTEZONAL"));                                                
                bean.setNombres(rs.getString("NOMBRES"));
                bean.setApellidos(rs.getString("APELLIDOS"));                
                bean.setDni(rs.getString("DNI"));
                bean.setEstado(rs.getInt("ESTADO"));                
                bean.setCorreo(rs.getString("CORREO"));
                bean.setCelular(rs.getString("CELULAR"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicGerenteZonal.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> insertarGerenteZonal(Connection cnx,GerenteZonal bean) throws UnknownException{
        try {
            DaoGerenteZonal ado = new DaoGerenteZonal(pm);
            ArrayList<Parametro> parametros = new ArrayList();             
            Parametro param3=new Parametro("IN",bean.getDni());
            Parametro param4=new Parametro("IN",bean.getNombres());
            Parametro param5=new Parametro("IN",bean.getApellidos());                                    
            Parametro param11=new Parametro("IN",bean.getCorreo());
            Parametro param12=new Parametro("IN",bean.getCelular());
            Parametro param13=new Parametro("IN",new java.sql.Date(bean.getFechaInc().getTime()));
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);            
            parametros.add(param11);
            parametros.add(param12);
            parametros.add(param13);
            ArrayList objetos = ado.insertarGerenteZonal(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<String> lista = new ArrayList();
            while (rs.next()) {                
                lista.add(rs.getString("MSG"));
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicGerenteZonal.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }    
    
    public ArrayList<String> actualizarGerenteZonal(Connection cnx,GerenteZonal bean) throws UnknownException{
        try {
            DaoGerenteZonal ado = new DaoGerenteZonal(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",bean.getId());            
            Parametro param3=new Parametro("IN",bean.getDni());
            Parametro param4=new Parametro("IN",bean.getNombres());
            Parametro param5=new Parametro("IN",bean.getApellidos());                        
            Parametro param11=new Parametro("IN",bean.getCorreo());
            Parametro param12=new Parametro("IN",bean.getCelular());
            parametros.add(param0);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);            
            parametros.add(param11);
            parametros.add(param12);            
            ArrayList objetos = ado.actualizarGerenteZonal(parametros, cnx);
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
            Logger.getLogger(LogicGerenteZonal.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> actDesGerenteZonal(Connection cnx,GerenteZonal bean) throws UnknownException{
        try {
            DaoGerenteZonal ado = new DaoGerenteZonal(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",bean.getId());
            Parametro param1=new Parametro("IN",bean.getEstado());
            Parametro param2=new Parametro("IN",new java.sql.Date(bean.getFechaInc().getTime()));            
            parametros.add(param0);
            parametros.add(param1);            
            parametros.add(param2);            
            ArrayList objetos = ado.actDesGerenteZonal(parametros, cnx);
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
            Logger.getLogger(LogicGerenteZonal.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> eliminarGerenteZonal(Connection cnx,GerenteZonal bean) throws UnknownException{
        try {
            DaoGerenteZonal ado = new DaoGerenteZonal(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getId());            
            parametros.add(param1);            
            ArrayList objetos = ado.eliminarGerenteZonal(parametros, cnx);
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
            Logger.getLogger(LogicGerenteZonal.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
