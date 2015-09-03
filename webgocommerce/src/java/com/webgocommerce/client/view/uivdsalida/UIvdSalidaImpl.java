/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uivdsalida;

import com.gocommerce.server.model.beans.ItemSerie;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.CabeceraVentaProxy;
import com.webgocommerce.client.beanproxy.CondicionVentaProxy;
import com.webgocommerce.client.beanproxy.DetalleVentaProxy;
import com.webgocommerce.client.beanproxy.ItemSerieProxy;
import com.webgocommerce.client.beanproxy.TipoVentaProxy;
import com.webgocommerce.client.beanproxy.VendedorProxy;
import com.webgocommerce.client.requestfactory.ContextGestionFacturacion;
import com.webgocommerce.client.requestfactory.ContextMantenimientoCondicionVenta;
import com.webgocommerce.client.requestfactory.ContextMantenimientoTipoVenta;
import com.webgocommerce.client.requestfactory.ContextMantenimientoVendedor;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uisesion.UISesion;
import com.webgocommerce.client.view.uisesion.UISesionImpl;
import com.webgocommerce.client.view.uivdentrada.UIvdEntradaImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIvdSalidaImpl extends UIvdSalida {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIvdEntradaImpl formParent;

    public UIvdSalidaImpl(UIvdEntradaImpl formParent) {
        this.formParent = formParent;
        loadField();
        loadVendedor();
        loadTipoVenta();
        loadCondicionVenta();
    }

    @Override
    public void loadField() {
        //uiSearchCliente.txtRucFacturacion.setText(formParent.getBeanCliente().getDni());
        if (formParent.getBeanCorrelativo().getIdDocumento().equalsIgnoreCase("01") || formParent.getBeanCorrelativo().getIdDocumento().equalsIgnoreCase("31")) {
            uiSearchCliente.txtRucFacturacion.setText(formParent.getBeanCliente().getRuc());
            uiSearchCliente.txtDescripcionFacturacion.setText(formParent.getBeanCliente().getNombres());
        } else {
            uiSearchCliente.txtRucFacturacion.setText(formParent.getBeanCliente().getDni());
            uiSearchCliente.txtDescripcionFacturacion.setText(formParent.getBeanCliente().getNombres());
        }
        uiInfoDoc.lstTipoDoc.addItem(formParent.getBeanCorrelativo().getNombreDocumento());
        uiInfoDoc.lstSerieCorre.addItem(formParent.getBeanCorrelativo().getSerie());
        uiInfoDoc.txtPreImpreso.setText(formParent.getBeanCorrelativo().getPreimpreso());
        uiInfoMoneda.lstMoneda.setSelectedIndex(formParent.getUiInfoMoneda().lstMoneda.getSelectedIndex());
        txtAcobrar.setText(NumberFormat.getFormat("#########.##").format(formParent.getMontoTotalaPagar()));
        txtRecibido.setText(recibido.toString());
        txtVuelto.setText(NumberFormat.getFormat("#########.##").format(vuelto));
    }

    @Override
    public void loadVendedor() {
        ContextMantenimientoVendedor context = FACTORY.contextMantenimientoVendedor();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<VendedorProxy>> request = context.listarVendedorXpto(keyPublic,UISesion.beanUsuario.getIdPuntoEmision());
        request.fire(new Receiver<List<VendedorProxy>>() {

            @Override
            public void onSuccess(List<VendedorProxy> response) {
                lstVendedor.setData(response);
            }
        });
    }

    @Override
    public void loadCondicionVenta() {
        ContextMantenimientoCondicionVenta context = FACTORY.contextMantenimientoCondicionVenta();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<CondicionVentaProxy>> request = context.listarxVigenciaCredito(keyPublic,formParent.getBeanCliente().getVigenciaLineaCredito());
        request.fire(new Receiver<List<CondicionVentaProxy>>() {

            @Override
            public void onSuccess(List<CondicionVentaProxy> response) {
                lstCondVenta.setData(response);
            }
        });
    }

    @Override
    public void loadTipoVenta() {
        ContextMantenimientoTipoVenta context = FACTORY.contextMantenimientoTipoVenta();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<TipoVentaProxy>> request = context.listar(keyPublic);
        request.fire(new Receiver<List<TipoVentaProxy>>() {

            @Override
            public void onSuccess(List<TipoVentaProxy> response) {
                lstTipoVenta.setData(response);
            }
        });
    }

    @Override
    public void calcularVuelto() {
        recibido = BigDecimal.valueOf(Double.parseDouble(txtRecibido.getText()));
        BigDecimal aCobrar = formParent.getMontoTotalaPagar();
        vuelto = recibido.subtract(aCobrar);
        txtVuelto.setText(NumberFormat.getFormat("#########.##").format(vuelto));
        if (vuelto.compareTo(BigDecimal.ZERO) == -1) {
            txtVuelto.getElement().getStyle().setBackgroundColor("red");
            txtVuelto.getElement().getStyle().setColor("white");
        } else {
            txtVuelto.getElement().getStyle().setBackgroundColor("green");
            txtVuelto.getElement().getStyle().setColor("white");
        }
    }

    @Override
    public void generarDocumento() {
        if (isValidData()) {
            ContextGestionFacturacion context = FACTORY.contextGestionFacturacion();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            List<DetalleVentaProxy> lista = new ArrayList<DetalleVentaProxy>();
            List<ItemSerieProxy> listaSeries=new ArrayList<ItemSerieProxy>();
            Iterator<DetalleVentaProxy> iterador = formParent.getUiGridDetalleVenta().grid.getData().iterator();
            int contador = 0;
            while (iterador.hasNext()) {
                contador = contador + 1;
                DetalleVentaProxy beanNew = context.create(DetalleVentaProxy.class);
                DetalleVentaProxy bean = iterador.next();
                //if (UISesionImpl.param.get("AUTODES").equals("1") && bean.getSeries()!=null && bean.getSeries().size()>0) {
                beanNew.setLserie(0);
                if (formParent.getChkDesAuto().getValue() && bean.getSeries()!=null && bean.getSeries().size()>0) {
                    beanNew.setLserie(bean.getLserie());
                    Iterator<ItemSerieProxy> iter=bean.getSeries().iterator();
                    while(iter.hasNext()){
                        ItemSerieProxy beanSerie=iter.next();
                        ItemSerieProxy beanSerieNew=context.create(ItemSerieProxy.class);  
                        beanSerieNew.setCdocu(formParent.getBeanCorrelativo().getIdDocumento());
                        beanSerieNew.setNdocu(formParent.getBeanCorrelativo().getNroInicio());
                        beanSerieNew.setCdocui(beanSerie.getCdocui());
                        beanSerieNew.setCdocuip(beanSerie.getCdocuip());
                        beanSerieNew.setCdocus(formParent.getBeanCorreDespacho().getIdDocumento());
                        beanSerieNew.setCodAlm(beanSerie.getCodAlm());
                        beanSerieNew.setCodi(beanSerie.getCodi());
                        beanSerieNew.setCodpro(beanSerie.getCodpro());
                        beanSerieNew.setFlag(beanSerie.getFlag());
                        beanSerieNew.setItem(beanSerie.getItem());
                        beanSerieNew.setNdocui(beanSerie.getNdocui());
                        beanSerieNew.setNdocuip(beanSerie.getNdocuip());
                        beanSerieNew.setNdocus(formParent.getBeanCorreDespacho().getNroInicio());
                        beanSerieNew.setNdocusp(beanSerie.getNdocusp());
                        beanSerieNew.setSerie(beanSerie.getSerie());                        
                        beanSerieNew.setTelefono(beanSerie.getTelefono());
                        listaSeries.add(beanSerieNew);
                    }
                }
                beanNew.setAfectoIgv(bean.getAfectoIgv());
                beanNew.setAfectoPercepcion(bean.getAfectoPercepcion());
                beanNew.setCantidad(bean.getCantidad());
                beanNew.setCodigo(bean.getCodigo());
                beanNew.setCorrelativo(formParent.getBeanCorrelativo().getNroInicio());
                beanNew.setCosto(bean.getCosto());
                beanNew.setCtaVenta(bean.getCtaVenta());
                beanNew.setDescripcion(bean.getDescripcion());
                beanNew.setFechaEmision(bean.getFechaEmision());
                beanNew.setIdAlmacen(bean.getIdAlmacen());
                beanNew.setIdCliente(formParent.getBeanCliente().getId());
                beanNew.setIdDetalleVenta(bean.getIdDetalleVenta());
                beanNew.setIdItem(bean.getIdItem());
                beanNew.setIdMonedaItem(bean.getIdMonedaItem());
                beanNew.setIdTipoVenta(lstTipoVenta.getSelectedItem().getIdTipoVenta());
                beanNew.setManejaStock(bean.getManejaStock());
                beanNew.setMarca(bean.getMarca());
                beanNew.setMoneda(formParent.getUiInfoMoneda().lstMoneda.getValue(formParent.getUiInfoMoneda().lstMoneda.getSelectedIndex()));
                beanNew.setMontoAfecto(bean.getMontoAfecto());
                beanNew.setMontoIgv(bean.getMontoIgv());
                beanNew.setMontoNoAfecto(bean.getMontoNoAfecto());
                beanNew.setMontoPercepcion(bean.getMontoPercepcion());
                beanNew.setPorcDescuento(bean.getPorcDescuento());
                beanNew.setPrecio(bean.getPrecio());
                beanNew.setPrecioPlan(bean.getPrecioPlan());
                beanNew.setPrecioUnitario(bean.getPrecioUnitario());
                beanNew.setSecuencia(contador);
                beanNew.setTipoCambio(bean.getTipoCambio());
                beanNew.setTipoDoc(formParent.getBeanCorrelativo().getIdDocumento());
                beanNew.setTipoFacturacion(formParent.getBeanCorrelativo().getIdDocumento().equalsIgnoreCase("01") ? "1" : formParent.getBeanCorrelativo().getIdDocumento().equalsIgnoreCase("03") ? "2" : "");
                beanNew.setTotalNeto(bean.getTotalNeto());
                beanNew.setTotalVenta(bean.getTotalVenta());
                beanNew.setUnidadCom(bean.getUnidadCom());
                beanNew.setUnidadMedida(bean.getUnidadMedida());
                beanNew.setValorVenta(bean.getValorVenta());
                beanNew.setVersion(bean.getVersion());
                beanNew.setIdVendedor(lstVendedor.getSelectedItem().getIdVendedor());
                beanNew.setIdCondicionVenta(lstCondVenta.getSelectedItem().getIdCondicionVenta());
                beanNew.setNomCliente(formParent.getBeanCliente().getNombres());
                beanNew.setRucCliente(formParent.getBeanCliente().getRuc());
                beanNew.setIdPrecioItem(bean.getIdPrecioItem());
                beanNew.setIdLista(bean.getIdLista());
                beanNew.setPrecioPlan(bean.getPrecioPlan());
                beanNew.setVigencia(bean.getVigencia());                
                beanNew.setCategoriaVenta(lstCategoriaVenta.getValue(lstCategoriaVenta.getSelectedIndex()));
                beanNew.setUsuarioReg(UISesion.beanUsuario.getNombres());
                lista.add(beanNew);                
            }
            DetalleVentaProxy bean = lista.get(0);
            List<CabeceraVentaProxy> listaHead = new ArrayList<CabeceraVentaProxy>();
            CabeceraVentaProxy beanHead = context.create(CabeceraVentaProxy.class);
            beanHead.setFechaEmision(bean.getFechaEmision());
            beanHead.setTipoDoc(bean.getTipoDoc());
            beanHead.setCorrelativo(bean.getCorrelativo());
            beanHead.setTipoFacturacion(bean.getTipoFacturacion());
            beanHead.setIdCliente(bean.getIdCliente());
            beanHead.setNombreCliente(formParent.getBeanCliente().getNombres());
            beanHead.setRucCliente(formParent.getBeanCliente().getRuc());
            beanHead.setDireccionCliente(formParent.getBeanCliente().getDireccionEntrega());
            beanHead.setIdCondicionVenta(bean.getIdCondicionVenta());
            beanHead.setIdTipoVenta(bean.getIdTipoVenta());
            beanHead.setDiasCredito(lstCondVenta.getSelectedItem().getDiasVencimiento());
            Long valVencimiento = bean.getFechaEmision().getTime() + (lstCondVenta.getSelectedItem().getDiasVencimiento() * 86400000);
            beanHead.setFechaVencimiento(new Date(valVencimiento));
            beanHead.setIdMoneda(bean.getMoneda());
            beanHead.setTipoCambio(bean.getTipoCambio());
            beanHead.setTotalAfecto(formParent.getMontoTotalAfecto());
            beanHead.setTotalIgv(formParent.getMontoTotalIgv());
            beanHead.setTotalNeto(formParent.getMontoTotalaPagar());
            beanHead.setIdVendedor(bean.getIdVendedor());
            beanHead.setIdAlmacen(bean.getIdAlmacen());
            beanHead.setIdPtoEmision(formParent.getBeanCorrelativo().getIdPuntoEmision());
            beanHead.setIdTipoVenta(bean.getIdTipoVenta());
            beanHead.setTcMercado(UISesion.beanTipoCambio.getTcMercado());
            beanHead.setVersion(bean.getVersion());
            beanHead.setCtaIgv(UISesion.beanInitParam.getCtaIgv());
            beanHead.setCtaCliente(UISesion.beanInitParam.getCtaClientes());
            beanHead.setFrontera(formParent.getBtnAfectoIgv().isDown()?0:formParent.getBtnNoAfectoIgv().isDown()?1:-1/*UISesion.beanInitParam.getFrontera()*/);
            beanHead.setUsuarioReg(UISesion.beanUsuario.getNombres());
            //if (UISesionImpl.param.get("AUTODES").equals("1")) {
            if (formParent.getChkDesAuto().getValue()) {
                beanHead.setIdTipoDocRef(formParent.getBeanCorreDespacho().getIdDocumento());
                beanHead.setCorrelativoRef(formParent.getBeanCorreDespacho().getNroInicio());
            }
            listaHead.add(beanHead);
            Request<String> request = context.generarDocumento(keyPublic, lista, listaHead,listaSeries,formParent.getChkDesAuto().getValue()?1:0,Integer.parseInt(UISesionImpl.param.get("GAF")),dtFechaEmsion.getValue(),UISesion.beanUsuario.getIdPuntoEmision());
            request.fire(new Receiver<String>() {

                @Override
                public void onSuccess(String response) {
                    if (response.equalsIgnoreCase("CORRECTO")) {
                        cleanForm();   
                        cancelarOperacion();
                        formParent.cleanFormData();  
                        formParent.loadCorrelativo();
                        //Window.alert("Documento de venta generado correctamente");                        
                        Notification not=new Notification(Notification.INFORMATION,"Documento de venta generado correctamente");
                        not.showPopup();
                    } else {
                        //Window.alert("Error al generar el documento de venta");
                        Notification not=new Notification(Notification.ALERT,response);
                        not.showPopup();
                    }

                }

            });
        }
    }

}
