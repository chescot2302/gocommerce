/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Documento;
import com.gocommerce.server.model.dao.DaoDocumento;
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
public class LogicDocumento {

    private PersistenceManager pm;

    public LogicDocumento(PersistenceManager pm) {
        this.pm = pm;
    }

    public ArrayList<Documento> getListarBeanVenta(Connection cnx) throws UnknownException {
        try {
            DaoDocumento ado = new DaoDocumento(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            ArrayList objetos = ado.getListarBeanVenta(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Documento> lista = new ArrayList();
            while (rs.next()) {
                Documento bean = new Documento();
                bean.setId(rs.getString("cdocu").trim());                
                bean.setNombre(rs.getString("nomdoc"));                
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicDocumento.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<Documento> getDocVentaCe(Connection cnx) throws UnknownException {
        try {
            DaoDocumento ado = new DaoDocumento(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            ArrayList objetos = ado.getDocVentaCe(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Documento> lista = new ArrayList();
            while (rs.next()) {
                Documento bean = new Documento();
                bean.setId(rs.getString("cdocu").trim());                
                bean.setNombre(rs.getString("nomdoc"));                
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicDocumento.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<Documento> getListarBeanDespacho(Connection cnx) throws UnknownException {
        try {
            DaoDocumento ado = new DaoDocumento(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            ArrayList objetos = ado.getListarBeanDespacho(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Documento> lista = new ArrayList();
            while (rs.next()) {
                Documento bean = new Documento();
                bean.setId(rs.getString("cdocu").trim());                
                bean.setNombre(rs.getString("nomdoc"));                
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicDocumento.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }

}
