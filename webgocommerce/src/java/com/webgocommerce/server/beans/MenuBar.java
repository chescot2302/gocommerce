/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author SISTEMAS
 */
public class MenuBar implements Serializable{
    private Integer idMenuBar;
    private String variable;
    private String descripcion;
    private String tipo;
    private Integer orden;
    private Integer nivel;
    private Integer idMenuPadre;
    private Integer grupo;
    private Integer numSubMenu;
    private List<MenuBar> hijos=new ArrayList<MenuBar>();
    private Long version;
    private String operacion="N";    
    private MenuBar padre;  
    private Integer idBdUsuario;
    private String estado="D";        
    private String comando;

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }       

    public Integer getIdBdUsuario() {
        return idBdUsuario;
    }

    public void setIdBdUsuario(Integer idBdUsuario) {
        this.idBdUsuario = idBdUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }        

    public MenuBar getPadre() {
        return padre;
    }

    public void setPadre(MenuBar padre) {
        this.padre = padre;
    }        

    public Integer getIdMenuBar() {
        return idMenuBar;
    }

    public void setIdMenuBar(Integer idMenuBar) {
        this.idMenuBar = idMenuBar;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getIdMenuPadre() {
        return idMenuPadre;
    }

    public void setIdMenuPadre(Integer idMenuPadre) {
        this.idMenuPadre = idMenuPadre;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Integer getNumSubMenu() {
        return numSubMenu;
    }

    public void setNumSubMenu(Integer numSubMenu) {
        this.numSubMenu = numSubMenu;
    }

    public List<MenuBar> getHijos() {
        return hijos;
    }

    public void setHijos(List<MenuBar> hijos) {
        this.hijos = hijos;
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
    
    public void setHijo(MenuBar bean){
        this.hijos.add(bean);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idMenuBar);
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
        final MenuBar other = (MenuBar) obj;
        if (!Objects.equals(this.idMenuBar, other.idMenuBar)) {
            return false;
        }
        return true;
    }
    
    
}
