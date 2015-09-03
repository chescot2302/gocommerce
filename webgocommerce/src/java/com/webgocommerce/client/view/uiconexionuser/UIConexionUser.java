/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uiconexionuser;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.webgocommerce.client.beanproxy.BdUsuarioProxy;
import com.webgocommerce.client.model.HeaderMenu;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.UIFormMantenimiento;
import com.webgocommerce.client.view.grid.GridDataSesion;

/**
 *
 * @author SISTEMAS
 */
public class UIConexionUser extends UIFormMantenimiento implements  InterUIConexionUser, TouchEndHandler {
    private HeaderMenu header;
    private Label lblCenter;
    private PushButton btnBack;
    private PushButton btnActualizar;
    protected BdUsuarioProxy bean; 
    protected GridDataSesion grid;
    private Button btnGuardar;
    protected FlowPanel pnlGuardar;
   

    public UIConexionUser() {
        initComponents();
        initStyle();
        initListener();
        reCalcularWindows();
    }

    private void initComponents() {   
        grid=new GridDataSesion();
        grid.setMinimumTableWidth(1024, Style.Unit.PX);
        header = new HeaderMenu();
        lblCenter = new Label("USUARIO");
        btnActualizar = new PushButton(new Image(MyResource.INSTANCE.getImgRefresh32()));
        btnBack = new PushButton(new Image(MyResource.INSTANCE.getImgBack32()));
        btnBack.setTitle("Volver Atras");
        header.setLeftWidget(btnBack);
        header.setCenterWidget(lblCenter);
        header.setRightWidget(btnActualizar);
        pnlGuardar = new FlowPanel();
        btnGuardar = new Button("Kill Session");
        pnlGuardar.add(btnGuardar);
        btnGuardar.setConfirm(true);  
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());
        this.getPnlTabla().add(pnlGuardar);
        this.getPnlBusqueda().add(header);
        this.getPnlBotones().setVisible(false);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }

    private void initListener() {
        btnBack.addClickHandler(clickHandler);
        btnActualizar.addClickHandler(clickHandler);
        btnGuardar.addTouchEndHandler(this);
    }

    ClickHandler clickHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            if (event.getSource().equals(btnBack)) {
                goToUIBdUsuario();
            } else if (event.getSource().equals(btnActualizar)) {
                loadTable();
            }
        }

    };

    private void initStyle() {
        MyResource.INSTANCE.getStlModel().ensureInjected();
        btnBack.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
        btnActualizar.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
        lblCenter.getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);
        this.getPnlUnion().setWidth("100%");
        pnlGuardar.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
        btnGuardar.setWidth("90%");
        MyResource.INSTANCE.getStlModel().ensureInjected();                              
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight();
        this.getPnlTabla().setHeight(alto - 235 + "px");   
        //this.getPnlBotones().setHeight(alto-150 + "px");
    }

    @Override
    public void loadTable() {

    }
   
    @Override
    public void onTouchEnd(TouchEndEvent event) {
        killSession();
    }

    @Override
    public void goToUIBdUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  

    public void setBean(BdUsuarioProxy bean) {
        this.bean = bean;
        lblCenter.setText(bean.getBeanBdEmpresa().getNombre()+" : "+bean.getCorreo());
    }

    @Override
    public void killSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
}
