/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.ItemPlan;
import com.gocommerce.server.model.dao.DaoItemPlan;
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
public class LogicItemPlan {
    private PersistenceManager pm;
    
    public LogicItemPlan(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<ItemPlan> getListarItemPlan(Connection cnx) throws UnknownException{
        try {
            DaoItemPlan ado = new DaoItemPlan(pm);
            ArrayList<Parametro> parametros = new ArrayList();   
            ArrayList objetos = ado.getListarItemPlan(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<ItemPlan> lista = new ArrayList();
            Integer contador=0;
            while (rs.next()) {
                ItemPlan bean = new ItemPlan();
                bean.setIdEquipPlan(contador);
                contador=contador+1;
                bean.setIdItem(rs.getString("iditem"));
                bean.setDescripcion(rs.getString("descripcion"));                
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicItemPlan.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<ItemPlan> getEquiposxPlan(Connection cnx,String tipoDoc,String correlativo,String codiPlan,Integer idLista) throws UnknownException{
        try {
            DaoItemPlan ado = new DaoItemPlan(pm);
            ArrayList<Parametro> parametros = new ArrayList();   
            Parametro param1=new Parametro("IN",tipoDoc);
            Parametro param2=new Parametro("IN",correlativo);
            Parametro param3=new Parametro("IN",codiPlan);
            Parametro param4=new Parametro("IN",idLista);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            ArrayList objetos = ado.getEquiposxPlan(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<ItemPlan> lista = new ArrayList();
            while (rs.next()) {
                ItemPlan bean = new ItemPlan();
                bean.setIdEquipPlan(rs.getInt("idequipplan"));
                bean.setIdItem(rs.getString("codi"));
                bean.setDescripcion(rs.getString("descripcion"));
                bean.setCantidad(rs.getBigDecimal("cantidad"));
                bean.setPrecio(rs.getBigDecimal("precio"));
                bean.setTotal(rs.getBigDecimal("total"));
                bean.setIdItemPlan(rs.getString("codiplan"));
                bean.setIdLista(rs.getInt("idlista"));                
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicItemPlan.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
}
