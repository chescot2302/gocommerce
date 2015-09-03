/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author SISTEMAS
 */
public class BdEmpresaRPC implements Serializable{
    private Integer idBdEmpresa;
    private String nombre;    
    private String schema;
    private String userPrincipal;
    private String clavePrincipal;
    private String estadoActivacion;
    private String IpHost;
    private Integer puerto;
    private Long version;
    private String operacion;
    private List<BdUsuarioRPC> listBdUsuario=new ArrayList();

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

    public List<BdUsuarioRPC> getListBdUsuario() {
        return listBdUsuario;
    }

    public void setListBdUsuario(List<BdUsuarioRPC> listBdUsuario) {
        this.listBdUsuario = listBdUsuario;
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
        final BdEmpresaRPC other = (BdEmpresaRPC) obj;
        if (!Objects.equals(this.idBdEmpresa, other.idBdEmpresa)) {
            return false;
        }
        return true;
    }
    
   
    
}
