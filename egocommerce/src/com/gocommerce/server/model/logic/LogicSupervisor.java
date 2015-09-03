/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Supervisor;
import com.gocommerce.server.model.dao.DaoSupervisor;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.Parametro;
import com.gocommerce.server.util.UnknownException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class LogicSupervisor {
    private PersistenceManager pm;
    
    public LogicSupervisor(PersistenceManager pm) {
        this.pm=pm;
    }

    public boolean mantenimiento(BeanParametro parametro)
            throws UnknownException {
        DaoSupervisor dao = new DaoSupervisor(this.pm);
        return dao.mantenimiento(parametro);
    }

    public Object getBean(Integer id) throws UnknownException {
        DaoSupervisor dao = new DaoSupervisor(this.pm);
        return dao.getBean(id);
    }        

    public Collection<Supervisor> getListarBean() throws UnknownException {
        DaoSupervisor dao = new DaoSupervisor(this.pm);
        Collection<Supervisor> lista = dao.getListarBean();
        return lista;
    }
    
    public ArrayList<Supervisor> getListarBean(Connection cnx,String estado) throws UnknownException{
        try {
            DaoSupervisor ado = new DaoSupervisor(pm);
            ArrayList<Parametro> parametros = new ArrayList();            
            Parametro param1=new Parametro("IN",estado);
            parametros.add(param1);
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Supervisor> lista = new ArrayList();
            while (rs.next()) {
                Supervisor bean = new Supervisor();
                bean.setIdSupervisor(rs.getInt("IDSUPERVISOR"));                                
                bean.setCodigoAlterno(rs.getString("CODIGOALTERNO"));
                bean.setNombres(rs.getString("NOMBRES"));
                bean.setApellidos(rs.getString("APELLIDOS"));                
                bean.setDni(rs.getString("DNI"));
                bean.setIdPtoEmision(rs.getString("CODPTO"));
                bean.setNomPtoEmision(rs.getString("NOMPTO"));
                bean.setEstado(rs.getInt("ESTADO"));
                bean.setIdSucursal(rs.getString("CODSUC"));
                bean.setNomSucursal(rs.getString("NOMSUC"));
                bean.setIdTienda(rs.getString("CODTIE"));
                bean.setNomTienda(rs.getString("NOMTIE"));
                bean.setIdMesa(rs.getInt("IDMESA"));
                bean.setNomMesa(rs.getString("NOMMESA"));
                bean.setCanal(rs.getString("CANAL"));
                bean.setCorreo(rs.getString("CORREO"));
                bean.setCelular(rs.getString("CELULAR"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicSupervisor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<Supervisor> getListarSupervisorCl(Connection cnx) throws UnknownException{
        try {
            DaoSupervisor ado = new DaoSupervisor(pm);
            ArrayList<Parametro> parametros = new ArrayList();                                   
            ArrayList objetos = ado.getListarSupervisorCl(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Supervisor> lista = new ArrayList();
            while (rs.next()) {
                Supervisor bean = new Supervisor();
                bean.setIdSupervisor(rs.getInt("IDSUPERVISOR"));                                
                bean.setCodigoAlterno(rs.getString("CODIGOALTERNO"));
                bean.setNombres(rs.getString("NOMBRES"));
                bean.setApellidos(rs.getString("APELLIDOS"));                
                bean.setDni(rs.getString("DNI"));
                bean.setIdPtoEmision(rs.getString("CODPTO"));
                bean.setNomPtoEmision(rs.getString("NOMPTO"));
                bean.setEstado(rs.getInt("ESTADO"));
                bean.setIdSucursal(rs.getString("CODSUC"));
                bean.setNomSucursal(rs.getString("NOMSUC"));
                bean.setIdTienda(rs.getString("CODTIE"));
                bean.setNomTienda(rs.getString("NOMTIE"));
                bean.setIdMesa(rs.getInt("IDMESA"));
                bean.setNomMesa(rs.getString("NOMMESA"));
                bean.setCanal(rs.getString("CANAL"));
                bean.setCorreo(rs.getString("CORREO"));
                bean.setCelular(rs.getString("CELULAR"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicSupervisor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> insertarSupervisor(Connection cnx,Supervisor bean) throws UnknownException{
        try {
            DaoSupervisor ado = new DaoSupervisor(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getIdPtoEmision());
            Parametro param2=new Parametro("IN",bean.getCodigoAlterno());
            Parametro param3=new Parametro("IN",bean.getDni());
            Parametro param4=new Parametro("IN",bean.getNombres());
            Parametro param5=new Parametro("IN",bean.getApellidos());                        
            Parametro param10=new Parametro("IN",bean.getCanal());
            Parametro param11=new Parametro("IN",bean.getCorreo());
            Parametro param12=new Parametro("IN",bean.getCelular());
            Parametro param13=new Parametro("IN",new java.sql.Date(bean.getFechaInc().getTime()));
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);            
            parametros.add(param10);
            parametros.add(param11);
            parametros.add(param12);
            parametros.add(param13);
            ArrayList objetos = ado.insertarSupervisor(parametros, cnx);
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
            Logger.getLogger(LogicSupervisor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }    
    
    public ArrayList<String> actualizarSupervisor(Connection cnx,Supervisor bean) throws UnknownException{
        try {
            DaoSupervisor ado = new DaoSupervisor(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",bean.getIdSupervisor());
            Parametro param1=new Parametro("IN",bean.getIdPtoEmision());
            Parametro param2=new Parametro("IN",bean.getCodigoAlterno());
            Parametro param3=new Parametro("IN",bean.getDni());
            Parametro param4=new Parametro("IN",bean.getNombres());
            Parametro param5=new Parametro("IN",bean.getApellidos());            
            Parametro param10=new Parametro("IN",bean.getCanal());
            Parametro param11=new Parametro("IN",bean.getCorreo());
            Parametro param12=new Parametro("IN",bean.getCelular());
            parametros.add(param0);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);            
            parametros.add(param10);
            parametros.add(param11);
            parametros.add(param12);            
            ArrayList objetos = ado.actualizarSupervisor(parametros, cnx);
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
            Logger.getLogger(LogicSupervisor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> actDesSupervisor(Connection cnx,Supervisor bean) throws UnknownException{
        try {
            DaoSupervisor ado = new DaoSupervisor(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",bean.getIdSupervisor());
            Parametro param1=new Parametro("IN",bean.getEstado());
            Parametro param2=new Parametro("IN",new java.sql.Date(bean.getFechaInc().getTime()));            
            parametros.add(param0);
            parametros.add(param1);            
            parametros.add(param2);            
            ArrayList objetos = ado.actDesSupervisor(parametros, cnx);
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
            Logger.getLogger(LogicSupervisor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> eliminarSupervisor(Connection cnx,Supervisor bean) throws UnknownException{
        try {
            DaoSupervisor ado = new DaoSupervisor(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getIdSupervisor());            
            parametros.add(param1);            
            ArrayList objetos = ado.eliminarSupervisor(parametros, cnx);
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
            Logger.getLogger(LogicSupervisor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
