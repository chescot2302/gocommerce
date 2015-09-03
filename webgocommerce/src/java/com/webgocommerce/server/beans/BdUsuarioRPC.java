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
public class BdUsuarioRPC implements Serializable{
    private Integer idBdUsuario;
    private String usuarioBd;
    private String claveBd;
    private String schema;
    private String correo;
    private String clave;
    private String nivel;
    private BdEmpresaRPC beanBdEmpresa;
    private String estadoActivacion;
    private Long version;
    private String operacion;
    private List<ActivaBdUsuarioRPC> listActivaBdUsuario=new ArrayList();
    private Integer idBdEmpresa;

    public Integer getIdBdUsuario() {
        return idBdUsuario;
    }

    public void setIdBdUsuario(Integer idBdUsuario) {
        this.idBdUsuario = idBdUsuario;
    }

    public String getUsuarioBd() {
        return usuarioBd;
    }

    public void setUsuarioBd(String usuarioBd) {
        this.usuarioBd = usuarioBd;
    }

    public String getClaveBd() {
        return claveBd;
    }

    public void setClaveBd(String claveBd) {
        this.claveBd = claveBd;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public BdEmpresaRPC getBeanBdEmpresa() {
        return beanBdEmpresa;
    }

    public void setBeanBdEmpresa(BdEmpresaRPC beanBdEmpresa) {
        this.beanBdEmpresa = beanBdEmpresa;
    }

    public String getEstadoActivacion() {
        return estadoActivacion;
    }

    public void setEstadoActivacion(String estadoActivacion) {
        this.estadoActivacion = estadoActivacion;
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

    public List<ActivaBdUsuarioRPC> getListActivaBdUsuario() {
        return listActivaBdUsuario;
    }

    public void setListActivaBdUsuario(List<ActivaBdUsuarioRPC> listActivaBdUsuario) {
        this.listActivaBdUsuario = listActivaBdUsuario;
    }

    public Integer getIdBdEmpresa() {
        return idBdEmpresa;
    }

    public void setIdBdEmpresa(Integer idBdEmpresa) {
        this.idBdEmpresa = idBdEmpresa;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.idBdUsuario);
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
        final BdUsuarioRPC other = (BdUsuarioRPC) obj;
        if (!Objects.equals(this.idBdUsuario, other.idBdUsuario)) {
            return false;
        }
        return true;
    }

   

}
