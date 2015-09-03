/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.SuperVen;
import com.gocommerce.server.model.dao.DaoSuperVen;
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
public class LogicSuperVen {
    private PersistenceManager pm;
    
    public LogicSuperVen(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<SuperVen> getListarBean(Connection cnx,Integer idMesa,String estado) throws UnknownException{
        try {
            DaoSuperVen ado = new DaoSuperVen(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",idMesa);
            Parametro param2=new Parametro("IN",estado);
            parametros.add(param1);
            parametros.add(param2);
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<SuperVen> lista = new ArrayList();
            while (rs.next()) {
                SuperVen bean = new SuperVen();    
                bean.setIdSuperVen(rs.getInt("IDSUPERVEN"));
                bean.setIdMesa(rs.getInt("IDMESA"));
                bean.setIdVendedor(rs.getString("IDVENDEDOR"));
                bean.setDni(rs.getString("DNI"));
                bean.setNomConsultor(rs.getString("NOMCONSULTOR"));                                
                bean.setFechaIni(rs.getDate("FECHAINI"));
                bean.setFechaFin(rs.getDate("FECHAFIN"));
                bean.setVersion(Long.valueOf("1"));
                bean.setEstado(rs.getString("ESTADO"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicSuperVen.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<SuperVen> consultorSinMesa(Connection cnx) throws UnknownException{
        try {
            DaoSuperVen ado = new DaoSuperVen(pm);
            ArrayList<Parametro> parametros = new ArrayList();                              
            ArrayList objetos = ado.consultorSinMesa(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<SuperVen> lista = new ArrayList();
            Integer index=0;
            while (rs.next()) {
                SuperVen bean = new SuperVen();    
                bean.setIdSuperVen(index);
                bean.setIdVendedor(rs.getString("IDVENDEDOR"));
                bean.setDni(rs.getString("DNI"));
                bean.setNomConsultor(rs.getString("NOMCONSULTOR"));                                                
                bean.setVersion(Long.valueOf("1"));
                index=index+1;
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicSuperVen.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> insertarSuperVen(Connection cnx,String xmlSuperVen) throws UnknownException{
        try {
            DaoSuperVen ado = new DaoSuperVen(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",xmlSuperVen);
            parametros.add(param1);
            ArrayList objetos = ado.insertarSuperVen(parametros, cnx);
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
            Logger.getLogger(LogicSuperVen.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> desactivarSuperVen(Connection cnx,String xmlSuperVen) throws UnknownException{
        try {
            DaoSuperVen ado = new DaoSuperVen(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",xmlSuperVen);
            parametros.add(param1);
            ArrayList objetos = ado.desactivarSuperVen(parametros, cnx);
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
            Logger.getLogger(LogicSuperVen.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> eliminarSuperVen(Connection cnx,String xmlSuperVen) throws UnknownException{
        try {
            DaoSuperVen ado = new DaoSuperVen(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",xmlSuperVen);
            parametros.add(param1);
            ArrayList objetos = ado.eliminarSuperVen(parametros, cnx);
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
            Logger.getLogger(LogicSuperVen.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
