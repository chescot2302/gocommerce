/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uidespacho;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.webgocommerce.client.beanproxy.CorrelativoProxy;
import com.webgocommerce.client.model.BorderLayout;
import com.webgocommerce.client.uiutil.UIInfoDoc;

/**
 *
 * @author SISTEMAS
 */
public class UIDespacho extends PopupPanel implements InterUIDespacho,ClickHandler,ChangeHandler,BlurHandler{
    private BorderLayout pnlContenedor;
    protected UIInfoDoc uiInfoDoc;    
    private Button btnContinuar;
    private Button btnCancelar;
    protected CorrelativoProxy beanCorrelativo;
    
    public UIDespacho(){
        initComponents();
        initListener();
    }
    
    private void initComponents(){
        pnlContenedor=new BorderLayout();
        uiInfoDoc = new UIInfoDoc();
        btnContinuar=new Button("Continuar");
        btnCancelar=new Button("Cancelar");
        pnlContenedor.add(BorderLayout.NORTE, uiInfoDoc);
        pnlContenedor.add(BorderLayout.SUR, btnContinuar);
        pnlContenedor.add(BorderLayout.SUR, btnCancelar);
        this.add(pnlContenedor);
        this.setGlassEnabled(true);
        this.setAnimationEnabled(true);
        this.setModal(true);
        this.setAutoHideEnabled(true); 
        this.setSize("380px", "70px");
        this.center();
    }
    
    private void initListener(){
        btnCancelar.addClickHandler(this);
        btnContinuar.addClickHandler(this);
        uiInfoDoc.lstTipoDoc.addChangeHandler(this);
        uiInfoDoc.lstTipoDoc.addBlurHandler(this);
        uiInfoDoc.lstSerieCorre.addChangeHandler(this);
        uiInfoDoc.lstSerieCorre.addBlurHandler(this);
    }

    @Override
    public void loadDocDespacho() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadCorrelativo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelarOperacion() {
        this.hide();
    }

    @Override
    public void onClick(ClickEvent event) {
        if(event.getSource().equals(btnCancelar)){
            cancelarOperacion();
        }else if(event.getSource().equals(btnContinuar)){
            nextStep();
        }
    }
    
    public void setBeanCorrelativo(CorrelativoProxy beanCorrelativo) {
        this.beanCorrelativo = beanCorrelativo;
    }

    @Override
    public void onChange(ChangeEvent event) {
       if (event.getSource().equals(uiInfoDoc.lstTipoDoc)) {
            selectTipoDoc();
        } else if (event.getSource().equals(uiInfoDoc.lstSerieCorre)) {
            selectCorrelativo();
        }
    }

    @Override
    public void onBlur(BlurEvent event) {
        if (event.getSource().equals(uiInfoDoc.lstTipoDoc)) {
            selectTipoDoc();
        } else if (event.getSource().equals(uiInfoDoc.lstSerieCorre)) {
            selectCorrelativo();
        }
    }
    
    @Override
    public void selectCorrelativo() {
        uiInfoDoc.txtPreImpreso.setText(uiInfoDoc.lstSerieCorre.getSelectedItem().getPreimpreso());
        beanCorrelativo = uiInfoDoc.lstSerieCorre.getSelectedItem();
    }
    
    @Override
    public void selectTipoDoc() {

    }
    
    @Override
    protected void onPreviewNativeEvent(Event.NativePreviewEvent event) {
        super.onPreviewNativeEvent(event);
        switch (event.getTypeInt()) {
            case Event.ONKEYDOWN:
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    hide();
                }
                break;
        }        
    }

    @Override
    public boolean isValidData() {
        if(beanCorrelativo!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void nextStep() {
        
    }
    
    
}
