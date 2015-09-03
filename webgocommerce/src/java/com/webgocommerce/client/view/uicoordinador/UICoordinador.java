/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uicoordinador;

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
import com.webgocommerce.client.beanproxy.CoordinadorProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.UIFormMantenimiento;
import com.webgocommerce.client.view.grid.GridCoordinador;
import java.util.Date;

/**
 *
 * @author SISTEMAS
 */
public class UICoordinador extends UIFormMantenimiento implements InterUICoordinador,KeyUpHandler{
    private MSearchBox txtBuscar;
    protected GridCoordinador grid;
    protected Button btnExportar;
    protected Button btnActivar;
    protected Button btnDesactivar;
    
    public UICoordinador(){
        initComponents();
        initListener();
        initStyle();
        reCalcularWindows();
    }
    
    private void initComponents(){   
        btnActivar=new Button("Activar");
        btnDesactivar=new Button("Desactivar");
        btnExportar=new Button("Exportar");
        txtBuscar=new MSearchBox();
        txtBuscar.setPlaceHolder("Buscar");
        grid=new GridCoordinador();
        grid.setMinimumTableWidth(1024, Style.Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());                
        this.getPnlBusqueda().add(txtBuscar);
        this.addComponent(btnActivar);
        this.addComponent(btnDesactivar);
        this.addComponent(btnExportar);
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
    
    private void initListener(){
        txtBuscar.textBox.addKeyUpHandler(this);
        btnExportar.addClickHandler(clickHandler);
        btnActivar.addClickHandler(clickHandler);
        btnDesactivar.addClickHandler(clickHandler);
    }
    
    ClickHandler clickHandler=new ClickHandler(){

        @Override
        public void onClick(ClickEvent event) {
            if(event.getSource().equals(btnExportar)){
                exportarData();
            }else if(event.getSource().equals(btnActivar)){
                activar();
            }else if(event.getSource().equals(btnDesactivar)){
                desactivar();
            }
        }
        
    };
    
    @Override
    public void activar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void desactivar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void exportarData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void processActivar(CoordinadorProxy bean, Date fechaIncorporacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processDesactivar(CoordinadorProxy bean, Date fechaIncorporacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
