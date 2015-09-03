/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uiestproy;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.webgocommerce.client.beanproxy.CabeceraVentaProxy;
import com.webgocommerce.client.uiutil.UILabel;
import com.webgocommerce.client.view.grid.GridEstProy;

/**
 *
 * @author SISTEMAS
 */
public class UIEstProy extends PopupPanel implements InterUIEstProy,ClickHandler,CloseHandler{    
    private UILabel lblProy;
    private TextBox txtProyecto;
    protected GridEstProy grid;
    protected ListBox lstEstadoActual;
    protected TextArea txtComentario;
    private Button btnActualizar;
    protected CabeceraVentaProxy bean;
    
    public UIEstProy(){
        initComponents();
        initListener();
        initStyle();
    }
    
    private void initComponents(){
        txtComentario=new TextArea();
        btnActualizar=new Button("Actualizar");
        lstEstadoActual= new ListBox();
        lstEstadoActual.addItem("APROBADO");
        lstEstadoActual.addItem("ENVIADO A CREDITOS");
        lstEstadoActual.addItem("OBSERVADO");
        lstEstadoActual.addItem("OBSERVADO POR ACTIVACIONES");
        lstEstadoActual.addItem("PENDIENTE SUBSANACION");
        lstEstadoActual.addItem("APROBADO Y ENVIADO A ACTIVACIONES");
        lstEstadoActual.addItem("ENVIADO A POOL DE PORTABILIDAD");
        lstEstadoActual.addItem("APROB X ACTIVACIONES Y ENVIADO A DESPACHO");
        lstEstadoActual.addItem("APROB X DESPACHOS Y ENVIADO A ALMACEN");
        lstEstadoActual.addItem("ACTIVACION Y DESPACHO DE PORTABILIDAD");
        lstEstadoActual.addItem("RECHAZADO");
        lstEstadoActual.addItem("ANULADO");
        lstEstadoActual.addItem("PENDIENTE EN EJECUCION");
        lstEstadoActual.addItem("ATENDIDOS FIJA");
        VerticalPanel pnlContenedor=new VerticalPanel();
        lblProy=new UILabel("SEC / SOT / PROYECTO");
        txtProyecto=new TextBox();
        txtProyecto.setEnabled(false);
        HorizontalPanel pnlProy=new HorizontalPanel();
        pnlProy.add(lblProy);
        pnlProy.add(txtProyecto);
        grid=new GridEstProy();
        grid.setWidth("600px");
        grid.setHeight("250px");
        Grid advancedOptions = new Grid(3, 2);
        advancedOptions.setCellSpacing(6);
        advancedOptions.setHTML(0, 0, "NUEVO ESTADO");
        advancedOptions.setWidget(0, 1, lstEstadoActual);        
        advancedOptions.setHTML(1, 0, "COMENTARIO");
        advancedOptions.setWidget(1, 1, txtComentario);                
        advancedOptions.setWidget(2, 1,btnActualizar);
        DisclosurePanel advancedDisclosure = new DisclosurePanel("Actualizar Estado");
        advancedDisclosure.setAnimationEnabled(true);
        advancedDisclosure.ensureDebugId("cwDisclosurePanel");
        advancedDisclosure.setContent(advancedOptions);
        pnlContenedor.add(pnlProy);
        pnlContenedor.add(grid);
        pnlContenedor.add(advancedDisclosure);
        pnlContenedor.setWidth("600px");  
        this.addCloseHandler(this);
        this.add(pnlContenedor);
        this.setGlassEnabled(true);
        this.setAnimationEnabled(true);
        this.setModal(true);
        this.setAutoHideEnabled(true);
        this.setSize("600px", "300px");
        this.center();
    }
    
    private void initListener(){
        btnActualizar.addClickHandler(this);
    }
    
    private void initStyle(){
        grid.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        grid.getElement().getStyle().setBorderWidth(0.2, Style.Unit.PX);        
        
    }

    @Override
    public void loadTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBean(CabeceraVentaProxy bean) {
        this.bean = bean;
        txtProyecto.setText(this.bean.getCodProy());
    }

    @Override
    public void processChangeState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onClick(ClickEvent event) {
        processChangeState();
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

    @Override
    public void onClose(CloseEvent event) {
        loadTableFather();
    }

    @Override
    public void loadTableFather() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
