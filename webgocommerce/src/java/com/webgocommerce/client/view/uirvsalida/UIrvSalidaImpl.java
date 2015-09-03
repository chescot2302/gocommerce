/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uirvsalida;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.CabeceraVentaProxy;
import com.webgocommerce.client.beanproxy.DetalleVentaProxy;
import com.webgocommerce.client.beanproxy.ItemPlanProxy;
import com.webgocommerce.client.beanproxy.MesaProxy;
import com.webgocommerce.client.beanproxy.VendedorProxy;
import com.webgocommerce.client.requestfactory.ContextGestionFacturacion;
import com.webgocommerce.client.requestfactory.ContextMantenimientoMesa;
import com.webgocommerce.client.requestfactory.ContextMantenimientoVendedor;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uirventrada.UIrvEntradaImpl;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIrvSalidaImpl extends UIrvSalida {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIrvEntradaImpl formParent;

    public UIrvSalidaImpl(UIrvEntradaImpl formParent) {
        this.formParent = formParent;
        loadField();
        loadVendedor();
    }

    @Override
    public void loadField() {
        uiSearchCliente.txtRucFacturacion.setText(formParent.getBeanCliente().getRuc());
        uiSearchCliente.txtDescripcionFacturacion.setText(formParent.getBeanCliente().getNombres());
        uiInfoDoc.lstTipoDoc.addItem(formParent.getBeanCorrelativo().getNombreDocumento());
        uiInfoDoc.lstSerieCorre.addItem(formParent.getBeanCorrelativo().getSerie());
        uiInfoDoc.txtPreImpreso.setText(formParent.getBeanCorrelativo().getPreimpreso());
        uiInfoMoneda.lstMoneda.setSelectedIndex(formParent.getUiInfoMoneda().lstMoneda.getSelectedIndex());
        txtPlan.setText(NumberFormat.getFormat("#########.##").format(formParent.getMontoTotalaPagar()));
        txtEquipos.setText(NumberFormat.getFormat("#########.##").format(formParent.getMontoTotalEquipos()));
    }

    @Override
    public void loadVendedor() {
        ContextMantenimientoVendedor context = FACTORY.contextMantenimientoVendedor();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<VendedorProxy>> request = context.listarVendedorXptoCe(keyPublic, UISesion.beanUsuario.getIdPuntoEmision());
        request.fire(new Receiver<List<VendedorProxy>>() {

            @Override
            public void onSuccess(List<VendedorProxy> response) {
                lstVendedor.setData(response);
                getMesa();
            }
        });
    }

    @Override
    public void generarDocumento() {
        if (isValidData()) {
            ContextGestionFacturacion context = FACTORY.contextGestionFacturacion();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            List<DetalleVentaProxy> lista = new ArrayList<DetalleVentaProxy>();
            List<ItemPlanProxy> listaEquipos = new ArrayList<ItemPlanProxy>();
            Iterator<DetalleVentaProxy> iterador = formParent.getUiGridDetalleVenta().grid.getData().iterator();
            int contador = 0;
            Integer contarPlan=0;
            while (iterador.hasNext()) {
                contador = contador + 1;
                DetalleVentaProxy beanNew = context.create(DetalleVentaProxy.class);
                DetalleVentaProxy bean = iterador.next();
                Iterator<ItemPlanProxy> iter = bean.getEquipos().iterator();
                while (iter.hasNext()) {
                    ItemPlanProxy beanItemPlanOld = iter.next();
                    ItemPlanProxy beanItemPlanNew = context.create(ItemPlanProxy.class);
                    beanItemPlanNew.setIdEquipPlan(contarPlan);
                    contarPlan=contarPlan+1;
                    beanItemPlanNew.setCantidad(beanItemPlanOld.getCantidad());
                    beanItemPlanNew.setDescripcion(beanItemPlanOld.getDescripcion());
                    beanItemPlanNew.setIdItem(beanItemPlanOld.getIdItem());
                    beanItemPlanNew.setIdItemPlan(beanItemPlanOld.getIdItemPlan());
                    beanItemPlanNew.setIdLista(beanItemPlanOld.getIdLista());
                    beanItemPlanNew.setPrecio(beanItemPlanOld.getPrecio());
                    beanItemPlanNew.setTotal(beanItemPlanOld.getTotal());
                    beanItemPlanNew.setcDocu(formParent.getBeanCorrelativo().getIdDocumento());
                    beanItemPlanNew.setnDocu(formParent.getBeanCorrelativo().getNroInicio());
                    listaEquipos.add(beanItemPlanNew);
                }
                beanNew.setLserie(0);
                beanNew.setAfectoIgv(bean.getAfectoIgv());
                beanNew.setAfectoPercepcion(bean.getAfectoPercepcion());
                beanNew.setCantidad(bean.getCantidad());
                beanNew.setCodigo(bean.getCodigo());
                beanNew.setCorrelativo(formParent.getBeanCorrelativo().getNroInicio());
                beanNew.setCosto(bean.getCosto());
                beanNew.setDescripcion(bean.getDescripcion());
                beanNew.setFechaEmision(bean.getFechaEmision());
                beanNew.setIdCliente(formParent.getBeanCliente().getId());
                beanNew.setIdDetalleVenta(bean.getIdDetalleVenta());
                beanNew.setIdItem(bean.getIdItem());
                beanNew.setIdMonedaItem(bean.getIdMonedaItem());
                beanNew.setManejaStock("N");
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
            beanHead.setIdMoneda(bean.getMoneda());
            beanHead.setTipoCambio(bean.getTipoCambio());
            beanHead.setTotalAfecto(formParent.getMontoTotalAfecto());
            beanHead.setTotalIgv(formParent.getMontoTotalIgv());
            beanHead.setTotalNeto(formParent.getMontoTotalaPagar());
            beanHead.setTotalPlan(formParent.getMontoTotalPlan());
            beanHead.setTotalEquipos(formParent.getMontoTotalEquipos());
            beanHead.setIdVendedor(bean.getIdVendedor());
            beanHead.setIdPtoEmision(formParent.getBeanCorrelativo().getIdPuntoEmision());
            beanHead.setTcMercado(UISesion.beanTipoCambio.getTcMercado());
            beanHead.setVersion(bean.getVersion());
            beanHead.setFrontera(formParent.getBtnAfectoIgv().isDown() ? 0 : formParent.getBtnNoAfectoIgv().isDown() ? 1 : -1/*UISesion.beanInitParam.getFrontera()*/);
            beanHead.setUsuarioReg(UISesion.beanUsuario.getNombres());
            beanHead.setIdSuperVen(beanMesa.getIdSuperVen());
            beanHead.setIdMesa(beanMesa.getIdMesa());
            beanHead.setIdCoordinador(beanMesa.getIdCoordinador());
            beanHead.setIdGerenteZonal(beanMesa.getIdGerenteZonal());
            beanHead.setIdSupervisor(beanMesa.getIdSupervisor());
            beanHead.setIdSucursal(beanMesa.getIdSucursal());
            beanHead.setCodProy(txtProy.getText());
            beanHead.setEstadoActual(lstEstadoActual.getItemText(lstEstadoActual.getSelectedIndex()));
            listaHead.add(beanHead);
            Request<String> request = context.registrarVentaCe(keyPublic, lista, listaHead,listaEquipos,dtFechaEmsion.getValue(), UISesion.beanUsuario.getIdPuntoEmision());
            request.fire(new Receiver<String>() {

                @Override
                public void onSuccess(String response) {
                    if (response.equalsIgnoreCase("CORRECTO")) {
                        cleanForm();
                        cancelarOperacion();
                        formParent.cleanFormData();
                        formParent.loadCorrelativo();
                        Notification not = new Notification(Notification.INFORMATION, "Documento de Altas generado correctamente");
                        not.showPopup();
                    } else {
                        Notification not = new Notification(Notification.ALERT, response);
                        not.showPopup();
                    }

                }

            });
        }
    }

    @Override
    public void getMesa() {
        ContextMantenimientoMesa context = FACTORY.contextMantenimientoMesa();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<MesaProxy> request = context.getMesaxVendedor(keyPublic, lstVendedor.getSelectedItem().getIdVendedor());
        request.fire(new Receiver<MesaProxy>() {

            @Override
            public void onSuccess(MesaProxy response) {
                beanMesa = response;
                txtSupervisor.setText(beanMesa.getNomSupervisor());
            }

            @Override
            public void onFailure(ServerFailure error) {
                Notification not = new Notification(Notification.WARNING, error.getMessage());
                not.showPopup();
            }
        });
    }

}
