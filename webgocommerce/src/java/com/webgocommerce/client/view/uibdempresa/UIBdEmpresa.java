/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uibdempresa;

import com.google.gwt.dom.client.Style.Unit;
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
import com.webgocommerce.client.view.grid.GridBdEmpresa;

/**
 *
 * @author SISTEMAS
 */
public class UIBdEmpresa extends UIFormMantenimiento implements InterUIBdEmpresa,KeyUpHandler/*,ChangeHandler*/{
    //private Label lblBuscar;
    private MSearchBox txtBuscar;
    //private FlexTable formBuscar;
    protected GridBdEmpresa grid;
    /*protected ListModelBdEmpresa lstBdEmpresa;
    protected Label lblDescripcion;*/
    protected Button btnExportar;
    
    public UIBdEmpresa(){
        initComponents();
        initListener();
        initStyle();
        reCalcularWindows();
    }
    
    private void initComponents(){     
        btnExportar=new Button("Exportar");
        //formBuscar=new FlexTable();        
        //lblBuscar=new Label("Buscar:");
        txtBuscar=new MSearchBox();
        txtBuscar.setPlaceHolder("Buscar");
        //txtBuscar.getElement().setPropertyString("placeholder", "escriba aqui");
        /*lstBdEmpresa=new ListModelBdEmpresa();
        lblDescripcion=new Label("AQUI DEBE CAMBIAR EL TEXTO");*/
        //formBuscar.setWidget(0, 0, lblBuscar);
        //formBuscar.setWidget(0, 1, txtBuscar);
        /*formBuscar.setWidget(0, 2, lstBdEmpresa);
        formBuscar.setWidget(0, 3, lblDescripcion);*/
        grid=new GridBdEmpresa();
        grid.setMinimumTableWidth(1024, Unit.PX);
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
    
    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 165;
        this.getPnlTabla().setHeight(alto + "px");
        this.getPnlBotones().setHeight(alto + "px");
    }
    
    private void initListener(){
        txtBuscar.textBox.addKeyUpHandler(this);
        btnExportar.addClickHandler(clickHandler);
        //lstBdEmpresa.addChangeHandler(this);
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
    

    @Override
    public void onKeyUp(KeyUpEvent event) {
        //lblDescripcion.setText(lstBdEmpresa.getSelectedItem().getNombre());
        grid.getDataProvider().setFilter(txtBuscar.getText());
        grid.getDataProvider().refresh();
    }

    /*@Override
    public void onChange(ChangeEvent event) {
        if(event.getSource().equals(lstBdEmpresa)){
            lblDescripcion.setText(lstBdEmpresa.getSelectedItem().getNombre());
            Window.alert("cantidad: "+lstBdEmpresa.getItemCount());
            Window.alert("value: "+lstBdEmpresa.getValue(lstBdEmpresa.getSelectedIndex()));
            Window.alert("Text: "+lstBdEmpresa.getItemText(lstBdEmpresa.getSelectedIndex()));
            Window.alert("Text: "+lstBdEmpresa.getElement(lstBdEmpresa.getValue(lstBdEmpresa.getSelectedIndex())).getIpHost());
        }
    }*/

    @Override
    public void exportarData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
