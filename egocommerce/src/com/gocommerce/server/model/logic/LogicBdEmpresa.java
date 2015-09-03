/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.BdEmpresa;
import com.gocommerce.server.model.dao.DaoBdEmpresa;
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
public class LogicBdEmpresa {
    private PersistenceManager pm;
    
    public LogicBdEmpresa(PersistenceManager pm) {
        this.pm=pm;
    }

    public boolean mantenimiento(BeanParametro parametro)
            throws UnknownException {
        DaoBdEmpresa dao = new DaoBdEmpresa(this.pm);
        return dao.mantenimiento(parametro);
    }
    
    public Object backOperBean(BeanParametro parametro)
            throws UnknownException {
        DaoBdEmpresa dao = new DaoBdEmpresa(this.pm);
        return dao.backOperBean(parametro);
    }

    public Object getBean(Integer id) throws UnknownException {
        DaoBdEmpresa dao = new DaoBdEmpresa(this.pm);
        return dao.getBean(id);
    }        

    public Collection<BdEmpresa> getListarBean() throws UnknownException {
        DaoBdEmpresa dao = new DaoBdEmpresa(this.pm);
        Collection<BdEmpresa> lista = dao.getListarBean();
        return lista;
    }    
    
    public Collection<BdEmpresa> getListarBean(String estado) throws UnknownException {
        DaoBdEmpresa dao = new DaoBdEmpresa(this.pm);
        Collection<BdEmpresa> lista = dao.getListarBean(estado);
        return lista;
    }
    
    public Boolean insertBean(Connection cnx,Integer idBdEmpresa,String nombreEmpresa,String host,int puerto,String esquema,String usuario,String clave) throws UnknownException{
        try {
            DaoBdEmpresa ado = new DaoBdEmpresa(pm);
            ArrayList<Parametro> parametros = new ArrayList();   
            Parametro param0=new Parametro("IN",idBdEmpresa);            
            Parametro param1=new Parametro("IN",nombreEmpresa);            
            Parametro param2=new Parametro("IN",host);            
            Parametro param3=new Parametro("IN",puerto);            
            Parametro param4=new Parametro("IN",esquema);            
            Parametro param5=new Parametro("IN",usuario);            
            Parametro param6=new Parametro("IN",clave);            
            parametros.add(param0);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);
            parametros.add(param6);
            ArrayList objetos = ado.insertBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);            
            String val="false";
            while (rs.next()) {
                val=rs.getString("result");
            }
            rs.close();
            cst.close();
            return val.equals("true");
        } catch (Exception ex) {
            Logger.getLogger(LogicBdEmpresa.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public Boolean updateBean(Connection cnx,String nombreEmpresa,String host,int puerto,String esquema,String usuario,String clave) throws UnknownException{
        try {
            DaoBdEmpresa ado = new DaoBdEmpresa(pm);
            ArrayList<Parametro> parametros = new ArrayList();   
            Parametro param1=new Parametro("IN",nombreEmpresa);            
            Parametro param2=new Parametro("IN",host);            
            Parametro param3=new Parametro("IN",puerto);            
            Parametro param4=new Parametro("IN",esquema);            
            Parametro param5=new Parametro("IN",usuario);            
            Parametro param6=new Parametro("IN",clave);            
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);
            parametros.add(param6);
            ArrayList objetos = ado.updateBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);            
            String val="false";
            while (rs.next()) {
                val=rs.getString("result");
            }
            rs.close();
            cst.close();
            return val.equals("true");
        } catch (Exception ex) {
            Logger.getLogger(LogicBdEmpresa.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
