/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.InitParam;
import com.gocommerce.server.model.dao.DaoInitParam;
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
public class LogicInitParam {
    private PersistenceManager pm;
    
    public LogicInitParam(PersistenceManager pm) {
        this.pm=pm;
    }
    
    public ArrayList<InitParam> getListarBean(Connection cnx) throws UnknownException{
        try {
            DaoInitParam ado = new DaoInitParam(pm);
            ArrayList<Parametro> parametros = new ArrayList();            
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<InitParam> lista = new ArrayList();
            while (rs.next()) {
                InitParam bean = new InitParam();
                bean.setId(1); 
                bean.setIgv(rs.getBigDecimal("IGV"));
                bean.setHabilitaPercepcion(rs.getInt("PERSNT"));
                bean.setAgentePercepcion(rs.getInt("PERCIA"));
                bean.setTopeBoletaVentaSinDni(rs.getBigDecimal("TOPEBOLVTA"));
                bean.setDsctoAgentePerceptor(rs.getBigDecimal("PERCIA_DSCT"));
                bean.setDsctoFacturaPerceptor(rs.getBigDecimal("PERFAC_DSCT"));
                bean.setDsctoBoletaPerceptor(rs.getBigDecimal("PERBOL_DSCT"));
                bean.setTopeBoletaPercepcion(rs.getBigDecimal("PERBOL_TOPE"));   
                bean.setCtaIgv(rs.getString("CTA4011"));
                bean.setCtaClientes(rs.getString("CTA121"));
                bean.setCtaPercepcion(rs.getString("PERCTAIGV_PAG"));
                bean.setMaxDetalleCot(rs.getInt("LINDETCOT"));
                bean.setMaxDetalleBol(rs.getInt("LINDETBOL"));
                bean.setMaxDetalleFac(rs.getInt("LINDETFAC"));
                bean.setFechaServer(rs.getDate("FECHASERVER"));
                bean.setFrontera(rs.getInt("frontera"));
                bean.setAutoDespacho(rs.getInt("AUTODES"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicInitParam.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
