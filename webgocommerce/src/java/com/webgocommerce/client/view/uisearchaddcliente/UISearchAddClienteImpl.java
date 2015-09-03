/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uisearchaddcliente;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.ClienteProxy;
import com.webgocommerce.client.beanproxy.DepartamentoProxy;
import com.webgocommerce.client.beanproxy.DistritoProxy;
import com.webgocommerce.client.beanproxy.PaisProxy;
import com.webgocommerce.client.beanproxy.ProvinciaProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoCliente;
import com.webgocommerce.client.requestfactory.ContextMantenimientoDepartamento;
import com.webgocommerce.client.requestfactory.ContextMantenimientoDistrito;
import com.webgocommerce.client.requestfactory.ContextMantenimientoPais;
import com.webgocommerce.client.requestfactory.ContextMantenimientoProvincia;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.view.uihomerv.UIHomerv;
import com.webgocommerce.client.view.uihomevd.UIHomevd;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UISearchAddClienteImpl extends UISearchAddCliente {

    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomevd uiHomevd;
    private UIHomerv uiHomerv;

    public UISearchAddClienteImpl(UIHomevd uiHomevd) {
        this.uiHomevd = uiHomevd;
        loadPais();
        //loadTable();
    }

    public UISearchAddClienteImpl(UIHomerv uiHomerv) {
        this.uiHomerv = uiHomerv;
        loadPais();
        //loadTable();
    }

    @Override
    public void loadTable() {
        popup.showPopup();
        ContextMantenimientoCliente context = FACTORY.contextMantenimientoCliente();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<ClienteProxy>> request = context.getListarCliente(keyPublic, uiBuscarCliente.lstFiltro.getSelectedIndex(), uiBuscarCliente.txtBuscar.getValue());;
        request.fire(new Receiver<List<ClienteProxy>>() {

            @Override
            public void onSuccess(List<ClienteProxy> response) {
                uiBuscarCliente.grid.getDataProvider().resetFilter();
                uiBuscarCliente.grid.getSelectionModel().clear();
                uiBuscarCliente.grid.setData(response);
                popup.hidePopup();
            }

            @Override
            public void onFailure(ServerFailure error) {
                popup.hidePopup();
                Window.alert(error.getMessage());
            }
        });
    }

    @Override
    public void goToBack() {
        if (uiHomevd != null) {
            uiHomevd.getContainer().showWidget(0);
        } else if (uiHomerv != null) {
            uiHomerv.getContainer().showWidget(0);
        }

    }

    @Override
    public void loadPais() {
        ContextMantenimientoPais context = FACTORY.contextMantenimientoPais();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<PaisProxy>> request = context.listar(keyPublic);
        request.fire(new Receiver<List<PaisProxy>>() {

            @Override
            public void onSuccess(List<PaisProxy> response) {
                if (!response.isEmpty()) {
                    formCliente.lstPais.setData(response);
                    loadDepartamento();
                }
            }
        });
    }

    @Override
    public void loadDepartamento() {
        ContextMantenimientoDepartamento context = FACTORY.contextMantenimientoDepartamento();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<DepartamentoProxy>> request = context.listar(keyPublic, formCliente.lstPais.getSelectedItem().getIdPais());
        request.fire(new Receiver<List<DepartamentoProxy>>() {

            @Override
            public void onSuccess(List<DepartamentoProxy> response) {
                if (!response.isEmpty()) {
                    formCliente.lstDepartamento.setData(response);
                    loadProvincia();
                }
            }
        });
    }

    @Override
    public void loadProvincia() {
        ContextMantenimientoProvincia context = FACTORY.contextMantenimientoProvincia();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<ProvinciaProxy>> request = context.listar(keyPublic, formCliente.lstPais.getSelectedItem().getIdPais(), formCliente.lstDepartamento.getSelectedItem().getIdDepartamento());
        request.fire(new Receiver<List<ProvinciaProxy>>() {

            @Override
            public void onSuccess(List<ProvinciaProxy> response) {
                if (!response.isEmpty()) {
                    formCliente.lstProvincia.setData(response);
                    loadDistrito();
                }
            }
        });
    }

    @Override
    public void loadDistrito() {
        ContextMantenimientoDistrito context = FACTORY.contextMantenimientoDistrito();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<DistritoProxy>> request = context.listar(keyPublic, formCliente.lstPais.getSelectedItem().getIdPais(), formCliente.lstDepartamento.getSelectedItem().getIdDepartamento(), formCliente.lstProvincia.getSelectedItem().getIdProvincia());
        request.fire(new Receiver<List<DistritoProxy>>() {

            @Override
            public void onSuccess(List<DistritoProxy> response) {
                if (!response.isEmpty()) {
                    formCliente.lstDistrito.setData(response);
                }
            }
        });
    }

    @Override
    public void processInsertar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoCliente context = FACTORY.contextMantenimientoCliente();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            ClienteProxy bean = context.create(ClienteProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setTipoDocIden(formCliente.lstTipoDoc.getItemText(formCliente.lstTipoDoc.getSelectedIndex()));
            bean.setNombres(formCliente.txtNombres.getText());
            bean.setRuc(formCliente.txtDocumento.getText());
            bean.setIdPais(formCliente.lstPais.getSelectedItem().getIdPais());
            bean.setIdDepartamento(formCliente.lstDepartamento.getSelectedItem().getIdDepartamento());
            bean.setIdProvincia(formCliente.lstProvincia.getSelectedItem().getIdProvincia());
            bean.setIdDistrito(formCliente.lstDistrito.getSelectedItem().getIdDistrito());
            bean.setDireccion(formCliente.txtDireccion.getText());
            bean.setTelefono(formCliente.txtTelefono.getText());
            bean.setEmail(formCliente.txtEmail.getText());
            bean.setOperacion("I");
            Request<String> request = context.insertarCliente(keyPublic, bean);
            request.fire(new Receiver<String>() {

                @Override
                public void onSuccess(String response) {
                    String docCliente = formCliente.txtDocumento.getText();
                    cleanForm();
                    //Window.alert(response);
                    Notification not = new Notification(Notification.INFORMATION, response);
                    not.showPopup();
                    goToUIvdEntrada(docCliente);
                }

                @Override
                public void onFailure(ServerFailure error) {
                    //Window.alert(error.getMessage());
                    Notification not = new Notification(Notification.WARNING, error.getMessage());
                    not.showPopup();
                }

            });
        }
    }

    @Override
    public void goToUIvdEntrada(String docCliente) {
        if (uiHomevd != null) {
            uiHomevd.getContainer().showWidget(0);
            uiHomevd.getUiVentaDirecta().searchCliente(docCliente);
        } else if (uiHomerv != null) {
            uiHomerv.getContainer().showWidget(0);
            uiHomerv.getUiVentaDirecta().searchCliente(docCliente);
        }
    }

    @Override
    public void selectClient() {
        ClienteProxy bean = uiBuscarCliente.grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            if (uiHomevd != null) {
                uiHomevd.getContainer().showWidget(0);
                uiHomevd.getUiVentaDirecta().setBeanCliente(bean);
                uiHomevd.getUiVentaDirecta().getUiSearchCliente().txtRucFacturacion.setFocus(true);
            } else if (uiHomerv != null) {
                uiHomerv.getContainer().showWidget(0);
                uiHomerv.getUiVentaDirecta().setBeanCliente(bean);
                uiHomerv.getUiVentaDirecta().getUiSearchCliente().txtRucFacturacion.setFocus(true);
            }
        } else {
            //Window.alert("Seleccione un registro de la tabla\ncon estado activo");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
}
