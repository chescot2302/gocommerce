/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.PrecioItem;
import com.gocommerce.server.model.dao.DaoPrecioItem;
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
public class LogicPrecioItem {
    private PersistenceManager pm;
    
    public LogicPrecioItem(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<PrecioItem> getListarBean(Connection cnx,Integer idLista,String estado) throws UnknownException{
        try {
            DaoPrecioItem ado = new DaoPrecioItem(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",idLista);
            Parametro param2=new Parametro("IN",estado);
            parametros.add(param1);
            parametros.add(param2);
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<PrecioItem> lista = new ArrayList();
            while (rs.next()) {
                PrecioItem bean = new PrecioItem();    
                bean.setId(rs.getInt("IDPRECIOSITEM"));
                bean.setIdListaPrecio(rs.getInt("IDLISTA"));
                bean.setIdItem(rs.getString("IDITEM"));
                bean.setCodigo(rs.getString("CODIGO"));
                bean.setDescripcion(rs.getString("DESCRIPCION"));
                bean.setMarca(rs.getString("MARCA"));
                bean.setIdMoneda(rs.getString("IDMONEDA"));
                bean.setPrecioSD(rs.getBigDecimal("PRECIOSD"));
                bean.setDescuento(rs.getBigDecimal("DESCUENTO"));
                bean.setPrecioVenta(rs.getBigDecimal("PRECIOVENTA"));
                bean.setValorVenta(rs.getBigDecimal("VALORVENTA"));
                bean.setIgv(rs.getBigDecimal("IGV"));
                bean.setFechaIni(rs.getDate("FECHAINI"));
                bean.setFechaFin(rs.getDate("FECHAFIN"));
                bean.setVersion(rs.getLong("VERSION"));
                bean.setEstadoActiva(rs.getString("ESTADOACTIVA"));
                bean.setIsEditable(rs.getInt("SCPRECIO"));
                bean.setEstadoColor(rs.getString("ESTADOCOLOR"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<PrecioItem> listaPrecioxItem(Connection cnx,String idItem,String idMoneda,String canal) throws UnknownException{
        try {
            DaoPrecioItem ado = new DaoPrecioItem(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",idItem);
            Parametro param2=new Parametro("IN",idMoneda);
            Parametro param3=new Parametro("IN",canal);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            ArrayList objetos = ado.listaPrecioxItem(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<PrecioItem> lista = new ArrayList();
            while (rs.next()) {
                PrecioItem bean = new PrecioItem();    
                bean.setId(rs.getInt("IDPRECIOSITEM"));
                bean.setIdListaPrecio(rs.getInt("IDLISTA"));
                bean.setIdItem(rs.getString("IDITEM"));
                bean.setIdMoneda(rs.getString("IDMONEDA"));
                bean.setValorVenta(rs.getBigDecimal("VALORVENTA"));
                bean.setPrecioVenta(rs.getBigDecimal("PRECIOVENTA"));
                bean.setDescripcion(rs.getString("DESCRIPCION"));
                bean.setVigencia(rs.getInt("VIGENCIA"));
                bean.setPagoMensual(rs.getBigDecimal("PAGOMENSUAL"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<PrecioItem> getItemSinLista(Connection cnx,Integer idLista) throws UnknownException{
        try {
            DaoPrecioItem ado = new DaoPrecioItem(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",idLista);
            parametros.add(param1);
            ArrayList objetos = ado.getItemSinLista(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<PrecioItem> lista = new ArrayList();
            while (rs.next()) {
                PrecioItem bean = new PrecioItem();    
                bean.setId(rs.getInt("IDPRECIOSITEM"));
                bean.setIdListaPrecio(rs.getInt("IDLISTA"));
                bean.setIdItem(rs.getString("IDITEM"));
                bean.setCodigo(rs.getString("CODIGO"));
                bean.setDescripcion(rs.getString("DESCRIPCION"));
                bean.setMarca(rs.getString("MARCA"));
                bean.setIdMoneda(rs.getString("IDMONEDA"));
                bean.setPrecioSD(rs.getBigDecimal("PRECIOSD"));
                bean.setDescuento(rs.getBigDecimal("DESCUENTO"));
                bean.setPrecioVenta(rs.getBigDecimal("PRECIOVENTA"));
                bean.setValorVenta(rs.getBigDecimal("VALORVENTA"));
                bean.setIgv(rs.getBigDecimal("IGV"));
                bean.setFechaIni(rs.getDate("FECHAINI"));
                bean.setFechaFin(rs.getDate("FECHAFIN"));
                bean.setVersion(rs.getLong("VERSION"));
                bean.setEstadoActiva(rs.getString("ESTADOACTIVA"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<PrecioItem> getItemSinListaCategoria(Connection cnx,Integer idLista,String codFam,String descripcion) throws UnknownException{
        try {
            DaoPrecioItem ado = new DaoPrecioItem(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",idLista);
            Parametro param2=new Parametro("IN",codFam);
            Parametro param3=new Parametro("IN",descripcion==null?"":descripcion);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            ArrayList objetos = ado.getItemSinListaCategoria(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<PrecioItem> lista = new ArrayList();
            while (rs.next()) {
                PrecioItem bean = new PrecioItem();    
                bean.setId(rs.getInt("IDPRECIOSITEM"));
                bean.setIdListaPrecio(rs.getInt("IDLISTA"));
                bean.setIdItem(rs.getString("IDITEM"));
                bean.setCodigo(rs.getString("CODIGO"));
                bean.setDescripcion(rs.getString("DESCRIPCION"));
                bean.setMarca(rs.getString("MARCA"));
                bean.setIdMoneda(rs.getString("IDMONEDA"));
                bean.setPrecioSD(rs.getBigDecimal("PRECIOSD"));
                bean.setDescuento(rs.getBigDecimal("DESCUENTO"));
                bean.setPrecioVenta(rs.getBigDecimal("PRECIOVENTA"));
                bean.setValorVenta(rs.getBigDecimal("VALORVENTA"));
                bean.setIgv(rs.getBigDecimal("IGV"));
                bean.setFechaIni(rs.getDate("FECHAINI"));
                bean.setFechaFin(rs.getDate("FECHAFIN"));
                bean.setVersion(rs.getLong("VERSION"));
                bean.setEstadoActiva(rs.getString("ESTADOACTIVA"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicPrecioItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> insertarPrecioItem(Connection cnx,String xmlPrecioItem) throws UnknownException{
        try {
            DaoPrecioItem ado = new DaoPrecioItem(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",xmlPrecioItem);
            parametros.add(param1);
            ArrayList objetos = ado.insertarPrecioItem(parametros, cnx);
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
    
    public ArrayList<String> eliminarPrecioItem(Connection cnx,String xmlPrecioItem) throws UnknownException{
        try {
            DaoPrecioItem ado = new DaoPrecioItem(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",xmlPrecioItem);
            parametros.add(param1);
            ArrayList objetos = ado.eliminarPrecioItem(parametros, cnx);
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
    
    public ArrayList<String> desactivarPrecioItem(Connection cnx,String xmlPrecioItem) throws UnknownException{
        try {
            DaoPrecioItem ado = new DaoPrecioItem(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",xmlPrecioItem);
            parametros.add(param1);
            ArrayList objetos = ado.desactivarPrecioItem(parametros, cnx);
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
    
    public ArrayList<String> actualizarPrecioItem(Connection cnx,String xmlPrecioItem) throws UnknownException{
        try {
            DaoPrecioItem ado = new DaoPrecioItem(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",xmlPrecioItem);
            parametros.add(param1);
            ArrayList objetos = ado.actualizarPrecioItem(parametros, cnx);
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
