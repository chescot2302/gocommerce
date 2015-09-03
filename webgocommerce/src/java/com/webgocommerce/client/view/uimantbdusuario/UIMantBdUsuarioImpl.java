/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantbdusuario;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.webgocommerce.client.beanproxy.BdEmpresaProxy;
import com.webgocommerce.client.beanproxy.BdUsuarioProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoBdEmpresa;
import com.webgocommerce.client.requestfactory.ContextMantenimientoBdUsuario;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.service.ServiceGestionSeguridad;
import com.webgocommerce.client.service.ServiceGestionSeguridadAsync;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.view.uihomebdusuario.UIHomeBdUsuario;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UIMantBdUsuarioImpl extends UIMantBdUsuario{
    private final ServiceGestionSeguridadAsync servicio = GWT.create(ServiceGestionSeguridad.class);
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeBdUsuario uiHomeBdUsuario;
    
    public UIMantBdUsuarioImpl(UIHomeBdUsuario uiHomeBdUsuario) {  
        this.uiHomeBdUsuario=uiHomeBdUsuario;
        loadListBox();
    }
    
    @Override
    public void getPassword() {
        if(!txtEsquema.getValue().isEmpty() && !txtUsuarioLog.getValue().isEmpty()){
        String keyPublic = UISesion.keyPublic;        
        servicio.getPassword(keyPublic,txtEsquema.getValue(),txtUsuarioLog.getValue(),new AsyncCallback<String>(){
           
            @Override
            public void onSuccess(String result) {
                txtClaveLog.setText(result);
            }
            
            @Override
            public void onFailure(Throwable caught) {
                //Window.alert(caught.getMessage());
                Notification not=new Notification(Notification.WARNING,caught.getMessage());
                not.showPopup();
            }
            
        });
        }else{
            //Window.alert("Seleccione empresa y defina usuario antes de continuar");
            Notification not=new Notification(Notification.ALERT,"Seleccione empresa y </br>defina usuario antes de continuar");
            not.showPopup();
        }
    }
    
    @Override
    public void processInsertar() {
        Date fecha = new Date();
        ContextMantenimientoBdUsuario context = FACTORY.contextMantenimientoBdUsuario();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        BdUsuarioProxy bean = context.create(BdUsuarioProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");        
        bean.setIdBdEmpresa(lstEmpresa.getSelectedItem().getIdBdEmpresa());
        bean.setSchema(txtEsquema.getText());                
        bean.setNivel(lstNivel.getItemText(lstNivel.getSelectedIndex()));
        bean.setCorreo(txtUsuarioLog.getText());
        bean.setClave(txtClaveLog.getText());
        bean.setUsuarioBd(txtUsuarioBd.getText());
        bean.setClaveBd(txtClaveBd.getText());
        bean.setEstadoActivacion("A");
        Request<Boolean> request = context.insertarBdUsuario(bean, keyPublic);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                cleanForm();
                //Window.alert("Insertado correctamente");                
                Notification not=new Notification(Notification.INFORMATION,"Insertado correctamente");
                not.showPopup();
            }
        });
    }
    
    
    
    @Override
    public void loadListBox() {
        ContextMantenimientoBdEmpresa context = FACTORY.contextMantenimientoBdEmpresa();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<BdEmpresaProxy>> request = context.listar(keyPublic);
        request.fire(new Receiver<List<BdEmpresaProxy>>() {

            @Override
            public void onSuccess(List<BdEmpresaProxy> response) {
                lstEmpresa.setData(response);         
                txtEsquema.setText(response.get(0).getSchema());
            }
        });
    }
    
    @Override
    public void goToBack() {
        uiHomeBdUsuario.getContainer().showWidget(0);        
        uiHomeBdUsuario.getUiBdUsuario().loadTable();
    }
    
    @Override
    public void goToUIBdUsuario() {
        cleanForm();        
        uiHomeBdUsuario.getContainer().showWidget(0);
        uiHomeBdUsuario.getUiBdUsuario().loadTable();
    }
    
    @Override
    public void loadPwNava() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
