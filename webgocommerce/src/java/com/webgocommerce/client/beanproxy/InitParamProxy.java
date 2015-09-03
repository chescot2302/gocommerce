/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.beanproxy;

import com.gocommerce.server.model.beans.InitParam;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.webgocommerce.server.locator.LocatorInitParam;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
@ProxyFor(value=InitParam.class,locator=LocatorInitParam.class)
public interface InitParamProxy extends EntityProxy{
    public Integer getId();

    public void setId(Integer id);

    public BigDecimal getIgv();

    public void setIgv(BigDecimal igv);

    public Long getVersion();

    public void setVersion(Long version);

    public String getOperacion();

    public void setOperacion(String operacion);
    
    public Integer getHabilitaPercepcion();

    public void setHabilitaPercepcion(Integer habilitaPercepcion);

    public Integer getAgentePercepcion();

    public void setAgentePercepcion(Integer agentePercepcion);
    
    public BigDecimal getTopeBoletaVentaSinDni();

    public void setTopeBoletaVentaSinDni(BigDecimal topeBoletaVentaSinDni);
    
    public BigDecimal getDsctoAgentePerceptor();

    public void setDsctoAgentePerceptor(BigDecimal dsctoAgentePerceptor);

    public BigDecimal getDsctoFacturaPerceptor();

    public void setDsctoFacturaPerceptor(BigDecimal dsctoFacturaPerceptor);

    public BigDecimal getDsctoBoletaPerceptor();

    public void setDsctoBoletaPerceptor(BigDecimal dsctoBoletaPerceptor);
    
    public String getCtaIgv();

    public void setCtaIgv(String ctaIgv);

    public String getCtaClientes();

    public void setCtaClientes(String ctaClientes);
    
    public Integer getMaxDetalleCot();

    public void setMaxDetalleCot(Integer maxDetalleCot);

    public Integer getMaxDetalleBol();

    public void setMaxDetalleBol(Integer maxDetalleBol);

    public Integer getMaxDetalleFac();

    public void setMaxDetalleFac(Integer maxDetalleFac);
    
    public Date getFechaServer();

    public void setFechaServer(Date fechaServer);
    
    public Integer getFrontera();

    public void setFrontera(Integer frontera);
    
    public Integer getAutoDespacho();

    public void setAutoDespacho(Integer autoDespacho);
}
