/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.dao;

import java.util.Properties;

/**
 *
 * @author SISTEMAS
 */
public class Conexion {
    
    public Properties cnxBdMysql(String usuario,String clave,String ip,Integer puerto,String bd){
        Properties props=new Properties();
        props.setProperty("datanucleus.ConnectionDriverName", "com.mysql.jdbc.Driver");
        props.setProperty("datanucleus.ConnectionURL", "jdbc:mysql://"+ip+":"+puerto+"/"+bd);
        props.setProperty("datanucleus.ConnectionUserName", usuario);
        props.setProperty("datanucleus.ConnectionPassword", clave);
        props.setProperty("datanucleus.connection.nontx.releaseAfterUse", "false");
        props.setProperty("datanucleus.schema.autoCreateTables","true");
        return props;
    }
    
    public Properties cnxBdSQLServer(String usuario,String clave,String ip,Integer puerto,String bd){
        Properties props=new Properties();
        props.setProperty("datanucleus.ConnectionDriverName", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        props.setProperty("datanucleus.ConnectionURL", "jdbc:sqlserver://" + ip + ":" + puerto + ";DatabaseName=" + bd);
        props.setProperty("datanucleus.ConnectionUserName", usuario);
        props.setProperty("datanucleus.ConnectionPassword", clave);
        props.setProperty("datanucleus.connection.nontx.releaseAfterUse", "false");
        props.setProperty("datanucleus.schema.autoCreateTables","true");
        props.setProperty("datanucleus.NontransactionalRead","true");
        props.setProperty("datanucleus.NontransactionalWrite","true");
        props.setProperty("datanucleus.RetainValues","true");
        return props;
    }
}
