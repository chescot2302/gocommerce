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
import com.webgocommerce.client.view.grid.GridAlmacen;
import com.webgocommerce.client.view.grid.GridItemSingleSelection;

/**
 *
 * @author SISTEMAS
 */
public class UIStockItemAlmacen extends Composite{

    private FlowPanel pnlContenedor;
    private GridItemSingleSelection gridItem;
    private GridAlmacen gridAlmacen;

    public UIStockItemAlmacen() {
        initComponents();
        initStyle();    
    }

    private void initComponents() {
        pnlContenedor = new FlowPanel();
        gridItem = new GridItemSingleSelection();
        gridAlmacen = new GridAlmacen();
        gridItem.setMinimumTableWidth(512, Style.Unit.PX);
        gridAlmacen.setMinimumTableWidth(200, Style.Unit.PX);
        pnlContenedor.add(gridItem);
        pnlContenedor.add(gridAlmacen);
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
        gridItem.setWidth("69%");
        gridAlmacen.setHeight("97%");
        gridAlmacen.setWidth("29%");
        gridItem.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        gridItem.getElement().getStyle().setBorderWidth(0.2, Style.Unit.PX);
        gridItem.getElement().getStyle().setFloat(Style.Float.LEFT);
        gridItem.getElement().getStyle().setMargin(3, Style.Unit.PX);
        gridAlmacen.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        gridAlmacen.getElement().getStyle().setBorderWidth(0.2, Style.Unit.PX);
        gridAlmacen.getElement().getStyle().setFloat(Style.Float.LEFT);
        gridAlmacen.getElement().getStyle().setMargin(3, Style.Unit.PX);
    }

    public GridItemSingleSelection getGridItem() {
        return gridItem;
    }

    public GridAlmacen getGridAlmacen() {
        return gridAlmacen;
    }    

}
