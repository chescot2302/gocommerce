/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Item;
import com.gocommerce.server.model.dao.DaoItem;
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
public class LogicItem {
    private PersistenceManager pm;
    
    public LogicItem(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<Item> getListarBean(Connection cnx) throws UnknownException{
        try {
            DaoItem ado = new DaoItem(pm);
            ArrayList<Parametro> parametros = new ArrayList();            
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Item> lista = new ArrayList();
            while (rs.next()) {
                Item bean = new Item();
                bean.setId(rs.getString("codi").trim());                
                bean.setCodigo(rs.getString("codf").trim());
                bean.setDescripcion(rs.getString("descr").trim());
                bean.setMarca(rs.getString("marc").trim());
                bean.setUnidad(rs.getString("umed").trim());
                bean.setPercepcion(rs.getDouble("persnt"));
                bean.setUbica(rs.getString("ubica").trim());
                bean.setCtaVenta(rs.getString("CTAVTA"));
                bean.setMoneda(rs.getString("mone"));
                bean.setUcom(rs.getString("ucom"));
                bean.setCostoSoles(rs.getBigDecimal("pcns"));
                bean.setAfectoIgv(rs.getString("aigv"));
                bean.setManejaStock(rs.getString("msto"));
                bean.setLserie(rs.getInt("lserie"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<Item> getListarBeanVenta(Connection cnx,String codFam,String descripcion) throws UnknownException{
        try {
            DaoItem ado = new DaoItem(pm);
            ArrayList<Parametro> parametros = new ArrayList();            
            Parametro param1=new Parametro("IN",codFam);
            Parametro param2=new Parametro("IN",descripcion==null?"":descripcion);
            parametros.add(param1);
            parametros.add(param2);
            ArrayList objetos = ado.getListarBeanVenta(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Item> lista = new ArrayList();
            while (rs.next()) {
                Item bean = new Item();
                bean.setId(rs.getString("codi").trim());                
                bean.setCodigo(rs.getString("codf").trim());
                bean.setDescripcion(rs.getString("descr").trim());
                bean.setMarca(rs.getString("marc").trim());
                bean.setUnidad(rs.getString("umed").trim());
                bean.setPercepcion(rs.getDouble("persnt"));
                bean.setUbica(rs.getString("ubica").trim());
                bean.setCtaVenta(rs.getString("CTAVTA"));
                bean.setMoneda(rs.getString("mone"));
                bean.setUcom(rs.getString("ucom"));
                bean.setCostoSoles(rs.getBigDecimal("pcns"));
                bean.setAfectoIgv(rs.getString("aigv"));
                bean.setManejaStock(rs.getString("msto"));
                bean.setLserie(rs.getInt("lserie"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    
}
