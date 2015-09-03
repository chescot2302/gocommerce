/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author SISTEMAS
 */
public class UISeparador extends Label{
    
    public UISeparador(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        this.setText("|");
    }
    
    private void initStyle(){
        this.getElement().getStyle().setMargin(5, Style.Unit.PX);
    }
    
    public Label isSpace(){
        this.setText("");
        initStyle();
        return this;
    }
}
