/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.uiutil;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 * @author root
 */
public class UIMantRegistrar extends DialogBox implements ClickHandler {
    public final static String MODOINSERTAR = "INSERTAR";
    public final static String MODOUPDATE = "UPDATE";
    public final static String MODODELETE = "DELETE";
    public final static String MODODETALLE = "DETALLE";
    private FlowPanel pnlContenedorDialog;
    private FlowPanel pnlContenido;
    private FlowPanel pnlBotones;
    private Button btnRegistrar;
    private Button btnCancelar;
    protected String modo;

    public UIMantRegistrar(String titulo, String modo) {
        this.setText(titulo + " - " + modo);
        this.modo = modo;
        initComponents();
        initListener();
        initStyle();        
        this.setAnimationEnabled(true);
        this.setModal(true);
        this.center();        
    }

    private void initComponents() {
        this.setGlassEnabled(true);        
        pnlContenedorDialog = new FlowPanel();
        pnlContenido = new FlowPanel();

        pnlBotones = new FlowPanel();
        btnRegistrar = new Button("Registrar");
        btnCancelar = new Button("Cancelar");
        pnlBotones.add(btnRegistrar);
        pnlBotones.add(btnCancelar);

        pnlContenedorDialog.add(pnlContenido);
        pnlContenedorDialog.add(pnlBotones);

        this.setWidget(pnlContenedorDialog);
        if (modo.equalsIgnoreCase(MODODETALLE)) {
            btnRegistrar.setEnabled(false);
        }
    }
        

    private void initListener() {
        btnCancelar.addClickHandler(this);
        btnRegistrar.addClickHandler(this);
    }

    private void initStyle() {
        pnlBotones.getElement().setId("pnlBotonesDialog");
    }

    public void cerrarDialog() {
        this.hide();
    }

    public FlowPanel getPnlContenido() {
        return pnlContenido;
    }

    public String getModo() {
        return modo;
    }

    public void processInsertar() {
        // TODO Auto-generated method stub		
    }

    public void processActualizar() {
        // TODO Auto-generated method stub		
    }

    public void processEliminar() {
        // TODO Auto-generated method stub		
    }
    
    public void setButtonAcept(String operacion){
        btnRegistrar.setText(operacion);
    }

    @Override
    public void onClick(ClickEvent event) {
        // TODO Auto-generated method stub
        if (event.getSource().equals(btnCancelar)) {
            cerrarDialog();
        } else if (event.getSource().equals(btnRegistrar)) {
            if (modo.equalsIgnoreCase(MODOINSERTAR)) {                
                processInsertar();
            } else if (modo.equalsIgnoreCase(MODOUPDATE)) {                
                processActualizar();
            } else if (modo.equalsIgnoreCase(MODODELETE)) {
                processEliminar();
            } else {
                //Window.alert("Operación no contemplada");
                Notification not=new Notification(Notification.ALERT,"Operación no contemplada");
                not.showPopup();
            }
        }
    }

}
