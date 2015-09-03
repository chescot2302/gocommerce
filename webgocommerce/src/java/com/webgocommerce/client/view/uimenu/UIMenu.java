/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimenu;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.UIFormMantenimiento;
import com.webgocommerce.client.view.grid.GridMenu;
import com.webgocommerce.client.view.tree.TreeMenuModel;

/**
 *
 * @author SISTEMAS
 */
public class UIMenu extends UIFormMantenimiento implements KeyUpHandler, InterUIMenu {    
    private MSearchBox txtBuscar;    
    protected GridMenu grid;
    private Button btnVerMenu;
    protected Button btnExportar;

    public UIMenu() {
        initComponents();
        initListener();
        initStyle();
        reCalcularWindows();
    }

    private void initComponents() {    
        btnExportar=new Button("Exportar");
        txtBuscar = new MSearchBox();
        txtBuscar.setPlaceHolder("Escriba aqui");        
        grid = new GridMenu();
        grid.setMinimumTableWidth(1024, Style.Unit.PX);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());
        this.getPnlBusqueda().add(txtBuscar);
        this.getPnlBotones().add(btnExportar);
        btnVerMenu=new Button("Ver Menu");
        this.getPnlBotones().add(btnVerMenu);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }
    
    ClickHandler clickHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            if(event.getSource().equals(btnVerMenu)){
                showTreeMenuBar();
            }else if(event.getSource().equals(btnExportar)){
                exportarData();
            }
        }
        
    };

    private void initListener() {        
        txtBuscar.textBox.addKeyUpHandler(this);
        btnVerMenu.addClickHandler(clickHandler);
        btnExportar.addClickHandler(clickHandler);
    }
    
    private void initStyle(){
        MyResource.INSTANCE.getStlModel().ensureInjected();        
        txtBuscar.getElement().getFirstChild().getFirstChild().removeFromParent();        
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 150;
        this.getPnlTabla().setHeight(alto + "px");
        this.getPnlBotones().setHeight(alto + "px");
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        grid.getDataProvider().setFilter(txtBuscar.getText());
        grid.getDataProvider().refresh();
    }

    @Override
    public void showTreeMenuBar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportarData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
