/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.util.Objects;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * @author SISTEMAS
 */
@PersistenceCapable(table = "tbl01doc",detachable="true")
public class Documento implements Serializable{
    @Persistent
    @PrimaryKey
    @Column(name = "cdocu")
    private String id;
    @Persistent
    @Column(name = "nomdoc")
    private String nombre;
    @Persistent
    @Column(name = "Regcom")
    private Integer regCom;
    @Persistent
    @Column(name = "coddocsnt")
    private String codDocSnt;
    @NotPersistent
    private Long version;
    @NotPersistent
    private String operacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getRegCom() {
        return regCom;
    }

    public void setRegCom(Integer regCom) {
        this.regCom = regCom;
    }

    public String getCodDocSnt() {
        return codDocSnt;
    }

    public void setCodDocSnt(String codDocSnt) {
        this.codDocSnt = codDocSnt;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Documento other = (Documento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
