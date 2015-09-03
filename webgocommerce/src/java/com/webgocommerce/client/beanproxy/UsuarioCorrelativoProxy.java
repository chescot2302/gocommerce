/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.UsuarioCorrelativo;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorUsuarioCorrelativo;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=UsuarioCorrelativo.class,locator=LocatorUsuarioCorrelativo.class)
public interface UsuarioCorrelativoProxy  extends EntityProxy{
    public Integer getId();

    public void setId(Integer id);

    public String getIdUsuario();

    public void setIdUsuario(String idUsuario);

    public String getNomUsuario();

    public void setNomUsuario(String nomUsuario);

    public String getNomAcceso();

    public void setNomAcceso(String nomAcceso);

    public Integer getIdBdUsuario();

    public void setIdBdUsuario(Integer idBdUsuario);

    public String getIdDocumento();

    public void setIdDocumento(String idDocumento);

    public String getNomDocumento();

    public void setNomDocumento(String nomDocumento);

    public String getIdPuntoEmision();

    public void setIdPuntoEmision(String idPuntoEmision);

    public String getNomPtoEmision();

    public void setNomPtoEmision(String nomPtoEmision);

    public String getSerie();

    public void setSerie(String serie);

    public Date getFechaIni();

    public void setFechaIni(Date fechaIni);

    public Date getFechaFin();

    public void setFechaFin(Date fechaFin);

    public String getEstado();

    public void setEstado(String estado);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);

    public String getIdSucursal();

    public void setIdSucursal(String idSucursal);

    public String getNomSucursal();

    public void setNomSucursal(String nomSucursal);

    public String getIdTienda();

    public void setIdTienda(String idTienda);

    public String getNomTienda();

    public void setNomTienda(String nomTienda);
}
