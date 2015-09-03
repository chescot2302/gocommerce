/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.PuntoEmision;
import com.gocommerce.server.model.dao.DaoPuntoEmision;
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
public class LogicPuntoEmision {
    private PersistenceManager pm;

    public LogicPuntoEmision(PersistenceManager pm) {
        this.pm = pm;
    }

    public ArrayList<PuntoEmision> getListarBeanxTienda(Connection cnx,String idTienda) throws UnknownException {
        try {
            DaoPuntoEmision ado = new DaoPuntoEmision(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            Parametro param1=new Parametro("IN",idTienda);
            parametros.add(param1);
            ArrayList objetos = ado.getListarBeanTienda(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<PuntoEmision> lista = new ArrayList();
            while (rs.next()) {
                PuntoEmision bean = new PuntoEmision();
                bean.setId(rs.getString("codpto").trim()); 
                bean.setDescripcion(rs.getString("nompto").trim());
                bean.setIdSucursal(rs.getString("codsuc").trim());
                bean.setIdTienda(rs.getString("codtie").trim());
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicPuntoEmision.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
