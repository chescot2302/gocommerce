/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.DetalleVenta;
import com.gocommerce.server.model.dao.DaoDetalleVenta;
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
public class LogicDetalleVenta {
    private PersistenceManager pm;
    
    public LogicDetalleVenta(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<DetalleVenta> getDetalleVenta(
            Connection cnx,
            String tipoDoc,
            String correlativo) throws UnknownException{
        try {
            DaoDetalleVenta ado = new DaoDetalleVenta(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",tipoDoc);
            Parametro param2=new Parametro("IN",correlativo);              
            parametros.add(param1);
            parametros.add(param2);            
            ArrayList objetos = ado.getDetalleVenta(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<DetalleVenta> lista = new ArrayList();
            while (rs.next()) {                
                DetalleVenta bean=new DetalleVenta();
                bean.setIdDetalleVenta(rs.getString("IDDETALLEVENTA"));                
                bean.setTipoDoc(rs.getString("CDOCU"));
                bean.setCorrelativo(rs.getString("NDOCU"));
                bean.setIdItem(rs.getString("CODI"));
                bean.setSecuencia(rs.getInt("ITEM"));
                bean.setCodigo(rs.getString("CODF"));
                bean.setMarca(rs.getString("MARCA"));
                bean.setDescripcion(rs.getString("DESCITEM"));
                bean.setUnidadMedida(rs.getString("UMED"));
                bean.setCantidad(rs.getBigDecimal("CANT"));
                bean.setPrecioUnitario(rs.getBigDecimal("PRECIOUNITARIO"));
                bean.setTotalVenta(rs.getBigDecimal("TOTALVENTA"));
                bean.setPlan(rs.getString("PLANES"));
                bean.setCondPlan(rs.getString("CONDICION"));
                bean.setPrecioPlan(rs.getBigDecimal("PRECIOPLAN"));
                bean.setVigencia(rs.getInt("VIGENCIA"));
                bean.setIdAlmacen(rs.getString("CODALM"));
                bean.setNomAlmacen(rs.getString("NOMALM"));
                bean.setLserie(rs.getInt("MSERIE"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicDetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<DetalleVenta> getDetalleVentaCe(
            Connection cnx,
            String tipoDoc,
            String correlativo) throws UnknownException{
        try {
            DaoDetalleVenta ado = new DaoDetalleVenta(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",tipoDoc);
            Parametro param2=new Parametro("IN",correlativo);              
            parametros.add(param1);
            parametros.add(param2);            
            ArrayList objetos = ado.getDetalleVentaCe(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<DetalleVenta> lista = new ArrayList();
            while (rs.next()) {                
                DetalleVenta bean=new DetalleVenta();
                bean.setIdDetalleVenta(rs.getString("IDDETALLEVENTA"));                
                bean.setTipoDoc(rs.getString("CDOCU"));
                bean.setCorrelativo(rs.getString("NDOCU"));
                bean.setIdItem(rs.getString("CODI"));
                bean.setSecuencia(rs.getInt("ITEM"));
                bean.setCodigo(rs.getString("CODF"));
                bean.setMarca(rs.getString("MARCA"));
                bean.setDescripcion(rs.getString("DESCITEM"));
                bean.setUnidadMedida(rs.getString("UMED"));
                bean.setCantidad(rs.getBigDecimal("CANT"));
                bean.setPrecioUnitario(rs.getBigDecimal("PRECIOUNITARIO"));
                bean.setTotalVenta(rs.getBigDecimal("TOTALVENTA"));
                bean.setPlan(rs.getString("PLANES"));
                bean.setCondPlan(rs.getString("CONDICION"));
                bean.setPrecioPlan(rs.getBigDecimal("PRECIOPLAN"));
                bean.setVigencia(rs.getInt("VIGENCIA"));
                bean.setLserie(rs.getInt("MSERIE"));
                bean.setIdLista(rs.getInt("IDLISTA"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicDetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
