/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.TipoCambio;
import com.gocommerce.server.model.dao.DaoTipoCambio;
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
public class LogicTipoCambio {
    private PersistenceManager pm;
    
    public LogicTipoCambio(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public TipoCambio getBeanNow(Connection cnx) throws UnknownException{
        try {
            DaoTipoCambio ado = new DaoTipoCambio(pm);
            ArrayList<Parametro> parametros = new ArrayList();            
            ArrayList objetos = ado.getBeanNow(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<TipoCambio> lista = new ArrayList();
            while (rs.next()) {
                TipoCambio bean = new TipoCambio();
                bean.setFecha(rs.getDate("fecha"));
                bean.setTcCompra(rs.getBigDecimal("tccom"));
                bean.setTcVenta(rs.getBigDecimal("tcvta"));
                bean.setTcMercado(rs.getBigDecimal("tcmer"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            if(lista.size()>1){
                throw new UnknownException("Existe más de un tipo de cambio en el día");
            }
            return lista.get(0);
        } catch (Exception ex) {
            Logger.getLogger(LogicTipoCambio.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException("Ingrese Tipo de Cambio del día");
        }
    }
}
