/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Usuario;
import com.gocommerce.server.model.dao.DaoUsuario;
import com.gocommerce.server.util.NavaEncrypt;
import com.gocommerce.server.util.Parametro;
import com.gocommerce.server.util.UnknownException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class LogicUsuario {
    private PersistenceManager pm;
    
    public LogicUsuario(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public Usuario getBeanNick(Connection cnx,String nick) throws UnknownException{
        try {
            DaoUsuario ado = new DaoUsuario(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",nick);
            parametros.add(param1);
            ArrayList objetos = ado.getBeanNick(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Usuario> lista = new ArrayList();
            while (rs.next()) {
                Usuario bean = new Usuario();
                bean.setId(rs.getString("codusu"));
                bean.setNombres(rs.getString("nomusu"));
                bean.setNick(rs.getString("nomacc"));
                bean.setFechaIngreso(rs.getDate("fecing"));
                bean.setNivel(rs.getString("nivusu"));
                bean.setIdPuntoEmision(rs.getString("codpto"));
                bean.setIdGrupo(rs.getString("codgru"));
                bean.setIdVendedor(rs.getString("codven"));
                bean.setIdAlmacen(rs.getString("codalm"));
                bean.setIdDocumento(rs.getString("cdocu"));
                bean.setVersion(Long.parseLong("1"));
                bean.setNomPtoEmision(rs.getString("nompto"));
                bean.setIdTienda(rs.getString("codtie"));
                bean.setNomTienda(rs.getString("nomtie"));
                bean.setIdSucursal(rs.getString("codsuc"));
                bean.setNomSucursal(rs.getString("nomsuc"));
                bean.setIdLocalidad(rs.getString("idLocalidad"));
                bean.setNomLocalidad(rs.getString("nomLocalidad"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            if(lista.size()>1){
                throw new UnknownException("Usuario no es unico");
            }
            return lista.get(0);
        } catch (Exception ex) {
            Logger.getLogger(LogicUsuario.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public String getPassword(Connection cnx,String esquema,String nick) throws UnknownException{
        try {
            DaoUsuario ado = new DaoUsuario(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",esquema);
            Parametro param1=new Parametro("IN",nick);
            Parametro param2=new Parametro("OUT",Types.VARCHAR);
            parametros.add(param0);
            parametros.add(param1);
            parametros.add(param2);
            ArrayList objetos = ado.getPassword(parametros, cnx);
            String value = (String) objetos.get(0);
            value=NavaEncrypt.decript(value);
            ResultSet rs= (ResultSet) objetos.get(1);
            CallableStatement cst = (CallableStatement) objetos.get(2);
            rs.close();
            cst.close();
            return value;
        } catch (Exception ex) {
            Logger.getLogger(LogicUsuario.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    
    public List<Usuario> getListarBeanPto(Connection cnx,String idPuntoEmision,String idDocumento,String serie) throws UnknownException{
        try {
            DaoUsuario ado = new DaoUsuario(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param1=new Parametro("IN",idPuntoEmision);
            Parametro param2=new Parametro("IN",idDocumento);
            Parametro param3=new Parametro("IN",serie);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            ArrayList objetos = ado.getListarBeanPto(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Usuario> lista = new ArrayList();
            while (rs.next()) {
                Usuario bean = new Usuario();
                bean.setId(rs.getString("codusu"));
                bean.setNombres(rs.getString("nomusu"));
                bean.setNick(rs.getString("nomacc"));
                bean.setIdBdUsuario(rs.getInt("IDBDUSUARIO"));                
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();           
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicUsuario.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
