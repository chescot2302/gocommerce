/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.model;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.webgocommerce.client.resource.MyResource;

/**
 *
 * @author SISTEMAS
 */
public class JBorderLayout extends Composite{
    public static final int NORTE=0;
    public static final int OESTE=1;
    public static final int CENTRO=2;
    public static final int ESTE=3;        
    public static final int SUR=4;    
    private FlexTable pnlContenedor;
    private FlowPanel pnlNorte;    
    private FlexTable pnlCenter;
    private FlowPanel pnlOeste;    
    private FlowPanel pnlEste;    
    private FlowPanel pnlCentro;    
    private FlowPanel pnlSur;
    
    public JBorderLayout(){
        initComponents();  
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new FlexTable();
        pnlNorte=new FlowPanel();
        pnlCenter=new FlexTable();
        pnlOeste=new FlowPanel();
        pnlEste=new FlowPanel();
        pnlCentro=new FlowPanel();
        pnlSur=new FlowPanel();
        pnlContenedor.setWidget(0, 0, pnlNorte);
        pnlContenedor.setWidget(1, 0, pnlCenter);
        pnlContenedor.setWidget(2, 0, pnlSur);
        pnlCenter.setWidget(0, 0, pnlOeste);
        pnlCenter.setWidget(0, 1, pnlCentro);
        pnlCenter.setWidget(0, 2, pnlEste);   
        this.initWidget(pnlContenedor);
    }
    
    private void initStyle(){
        pnlContenedor.getElement().setId("pnlContenedor");
        pnlNorte.getElement().setId("pnlNorte");
        pnlSur.getElement().setId("pnlSur");
        pnlCenter.getElement().setId("pnlCenter");
        pnlOeste.getElement().setId("pnlOeste");
        pnlCentro.getElement().setId("pnlCentro");
        pnlEste.getElement().setId("pnlEste");        
        MyResource.INSTANCE.getStlJBorderLayout().ensureInjected();
    }
        
    
    public void add(int ubicacion,Widget component){
        switch(ubicacion){
            case 0:
                pnlNorte.add(component);
                break;
            case 1:
                pnlOeste.add(component);
                break;
            case 2:
                pnlCentro.add(component);
                break;
            case 3:
                pnlEste.add(component);
                break;
            case 4:
                pnlSur.add(component);
                break;                         
        }
    }      
    
    
    
    
}
