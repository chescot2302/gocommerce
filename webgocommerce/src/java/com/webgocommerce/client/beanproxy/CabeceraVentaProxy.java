/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.CabeceraVenta;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorCabeceraVenta;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=CabeceraVenta.class,locator=LocatorCabeceraVenta.class)
public interface CabeceraVentaProxy extends EntityProxy{
    public String getUsuarioReg();

    public void setUsuarioReg(String usuarioReg);
    
    public String getDniVendedor();

    public void setDniVendedor(String dniVendedor);
    
    public String getIdCabecera();

    public void setIdCabecera(String idCabecera);
    
    public Date getFechaEmision();

    public void setFechaEmision(Date fechaEmision);

    public String getTipoDoc();

    public void setTipoDoc(String tipoDoc);

    public String getCorrelativo();

    public void setCorrelativo(String correlativo);

    public String getTipoFacturacion();

    public void setTipoFacturacion(String tipoFacturacion);

    public String getIdCliente();

    public void setIdCliente(String idCliente);

    public String getNombreCliente();

    public void setNombreCliente(String nombreCliente);

    public String getRucCliente();

    public void setRucCliente(String rucCliente);

    public String getDireccionCliente();

    public void setDireccionCliente(String direccionCliente);

    public String getIdCondicionVenta();

    public void setIdCondicionVenta(String idCondicionVenta);

    public String getIdTipoVenta();

    public void setIdTipoVenta(String idTipoVenta);

    public Integer getDiasCredito();

    public void setDiasCredito(Integer diasCredito);

    public Date getFechaVencimiento();

    public void setFechaVencimiento(Date fechaVencimiento);

    public String getIdMoneda();

    public void setIdMoneda(String idMoneda);

    public BigDecimal getTipoCambio();

    public void setTipoCambio(BigDecimal tipoCambio);

    public BigDecimal getTotalAfecto();

    public void setTotalAfecto(BigDecimal totalAfecto);

    public BigDecimal getTotalIgv();

    public void setTotalIgv(BigDecimal totalIgv);

    public BigDecimal getTotalNeto();

    public void setTotalNeto(BigDecimal totalNeto);

    public String getIdVendedor();

    public void setIdVendedor(String idVendedor);

    public String getIdAlmacen();

    public void setIdAlmacen(String idAlmacen);

    public String getIdPtoEmision();

    public void setIdPtoEmision(String idPtoEmision);

    public String getFlag();

    public void setFlag(String flag);

    public String getIdTransportista();

    public void setIdTransportista(String idTransportista);

    public String getIdTipoDocRef();

    public void setIdTipoDocRef(String idTipoDocRef);

    public String getCorrelativoRef();

    public void setCorrelativoRef(String correlativoRef);

    public String getCompro();

    public void setCompro(String compro);

    public String getDocRefe();

    public void setDocRefe(String docRefe);

    public String getDirPar();

    public void setDirPar(String dirPar);

    public String getIdSucursal();

    public void setIdSucursal(String idSucursal);

    public Integer getFrontera();

    public void setFrontera(Integer frontera);

    public BigDecimal getTcMercado();

    public void setTcMercado(BigDecimal tcMercado);

    public BigDecimal getTotalNoAfecto();

    public void setTotalNoAfecto(BigDecimal totalNoAfecto);

    public BigDecimal getTotalPercepcion();

    public void setTotalPercepcion(BigDecimal totalPercepcion);

    public String getAfectoPercepcion();

    public void setAfectoPercepcion(String afectoPercepcion);

    public Long getVersion();

    public void setVersion(Long version);
     
    public String getCtaIgv();

    public void setCtaIgv(String ctaIgv);
        
    public String getCtaCliente();

    public void setCtaCliente(String ctaCliente);
        
    public String getNroci();

    public void setNroci(String nroci);
       
    public String getCodSubCentroCosto();

    public void setCodSubCentroCosto(String codSubCentroCosto);
            
    public String getMarcaVehiculo();

    public void setMarcaVehiculo(String marcaVehiculo);
        
    public String getNomChofer();

    public void setNomChofer(String nomChofer);
        
    public String getNumeroLicencia();

    public void setNumeroLicencia(String numeroLicencia);
        
    public String getPlacaVehiculo();

    public void setPlacaVehiculo(String placaVehiculo);
    
    public String getNomDoc();

    public void setNomDoc(String nomDoc);

    public String getSerie();

    public void setSerie(String serie);

    public String getPreimpreso();

    public void setPreimpreso(String preimpreso);

    public String getNomCondicionVenta();

    public void setNomCondicionVenta(String nomCondicionVenta);

    public String getTipoVenta();

    public void setTipoVenta(String tipoVenta);

    public String getNomVendedor();

    public void setNomVendedor(String nomVendedor);

    public String getPuntoEmsion();

    public void setPuntoEmsion(String puntoEmsion);
        
    public Integer getIdSuperVen();

    public void setIdSuperVen(Integer idSuperVen);
        
    public Integer getIdMesa();

    public void setIdMesa(Integer idMesa);
            
    public Integer getIdCoordinador();

    public void setIdCoordinador(Integer idCoordinador);
        
    public Integer getIdGerenteZonal();

    public void setIdGerenteZonal(Integer idGerenteZonal);
        
    public Integer getIdSupervisor();

    public void setIdSupervisor(Integer idSupervisor);
        
    public String getEstadoActual();

    public void setEstadoActual(String estadoActual);
    
    public String getCodProy();

    public void setCodProy(String codProy);
    
    public BigDecimal getTotalPlan();

    public void setTotalPlan(BigDecimal totalPlan);
    
    public String getNomMesa();

    public void setNomMesa(String nomMesa);

    public String getDniCoordinador();

    public void setDniCoordinador(String dniCoordinador);

    public String getNomCoordinador();

    public void setNomCoordinador(String nomCoordinador);

    public String getDniGerenteZonal();

    public void setDniGerenteZonal(String dniGerenteZonal);

    public String getNomGerenteZonal();

    public void setNomGerenteZonal(String nomGerenteZonal);

    public String getDniSupervisor();

    public void setDniSupervisor(String dniSupervisor);

    public String getNomSupervisor();

    public void setNomSupervisor(String nomSupervisor);
    
    public BigDecimal getTotalEquipos();

    public void setTotalEquipos(BigDecimal totalEquipos);
    
    public String getObservacion();

    public void setObservacion(String observacion);
}
