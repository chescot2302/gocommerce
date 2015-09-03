/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.model;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class ToolBarButton extends Composite implements ClickHandler{
    private FlexTable pnlContenedor; 
    public HeaderMenu header;
    private Label lblTitulo;    
    private DeckPanel pnlSlide;
    private List<ButtonBar> arrayButton=new ArrayList<ButtonBar>();
    private FlexCellFormatter cellFormatter;
    
    public ToolBarButton(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new FlexTable();
        header=new HeaderMenu();        
        lblTitulo=new Label("TITULO");
        header.setCenterWidget(lblTitulo);
        pnlSlide=new DeckPanel();
        pnlSlide.setAnimationEnabled(true);
        pnlContenedor.setWidget(0, 0, header);
        pnlContenedor.setWidget(2, 0, pnlSlide);
        cellFormatter = pnlContenedor.getFlexCellFormatter();
        initWidget(pnlContenedor);
    }
    
    private void initStyle(){
        lblTitulo.setWidth("100%");
        lblTitulo.setHeight("32px");
        lblTitulo.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);  
        lblTitulo.getElement().getStyle().setProperty("display", "table-cell");
        lblTitulo.getElement().getStyle().setVerticalAlign(Style.VerticalAlign.MIDDLE);
        lblTitulo.getElement().getStyle().setFontSize(20, Style.Unit.PX);
        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);        
        this.setWidth("100%");
    }
    
    public void addSlideBar(ButtonBar button,Widget w){
        arrayButton.add(button);
        pnlSlide.add(w);
        pnlContenedor.setWidget(1, arrayButton.size()-1, button);
        pnlSlide.showWidget(arrayButton.size()-1);
        cellFormatter.setColSpan(0,0,arrayButton.size());
        cellFormatter.setColSpan(2,0,arrayButton.size());
        button.addClickHandler(this);
    }
    
    public void setTitulo(String titulo){
        lblTitulo.setText(titulo);
    }

    @Override
    public void onClick(ClickEvent event) {
       for(int i=0;i<arrayButton.size();i++){
           arrayButton.get(i).setValue(Boolean.FALSE);
           if(event.getSource().equals(arrayButton.get(i))){
               pnlSlide.showWidget(i);
               arrayButton.get(i).setValue(Boolean.TRUE);
           }                      
       }
    }

    public DeckPanel getPnlSlide() {
        return pnlSlide;
    }
    
    public void showSlide(int index){
        pnlSlide.showWidget(index);
    }
    
    
}
