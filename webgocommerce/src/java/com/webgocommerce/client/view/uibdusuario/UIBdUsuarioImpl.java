/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uibdusuario;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.BdUsuarioProxy;
import com.webgocommerce.client.beanproxy.DataSesionProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.requestfactory.ContextMantenimientoBdUsuario;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.service.ServiceExportar;
import com.webgocommerce.client.service.ServiceExportarAsync;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.TableToExcel;
import com.webgocommerce.client.view.uiconexionuser.UIConexionUserImpl;
import com.webgocommerce.client.view.uihomebdusuario.UIHomeBdUsuario;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIBdUsuarioImpl extends UIBdUsuario {

    PopupProgress popup = new PopupProgress();
    private final ServiceExportarAsync servicioExportar = GWT.create(ServiceExportar.class);
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeBdUsuario uiHomeBdUsuario;

    public UIBdUsuarioImpl(UIHomeBdUsuario uiHomeBdUsuario) {
        this.uiHomeBdUsuario = uiHomeBdUsuario;
        loadTable();
    }

    @Override
    public void loadTable() {
        popup.showPopup();
        ContextMantenimientoBdUsuario context = FACTORY.contextMantenimientoBdUsuario();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<BdUsuarioProxy>> request = context.listar(keyPublic).with("beanBdEmpresa");
        request.fire(new Receiver<List<BdUsuarioProxy>>() {

            @Override
            public void onSuccess(List<BdUsuarioProxy> response) {
                //Window.alert(response.get(0).getBeanBdEmpresa().getNombre());
                grid.getSelectionModel().clear();
                grid.setData(response);
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
    public void showUIOper1() {
        uiHomeBdUsuario.getContainer().showWidget(1);
        uiHomeBdUsuario.getUiMantBdUsuario().setModo(UIMantenimiento.MODOINSERTAR);
        uiHomeBdUsuario.getUiMantBdUsuario().setBean(null);
        uiHomeBdUsuario.getUiMantBdUsuario().cleanForm();
        uiHomeBdUsuario.getUiMantBdUsuario().loadListBox();
        uiHomeBdUsuario.getUiMantBdUsuario().loadFields();
        uiHomeBdUsuario.getUiMantBdUsuario().scrollPanel.refresh();
    }

    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        BdUsuarioProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            uiHomeBdUsuario.getContainer().showWidget(1);
            uiHomeBdUsuario.getUiMantBdUsuario().setModo(UIMantenimiento.MODOUPDATE);
            uiHomeBdUsuario.getUiMantBdUsuario().setBean(bean);
            uiHomeBdUsuario.getUiMantBdUsuario().loadFields();
            uiHomeBdUsuario.getUiMantBdUsuario().scrollPanel.refresh();
        } else {
            //Window.alert("Seleccione un registro de la tabla");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        BdUsuarioProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            uiHomeBdUsuario.getContainer().showWidget(1);
            uiHomeBdUsuario.getUiMantBdUsuario().setModo(UIMantenimiento.MODODELETE);
            uiHomeBdUsuario.getUiMantBdUsuario().setBean(bean);
            uiHomeBdUsuario.getUiMantBdUsuario().loadListBox();
            uiHomeBdUsuario.getUiMantBdUsuario().loadFields();
            uiHomeBdUsuario.getUiMantBdUsuario().scrollPanel.refresh();
        } else {
            //Window.alert("Seleccione un registro de la tabla");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        BdUsuarioProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            uiHomeBdUsuario.getContainer().showWidget(1);
            uiHomeBdUsuario.getUiMantBdUsuario().setModo(UIMantenimiento.MODODETALLE);
            uiHomeBdUsuario.getUiMantBdUsuario().setBean(bean);
            uiHomeBdUsuario.getUiMantBdUsuario().loadListBox();
            uiHomeBdUsuario.getUiMantBdUsuario().loadFields();
            uiHomeBdUsuario.getUiMantBdUsuario().scrollPanel.refresh();
        } else {
            //Window.alert("Seleccione un registro de la tabla");
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void showUIMenuAcceso() {
        BdUsuarioProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            uiHomeBdUsuario.getContainer().showWidget(2);
            uiHomeBdUsuario.getUiMenuAcceso().setBean(bean);
            uiHomeBdUsuario.getUiMenuAcceso().showTreeMenuBar();
        } else {
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }

    @Override
    public void exportarData() {
        int row = 0;
        List<BdUsuarioProxy> lista;
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
        flex.setText(0, 0, "ID");
        flex.setText(0, 1, "EMPRESA");
        flex.setText(0, 2, "ESQUEMA");
        flex.setText(0, 3, "NIVEL");
        flex.setText(0, 4, "USUARIO LOG");
        flex.setText(0, 5, "CLAVE LOG");
        flex.setText(0, 6, "USUARIO BD");
        flex.setText(0, 7, "CLAVE BD");
        flex.setText(0, 8, "ESTADO");
        for (int j = 0; j < 9; j++) {
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }
        int fila = 1;
        for (int i = 0; i < row; i++) {
            BdUsuarioProxy bean = lista.get(i);
            flex.setText(fila, 0, bean.getIdBdUsuario().toString());
            flex.setText(fila, 1, bean.getBeanBdEmpresa().getNombre());
            flex.setText(fila, 2, bean.getSchema());
            flex.setText(fila, 3, bean.getNivel());
            flex.setText(fila, 4, bean.getCorreo());
            flex.setText(fila, 5, bean.getClave());
            flex.setText(fila, 6, bean.getUsuarioBd());
            flex.setText(fila, 7, bean.getClaveBd());
            flex.setText(fila, 8, bean.getEstadoActivacion());
            fila = fila + 1;
        }
        TableToExcel.save(flex, "BdUsuario" + (new Date()).getTime());
    }
    /*@Override
     public void showTreeMenuBar() {
     popup.showPopup();
     ContextMantenimientoMenuBar context = FACTORY.contextMantenimientoMenuBar();
     FACTORY.initialize(EVENTBUS);
     String keyPublic = UISesion.keyPublic;
     BdUsuarioProxy bean = grid.getSelectionModel().getSelectedObject();
     Request<List<MenuBarProxy>> request = context.listarXusuario(keyPublic,bean.getIdBdUsuario());
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
     DialogBox dialog = new DialogBox();
     dialog.setText("Vista previa de Menu");
     dialog.setAnimationEnabled(true);
     dialog.setAutoHideEnabled(true);
     dialog.setGlassEnabled(true);
     ScrollPanel scroll = new ScrollPanel();
     TreeMenuModel model = new TreeMenuModel(root.getHijos());
     CellTree tree = new CellTree(model, root);
     tree.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);                                
     scroll.setSize("500px", "500px");
     scroll.setWidget(tree);
     dialog.add(scroll);
     dialog.setSize("500px", "500px");
     dialog.center();
     dialog.show();
     popup.hidePopup();
     }

     @Override
     public void onFailure(ServerFailure error) {
     popup.hidePopup();               
     Notification not = new Notification(Notification.WARNING, error.getMessage());
     not.showPopup();
     }
     });

     }*/

    /*public MenuBar createTree(MenuBar beanPadre, List<MenuBar> lista) {
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

     }*/
    @Override
    public void verConexionServer() {
        BdUsuarioProxy bean = grid.getSelectionModel().getSelectedObject();
        if (bean != null) {
            uiHomeBdUsuario.getContainer().showWidget(3);
            uiHomeBdUsuario.getUiConexionUser().setBean(bean);
            uiHomeBdUsuario.getUiConexionUser().loadTable();
        } else {
            Notification not = new Notification(Notification.ALERT, "Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
}
