/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author SISTEMAS
 */
public class InitParam implements Serializable{
    private Integer id;
    private BigDecimal igv;
    private Integer habilitaPercepcion;
    private Integer agentePercepcion;
    private Long version;
    private String operacion;  
    private BigDecimal topeBoletaVentaSinDni;
    private BigDecimal dsctoAgentePerceptor;
    private BigDecimal dsctoFacturaPerceptor;
    private BigDecimal dsctoBoletaPerceptor;
    private BigDecimal topeBoletaPercepcion;
    private String ctaIgv;
    private String ctaClientes;
    private String ctaPercepcion;
    private Integer maxDetalleCot;
    private Integer maxDetalleBol;
    private Integer maxDetalleFac;
    private Date fechaServer;
    private Integer frontera;//1: FUERRA DE AMAZONIA , 2: AMAZONIA SIN REGISTRO TRIBUTARIO, 3: AMAZONIA CON REGISTRO TRIBUTARIO
    private Integer autoDespacho;

    public Integer getAutoDespacho() {
        return autoDespacho;
    }

    public void setAutoDespacho(Integer autoDespacho) {
        this.autoDespacho = autoDespacho;
    }        
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
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

    public Integer getHabilitaPercepcion() {
        return habilitaPercepcion;
    }

    public void setHabilitaPercepcion(Integer habilitaPercepcion) {
        this.habilitaPercepcion = habilitaPercepcion;
    }

    public Integer getAgentePercepcion() {
        return agentePercepcion;
    }

    public void setAgentePercepcion(Integer agentePercepcion) {
        this.agentePercepcion = agentePercepcion;
    }

    public BigDecimal getTopeBoletaVentaSinDni() {
        return topeBoletaVentaSinDni;
    }

    public void setTopeBoletaVentaSinDni(BigDecimal topeBoletaVentaSinDni) {
        this.topeBoletaVentaSinDni = topeBoletaVentaSinDni;
    }    

    public BigDecimal getDsctoAgentePerceptor() {
        return dsctoAgentePerceptor;
    }

    public void setDsctoAgentePerceptor(BigDecimal dsctoAgentePerceptor) {
        this.dsctoAgentePerceptor = dsctoAgentePerceptor;
    }

    public BigDecimal getDsctoFacturaPerceptor() {
        return dsctoFacturaPerceptor;
    }

    public void setDsctoFacturaPerceptor(BigDecimal dsctoFacturaPerceptor) {
        this.dsctoFacturaPerceptor = dsctoFacturaPerceptor;
    }

    public BigDecimal getDsctoBoletaPerceptor() {
        return dsctoBoletaPerceptor;
    }

    public void setDsctoBoletaPerceptor(BigDecimal dsctoBoletaPerceptor) {
        this.dsctoBoletaPerceptor = dsctoBoletaPerceptor;
    }

    public BigDecimal getTopeBoletaPercepcion() {
        return topeBoletaPercepcion;
    }

    public void setTopeBoletaPercepcion(BigDecimal topeBoletaPercepcion) {
        this.topeBoletaPercepcion = topeBoletaPercepcion;
    }

    public String getCtaIgv() {
        return ctaIgv;
    }

    public void setCtaIgv(String ctaIgv) {
        this.ctaIgv = ctaIgv;
    }   

    public String getCtaClientes() {
        return ctaClientes;
    }

    public void setCtaClientes(String ctaClientes) {
        this.ctaClientes = ctaClientes;
    }

    public String getCtaPercepcion() {
        return ctaPercepcion;
    }

    public void setCtaPercepcion(String ctaPercepcion) {
        this.ctaPercepcion = ctaPercepcion;
    }    

    public Integer getMaxDetalleCot() {
        return maxDetalleCot;
    }

    public void setMaxDetalleCot(Integer maxDetalleCot) {
        this.maxDetalleCot = maxDetalleCot;
    }

    public Integer getMaxDetalleBol() {
        return maxDetalleBol;
    }

    public void setMaxDetalleBol(Integer maxDetalleBol) {
        this.maxDetalleBol = maxDetalleBol;
    }

    public Integer getMaxDetalleFac() {
        return maxDetalleFac;
    }

    public void setMaxDetalleFac(Integer maxDetalleFac) {
        this.maxDetalleFac = maxDetalleFac;
    }

    public Date getFechaServer() {
        return fechaServer;
    }

    public void setFechaServer(Date fechaServer) {
        this.fechaServer = fechaServer;
    }      

    public Integer getFrontera() {
        return frontera;
    }

    public void setFrontera(Integer frontera) {
        this.frontera = frontera;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final InitParam other = (InitParam) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
}
