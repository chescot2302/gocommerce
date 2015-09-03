/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.TipoVenta;
import com.gocommerce.server.model.dao.DaoTipoVenta;
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
public class LogicTipoVenta {
    private PersistenceManager pm;

    public LogicTipoVenta(PersistenceManager pm) {
        this.pm = pm;
    }
    

    public ArrayList<TipoVenta> getListarBean(Connection cnx) throws UnknownException {
        try {
            DaoTipoVenta ado = new DaoTipoVenta(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<TipoVenta> lista = new ArrayList();
            while (rs.next()) {
                TipoVenta bean = new TipoVenta();
                bean.setIdTipoVenta(rs.getString("codvta").trim());                
                bean.setDescripcion(rs.getString("Nomvta").trim());
                bean.setCuenta(rs.getString("cuenta").trim());   
                bean.setManejaStock(rs.getInt("msto"));
                bean.setCobroEnCaja(rs.getInt("cobcaj"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicTipoVenta.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
