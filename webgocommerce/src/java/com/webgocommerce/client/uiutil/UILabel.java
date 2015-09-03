/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author SISTEMAS
 */
public class UILabel  extends Label{
    
    public UILabel(String texto){
        initComponents(texto);
        initStyle();
    }
    
    private void initComponents(String texto){
       this.setText(texto);        
    }
    
    private void initStyle(){
        this.getElement().getStyle().setMargin(5, Style.Unit.PX);
        this.setHeight("100%");
        this.getElement().getStyle().setVerticalAlign(Style.VerticalAlign.MIDDLE);        
    }
    
    
}
