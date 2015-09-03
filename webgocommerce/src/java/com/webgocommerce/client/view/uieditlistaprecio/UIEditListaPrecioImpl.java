/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uieditlistaprecio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.PrecioItemProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoPrecioItem;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.view.uihomelistaprecio.UIHomeListaPrecio;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
public class UIEditListaPrecioImpl extends UIEditListaPrecio {
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeListaPrecio uiHomeListaPrecio;

    public UIEditListaPrecioImpl(UIHomeListaPrecio uiHomeListaPrecio) {
        this.uiHomeListaPrecio = uiHomeListaPrecio;
    }

    @Override
    public void goToUIListaPrecio() {
        uiHomeListaPrecio.getContainer().showWidget(0);
        uiHomeListaPrecio.getUiListaPrecio().loadTable();
    }

    @Override
    public void loadTable() {
        cargarTabla("A");
    }

    @Override
    public void loadTableEstado() {
        String estado = lstEstado.getValue(lstEstado.getSelectedIndex());
        cargarTabla(estado);
    }

    private void cargarTabla(String estado) {
        popup.showPopup();
        ContextMantenimientoPrecioItem context = FACTORY.contextMantenimientoPrecioItem();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<PrecioItemProxy>> request = context.listar(keyPublic, beanListaPrecio.getId(), estado);
        request.fire(new Receiver<List<PrecioItemProxy>>() {

            @Override
            public void onSuccess(List<PrecioItemProxy> response) {
                txtBuscar.setText(null);
                grid.getDataProvider().resetFilter();
                grid.setData(response);
                grid.getSelectionModel().clear();
                grid.checkAll.setIsSelected(Boolean.FALSE);
                grid.actualizarGrid();
                //lstBdEmpresa.setData(grid.getData());
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
    public void showUIOper1() {
        uiHomeListaPrecio.getContainer().showWidget(3);
        uiHomeListaPrecio.getUiAddItemLista().setBeanListaPrecio(beanListaPrecio);
        uiHomeListaPrecio.getUiAddItemLista().loadFields();
        uiHomeListaPrecio.getUiAddItemLista().loadFamilias();
    }

    @Override
    public void showUIOper3() {
        if (grid.getSelectionModel().getSelectedSet().size() > 0) {
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
                bean.setVersion(beanEdit.getVersion());
                lista.add(bean);
            }
            Request<Boolean> request = context.eliminarPrecioItem(keyPublic, lista);
            request.fire(new Receiver<Boolean>() {

                @Override
                public void onSuccess(Boolean response) {
                    if (response) {
                        popup.hidePopup();
                        //Window.alert("Items removidos de lista");
                        Notification not=new Notification(Notification.INFORMATION,"Items removidos de lista");
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
        } else {
            //Window.alert("Seleccione registros de la tabla");
            Notification not=new Notification(Notification.WARNING,"Seleccione registros de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        if (grid.getSelectionModel().getSelectedSet().size() > 0) {
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
                bean.setVersion(beanEdit.getVersion());
                lista.add(bean);
            }
            Request<Boolean> request = context.desactivarPrecioItem(keyPublic, lista);
            request.fire(new Receiver<Boolean>() {

                @Override
                public void onSuccess(Boolean response) {
                    if (response) {
                        popup.hidePopup();
                        //Window.alert("Items desactivados de lista");
                        Notification not=new Notification(Notification.INFORMATION,"Items desactivados de lista");
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
        } else {
            //Window.alert("Seleccione registros de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione registros de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void updatePrices() {
        HashSet<Integer> listUpdate = grid.getIndexUpdates();
        if (!listUpdate.isEmpty()) {
            popup.showPopup();
            ContextMantenimientoPrecioItem context = FACTORY.contextMantenimientoPrecioItem();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            Set<PrecioItemProxy> lista = new HashSet<PrecioItemProxy>();
            Iterator<Integer> i = listUpdate.iterator();
            while (i.hasNext()) {
                PrecioItemProxy bean = context.create(PrecioItemProxy.class);
                PrecioItemProxy beanEdit = grid.getData().get(i.next());
                bean.setId(beanEdit.getId());
                bean.setPrecioSD(beanEdit.getPrecioSD());
                bean.setDescuento(beanEdit.getDescuento());
                bean.setValorVenta(beanEdit.getValorVenta());
                bean.setIgv(beanEdit.getIgv());
                bean.setPrecioVenta(beanEdit.getPrecioVenta());
                bean.setVersion(beanEdit.getVersion());
                bean.setIsEditable(0);
                lista.add(bean);
            }
            Request<Boolean> request = context.actualizarPrecioItem(keyPublic, lista);
            request.fire(new Receiver<Boolean>() {

                @Override
                public void onSuccess(Boolean response) {
                    if (response) {
                        popup.hidePopup();
                        //Window.alert("Precios actualizados de lista");
                        Notification not=new Notification(Notification.INFORMATION,"Precios actualizados de lista");
                        not.showPopup();
                        loadTable();
                    }
                }

                @Override
                public void onFailure(ServerFailure error) {
                    //Window.alert(error.getMessage());
                    Notification not=new Notification(Notification.WARNING,error.getMessage());
                    not.showPopup();
                    popup.hidePopup();
                }
            });
        } else {
            //Window.alert("Modifique algún precio");
            Notification not=new Notification(Notification.ALERT,"Modifique algún precio");
            not.showPopup();
        }
    }
    
    @Override
    public void exportarData() {  
        int row=0;
        List<PrecioItemProxy> lista;
        if(!grid.getDataProvider().hasFilter()){
            row=grid.getData().size();
            lista=grid.getData();
        }else{
            row=grid.getDataProvider().resulted.size();  
            lista=grid.getDataProvider().resulted;
        }
        if(row==0){
            Notification not=new Notification(Notification.ALERT,"Grid sin datos");
            not.showPopup();
            return;
        }
        FlexTable flex=new FlexTable();
        flex.setText(0, 0, "ESTADO");    
        flex.setText(0, 1, "CODIGO");           
        flex.setText(0, 2, "DESCRIPCION");           
        flex.setText(0, 3, "MARCA");           
        flex.setText(0, 4, "PRECIO SD");           
        flex.setText(0, 5, "DESCUENTO");           
        flex.setText(0, 6, "PRECIO VENTA");           
        flex.setText(0, 7, "VALOR VENTA");           
        flex.setText(0, 8, "IGV");           
        flex.setText(0, 9, "FECHA INI");           
        flex.setText(0, 10, "FECHA FIN");           
        for(int j=0;j<11;j++){
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");            
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }        
        int fila=1;
        for(int i=0;i<row;i++){           
            PrecioItemProxy bean=lista.get(i);
            flex.setText(fila, 0, bean.getEstadoActiva());            
            flex.setText(fila, 1, bean.getCodigo());                       
            flex.setText(fila, 2, bean.getDescripcion());                       
            flex.setText(fila, 3, bean.getMarca());                       
            flex.setText(fila, 4, bean.getPrecioSD().toString());                       
            flex.setText(fila, 5, bean.getDescuento().toString());
            flex.setText(fila, 6, bean.getPrecioVenta().toString());
            flex.setText(fila, 7, bean.getValorVenta().toString());
            flex.setText(fila, 8, bean.getIgv().toString());
            flex.setText(fila, 9, bean.getFechaIni()!=null?dateFormat.format(bean.getFechaIni()):"");
            flex.setText(fila, 10, bean.getFechaFin()!=null?dateFormat.format(bean.getFechaFin()):"");            
            fila=fila+1;
        }
        TableToExcel.save(flex, "preciositem"+(new Date()).getTime());
    }

}
