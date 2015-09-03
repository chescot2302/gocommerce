/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * @author SISTEMAS
 */
@PersistenceCapable(table = "TBLVENDEDOR",detachable="true")
public class Vendedor implements Serializable{
    @PrimaryKey
    @Persistent
    private String idVendedor;
    @Persistent
    private String dni;
    @Persistent
    private String nomVendedor;
    @Persistent
    private String primerNombre;
    @Persistent
    private String segundoNombre;
    @Persistent
    private String apellidoPaterno;
    @Persistent
    private String apellidoMaterno;
    @Persistent
    private String idPtoEmision;
    @Persistent
    private Integer idMesa;
    @NotPersistent
    private String nomMesa;
    @NotPersistent
    private Long version;
    @NotPersistent
    private String operacion;
    @NotPersistent
    private String nomPtoEmision;
    @NotPersistent
    private String idTienda;
    @NotPersistent
    private String nomTienda;
    @NotPersistent
    private String idSucursal;
    @NotPersistent
    private String nomSucursal;
    @NotPersistent
    private String idLocalidad;
    @NotPersistent
    private String nomLocalidad;
    @Persistent
    private Integer estado;
    @Persistent
    private String codigoAlterno;
    @Persistent
    private String canal;
    @Persistent
    private String correo;
    @Persistent
    private String celular;
    @Persistent
    private Date fechaInc;
    @Persistent
    private Date fechaCese;
    @NotPersistent
    private String nomConsultor;

    public String getNomConsultor() {
        return nomConsultor;
    }

    public void setNomConsultor(String nomConsultor) {
        this.nomConsultor = nomConsultor;
    }
    
    

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getFechaInc() {
        return fechaInc;
    }

    public void setFechaInc(Date fechaInc) {
        this.fechaInc = fechaInc;
    }

    public Date getFechaCese() {
        return fechaCese;
    }

    public void setFechaCese(Date fechaCese) {
        this.fechaCese = fechaCese;
    }        

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }        

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public String getNomMesa() {
        return nomMesa;
    }

    public void setNomMesa(String nomMesa) {
        this.nomMesa = nomMesa;
    }        

    public String getCodigoAlterno() {
        return codigoAlterno;
    }

    public void setCodigoAlterno(String codigoAlterno) {
        this.codigoAlterno = codigoAlterno;
    }       

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
           
    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNomVendedor() {
        return nomVendedor;
    }

    public void setNomVendedor(String nomVendedor) {
        this.nomVendedor = nomVendedor;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getIdPtoEmision() {
        return idPtoEmision;
    }

    public void setIdPtoEmision(String idPtoEmision) {
        this.idPtoEmision = idPtoEmision;
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

    public String getNomPtoEmision() {
        return nomPtoEmision;
    }

    public void setNomPtoEmision(String nomPtoEmision) {
        this.nomPtoEmision = nomPtoEmision;
    }

    public String getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(String idTienda) {
        this.idTienda = idTienda;
    }

    public String getNomTienda() {
        return nomTienda;
    }

    public void setNomTienda(String nomTienda) {
        this.nomTienda = nomTienda;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public void setNomSucursal(String nomSucursal) {
        this.nomSucursal = nomSucursal;
    }

    public String getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(String idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getNomLocalidad() {
        return nomLocalidad;
    }

    public void setNomLocalidad(String nomLocalidad) {
        this.nomLocalidad = nomLocalidad;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idVendedor);
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
        final Vendedor other = (Vendedor) obj;
        if (!Objects.equals(this.idVendedor, other.idVendedor)) {
            return false;
        }
        return true;
    }
    
    
    
}
