/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.MenuBar;
import com.gocommerce.server.model.dao.DaoMenuBar;
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
public class LogicMenuBar {
    private PersistenceManager pm;

    public LogicMenuBar(PersistenceManager pm) {
        this.pm = pm;
    }
    

    public ArrayList<MenuBar> getListarBean(Connection cnx) throws UnknownException {
        try {
            DaoMenuBar ado = new DaoMenuBar(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<MenuBar> lista = new ArrayList();
            while (rs.next()) {
                MenuBar bean = new MenuBar();
                bean.setIdMenuBar(rs.getInt("id"));
                bean.setVariable(rs.getString("variable"));
                bean.setDescripcion(rs.getString("descripcion"));
                bean.setTipo(rs.getString("tipo"));
                bean.setOrden(rs.getInt("orden"));
                bean.setNivel(rs.getInt("nivel"));
                bean.setIdMenuPadre(rs.getInt("idpadre"));
                bean.setGrupo(rs.getInt("grupo"));
                bean.setNumSubMenu(rs.getInt("numsubmenu"));
                bean.setComando(rs.getString("operacion"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicMenuBar.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<MenuBar> getListarBeanXusuario(Connection cnx,Integer idBdUsuario,String esquema) throws UnknownException {
        try {
            DaoMenuBar ado = new DaoMenuBar(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            Parametro param1=new Parametro("IN",idBdUsuario);
            Parametro param2=new Parametro("IN",esquema);
            parametros.add(param1);
            parametros.add(param2);
            ArrayList objetos = ado.getListarBeanXusuario(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<MenuBar> lista = new ArrayList();
            while (rs.next()) {
                MenuBar bean = new MenuBar();
                bean.setIdMenuBar(rs.getInt("id"));
                bean.setVariable(rs.getString("variable"));
                bean.setDescripcion(rs.getString("descripcion"));
                bean.setTipo(rs.getString("tipo"));
                bean.setOrden(rs.getInt("orden"));
                bean.setNivel(rs.getInt("nivel"));
                bean.setIdMenuPadre(rs.getInt("idpadre"));
                bean.setGrupo(rs.getInt("grupo"));
                bean.setNumSubMenu(rs.getInt("numsubmenu"));
                bean.setIdBdUsuario(rs.getInt("idbdusuario"));
                bean.setEstado(rs.getString("estado"));
                bean.setComando(rs.getString("operacion"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicMenuBar.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> InsertGrantMenusuario(Connection cnx,String xmlMenuBarUsuario,String esquema) throws UnknownException{
        try {
            DaoMenuBar ado = new DaoMenuBar(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",xmlMenuBarUsuario);
            Parametro param2=new Parametro("IN",esquema);
            parametros.add(param1);
            parametros.add(param2);
            ArrayList objetos = ado.InsertGrantMenusuario(parametros, cnx);
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
            Logger.getLogger(LogicPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
