/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.dao;

import com.gocommerce.server.util.Parametro;
import com.gocommerce.server.util.UnknownException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;

/**
 *
 * @author SISTEMAS
 */
public class DaoDocumento {    
    private PersistenceManager pm;

	public DaoDocumento(PersistenceManager pm) {
		this.pm = pm;
	}        
        
        public ArrayList getListarBeanVenta(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call NewSpListarDocVenta()}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
        
        public ArrayList getDocVentaCe(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call NewSpListarDocCanalEmp()}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
        
        public ArrayList getListarBeanDespacho(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call NewSpListarDocDespacho()}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoItem.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
