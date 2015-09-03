/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.webgocommerce.client.resource.MyResource;

/**
 *
 * @author SISTEMAS
 */
public class Main implements EntryPoint,ClickHandler {

    private Image img = new Image(MyResource.INSTANCE.getImgWallPaper());
    private Button btnEntrar = new Button("ENTRAR");
    private FlowPanel panel = new FlowPanel();
    
    @Override
    public void onModuleLoad() {
        Window.setMargin("0px");
        MGWT.applySettings(MGWTSettings.getAppSetting());
        panel.add(btnEntrar);
        panel.add(img);        
        RootPanel.get().add(panel);
        initListener();
        initStyle();
        //History.newItem("X");
        //History.addValueChangeHandler(valueChangeHandler);
    }
    
    /*ValueChangeHandler<String> valueChangeHandler=new ValueChangeHandler<String>(){

		@Override
		public void onValueChange(ValueChangeEvent<String> event) {
			// TODO Auto-generated method stub
			String historyToken=event.getValue();
			if (!historyToken.equals("X"))
				  History.newItem("X");
		}

		
		
	};*/
    
    private void initListener(){
        btnEntrar.addClickHandler(this);
    }
    
    private void initStyle() {        
        int alto = Window.getClientHeight();        
        img.setWidth("100%");
        img.setHeight(alto + "px");        
        btnEntrar.getElement().setId("btnEntrar");
    }

    @Override
    public void onClick(ClickEvent event) {
        String url = Window.Location.getHref();
        url = url.concat("indexuser.html");
        Window.Location.assign(url);
        /*Notification not=new Notification();
        not.showPopup();*/
    }
    
}
