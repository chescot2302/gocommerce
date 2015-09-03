/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.validation.constraints.NotNull;

/**
 *
 * @author SISTEMAS
 */
@PersistenceCapable(table="GOBDEMPRESA",detachable="true")
public class BdEmpresa implements Serializable{
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @PrimaryKey 
    @Column(name="IDBDEMPRESA")
    private Integer idBdEmpresa;
    @Persistent
    @Column(name="NOMBRE")
    private String nombre;    
    @Persistent
    @Column(name="ESQUEMA")
    private String schema;
    @Persistent
    @Column(name="USERPRINCIPAL")
    private String userPrincipal;
    @Persistent
    @Column(name="CLAVEPRINCIPAL")
    private String clavePrincipal;
    @Persistent
    @Column(name="ESTADOACTIVACION")
    private String estadoActivacion;
    @Persistent
    @Column(name="IPHOST")
    private String IpHost;
    @Persistent
    @Column(name="PUERTO")
    private Integer puerto;
    @Persistent    
    @Column(name="VERSION")    
    @NotNull
    private Long version;
    @NotPersistent
    private String operacion;
    @Persistent(mappedBy="beanBdEmpresa")
    private List<BdUsuario> listBdUsuario=new ArrayList();

    public Integer getIdBdEmpresa() {
        return idBdEmpresa;
    }

    public void setIdBdEmpresa(Integer idBdEmpresa) {
        this.idBdEmpresa = idBdEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }  

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUserPrincipal() {
        return userPrincipal;
    }

    public void setUserPrincipal(String userPrincipal) {
        this.userPrincipal = userPrincipal;
    }

    public String getClavePrincipal() {
        return clavePrincipal;
    }

    public void setClavePrincipal(String clavePrincipal) {
        this.clavePrincipal = clavePrincipal;
    }

    public String getEstadoActivacion() {
        return estadoActivacion;
    }

    public void setEstadoActivacion(String estadoActivacion) {
        this.estadoActivacion = estadoActivacion;
    }

    public String getIpHost() {
        return IpHost;
    }

    public void setIpHost(String IpHost) {
        this.IpHost = IpHost;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
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

    public List<BdUsuario> getListBdUsuario() {
        return listBdUsuario;
    }

    public void setListBdUsuario(List<BdUsuario> listBdUsuario) {
        this.listBdUsuario = listBdUsuario;
    }
    
    public String getClassName(){
        return this.getClass().getSimpleName();
    }    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.idBdEmpresa);
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
        final BdEmpresa other = (BdEmpresa) obj;
        if (!Objects.equals(this.idBdEmpresa, other.idBdEmpresa)) {
            return false;
        }
        return true;
    }
    
   
    
}
