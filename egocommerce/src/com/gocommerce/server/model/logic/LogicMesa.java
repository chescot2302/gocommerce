/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Mesa;
import com.gocommerce.server.model.dao.DaoMesa;
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
public class LogicMesa {
    private PersistenceManager pm;
    
    public LogicMesa(PersistenceManager pm) {
        this.pm=pm;
    }

    public boolean mantenimiento(BeanParametro parametro)
            throws UnknownException {
        DaoMesa dao = new DaoMesa(this.pm);
        return dao.mantenimiento(parametro);
    }

    public Object getBean(Integer id) throws UnknownException {
        DaoMesa dao = new DaoMesa(this.pm);
        return dao.getBean(id);
    }        

    public Collection<Mesa> getListarBean() throws UnknownException {
        DaoMesa dao = new DaoMesa(this.pm);
        Collection<Mesa> lista = dao.getListarBean();
        return lista;
    }
    
    public ArrayList<Mesa> getListarBean(Connection cnx) throws UnknownException{
        try {
            DaoMesa ado = new DaoMesa(pm);
            ArrayList<Parametro> parametros = new ArrayList();               
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Mesa> lista = new ArrayList();
            while (rs.next()) {
                Mesa bean = new Mesa();                    
                bean.setIdMesa(rs.getInt("IDMESA"));                
                bean.setDescripcion(rs.getString("DESCRIPCION").trim());
                bean.setIdCoordinador(rs.getInt("IDCOORDINADOR"));
                bean.setIdGerenteZonal(rs.getInt("IDGERENTEZONAL"));
                bean.setIdSupervisor(rs.getInt("IDSUPERVISOR"));
                bean.setIdSucursal(rs.getString("IDSUCURSAL"));
                bean.setEstado(rs.getString("ESTADO"));  
                bean.setFechaIni(new java.util.Date(rs.getDate("FECHAINI").getTime()));
                if(rs.getString("ESTADO").equalsIgnoreCase("D")){
                    bean.setFechaFin(new java.util.Date(rs.getDate("FECHAFIN").getTime()));
                }
                bean.setNomCoordinador(rs.getString("NOMCOORDINADOR"));
                bean.setNomGerenteZonal(rs.getString("NOMGERENTEZONAL"));
                bean.setNomSupervisor(rs.getString("NOMSUPERVISOR"));
                bean.setNomSucursal(rs.getString("NOMSUCURSAL"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicMesa.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public Mesa getMesaxVendedor(Connection cnx,String idVendedor) throws UnknownException{
        try {
            DaoMesa ado = new DaoMesa(pm);
            ArrayList<Parametro> parametros = new ArrayList();               
            Parametro param1=new Parametro("IN",idVendedor);
            parametros.add(param1);
            ArrayList objetos = ado.getMesaxVendedor(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Mesa> lista = new ArrayList();
            while (rs.next()) {
                Mesa bean = new Mesa();                    
                bean.setIdMesa(rs.getInt("IDMESA"));                
                bean.setDescripcion(rs.getString("DESCRIPCION").trim());
                bean.setIdCoordinador(rs.getInt("IDCOORDINADOR"));
                bean.setIdGerenteZonal(rs.getInt("IDGERENTEZONAL"));
                bean.setIdSupervisor(rs.getInt("IDSUPERVISOR"));
                bean.setIdSucursal(rs.getString("IDSUCURSAL"));                
                bean.setNomSupervisor(rs.getString("NOMSUPERVISOR"));                
                bean.setIdSuperVen(rs.getInt("IDSUPERVEN"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            if(lista.size()==1){
                return lista.get(0);
            }else{
                throw new UnknownException("Error al obtener Mesa");
            }            
        } catch (Exception ex) {
            Logger.getLogger(LogicMesa.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> insertarMesa(Connection cnx,Mesa bean) throws UnknownException{
        try {
            DaoMesa ado = new DaoMesa(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getIdCoordinador());            
            Parametro param2=new Parametro("IN",bean.getIdGerenteZonal());            
            Parametro param3=new Parametro("IN",bean.getIdSupervisor());            
            Parametro param4=new Parametro("IN",bean.getIdSucursal());            
            Parametro param5=new Parametro("IN",bean.getDescripcion());            
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);            
            parametros.add(param4);            
            parametros.add(param5);            
            ArrayList objetos = ado.insertarMesa(parametros, cnx);
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
            Logger.getLogger(LogicMesa.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> actualizarMesa(Connection cnx,Mesa bean) throws UnknownException{
        try {
            DaoMesa ado = new DaoMesa(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",bean.getIdMesa());
            Parametro param1=new Parametro("IN",bean.getIdCoordinador());
            Parametro param2=new Parametro("IN",bean.getIdGerenteZonal());            
            Parametro param3=new Parametro("IN",bean.getIdSupervisor());            
            Parametro param4=new Parametro("IN",bean.getIdSucursal());            
            Parametro param5=new Parametro("IN",bean.getDescripcion());            
            parametros.add(param0);
            parametros.add(param1);
            parametros.add(param2);            
            parametros.add(param3);            
            parametros.add(param4);            
            parametros.add(param5);                        
            ArrayList objetos = ado.actualizarMesa(parametros, cnx);
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
    
    public ArrayList<String> eliminarMesa(Connection cnx,Mesa bean) throws UnknownException{
        try {
            DaoMesa ado = new DaoMesa(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getIdMesa());            
            parametros.add(param1);            
            ArrayList objetos = ado.eliminarMesa(parametros, cnx);
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
    
    public ArrayList<String> desactivarMesa(Connection cnx,Mesa bean) throws UnknownException{
        try {
            DaoMesa ado = new DaoMesa(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getIdMesa());            
            parametros.add(param1);            
            ArrayList objetos = ado.desactivarMesa(parametros, cnx);
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
