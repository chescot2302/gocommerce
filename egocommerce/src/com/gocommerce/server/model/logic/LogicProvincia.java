/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Provincia;
import com.gocommerce.server.model.dao.DaoProvincia;
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
public class LogicProvincia {
    private PersistenceManager pm;
    
    public LogicProvincia(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<Provincia> getListarBean(Connection cnx,String idPais,String idDepartamento) throws UnknownException{
        try {
            DaoProvincia ado = new DaoProvincia(pm);
            ArrayList<Parametro> parametros = new ArrayList();               
            Parametro param1=new Parametro("IN",idPais);
            Parametro param2=new Parametro("IN",idDepartamento);
            parametros.add(param1);
            parametros.add(param2);
            ArrayList objetos = ado.getListarBeanxDepartamento(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Provincia> lista = new ArrayList();
            while (rs.next()) {
                Provincia bean = new Provincia();
                bean.setIdProvincia(rs.getString("codpro").trim());     
                bean.setDescripcion(rs.getString("nompro").trim());
                bean.setIdDepartamento(rs.getString("coddep").trim());
                bean.setIdPais(rs.getString("codpai").trim());
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicProvincia.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
