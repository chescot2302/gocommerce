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
public class CabIngreso implements Serializable{
    private Date fecha;
    private String cdocu;
    private String ndocu;
    private String crefe;
    private String nrefe;
    private String orde;
    private String codPro;
    private String nomPro;
    private String rucPro;
    private String guia;
    private String mone;
    private BigDecimal tcam;
    private BigDecimal tota;
    private BigDecimal toti;
    private BigDecimal totn; 
    private String flag;
    private String codAlm;
    private String codGlo;
    private String cdge;
    private String ndge;
    private String codven;
    private String codscc;
    private Integer egrc;
    private Integer selchk;
    private String marchk;
    private Integer autg;
    private String codtra;
    private String origen;
    private BigDecimal tasfci;
    private String flagtp;
    private Long version;
    private String operacion; 

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCdocu() {
        return cdocu;
    }

    public void setCdocu(String cdocu) {
        this.cdocu = cdocu;
    }

    public String getNdocu() {
        return ndocu;
    }

    public void setNdocu(String ndocu) {
        this.ndocu = ndocu;
    }

    public String getCrefe() {
        return crefe;
    }

    public void setCrefe(String crefe) {
        this.crefe = crefe;
    }

    public String getNrefe() {
        return nrefe;
    }

    public void setNrefe(String nrefe) {
        this.nrefe = nrefe;
    }

    public String getOrde() {
        return orde;
    }

    public void setOrde(String orde) {
        this.orde = orde;
    }

    public String getCodPro() {
        return codPro;
    }

    public void setCodPro(String codPro) {
        this.codPro = codPro;
    }

    public String getNomPro() {
        return nomPro;
    }

    public void setNomPro(String nomPro) {
        this.nomPro = nomPro;
    }

    public String getRucPro() {
        return rucPro;
    }

    public void setRucPro(String rucPro) {
        this.rucPro = rucPro;
    }

    public String getGuia() {
        return guia;
    }

    public void setGuia(String guia) {
        this.guia = guia;
    }

    public String getMone() {
        return mone;
    }

    public void setMone(String mone) {
        this.mone = mone;
    }

    public BigDecimal getTcam() {
        return tcam;
    }

    public void setTcam(BigDecimal tcam) {
        this.tcam = tcam;
    }

    public BigDecimal getTota() {
        return tota;
    }

    public void setTota(BigDecimal tota) {
        this.tota = tota;
    }

    public BigDecimal getToti() {
        return toti;
    }

    public void setToti(BigDecimal toti) {
        this.toti = toti;
    }

    public BigDecimal getTotn() {
        return totn;
    }

    public void setTotn(BigDecimal totn) {
        this.totn = totn;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCodAlm() {
        return codAlm;
    }

    public void setCodAlm(String codAlm) {
        this.codAlm = codAlm;
    }

    public String getCodGlo() {
        return codGlo;
    }

    public void setCodGlo(String codGlo) {
        this.codGlo = codGlo;
    }

    public String getCdge() {
        return cdge;
    }

    public void setCdge(String cdge) {
        this.cdge = cdge;
    }

    public String getNdge() {
        return ndge;
    }

    public void setNdge(String ndge) {
        this.ndge = ndge;
    }

    public String getCodven() {
        return codven;
    }

    public void setCodven(String codven) {
        this.codven = codven;
    }

    public String getCodscc() {
        return codscc;
    }

    public void setCodscc(String codscc) {
        this.codscc = codscc;
    }

    public Integer getEgrc() {
        return egrc;
    }

    public void setEgrc(Integer egrc) {
        this.egrc = egrc;
    }

    public Integer getSelchk() {
        return selchk;
    }

    public void setSelchk(Integer selchk) {
        this.selchk = selchk;
    }

    public String getMarchk() {
        return marchk;
    }

    public void setMarchk(String marchk) {
        this.marchk = marchk;
    }

    public Integer getAutg() {
        return autg;
    }

    public void setAutg(Integer autg) {
        this.autg = autg;
    }

    public String getCodtra() {
        return codtra;
    }

    public void setCodtra(String codtra) {
        this.codtra = codtra;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public BigDecimal getTasfci() {
        return tasfci;
    }

    public void setTasfci(BigDecimal tasfci) {
        this.tasfci = tasfci;
    }

    public String getFlagtp() {
        return flagtp;
    }

    public void setFlagtp(String flagtp) {
        this.flagtp = flagtp;
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
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.cdocu);
        hash = 17 * hash + Objects.hashCode(this.ndocu);
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
        final CabIngreso other = (CabIngreso) obj;
        if (!Objects.equals(this.cdocu, other.cdocu)) {
            return false;
        }
        if (!Objects.equals(this.ndocu, other.ndocu)) {
            return false;
        }
        return true;
    }
    
    
    
}
