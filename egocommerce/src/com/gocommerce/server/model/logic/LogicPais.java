/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Pais;
import com.gocommerce.server.model.dao.DaoPais;
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
public class LogicPais {
    private PersistenceManager pm;
    
    public LogicPais(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<Pais> getListarBean(Connection cnx) throws UnknownException{
        try {
            DaoPais ado = new DaoPais(pm);
            ArrayList<Parametro> parametros = new ArrayList();               
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Pais> lista = new ArrayList();
            while (rs.next()) {
                Pais bean = new Pais();
                bean.setIdPais(rs.getString("codpai").trim());     
                bean.setDescripcion(rs.getString("nompai").trim());
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicPais.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
       
}
