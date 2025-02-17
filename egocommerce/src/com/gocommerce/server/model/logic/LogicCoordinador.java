/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Coordinador;
import com.gocommerce.server.model.dao.DaoCoordinador;
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
public class LogicCoordinador {
    private PersistenceManager pm;
    
    public LogicCoordinador(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<Coordinador> getListarBean(Connection cnx,String estado) throws UnknownException{
        try {
            DaoCoordinador ado = new DaoCoordinador(pm);
            ArrayList<Parametro> parametros = new ArrayList();            
            Parametro param1=new Parametro("IN",estado);
            parametros.add(param1);
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Coordinador> lista = new ArrayList();
            while (rs.next()) {
                Coordinador bean = new Coordinador();
                bean.setId(rs.getInt("IDCOORDINADOR"));                                                
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
            Logger.getLogger(LogicCoordinador.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> insertarCoordinador(Connection cnx,Coordinador bean) throws UnknownException{
        try {
            DaoCoordinador ado = new DaoCoordinador(pm);
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
            ArrayList objetos = ado.insertarCoordinador(parametros, cnx);
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
            Logger.getLogger(LogicCoordinador.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }    
    
    public ArrayList<String> actualizarCoordinador(Connection cnx,Coordinador bean) throws UnknownException{
        try {
            DaoCoordinador ado = new DaoCoordinador(pm);
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
            ArrayList objetos = ado.actualizarCoordinador(parametros, cnx);
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
            Logger.getLogger(LogicCoordinador.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> actDesCoordinador(Connection cnx,Coordinador bean) throws UnknownException{
        try {
            DaoCoordinador ado = new DaoCoordinador(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",bean.getId());
            Parametro param1=new Parametro("IN",bean.getEstado());
            Parametro param2=new Parametro("IN",new java.sql.Date(bean.getFechaInc().getTime()));            
            parametros.add(param0);
            parametros.add(param1);            
            parametros.add(param2);            
            ArrayList objetos = ado.actDesCoordinador(parametros, cnx);
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
            Logger.getLogger(LogicCoordinador.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> eliminarCoordinador(Connection cnx,Coordinador bean) throws UnknownException{
        try {
            DaoCoordinador ado = new DaoCoordinador(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getId());            
            parametros.add(param1);            
            ArrayList objetos = ado.eliminarCoordinador(parametros, cnx);
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
            Logger.getLogger(LogicCoordinador.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
