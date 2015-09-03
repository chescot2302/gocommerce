/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.ItemPlan;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorItemPlan;
import java.math.BigDecimal;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=ItemPlan.class,locator=LocatorItemPlan.class)
public interface ItemPlanProxy extends EntityProxy{
    public Integer getIdEquipPlan();

    public void setIdEquipPlan(Integer idEquipPlan);

    public String getIdItem();

    public void setIdItem(String idItem);

    public String getIdItemPlan();

    public void setIdItemPlan(String idItemPlan);

    public Integer getIdLista();

    public void setIdLista(Integer idLista);

    public String getcDocu();

    public void setcDocu(String cDocu);

    public String getnDocu();

    public void setnDocu(String nDocu);

    public BigDecimal getCantidad();

    public void setCantidad(BigDecimal cantidad);

    public BigDecimal getPrecio();

    public void setPrecio(BigDecimal precio);

    public BigDecimal getTotal();

    public void setTotal(BigDecimal total);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
    
    public String getDescripcion();

    public void setDescripcion(String descripcion);
}
