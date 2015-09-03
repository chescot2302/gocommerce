/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.EstProy;
import com.gocommerce.server.model.beans.EstProy;
import com.gocommerce.server.model.dao.DaoEstProy;
import com.gocommerce.server.model.dao.DaoEstProy;
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
public class LogicEstProy {
    private PersistenceManager pm;

    public LogicEstProy(PersistenceManager pm) {
        this.pm = pm;
    }
    

    public ArrayList<EstProy> getListarBean(Connection cnx,String cDocu,String nDocu) throws UnknownException {
        try {
            DaoEstProy ado = new DaoEstProy(pm);
            ArrayList<Parametro> parametros = new ArrayList();
            Parametro param1=new Parametro("IN",cDocu);
            Parametro param2=new Parametro("IN",nDocu);
            parametros.add(param1);
            parametros.add(param2);
            ArrayList objetos = ado.getListarBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<EstProy> lista = new ArrayList();
            while (rs.next()) {
                EstProy bean = new EstProy();
                bean.setIdEstadoProy(rs.getInt("IDESTADOPROY"));
                bean.setFechaInc(rs.getDate("FECHAINC"));
                bean.setFechaCese(rs.getDate("FECHACESE"));
                bean.setEstado(rs.getInt("ESTADO"));
                bean.setEstadoActual(rs.getString("ESTADOACTUAL"));
                bean.setObservacion(rs.getString("OBSERVACION"));
                bean.setVersion(Long.parseLong("1"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicEstProy.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
    
    public ArrayList<String> actualizarEstProy(Connection cnx,EstProy bean) throws UnknownException{
        try {
            DaoEstProy ado = new DaoEstProy(pm);
            ArrayList<Parametro> parametros = new ArrayList(); 
            Parametro param0=new Parametro("IN",bean.getTipoDoc());
            Parametro param1=new Parametro("IN",bean.getCorrelativo());
            Parametro param2=new Parametro("IN",bean.getEstadoActual());            
            Parametro param3=new Parametro("IN",bean.getObservacion());            
            parametros.add(param0);
            parametros.add(param1);
            parametros.add(param2);            
            parametros.add(param3);            
            ArrayList objetos = ado.actualizarEstProy(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            ArrayList<String> lista = new ArrayList();
            while (rs.next()) {                
                lista.add(rs.getString(1));
            }
            rs.close();
            cst.close();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(LogicEstProy.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }        
    
}
