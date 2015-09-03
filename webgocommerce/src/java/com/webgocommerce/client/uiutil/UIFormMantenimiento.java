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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.webgocommerce.client.resource.MyResource;

/**
 *
 * @author root
 */
public class UIFormMantenimiento extends Composite implements ClickHandler {

    public static final int BOTONOPER0 = 0;
    public static final int BOTONOPER1 = 1;
    public static final int BOTONOPER2 = 2;
    public static final int BOTONOPER3 = 3;
    public static final int BOTONOPER4 = 4;
    protected Button btnOper1;
    protected Button btnOper2;
    protected Button btnOper3;
    protected Button btnOper4;
    protected Button btnOper0;

    private FlowPanel pnlContenedor;
    private FlowPanel pnlBotones;
    private FlowPanel pnlBusqueda;
    private FlowPanel pnlTabla;
    private FlowPanel pnlUnion;

    public UIFormMantenimiento() {
        initComponents();
        Estilo();
        initWidget(pnlContenedor);
    }

    private void initComponents() {

        pnlContenedor = new FlowPanel();
        pnlBotones = new FlowPanel();
        pnlBusqueda = new FlowPanel();
        pnlTabla = new FlowPanel();
        pnlUnion = new FlowPanel();

        btnOper1 = new Button("Agregar");
        btnOper2 = new Button("Modificar");
        btnOper3 = new Button("Eliminar");
        btnOper4 = new Button("Detalle");
        btnOper0 = new Button("Actualizar");

        pnlBotones.add(btnOper1);
        pnlBotones.add(btnOper2);
        pnlBotones.add(btnOper3);
        pnlBotones.add(btnOper4);
        pnlBotones.add(btnOper0);

        pnlUnion.add(pnlBusqueda);
        pnlUnion.add(pnlTabla);

        pnlContenedor.add(pnlBotones);
        pnlContenedor.add(pnlUnion);
        btnOper1.addClickHandler(this);
        btnOper2.addClickHandler(this);
        btnOper3.addClickHandler(this);
        btnOper4.addClickHandler(this);
        btnOper0.addClickHandler(this);
    }

    private void Estilo() {
        pnlBotones.getElement().setId("pnlBotones");
        pnlBusqueda.getElement().setId("pnlBusqueda");
        pnlTabla.getElement().setId("pnlTabla");
        pnlContenedor.getElement().setId("pnlContenedor");
        pnlUnion.getElement().setId("pnlUnion");
        btnOper0.getElement().setId("btnActualizar");
        MyResource.INSTANCE.getStlUIFormMantenimiento().ensureInjected();
    }

    public FlowPanel getPnlTabla() {
        return pnlTabla;
    }

    public FlowPanel getPnlBusqueda() {
        return pnlBusqueda;
    }

    public FlowPanel getPnlBotones() {
        return pnlBotones;
    }

    public FlowPanel getPnlUnion() {
        return pnlUnion;
    }
    
    

    @Override
    public void onClick(ClickEvent event) {
        // TODO Auto-generated method stub
        if (event.getSource().equals(btnOper1)) {
            showUIOper1();
        } else if (event.getSource().equals(btnOper2)) {
            showUIOper2();
        } else if (event.getSource().equals(btnOper3)) {
            showUIOper3();
        } else if (event.getSource().equals(btnOper4)) {
            showUIOper4();
        } else if (event.getSource().equals(btnOper0)) {
            showUIOper0();
        }
    }

    public void addComponent(Widget Component) {
        pnlBotones.add(Component);
    }

    public void setRenderizar(int component, String titulo, String toolTip) {
        switch (component) {
            case 0:
                btnOper0.setTitle(toolTip);
                btnOper0.setText(titulo);
                break;
            case 1:
                btnOper1.setTitle(toolTip);
                btnOper1.setText(titulo);
                break;
            case 2:
                btnOper2.setTitle(toolTip);
                btnOper2.setText(titulo);
                break;
            case 3:
                btnOper3.setTitle(toolTip);
                btnOper3.setText(titulo);
                break;
            case 4:
                btnOper4.setTitle(toolTip);
                btnOper4.setText(titulo);
                break;
        }

    }

    public void setVisible(int component, boolean value) {
        switch (component) {
            case 0:
                btnOper0.setVisible(value);
                break;
            case 1:
                btnOper1.setVisible(value);
                break;
            case 2:
                btnOper2.setVisible(value);
                break;
            case 3:
                btnOper3.setVisible(value);
                break;
            case 4:
                btnOper4.setVisible(value);
                break;
        }

    }

    public void showUIOper1() {
        // TODO Auto-generated method stub
        //Window.alert("Sobrescriba el metodo Agregar");
        Notification not=new Notification(Notification.ALERT,"Sobrescriba el metodo Agregar");
        not.showPopup();
    }

    public void showUIOper2() {
        // TODO Auto-generated method stub
        //Window.alert("Sobrescriba el metodo Modificar");
        Notification not=new Notification(Notification.ALERT,"Sobrescriba el metodo Modificar");
        not.showPopup();
    }

    public void showUIOper3() {
        // TODO Auto-generated method stub
        //Window.alert("Sobrescriba el metodo Eliminar");
        Notification not=new Notification(Notification.ALERT,"Sobrescriba el metodo Eliminar");
        not.showPopup();
    }

    public void showUIOper4() {
        // TODO Auto-generated method stub
        //Window.alert("Sobrescriba el metodo Detalle");
        Notification not=new Notification(Notification.ALERT,"Sobrescriba el metodo Detalle");
        not.showPopup();
    }

    public void showUIOper0() {
        // TODO Auto-generated method stub
        loadTable();
    }

    public void loadTable() {

    }

}
