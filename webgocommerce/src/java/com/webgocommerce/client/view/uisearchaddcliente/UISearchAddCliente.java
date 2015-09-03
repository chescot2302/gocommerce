/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uisearchaddcliente;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.webgocommerce.client.model.ButtonBar;
import com.webgocommerce.client.model.ToolBarButton;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.UIBuscarCliente;
import com.webgocommerce.client.uiutil.UIFormCliente;
import com.webgocommerce.shared.FieldVerifier;

/**
 *
 * @author SISTEMAS
 */
public class UISearchAddCliente extends Composite implements InterUISearchAddCliente, ClickHandler, ChangeHandler, KeyUpHandler, TouchEndHandler {

    private ToolBarButton toolBar;
    public UIFormCliente formCliente;
    private ButtonBar btnBarRegistrarCliente;
    private ButtonBar btnBarBuscarCliente;
    private PushButton btnBack;
    protected UIBuscarCliente uiBuscarCliente;

    public UISearchAddCliente() {
        initComponents();
        initListener();
        initStyle();
        reCalcularWindows();
    }

    private void initComponents() {
        toolBar = new ToolBarButton();
        toolBar.setTitulo("CLIENTES");
        btnBack = new PushButton(new Image(MyResource.INSTANCE.getImgBack32()));
        btnBack.setTitle("Volver Atras");
        toolBar.header.setLeftWidget(btnBack);
        formCliente = new UIFormCliente();
        btnBarRegistrarCliente = new ButtonBar(new Image(MyResource.INSTANCE.getImgPerson32()));
        btnBarBuscarCliente = new ButtonBar(new Image(MyResource.INSTANCE.getImgSearch32()));
        toolBar.addSlideBar(btnBarRegistrarCliente, formCliente);
        uiBuscarCliente = new UIBuscarCliente();
        toolBar.addSlideBar(btnBarBuscarCliente, uiBuscarCliente);
        initWidget(toolBar);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }

    private void initListener() {
        btnBack.addClickHandler(this);
        formCliente.lstPais.addChangeHandler(this);
        formCliente.lstDepartamento.addChangeHandler(this);
        formCliente.lstProvincia.addChangeHandler(this);
        formCliente.btnGuardar.addClickHandler(this);
        uiBuscarCliente.txtBuscar.textBox.addKeyUpHandler(this);
        uiBuscarCliente.btnGuardar.addTouchEndHandler(this);
    }

    private void initStyle() {
        formCliente.setWidth("99%");
        MyResource.INSTANCE.getStlModel().ensureInjected();
        btnBack.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 260;
        formCliente.scrollPanel.setHeight(alto + "px");
    }

    public UIFormCliente getFormCliente() {
        return formCliente;
    }

    @Override
    public void onClick(ClickEvent event) {
        if (event.getSource().equals(btnBack)) {
            goToBack();
        } else if (event.getSource().equals(formCliente.btnGuardar)) {
            processInsertar();
        }
    }

    @Override
    public void goToBack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ToolBarButton getToolBar() {
        return toolBar;
    }

    public ButtonBar getBtnBarRegistrarCliente() {
        return btnBarRegistrarCliente;
    }

    public ButtonBar getBtnBarBuscarCliente() {
        return btnBarBuscarCliente;
    }

    @Override
    public void loadPais() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadDepartamento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadProvincia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadDistrito() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onChange(ChangeEvent event) {
        if (event.getSource().equals(formCliente.lstPais)) {
            loadDepartamento();
        } else if (event.getSource().equals(formCliente.lstDepartamento)) {
            loadProvincia();
        } else if (event.getSource().equals(formCliente.lstProvincia)) {
            loadDistrito();
        }
    }

    @Override
    public boolean isValidData() {
        if (formCliente.lstTipoDoc.getItemText(formCliente.lstTipoDoc.getSelectedIndex()).equalsIgnoreCase("RUC")) {
            if (!FieldVerifier.isValidRUC(formCliente.txtDocumento.getText())) {
                return false;
            }
        } else if (formCliente.lstTipoDoc.getItemText(formCliente.lstTipoDoc.getSelectedIndex()).equalsIgnoreCase("DNI")) {
            if (!FieldVerifier.isValidDNI(formCliente.txtDocumento.getText())) {
                return false;
            }
        }
        if (FieldVerifier.isEmpty(formCliente.txtNombres.getText())) {
            //Window.alert("Nombres es un campo obligatorio");
            Notification not = new Notification(Notification.ALERT, "Nombres es un campo obligatorio");
            not.showPopup();
            formCliente.txtNombres.setFocus(true);
            return false;
        } else if (FieldVerifier.isEmpty(formCliente.txtDireccion.getText())) {
            //Window.alert("Direccion es un campo obligatorio");
            Notification not = new Notification(Notification.ALERT, "Direccion es un campo obligatorio");
            not.showPopup();
            formCliente.txtDireccion.setFocus(true);
            return false;
        } /*else if(FieldVerifier.isEmpty(formCliente.txtEmail.getText())){
         Window.alert("Correo electronico es un campo obligatorio");
         formCliente.txtEmail.setFocus(true);
         return false;
         }*/

        return true;
    }

    @Override
    public void processInsertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cleanForm() {
        formCliente.txtNombres.setText(null);
        formCliente.txtDocumento.setText(null);
        formCliente.txtDireccion.setText(null);
        formCliente.txtTelefono.setText(null);
        formCliente.txtEmail.setText(null);
    }

    @Override
    public void goToUIvdEntrada(String docCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void loadTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public UIBuscarCliente getUiBuscarCliente() {
        return uiBuscarCliente;
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getSource().equals(uiBuscarCliente.txtBuscar.textBox)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                if (uiBuscarCliente.txtBuscar.textBox.getText().length() >= 3) {
                    loadTable();
                } else {
                    Notification not = new Notification(Notification.ALERT, "escriba minimo 3 caracteres");
                    not.showPopup();
                }
            }
        }
    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
        if (event.getSource().equals(uiBuscarCliente.btnGuardar)) {
            selectClient();
        }
    }

    @Override
    public void selectClient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
