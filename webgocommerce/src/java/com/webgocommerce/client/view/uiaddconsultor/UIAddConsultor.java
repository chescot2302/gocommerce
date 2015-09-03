/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uiaddconsultor;

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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressBar;
import com.webgocommerce.client.beanproxy.MesaProxy;
import com.webgocommerce.client.model.HeaderMenu;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.UIFormMantenimiento;
import com.webgocommerce.client.view.grid.GridConsultorSinMesa;

/**
 *
 * @author SISTEMAS
 */
public class UIAddConsultor extends UIFormMantenimiento implements KeyUpHandler, InterUIAddConsultor, TouchEndHandler {
    protected ProgressBar progreso;
    private HeaderMenu header;
    private Label lblCenter;
    private PushButton btnBack;
    //private Label lblBuscar;
    protected MSearchBox txtBuscar;
    //private FlexTable formBuscar;
    protected GridConsultorSinMesa grid;
    protected MesaProxy beanMesa;
    private FlowPanel pnlGuardar;
    private com.googlecode.mgwt.ui.client.widget.button.Button btnGuardar;

    public UIAddConsultor() {
        initComponents();
        initStyle();
        initListener();
        reCalcularWindows();
    }

    private void initComponents() {
        progreso=new ProgressBar();
        header = new HeaderMenu();
        lblCenter = new Label("CATEGORIA - LISTA");
        btnBack = new PushButton(new Image(MyResource.INSTANCE.getImgBack32()));
        btnBack.setTitle("Volver Atras");
        header.setLeftWidget(btnBack);
        header.setCenterWidget(lblCenter);
        //header.setRightWidget(btnActualizar);
        //formBuscar = new FlexTable();
        //lblBuscar = new Label("Buscar:");
        txtBuscar = new MSearchBox();
        txtBuscar.getElement().setPropertyString("placeholder", "escriba aqui");
        //formBuscar.setWidget(0, 0, lblBuscar);
        //formBuscar.setWidget(0, 1, txtBuscar);
        grid = new GridConsultorSinMesa();
        //grid.setAlwaysShowScrollBars(true);
        grid.setMinimumTableWidth(1024, Style.Unit.PX);
        pnlGuardar = new FlowPanel();
        btnGuardar = new com.googlecode.mgwt.ui.client.widget.button.Button("Agregar a Lista");
        pnlGuardar.add(btnGuardar);
        btnGuardar.setConfirm(true);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());
        this.getPnlTabla().add(pnlGuardar);
        this.getPnlBusqueda().add(header);
        HorizontalPanel pnlSearch=new HorizontalPanel();
        pnlSearch.add(txtBuscar); 
        pnlSearch.setWidth("100%");
        this.getPnlBusqueda().add(pnlSearch);
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
        btnBack.addClickHandler(clickHandler);
        btnGuardar.addTouchEndHandler(this);
    }

    ClickHandler clickHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            if (event.getSource().equals(btnBack)) {
                goToUIEditConsultor();
            }
        }

    };

    private void initStyle() {
        MyResource.INSTANCE.getStlModel().ensureInjected();
        btnBack.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
        //btnActualizar.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
        lblCenter.getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);
        pnlGuardar.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
        btnGuardar.setWidth("90%");
        this.getPnlUnion().setWidth("100%");
        MyResource.INSTANCE.getStlModel().ensureInjected();        
        txtBuscar.getElement().getFirstChild().getFirstChild().removeFromParent();        
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight();
        this.getPnlTabla().setHeight(alto - 280 + "px");
        //this.getPnlBotones().setHeight(alto-150 + "px");
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
            grid.getDataProvider().setFilter(txtBuscar.getText());
            grid.getDataProvider().refresh();
    }

    public void setBeanMesa(MesaProxy beanMesa) {
        this.beanMesa = beanMesa;
    }

    @Override
    public void goToUIEditConsultor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadFields() {
        lblCenter.setText(beanMesa.getNomSucursal()+" - "+beanMesa.getDescripcion()+" : "+beanMesa.getNomSupervisor());
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

}
