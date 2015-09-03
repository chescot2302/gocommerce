/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uiselectuser;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.webgocommerce.client.model.UIMantenimiento;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.view.grid.GridUsuario;

/**
 *
 * @author SISTEMAS
 */
public class UISelectUser extends UIMantenimiento implements InterUISelectUser,KeyUpHandler{
    private PushButton btnSelect;
    private MSearchBox txtBuscar;
    private FlowPanel pnlTable;
    protected GridUsuario grid;
    protected String idPuntoEmision;
    protected String idDocumento;
    

    public UISelectUser() {
        initComponents();
        initListener();
        initStyle();
        reCalcularWindows();
    }
    
    private void initComponents(){
        lblCenter.setText("SELECCIONAR USUARIO");
        btnSelect=new PushButton(new Image(MyResource.INSTANCE.getImgSelect32()));
        header.setRightWidget(btnSelect);
        btnOperacion.removeFromParent();
        pnlForm.removeFromParent();
        txtBuscar=new MSearchBox();        
        contenido.add(txtBuscar);
        txtBuscar.setPlaceHolder("Buscar usuario");
        pnlTable=new FlowPanel();
        grid=new GridUsuario();
        pnlTable.add(grid);
        pnlTable.add(grid.getPager());
        contenido.add(pnlTable);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }
    
    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 220;
        this.scrollPanel.setHeight(alto + "px");
        txtBuscar.setWidth(Window.getClientWidth()+"px");
        pnlTable.setHeight(alto-30 + "px");
    }
    
    private void initListener(){
        btnSelect.addClickHandler(clickHandler);   
        txtBuscar.textBox.addKeyUpHandler(this);
    }
    
    ClickHandler clickHandler=new ClickHandler(){

        @Override
        public void onClick(ClickEvent event) {
            if(event.getSource().equals(btnSelect)){
                seleccionar();
            }
        }
        
    };
    
    private void initStyle(){
        MyResource.INSTANCE.getStlModel().ensureInjected();
        btnSelect.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());       
        txtBuscar.getElement().getFirstChild().getFirstChild().removeFromParent();        
    }

    @Override
    public void loadTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setIdPuntoEmision(String idPuntoEmision) {
        this.idPuntoEmision = idPuntoEmision;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }        

    @Override
    public void seleccionar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        grid.getDataProvider().setFilter(txtBuscar.getText());
        grid.getDataProvider().refresh();
    }
    
    
    
    
}
