/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uieditlistaprecio;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.webgocommerce.client.beanproxy.ListaPrecioProxy;
import com.webgocommerce.client.model.HeaderMenu;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.UIFormMantenimiento;
import com.webgocommerce.client.uiutil.UISeparador;
import com.webgocommerce.client.view.grid.GridPrecioItem;

/**
 *
 * @author SISTEMAS
 */
public class UIEditListaPrecio extends UIFormMantenimiento implements KeyUpHandler,InterUIEditListaPrecio,ChangeHandler,TouchEndHandler{
    private HeaderMenu header;
    private Label lblCenter;
    private Label lblRight;
    private PushButton btnBack;
    private Label lblBuscar;
    protected TextBox txtBuscar;
    private Label lblEstado;
    protected ListBox lstEstado;
    private FlexTable formBuscar;
    protected GridPrecioItem grid;
    protected ListaPrecioProxy beanListaPrecio;
    private FlowPanel pnlGuardar;
    private Button btnGuardar; 
    protected com.google.gwt.user.client.ui.Button btnExportar;

    public UIEditListaPrecio() {
        initComponents();
        initStyle();
        initListener();
        reCalcularWindows();
    }

    private void initComponents() {
        btnExportar=new com.google.gwt.user.client.ui.Button("Exportar");
        header = new HeaderMenu();
        lblCenter = new Label("CATEGORIA - LISTA");
        header.setCenterWidget(lblCenter);
        lblRight=new Label("PREPAGO");
        header.setRightWidget(lblRight);
        btnBack = new PushButton(new Image(MyResource.INSTANCE.getImgBack32()));
        btnBack.setTitle("Volver Atras");
        header.setLeftWidget(btnBack);
        formBuscar = new FlexTable();
        lblBuscar = new Label("Buscar:");
        txtBuscar = new TextBox();
        txtBuscar.getElement().setPropertyString("placeholder", "escriba aqui");
        lblEstado=new Label("Estado");
        lstEstado=new ListBox();
        lstEstado.addItem("T");
        lstEstado.addItem("A");
        lstEstado.addItem("D");
        formBuscar.setWidget(0, 0, lblBuscar);
        formBuscar.setWidget(0, 1, txtBuscar);        
        formBuscar.setWidget(0, 2, new UISeparador());        
        formBuscar.setWidget(0, 3, lblEstado);
        formBuscar.setWidget(0, 4, lstEstado);
        grid = new GridPrecioItem();
        //grid.setAlwaysShowScrollBars(true);
        grid.setMinimumTableWidth(1024, Style.Unit.PX);
        this.addComponent(btnExportar);
        pnlGuardar=new FlowPanel();
        btnGuardar=new Button("Actualizar Precios");
        pnlGuardar.add(btnGuardar);
        btnGuardar.setConfirm(true);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());
        this.getPnlTabla().add(pnlGuardar);
        this.getPnlBusqueda().add(header);
        this.getPnlBusqueda().add(formBuscar);
        this.setRenderizar(UIFormMantenimiento.BOTONOPER4, "Desactivar Item", "Desactivar Precio de Lista");
        this.setRenderizar(UIFormMantenimiento.BOTONOPER1, "Agregar Item", "Agregar Item a la Lista");
        this.setRenderizar(UIFormMantenimiento.BOTONOPER3, "Eliminar Item", "Eliminar Item de la Lista");
        this.btnOper2.setVisible(false);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });        
    }

    private void initListener() {
        txtBuscar.addKeyUpHandler(this);
        btnBack.addClickHandler(clickHandler);
        lstEstado.addChangeHandler(this);
        btnGuardar.addTouchEndHandler(this);
        btnExportar.addClickHandler(clickHandler);
    }
    
    ClickHandler clickHandler=new ClickHandler(){

        @Override
        public void onClick(ClickEvent event) {
            if(event.getSource().equals(btnBack)){
                goToUIListaPrecio();
            }else if(event.getSource().equals(btnExportar)){
                exportarData();
            }
        }
        
    };
    
    private void initStyle(){
        MyResource.INSTANCE.getStlModel().ensureInjected();
        btnBack.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
        lblCenter.getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);   
        pnlGuardar.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
        btnGuardar.setWidth("90%");
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight();
        this.getPnlTabla().setHeight(alto-280 + "px");
        this.getPnlBotones().setHeight(alto-150 + "px");
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        grid.getDataProvider().setFilter(txtBuscar.getText());
        grid.getDataProvider().refresh();
    }

    public void setBeanListaPrecio(ListaPrecioProxy beanListaPrecio) {
        this.beanListaPrecio = beanListaPrecio;
    }

    @Override
    public void goToUIListaPrecio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadFields() {
        lblCenter.setText(beanListaPrecio.getBeanCategoriaLista().getDescripcion()+" - "+beanListaPrecio.getDescripcion());
        lblRight.setText(beanListaPrecio.getCondicion());
    }
    
    @Override
    public void loadTable() {

    }

    @Override
    public void loadTableEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onChange(ChangeEvent event) {
        if(event.getSource().equals(lstEstado)){
            loadTableEstado();
        }
    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
        if(event.getSource().equals(btnGuardar)){
            updatePrices();
        }
    }

    @Override
    public void updatePrices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modoGrid(String modo) {        
        grid.setModo(modo);
        if(modo.equalsIgnoreCase(GridPrecioItem.L)){
            btnGuardar.setDisabled(true);   
            btnOper1.setEnabled(false);
            btnOper2.setEnabled(false);
            btnOper3.setEnabled(false);
            btnOper4.setEnabled(false);
        }else if(modo.equalsIgnoreCase(GridPrecioItem.E)){
            btnGuardar.setDisabled(false);
            btnOper1.setEnabled(true);
            btnOper2.setEnabled(true);
            btnOper3.setEnabled(true);
            btnOper4.setEnabled(true);
        }
    }

    @Override
    public void exportarData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
                  
}

