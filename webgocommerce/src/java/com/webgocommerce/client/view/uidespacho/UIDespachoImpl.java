/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uidespacho;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.CorrelativoProxy;
import com.webgocommerce.client.beanproxy.DocumentoProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoCorrelativo;
import com.webgocommerce.client.requestfactory.ContextMantenimientoDocumento;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.view.uisesion.UISesion;
import com.webgocommerce.client.view.uivdentrada.UIvdEntradaImpl;
import com.webgocommerce.client.view.uivdsalida.UIvdSalidaImpl;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIDespachoImpl extends UIDespacho {

    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIvdEntradaImpl formParent;

    public UIDespachoImpl(UIvdEntradaImpl formParent) {
        this.formParent = formParent;
        loadDocDespacho();
    }

    @Override
    public void loadDocDespacho() {
        ContextMantenimientoDocumento context = FACTORY.contextMantenimientoDocumento();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<DocumentoProxy>> request = context.listaDocDespacho(keyPublic);
        request.fire(new Receiver<List<DocumentoProxy>>() {

            @Override
            public void onSuccess(List<DocumentoProxy> response) {
                if (response.size() > 0) {
                    uiInfoDoc.lstTipoDoc.setData(response);
                    loadCorrelativo();
                }
            }
        });
    }

    @Override
    public void loadCorrelativo() {
        ContextMantenimientoCorrelativo context = FACTORY.contextMantenimientoCorrelativo();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<CorrelativoProxy>> request = context.getCorrelativoActual(keyPublic, UISesion.beanUsuario.getId(), formParent.getBeanCorrelativo().getIdPuntoEmision(), uiInfoDoc.lstTipoDoc.getSelectedItem().getId(),"S");
        request.fire(new Receiver<List<CorrelativoProxy>>() {

            @Override
            public void onSuccess(List<CorrelativoProxy> response) {
                if (response.size() > 0) {
                    uiInfoDoc.lstSerieCorre.setData(response);
                    uiInfoDoc.txtPreImpreso.setText(response.get(0).getPreimpreso());
                    setBeanCorrelativo(response.get(0));
                } else {
                    uiInfoDoc.lstSerieCorre.clear();
                    uiInfoDoc.txtPreImpreso.setText(null);
                    beanCorrelativo = null;
                }
            }
        });
    }

    @Override
    public void selectTipoDoc() {
        loadCorrelativo();
    }

    @Override
    public void nextStep() {
        if (isValidData()) {
            formParent.setBeanCorreDespacho(beanCorrelativo);
            this.hide();
            UIvdSalidaImpl dialog = new UIvdSalidaImpl(formParent);
            dialog.show();
        }
    }

}
