/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Vendedor;
import com.gocommerce.server.model.dao.DaoVendedor;
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
public class LogicVendedor {
    private PersistenceManager pm;
    
    public LogicVendedor(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public Collection<Vendedor> getListarBean() throws UnknownException {
        DaoVendedor dao = new DaoVendedor(this.pm);
        Collection<Vendedor> lista = dao.getListarBean();
        return lista;
    }   
    
    public ArrayList<Vendedor> getListarBean(Connection cnx) throws UnknownException{
        try {
            DaoVendedor ado = new DaoVendedor(pm);
            ArrayList<Parametro> parametros = new ArrayList();            
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Vendedor> lista = new ArrayList();
            while (rs.next()) {
                Vendedor bean = new Vendedor();
                bean.setIdVendedor(rs.getString("IDVENDEDOR"));                                
                bean.setCodigoAlterno(rs.getString("CODIGOALTERNO"));
                bean.setPrimerNombre(rs.getString("PRIMERNOMBRE"));
                bean.setSegundoNombre(rs.getString("SEGUNDONOMBRE"));
                bean.setApellidoPaterno(rs.getString("APELLIDOPATERNO"));
                bean.setApellidoMaterno(rs.getString("APELLIDOMATERNO"));
                bean.setNomVendedor(rs.getString("NOMVENDEDOR"));
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
            Logger.getLogger(LogicVendedor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    
    public ArrayList<Vendedor> getListarBeanXpto(Connection cnx,String idPuntoEmision) throws UnknownException{
        try {
            DaoVendedor ado = new DaoVendedor(pm);
            ArrayList<Parametro> parametros = new ArrayList();      
            Parametro param1=new Parametro("IN",idPuntoEmision);
            parametros.add(param1);
            ArrayList objetos = ado.getListarBeanXpto(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Vendedor> lista = new ArrayList();
            while (rs.next()) {
                Vendedor bean = new Vendedor();
                bean.setIdVendedor(rs.getString("IDVENDEDOR"));                                
                bean.setCodigoAlterno(rs.getString("CODIGOALTERNO"));
                bean.setPrimerNombre(rs.getString("PRIMERNOMBRE"));
                bean.setSegundoNombre(rs.getString("SEGUNDONOMBRE"));
                bean.setApellidoPaterno(rs.getString("APELLIDOPATERNO"));
                bean.setApellidoMaterno(rs.getString("APELLIDOMATERNO"));
                bean.setNomVendedor(rs.getString("NOMVENDEDOR"));
                bean.setDni(rs.getString("DNI"));
                bean.setIdPtoEmision(rs.getString("CODPTO"));
                bean.setNomPtoEmision(rs.getString("NOMPTO"));
                bean.setEstado(rs.getInt("ESTADO"));
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
            Logger.getLogger(LogicVendedor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<Vendedor> getListarBeanXptoCe(Connection cnx,String idPuntoEmision) throws UnknownException{
        try {
            DaoVendedor ado = new DaoVendedor(pm);
            ArrayList<Parametro> parametros = new ArrayList();      
            Parametro param1=new Parametro("IN",idPuntoEmision);
            parametros.add(param1);
            ArrayList objetos = ado.getListarBeanXptoCe(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Vendedor> lista = new ArrayList();
            while (rs.next()) {
                Vendedor bean = new Vendedor();
                bean.setIdVendedor(rs.getString("IDVENDEDOR"));                                
                bean.setCodigoAlterno(rs.getString("CODIGOALTERNO"));
                bean.setPrimerNombre(rs.getString("PRIMERNOMBRE"));
                bean.setSegundoNombre(rs.getString("SEGUNDONOMBRE"));
                bean.setApellidoPaterno(rs.getString("APELLIDOPATERNO"));
                bean.setApellidoMaterno(rs.getString("APELLIDOMATERNO"));
                bean.setNomVendedor(rs.getString("NOMVENDEDOR"));
                bean.setDni(rs.getString("DNI"));
                bean.setIdPtoEmision(rs.getString("CODPTO"));
                bean.setNomPtoEmision(rs.getString("NOMPTO"));
                bean.setEstado(rs.getInt("ESTADO"));
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
            Logger.getLogger(LogicVendedor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> insertarVendedor(Connection cnx,Vendedor bean) throws UnknownException{
        try {
            DaoVendedor ado = new DaoVendedor(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getIdPtoEmision());
            Parametro param2=new Parametro("IN",bean.getCodigoAlterno());
            Parametro param3=new Parametro("IN",bean.getDni());
            Parametro param4=new Parametro("IN",bean.getPrimerNombre());
            Parametro param5=new Parametro("IN",bean.getSegundoNombre());
            Parametro param6=new Parametro("IN",bean.getApellidoPaterno());
            Parametro param7=new Parametro("IN",bean.getApellidoMaterno());
            Parametro param8=new Parametro("IN",bean.getNomVendedor());
            //Parametro param9=new Parametro("IN",bean.getIdMesa());
            Parametro param10=new Parametro("IN",bean.getCanal());
            Parametro param11=new Parametro("IN",bean.getCorreo());
            Parametro param12=new Parametro("IN",bean.getCelular());
            Parametro param13=new Parametro("IN",new java.sql.Date(bean.getFechaInc().getTime()));
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);
            parametros.add(param6);
            parametros.add(param7);
            parametros.add(param8);
            //parametros.add(param9);
            parametros.add(param10);
            parametros.add(param11);
            parametros.add(param12);
            parametros.add(param13);
            ArrayList objetos = ado.insertarVendedor(parametros, cnx);
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
            Logger.getLogger(LogicVendedor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }    
    
    public ArrayList<String> actualizarVendedor(Connection cnx,Vendedor bean) throws UnknownException{
        try {
            DaoVendedor ado = new DaoVendedor(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",bean.getIdVendedor());
            Parametro param1=new Parametro("IN",bean.getIdPtoEmision());
            Parametro param2=new Parametro("IN",bean.getCodigoAlterno());
            Parametro param3=new Parametro("IN",bean.getDni());
            Parametro param4=new Parametro("IN",bean.getPrimerNombre());
            Parametro param5=new Parametro("IN",bean.getSegundoNombre());
            Parametro param6=new Parametro("IN",bean.getApellidoPaterno());
            Parametro param7=new Parametro("IN",bean.getApellidoMaterno());
            Parametro param8=new Parametro("IN",bean.getNomVendedor());            
            Parametro param10=new Parametro("IN",bean.getCanal());
            Parametro param11=new Parametro("IN",bean.getCorreo());
            Parametro param12=new Parametro("IN",bean.getCelular());
            parametros.add(param0);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);
            parametros.add(param6);
            parametros.add(param7);
            parametros.add(param8);            
            parametros.add(param10);
            parametros.add(param11);
            parametros.add(param12);            
            ArrayList objetos = ado.actualizarVendedor(parametros, cnx);
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
            Logger.getLogger(LogicVendedor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> actDesVendedor(Connection cnx,Vendedor bean) throws UnknownException{
        try {
            DaoVendedor ado = new DaoVendedor(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",bean.getIdVendedor());
            Parametro param1=new Parametro("IN",bean.getEstado());
            Parametro param2=new Parametro("IN",new java.sql.Date(bean.getFechaInc().getTime()));            
            parametros.add(param0);
            parametros.add(param1);            
            parametros.add(param2);            
            ArrayList objetos = ado.actDesVendedor(parametros, cnx);
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
            Logger.getLogger(LogicVendedor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> eliminarVendedor(Connection cnx,Vendedor bean) throws UnknownException{
        try {
            DaoVendedor ado = new DaoVendedor(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getIdVendedor());            
            parametros.add(param1);            
            ArrayList objetos = ado.eliminarVendedor(parametros, cnx);
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
            Logger.getLogger(LogicVendedor.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
