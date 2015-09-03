/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.uiutil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressBar;
import com.webgocommerce.client.beanproxy.ClienteProxy;
import com.webgocommerce.client.beanproxy.ListaPrecioProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.view.grid.GridCliente;
import com.webgocommerce.client.view.uiadditemlista.InterUIAddItemLista;

/**
 *
 * @author SISTEMAS
 */
public class UIBuscarCliente extends UIFormMantenimiento implements KeyUpHandler, InterUIAddItemLista, TouchEndHandler {

    protected ProgressBar progreso;
    public ListBox lstFiltro;
    public MSearchBox txtBuscar;
    public GridCliente grid;
    protected ClienteProxy beanCliente;
    private FlowPanel pnlGuardar;
    public Button btnGuardar;
    private FlexTable pnlFiltro;

    public UIBuscarCliente() {
        initComponents();
        initStyle();
        initListener();
        reCalcularWindows();
    }

    private void initComponents() {
        pnlFiltro = new FlexTable();
        progreso = new ProgressBar();
        lstFiltro = new ListBox();
        lstFiltro.addItem("Descripcion");
        lstFiltro.addItem("Nro. Doc");
        txtBuscar = new MSearchBox();
        txtBuscar.getElement().setPropertyString("placeholder", "escriba aqui");
        pnlFiltro.setWidget(0, 0, lstFiltro);
        pnlFiltro.setWidget(0, 1, txtBuscar);
        grid = new GridCliente();
        grid.setMinimumTableWidth(1024, Style.Unit.PX);
        pnlGuardar = new FlowPanel();
        btnGuardar = new Button("Seleccionar");
        pnlGuardar.add(btnGuardar);
        btnGuardar.setConfirm(true);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());
        this.getPnlTabla().add(pnlGuardar);
        this.getPnlBusqueda().add(pnlFiltro);
        this.getPnlBotones().setVisible(false);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }

    private void initListener() {
        txtBuscar.textBox.addKeyUpHandler(this);
        btnGuardar.addTouchEndHandler(this);
    }

    ClickHandler clickHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
        }

    };

    private void initStyle() {
        MyResource.INSTANCE.getStlModel().ensureInjected();
        pnlGuardar.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
        btnGuardar.setWidth("90%");
        this.getPnlUnion().setWidth("100%");
        MyResource.INSTANCE.getStlModel().ensureInjected();
        txtBuscar.getElement().getFirstChild().getFirstChild().removeFromParent();
        pnlFiltro.setWidth("100%");
        pnlFiltro.getCellFormatter().setWidth(0, 0, "7%");
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight();
        this.getPnlTabla().setHeight(alto - 340 + "px");
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
          
                grid.getDataProvider().setFilter(txtBuscar.getText());
                grid.getDataProvider().refresh();            

    }

    public void setBeanListaPrecio(ClienteProxy beanCliente) {
        this.beanCliente = beanCliente;
    }

    @Override
    public void goToUIEditListaPrecio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadFields() {

    }

    @Override
    public void loadTable() {

    }

    @Override
    public void addItemLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
        addItemLista();
    }

    @Override
    public void loadFamilias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ClienteProxy getBeanCliente() {
        return beanCliente;
    }

    public void setBeanCliente(ClienteProxy beanCliente) {
        this.beanCliente = beanCliente;
    }

}
