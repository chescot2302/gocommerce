/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Cliente;
import com.gocommerce.server.model.dao.DaoCliente;
import com.gocommerce.server.util.Parametro;
import com.gocommerce.server.util.UnknownException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class LogicCliente {
    private PersistenceManager pm;
    
    public LogicCliente(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public Cliente getBean(Connection cnx,String documento) throws UnknownException{
        try {
            DaoCliente ado = new DaoCliente(pm);
            ArrayList<Parametro> parametros = new ArrayList();   
            Parametro param1=new Parametro("IN",documento);
            parametros.add(param1);
            ArrayList objetos = ado.getBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);            
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Cliente> lista = new ArrayList();
            while (rs.next()) {
                Cliente bean = new Cliente();
                bean.setId(rs.getString("codcli").trim());                
                bean.setNombres(rs.getString("nomcli"));
                bean.setRuc(rs.getString("ruccli").trim());
                bean.setDni(rs.getString("nrodni").trim());
                bean.setTipoPersona(rs.getInt("flaper"));
                bean.setPercepcion(rs.getInt("ctlper"));
                bean.setAgentePerceptor(rs.getInt("catsnt"));
                bean.setEstado(rs.getInt("estado"));
                bean.setVigenciaLineaCredito(rs.getInt("flalin"));
                bean.setMonedaLineaCredito(rs.getString("codmonlinea"));
                bean.setMontoCredito(rs.getBigDecimal("mcredi"));
                bean.setAmpliaCredito(rs.getBigDecimal("mampcre"));
                bean.setIdCondicionVenta(rs.getString("codcdv"));
                bean.setPorcDsctoContado(rs.getBigDecimal("dsctxv"));
                bean.setPorcDsctoCredito(rs.getBigDecimal("dsctcr"));
                bean.setDireccion(rs.getString("dircli"));
                bean.setDireccionEntrega(rs.getString("dirent"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            if(lista.size()>1){
                throw new UnknownException("Existe más de un cliente");
            }
            return lista.get(0);
        } catch (Exception ex) {
            Logger.getLogger(LogicCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException("Cliente no encontrado");
        }
    }
    
    public List<Cliente> getListarBean(Connection cnx,Integer indexFiltro,String filtro) throws UnknownException{
        try {
            DaoCliente ado = new DaoCliente(pm);
            ArrayList<Parametro> parametros = new ArrayList();  
            Parametro param1=new Parametro("IN",indexFiltro);
            Parametro param2=new Parametro("IN",filtro);
            parametros.add(param1);
            parametros.add(param2);
            ArrayList objetos = ado.getListaBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);            
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Cliente> lista = new ArrayList();
            while (rs.next()) {
                Cliente bean = new Cliente();
                bean.setId(rs.getString("codcli").trim());                
                bean.setNombres(rs.getString("nomcli"));
                bean.setRuc(rs.getString("ruccli").trim());
                bean.setDni(rs.getString("nrodni").trim());
                bean.setTipoPersona(rs.getInt("flaper"));
                bean.setPercepcion(rs.getInt("ctlper"));
                bean.setAgentePerceptor(rs.getInt("catsnt"));
                bean.setEstado(rs.getInt("estado"));
                bean.setVigenciaLineaCredito(rs.getInt("flalin"));
                bean.setMonedaLineaCredito(rs.getString("codmonlinea"));
                bean.setMontoCredito(rs.getBigDecimal("mcredi"));
                bean.setAmpliaCredito(rs.getBigDecimal("mampcre"));
                bean.setIdCondicionVenta(rs.getString("codcdv"));
                bean.setPorcDsctoContado(rs.getBigDecimal("dsctxv"));
                bean.setPorcDsctoCredito(rs.getBigDecimal("dsctcr"));
                bean.setDireccion(rs.getString("dircli"));
                bean.setDireccionEntrega(rs.getString("dirent"));
                bean.setVersion(Long.parseLong("1"));
                bean.setTipoDocIden(rs.getString("tipodoc"));
                bean.setDocumento(rs.getString("documento"));
                lista.add(bean);
            }
            rs.close();
            cst.close();           
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException("Cliente no encontrado");
        }
    }
    
    public ArrayList<String> insertarCliente(Connection cnx,Cliente bean) throws UnknownException{
        try {
            Date fecha=new Date();
            DaoCliente ado = new DaoCliente(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",bean.getTipoDocIden());
            Parametro param2=new Parametro("IN",bean.getNombres());
            Parametro param3=new Parametro("IN",bean.getRuc());
            Parametro param4=new Parametro("IN",bean.getIdPais());
            Parametro param5=new Parametro("IN",bean.getIdDepartamento());
            Parametro param6=new Parametro("IN",bean.getIdProvincia());
            Parametro param7=new Parametro("IN",bean.getIdDistrito());
            Parametro param8=new Parametro("IN",bean.getDireccion());
            Parametro param9=new Parametro("IN",bean.getTelefono());
            Parametro param10=new Parametro("IN",bean.getEmail());
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);
            parametros.add(param6);
            parametros.add(param7);
            parametros.add(param8);
            parametros.add(param9);
            parametros.add(param10);
            ArrayList objetos = ado.insertarCliente(parametros, cnx);
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
            Logger.getLogger(LogicUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
