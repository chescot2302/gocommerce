/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.view.grid.GridPrecioXitem;

/**
 *
 * @author SISTEMAS
 */
public class UIInputPrecio extends HorizontalPanel implements KeyUpHandler{
    public TextBox txtInputPrecio; 
    public MSearchBox txtPlan;
    public PushButton btnCombo;
    public Image imgCombo;
    public GridPrecioXitem gridPrecioItem;
    private PopupPanel simplePopup;
    
    public UIInputPrecio(){
        initComponents();
        initListener();
        initStyle();
    }
    
    private void initComponents(){
        txtPlan=new MSearchBox();
        //txtPlan.setPlaceHolder("Buscar plan");
        txtPlan.getElement().setPropertyString("placeholder", "escriba aqui");
        gridPrecioItem=new GridPrecioXitem();        
        txtInputPrecio=new TextBox();
        imgCombo=new Image(MyResource.INSTANCE.getImgAbajo32());
        btnCombo=new PushButton(imgCombo);
        txtInputPrecio.setWidth("350px");  
        simplePopup = new PopupPanel(true);
        gridPrecioItem.setHeight("300px");
        gridPrecioItem.setWidth("600px");
        simplePopup.setWidth("600px");
        simplePopup.setHeight("300px");
        VerticalPanel flow=new VerticalPanel();
        flow.add(txtPlan);
        flow.add(gridPrecioItem);
        flow.setHeight("300px");
        simplePopup.setWidget(flow);
        this.add(txtInputPrecio);
        this.add(btnCombo);
    }
    
    private void initListener(){
        //btnCombo.addClickHandler(this);
        //txtInputPrecio.addKeyUpHandler(this);
        txtPlan.textBox.addKeyUpHandler(this);
    }
    
    private void initStyle(){    
        imgCombo.setWidth("16px");
        imgCombo.setHeight("16px");
        btnCombo.setWidth("16px");
        btnCombo.setHeight("16px");
        txtPlan.getElement().getFirstChild().getFirstChild().removeFromParent();        
    }
    
    
    public void showPopup(){            
            int left = txtInputPrecio.getAbsoluteLeft();
            int top = txtInputPrecio.getAbsoluteTop()+24;
            simplePopup.setPopupPosition(left, top);
            simplePopup.show();
            //gridPrecioItem.getSelectionModel().clear();
            gridPrecioItem.setFocus(true);
    }
    
    public void hidePopup(){        
            simplePopup.hide();
    }

    /*@Override
    public void onClick(ClickEvent event) {
        if(event.getSource().equals(btnCombo)){
            showPopup();
        }
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if(event.getSource().equals(txtInputPrecio)){
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_DOWN) {
                showPopup();
            }else if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE){
                hidePopup();
            }
        }
    }*/

    @Override
    public void onKeyUp(KeyUpEvent event) {
        gridPrecioItem.getDataProvider().setFilter(txtPlan.getText());
        gridPrecioItem.getDataProvider().refresh();
    }
    
}
