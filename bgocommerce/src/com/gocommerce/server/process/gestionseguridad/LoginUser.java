/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.process.gestionseguridad;

import com.gocommerce.server.model.beans.BdUsuario;
import com.gocommerce.server.model.dao.Conexion;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicBdUsuario;
import com.gocommerce.server.model.beans.CredencialUser;
import com.gocommerce.server.model.beans.DataSesion;
import com.gocommerce.server.model.logic.LogicDataSesion;
import com.gocommerce.server.model.xmlstore.StoreDataSesion;
import com.gocommerce.server.process.gestionventas.GestionFacturacion;
import com.gocommerce.server.util.AESencrypt;
import com.gocommerce.server.util.StringHex;
import com.gocommerce.server.util.UnknownException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.datastore.JDOConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author SISTEMAS
 */
public class LoginUser {

    public static HashMap<String,Object> loginUser(CredencialUser bean) throws UnknownException{
        try {
            HashMap<String,Object> map=new HashMap();
            Properties props=Autenticacion.intercambiarCredencial(bean,map);
            String valor=bean.getClavePublica()+"@"+bean.getUsuario()+"@"+bean.getMiClavePrivada();
            String keyPrivateUser=AESencrypt.encrypt(valor);
            keyPrivateUser= Autenticacion.autenticar(keyPrivateUser, props,map);
            map.put("keypublic", keyPrivateUser);
            return map;
        } catch (SQLException ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        }
    }


    private static class Autenticacion {
        
        public static Properties intercambiarCredencial(CredencialUser bean,HashMap<String,Object> map) throws UnknownException, SQLException, Exception{                        
            Properties props=null;
            String keyPublicAdmin=AESencrypt.encrypt(bean.getClavePublica());            
            PersistenceManager pm=null;
            try{
                pm=PMF.getClassPMF().getPMF(keyPublicAdmin).getPersistenceManager();              
            }catch(Exception ex){
                throw new UnknownException("Error!\nAutenticar GoAdmin\n"+ex.getLocalizedMessage());
            }
            
            if(!pm.isClosed()){
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                Connection con =(Connection)cnxJDO.getNativeConnection();                
                DatabaseMetaData md=con.getMetaData();
                String bd=md.getUserName();
                if(bd.indexOf("@")!=-1){
                    bd=bd.substring(0,bd.indexOf("@"));
                }                                
                LogicBdUsuario logicBdUsuario=new LogicBdUsuario(pm);                
                List<BdUsuario> listaBdUsuario=(List<BdUsuario>)logicBdUsuario.getListarBean("USER",bd,bean.getUsuario(),bean.getClave());
                if(listaBdUsuario.size()==1){
                    BdUsuario beanBdUSuario=listaBdUsuario.get(0);
                    Conexion cnx=new Conexion();
                    //props=cnx.cnxBdMysql(beanBdUSuario.getUsuarioBd(), beanBdUSuario.getClaveBd(),beanBdUSuario.getBeanBdEmpresa().getIpHost(),beanBdUSuario.getBeanBdEmpresa().getPuerto(), beanBdUSuario.getSchema());                    
                    props=cnx.cnxBdSQLServer(beanBdUSuario.getUsuarioBd(), beanBdUSuario.getClaveBd(),beanBdUSuario.getBeanBdEmpresa().getIpHost(),beanBdUSuario.getBeanBdEmpresa().getPuerto(), beanBdUSuario.getSchema());                    
                    map.put("usuariobd", beanBdUSuario.getUsuarioBd());
                    map.put("schemabd", beanBdUSuario.getSchema());
                }else if(listaBdUsuario.isEmpty()){
                    throw new UnknownException("Error!\nNo existe Credencial");
                }else{
                    throw new UnknownException("Error!\nExiste más de una credencial");
                }
                cnxJDO.close();
                pm.close();
            }
            if(props==null){
                 throw new UnknownException("Error!\nNull Properties Connection");
            }else{
                return props;
            }               
        }
        
        public static String autenticar(String miClavePrivada,Properties props, HashMap<String, Object> lista) throws UnknownException{            
            try{
            String response="ERROR";
            PersistenceManager pm = PMF.getClassPMF().getPMF(miClavePrivada,props).getPersistenceManager();
            if(!pm.isClosed()){
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                    Connection con = (Connection) cnxJDO.getNativeConnection();
                    Statement stmt = con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select @@SPID as IDSESIONSQL");
                    while (rs.next()) {
                        Integer idSesionSQL = rs.getInt("IDSESIONSQL");
                        lista.put("spid", idSesionSQL);
                    }
                    rs.close();
                    stmt.close();
                    con.close();
                    cnxJDO.close();
                response=StringHex.convertStringToHex(miClavePrivada);
                pm.close();
            }
            return response;            
            }catch(Exception ex){
                throw new UnknownException("Error!\n"+"Credenciales no coinciden\n"+ex.getLocalizedMessage());
            }
        }
        
        
    }
    
    public static void insertDataSesion(String clavePublicaAdmin,String miClavePrivada, List<DataSesion> data, Integer sesionIdSql, String schemaBd, String nivel, String usuario, String usuarioBd, DataSesion bean) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            String keypublicAdmin = AESencrypt.encrypt(clavePublicaAdmin);             
            DataSesion beanGo=PMF.getClassPMF().getSesion(keypublicAdmin);
            data.get(0).setIdUsuarioBdGO(beanGo.getIdUsuarioBd());
            StoreDataSesion store = new StoreDataSesion();
            store.setLista(data);
            JAXBContext context = JAXBContext.newInstance(StoreDataSesion.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
            m.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
            m.marshal(store, System.out);
            Writer writer = new StringWriter();
            m.marshal(store, writer);
            String strSesion = writer.toString();
            writer.close();      
            String keypublicSuper=beanGo.getTokenGo();
            keypublicSuper = AESencrypt.encrypt(keypublicSuper);             
            pm = PMF.getClassPMF().getPMF(keypublicSuper).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicDataSesion logic = new LogicDataSesion(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                cnx.setAutoCommit(false);                
                bean.setIdUsuarioBdGO(beanGo.getIdUsuarioBd());
                logic.insertDataSesion(cnx, strSesion, sesionIdSql, schemaBd, nivel, usuario, usuarioBd, bean);
                if (bean.getIdDataSesion() != null) {
                    //System.out.println(bean.getIdDataSesion());
                    bean.setBeanGO(beanGo);
                    String keypublic = StringHex.convertHexToString(miClavePrivada);                    
                    keypublic=keypublic+"@"+bean.getIdSession()+"@"+bean.getSessionIdSQL();
                    PMF.getClassPMF().getSesion(keypublic, bean);
                    cnx.commit();
                    cnx.close();
                }
            } else {
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }
            //return bean;
        } catch (JAXBException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (IOException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if (cnx != null) {
                try {
                    if (!cnx.isClosed()) {
                        cnx.rollback();
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GestionFacturacion.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }
}
