/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Correlativo;
import com.gocommerce.server.model.dao.DaoCorrelativo;
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
public class LogicCorrelativo {
    private PersistenceManager pm;

    public LogicCorrelativo(PersistenceManager pm) {
        this.pm = pm;
    }

    public ArrayList<Correlativo> getListarBeanxPto(Connection cnx,String idPuntoEmision) throws UnknownException {
        try {
            DaoCorrelativo ado = new DaoCorrelativo(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            Parametro param1=new Parametro("IN",idPuntoEmision);
            parametros.add(param1);
            ArrayList objetos = ado.getListarBeanPto(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Correlativo> lista = new ArrayList();
            while (rs.next()) {
                Correlativo bean = new Correlativo();
                bean.setIdDocumento(rs.getString("cdocu"));
                bean.setIdPuntoEmision(rs.getString("codpto"));
                bean.setNombreDocumento(rs.getString("nomdoc"));
                bean.setNroInicio(rs.getString("nroini").substring(0,3));                
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<Correlativo> getCorreActual(Connection cnx,String idUsuario,String idPuntoEmision,String idDocumento,String incluyePto) throws UnknownException {
        try {
            DaoCorrelativo ado = new DaoCorrelativo(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            Parametro param0=new Parametro("IN",idUsuario);
            Parametro param1=new Parametro("IN",idPuntoEmision);
            Parametro param2=new Parametro("IN",idDocumento);
            Parametro param3=new Parametro("IN",incluyePto);
            parametros.add(param0);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            ArrayList objetos = ado.getCorreActual(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Correlativo> lista = new ArrayList();
            while (rs.next()) {
                Correlativo bean = new Correlativo();
                bean.setIdDocumento(rs.getString("cdocu"));
                bean.setNombreDocumento(rs.getString("nomdoc"));
                bean.setIdPuntoEmision(rs.getString("codpto"));
                bean.setSerie(rs.getString("serie"));
                bean.setPreimpreso(rs.getString("preimpreso"));                
                bean.setNroInicio(rs.getString("ndocu"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<Correlativo> getSerieUserVenta(Connection cnx,String idUsuario,String idPuntoEmision) throws UnknownException {
        try {
            DaoCorrelativo ado = new DaoCorrelativo(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            Parametro param0=new Parametro("IN",idUsuario);
            Parametro param1=new Parametro("IN",idPuntoEmision);
            parametros.add(param0);
            parametros.add(param1);
            ArrayList objetos = ado.getSerieUserVenta(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Correlativo> lista = new ArrayList();
            while (rs.next()) {
                Correlativo bean = new Correlativo();                   
                bean.setIdDocumento(rs.getString("cdocu"));
                bean.setIdPuntoEmision(rs.getString("CODPTO"));
                bean.setSerie(rs.getString("serie")); 
                bean.setDocSerie(rs.getString("docserie"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<Correlativo> getSerieUserVentaCe(Connection cnx,String idUsuario,String idPuntoEmision) throws UnknownException {
        try {
            DaoCorrelativo ado = new DaoCorrelativo(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            Parametro param0=new Parametro("IN",idUsuario);
            Parametro param1=new Parametro("IN",idPuntoEmision);
            parametros.add(param0);
            parametros.add(param1);
            ArrayList objetos = ado.getSerieUserVentaCe(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Correlativo> lista = new ArrayList();
            while (rs.next()) {
                Correlativo bean = new Correlativo();                   
                bean.setIdDocumento(rs.getString("cdocu"));
                bean.setIdPuntoEmision(rs.getString("CODPTO"));
                bean.setSerie(rs.getString("serie")); 
                bean.setDocSerie(rs.getString("docserie"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
