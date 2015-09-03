/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@XmlRootElement(name = "datasesion")
public class DataSesion implements Serializable{
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
    private DataSesion beanGO;
    private Long version;
    private String operacion;
    private Long timeSession;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    @XmlElement(name="TIMESESSION")
    public Long getTimeSession() {
        return timeSession;
    }

    public void setTimeSession(Long timeSession) {
        this.timeSession = timeSession;
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
    
    @XmlElement(name="SESIONIDSQL")
    public Integer getSessionIdSQL() {
        return sessionIdSQL;
    }

    public void setSessionIdSQL(Integer sessionIdSQL) {
        this.sessionIdSQL = sessionIdSQL;
    }
    
    @XmlElement(name="LOGINNAME")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @XmlElement(name="HOSTNAME")
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    
    @XmlElement(name="DBNAME")
    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    
    @XmlElement(name="PROGRAMNAME")
    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
    
    @XmlElement(name="LOGINTIME")
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    
    @XmlElement(name="HOSTPROCESSID")
    public Integer getHostProcessId() {
        return hostProcessId;
    }

    public void setHostProcessId(Integer hostProcessId) {
        this.hostProcessId = hostProcessId;
    }
    
    @XmlElement(name="SECURITYID")
    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }
    
    @XmlElement(name="BDID")
    public Integer getBdId() {
        return bdId;
    }

    public void setBdId(Integer bdId) {
        this.bdId = bdId;
    }        
    
    @XmlElement(name="IDBDUSUARIOGO")
    public Integer getIdUsuarioBdGO() {
        return idUsuarioBdGO;
    }

    public void setIdUsuarioBdGO(Integer idUsuarioBdGO) {
        this.idUsuarioBdGO = idUsuarioBdGO;
    }      
    
    @XmlElement(name="IDDATASESION")
    public Integer getIdDataSesion() {
        return idDataSesion;
    }

    public void setIdDataSesion(Integer idDataSesion) {
        this.idDataSesion = idDataSesion;
    }
    
    @XmlElement(name="NIVEL")
    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    @XmlElement(name="TOKENGO")
    public String getTokenGo() {
        return tokenGo;
    }

    public void setTokenGo(String tokenGo) {
        this.tokenGo = tokenGo;
    }
    
    @XmlElement(name="MYTOKEN")
    public String getMyToken() {
        return myToken;
    }

    public void setMyToken(String myToken) {
        this.myToken = myToken;
    }
    
    @XmlElement(name="IDSESSION")
    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }
    
    @XmlElement(name="USUARIO")
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
        
    @XmlElement(name="USUARIOBD")
    public String getUsuariobd() {
        return usuariobd;
    }
    
    public void setUsuariobd(String usuariobd) {
        this.usuariobd = usuariobd;
    }
    
    @XmlElement(name="SCHEMABD")
    public String getSchemabd() {
        return schemabd;
    }

    public void setSchemabd(String schemabd) {
        this.schemabd = schemabd;
    }
    
    @XmlElement(name="REMOTEADDR")
    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
    
    @XmlElement(name="REMOTEHOST")
    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }
    
    @XmlElement(name="REMOTEPORT")
    public Integer getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(Integer remotePort) {
        this.remotePort = remotePort;
    }
    
    @XmlElement(name="LOCALNAME")
    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }
    
    @XmlElement(name="LOCALADDR")
    public String getLocalAddr() {
        return localAddr;
    }

    public void setLocalAddr(String localAddr) {
        this.localAddr = localAddr;
    }
    
    @XmlElement(name="LOCALPORT")
    public Integer getLocalPort() {
        return localPort;
    }

    public void setLocalPort(Integer localPort) {
        this.localPort = localPort;
    }
    
    @XmlElement(name="ACCEPT")
    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
    
    @XmlElement(name="CONNECTION")
    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }
    
    @XmlElement(name="REFERER")
    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }
    
    @XmlElement(name="PRAGMA")
    public String getPragma() {
        return pragma;
    }

    public void setPragma(String pragma) {
        this.pragma = pragma;
    }
    
    @XmlElement(name="ACCEPTENCODING")
    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }
    
    @XmlElement(name="CACHECONTROL")
    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }
    
    @XmlElement(name="XGWTMODULEBASE")
    public String getxGwtModuleBase() {
        return xGwtModuleBase;
    }

    public void setxGwtModuleBase(String xGwtModuleBase) {
        this.xGwtModuleBase = xGwtModuleBase;
    }
    
    @XmlElement(name="USERAGENT")
    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    
    @XmlElement(name="CONTENTTYPE")
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    @XmlElement(name="ACCEPTLANGUAGE")
    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }
    
    @XmlElement(name="CONTENTLENGTH")
    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }
    
    @XmlElement(name="COOKIE")
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
    
    @XmlElement(name="HOST")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
    
    @XmlElement(name="CREATIONSESION")
    public Date getCreationTimeSesion() {
        return CreationTimeSesion;
    }

    public void setCreationTimeSesion(Date CreationTimeSesion) {
        this.CreationTimeSesion = CreationTimeSesion;
    }
    
    @XmlElement(name="LASTSESION")
    public Date getLastTimeSesion() {
        return LastTimeSesion;
    }

    public void setLastTimeSesion(Date LastTimeSesion) {
        this.LastTimeSesion = LastTimeSesion;
    }
    
    @XmlElement(name="FECHAINI")
    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }
    
    @XmlElement(name="FECHAFIN")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    @XmlElement(name="IDBDUSURIO")
    public Integer getIdUsuarioBd() {
        return idUsuarioBd;
    }

    public void setIdUsuarioBd(Integer idUsuarioBd) {
        this.idUsuarioBd = idUsuarioBd;
    }

    public DataSesion getBeanGO() {
        return beanGO;
    }

    public void setBeanGO(DataSesion beanGO) {
        this.beanGO = beanGO;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.idDataSesion);
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
        final DataSesion other = (DataSesion) obj;
        if (!Objects.equals(this.idDataSesion, other.idDataSesion)) {
            return false;
        }
        return true;
    }
    
    
}
