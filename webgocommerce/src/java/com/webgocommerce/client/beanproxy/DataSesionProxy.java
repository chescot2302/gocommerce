/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.DataSesion;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorDataSesion;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=DataSesion.class,locator=LocatorDataSesion.class)
public interface DataSesionProxy extends EntityProxy{
    public String getEstado();

    public void setEstado(String estado);
    
    public Long getTimeSession();

    public void setTimeSession(Long timeSession);
    
    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
        
    public Integer getSessionIdSQL();

    public void setSessionIdSQL(Integer sessionIdSQL);
        
    public String getLoginName();

    public void setLoginName(String loginName);
    
    public String getHostName();

    public void setHostName(String hostName);
        
    public String getDbName();

    public void setDbName(String dbName);
        
    public String getProgramName();

    public void setProgramName(String programName);
        
    public Date getLoginTime();

    public void setLoginTime(Date loginTime);
        
    public Integer getHostProcessId();

    public void setHostProcessId(Integer hostProcessId);
        
    public String getSecurityId();

    public void setSecurityId(String securityId);
        
    public Integer getBdId();

    public void setBdId(Integer bdId);
        
    public Integer getIdUsuarioBdGO();

    public void setIdUsuarioBdGO(Integer idUsuarioBdGO);
        
    public Integer getIdDataSesion();

    public void setIdDataSesion(Integer idDataSesion);
        
    public String getNivel();

    public void setNivel(String nivel);
        
    public String getTokenGo();

    public void setTokenGo(String tokenGo);
        
    public String getMyToken();

    public void setMyToken(String myToken);
        
    public String getIdSession();

    public void setIdSession(String idSession);
        
    public String getUsuario();

    public void setUsuario(String usuario);
            
    public String getUsuariobd();
    
    public void setUsuariobd(String usuariobd);
        
    public String getSchemabd();

    public void setSchemabd(String schemabd);
        
    public String getRemoteAddr();

    public void setRemoteAddr(String remoteAddr);
        
    public String getRemoteHost();

    public void setRemoteHost(String remoteHost);
        
    public Integer getRemotePort();

    public void setRemotePort(Integer remotePort);
        
    public String getLocalName();

    public void setLocalName(String localName);
        
    public String getLocalAddr();

    public void setLocalAddr(String localAddr);
        
    public Integer getLocalPort();

    public void setLocalPort(Integer localPort);
        
    public String getAccept();

    public void setAccept(String accept);
        
    public String getConnection();

    public void setConnection(String connection);
        
    public String getReferer();

    public void setReferer(String referer);
        
    public String getPragma();

    public void setPragma(String pragma);
        
    public String getAcceptEncoding();

    public void setAcceptEncoding(String acceptEncoding);
        
    public String getCacheControl();

    public void setCacheControl(String cacheControl);
        
    public String getxGwtModuleBase();

    public void setxGwtModuleBase(String xGwtModuleBase);
        
    public String getUserAgent();

    public void setUserAgent(String userAgent);
        
    public String getContentType();

    public void setContentType(String contentType);
        
    public String getAcceptLanguage();

    public void setAcceptLanguage(String acceptLanguage);
        
    public String getContentLength();

    public void setContentLength(String contentLength);
        
    public String getCookie();

    public void setCookie(String cookie);
        
    public String getHost();

    public void setHost(String host);
        
    public Date getCreationTimeSesion();

    public void setCreationTimeSesion(Date CreationTimeSesion);
        
    public Date getLastTimeSesion();

    public void setLastTimeSesion(Date LastTimeSesion);
        
    public Date getFechaIni();

    public void setFechaIni(Date fechaIni);
        
    public Date getFechaFin();

    public void setFechaFin(Date fechaFin);
        
    public Integer getIdUsuarioBd();

    public void setIdUsuarioBd(Integer idUsuarioBd);

    public DataSesionProxy getBeanGO();

    public void setBeanGO(DataSesionProxy beanGO);
}
