/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uieditconsultor;

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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.webgocommerce.client.beanproxy.MesaProxy;
import com.webgocommerce.client.model.HeaderMenu;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.UIFormMantenimiento;
import com.webgocommerce.client.uiutil.UISeparador;
import com.webgocommerce.client.view.grid.GridSuperVen;

/**
 *
 * @author SISTEMAS
 */
public class UIEditConsultor extends UIFormMantenimiento implements KeyUpHandler,InterUIEditConsultor,ChangeHandler,TouchEndHandler{
    private HeaderMenu header;
    private Label lblCenter;
    private Label lblRight;
    private PushButton btnBack;
    private Label lblBuscar;
    protected TextBox txtBuscar;
    private Label lblEstado;
    protected ListBox lstEstado;
    private FlexTable formBuscar;
    protected GridSuperVen grid;
    protected MesaProxy beanMesa;
    protected com.google.gwt.user.client.ui.Button btnExportar;

    public UIEditConsultor() {
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
        grid = new GridSuperVen();
        //grid.setAlwaysShowScrollBars(true);
        grid.setMinimumTableWidth(1024, Style.Unit.PX);
        this.addComponent(btnExportar);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());
        this.getPnlBusqueda().add(header);
        this.getPnlBusqueda().add(formBuscar);
        this.setRenderizar(UIFormMantenimiento.BOTONOPER4, "Desactivar Consultor", "Desactivar Consultor");
        this.setRenderizar(UIFormMantenimiento.BOTONOPER1, "Agregar Consultor", "Agregar Consultor");
        this.setRenderizar(UIFormMantenimiento.BOTONOPER3, "Eliminar Consultor", "Eliminar Consultor");
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
        btnExportar.addClickHandler(clickHandler);
    }
    
    ClickHandler clickHandler=new ClickHandler(){

        @Override
        public void onClick(ClickEvent event) {
            if(event.getSource().equals(btnBack)){
                goToUIMesa();
            }else if(event.getSource().equals(btnExportar)){
                exportarData();
            }
        }
        
    };
    
    private void initStyle(){
        MyResource.INSTANCE.getStlModel().ensureInjected();
        btnBack.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
        lblCenter.getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);   
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight();
        this.getPnlTabla().setHeight(alto-229 + "px");
        this.getPnlBotones().setHeight(alto-150 + "px");
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
    public void loadFields() {
        lblCenter.setText(beanMesa.getNomSucursal()+" - "+beanMesa.getDescripcion()+" : "+beanMesa.getNomSupervisor());
        lblRight.setText(beanMesa.getIdMesa().toString());
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
    }

    @Override
    public void modoGrid(String modo) {        
        if(modo.equalsIgnoreCase(GridSuperVen.L)){  
            btnOper1.setEnabled(false);
            btnOper2.setEnabled(false);
            btnOper3.setEnabled(false);
            btnOper4.setEnabled(false);
        }else if(modo.equalsIgnoreCase(GridSuperVen.E)){
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

    @Override
    public void goToUIMesa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
                  
}

