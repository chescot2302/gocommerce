/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uicategorialista;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.UIFormMantenimiento;
import com.webgocommerce.client.view.grid.GridCategoriaLista;

/**
 *
 * @author SISTEMAS
 */
public class UICategoriaLista extends UIFormMantenimiento implements KeyUpHandler, InterUICategoriaLista {

    //private Label lblBuscar;
    private MSearchBox txtBuscar;
    //private FlexTable formBuscar;
    protected GridCategoriaLista grid;
    protected Button btnExportar;

    public UICategoriaLista() {
        initComponents();
        initListener();
        initStyle();
        reCalcularWindows();
    }

    private void initComponents() {
        btnExportar=new Button("Exportar");
        //formBuscar = new FlexTable();
        //lblBuscar = new Label("Buscar:");
        txtBuscar = new MSearchBox();
        txtBuscar.setPlaceHolder("Buscar");
        //formBuscar.setWidget(0, 0, lblBuscar);
        //formBuscar.setWidget(0, 1, txtBuscar);
        grid = new GridCategoriaLista();
        grid.setMinimumTableWidth(1024, Style.Unit.PX);
        this.addComponent(btnExportar);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager());
        this.getPnlBusqueda().add(txtBuscar);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }

    private void initListener() {
        txtBuscar.textBox.addKeyUpHandler(this);
        btnExportar.addClickHandler(clickHandler);
    }
    
    ClickHandler clickHandler=new ClickHandler(){

        @Override
        public void onClick(ClickEvent event) {
            if(event.getSource().equals(btnExportar)){
                exportarData();
            }
        }
        
    };
    
    private void initStyle(){
        MyResource.INSTANCE.getStlModel().ensureInjected();        
        txtBuscar.getElement().getFirstChild().getFirstChild().removeFromParent();        
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight()-165;
        this.getPnlTabla().setHeight(alto + "px");
        this.getPnlBotones().setHeight(alto + "px");
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        grid.getDataProvider().setFilter(txtBuscar.getText());
        grid.getDataProvider().refresh();
    }

    @Override
    public void exportarData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
