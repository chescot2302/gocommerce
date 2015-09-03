/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uivdentrada;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.AlmacenProxy;
import com.webgocommerce.client.beanproxy.ClienteProxy;
import com.webgocommerce.client.beanproxy.CorrelativoProxy;
import com.webgocommerce.client.beanproxy.DetalleVentaProxy;
import com.webgocommerce.client.beanproxy.DocumentoProxy;
import com.webgocommerce.client.beanproxy.FamiliaProxy;
import com.webgocommerce.client.beanproxy.ItemProxy;
import com.webgocommerce.client.beanproxy.ItemSerieProxy;
import com.webgocommerce.client.beanproxy.PrecioItemProxy;
import com.webgocommerce.client.requestfactory.ContextGestionFacturacion;
import com.webgocommerce.client.requestfactory.ContextMantenimientoAlmacen;
import com.webgocommerce.client.requestfactory.ContextMantenimientoCliente;
import com.webgocommerce.client.requestfactory.ContextMantenimientoCorrelativo;
import com.webgocommerce.client.requestfactory.ContextMantenimientoDocumento;
import com.webgocommerce.client.requestfactory.ContextMantenimientoFamilia;
import com.webgocommerce.client.requestfactory.ContextMantenimientoItem;
import com.webgocommerce.client.requestfactory.ContextMantenimientoPrecioItem;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.UIInfoTC;
import com.webgocommerce.client.view.uidespacho.UIDespachoImpl;
import com.webgocommerce.client.view.uihomevd.UIHomevd;
import com.webgocommerce.client.view.uiitemserie.UIItemSerie;
import com.webgocommerce.client.view.uisesion.UISesion;
import com.webgocommerce.client.view.uisesion.UISesionImpl;
import com.webgocommerce.client.view.uivdsalida.UIvdSalidaImpl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
public class UIvdEntradaImpl extends UIvdEntrada {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomevd uiHomevd;

    public UIvdEntradaImpl(UIHomevd uiHomevd) {
        this.uiHomevd = uiHomevd;
        loadFamilia();        
        loadDocVenta();
        //if (UISesionImpl.param.get("AUTODES").equals("1")) {
        if (chkDesAuto.getValue()) {
            if (uiGridDetalleVenta.grid.getColumnIndex(uiGridDetalleVenta.grid.getVer()) == -1) {
                uiGridDetalleVenta.grid.addColumn(uiGridDetalleVenta.grid.getVer());
            }
        } else {
            if (uiGridDetalleVenta.grid.getColumnIndex(uiGridDetalleVenta.grid.getVer()) != -1) {
                uiGridDetalleVenta.grid.removeColumn(uiGridDetalleVenta.grid.getVer());
            }
        }
    }

    @Override
    public void loadDocVenta() {
        ContextMantenimientoDocumento context = FACTORY.contextMantenimientoDocumento();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<DocumentoProxy>> request = context.listaDocVenta(keyPublic);
        request.fire(new Receiver<List<DocumentoProxy>>() {

            @Override
            public void onSuccess(List<DocumentoProxy> response) {
                if (response.size() > 0) {
                    uiInfoDoc.lstTipoDoc.setData(response);
                    loadCorrelativo();
                }
            }
        });
    }

    @Override
    public void loadCorrelativo() {
        ContextMantenimientoCorrelativo context = FACTORY.contextMantenimientoCorrelativo();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<CorrelativoProxy>> request = context.getCorrelativoActual(keyPublic, UISesion.beanUsuario.getId(), UISesion.beanUsuario.getIdPuntoEmision(), uiInfoDoc.lstTipoDoc.getSelectedItem().getId(),"N");
        request.fire(new Receiver<List<CorrelativoProxy>>() {

            @Override
            public void onSuccess(List<CorrelativoProxy> response) {
                if (response.size() > 0) {
                    uiInfoDoc.lstSerieCorre.setData(response);
                    uiInfoDoc.txtPreImpreso.setText(response.get(0).getPreimpreso());
                    setBeanCorrelativo(response.get(0));
                } else {
                    uiInfoDoc.lstSerieCorre.clear();
                    uiInfoDoc.txtPreImpreso.setText(null);
                    beanCorrelativo = null;
                }
            }
        });
    }
    
    @Override
    public void loadFamilia() {
        ContextMantenimientoFamilia context = FACTORY.contextMantenimientoFamilia();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<FamiliaProxy>> request = context.listar(keyPublic);
        request.fire(new Receiver<List<FamiliaProxy>>() {

            @Override
            public void onSuccess(List<FamiliaProxy> response) {
                if (response.size() > 0) {
                    uiSearch.lstFamilia.setData(response);
                    loadItem();
                }
            }
        });
    }

    @Override
    public void loadItem() {
        ContextMantenimientoItem context = FACTORY.contextMantenimientoItem();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<ItemProxy>> request = context.listarItemVenta(keyPublic,uiSearch.lstFamilia.getSelectedItem().getCodFam(),uiSearch.txtBuscar.getText());
        request.fire(new Receiver<List<ItemProxy>>() {

            @Override
            public void onSuccess(List<ItemProxy> response) {
                uiSearch.txtBuscar.setText(null);
                //uiStockItemAlm.getGridItem().getDataProvider().resetFilter();                
                uiStockItemAlm.getGridItem().setData(response);
                cleanAlmacen();
            }
        });
    }

    @Override
    public void loadAlmacen() {
        ContextMantenimientoAlmacen context = FACTORY.contextMantenimientoAlmacen();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        String codi = uiStockItemAlm.getGridItem().getSelectionModel().getSelectedObject().getId();
        //String ubica = uiStockItemAlm.getGridItem().getSelectionModel().getSelectedObject().getUbica();        
        String idLocalidad = UISesion.beanUsuario.getIdLocalidad();
        String idAlmacen = UISesion.beanUsuario.getIdAlmacen();
        if (!idLocalidad.isEmpty()) {
            Request<List<AlmacenProxy>> request = context.listarAlmXLocalidad(keyPublic, codi, idLocalidad, idAlmacen);
            request.fire(new Receiver<List<AlmacenProxy>>() {

                @Override
                public void onSuccess(List<AlmacenProxy> response) {
                    //uiStockItemAlm.getGridAlmacen().getDataProvider().resetFilter();                    
                    uiStockItemAlm.getGridAlmacen().setKeyboardSelectedRow(-1);
                    uiStockItemAlm.getGridAlmacen().getSelectionModel().clear();
                    uiStockItemAlm.getGridAlmacen().setData(response);
                    //uiStockItemAlm.getGridAlmacen().setFocus(true);
                }

            });

        } else {
            //Window.alert("Item sin almacenes configurados");
            Notification not = new Notification(Notification.ALERT, "Item sin almacenes configurados");
            not.showPopup();
            cleanAlmacen();
        }
    }

    @Override
    public void cleanAlmacen() {
        uiStockItemAlm.getGridAlmacen().getSelectionModel().clear();
        uiStockItemAlm.getGridAlmacen().setData(new ArrayList<AlmacenProxy>());
        //uiStockItemAlm.getGridAlmacen().getDataProvider().resulted.clear();
        uiStockItemAlm.getGridAlmacen().redraw();
    }

    @Override
    public void searchCliente() {
        ContextMantenimientoCliente context = FACTORY.contextMantenimientoCliente();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<ClienteProxy> request = context.getCliente(keyPublic, uiSearchCliente.txtRucFacturacion.getText());
        request.fire(new Receiver<ClienteProxy>() {

            @Override
            public void onSuccess(ClienteProxy response) {
                setBeanCliente(response);
            }

            @Override
            public void onFailure(ServerFailure error) {
                setBeanCliente(null);
                //Window.alert(error.getMessage());
                Notification not = new Notification(Notification.WARNING, error.getMessage());
                not.showPopup();
            }
        }
        );

    }

    public void searchCliente(String documento) {
        ContextMantenimientoCliente context = FACTORY.contextMantenimientoCliente();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<ClienteProxy> request = context.getCliente(keyPublic, documento);
        request.fire(new Receiver<ClienteProxy>() {

            @Override
            public void onSuccess(ClienteProxy response) {
                setBeanCliente(response);
            }

            @Override
            public void onFailure(ServerFailure error) {
                setBeanCliente(null);
                //Window.alert(error.getMessage());
                Notification not = new Notification(Notification.WARNING, error.getMessage());
                not.showPopup();
            }
        }
        );

    }

    @Override
    public void selectTipoDoc() {
        if (uiSearchCliente.txtRucFacturacion.getText().length() == 11 && (uiInfoDoc.lstTipoDoc.getSelectedIndex() == 1)) {
            uiInfoDoc.lstTipoDoc.setSelectedIndex(0);
        } else if (uiSearchCliente.txtRucFacturacion.getText().length() == 8 && uiInfoDoc.lstTipoDoc.getSelectedIndex() == 0) {
            uiInfoDoc.lstTipoDoc.setSelectedIndex(1);
        }
        loadCorrelativo();
    }

    @Override
    public void loadPreciosItem() {
        if (uiStockItemAlm.getGridItem().getDataProvider().hasFilter()) {
            beanItem = uiStockItemAlm.getGridItem().getDataProvider().resulted.get(uiStockItemAlm.getGridItem().getKeyboardSelectedRow());
            uiStockItemAlm.getGridItem().getSelectionModel().setSelected(beanItem, true);
        } else {
            beanItem = uiStockItemAlm.getGridItem().getData().get(uiStockItemAlm.getGridItem().getKeyboardSelectedRow());
            uiStockItemAlm.getGridItem().getSelectionModel().setSelected(beanItem, true);
        }
        ContextMantenimientoPrecioItem context = FACTORY.contextMantenimientoPrecioItem();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<PrecioItemProxy>> request = context.listaPrecioxItem(keyPublic, beanItem.getId(), uiInfoMoneda.lstMoneda.getValue(uiInfoMoneda.lstMoneda.getSelectedIndex()),"CANAL PERSONAS");
        request.fire(new Receiver<List<PrecioItemProxy>>() {

            @Override
            public void onSuccess(List<PrecioItemProxy> response) {
                uiControlVenta.txtPrecio.gridPrecioItem.getData().clear();
                uiControlVenta.txtPrecio.gridPrecioItem.setData(response);
            }
        });
    }

    /*@Override
     public void addItem() {    
     if (validDataPreAddItem()) {
     //ContextGestionFacturacion context = FACTORY.contextGestionFacturacion();
     //FACTORY.initialize(EVENTBUS);
     if (maxDetalleValid()) {
     if (existeCtaVentaItem()) {
     if (almacenCorrecto()) {
     if (existeStock()) {
     //if (esPrecioMayorACosto()) {
     ContextGestionFacturacion context = FACTORY.contextGestionFacturacion();
     FACTORY.initialize(EVENTBUS);
     int repeaItemt = uiGridDetalleVenta.grid.existe(this.beanItem.getId());
     if (repeaItemt != -1) {
     DetalleVentaProxy beanEdit = context.edit(uiGridDetalleVenta.grid.getData().get(repeaItemt));
     loadDetalleItem(beanEdit);
     uiGridDetalleVenta.grid.getData().set(repeaItemt, beanEdit);
     uiGridDetalleVenta.grid.getDataProvider().refresh();
     cleanDataPostItem();                                
     context.fire();
     } else {
     DetalleVentaProxy bean = context.create(DetalleVentaProxy.class);
     loadDetalleItem(bean);
     uiGridDetalleVenta.grid.addItem(bean);
     cleanDataPostItem();
     context.fire();
     //Window.alert("Tamano: "+uiGridDetalleVenta.grid.getData().size());
     }
     calcularMontoTotales();
     //}
     }
     }
     }
     }
     }
     }*/
    @Override
    public void addVentaDetalle() {
        //if (UISesionImpl.param.get("AUTODES").equals("1") && this.beanItem.getLserie() == 1) {
        if (chkDesAuto.getValue() && this.beanItem.getLserie() == 1) {
            UIItemSerie ui = new UIItemSerie(this);
            ui.setCountSelected(Integer.parseInt(uiControlVenta.txtCantidad.getText()));
            ui.setModo("ADD");
            ui.loadData(beanItem, beanAlmacen);
            ui.show();
        } else {
            addItem(null);
        }
    }

    @Override
    public void addItem(Set<ItemSerieProxy> series) {
        ContextGestionFacturacion context = FACTORY.contextGestionFacturacion();
        FACTORY.initialize(EVENTBUS);
        int repeaItemt = uiGridDetalleVenta.grid.existe(this.beanItem.getId());
        if (repeaItemt != -1) {
            DetalleVentaProxy beanEdit = context.edit(uiGridDetalleVenta.grid.getData().get(repeaItemt));
            //if (UISesionImpl.param.get("AUTODES").equals("1") && this.beanItem.getLserie() == 1 && series != null) {
            if (chkDesAuto.getValue() && this.beanItem.getLserie() == 1 && series != null) {
                double cantSeries = beanEdit.getSeries().size() + series.size();
                BigDecimal cantidad = BigDecimal.valueOf(cantSeries);
                beanEdit.setCantidad(cantidad);
                Iterator<ItemSerieProxy> iterador = series.iterator();
                while (iterador.hasNext()) {
                    ItemSerieProxy beanItemSerieOld = iterador.next();
                    ItemSerieProxy beanItemSerieNew = context.create(ItemSerieProxy.class);
                    beanItemSerieNew.setSerie(beanItemSerieOld.getSerie());
                    beanItemSerieNew.setTelefono(beanItemSerieOld.getTelefono());
                    beanEdit.getSeries().add(beanItemSerieNew);
                }
                uiControlVenta.txtCantidad.setText(cantidad.toString());
            }
            loadDetalleItem(beanEdit, "ADD");
            uiGridDetalleVenta.grid.getData().set(repeaItemt, beanEdit);
            uiGridDetalleVenta.grid.getDataProvider().refresh();
            cleanDataPostItem();
            context.fire();
        } else {
            DetalleVentaProxy bean = context.create(DetalleVentaProxy.class);
            loadDetalleItem(bean, "ADD");
            //if (UISesionImpl.param.get("AUTODES").equals("1") && this.beanItem.getLserie() == 1 && series != null) {
            if (chkDesAuto.getValue() && this.beanItem.getLserie() == 1 && series != null) {
                List<ItemSerieProxy> listaSeries = new ArrayList();
                Iterator<ItemSerieProxy> iterador = series.iterator();
                while (iterador.hasNext()) {
                    ItemSerieProxy beanItemSerieOld = iterador.next();
                    ItemSerieProxy beanItemSerieNew = context.create(ItemSerieProxy.class);
                    beanItemSerieNew.setCdocui(beanItemSerieOld.getCdocui());
                    beanItemSerieNew.setCdocuip(beanItemSerieOld.getCdocuip());
                    beanItemSerieNew.setCdocus(beanItemSerieOld.getCdocus());
                    beanItemSerieNew.setCodAlm(beanItemSerieOld.getCodAlm());
                    beanItemSerieNew.setCodi(beanItemSerieOld.getCodi());
                    beanItemSerieNew.setCodpro(beanItemSerieOld.getCodpro());
                    beanItemSerieNew.setFlag(beanItemSerieOld.getFlag());
                    beanItemSerieNew.setItem(beanItemSerieOld.getItem());
                    beanItemSerieNew.setNdocui(beanItemSerieOld.getNdocui());
                    beanItemSerieNew.setNdocuip(beanItemSerieOld.getNdocuip());
                    beanItemSerieNew.setNdocus(beanItemSerieOld.getNdocus());
                    beanItemSerieNew.setNdocusp(beanItemSerieOld.getNdocusp());
                    beanItemSerieNew.setSerie(beanItemSerieOld.getSerie());
                    beanItemSerieNew.setTelefono(beanItemSerieOld.getTelefono());
                    listaSeries.add(beanItemSerieNew);
                }
                bean.setSeries(listaSeries);
            }
            uiGridDetalleVenta.grid.addItem(bean);
            cleanDataPostItem();
            context.fire();
        }
        calcularMontoTotales();
    }

    @Override
    public boolean isValidData() {
        if (validDataPreAddItem()) {
            if (maxDetalleValid()) {
                if (existeCtaVentaItem()) {
                    if (almacenCorrecto()) {
                        if (existeStock()) {
                            //if (esPrecioMayorACosto()) {
                            return true;
                            //}
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void calcularMontoTotales() {
        resetMontoTotales();
        Iterator<DetalleVentaProxy> iterador = uiGridDetalleVenta.grid.getData().iterator();
        while (iterador.hasNext()) {
            DetalleVentaProxy bean = iterador.next();
            montoTotalAfecto = montoTotalAfecto.add(bean.getMontoAfecto());
            montoTotalNoAfecto = montoTotalNoAfecto.add(bean.getMontoNoAfecto());
            montoTotalIgv = montoTotalIgv.add(bean.getMontoIgv());
            montoTotalPercepcion = montoTotalPercepcion.add(bean.getMontoPercepcion());
            montoTotalaPagar = montoTotalaPagar.add(bean.getTotalNeto());
        }
        uiDocMonto.txtAfecto.setText(NumberFormat.getFormat("#########.##").format(montoTotalAfecto));
        uiDocMonto.txtNoAfecto.setText(NumberFormat.getFormat("#########.##").format(montoTotalNoAfecto));
        uiDocMonto.txtIgv.setText(NumberFormat.getFormat("#########.##").format(montoTotalIgv));
        uiDocMonto.txtPercepcion.setText(NumberFormat.getFormat("#########.##").format(montoTotalPercepcion));
        uiDocMonto.txtTotal.setText(NumberFormat.getFormat("#########.##").format(montoTotalaPagar));
    }

    @Override
    public void loadDetalleItem(DetalleVentaProxy bean, String modo) {
        if (modo.equalsIgnoreCase("ADD")) {
            Date fecha = new Date();
            bean.setIdDetalleVenta(this.beanItem.getId());
            bean.setFechaEmision(UISesion.beanInitParam.getFechaServer());
            BigDecimal tipoCambio = BigDecimal.valueOf(Double.parseDouble(UIInfoTC.txtTcVenta.getText()));
            bean.setTipoCambio(tipoCambio);
            bean.setIdMonedaItem(this.beanItem.getMoneda());
            bean.setAfectoIgv(this.beanItem.getAfectoIgv());
            bean.setIdItem(this.beanItem.getId());
            bean.setCodigo(this.beanItem.getCodigo());
            bean.setMarca(this.beanItem.getMarca());
            bean.setUnidadMedida(this.beanItem.getUnidad());
            bean.setDescripcion(this.beanItem.getDescripcion());
            bean.setCosto(this.beanItem.getCostoSoles());
            BigDecimal cantidad = BigDecimal.valueOf(Double.parseDouble(uiControlVenta.txtCantidad.getText()));
            bean.setCantidad(cantidad);
            bean.setPrecioPlan(this.beanPrecioItem.getPagoMensual());
            bean.setPrecioUnitario(this.beanPrecioItem.getValorVenta());
            bean.setPrecio(this.beanPrecioItem.getPrecioVenta());
            bean.setVigencia(this.beanPrecioItem.getVigencia());
            bean.setIdPrecioItem(this.beanPrecioItem.getId());
            bean.setIdLista(this.beanPrecioItem.getIdListaPrecio());
            bean.setPlan(this.beanPrecioItem.getDescripcion());
            bean.setEstadoAnulacion(1);
            BigDecimal totalVenta = cantidad.multiply(this.beanPrecioItem.getPrecioVenta());
            BigDecimal valValorVenta=BigDecimal.ZERO;
            if(/*UISesion.beanInitParam.getFrontera()==0*/btnAfectoIgv.isDown()){
            valValorVenta = totalVenta.divide(UISesionImpl.beanInitParam.getIgv().divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP).add(BigDecimal.ONE), 5, RoundingMode.HALF_UP);
            }else if(/*UISesion.beanInitParam.getFrontera()==1*/btnNoAfectoIgv.isDown()){
              valValorVenta=totalVenta;  
            }else{
                Window.alert("Error: Valor de frontera no comteplado");
                return;
            }                
            bean.setValorVenta(valValorVenta);
            bean.setTotalVenta(totalVenta);
            bean.setMontoAfecto(valValorVenta);
            bean.setPorcDescuento(BigDecimal.ZERO);
            bean.setTotalNeto(totalVenta);
            bean.setMontoPercepcion(BigDecimal.ZERO);
            bean.setIdAlmacen(this.beanAlmacen.getId());
            bean.setIdTipoVenta("01");//actualizar
            bean.setManejaStock(this.beanItem.getManejaStock());
            bean.setCtaVenta(this.beanItem.getCtaVenta());
            bean.setUnidadCom(this.beanItem.getUcom());
            bean.setAfectoPercepcion(this.beanItem.getPercepcion());
            BigDecimal montoIgv = totalVenta.subtract(valValorVenta);
            bean.setMontoIgv(montoIgv);
            bean.setMontoNoAfecto(BigDecimal.ZERO);
            bean.setVersion(fecha.getTime());
            bean.setLserie(this.beanItem.getLserie());
        } else if (modo.equalsIgnoreCase("REMOVE")) {
            BigDecimal cantidad = BigDecimal.valueOf(Double.parseDouble(uiControlVenta.txtCantidad.getText()));
            bean.setCantidad(cantidad);
            BigDecimal totalVenta = cantidad.multiply(bean.getPrecio());
            BigDecimal valValorVenta=BigDecimal.ZERO;
            if(/*UISesion.beanInitParam.getFrontera()==0*/btnAfectoIgv.isDown()){
            valValorVenta = totalVenta.divide(UISesionImpl.beanInitParam.getIgv().divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP).add(BigDecimal.ONE), 5, RoundingMode.HALF_UP);
            }else if(/*UISesion.beanInitParam.getFrontera()==1*/btnNoAfectoIgv.isDown()){
              valValorVenta=totalVenta;  
            }else{
                Window.alert("Error: Valor de frontera no comteplado");
                return;
            }
            bean.setValorVenta(valValorVenta);
            bean.setTotalVenta(totalVenta);
            bean.setMontoAfecto(valValorVenta);
            bean.setTotalNeto(totalVenta);
            BigDecimal montoIgv = totalVenta.subtract(valValorVenta);
            bean.setMontoIgv(montoIgv);
        }
    }

    @Override
    public void removeVentaDetalle() {
        DetalleVentaProxy bean = uiGridDetalleVenta.grid.getSelectionModel().getSelectedObject();
        //Window.alert("Num Series: " + bean.getSeries().size());
        if (bean != null) {
            //if (UISesionImpl.param.get("AUTODES").equals("1") && bean.getLserie() == 1 && bean.getSeries() != null) {
            if (chkDesAuto.getValue() && bean.getLserie() == 1 && bean.getSeries() != null) {
                UIItemSerie ui = new UIItemSerie(this);
                ui.setModo("REMOVE");
                ui.getGrid().setData(bean.getSeries());
                ui.setBeanDetalle(bean);
                ui.show();
            } else {
                removeItem(null, bean);
            }
        } else {
            //Window.alert("Seleccione un detalle de venta");
            Notification not = new Notification(Notification.ALERT, "Seleccione un detalle de venta");
            not.showPopup();
        }
    }

    @Override
    public void removeItem(List<ItemSerieProxy> series, DetalleVentaProxy bean) {
        //if (UISesionImpl.param.get("AUTODES").equals("1") && bean.getLserie() == 1 && series != null) {
        if (chkDesAuto.getValue() && bean.getLserie() == 1 && series != null) {
            int repeaItemt = uiGridDetalleVenta.grid.existe(bean.getIdItem());
            if (repeaItemt != -1) {
                double cantSeries = bean.getSeries().size() - series.size();
                BigDecimal cantidad = BigDecimal.valueOf(cantSeries);
                if (cantidad.compareTo(BigDecimal.ZERO) == 0) {
                    uiGridDetalleVenta.grid.removeItem(bean);
                    uiGridDetalleVenta.grid.actualizarGrid();
                    uiGridDetalleVenta.grid.redraw();
                    cleanDataPostItem();
                    calcularMontoTotales();
                } else if (cantidad.compareTo(BigDecimal.ZERO) == 1) {
                    ContextGestionFacturacion context = FACTORY.contextGestionFacturacion();
                    FACTORY.initialize(EVENTBUS);
                    DetalleVentaProxy beanEdit = context.edit(bean);
                    beanEdit.setCantidad(cantidad);
                    for (int i = 0; i < series.size(); i++) {
                        for (int j = 0; j < beanEdit.getSeries().size(); j++) {
                            if (series.get(i).getSerie().equalsIgnoreCase(beanEdit.getSeries().get(j).getSerie())) {
                                beanEdit.getSeries().remove(j);
                                break;
                            }
                        }
                    }
                    uiControlVenta.txtCantidad.setText(cantidad.toString());
                    loadDetalleItem(beanEdit, "REMOVE");
                    uiGridDetalleVenta.grid.getData().set(repeaItemt, beanEdit);
                    uiGridDetalleVenta.grid.getDataProvider().refresh();
                    cleanDataPostItem();
                    context.fire();
                    calcularMontoTotales();
                    uiGridDetalleVenta.grid.actualizarGrid();
                } else {
                    Notification not = new Notification(Notification.WARNING, "Error de sistema, cantidad negativa no aceptable");
                    not.showPopup();
                }
            } else {
                Notification not = new Notification(Notification.WARNING, "Error de sistema, Item supuestamente no existe");
                not.showPopup();
            }
        } else {
            uiGridDetalleVenta.grid.removeItem(bean);
            uiGridDetalleVenta.grid.actualizarGrid();
            uiGridDetalleVenta.grid.redraw();
            cleanDataPostItem();
            calcularMontoTotales();
        }

    }

    /*@Override
     public void removeItem() {
     DetalleVentaProxy bean = uiGridDetalleVenta.grid.getSelectionModel().getSelectedObject();
     if (bean != null) {            
     uiGridDetalleVenta.grid.removeItem(bean);
     uiGridDetalleVenta.grid.actualizarGrid();
     uiGridDetalleVenta.grid.redraw();
     cleanDataPostItem();
     calcularMontoTotales();
     } else {
     //Window.alert("Seleccione un detalle de venta");
     Notification not = new Notification(Notification.ALERT, "Seleccione un detalle de venta");
     not.showPopup();
     }
     }*/
    @Override
    public void showUIvdSalida() {
        if (beanCliente != null) {
            if (beanCorrelativo != null) {
                if (uiGridDetalleVenta.grid.getData().size() > 0) {
                    //if (UISesionImpl.param.get("AUTODES").equals("1")) {
                    if (chkDesAuto.getValue()) {
                        UIDespachoImpl ui = new UIDespachoImpl(this);
                        ui.show();
                    } else {
                        UIvdSalidaImpl dialog = new UIvdSalidaImpl(this);
                        dialog.show();
                    }
                } else {
                    Notification not = new Notification(Notification.ALERT, "Agregue al menos un item a facturar");
                    not.showPopup();
                }
            } else {
                Notification not = new Notification(Notification.ALERT, "Seleccione correlativo valido");
                not.showPopup();
            }
        } else {
            Notification not = new Notification(Notification.ALERT, "Seleccione Cliente");
            not.showPopup();
            uiSearchCliente.txtRucFacturacion.setFocus(true);
        }
    }

    @Override
    public void goUISearchAddCliente() {
        uiHomevd.getContainer().showWidget(1);
        //uiHomevd.getUiSeachAddCliente().getBtnBarBuscarCliente().setValue(Boolean.FALSE);
        uiHomevd.getUiSeachAddCliente().getBtnBarRegistrarCliente().setValue(Boolean.TRUE);
        uiHomevd.getUiSeachAddCliente().getToolBar().showSlide(0);
        uiHomevd.getUiSeachAddCliente().getUiBuscarCliente().grid.getDataProvider().resetFilter();
        uiHomevd.getUiSeachAddCliente().getFormCliente().scrollPanel.refresh();
    }

}
