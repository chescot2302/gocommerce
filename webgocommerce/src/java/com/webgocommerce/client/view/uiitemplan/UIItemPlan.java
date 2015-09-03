/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uiitemplan;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.webgocommerce.client.beanproxy.DetalleVentaProxy;
import com.webgocommerce.client.beanproxy.ItemPlanProxy;
import com.webgocommerce.client.model.HeaderMenu;
import com.webgocommerce.client.requestfactory.ContextMantenimientoItemPlan;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.UIFormMantenimiento;
import com.webgocommerce.client.view.grid.GridItemPlan;
import com.webgocommerce.client.view.uirventrada.UIrvEntradaImpl;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author SISTEMAS
 */
public class UIItemPlan extends PopupPanel implements InterUIItemPlan, TouchEndHandler, ClickHandler, KeyUpHandler {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private HeaderMenu header;
    private Label lblCenter;
    private PushButton btnBack;
    protected UIFormMantenimiento form;
    protected GridItemPlan grid;
    private FlowPanel pnlOperacion;
    private Button btnOperacion;
    protected String modo;
    private UIrvEntradaImpl ui;
    protected DetalleVentaProxy beanDetalle;
    private int countSelected;
    protected MSearchBox txtBuscar;
    private FlexTable pnlHeader;

    public UIItemPlan(UIrvEntradaImpl ui, DetalleVentaProxy object) {
        this.ui = ui;
        this.beanDetalle = object;
        initComponents();
        initStyle();
        initListener();
    }   

    public UIItemPlan() {
        initComponents();
        initStyle();
        initListener();
    }

    private void initComponents() {
        pnlHeader = new FlexTable();
        txtBuscar = new MSearchBox();
        txtBuscar.setPlaceHolder("Buscar");
        form = new UIFormMantenimiento();
        header = new HeaderMenu();
        lblCenter = new Label("SELECCIONAR EQUIPOS");
        btnBack = new PushButton(new Image(MyResource.INSTANCE.getImgBack32()));
        btnBack.setTitle("Volver Atras");
        header.setLeftWidget(btnBack);
        header.setCenterWidget(lblCenter);
        grid = new GridItemPlan();
        //grid.setAlwaysShowScrollBars(true);
        //grid.setMinimumTableWidth(1024, Style.Unit.PX);
        pnlOperacion = new FlowPanel();
        btnOperacion = new Button("Agregar");
        pnlOperacion.add(btnOperacion);
        btnOperacion.setConfirm(true);
        form.getPnlTabla().add(grid);
        form.getPnlTabla().add(grid.getPager());
        form.getPnlTabla().add(pnlOperacion);

        pnlHeader.setWidget(0, 0, header);
        pnlHeader.setWidget(1, 0, txtBuscar);
        form.getPnlBusqueda().add(pnlHeader);
        form.getPnlBotones().setVisible(false);
        grid.addHandler(this, KeyUpEvent.getType());
        grid.getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {

            }
        });
        this.add(form);
        this.setGlassEnabled(true);
        this.setAnimationEnabled(true);
        this.setModal(true);
        this.setAutoHideEnabled(true);
        this.setSize("700px", "540px");
        this.center();
    }

    private void initStyle() {
        MyResource.INSTANCE.getStlModel().ensureInjected();
        pnlOperacion.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
        btnOperacion.setWidth("90%");
        form.getPnlUnion().setWidth("100%");
        btnBack.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
        lblCenter.getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);
        form.getPnlBusqueda().setHeight("80px");
        form.getPnlBusqueda().getElement().getStyle().setMarginBottom(10, Style.Unit.PX);
        form.getPnlTabla().setHeight("400px");
        MyResource.INSTANCE.getStlModel().ensureInjected();
        txtBuscar.getElement().getFirstChild().getFirstChild().removeFromParent();
        pnlHeader.setWidth("100%");
    }

    @Override
    protected void onPreviewNativeEvent(Event.NativePreviewEvent event) {
        super.onPreviewNativeEvent(event);
        switch (event.getTypeInt()) {
            case Event.ONKEYDOWN:
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    hide();
                }
                break;
        }
    }

    private void initListener() {
        btnOperacion.addTouchEndHandler(this);
        btnBack.addClickHandler(this);
        txtBuscar.textBox.addKeyUpHandler(this);
    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
        if (event.getSource().equals(btnOperacion)) {
            if (modo.equalsIgnoreCase("ADD")) {
                addEquipos();
            } else if (modo.equalsIgnoreCase("REMOVE")) {
                removeEquipos();
            }
        }
    }

    @Override
    public void onClick(ClickEvent event) {
        if (event.getSource().equals(btnBack)) {
            hide();
        }
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo, String edit) {
        this.modo = modo;
        if (this.modo.equalsIgnoreCase("ADD")) {
            lblCenter.setText("SELECCIONAR EQUIPOS");
            btnOperacion.setText("Agregar");
            btnOperacion.setVisible(true);
        } else if (this.modo.equalsIgnoreCase("REMOVE")) {
            lblCenter.setText("SELECCIONAR EQUIPOS");
            btnOperacion.setText("Quitar");
            btnOperacion.setVisible(true);
        }else{
            lblCenter.setText("LISTADO DE EQUIPOS");
            btnOperacion.setText("DETALLE");
            btnOperacion.setVisible(false);
        }
        grid.setModo(edit);
    }

    @Override
    public void loadData() {
        if (modo.equalsIgnoreCase("ADD")) {
            ContextMantenimientoItemPlan context = FACTORY.contextMantenimientoItemPlan();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            Request<List<ItemPlanProxy>> request = context.listar(keyPublic);
            request.fire(new Receiver<List<ItemPlanProxy>>() {

                @Override
                public void onSuccess(List<ItemPlanProxy> response) {
                    grid.getDataProvider().resetFilter();
                    grid.setData(response);
                    grid.getSelectionModel().clear();
                    grid.checkAll.setIsSelected(Boolean.FALSE);
                }
            });
        }
    }

    @Override
    public void addEquipos() {
        Set<ItemPlanProxy> lista = grid.getSelectionModel().getSelectedSet();
        if (lista.size() > 0) {
            this.ui.addEquipos(lista, beanDetalle);
        } else {
            Notification not = new Notification(Notification.ALERT, "Seleccione series a agregar");
            not.showPopup();
            this.ui.cleanDataPostItem();
        }
        hide();
    }

    public GridItemPlan getGrid() {
        return grid;
    }

    public Button getBtnOperacion() {
        return btnOperacion;
    }

    @Override
    public void removeEquipos() {
        List<ItemPlanProxy> lista = new ArrayList(grid.getSelectionModel().getSelectedSet());
        if (lista.size() > 0) {
            this.ui.removeEquipos(lista, beanDetalle);
        } else {
            Notification not = new Notification(Notification.ALERT, "Seleccione series a remover");
            not.showPopup();
        }
        hide();
    }

    public int getCountSelected() {
        return countSelected;
    }

    public void setCountSelected(int countSelected) {
        this.countSelected = countSelected;
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getSource().equals(txtBuscar.textBox)) {
            grid.getDataProvider().setFilter(txtBuscar.getText());
            grid.getDataProvider().refresh();
        } else if (event.getSource().equals(grid)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER || event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SPACE) {
                itemSelected();
            }
        }
    }

    private void itemSelected() {
        if (grid.getDataProvider().hasFilter()) {
            ItemPlanProxy beanSerieItem = grid.getDataProvider().resulted.get(grid.getKeyboardSelectedRow());
            grid.getSelectionModel().setSelected(beanSerieItem, Boolean.TRUE);
            grid.flush();
            grid.redraw();
        } else {
            ItemPlanProxy beanSerieItem = grid.getData().get(grid.getKeyboardSelectedRow());
            grid.getSelectionModel().setSelected(beanSerieItem, Boolean.TRUE);
            grid.flush();
            grid.redraw();
        }
    }

    public void changeTituloForm(String titulo) {
        lblCenter.setText(titulo);
    }

}
