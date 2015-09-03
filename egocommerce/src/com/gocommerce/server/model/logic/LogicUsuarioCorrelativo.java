/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.UsuarioCorrelativo;
import com.gocommerce.server.model.dao.DaoUsuarioCorrelativo;
import com.gocommerce.server.util.Parametro;
import com.gocommerce.server.util.UnknownException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class LogicUsuarioCorrelativo {
    private PersistenceManager pm;
    
    public LogicUsuarioCorrelativo(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<UsuarioCorrelativo> getListarBean(Connection cnx) throws UnknownException{
        try {
            DaoUsuarioCorrelativo ado = new DaoUsuarioCorrelativo(pm);
            ArrayList<Parametro> parametros = new ArrayList();            
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<UsuarioCorrelativo> lista = new ArrayList();
            while (rs.next()) {
                UsuarioCorrelativo bean = new UsuarioCorrelativo();
                bean.setId(rs.getInt("IDUSERCORRE"));                                
                bean.setIdUsuario(rs.getString("CODUSU"));
                bean.setNomUsuario(rs.getString("NOMUSU"));
                bean.setNomAcceso(rs.getString("NOMACC"));
                bean.setIdBdUsuario(rs.getInt("IDBDUSUARIO"));
                bean.setIdDocumento(rs.getString("CDOCU"));
                bean.setNomDocumento(rs.getString("NOMDOC"));
                bean.setIdPuntoEmision(rs.getString("CODPTO"));
                bean.setNomPtoEmision(rs.getString("NOMPTO"));
                bean.setSerie(rs.getString("SERIE"));
                bean.setFechaIni(rs.getDate("FECHAINI"));
                bean.setFechaFin(rs.getDate("FECHAFIN"));
                bean.setEstado(rs.getString("ESTADO"));
                bean.setIdSucursal(rs.getString("CODSUC"));
                bean.setNomSucursal(rs.getString("NOMSUC"));
                bean.setIdTienda(rs.getString("CODTIE"));
                bean.setNomTienda(rs.getString("NOMTIE"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> insertarUsuarioCorrelativo(Connection cnx,UsuarioCorrelativo bean) throws UnknownException{
        try {
            Date fecha=new Date();
            DaoUsuarioCorrelativo ado = new DaoUsuarioCorrelativo(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getIdUsuario());
            Parametro param2=new Parametro("IN",bean.getIdBdUsuario());
            Parametro param3=new Parametro("IN",bean.getIdDocumento());
            Parametro param4=new Parametro("IN",bean.getIdPuntoEmision());
            Parametro param5=new Parametro("IN",bean.getSerie());
            Parametro param6=new Parametro("IN",new java.sql.Date(fecha.getTime()));
            Parametro param7=new Parametro("IN",bean.getFechaFin());
            Parametro param8=new Parametro("IN","A");
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);
            parametros.add(param6);
            parametros.add(param7);
            parametros.add(param8);
            ArrayList objetos = ado.insertarUsuarioCorrelativo(parametros, cnx);
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
            Logger.getLogger(LogicUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> actualizarUsuarioCorrelativo(Connection cnx,UsuarioCorrelativo bean) throws UnknownException{
        try {
            DaoUsuarioCorrelativo ado = new DaoUsuarioCorrelativo(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",bean.getId());
            Parametro param1=new Parametro("IN",bean.getIdUsuario());
            Parametro param2=new Parametro("IN",bean.getIdBdUsuario());
            Parametro param3=new Parametro("IN",bean.getIdDocumento());
            Parametro param4=new Parametro("IN",bean.getIdPuntoEmision());
            Parametro param5=new Parametro("IN",bean.getSerie());
            parametros.add(param0);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);
            ArrayList objetos = ado.actualizarUsuarioCorrelativo(parametros, cnx);
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
            Logger.getLogger(LogicUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> desactivarUsuarioCorrelativo(Connection cnx,UsuarioCorrelativo bean) throws UnknownException{
        try {
            Date fecha=new Date();
            DaoUsuarioCorrelativo ado = new DaoUsuarioCorrelativo(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",bean.getId());
            Parametro param1=new Parametro("IN",new java.sql.Date(fecha.getTime()));            
            parametros.add(param0);
            parametros.add(param1);            
            ArrayList objetos = ado.desactivarUsuarioCorrelativo(parametros, cnx);
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
            Logger.getLogger(LogicUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> eliminarUsuarioCorrelativo(Connection cnx,UsuarioCorrelativo bean) throws UnknownException{
        try {
            DaoUsuarioCorrelativo ado = new DaoUsuarioCorrelativo(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getId());            
            parametros.add(param1);            
            ArrayList objetos = ado.eliminarUsuarioCorrelativo(parametros, cnx);
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
            Logger.getLogger(LogicUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    
}
