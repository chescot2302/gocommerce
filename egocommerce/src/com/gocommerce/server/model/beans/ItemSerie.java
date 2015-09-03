/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.model.beans;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@XmlRootElement(name = "itemserie")
public class ItemSerie implements Serializable{
    private Integer item;
    private String codi;
    private String serie;
    private String cdocu;
    private String ndocu;
    private String cdocui;
    private String ndocui;
    private String cdocus;
    private String ndocus;
    private String flag;
    private String codpro;
    private String cdocusp;
    private String ndocusp;
    private String cdocuip;
    private String ndocuip;
    private String codAlm;
    private Long version;
    private String operacion;
    private String telefono="";
    private Boolean seleccionado=false;
    private String usuarioReg;
    
    @XmlElement(name="USERREG")
    public String getUsuarioReg() {
        return usuarioReg;
    }

    public void setUsuarioReg(String usuarioReg) {
        this.usuarioReg = usuarioReg;
    }        

    public Boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    
    
    @XmlElement(name="TELEFONO")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }        
    
    @XmlElement(name="item")
    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }
    
    @XmlElement(name="codi")
    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }
    
    @XmlElement(name="serie")
    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }
    
    @XmlElement(name="cdocu")
    public String getCdocu() {
        return cdocu;
    }

    public void setCdocu(String cdocu) {
        this.cdocu = cdocu;
    }
    
    @XmlElement(name="ndocu")
    public String getNdocu() {
        return ndocu;
    }

    public void setNdocu(String ndocu) {
        this.ndocu = ndocu;
    }        
    
    @XmlElement(name="cdocui")
    public String getCdocui() {
        return cdocui;
    }

    public void setCdocui(String cdocui) {
        this.cdocui = cdocui;
    }
    
    @XmlElement(name="ndocui")
    public String getNdocui() {
        return ndocui;
    }

    public void setNdocui(String ndocui) {
        this.ndocui = ndocui;
    }
    
    @XmlElement(name="cdocus")
    public String getCdocus() {
        return cdocus;
    }

    public void setCdocus(String cdocus) {
        this.cdocus = cdocus;
    }
    
    @XmlElement(name="ndocus")
    public String getNdocus() {
        return ndocus;
    }

    public void setNdocus(String ndocus) {
        this.ndocus = ndocus;
    }
    
    @XmlElement(name="flag")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    
    @XmlElement(name="codpro")
    public String getCodpro() {
        return codpro;
    }

    public void setCodpro(String codpro) {
        this.codpro = codpro;
    }
    
    @XmlElement(name="cdocusp")
    public String getCdocusp() {
        return cdocusp;
    }

    public void setCdocusp(String cdocusp) {
        this.cdocusp = cdocusp;
    }
    
    @XmlElement(name="ndocusp")
    public String getNdocusp() {
        return ndocusp;
    }

    public void setNdocusp(String ndocusp) {
        this.ndocusp = ndocusp;
    }
    
    @XmlElement(name="cdocuip")
    public String getCdocuip() {
        return cdocuip;
    }

    public void setCdocuip(String cdocuip) {
        this.cdocuip = cdocuip;
    }
    
    @XmlElement(name="ndocuip")
    public String getNdocuip() {
        return ndocuip;
    }

    public void setNdocuip(String ndocuip) {
        this.ndocuip = ndocuip;
    }
    
    @XmlElement(name="codalm")
    public String getCodAlm() {
        return codAlm;
    }

    public void setCodAlm(String codAlm) {
        this.codAlm = codAlm;
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
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.serie);
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
        final ItemSerie other = (ItemSerie) obj;
        if (!Objects.equals(this.serie, other.serie)) {
            return false;
        }
        return true;
    }
    
    
    
}
