/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.process.gestionseguridad;

import com.gocommerce.server.model.beans.DataSesion;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicDataSesion;
import com.gocommerce.server.util.AESencrypt;
import com.gocommerce.server.util.StringHex;
import com.gocommerce.server.util.UnknownException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.jdo.datastore.JDOConnection;

/**
 *
 * @author SISTEMAS
 */
public class LogOutUser {

    public static Boolean logOut(String nivel, String keyPublic, String idSession, Integer SessionIdSql, Integer idDataSesion, Date fechaLastSesion, DataSesion bean) throws UnknownException, Exception {
        keyPublic = StringHex.convertHexToString(keyPublic);
        //PMF.getClassPMF().destroyPMF(keyPublic);
        if (nivel.equals("user")) {
            String keySuperGoAdmin = bean.getBeanGO().getBeanGO().getMyToken();
            keySuperGoAdmin=AESencrypt.encrypt(keySuperGoAdmin);
            if (updateDataSesion(keySuperGoAdmin, idDataSesion, fechaLastSesion).equalsIgnoreCase("CORRECTO")) {
                PMF.getClassPMF().destroyPMF(keyPublic, idSession, SessionIdSql);
            } else {
                return false;
            }
        }else if (nivel.equals("admin")) {
            String keySuperGoAdmin = bean.getBeanGO().getMyToken();
            keySuperGoAdmin=AESencrypt.encrypt(keySuperGoAdmin);
            if (updateDataSesion(keySuperGoAdmin, idDataSesion, fechaLastSesion).equalsIgnoreCase("CORRECTO")) {
                PMF.getClassPMF().destroyPMF(keyPublic);
            } else {
                return false;
            }
        }else if (nivel.equals("superadmin")) {
            if (updateDataSesion(keyPublic, idDataSesion, fechaLastSesion).equalsIgnoreCase("CORRECTO")) {
                PMF.getClassPMF().destroyPMF(keyPublic);
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public static String updateDataSesion(String keyPublic, Integer idDataSesion, Date fechaLastSesion) throws UnknownException {
        String msg = "error";
        PersistenceManager pm = null;
        Connection cnx = null;
        try {
            pm = PMF.getClassPMF().getPMF(keyPublic).getPersistenceManager();
            if (!pm.isClosed()) {
                LogicDataSesion logic = new LogicDataSesion(pm);
                JDOConnection cnxJDO = pm.getDataStoreConnection();
                cnx = (Connection) cnxJDO.getNativeConnection();
                cnx.setAutoCommit(false);
                msg = logic.updateDataSesion(cnx, idDataSesion, fechaLastSesion);
                cnx.commit();
                cnx.close();
                return msg.trim();
            } else {
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }
        } catch (SQLException ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if (cnx != null) {
                try {
                    if (!cnx.isClosed()) {
                        cnx.rollback();
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {
                pm.close();
            }
        }
    }
}
