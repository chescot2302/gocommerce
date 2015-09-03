/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Departamento;
import com.gocommerce.server.model.dao.DaoDepartamento;
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
public class LogicDepartamento {
    private PersistenceManager pm;
    
    public LogicDepartamento(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<Departamento> getListarBean(Connection cnx,String idPais) throws UnknownException{
        try {
            DaoDepartamento ado = new DaoDepartamento(pm);
            ArrayList<Parametro> parametros = new ArrayList();               
            Parametro param1=new Parametro("IN",idPais);
            parametros.add(param1);
            ArrayList objetos = ado.getListarBeanxPais(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Departamento> lista = new ArrayList();
            while (rs.next()) {
                Departamento bean = new Departamento();
                bean.setIdDepartamento(rs.getString("coddep").trim());     
                bean.setDescripcion(rs.getString("nomdep").trim());
                bean.setIdPais(rs.getString("codpai").trim());
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicDepartamento.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
