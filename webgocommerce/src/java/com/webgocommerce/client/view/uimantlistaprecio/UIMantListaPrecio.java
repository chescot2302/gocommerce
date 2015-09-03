/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uimantlistaprecio;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.webgocommerce.client.beanproxy.ListaPrecioProxy;
import com.webgocommerce.client.model.UIMantenimiento;
import static com.webgocommerce.client.model.UIMantenimiento.MODOINSERTAR;
import static com.webgocommerce.client.model.UIMantenimiento.MODOUPDATE;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.listmodel.ListModelCategoriaLista;
import com.webgocommerce.shared.FieldVerifier;

/**
 *
 * @author SISTEMAS
 */
public class UIMantListaPrecio extends UIMantenimiento implements KeyUpHandler, InterUIMantListaPrecio {
    protected ListBox lstCanal;
    protected TextBox txtId;
    protected TextBox txtDescripcion;
    protected ListaPrecioProxy bean;
    protected ListModelCategoriaLista lstCategoriaLista;
    protected ListBox lstCondicion;
    protected TextBox txtPago;
    protected TextBox txtVigencia;

    public UIMantListaPrecio() {
        initComponents();
        initStyle();
        initListener();
        reCalcularWindows();
    }

    private void initComponents() {
        lstCanal=new ListBox();
        lstCanal.addItem("CANAL PERSONAS Y EMPRESAS");        
        lstCanal.addItem("CANAL PERSONAS");
        lstCanal.addItem("CANAL EMPRESAS");
        txtId = new TextBox();
        lstCategoriaLista = new ListModelCategoriaLista();
        txtDescripcion = new TextBox();
        txtDescripcion.getElement().setAttribute("required", "required");
        lstCondicion = new ListBox();
        lstCondicion.addItem("POSTPAGO");
        lstCondicion.addItem("PREPAGO");
        txtPago = new TextBox();
        txtPago.getElement().setAttribute("required", "required");
        txtVigencia = new TextBox();
        txtVigencia.getElement().setAttribute("required", "required");
        //txtVigencia.setText("18");
        //this.addWidget("ID", txtId);
        this.addWidget("CANAL (*)", lstCanal);
        this.addWidget("CATEGORIA (*)", lstCategoriaLista);
        this.addWidget("DESCRIPCION (*)", txtDescripcion);
        this.addWidget("CONDICION (*)", lstCondicion);
        this.addWidget("PAGO MENSUAL (*)", txtPago);
        this.addWidget("VIGENCIA - # MESES (*)", txtVigencia);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }

    private void initStyle() {
        lstCanal.setWidth("100%");
        lstCategoriaLista.setWidth("100%");
        lstCondicion.setWidth("100%");
    }

    private void initListener() {
        txtDescripcion.addKeyUpHandler(this);
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 220;
        this.scrollPanel.setHeight(alto + "px");
    }

    @Override
    public void loadFields() {
        if (this.modo.equals(UIMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getId().toString());
            lstCategoriaLista.setSelectedItem(this.bean.getBeanCategoriaLista().getDescripcion());
            lstCondicion.setSelectedIndex(this.bean.getCondicion().equals("POSTPAGO") ? 0 : this.bean.getCondicion().equals("PREPAGO") ? 1 : -1);
            txtDescripcion.setText(this.bean.getDescripcion());
            txtPago.setText(this.bean.getPagoMensual().toString());
            txtVigencia.setText(this.bean.getVigencia().toString());
            txtId.setEnabled(false);
            lstCategoriaLista.setEnabled(true);
            lstCategoriaLista.setFocus(true);
            txtDescripcion.setEnabled(true);
            lstCondicion.setEnabled(true);
            txtPago.setEnabled(true);
            txtVigencia.setEnabled(true);
            lstCanal.setSelectedIndex(this.bean.getCanal()==null?-1:this.bean.getCanal().equalsIgnoreCase("CANAL PERSONAS Y EMPRESAS")?0:this.bean.getCanal().equalsIgnoreCase("CANAL PERSONAS")?1:this.bean.getCanal().equalsIgnoreCase("CANAL EMPRESAS")?2:-1);
            lstCanal.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getId().toString());
            lstCategoriaLista.setSelectedItem(this.bean.getBeanCategoriaLista().getDescripcion());
            lstCondicion.setSelectedIndex(this.bean.getCondicion().equals("POSTPAGO") ? 0 : this.bean.getCondicion().equals("PREPAGO") ? 1 : -1);
            txtDescripcion.setText(this.bean.getDescripcion());
            txtPago.setText(this.bean.getPagoMensual().toString());
            txtVigencia.setText(this.bean.getVigencia().toString());
            txtId.setEnabled(false);
            lstCategoriaLista.setEnabled(false);
            txtDescripcion.setEnabled(false);
            lstCondicion.setEnabled(false);
            txtPago.setEnabled(false);
            txtVigencia.setEnabled(false);
            lstCanal.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UIMantenimiento.MODODETALLE) || this.modo.equals("DESACTIVAR")) {
            txtId.setText(this.bean.getId().toString());
            lstCategoriaLista.setSelectedItem(this.bean.getBeanCategoriaLista().getDescripcion());
            lstCondicion.setSelectedIndex(this.bean.getCondicion().equals("POSTPAGO") ? 0 : this.bean.getCondicion().equals("PREPAGO") ? 1 : -1);
            txtDescripcion.setText(this.bean.getDescripcion());
            txtPago.setText(this.bean.getPagoMensual().toString());
            txtVigencia.setText(this.bean.getVigencia().toString());
            txtId.setEnabled(false);
            lstCategoriaLista.setEnabled(false);
            txtDescripcion.setEnabled(false);
            lstCondicion.setEnabled(false);
            txtPago.setEnabled(false);
            txtVigencia.setEnabled(false);
            lstCanal.setEnabled(false);
            this.btnOperacion.setDisabled(this.modo.equals(UIMantenimiento.MODODETALLE) ? true : false);
        } else {
            txtId.setEnabled(false);
            lstCategoriaLista.setEnabled(true);
            lstCategoriaLista.setFocus(true);
            txtDescripcion.setEnabled(true);
            lstCondicion.setEnabled(true);
            txtPago.setEnabled(true);
            txtVigencia.setEnabled(true);
            lstCanal.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        }
    }

    public void setBean(ListaPrecioProxy bean) {
        this.bean = bean;
    }

    @Override
    public void cleanForm() {
        txtId.setText(null);
        txtDescripcion.setText(null);
        txtPago.setText(null);
        txtVigencia.setText(null);
        txtDescripcion.setFocus(true);
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getSource().equals(txtVigencia)) {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                if (modo.equalsIgnoreCase(MODOINSERTAR)) {
                        processInsertar();
                } else if (modo.equalsIgnoreCase(MODOUPDATE)) {
                    processActualizar();
                } else {
                    //Window.alert("Operacion no contemplada");
                    Notification not=new Notification(Notification.ALERT,"Operacion no contemplada");
                    not.showPopup();
                }
            }
        }
    }

    @Override
    public void goToUIListaPrecio() {
        cleanForm();
    }

    @Override
    public void loadListBox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processDesactivar() {

    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
        if (event.getSource().equals(btnOperacion)) {
            if (modo.equalsIgnoreCase(MODOINSERTAR)) {
                    processInsertar();
            } else if (modo.equalsIgnoreCase(MODOUPDATE)) {
                processActualizar();
            } else if (modo.equalsIgnoreCase(MODODELETE)) {
                processEliminar();
            } else if (modo.equalsIgnoreCase("DESACTIVAR")) {
                processDesactivar();
            } else {
                //Window.alert("Operación no contemplada");
                Notification not=new Notification(Notification.ALERT,"Operacion no contemplada");
                not.showPopup();
            }
        }
    }

    @Override
    public boolean isValidData() {
        if (FieldVerifier.isEmpty(txtDescripcion.getText())) {
            //Window.alert("Descripcion es un campo obligatorio");
            Notification not=new Notification(Notification.ALERT,"Descripcion es un campo obligatorio");
            not.showPopup();
            txtDescripcion.setFocus(true);
            return false;
        } else if (FieldVerifier.notIsNumberPositivo(txtPago.getText(),"Pago")) {            
            txtPago.setText(null);
            txtPago.setFocus(true);
            return false;
        } else if (FieldVerifier.notIsEnteroPositivo(txtVigencia.getText(),"Vigencia")) {            
            txtVigencia.setText(null);
            txtVigencia.setFocus(true);
            return false;
        }
        return true;
    }
}
