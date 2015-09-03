/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantlistaprecio;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.CategoriaListaProxy;
import com.webgocommerce.client.beanproxy.ListaPrecioProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoCategoriaLista;
import com.webgocommerce.client.requestfactory.ContextMantenimientoListaPrecio;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomelistaprecio.UIHomeListaPrecio;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIMantListaPrecioImpl extends UIMantListaPrecio {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeListaPrecio uiHomeListaPrecio;

    public UIMantListaPrecioImpl(UIHomeListaPrecio uiHomeListaPrecio) {
        this.uiHomeListaPrecio = uiHomeListaPrecio;
        loadListBox();
    }

    @Override
    public void loadListBox() {
        ContextMantenimientoCategoriaLista context = FACTORY.contextMantenimientoCategoriaLista();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<CategoriaListaProxy>> request = context.listar(keyPublic);
        request.fire(new Receiver<List<CategoriaListaProxy>>() {

            @Override
            public void onSuccess(List<CategoriaListaProxy> response) {
                lstCategoriaLista.setData(response);
            }
        });
    }

    @Override
    public void processInsertar() {
        if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoListaPrecio context = FACTORY.contextMantenimientoListaPrecio();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            ListaPrecioProxy bean = context.create(ListaPrecioProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setOperacion("I");
            bean.setIdCategoriaLista(lstCategoriaLista.getSelectedItem().getId());
            bean.setDescripcion(txtDescripcion.getText().toUpperCase());
            bean.setCondicion(lstCondicion.getItemText(lstCondicion.getSelectedIndex()));
            bean.setPagoMensual(Double.parseDouble(txtPago.getValue()));
            bean.setVigencia(Integer.parseInt(txtVigencia.getValue()));
            bean.setCanal(lstCanal.getItemText(lstCanal.getSelectedIndex()));
            Request<Boolean> request = context.insertarListaPrecio(bean, keyPublic);
            request.fire(new Receiver<Boolean>() {

                @Override
                public void onSuccess(Boolean response) {
                    cleanForm();
                    //Window.alert("Insertado correctamente");
                    Notification not=new Notification(Notification.INFORMATION,"Insertado correctamente");
                    not.showPopup();
                }
            });
        }
    }

    @Override
    public void processActualizar() {
        if (isValidData()) {
        Date fecha = new Date();
        ContextMantenimientoListaPrecio context = FACTORY.contextMantenimientoListaPrecio();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        ListaPrecioProxy bean = context.create(ListaPrecioProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setIdCategoriaLista(lstCategoriaLista.getSelectedItem().getId());
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setCondicion(lstCondicion.getItemText(lstCondicion.getSelectedIndex()));
        bean.setPagoMensual(Double.parseDouble(txtPago.getValue()));
        bean.setVigencia(Integer.parseInt(txtVigencia.getValue()));
        bean.setId(this.bean.getId());
        bean.setCanal(lstCanal.getItemText(lstCanal.getSelectedIndex()));
        Request<Boolean> request = context.actualizarListaPrecio(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                //Window.alert("Actualizado correctamente");
                Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                not.showPopup();
                goToUIListaPrecio();
            }
        });
        }
    }

    @Override
    public void processDesactivar() {
        Date fecha = new Date();
        ContextMantenimientoListaPrecio context = FACTORY.contextMantenimientoListaPrecio();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        ListaPrecioProxy bean = context.create(ListaPrecioProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setId(this.bean.getId());
        Request<Boolean> request = context.desactivarListaPrecio(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                //Window.alert("Desactivado correctamente");
                Notification not=new Notification(Notification.INFORMATION,"Desactivado correctamente");
                not.showPopup();
                goToUIListaPrecio();
            }
        });
    }

    @Override
    public void processEliminar() {
        Date fecha = new Date();
        ContextMantenimientoListaPrecio context = FACTORY.contextMantenimientoListaPrecio();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        ListaPrecioProxy bean = context.create(ListaPrecioProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setId(this.bean.getId());
        Request<Boolean> request = context.eliminarListaPrecio(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                //Window.alert("Eliminado correctamente");
                Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                not.showPopup();
                goToUIListaPrecio();
            }
        });
    }

    @Override
    public void goToBack() {
        uiHomeListaPrecio.getContainer().showWidget(0);
        uiHomeListaPrecio.getUiListaPrecio().loadTable();
    }

    @Override
    public void goToUIListaPrecio() {
        cleanForm();
        uiHomeListaPrecio.getContainer().showWidget(0);
        uiHomeListaPrecio.getUiListaPrecio().loadTable();
    }

}
