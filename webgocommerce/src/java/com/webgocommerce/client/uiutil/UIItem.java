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
import com.webgocommerce.client.view.grid.GridItemSingleSelection;

/**
 *
 * @author SISTEMAS
 */
public class UIItem extends Composite{

    private FlowPanel pnlContenedor;
    private GridItemSingleSelection gridItem;

    public UIItem() {
        initComponents();
        initStyle();    
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        gridItem = new GridItemSingleSelection();        
        gridItem.setMinimumTableWidth(512, Style.Unit.PX);
        pnlContenedor.add(gridItem);        
        this.initWidget(pnlContenedor);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                initStyle();
            }
        });
    }

    private void initStyle() {
        int ancho = Window.getClientWidth() - 5;
        int alto = (Window.getClientHeight()) / 3;
        pnlContenedor.setWidth(ancho + "px");
        pnlContenedor.setHeight(alto + "px");        
        gridItem.setHeight("97%");
        gridItem.setWidth("99%");        
        gridItem.setColumnWidth(gridItem.descripcion, 85, Style.Unit.PCT);
        gridItem.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        gridItem.getElement().getStyle().setBorderWidth(0.2, Style.Unit.PX);
        gridItem.getElement().getStyle().setFloat(Style.Float.LEFT);
        gridItem.getElement().getStyle().setMargin(3, Style.Unit.PX);        
    }

    public GridItemSingleSelection getGridItem() {
        return gridItem;
    }      

}
