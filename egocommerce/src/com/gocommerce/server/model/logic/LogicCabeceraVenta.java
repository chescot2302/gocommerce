/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.CabeceraVenta;
import com.gocommerce.server.model.dao.DaoCabeceraVenta;
import com.gocommerce.server.model.dao.DaoPrecioItem;
import com.gocommerce.server.util.Parametro;
import com.gocommerce.server.util.UnknownException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class LogicCabeceraVenta {
    private PersistenceManager pm;
    
    public LogicCabeceraVenta(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<String> generarDocumentoVenta(Connection cnx,String xmlDetail,String xmlHead,String xmlSeries,Integer conDespacho,Integer conAsiento,Date fechaEmision,String idPuntoEmision) throws UnknownException{
        try {
            DaoCabeceraVenta ado = new DaoCabeceraVenta(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",xmlDetail);
            Parametro param2=new Parametro("IN",xmlHead);            
            Parametro param3=new Parametro("IN",conDespacho);
            Parametro param4=new Parametro("IN",conAsiento);
            Parametro param5=new Parametro("IN",xmlSeries);
            Parametro param6=new Parametro("IN",new java.sql.Date(fechaEmision.getTime()));
            Parametro param7=new Parametro("IN",idPuntoEmision);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);
            parametros.add(param6);
            parametros.add(param7);
            ArrayList objetos = ado.generarDocumentoVenta(parametros, cnx);
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
            Logger.getLogger(LogicCabeceraVenta.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    
    public ArrayList<String> registrarVentaCe(Connection cnx,String xmlDetail,String xmlHead,Date fechaEmision,String idPuntoEmision,String xmlPlan) throws UnknownException{
        try {
            DaoCabeceraVenta ado = new DaoCabeceraVenta(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",xmlDetail);
            Parametro param2=new Parametro("IN",xmlHead);            
            Parametro param6=new Parametro("IN",new java.sql.Date(fechaEmision.getTime()));
            Parametro param7=new Parametro("IN",idPuntoEmision);
            Parametro param8=new Parametro("IN",xmlPlan);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param6);
            parametros.add(param7);
            parametros.add(param8);
            ArrayList objetos = ado.registrarVentaCe(parametros, cnx);
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
            Logger.getLogger(LogicCabeceraVenta.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<CabeceraVenta> listarVentas(
            Connection cnx,
            Date fechaIni,
            Date fechaFin,
            String rucCliente,
            String descrCliente,
            String docseries,
            String correlativo,            
            String tas,
            String excluirFecha) throws UnknownException{
        try {
            DaoCabeceraVenta ado = new DaoCabeceraVenta(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",new java.sql.Date(fechaIni.getTime()));
            Parametro param2=new Parametro("IN",new java.sql.Date(fechaFin.getTime()));  
            Parametro param3=new Parametro("IN",rucCliente);
            Parametro param4=new Parametro("IN",descrCliente);
            Parametro param5=new Parametro("IN",docseries);
            Parametro param6=new Parametro("IN",correlativo);            
            Parametro param8=new Parametro("IN",tas);
            Parametro param9=new Parametro("IN",excluirFecha);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);
            parametros.add(param6);            
            parametros.add(param8);
            parametros.add(param9);
            ArrayList objetos = ado.listarVentas(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<CabeceraVenta> lista = new ArrayList();
            while (rs.next()) {                
                CabeceraVenta bean=new CabeceraVenta();
                bean.setFechaEmision(new Date(rs.getDate("fecha").getTime()));
                bean.setTipoDoc(rs.getString("cdocu"));
                bean.setCorrelativo(rs.getString("ndocu"));
                bean.setRucCliente(rs.getString("ruccli"));
                bean.setNombreCliente(rs.getString("nomcli"));
                bean.setIdCondicionVenta(rs.getString("codcdv"));
                bean.setIdTipoVenta(rs.getString("codvta"));
                bean.setFechaVencimiento(new Date(rs.getDate("fven").getTime()));
                bean.setDiasCredito(rs.getInt("dias"));
                bean.setIdMoneda(rs.getString("mone"));
                bean.setTipoCambio(rs.getBigDecimal("tcam"));
                bean.setTotalAfecto(rs.getBigDecimal("tota"));
                bean.setTotalIgv(rs.getBigDecimal("toti"));
                bean.setTotalNeto(rs.getBigDecimal("totn"));
                bean.setIdVendedor(rs.getString("codven"));
                bean.setIdPtoEmision(rs.getString("codpto"));
                bean.setFlag(rs.getString("flag"));
                bean.setNomDoc(rs.getString("nomdoc"));
                bean.setSerie(rs.getString("serie"));
                bean.setPreimpreso(rs.getString("preimpreso"));
                bean.setNomCondicionVenta(rs.getString("nomcdv"));
                bean.setTipoVenta(rs.getString("nomvta"));
                bean.setDniVendedor(rs.getString("DNIVEN"));
                bean.setNomVendedor(rs.getString("nomven"));
                bean.setPuntoEmsion(rs.getString("nompto"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicCabeceraVenta.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<CabeceraVenta> listarVentasCe(
            Connection cnx,
            Date fechaIni,
            Date fechaFin,
            String rucCliente,
            String descrCliente,
            String docseries,
            String correlativo,            
            String tas,
            String excluirFecha) throws UnknownException{
        try {
            DaoCabeceraVenta ado = new DaoCabeceraVenta(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",new java.sql.Date(fechaIni.getTime()));
            Parametro param2=new Parametro("IN",new java.sql.Date(fechaFin.getTime()));  
            Parametro param3=new Parametro("IN",rucCliente);
            Parametro param4=new Parametro("IN",descrCliente);
            Parametro param5=new Parametro("IN",docseries);
            Parametro param6=new Parametro("IN",correlativo);            
            Parametro param8=new Parametro("IN",tas);
            Parametro param9=new Parametro("IN",excluirFecha);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);
            parametros.add(param6);            
            parametros.add(param8);
            parametros.add(param9);
            ArrayList objetos = ado.listarVentasCe(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<CabeceraVenta> lista = new ArrayList();
            while (rs.next()) {                
                CabeceraVenta bean=new CabeceraVenta();
                bean.setFechaEmision(new Date(rs.getDate("fecha").getTime()));
                bean.setTipoDoc(rs.getString("cdocu"));
                bean.setCorrelativo(rs.getString("ndocu"));
                bean.setRucCliente(rs.getString("ruccli"));
                bean.setNombreCliente(rs.getString("nomcli"));                
                bean.setIdMoneda(rs.getString("mone"));
                bean.setTipoCambio(rs.getBigDecimal("tcam"));
                bean.setTotalAfecto(rs.getBigDecimal("tota"));
                bean.setTotalIgv(rs.getBigDecimal("toti"));
                bean.setTotalNeto(rs.getBigDecimal("totn"));
                bean.setIdVendedor(rs.getString("codven"));
                bean.setIdPtoEmision(rs.getString("codpto"));
                bean.setFlag(rs.getString("flag"));
                bean.setNomDoc(rs.getString("nomdoc"));
                bean.setSerie(rs.getString("serie"));
                bean.setPreimpreso(rs.getString("preimpreso"));                
                bean.setDniVendedor(rs.getString("DNIVEN"));
                bean.setNomVendedor(rs.getString("nomven"));
                bean.setPuntoEmsion(rs.getString("nompto"));
                bean.setDniSupervisor(rs.getString("dnisupervisor"));
                bean.setNomSupervisor(rs.getString("nomsupervisor"));
                bean.setDniGerenteZonal(rs.getString("dnigerentezonal"));
                bean.setNomGerenteZonal(rs.getString("nomgerentezonal"));
                bean.setDniCoordinador(rs.getString("dnicoordinador"));
                bean.setNomCoordinador(rs.getString("nomcoordinador"));
                bean.setNomMesa(rs.getString("nommesa"));
                bean.setUsuarioReg(rs.getString("USERREG"));
                bean.setEstadoActual(rs.getString("ESTADOACTUAL"));
                bean.setCodProy(rs.getString("CODPROY"));
                bean.setTotalPlan(rs.getBigDecimal("TOTALPLAN"));
                bean.setObservacion(rs.getString("OBSERVACION"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicCabeceraVenta.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}

