/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.webgocommerce.client.view.uiloginsuperadmin.DUILoginSuperAdmin;
import com.webgocommerce.client.view.uiloginuser.DUILoginUserImpl;
import java.util.HashMap;

/**
 *
 * @author SISTEMAS
 */
public class MainUser implements EntryPoint{

    @Override
    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {

            @Override
            public void onUncaughtException(Throwable e) {
                Throwable unwrapped=unwrap(e);
                GWT.log(e.getMessage()+"-"+e.getLocalizedMessage(),unwrapped);                
                //Window.alert(e.getMessage());
            }
        });        
        Window.setMargin("0px");
        MGWT.applySettings(MGWTSettings.getAppSetting());
        RootPanel.get().add(new DUILoginUserImpl());   
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
    
    public Throwable unwrap(Throwable e){
        if(e instanceof UmbrellaException){
            UmbrellaException ue=(UmbrellaException)e;
            if(ue.getCauses().size()==1){
                return unwrap(ue.getCauses().iterator().next());
            }
        }
        return e;
    }
    
}
