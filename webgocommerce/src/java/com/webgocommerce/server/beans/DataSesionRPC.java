/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author SISTEMAS
 */
public class DataSesionRPC implements Serializable{
    private Integer idDataSesion;
    private String nivel; 
    private String tokenGo;
    private String myToken;    
    private String idSession;
    private String usuario;
    private String usuariobd;
    private String schemabd;   
    private String remoteAddr;
    private String remoteHost;
    private Integer remotePort;
    private String localName;
    private String localAddr;
    private Integer localPort;    
    private String accept;
    private String connection;
    private String referer;
    private String pragma;
    private String acceptEncoding;
    private String cacheControl;
    private String xGwtModuleBase;
    private String userAgent;
    private String contentType;
    private String acceptLanguage;
    private String contentLength;
    private String cookie;
    private String host;
    private Date CreationTimeSesion;
    private Date LastTimeSesion;
    private Date fechaIni;
    private Date fechaFin;
    private Integer idUsuarioBd;
    private Integer idUsuarioBdGO;
    private Integer sessionIdSQL;
    private String loginName;
    private String hostName;
    private String dbName;
    private String programName;
    private Date loginTime;
    private Integer hostProcessId;
    private String securityId;
    private Integer bdId;
    private DataSesionRPC beanGO;
    private Long version;
    private String operacion;
    private Long timeSession;
    private String estado;

    public Integer getIdDataSesion() {
        return idDataSesion;
    }

    public void setIdDataSesion(Integer idDataSesion) {
        this.idDataSesion = idDataSesion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTokenGo() {
        return tokenGo;
    }

    public void setTokenGo(String tokenGo) {
        this.tokenGo = tokenGo;
    }

    public String getMyToken() {
        return myToken;
    }

    public void setMyToken(String myToken) {
        this.myToken = myToken;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuariobd() {
        return usuariobd;
    }

    public void setUsuariobd(String usuariobd) {
        this.usuariobd = usuariobd;
    }

    public String getSchemabd() {
        return schemabd;
    }

    public void setSchemabd(String schemabd) {
        this.schemabd = schemabd;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public Integer getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(Integer remotePort) {
        this.remotePort = remotePort;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getLocalAddr() {
        return localAddr;
    }

    public void setLocalAddr(String localAddr) {
        this.localAddr = localAddr;
    }

    public Integer getLocalPort() {
        return localPort;
    }

    public void setLocalPort(Integer localPort) {
        this.localPort = localPort;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getPragma() {
        return pragma;
    }

    public void setPragma(String pragma) {
        this.pragma = pragma;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getxGwtModuleBase() {
        return xGwtModuleBase;
    }

    public void setxGwtModuleBase(String xGwtModuleBase) {
        this.xGwtModuleBase = xGwtModuleBase;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getCreationTimeSesion() {
        return CreationTimeSesion;
    }

    public void setCreationTimeSesion(Date CreationTimeSesion) {
        this.CreationTimeSesion = CreationTimeSesion;
    }

    public Date getLastTimeSesion() {
        return LastTimeSesion;
    }

    public void setLastTimeSesion(Date LastTimeSesion) {
        this.LastTimeSesion = LastTimeSesion;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getIdUsuarioBd() {
        return idUsuarioBd;
    }

    public void setIdUsuarioBd(Integer idUsuarioBd) {
        this.idUsuarioBd = idUsuarioBd;
    }

    public Integer getIdUsuarioBdGO() {
        return idUsuarioBdGO;
    }

    public void setIdUsuarioBdGO(Integer idUsuarioBdGO) {
        this.idUsuarioBdGO = idUsuarioBdGO;
    }

    public Integer getSessionIdSQL() {
        return sessionIdSQL;
    }

    public void setSessionIdSQL(Integer sessionIdSQL) {
        this.sessionIdSQL = sessionIdSQL;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getHostProcessId() {
        return hostProcessId;
    }

    public void setHostProcessId(Integer hostProcessId) {
        this.hostProcessId = hostProcessId;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public Integer getBdId() {
        return bdId;
    }

    public void setBdId(Integer bdId) {
        this.bdId = bdId;
    }

    public DataSesionRPC getBeanGO() {
        return beanGO;
    }

    public void setBeanGO(DataSesionRPC beanGO) {
        this.beanGO = beanGO;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Long getTimeSession() {
        return timeSession;
    }

    public void setTimeSession(Long timeSession) {
        this.timeSession = timeSession;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idDataSesion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataSesionRPC other = (DataSesionRPC) obj;
        if (!Objects.equals(this.idDataSesion, other.idDataSesion)) {
            return false;
        }
        return true;
    }
    
    
}
