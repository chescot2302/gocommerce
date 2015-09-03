/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.model;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ToggleButton;

/**
 *
 * @author SISTEMAS
 */
public class ButtonBar extends ToggleButton{
   
    public ButtonBar(Image icon){
        super(icon);
        initSityle();
    }
    
    private void initSityle(){
        this.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
    }
    
    public void setImage(Image icon){
        
    }
}
