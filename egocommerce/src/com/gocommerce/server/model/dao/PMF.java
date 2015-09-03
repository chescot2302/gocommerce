/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gocommerce.server.model.dao;

import com.gocommerce.server.model.beans.DataSesion;
import java.util.HashMap;
import java.util.Properties;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 *
 * @author SISTEMAS
 */
public final class PMF {

    private static final PMF pmf = new PMF();    
    private final HashMap<String, DataSesion> mapSesion = new HashMap();
    private final HashMap<String, PersistenceManagerFactory> mapPMF = new HashMap();

    private PMF() {        
    }

    public static PMF getClassPMF() {
        return pmf;
    }

    public PersistenceManagerFactory getPMF(String publickey, Properties props) {
        if (mapPMF.get(publickey) == null) {
            PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(props);
            mapPMF.put(publickey, pmf);
        }
        return mapPMF.get(publickey);
    }
    
    public DataSesion getSesion(String publickey, DataSesion bean) {
        if (mapSesion.get(publickey) == null) {            
            mapSesion.put(publickey, bean);
        }
        return mapSesion.get(publickey);
    }
    
    public PersistenceManagerFactory getPMF(String publickey) {        
        return mapPMF.get(publickey);
    }
    
    public DataSesion getSesion(String publickey) {        
        return mapSesion.get(publickey);
    }
    
    

    public void destroyPMF(String publickey) {
        if (mapPMF.get(publickey) != null) {
            mapPMF.get(publickey).close();
            mapPMF.remove(publickey);
            mapSesion.remove(publickey);
        }

    }
    
    public void destroyPMF(String publickey,String idSession,Integer SesionIdSql) {
        if (mapPMF.get(publickey) != null) {
            mapPMF.get(publickey).close();
            mapPMF.remove(publickey);
            publickey=publickey+"@"+idSession+"@"+SesionIdSql;
            mapSesion.remove(publickey);
        }

    }
    
    public HashMap<String, PersistenceManagerFactory> getMapPMF(){
        return mapPMF;
    }

    public HashMap<String, DataSesion> getMapSesion() {
        return mapSesion;
    }
    
    

}
