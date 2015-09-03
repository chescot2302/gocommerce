/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Distrito;
import com.gocommerce.server.model.dao.DaoDistrito;
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
public class LogicDistrito {
    private PersistenceManager pm;
    
    public LogicDistrito(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<Distrito> getListarBean(Connection cnx,String idPais,String idDepartamento,String idProvincia) throws UnknownException{
        try {
            DaoDistrito ado = new DaoDistrito(pm);
            ArrayList<Parametro> parametros = new ArrayList();               
            Parametro param1=new Parametro("IN",idPais);
            Parametro param2=new Parametro("IN",idDepartamento);
            Parametro param3=new Parametro("IN",idProvincia);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            ArrayList objetos = ado.getListarBeanxProvincia(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Distrito> lista = new ArrayList();
            while (rs.next()) {
                Distrito bean = new Distrito();
                bean.setIdDistrito(rs.getString("coddis").trim());     
                bean.setDescripcion(rs.getString("nomdis").trim());
                bean.setIdProvincia(rs.getString("codpro").trim());
                bean.setIdDepartamento(rs.getString("coddep").trim());
                bean.setIdPais(rs.getString("codpai").trim());
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicDistrito.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
