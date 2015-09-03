/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uidocventa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.CabeceraVentaProxy;
import com.webgocommerce.client.beanproxy.CorrelativoProxy;
import com.webgocommerce.client.beanproxy.DetalleVentaProxy;
import com.webgocommerce.client.requestfactory.ContextGestionFacturacion;
import com.webgocommerce.client.requestfactory.ContextMantenimientoCorrelativo;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.view.uiestproy.UIEstProyImpl;
import com.webgocommerce.client.view.uihomedocventa.UIHomeRegVentaCe;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIRegVentaCeImpl extends UIDocVenta {

    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeRegVentaCe uiHomeDocVenta;

    public UIRegVentaCeImpl(UIHomeRegVentaCe uiHomeDocVenta) {
        this.uiHomeDocVenta = uiHomeDocVenta;
        loadSeriesUserVenta();
        gridUpdate();
        btnCambiarEstado.setVisible(true);
    }

    public void gridUpdate() {
        grid.removeColumn(grid.nomCondicionVenta);
        grid.removeColumn(grid.fechaVencimiento);
        grid.removeColumn(grid.tipoVenta);
        grid.addColumn(grid.nomMesa, "MESA");
        grid.addColumn(grid.auditor, "AUDITOR");
        grid.addColumn(grid.dniSupervisor, "DNI SUPERVISOR");
        grid.addColumn(grid.nomSupervisor, "SUPERVISOR");
        grid.addColumn(grid.dniGerenteZonal, "DNI GER. ZONAL");
        grid.addColumn(grid.nomGerenteZonal, "GERENTE ZONAL");
        grid.addColumn(grid.dniCoordinador, "DNI COODINADOR");
        grid.addColumn(grid.nomCoordinador, "COORDINADOR");
        grid.addColumn(grid.observacion, "OBSERVACION");
        grid.setColumnWidth(grid.nomMesa, 10, com.google.gwt.dom.client.Style.Unit.EM);
        grid.setColumnWidth(grid.auditor, 25, com.google.gwt.dom.client.Style.Unit.EM);
        grid.setColumnWidth(grid.dniSupervisor, 10, com.google.gwt.dom.client.Style.Unit.EM);
        grid.setColumnWidth(grid.nomSupervisor, 25, com.google.gwt.dom.client.Style.Unit.EM);
        grid.setColumnWidth(grid.dniGerenteZonal, 10, com.google.gwt.dom.client.Style.Unit.EM);
        grid.setColumnWidth(grid.nomGerenteZonal, 25, com.google.gwt.dom.client.Style.Unit.EM);
        grid.setColumnWidth(grid.dniCoordinador, 10, com.google.gwt.dom.client.Style.Unit.EM);
        grid.setColumnWidth(grid.nomCoordinador, 25, com.google.gwt.dom.client.Style.Unit.EM);
        grid.setColumnWidth(grid.observacion, 100, com.google.gwt.dom.client.Style.Unit.EM);

        grid.getColumn(grid.getColumnIndex(grid.nomMesa)).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        grid.getColumn(grid.getColumnIndex(grid.auditor)).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        grid.getColumn(grid.getColumnIndex(grid.dniSupervisor)).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        grid.getColumn(grid.getColumnIndex(grid.nomSupervisor)).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        grid.getColumn(grid.getColumnIndex(grid.dniGerenteZonal)).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        grid.getColumn(grid.getColumnIndex(grid.nomGerenteZonal)).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        grid.getColumn(grid.getColumnIndex(grid.dniCoordinador)).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        grid.getColumn(grid.getColumnIndex(grid.nomCoordinador)).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        grid.getColumn(grid.getColumnIndex(grid.observacion)).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

        grid2.removeColumn(grid2.codAlm);
        grid2.removeColumn(grid2.almacen);
        grid2.removeColumn(grid2.ver);
    }

    @Override
    public void loadSeriesUserVenta() {
        ContextMantenimientoCorrelativo context = FACTORY.contextMantenimientoCorrelativo();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<CorrelativoProxy>> request = context.getSerieUserVentaCe(keyPublic, UISesion.beanUsuario.getId(), UISesion.beanUsuario.getIdPuntoEmision());
        request.fire(new Receiver<List<CorrelativoProxy>>() {

            @Override
            public void onSuccess(List<CorrelativoProxy> response) {
                if (response.size() > 0) {
                    String series = "";
                    for (int i = 0; i < response.size(); i++) {
                        series = series + "," + response.get(i).getDocSerie();
                    }
                    series = series.substring(1);
                    docSeries = series;
                    loadTable();
                } else {
                    Notification not = new Notification(Notification.ALERT, "Usuario no administra series de venta");
                    not.showPopup();
                }

            }
        });
    }

    @Override
    public void loadTable() {
        popup.showPopup();
        ContextGestionFacturacion context = FACTORY.contextGestionFacturacion();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        String rucCliente = "";
        String descCliente = "";
        String correlativo = "";
        if (lstFiltro.getSelectedIndex() == 0) {
            rucCliente = txtBuscar.getText();
        } else if (lstFiltro.getSelectedIndex() == 1) {
            descCliente = txtBuscar.getText();
        } else if (lstFiltro.getSelectedIndex() == 2) {
            correlativo = txtBuscar.getText();
        }
        Request<List<CabeceraVentaProxy>> request = context.listarVentasCe(keyPublic, boxDateIni.getValue(), boxDateFin.getValue(), rucCliente, descCliente, docSeries, correlativo, chkSoloAnulados.getValue() ? "A" : "S", chkExcluirFechas.getValue() ? "S" : "N");
        request.fire(new Receiver<List<CabeceraVentaProxy>>() {

            @Override
            public void onSuccess(List<CabeceraVentaProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                grid2.getSelectionModel().clear();
                grid2.setData(new ArrayList<DetalleVentaProxy>());
                //lstBdEmpresa.setData(grid.getData());
                popup.hidePopup();
                txtBuscar.textBox.setText(null);
                grid.getDataProvider().resetFilter();
            }

            @Override
            public void onFailure(ServerFailure error) {
                popup.hidePopup();
                //Window.alert(error.getMessage());                    
                Notification not = new Notification(Notification.WARNING, error.getMessage());
                not.showPopup();
            }
        });
    }

    @Override
    public void showUIOper4() {
        CabeceraVentaProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            lblTipoDoc.setText(bean.getNomDoc());
            lblSerie.setText(bean.getSerie());
            lblPreImpreso.setText(bean.getPreimpreso());
            popup.showPopup();
            ContextGestionFacturacion context = FACTORY.contextGestionFacturacion();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            Request<List<DetalleVentaProxy>> request = context.getDetalleVentaCe(keyPublic, bean.getTipoDoc(), bean.getCorrelativo());
            request.fire(new Receiver<List<DetalleVentaProxy>>() {

                @Override
                public void onSuccess(List<DetalleVentaProxy> response) {
                    grid2.getSelectionModel().clear();
                    grid2.setData(response);
                    popup.hidePopup();
                }

                @Override
                public void onFailure(ServerFailure error) {
                    popup.hidePopup();
                    //Window.alert(error.getMessage());                    
                    Notification not = new Notification(Notification.WARNING, error.getMessage());
                    not.showPopup();
                }
            });
        } else {
            //Window.alert("Seleccione un registro de la tabla\ncon estado activo");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void exportarData() {
        int row = 0;
        List<CabeceraVentaProxy> lista;
        if (!grid.getDataProvider().hasFilter()) {
            row = grid.getData().size();
            lista = grid.getData();
        } else {
            row = grid.getDataProvider().resulted.size();
            lista = grid.getDataProvider().resulted;
        }
        if (row == 0) {
            Notification not = new Notification(Notification.ALERT, "Grid sin datos");
            not.showPopup();
            return;
        }
        FlexTable flex = new FlexTable();
        flex.setText(0, 0, "FECHA");
        flex.setText(0, 1, "ESTADO DEL PROYECTO");
        flex.setText(0, 2, "SEC/SOT/PROYECTO");
        flex.setText(0, 3, "TIPO DOC");
        flex.setText(0, 4, "SERIE");
        flex.setText(0, 5, "PREIMPRESO");
        flex.setText(0, 6, "RUC");
        flex.setText(0, 7, "CLIENTE");
        flex.setText(0, 8, "MONEDA");
        flex.setText(0, 9, "TIPO CAMBIO");
        flex.setText(0, 10, "TOTAL AFECTO");
        flex.setText(0, 11, "TOTAL IGV");
        flex.setText(0, 12, "TOTAL NETO");
        flex.setText(0, 13, "TOTAL PLAN");
        flex.setText(0, 14, "PTO. EMISION");
        flex.setText(0, 15, "MESA");
        flex.setText(0, 16, "DNI VENDEDOR");
        flex.setText(0, 17, "VENDEDOR");
        flex.setText(0, 18, "AUDITOR");
        flex.setText(0, 19, "DNI SUPERVISOR");
        flex.setText(0, 20, "SUPERVISOR");
        flex.setText(0, 21, "DNI GER. ZONAL");
        flex.setText(0, 22, "GERENTE ZONAL");
        flex.setText(0, 23, "DNI COORDINADOR");
        flex.setText(0, 24, "COORDINADOR");
        flex.setText(0, 25, "OBSERVACION");
        for (int j = 0; j < 26; j++) {
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }
        int fila = 1;
        for (int i = 0; i < row; i++) {
            CabeceraVentaProxy bean = lista.get(i);
            flex.setText(fila, 0, dateFormat.format(bean.getFechaEmision()));
            flex.setText(fila, 1, bean.getEstadoActual());
            flex.setText(fila, 2, bean.getCodProy());
            flex.setText(fila, 3, bean.getNomDoc());
            flex.setText(fila, 4, bean.getSerie());
            flex.setText(fila, 5, bean.getPreimpreso());
            flex.setText(fila, 6, bean.getRucCliente());
            flex.setText(fila, 7, bean.getNombreCliente());
            flex.setText(fila, 8, bean.getIdMoneda());
            flex.setText(fila, 9, bean.getTipoCambio().toString());
            flex.setText(fila, 10, bean.getTotalAfecto().toString());
            flex.setText(fila, 11, bean.getTotalIgv().toString());
            flex.setText(fila, 12, bean.getTotalNeto().toString());
            flex.setText(fila, 13, bean.getTotalPlan().toString());
            flex.setText(fila, 14, bean.getPuntoEmsion());
            flex.setText(fila, 15, bean.getNomMesa());
            flex.setText(fila, 16, bean.getDniVendedor());
            flex.setText(fila, 17, bean.getNomVendedor());
            flex.setText(fila, 18, bean.getUsuarioReg());
            flex.setText(fila, 19, bean.getDniSupervisor());
            flex.setText(fila, 20, bean.getNomSupervisor());
            flex.setText(fila, 21, bean.getDniGerenteZonal());
            flex.setText(fila, 22, bean.getNomGerenteZonal());
            flex.setText(fila, 23, bean.getDniCoordinador());
            flex.setText(fila, 24, bean.getNomCoordinador());
            flex.setText(fila, 25, bean.getObservacion());
            fila = fila + 1;
        }
        TableToExcel.save(flex, "RegVentasCe" + (new Date()).getTime());
    }

    @Override
    public void showUICambiarEstado() {
        CabeceraVentaProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            UIEstProyImpl ui=new UIEstProyImpl(this);
            ui.setBean(bean);
            ui.loadTable();
        } else {
            //Window.alert("Seleccione un registro de la tabla\ncon estado activo");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

}
