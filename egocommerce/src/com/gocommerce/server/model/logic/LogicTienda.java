/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.Tienda;
import com.gocommerce.server.model.dao.DaoTienda;
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
public class LogicTienda {
    private PersistenceManager pm;

    public LogicTienda(PersistenceManager pm) {
        this.pm = pm;
    }

    public ArrayList<Tienda> getListarBeanxSucursal(Connection cnx,String idSucursal) throws UnknownException {
        try {
            DaoTienda ado = new DaoTienda(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            Parametro param1=new Parametro("IN",idSucursal);
            parametros.add(param1);
            ArrayList objetos = ado.getListarBeanSucursal(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<Tienda> lista = new ArrayList();
            while (rs.next()) {
                Tienda bean = new Tienda();
                bean.setId(rs.getString("codtie").trim()); 
                bean.setDescripcion(rs.getString("nomtie").trim());
                bean.setIdSucursal(rs.getString("codsuc").trim());
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicTienda.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
