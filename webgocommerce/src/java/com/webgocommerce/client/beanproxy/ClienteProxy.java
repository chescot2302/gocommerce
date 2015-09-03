/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.Cliente;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorCliente;
import java.math.BigDecimal;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=Cliente.class,locator=LocatorCliente.class)
public interface ClienteProxy extends EntityProxy{
    public String getId();

    public void setId(String id);

    public String getNombres();

    public void setNombres(String nombres);

    public String getRuc();

    public void setRuc(String ruc);

    public String getDni();

    public void setDni(String dni);

    public Integer getTipoPersona();

    public void setTipoPersona(Integer tipoPersona);

    public Integer getPercepcion();

    public void setPercepcion(Integer percepcion);

    public Integer getAgentePerceptor();

    public void setAgentePerceptor(Integer agentePerceptor);

    public Integer getEstado();

    public void setEstado(Integer estado);

    public Integer getVigenciaLineaCredito();

    public void setVigenciaLineaCredito(Integer vigenciaLineaCredito);

    public String getMonedaLineaCredito();

    public void setMonedaLineaCredito(String monedaLineaCredito);

    public BigDecimal getMontoCredito();

    public void setMontoCredito(BigDecimal montoCredito);

    public BigDecimal getAmpliaCredito();

    public void setAmpliaCredito(BigDecimal ampliaCredito);

    public String getIdCondicionVenta();

    public void setIdCondicionVenta(String idCondicionVenta);

    public BigDecimal getPorcDsctoContado();

    public void setPorcDsctoContado(BigDecimal porcDsctoContado);

    public BigDecimal getPorcDsctoCredito();

    public void setPorcDsctoCredito(BigDecimal porcDsctoCredito);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
    
    public String getDireccion();

    public void setDireccion(String direccion);
    
    public String getDireccionEntrega();

    public void setDireccionEntrega(String direccionEntrega);
    
    public String getTipoDocIden();

    public void setTipoDocIden(String tipoDocIden);
    
    public String getIdPais();

    public void setIdPais(String idPais);

    public String getIdDepartamento();

    public void setIdDepartamento(String idDepartamento);

    public String getIdProvincia();

    public void setIdProvincia(String idProvincia);

    public String getIdDistrito();

    public void setIdDistrito(String idDistrito);
    
    public String getTelefono();

    public void setTelefono(String telefono);

    public String getEmail();

    public void setEmail(String email);
    
    public String getDocumento();

    public void setDocumento(String documento);
}
