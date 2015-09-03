/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.webgocommerce.client.view.uiloginsuperadmin.DUILoginSuperAdminImpl;

/**
 *
 * @author SISTEMAS
 */
public class MainSuperAdmin implements EntryPoint{

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
        /*Button btn=new Button("Click me");
        btn.addClickHandler(new ClickHandler(){

            @Override
            public void onClick(ClickEvent event) {
                PopupProgress popup=new PopupProgress();
                popup.showPopup();
            }
        });*/
        RootPanel.get().add(new DUILoginSuperAdminImpl());                       
        //RootPanel.get().add(btn);
        //ToolBarButton pnl=new ToolBarButton();        
        //pnl.addSlideBar(new ButtonBar(new Image(MyResource.INSTANCE.getImgPerson32())), new HTML("NAVA1"));
        //pnl.addSlideBar(new ButtonBar(new Image(MyResource.INSTANCE.getImgAbajo32())), new HTML("NAVA2"));
        //pnl.addSlideBar(new ButtonBar(new Image(MyResource.INSTANCE.getImgRefresh32())), new HTML("NAVA3"));
        //pnl.setWidth("100%");        
        //RootPanel.get().add(new UISearchAddCliente());
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
