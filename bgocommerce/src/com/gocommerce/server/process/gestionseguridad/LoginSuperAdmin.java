/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.process.gestionseguridad;

import com.gocommerce.server.model.beans.CredencialSuperAdmin;
import com.gocommerce.server.model.beans.DataSesion;
import com.gocommerce.server.model.dao.Conexion;
import com.gocommerce.server.model.dao.PMF;
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
public class LoginSuperAdmin {

    public static HashMap<String, Object> loginSuperAdmin(
            CredencialSuperAdmin bean
    ) throws UnknownException {
        try {
            Properties props = Credencial.getCredencial(bean);
            String keypublic = AESencrypt.encrypt(bean.getClavePublica());
            HashMap<String, Object> lista = new HashMap();
            keypublic = Autenticacion.autenticar(keypublic, props, lista);
            lista.put("keypublic", keypublic);
            lista.put("usuariobd", bean.getUsuario());
            lista.put("schemabd", bean.getBd());
            return lista;
        } catch (Exception ex) {
            throw new UnknownException(ex.getLocalizedMessage());
        }
    }

    private static class Credencial {

        public static Properties getCredencial(
                CredencialSuperAdmin bean
        ) {
            Conexion cnx = new Conexion();
            //Properties props=cnx.cnxBdMysql(bean.getUsuario(), bean.getClave(),bean.getIp(),bean.getPuerto(), bean.getBd());
            Properties props = cnx.cnxBdSQLServer(bean.getUsuario(), bean.getClave(), bean.getIp(), bean.getPuerto(), bean.getBd());
            return props;
        }
    }

    private static class Autenticacion {

        public static String autenticar(String clavePublica, Properties props, HashMap<String, Object> lista) throws UnknownException {
            try {
                String response = "ERROR";
                PersistenceManager pm = PMF.getClassPMF().getPMF(clavePublica, props).getPersistenceManager();
                if (!pm.isClosed()) {
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
                    response = StringHex.convertStringToHex(clavePublica);
                    pm.close();
                }
                return response;
            } catch (Exception ex) {
                throw new UnknownException("Error!\n" + "Credenciales no coinciden\n" + ex.getLocalizedMessage());
            }
        }
    }

    public static void insertDataSesion(String clavePublica, List<DataSesion> data, Integer sesionIdSql, String schemaBd, String nivel, String usuario, String usuarioBd, DataSesion bean) throws UnknownException {
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
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
            String keypublic = StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicDataSesion logic = new LogicDataSesion(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                cnx.setAutoCommit(false);
                logic.insertDataSesion(cnx, strSesion, sesionIdSql, schemaBd, nivel, usuario, usuarioBd, bean);
                if (bean.getIdDataSesion() != null) {
                    //System.out.println(bean.getIdDataSesion());
                    //keypublic=keypublic+"@"+bean.getIdSession()+"@"+bean.getSessionIdSQL();
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
