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
public class DaoUsuarioCorrelativo {
    private PersistenceManager pm;

    public DaoUsuarioCorrelativo(PersistenceManager pm) {
        this.pm = pm;
    }

    public ArrayList getListarBean(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call NewSpCorreUser()}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList insertarUsuarioCorrelativo(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call NewSpInsertarCorreUser(?,?,?,?,?,?,?,?)}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList eliminarUsuarioCorrelativo(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call NewSpEliminarCorreUser(?)}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList desactivarUsuarioCorrelativo(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call NewSpDesactivarCorreUser(?,?)}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList actualizarUsuarioCorrelativo(ArrayList<Parametro> param, Connection objCnx) throws UnknownException {
        try {
            ArrayList result;
            String fun = "{call NewSpActualizarCorreUser(?,?,?,?,?,?)}";
            Consultas query = new Consultas();
            result = query.funcionSQLServerResultSet(fun, param, objCnx);
            result.add(objCnx);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(DaoUsuarioCorrelativo.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}
