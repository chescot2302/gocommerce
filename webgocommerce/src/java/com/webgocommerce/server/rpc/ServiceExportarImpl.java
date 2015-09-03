/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.server.rpc;

import com.gocommerce.server.model.beans.DataSesion;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.util.StringHex;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webgocommerce.client.service.ServiceExportar;
import com.webgocommerce.server.servlet.HttpSessionCollector;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SISTEMAS
 */
public class ServiceExportarImpl extends RemoteServiceServlet implements ServiceExportar {

    @Override
    public void exportar(String keyPublic) {                
        String token = StringHex.convertHexToString(keyPublic);
        DataSesion bean=PMF.getClassPMF().getSesion(token);        
        System.out.println(bean.getAccept());
        System.out.println(bean.getAcceptEncoding());
        System.out.println(bean.getAcceptLanguage());
        System.out.println(bean.getBdId());
        System.out.println(bean.getBeanGO());
        System.out.println(bean.getCacheControl());
        System.out.println(bean.getConnection());
        System.out.println(bean.getContentLength());
        System.out.println(bean.getContentType());
        System.out.println(bean.getCookie());
        System.out.println(bean.getCreationTimeSesion());
        System.out.println(bean.getDbName());
        System.out.println(bean.getFechaIni());
        System.out.println(bean.getHost());
        System.out.println(bean.getHostName());
        System.out.println(bean.getHostProcessId());
        System.out.println(bean.getIdDataSesion());
        System.out.println(bean.getIdSession());
        System.out.println(bean.getIdUsuarioBd());
        System.out.println(bean.getLocalAddr());
        System.out.println(bean.getLocalName());
        System.out.println(bean.getLocalPort());
        System.out.println(bean.getLoginName());
        System.out.println(bean.getLoginTime());
        System.out.println(bean.getMyToken());
        System.out.println(bean.getNivel());
        System.out.println(bean.getPragma());
        System.out.println(bean.getProgramName());
        System.out.println(bean.getReferer());
        System.out.println(bean.getRemoteAddr());
        System.out.println(bean.getRemoteHost());
        System.out.println(bean.getRemotePort());
        System.out.println(bean.getSchemabd());
        System.out.println(bean.getSessionIdSQL());
        System.out.println(bean.getUserAgent());
        System.out.println(bean.getUsuario());
        System.out.println(bean.getUsuariobd());
        System.out.println(bean.getxGwtModuleBase());
    }

}
