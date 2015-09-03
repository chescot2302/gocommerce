/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@XmlRootElement(name = "cabeceraventa")
public class CabeceraVenta implements Serializable{
    private String idCabecera;
    private Date fechaEmision;
    private String tipoDoc;
    private String correlativo;
    private String tipoFacturacion;
    private String idCliente;
    private String nombreCliente;
    private String rucCliente;
    private String direccionCliente;
    private String idCondicionVenta;
    private String idTipoVenta;
    private Integer diasCredito;
    private Date fechaVencimiento;
    private String idMoneda;
    private BigDecimal tipoCambio;
    private BigDecimal totalAfecto;
    private BigDecimal totalNoAfecto;
    private BigDecimal totalPercepcion;
    private BigDecimal totalIgv;
    private BigDecimal totalNeto;
    private BigDecimal totalPlan;
    private BigDecimal totalEquipos;
    private String idVendedor;
    private String idAlmacen;
    private String idPtoEmision;
    private String flag="0";
    private String idTransportista;
    private String idTipoDocRef;
    private String correlativoRef;
    private String compro;
    private String docRefe="N";    
    private String dirPar;
    private String idSucursal;
    private Integer frontera;    
    private BigDecimal tcMercado;
    private String afectoPercepcion;
    private Long version;
    private String ctaIgv;
    private String ctaCliente;
    private String placaVehiculo="";
    private String numeroLicencia="";
    private String nomChofer="";
    private String marcaVehiculo="";
    private String codSubCentroCosto="00";
    private String nroci="";
    private String nomDoc;
    private String serie;
    private String preimpreso;
    private String nomCondicionVenta;
    private String tipoVenta;
    private String nomVendedor;
    private String puntoEmsion;
    private String dniVendedor;
    private String usuarioReg;
    
    private Integer idSuperVen;    
    private Integer idMesa;
    private String nomMesa;
    private Integer idCoordinador;
    private String dniCoordinador;
    private String nomCoordinador;
    private Integer idGerenteZonal;
    private String dniGerenteZonal;
    private String nomGerenteZonal;
    private Integer idSupervisor;
    private String dniSupervisor;
    private String nomSupervisor;
    private String estadoActual;
    private String codProy="";
    private String observacion;

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
    
    @XmlElement(name="TOTALEQUIPO")
    public BigDecimal getTotalEquipos() {
        return totalEquipos;
    }

    public void setTotalEquipos(BigDecimal totalEquipos) {
        this.totalEquipos = totalEquipos;
    }
    
    

    public String getNomMesa() {
        return nomMesa;
    }

    public void setNomMesa(String nomMesa) {
        this.nomMesa = nomMesa;
    }

    public String getDniCoordinador() {
        return dniCoordinador;
    }

    public void setDniCoordinador(String dniCoordinador) {
        this.dniCoordinador = dniCoordinador;
    }

    public String getNomCoordinador() {
        return nomCoordinador;
    }

    public void setNomCoordinador(String nomCoordinador) {
        this.nomCoordinador = nomCoordinador;
    }

    public String getDniGerenteZonal() {
        return dniGerenteZonal;
    }

    public void setDniGerenteZonal(String dniGerenteZonal) {
        this.dniGerenteZonal = dniGerenteZonal;
    }

    public String getNomGerenteZonal() {
        return nomGerenteZonal;
    }

    public void setNomGerenteZonal(String nomGerenteZonal) {
        this.nomGerenteZonal = nomGerenteZonal;
    }

    public String getDniSupervisor() {
        return dniSupervisor;
    }

    public void setDniSupervisor(String dniSupervisor) {
        this.dniSupervisor = dniSupervisor;
    }

    public String getNomSupervisor() {
        return nomSupervisor;
    }

    public void setNomSupervisor(String nomSupervisor) {
        this.nomSupervisor = nomSupervisor;
    }
    
    
    
    @XmlElement(name="TOTALPLAN")
    public BigDecimal getTotalPlan() {
        return totalPlan;
    }

    public void setTotalPlan(BigDecimal totalPlan) {
        this.totalPlan = totalPlan;
    }
    
    
    
    @XmlElement(name="CODPROY")
    public String getCodProy() {
        return codProy;
    }

    public void setCodProy(String codProy) {
        this.codProy = codProy;
    }
    
    
    
    @XmlElement(name="IDSUPERVEN")
    public Integer getIdSuperVen() {
        return idSuperVen;
    }

    public void setIdSuperVen(Integer idSuperVen) {
        this.idSuperVen = idSuperVen;
    }
    
    @XmlElement(name="IDMESA")
    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }
        
    @XmlElement(name="IDCOORDINADOR")
    public Integer getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }
    
    @XmlElement(name="IDGERENTEZONAL")
    public Integer getIdGerenteZonal() {
        return idGerenteZonal;
    }

    public void setIdGerenteZonal(Integer idGerenteZonal) {
        this.idGerenteZonal = idGerenteZonal;
    }
    
    @XmlElement(name="IDSUPERVISOR")
    public Integer getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(Integer idSupervisor) {
        this.idSupervisor = idSupervisor;
    }
    
    @XmlElement(name="ESTADOACTUAL")
    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }
    
    
    
    @XmlElement(name="USERREG")
    public String getUsuarioReg() {
        return usuarioReg;
    }

    public void setUsuarioReg(String usuarioReg) {
        this.usuarioReg = usuarioReg;
    }

    public String getDniVendedor() {
        return dniVendedor;
    }

    public void setDniVendedor(String dniVendedor) {
        this.dniVendedor = dniVendedor;
    }        
    
    public String getNomDoc() {
        return nomDoc;
    }

    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getPreimpreso() {
        return preimpreso;
    }

    public void setPreimpreso(String preimpreso) {
        this.preimpreso = preimpreso;
    }

    public String getNomCondicionVenta() {
        return nomCondicionVenta;
    }

    public void setNomCondicionVenta(String nomCondicionVenta) {
        this.nomCondicionVenta = nomCondicionVenta;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public String getNomVendedor() {
        return nomVendedor;
    }

    public void setNomVendedor(String nomVendedor) {
        this.nomVendedor = nomVendedor;
    }

    public String getPuntoEmsion() {
        return puntoEmsion;
    }

    public void setPuntoEmsion(String puntoEmsion) {
        this.puntoEmsion = puntoEmsion;
    }
    
    
    
    @XmlElement(name="nroci")
    public String getNroci() {
        return nroci;
    }

    public void setNroci(String nroci) {
        this.nroci = nroci;
    }       
    
    @XmlElement(name="codscc")
    public String getCodSubCentroCosto() {
        return codSubCentroCosto;
    }

    public void setCodSubCentroCosto(String codSubCentroCosto) {
        this.codSubCentroCosto = codSubCentroCosto;
    }
        
    @XmlElement(name="marveh")
    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }    
    
    @XmlElement(name="nomcho")
    public String getNomChofer() {
        return nomChofer;
    }

    public void setNomChofer(String nomChofer) {
        this.nomChofer = nomChofer;
    }    
    
    @XmlElement(name="nrolic")
    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }    
    
    @XmlElement(name="plaveh")
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }                 
    
    @XmlElement(name="idcabeceraventa")
    public String getIdCabecera() {
        return idCabecera;
    }

    public void setIdCabecera(String idCabecera) {
        this.idCabecera = idCabecera;
    }        
    
    @XmlElement(name="fecha")
    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    
    @XmlElement(name="cdocu")
    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }
    
    @XmlElement(name="ndocu")
    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }
    
    @XmlElement(name="tfact")
    public String getTipoFacturacion() {
        return tipoFacturacion;
    }

    public void setTipoFacturacion(String tipoFacturacion) {
        this.tipoFacturacion = tipoFacturacion;
    }
    
    @XmlElement(name="codcli")
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    
    @XmlElement(name="nomcli")
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    @XmlElement(name="ruccli")
    public String getRucCliente() {
        return rucCliente;
    }

    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }
    
    @XmlElement(name="dirent")
    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }
    
    @XmlElement(name="codcdv")
    public String getIdCondicionVenta() {
        return idCondicionVenta;
    }

    public void setIdCondicionVenta(String idCondicionVenta) {
        this.idCondicionVenta = idCondicionVenta;
    }
    
    @XmlElement(name="codvta")
    public String getIdTipoVenta() {
        return idTipoVenta;
    }

    public void setIdTipoVenta(String idTipoVenta) {
        this.idTipoVenta = idTipoVenta;
    }
    
    @XmlElement(name="dias")
    public Integer getDiasCredito() {
        return diasCredito;
    }

    public void setDiasCredito(Integer diasCredito) {
        this.diasCredito = diasCredito;
    }
    
    @XmlElement(name="fven")
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    
    @XmlElement(name="mone")
    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }
    
    @XmlElement(name="tcam")
    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
    
    @XmlElement(name="tota")
    public BigDecimal getTotalAfecto() {
        return totalAfecto;
    }

    public void setTotalAfecto(BigDecimal totalAfecto) {
        this.totalAfecto = totalAfecto;
    }
    
    @XmlElement(name="toti")
    public BigDecimal getTotalIgv() {
        return totalIgv;
    }

    public void setTotalIgv(BigDecimal totalIgv) {
        this.totalIgv = totalIgv;
    }
    
    @XmlElement(name="totn")
    public BigDecimal getTotalNeto() {  
        return totalNeto;
    }

    public void setTotalNeto(BigDecimal totalNeto) {
        this.totalNeto = totalNeto;
    }
    
    @XmlElement(name="codven")    
    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
    
    @XmlElement(name="codalm")    
    public String getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(String idAlmacen) {
        this.idAlmacen = idAlmacen;
    }
    
    @XmlElement(name="codpto")    
    public String getIdPtoEmision() {
        return idPtoEmision;
    }

    public void setIdPtoEmision(String idPtoEmision) {
        this.idPtoEmision = idPtoEmision;
    }
    
    @XmlElement(name="flag")    
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    
    @XmlElement(name="codtra")    
    public String getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(String idTransportista) {
        this.idTransportista = idTransportista;
    }
    
    @XmlElement(name="crefe")    
    public String getIdTipoDocRef() {
        return idTipoDocRef;
    }

    public void setIdTipoDocRef(String idTipoDocRef) {
        this.idTipoDocRef = idTipoDocRef;
    }
    
    @XmlElement(name="nrefe")    
    public String getCorrelativoRef() {
        return correlativoRef;
    }

    public void setCorrelativoRef(String correlativoRef) {
        this.correlativoRef = correlativoRef;
    }
    
    @XmlElement(name="compro")    
    public String getCompro() {
        return compro;
    }

    public void setCompro(String compro) {
        this.compro = compro;
    }
    
    @XmlElement(name="drefe")    
    public String getDocRefe() {
        return docRefe;
    }

    public void setDocRefe(String docRefe) {
        this.docRefe = docRefe;
    }
    
    @XmlElement(name="dirpar")    
    public String getDirPar() {
        return dirPar;
    }

    public void setDirPar(String dirPar) {
        this.dirPar = dirPar;
    }
    
    @XmlElement(name="IDSUCURSAL")
    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    @XmlElement(name="frontera")    
    public Integer getFrontera() {
        return frontera;
    }

    public void setFrontera(Integer frontera) {
        this.frontera = frontera;
    }
    
    @XmlElement(name="tcme")    
    public BigDecimal getTcMercado() {
        return tcMercado;
    }

    public void setTcMercado(BigDecimal tcMercado) {
        this.tcMercado = tcMercado;
    }

    public BigDecimal getTotalNoAfecto() {
        return totalNoAfecto;
    }

    public void setTotalNoAfecto(BigDecimal totalNoAfecto) {
        this.totalNoAfecto = totalNoAfecto;
    }

    public BigDecimal getTotalPercepcion() {
        return totalPercepcion;
    }

    public void setTotalPercepcion(BigDecimal totalPercepcion) {
        this.totalPercepcion = totalPercepcion;
    }

    public String getAfectoPercepcion() {
        return afectoPercepcion;
    }

    public void setAfectoPercepcion(String afectoPercepcion) {
        this.afectoPercepcion = afectoPercepcion;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
        
    @XmlElement(name="ctaigv")    
    public String getCtaIgv() {
        return ctaIgv;
    }

    public void setCtaIgv(String ctaIgv) {
        this.ctaIgv = ctaIgv;
    }
    
    @XmlElement(name="ctacliente")    
    public String getCtaCliente() {
        return ctaCliente;
    }

    public void setCtaCliente(String ctaCliente) {
        this.ctaCliente = ctaCliente;
    }
    
}
