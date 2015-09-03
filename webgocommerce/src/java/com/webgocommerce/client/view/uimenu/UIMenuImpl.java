/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimenu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.BdUsuarioProxy;
import com.webgocommerce.client.beanproxy.MenuBarProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.requestfactory.ContextMantenimientoMenuBar;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.view.tree.TreeMenuModel;
import com.webgocommerce.client.view.uihomemenu.UIHomeMenu;
import com.webgocommerce.client.view.uisesion.UISesion;
import com.webgocommerce.server.beans.MenuBar;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIMenuImpl extends UIMenu {

    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeMenu uiHomeMenu;

    public UIMenuImpl(UIHomeMenu uiHomeMenu) {
        this.uiHomeMenu = uiHomeMenu;
        loadTable();
    }

    @Override
    public void loadTable() {
        popup.showPopup();
        ContextMantenimientoMenuBar context = FACTORY.contextMantenimientoMenuBar();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<MenuBarProxy>> request = context.listar(keyPublic);
        request.fire(new Receiver<List<MenuBarProxy>>() {

            @Override
            public void onSuccess(List<MenuBarProxy> response) {                
                /*List<MenuBar> lista=new ArrayList<MenuBar>();
                Iterator<MenuBarProxy> iterador=response.iterator();
                while(iterador.hasNext()){
                    MenuBarProxy beanProxy=iterador.next();
                    MenuBar bean=new MenuBar();
                    bean.setIdMenuBar(beanProxy.getIdMenuBar());
                    bean.setVariable(beanProxy.getVariable());
                    bean.setDescripcion(beanProxy.getDescripcion());
                    bean.setGrupo(beanProxy.getGrupo());
                    bean.setNivel(beanProxy.getNivel());
                    bean.setOrden(beanProxy.getOrden());
                    bean.setTipo(beanProxy.getTipo());
                    bean.setNumSubMenu(beanProxy.getNumSubMenu());
                    bean.setIdMenuPadre(beanProxy.getIdMenuPadre());
                    lista.add(bean);
                }
                MenuBar root=createTree(lista.get(0),lista);
                Window.alert(root.getIdMenuBar()+"");*/
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstBdEmpresa.setData(grid.getData());
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
    }
    
    @Override
    public void showTreeMenuBar() {
        List<MenuBar> lista=new ArrayList<MenuBar>();
                Iterator<MenuBarProxy> iterador=grid.getData().iterator();
                while(iterador.hasNext()){
                    MenuBarProxy beanProxy=iterador.next();
                    MenuBar bean=new MenuBar();
                    bean.setIdMenuBar(beanProxy.getIdMenuBar());
                    bean.setVariable(beanProxy.getVariable());
                    bean.setDescripcion(beanProxy.getDescripcion());
                    bean.setGrupo(beanProxy.getGrupo());
                    bean.setNivel(beanProxy.getNivel());
                    bean.setOrden(beanProxy.getOrden());
                    bean.setTipo(beanProxy.getTipo());
                    bean.setNumSubMenu(beanProxy.getNumSubMenu());
                    bean.setIdMenuPadre(beanProxy.getIdMenuPadre());
                    lista.add(bean);
                }
                MenuBar root=createTree(lista.get(0),lista);  
                DialogBox popup=new DialogBox();                                
                popup.setText("Vista previa de Menu");
                popup.setAnimationEnabled(true);
                popup.setAutoHideEnabled(true);
                popup.setGlassEnabled(true);
                ScrollPanel scroll=new ScrollPanel();                
                TreeMenuModel model=new TreeMenuModel(root);                
                CellTree tree=new CellTree(model,root);
                tree.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
                scroll.setSize("500px", "500px");                
                scroll.setWidget(tree);
                popup.add(scroll);
                popup.setSize("500px", "500px");                
                popup.center();
                popup.show();
    }
    
    public MenuBar createTree(MenuBar beanPadre, List<MenuBar> lista) {
        int cont = 0;
        for (int j = 0; j < lista.size(); j++) {
            MenuBar beanHijo = lista.get(j);            
            if (beanPadre.getIdMenuBar() == beanHijo.getIdMenuPadre()) {
                beanHijo.setPadre(beanPadre);
                beanPadre.setHijo(beanHijo);
                createTree(beanHijo, lista);
                cont = cont + 1;
            }
            if (cont == beanPadre.getNumSubMenu()) {
                break;
            }
        }
        return beanPadre;

    }

    

    @Override
    public void showUIOper1() {
        // TODO Auto-generated method stub
        uiHomeMenu.getContainer().showWidget(1);
        uiHomeMenu.getUiMantMenu().setModo(UIMantenimiento.MODOINSERTAR);
        uiHomeMenu.getUiMantMenu().setBean(null);
        uiHomeMenu.getUiMantMenu().loadFields();
        uiHomeMenu.getUiMantMenu().cleanForm();
        uiHomeMenu.getUiMantMenu().scrollPanel.refresh();
    }

    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        MenuBarProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            uiHomeMenu.getContainer().showWidget(1);
            uiHomeMenu.getUiMantMenu().setModo(UIMantenimiento.MODOUPDATE);
            uiHomeMenu.getUiMantMenu().setBean(bean);
            uiHomeMenu.getUiMantMenu().loadFields();
            uiHomeMenu.getUiMantMenu().scrollPanel.refresh();
        } else {
            //Window.alert("Seleccione un registro de la tabla\ncon estado activo");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla</br>con estado activo");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        MenuBarProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            uiHomeMenu.getContainer().showWidget(1);
            uiHomeMenu.getUiMantMenu().setModo(UIMantenimiento.MODODELETE);
            uiHomeMenu.getUiMantMenu().setBean(bean);
            uiHomeMenu.getUiMantMenu().loadFields();
            uiHomeMenu.getUiMantMenu().scrollPanel.refresh();
        } else {
            //Window.alert("Seleccione un registro de la tabla");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        MenuBarProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            uiHomeMenu.getContainer().showWidget(1);
            uiHomeMenu.getUiMantMenu().setModo(UIMantenimiento.MODODETALLE);
            uiHomeMenu.getUiMantMenu().setBean(bean);
            uiHomeMenu.getUiMantMenu().loadFields();
            uiHomeMenu.getUiMantMenu().scrollPanel.refresh();
        } else {
            //Window.alert("Seleccione un registro de la tabla");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
    
    
    @Override
    public void exportarData() {
        int row=0;
        List<MenuBarProxy> lista;
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
        flex.setText(0, 0, "ID");    
        flex.setText(0, 1, "TIPO");           
        flex.setText(0, 2, "VARIABLE");           
        flex.setText(0, 3, "DESCRIPCION");           
        flex.setText(0, 4, "NIVEL");           
        flex.setText(0, 5, "ORDEN");           
        flex.setText(0, 6, "GRUPO");           
        flex.setText(0, 7, "NUMSUBMENU");                              
        for(int j=0;j<8;j++){
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");            
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }        
        int fila=1;
        for(int i=0;i<row;i++){           
            MenuBarProxy bean=lista.get(i);
            flex.setText(fila, 0, bean.getIdMenuBar().toString());            
            flex.setText(fila, 1, bean.getTipo());
            flex.setText(fila, 2, bean.getVariable());
            flex.setText(fila, 3, bean.getDescripcion());
            flex.setText(fila, 4, bean.getNivel().toString());
            flex.setText(fila, 5, bean.getOrden().toString());
            flex.setText(fila, 6, bean.getGrupo().toString());
            flex.setText(fila, 7, bean.getNumSubMenu().toString());            
            fila=fila+1;
        }
        TableToExcel.save(flex, "Menus"+(new Date()).getTime());
    }
}
