/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
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
@PersistenceCapable(table = "mst01cli",detachable="true")
public class Cliente implements Serializable{
    @Persistent
    @PrimaryKey
    @Column(name = "codcli")
    private String id;
    @Persistent
    @Column(name = "nomcli")
    private String nombres;
    @Persistent
    @Column(name = "dircli")
    private String direccion;
    @Persistent
    @Column(name = "dirent")
    private String direccionEntrega;
    @Persistent
    @Column(name = "ruccli")
    private String ruc;
    @Persistent
    @Column(name = "nrodni")
    private String dni;
    @Persistent
    @Column(name = "flaper")
    private Integer tipoPersona;
    @Persistent
    @Column(name = "ctlper")
    private Integer percepcion;
    @Persistent
    @Column(name = "catsnt")
    private Integer agentePerceptor;    
    @Persistent
    @Column(name = "estado")
    private Integer estado;
    @Persistent
    @Column(name = "flalin")
    private Integer vigenciaLineaCredito;
    @Persistent
    @Column(name = "codmonlinea")
    private String monedaLineaCredito;
    @Persistent
    @Column(name = "mcredi")
    private BigDecimal montoCredito;
    @Persistent
    @Column(name = "mampcre")
    private BigDecimal ampliaCredito;
    @Persistent
    @Column(name = "codcdv")
    private String idCondicionVenta;
    @Persistent
    @Column(name = "dsctxv")
    private BigDecimal porcDsctoContado;
    @Persistent
    @Column(name = "dsctcr")
    private BigDecimal porcDsctoCredito;
    @NotPersistent
    private Long version;
    @NotPersistent
    private String operacion;
    @NotPersistent
    private String tipoDocIden;
    @NotPersistent
    private String idPais;
    @NotPersistent
    private String idDepartamento;
    @NotPersistent
    private String idProvincia;
    @NotPersistent
    private String idDistrito;
    @NotPersistent
    private String telefono;
    @NotPersistent
    private String email;
    @NotPersistent
    private String documento;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }    

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
       
    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }        

    public String getTipoDocIden() {
        return tipoDocIden;
    }

    public void setTipoDocIden(String tipoDocIden) {
        this.tipoDocIden = tipoDocIden;
    }        

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }       
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(Integer tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Integer getPercepcion() {
        return percepcion;
    }

    public void setPercepcion(Integer percepcion) {
        this.percepcion = percepcion;
    }

    public Integer getAgentePerceptor() {
        return agentePerceptor;
    }

    public void setAgentePerceptor(Integer agentePerceptor) {
        this.agentePerceptor = agentePerceptor;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getVigenciaLineaCredito() {
        return vigenciaLineaCredito;
    }

    public void setVigenciaLineaCredito(Integer vigenciaLineaCredito) {
        this.vigenciaLineaCredito = vigenciaLineaCredito;
    }

    public String getMonedaLineaCredito() {
        return monedaLineaCredito;
    }

    public void setMonedaLineaCredito(String monedaLineaCredito) {
        this.monedaLineaCredito = monedaLineaCredito;
    }

    public BigDecimal getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(BigDecimal montoCredito) {
        this.montoCredito = montoCredito;
    }

    public BigDecimal getAmpliaCredito() {
        return ampliaCredito;
    }

    public void setAmpliaCredito(BigDecimal ampliaCredito) {
        this.ampliaCredito = ampliaCredito;
    }

    public String getIdCondicionVenta() {
        return idCondicionVenta;
    }

    public void setIdCondicionVenta(String idCondicionVenta) {
        this.idCondicionVenta = idCondicionVenta;
    }

    public BigDecimal getPorcDsctoContado() {
        return porcDsctoContado;
    }

    public void setPorcDsctoContado(BigDecimal porcDsctoContado) {
        this.porcDsctoContado = porcDsctoContado;
    }

    public BigDecimal getPorcDsctoCredito() {
        return porcDsctoCredito;
    }

    public void setPorcDsctoCredito(BigDecimal porcDsctoCredito) {
        this.porcDsctoCredito = porcDsctoCredito;
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

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
