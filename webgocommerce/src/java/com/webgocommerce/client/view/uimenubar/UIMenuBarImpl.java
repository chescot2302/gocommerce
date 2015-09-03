/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimenubar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
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
import com.webgocommerce.client.view.uisesion.UISesion;
import com.webgocommerce.client.view.uisesion.UISesionImpl;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIMenuBarImpl extends UIMenuBar {

    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();

    public UIMenuBarImpl() {
        drawMenuBar();
    }

    @Override
    public void drawMenuBar() {
        ContextMantenimientoMenuBar context = FACTORY.contextMantenimientoMenuBar();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<MenuBarProxy>> request = context.listarXusuario(keyPublic, UISesionImpl.beanUsuario.getIdBdUsuario(),UISesionImpl.beanUsuario.getEsquema());
        request.fire(new Receiver<List<MenuBarProxy>>() {

            @Override
            public void onSuccess(List<MenuBarProxy> response) {
                MenuBarProxy beanPadre = response.get(0);
                createMenu(menuRoot,beanPadre,response);
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

    public  void createMenu(MenuBar menu,MenuBarProxy beanPadre,List<MenuBarProxy> lista) {
        int cont = 0;
        for (int j = 0; j < lista.size(); j++) {
            MenuBarProxy beanHijo = lista.get(j);
            if (beanPadre.getIdMenuBar() == beanHijo.getIdMenuPadre() && beanHijo.getEstado().equalsIgnoreCase("A")) {
                if (beanHijo.getTipo().equalsIgnoreCase("MenuBar")) {                    
                    MenuBar menuBar = new MenuBar(true);
                    menuBar.ensureDebugId(beanHijo.getVariable());
                    menu.addItem(beanHijo.getDescripcion(), menuBar);                    
                    createMenu(menuBar,beanHijo, lista);
                    cont = cont + 1;
                } else if (beanHijo.getTipo().equalsIgnoreCase("MenuItem")) {
                    MenuItem menuItem = new MenuItem(beanHijo.getDescripcion(), UIMenuBar.comandos.get(beanHijo.getComando()));
                    menu.addItem(menuItem);
                }                
            }
            if (cont == beanPadre.getNumSubMenu()) {
                break;
            }
        }

    }

}
