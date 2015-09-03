package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(table = "GOACTIVABDUSUARIO",detachable="true")
public class ActivaBdUsuario implements Serializable{
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @PrimaryKey
    @Column(name = "IDACTIVABDUSUARIO")
    private Integer idActivaBdUsuario;
    @Persistent
    @Column(name = "FECHAINI")
    private Date fechaIni;
    @Persistent
    @Column(name = "FECHAFIN")
    private Date fechaFin;
    @Persistent
    @Column(name = "ESTADOACTIVACION")
    private String estadoActivacion;
    @Persistent
    @Column(name = "IDBDUSUARIO")
    private BdUsuario beanBdUsuario;
    @Persistent
    @Column(name = "VERSION")
    private Long version;
    @NotPersistent
    private String operacion;

    public Integer getIdActivaBdUsuario() {
        return idActivaBdUsuario;
    }

    public void setIdActivaBdUsuario(Integer idActivaBdUsuario) {
        this.idActivaBdUsuario = idActivaBdUsuario;
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

    public String getEstadoActivacion() {
        return estadoActivacion;
    }

    public void setEstadoActivacion(String estadoActivacion) {
        this.estadoActivacion = estadoActivacion;
    }

    public BdUsuario getBeanBdUsuario() {
        return beanBdUsuario;
    }

    public void setBeanBdUsuario(BdUsuario beanBdUsuario) {
        this.beanBdUsuario = beanBdUsuario;
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
        hash = 53 * hash + Objects.hashCode(this.idActivaBdUsuario);
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
        final ActivaBdUsuario other = (ActivaBdUsuario) obj;
        if (!Objects.equals(this.idActivaBdUsuario, other.idActivaBdUsuario)) {
            return false;
        }
        return true;
    }

    

}
