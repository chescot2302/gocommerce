/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uiadditemlista;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.FamiliaProxy;
import com.webgocommerce.client.beanproxy.PrecioItemProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoFamilia;
import com.webgocommerce.client.requestfactory.ContextMantenimientoPrecioItem;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.view.uihomelistaprecio.UIHomeListaPrecio;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
public class UIAddItemListaImpl extends UIAddItemLista {
    PopupProgress popup=new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeListaPrecio uiHomeListaPrecio;

    public UIAddItemListaImpl(UIHomeListaPrecio uiHomeListaPrecio) {
        this.uiHomeListaPrecio = uiHomeListaPrecio;
    }
    
    @Override
    public void loadFamilias() {
        ContextMantenimientoFamilia context = FACTORY.contextMantenimientoFamilia();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<FamiliaProxy>> request = context.listar(keyPublic);
        request.fire(new Receiver<List<FamiliaProxy>>() {

            @Override
            public void onSuccess(List<FamiliaProxy> response) {
                if (response.size() > 0) {
                    lstFamilia.setData(response);
                    loadTable();
                }
            }
        });
    }

    @Override
    public void loadTable() {        
        popup.showPopup();
        ContextMantenimientoPrecioItem context = FACTORY.contextMantenimientoPrecioItem();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<PrecioItemProxy>> request = context.itemSinListaCategoria(keyPublic, beanListaPrecio.getId(),lstFamilia.getSelectedItem().getCodFam(),txtBuscar.getText());
        request.fire(new Receiver<List<PrecioItemProxy>>() {

            @Override
            public void onSuccess(List<PrecioItemProxy> response) {                
                txtBuscar.setText(null);
                grid.getDataProvider().resetFilter();
                grid.setData(response);
                grid.getSelectionModel().clear();
                grid.checkAll.setIsSelected(Boolean.FALSE);
                popup.hidePopup();
            }

            @Override
            public void onFailure(ServerFailure error) {                
                    popup.hidePopup();
                    //Window.alert(error.getMessage());  
                    Notification not=new Notification(Notification.WARNING,error.getMessage());
                    not.showPopup();
            }
        });
    }

    @Override
    public void goToUIEditListaPrecio() {
        uiHomeListaPrecio.getContainer().showWidget(2);
        uiHomeListaPrecio.getUiEditListaPrecio().loadTable();
    }

    @Override
    public void addItemLista() {
        popup.showPopup();
        ContextMantenimientoPrecioItem context = FACTORY.contextMantenimientoPrecioItem();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Set<PrecioItemProxy> select = grid.getSelectionModel().getSelectedSet();
        Set<PrecioItemProxy> lista = new HashSet<PrecioItemProxy>();
        Iterator<PrecioItemProxy> i = select.iterator();
        while (i.hasNext()) {
            PrecioItemProxy bean = context.create(PrecioItemProxy.class);
            PrecioItemProxy beanEdit = i.next();
            bean.setId(beanEdit.getId());
            bean.setIdListaPrecio(beanEdit.getIdListaPrecio());
            bean.setIdItem(beanEdit.getIdItem());
            bean.setValorVenta(beanEdit.getValorVenta());
            bean.setIgv(beanEdit.getIgv());
            bean.setPrecioSD(beanEdit.getPrecioSD());
            bean.setDescuento(beanEdit.getDescuento());
            bean.setPrecioVenta(beanEdit.getPrecioVenta());
            bean.setIdMoneda(beanEdit.getIdMoneda());
            bean.setFechaIni(beanEdit.getFechaIni());
            bean.setFechaFin(beanEdit.getFechaFin());
            bean.setEstadoActiva(beanEdit.getEstadoActiva());
            bean.setVersion(beanEdit.getVersion());
            bean.setIsEditable(1);
            lista.add(bean);
        }
        Request<Boolean> request = context.insertarPrecioItem(keyPublic, lista);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                if (response) {
                    popup.hidePopup();
                    //Window.alert("Items agregados a lista");
                    Notification not=new Notification(Notification.INFORMATION,"Items agregados a lista");
                    not.showPopup();
                    loadTable();                    
                }
            }
            
            @Override
            public void onFailure(ServerFailure error) {                
                    popup.hidePopup();
                    //Window.alert(error.getMessage());                    
                    Notification not=new Notification(Notification.WARNING,error.getMessage());
                    not.showPopup();
            }
        });
    }
}
