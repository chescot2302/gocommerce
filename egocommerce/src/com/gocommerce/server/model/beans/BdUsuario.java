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

@PersistenceCapable(table = "GOBDUSUARIO",detachable="true")
public class BdUsuario  implements Serializable{

    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @PrimaryKey
    @Column(name = "IDBDUSUARIO")
    private Integer idBdUsuario;
    @Persistent
    @Column(name = "USUARIOBD")
    private String usuarioBd;
    @Persistent
    @Column(name = "CLAVEBD")
    private String claveBd;
    @Persistent
    @Column(name = "ESQUEMA")
    private String schema;
    @Persistent
    @Column(name = "CORREO")
    private String correo;
    @Persistent
    @Column(name = "CLAVE")
    private String clave;
    @Persistent
    @Column(name = "NIVEL")
    private String nivel;
    @Persistent
    @Column(name = "IDBDEMPRESA")
    private BdEmpresa beanBdEmpresa;
    @Persistent
    @Column(name = "ESTADOACTIVACION")
    private String estadoActivacion;
    @Persistent
    @Column(name = "VERSION")
    private Long version;
    @NotPersistent
    private String operacion;
    @Persistent(mappedBy="beanBdUsuario")
    private List<ActivaBdUsuario> listActivaBdUsuario=new ArrayList();
    @NotPersistent
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

    public BdEmpresa getBeanBdEmpresa() {
        return beanBdEmpresa;
    }

    public void setBeanBdEmpresa(BdEmpresa beanBdEmpresa) {
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

    public List<ActivaBdUsuario> getListActivaBdUsuario() {
        return listActivaBdUsuario;
    }

    public void setListActivaBdUsuario(List<ActivaBdUsuario> listActivaBdUsuario) {
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
        final BdUsuario other = (BdUsuario) obj;
        if (!Objects.equals(this.idBdUsuario, other.idBdUsuario)) {
            return false;
        }
        return true;
    }

   

}
