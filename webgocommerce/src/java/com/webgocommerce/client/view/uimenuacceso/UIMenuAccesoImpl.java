/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimenuacceso;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.MenuBarProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoMenuBar;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.view.tree.TreeMenuModel;
import com.webgocommerce.client.view.uihomebdusuario.UIHomeBdUsuario;
import com.webgocommerce.client.view.uisesion.UISesion;
import com.webgocommerce.server.beans.MenuBar;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
public class UIMenuAccesoImpl extends UIMenuAcceso {

    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeBdUsuario uiHomeBdUsuario;
    private TreeMenuModel model;
    private CellTree tree;

    public UIMenuAccesoImpl(UIHomeBdUsuario uiHomeBdUsuario) {
        this.uiHomeBdUsuario = uiHomeBdUsuario;
    }

    @Override
    public void showTreeMenuBar() {
        popup.showPopup();
        ContextMantenimientoMenuBar context = FACTORY.contextMantenimientoMenuBar();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<MenuBarProxy>> request = context.listarXusuario(keyPublic, bean.getIdBdUsuario(),bean.getSchema());
        request.fire(new Receiver<List<MenuBarProxy>>() {

            @Override
            public void onSuccess(List<MenuBarProxy> response) {
                List<MenuBar> lista = new ArrayList<MenuBar>();
                Iterator<MenuBarProxy> iterador = response.iterator();
                while (iterador.hasNext()) {
                    MenuBarProxy beanProxy = iterador.next();
                    MenuBar bean = new MenuBar();
                    bean.setIdMenuBar(beanProxy.getIdMenuBar());
                    bean.setVariable(beanProxy.getVariable());
                    bean.setDescripcion(beanProxy.getDescripcion());
                    bean.setGrupo(beanProxy.getGrupo());
                    bean.setNivel(beanProxy.getNivel());
                    bean.setOrden(beanProxy.getOrden());
                    bean.setTipo(beanProxy.getTipo());
                    bean.setNumSubMenu(beanProxy.getNumSubMenu());
                    bean.setIdMenuPadre(beanProxy.getIdMenuPadre());
                    bean.setIdBdUsuario(beanProxy.getIdBdUsuario());
                    bean.setEstado(beanProxy.getEstado());
                    lista.add(bean);
                }
                MenuBar root = createTree(lista.get(0), lista);
                model = new TreeMenuModel(root);
                tree = new CellTree(model, root);
                tree.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
                tree.setWidth("100%");
                scroll.setWidget(tree);
                popup.hidePopup();
            }

            @Override
            public void onFailure(ServerFailure error) {
                popup.hidePopup();                 
                Notification not = new Notification(Notification.WARNING, error.getMessage());
                not.showPopup();
            }
        });
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
    public void goToUIBdUsuario() {
        uiHomeBdUsuario.getContainer().showWidget(0);
        uiHomeBdUsuario.getUiBdUsuario().loadTable();
    }
    
    @Override
    public void saveAccess() {
        popup.showPopup();
        ContextMantenimientoMenuBar context = (ContextMantenimientoMenuBar) FACTORY.contextMantenimientoMenuBar();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        MenuBar root=model.getRootMenu();
        root.setEstado("A");
        root.setOperacion("A");
        List<MenuBar> lista=new ArrayList<MenuBar>();     
        Set<MenuBarProxy> dataUpdate = new HashSet<MenuBarProxy>();
        printMenu(root,lista);        
        Iterator<MenuBar> i=lista.iterator();
        Date fecha=new Date();
        while(i.hasNext()){
            MenuBar beanMenu=i.next();           
            if(beanMenu.getOperacion().equalsIgnoreCase("A")){
              MenuBarProxy  beanUpdate = context.create(MenuBarProxy.class);
              beanUpdate.setIdBdUsuario(beanMenu.getIdBdUsuario());
              beanUpdate.setIdMenuBar(beanMenu.getIdMenuBar());
              beanUpdate.setEstado(beanMenu.getEstado());
              beanUpdate.setOperacion("A");
              beanUpdate.setVersion(fecha.getTime());
              dataUpdate.add(beanUpdate);    
            }
        }
        Request<Boolean> request = context.updateGrantMenusuario(keyPublic, dataUpdate,bean.getSchema());
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                if (response) {
                    popup.hidePopup();
                    //Window.alert("Items agregados a lista");
                    Notification not=new Notification(Notification.INFORMATION,"Accesos actualizados");
                    not.showPopup();
                    showTreeMenuBar();
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
    
    public void printMenu(MenuBar padre,List<MenuBar> lista){        
        lista.add(padre);
        Iterator<MenuBar> i=padre.getHijos().iterator();
        while(i.hasNext()){
            MenuBar bean=i.next();
            printMenu(bean,lista);
        }
    }
    
    

}
