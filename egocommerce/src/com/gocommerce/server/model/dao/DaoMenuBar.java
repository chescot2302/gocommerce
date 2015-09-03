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
public class DaoMenuBar {
    private PersistenceManager pm;

    public DaoMenuBar(PersistenceManager pm) {
        this.pm = pm;
    }
    
    public ArrayList getListarBean(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call NewSpListarMenuBar()}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoMenuBar.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList getListarBeanXusuario(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call NewSpGrantMenusuario(?,?)}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoMenuBar.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    
    public ArrayList InsertGrantMenusuario(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call NewSpInsertGrantMenusuario(?,?)}";
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
