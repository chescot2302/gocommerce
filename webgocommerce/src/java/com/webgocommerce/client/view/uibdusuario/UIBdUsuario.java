/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uibdusuario;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.UIFormMantenimiento;
import com.webgocommerce.client.view.grid.GridBdUsuario;

/**
 *
 * @author SISTEMAS
 */
public class UIBdUsuario extends UIFormMantenimiento implements InterUIBdUsuario,KeyUpHandler{
    //private Label lblBuscar;
    private MSearchBox txtBuscar;
    //private FlexTable formBuscar;
    protected GridBdUsuario grid;
    private Button btnVerMenu;
    protected Button btnExportar;
    protected Button btnConexionServer;
    
    public UIBdUsuario(){
        initComponents();
        initListener();
        initStyle();
        reCalcularWindows();
    }
    
    private void initComponents(){    
        btnExportar=new Button("Exportar");
        btnConexionServer=new Button("Ver Conexion");
        //formBuscar=new FlexTable();        
        //lblBuscar=new Label("Buscar:");
        txtBuscar=new MSearchBox();
        txtBuscar.setPlaceHolder("Buscar");
        //formBuscar.setWidget(0, 0, lblBuscar);
        //formBuscar.setWidget(0, 1, txtBuscar);
        grid=new GridBdUsuario();
        grid.setMinimumTableWidth(1024, Style.Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());                
        this.getPnlBusqueda().add(txtBuscar);
        btnVerMenu=new Button("Ver Menu");
        this.addComponent(btnExportar);
        this.addComponent(btnVerMenu);
        this.addComponent(btnConexionServer);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }
    
    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 165;
        this.getPnlTabla().setHeight(alto + "px");
        this.getPnlBotones().setHeight(alto + "px");
    }
    
    ClickHandler clickHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            if(event.getSource().equals(btnVerMenu)){
                showUIMenuAcceso();
            }else if(event.getSource().equals(btnExportar)){
                exportarData();
            }else if(event.getSource().equals(btnConexionServer)){
                verConexionServer();
            }
        }
        
    };   
    
    @Override
    public void exportarData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void initListener(){
        txtBuscar.textBox.addKeyUpHandler(this);
        btnVerMenu.addClickHandler(clickHandler);
        btnExportar.addClickHandler(clickHandler);
        btnConexionServer.addClickHandler(clickHandler);
    }
    
    private void initStyle(){
        MyResource.INSTANCE.getStlModel().ensureInjected();        
        txtBuscar.getElement().getFirstChild().getFirstChild().removeFromParent();        
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        grid.getDataProvider().setFilter(txtBuscar.getText());
        grid.getDataProvider().refresh();
    }

    @Override
    public void showUIMenuAcceso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void verConexionServer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
