/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.TipoCambio;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorTipoCambio;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=TipoCambio.class,locator=LocatorTipoCambio.class)
public interface TipoCambioProxy extends EntityProxy{
    public Date getFecha();

    public void setFecha(Date fecha);

    public String getMes();

    public void setMes(String mes);

    public String getDia();

    public void setDia(String dia);

    public BigDecimal getTcCompra();

    public void setTcCompra(BigDecimal tcCompra);

    public BigDecimal getTcVenta();

    public void setTcVenta(BigDecimal tcVenta);

    public BigDecimal getTcMercado();

    public void setTcMercado(BigDecimal tcMercado);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
}
