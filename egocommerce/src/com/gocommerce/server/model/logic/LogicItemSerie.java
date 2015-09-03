/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.ItemSerie;
import com.gocommerce.server.model.dao.DaoItemSerie;
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
public class LogicItemSerie {
    private PersistenceManager pm;
    
    public LogicItemSerie(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<ItemSerie> getListarBeanxItem(Connection cnx,String codi,String codAlm,String tipo) throws UnknownException{
        try {
            DaoItemSerie ado = new DaoItemSerie(pm);
            ArrayList<Parametro> parametros = new ArrayList();   
            Parametro param1=new Parametro("IN",codi);
            Parametro param2=new Parametro("IN",codAlm);
            Parametro param3=new Parametro("IN",tipo);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            ArrayList objetos = ado.getListarBeanxItem(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<ItemSerie> lista = new ArrayList();
            while (rs.next()) {
                ItemSerie bean = new ItemSerie();
                bean.setItem(rs.getInt("item"));
                bean.setCodi(rs.getString("codi"));
                bean.setSerie(rs.getString("serie"));
                bean.setCdocui(rs.getString("cdocui"));
                bean.setNdocui(rs.getString("ndocui"));
                bean.setCdocus(rs.getString("cdocus"));
                bean.setNdocus(rs.getString("ndocus"));
                bean.setFlag(rs.getString("flag"));
                bean.setCodpro(rs.getString("codpro"));
                bean.setCdocusp(rs.getString("cdocusp"));
                bean.setNdocusp(rs.getString("ndocusp"));
                bean.setCdocuip(rs.getString("cdocuip"));
                bean.setNdocuip(rs.getString("ndocuip"));
                bean.setCodAlm(rs.getString("codalm"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicItemSerie.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<ItemSerie> getSeriesVenta(Connection cnx,String tipoDoc,String correlativo,String codi) throws UnknownException{
        try {
            DaoItemSerie ado = new DaoItemSerie(pm);
            ArrayList<Parametro> parametros = new ArrayList();   
            Parametro param1=new Parametro("IN",tipoDoc);
            Parametro param2=new Parametro("IN",correlativo);
            Parametro param3=new Parametro("IN",codi);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            ArrayList objetos = ado.getSeriesVenta(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<ItemSerie> lista = new ArrayList();
            while (rs.next()) {
                ItemSerie bean = new ItemSerie();
                bean.setCodi(rs.getString("codi"));
                bean.setSerie(rs.getString("serie")); 
                bean.setTelefono(rs.getString("telefono"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicItemSerie.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
