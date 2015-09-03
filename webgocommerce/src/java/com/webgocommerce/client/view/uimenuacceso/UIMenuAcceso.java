/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimenuacceso;

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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.webgocommerce.client.beanproxy.BdUsuarioProxy;
import com.webgocommerce.client.model.HeaderMenu;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.UIFormMantenimiento;
import com.webgocommerce.server.beans.MenuBar;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIMenuAcceso extends UIFormMantenimiento implements  InterUIMenuAcceso, TouchEndHandler {
    private HeaderMenu header;
    private Label lblCenter;
    private PushButton btnBack;
    private PushButton btnActualizar;
    protected BdUsuarioProxy bean; 
    protected FlowPanel pnlGuardar;
    private Button btnGuardar;
    protected ScrollPanel scroll;
   

    public UIMenuAcceso() {
        initComponents();
        initStyle();
        initListener();
        reCalcularWindows();
    }

    private void initComponents() {       
        scroll = new ScrollPanel();
        header = new HeaderMenu();
        lblCenter = new Label("USUARIO");
        btnActualizar = new PushButton(new Image(MyResource.INSTANCE.getImgRefresh32()));
        btnBack = new PushButton(new Image(MyResource.INSTANCE.getImgBack32()));
        btnBack.setTitle("Volver Atras");
        header.setLeftWidget(btnBack);
        header.setCenterWidget(lblCenter);
        header.setRightWidget(btnActualizar);
        pnlGuardar = new FlowPanel();
        btnGuardar = new Button("Guardar");
        pnlGuardar.add(btnGuardar);
        btnGuardar.setConfirm(true);  
        this.getPnlTabla().add(scroll);
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
                showTreeMenuBar();
            }
        }

    };

    private void initStyle() {
        MyResource.INSTANCE.getStlModel().ensureInjected();
        btnBack.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
        btnActualizar.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
        lblCenter.getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);
        pnlGuardar.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
        btnGuardar.setWidth("90%");
        this.getPnlUnion().setWidth("100%");
        MyResource.INSTANCE.getStlModel().ensureInjected();                      
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight();
        this.getPnlTabla().setHeight(alto - 235 + "px");   
        scroll.setHeight(alto - 270 + "px");
        //this.getPnlBotones().setHeight(alto-150 + "px");
    }

    @Override
    public void loadTable() {

    }
   
    @Override
    public void onTouchEnd(TouchEndEvent event) {
        saveAccess();
    }

    @Override
    public void goToUIBdUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveAccess() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showTreeMenuBar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBean(BdUsuarioProxy bean) {
        this.bean = bean;
        lblCenter.setText(bean.getBeanBdEmpresa().getNombre()+" : "+bean.getCorreo());
    }

    

    
}
