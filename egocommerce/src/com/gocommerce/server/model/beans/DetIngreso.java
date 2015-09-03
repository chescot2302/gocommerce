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
public class DetIngreso implements Serializable{
    private Date fecha;
    private String cdocu;
    private String ndocu;
    private String nrope;
    private String codpro;
    private Integer item;
    private String codi;
    private String codf;
    private String marc;
    private String descr;
    private BigDecimal cant;
    private String umed;
    private BigDecimal preu;
    private BigDecimal dsct;
    private BigDecimal dsct2;
    private BigDecimal dsct3;
    private BigDecimal tota;
    private BigDecimal totn;
    private BigDecimal tcam;
    private String mone;
    private String codalm;
    private String aigv;
    private String flag;
    private String coduc;
    private String ucom;
    private String uvta;
    private Integer ucon;
    private Integer uckd;
    private String msto;
    private BigDecimal peso;
    private Integer pcfle;
    private Integer tofle;
    private Integer pcemb;
    private Integer toemb;
    private Integer dsctnc;
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

    public String getNrope() {
        return nrope;
    }

    public void setNrope(String nrope) {
        this.nrope = nrope;
    }

    public String getCodpro() {
        return codpro;
    }

    public void setCodpro(String codpro) {
        this.codpro = codpro;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getCodf() {
        return codf;
    }

    public void setCodf(String codf) {
        this.codf = codf;
    }

    public String getMarc() {
        return marc;
    }

    public void setMarc(String marc) {
        this.marc = marc;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public BigDecimal getCant() {
        return cant;
    }

    public void setCant(BigDecimal cant) {
        this.cant = cant;
    }

    public String getUmed() {
        return umed;
    }

    public void setUmed(String umed) {
        this.umed = umed;
    }

    public BigDecimal getPreu() {
        return preu;
    }

    public void setPreu(BigDecimal preu) {
        this.preu = preu;
    }

    public BigDecimal getDsct() {
        return dsct;
    }

    public void setDsct(BigDecimal dsct) {
        this.dsct = dsct;
    }

    public BigDecimal getDsct2() {
        return dsct2;
    }

    public void setDsct2(BigDecimal dsct2) {
        this.dsct2 = dsct2;
    }

    public BigDecimal getDsct3() {
        return dsct3;
    }

    public void setDsct3(BigDecimal dsct3) {
        this.dsct3 = dsct3;
    }

    public BigDecimal getTota() {
        return tota;
    }

    public void setTota(BigDecimal tota) {
        this.tota = tota;
    }

    public BigDecimal getTotn() {
        return totn;
    }

    public void setTotn(BigDecimal totn) {
        this.totn = totn;
    }

    public BigDecimal getTcam() {
        return tcam;
    }

    public void setTcam(BigDecimal tcam) {
        this.tcam = tcam;
    }

    public String getMone() {
        return mone;
    }

    public void setMone(String mone) {
        this.mone = mone;
    }

    public String getCodalm() {
        return codalm;
    }

    public void setCodalm(String codalm) {
        this.codalm = codalm;
    }

    public String getAigv() {
        return aigv;
    }

    public void setAigv(String aigv) {
        this.aigv = aigv;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCoduc() {
        return coduc;
    }

    public void setCoduc(String coduc) {
        this.coduc = coduc;
    }

    public String getUcom() {
        return ucom;
    }

    public void setUcom(String ucom) {
        this.ucom = ucom;
    }

    public String getUvta() {
        return uvta;
    }

    public void setUvta(String uvta) {
        this.uvta = uvta;
    }

    public Integer getUcon() {
        return ucon;
    }

    public void setUcon(Integer ucon) {
        this.ucon = ucon;
    }

    public Integer getUckd() {
        return uckd;
    }

    public void setUckd(Integer uckd) {
        this.uckd = uckd;
    }

    public String getMsto() {
        return msto;
    }

    public void setMsto(String msto) {
        this.msto = msto;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Integer getPcfle() {
        return pcfle;
    }

    public void setPcfle(Integer pcfle) {
        this.pcfle = pcfle;
    }

    public Integer getTofle() {
        return tofle;
    }

    public void setTofle(Integer tofle) {
        this.tofle = tofle;
    }

    public Integer getPcemb() {
        return pcemb;
    }

    public void setPcemb(Integer pcemb) {
        this.pcemb = pcemb;
    }

    public Integer getToemb() {
        return toemb;
    }

    public void setToemb(Integer toemb) {
        this.toemb = toemb;
    }

    public Integer getDsctnc() {
        return dsctnc;
    }

    public void setDsctnc(Integer dsctnc) {
        this.dsctnc = dsctnc;
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
        hash = 97 * hash + Objects.hashCode(this.cdocu);
        hash = 97 * hash + Objects.hashCode(this.ndocu);
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
        final DetIngreso other = (DetIngreso) obj;
        if (!Objects.equals(this.cdocu, other.cdocu)) {
            return false;
        }
        if (!Objects.equals(this.ndocu, other.ndocu)) {
            return false;
        }
        return true;
    }
    
    
    
}
