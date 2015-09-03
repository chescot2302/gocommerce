/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.webgocommerce.client.view.grid.GridRegistroVenta;

/**
 *
 * @author SISTEMAS
 */
public class UIGridRegistroVenta extends Composite{
    private FlowPanel pnlContenedor;
    public GridRegistroVenta grid;
    
    public UIGridRegistroVenta(){
        initComponents();
        initStyle();
    }    
    
    private void initComponents(){  
        pnlContenedor=new FlowPanel();
        grid=new GridRegistroVenta();
        grid.setMinimumTableWidth(1024, Style.Unit.PX);
        pnlContenedor.add(grid);
        this.initWidget(pnlContenedor);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                initStyle();
            }
        });
    }
    
    private void initStyle(){
        int ancho = Window.getClientWidth() - 5;
        int alto = (Window.getClientHeight())/3;
        pnlContenedor.setWidth(ancho + "px");
        pnlContenedor.setHeight(alto+"px");
        grid.setWidth("99%");        
        grid.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        grid.getElement().getStyle().setBorderWidth(0.2, Style.Unit.PX);
        grid.getElement().getStyle().setFloat(Style.Float.LEFT);
        grid.getElement().getStyle().setMargin(3, Style.Unit.PX);        
    }
    
    
}
