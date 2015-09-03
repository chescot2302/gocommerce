/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.CondicionVenta;
import com.gocommerce.server.model.dao.DaoCondicionVenta;
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
public class LogicCondicionVenta {
    private PersistenceManager pm;
    
    public LogicCondicionVenta(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<CondicionVenta> getListarBean(Connection cnx) throws UnknownException{
        try {
            DaoCondicionVenta ado = new DaoCondicionVenta(pm);
            ArrayList<Parametro> parametros = new ArrayList();               
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<CondicionVenta> lista = new ArrayList();
            while (rs.next()) {
                CondicionVenta bean = new CondicionVenta();
                bean.setIdCondicionVenta(rs.getString("codcdv").trim());     
                bean.setDescripcion(rs.getString("nomcdv").trim());
                bean.setDiasVencimiento(rs.getInt("diaven"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicCondicionVenta.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<CondicionVenta> getListarBeanXvigenciaCredito(Connection cnx,Integer vigenciaCredito) throws UnknownException{
        try {
            DaoCondicionVenta ado = new DaoCondicionVenta(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",vigenciaCredito);
            parametros.add(param1);
            ArrayList objetos = ado.getListarBeanXvigenciaCredito(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<CondicionVenta> lista = new ArrayList();
            while (rs.next()) {
                CondicionVenta bean = new CondicionVenta();
                bean.setIdCondicionVenta(rs.getString("codcdv").trim());     
                bean.setDescripcion(rs.getString("nomcdv").trim());
                bean.setDiasVencimiento(rs.getInt("diaven"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicCondicionVenta.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
