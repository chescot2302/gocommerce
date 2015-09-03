package com.gocommerce.server.model.logic;

import com.gocommerce.server.model.beans.BdUsuario;
import com.gocommerce.server.model.dao.DaoBdUsuario;
import com.gocommerce.server.util.BeanParametro;
import com.gocommerce.server.util.Parametro;
import com.gocommerce.server.util.UnknownException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;

public class LogicBdUsuario {
	private PersistenceManager pm;

	public LogicBdUsuario(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoBdUsuario dao = new DaoBdUsuario(this.pm);
		return dao.mantenimiento(parametro);
	}
        
        public Object backOperBean(BeanParametro parametro)
			throws UnknownException {
		DaoBdUsuario dao = new DaoBdUsuario(this.pm);
		return dao.backOperBean(parametro);
	}

	public BdUsuario getBean(Integer id) throws UnknownException {
		DaoBdUsuario dao = new DaoBdUsuario(this.pm);
		return (BdUsuario)dao.getBean(id);
	}

	public Collection<BdUsuario> getListarBean() throws UnknownException {
		DaoBdUsuario dao = new DaoBdUsuario(this.pm);
		Collection<BdUsuario> lista = dao.getListarBean();
		return lista;
	}
        
        public Collection<BdUsuario> getListarBean(String nivel,String bd,String usuario,String clave) throws UnknownException {
		DaoBdUsuario dao = new DaoBdUsuario(this.pm);
		Collection<BdUsuario> lista = dao.getListarBean(nivel,bd,usuario,clave);
		return lista;
	}
        
        

	public Collection<BdUsuario> getListarBean(String estado)
			throws UnknownException {
		DaoBdUsuario dao = new DaoBdUsuario(this.pm);
		Collection<BdUsuario> lista = dao.getListarBean(estado);
		return lista;
	}
        
        public Boolean insertBean(Connection cnx,int idBdUsuario,int idEmpresa,String esquema,String nivel,String userLog,String claveLog,String userBd,String claveBd,java.sql.Date fechaIni) throws UnknownException{
        try {
            DaoBdUsuario ado = new DaoBdUsuario(pm);
            ArrayList<Parametro> parametros = new ArrayList();   
            Parametro param0=new Parametro("IN",idBdUsuario);            
            Parametro param1=new Parametro("IN",idEmpresa);            
            Parametro param2=new Parametro("IN",esquema);            
            Parametro param3=new Parametro("IN",nivel);            
            Parametro param4=new Parametro("IN",userLog);            
            Parametro param5=new Parametro("IN",claveLog);            
            Parametro param6=new Parametro("IN",userBd);            
            Parametro param7=new Parametro("IN",claveBd);            
            Parametro param8=new Parametro("IN",fechaIni);           
            parametros.add(param0);
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);
            parametros.add(param4);
            parametros.add(param5);
            parametros.add(param6);
            parametros.add(param7);
            parametros.add(param8);
            ArrayList objetos = ado.insertBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);            
            String val="false";
            while (rs.next()) {
                val=rs.getString("result");
            }
            rs.close();
            cst.close();
            return val.equals("true");
        } catch (Exception ex) {
            Logger.getLogger(LogicBdEmpresa.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
        
        
        public BdUsuario getBean(Connection cnx,String esquema,String userBd,String nivel,String usuarioLog) throws UnknownException{
        try {
            DaoBdUsuario ado = new DaoBdUsuario(pm);
            ArrayList<Parametro> parametros = new ArrayList();             
            Parametro param1=new Parametro("IN",esquema);            
            Parametro param2=new Parametro("IN",userBd);                     
            Parametro param3=new Parametro("IN",nivel);                                               
            Parametro param4=new Parametro("IN",usuarioLog);                                               
            parametros.add(param1);
            parametros.add(param2);
            parametros.add(param3);     
            parametros.add(param4);            
            ArrayList objetos = ado.getBean(parametros, cnx);
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);   
            List<BdUsuario> lista=new ArrayList();
            while (rs.next()) {
                BdUsuario bean = new BdUsuario();
                bean.setIdBdUsuario(rs.getInt("IDBDUSUARIO"));
                lista.add(bean);
            }
            rs.close();
            cst.close();
            if(lista.size()>1){
                throw new UnknownException("Existe más de un cliente");
            }
            return lista.get(0);
        } catch (Exception ex) {
            Logger.getLogger(LogicBdEmpresa.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownException(ex.getMessage());
        }
    }
}

