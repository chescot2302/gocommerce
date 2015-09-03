/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uiselectuser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.webgocommerce.client.beanproxy.UsuarioProxy;
import com.webgocommerce.client.requestfactory.ContextMantenimientoUsuario;
import com.webgocommerce.client.requestfactory.FactoryGestion;
import com.webgocommerce.client.uiutil.Notification;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.view.uihomecorreuser.UIHomeCorreUser;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UISelectUserImpl extends UISelectUser{
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UIHomeCorreUser uiHomeCorreUser;        

    public UISelectUserImpl(UIHomeCorreUser uiHomeCorreUser) {
        this.uiHomeCorreUser=uiHomeCorreUser;
    }
    
    @Override
    public void loadTable() {
        popup.showPopup();
        ContextMantenimientoUsuario context = FACTORY.contextMantenimientoUsuario();
        FACTORY.initialize(EVENTBUS);
        String keyPublic = UISesion.keyPublic;
        Request<List<UsuarioProxy>> request = context.getListarUsuarioPto(keyPublic, idPuntoEmision,idDocumento,uiHomeCorreUser.getUiMantCorreUser().getTxtSerie().getText());
        request.fire(new Receiver<List<UsuarioProxy>>() {

            @Override
            public void onSuccess(List<UsuarioProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response); 
                grid.actualizarGrid();
                popup.hidePopup();
            }
            
            @Override
                public void onFailure(ServerFailure error) {
                    popup.hidePopup();
                    //Window.alert(error.getMessage());  
                    Notification not=new Notification(Notification.WARNING,error.getMessage());
                    not.showPopup();
                }
        });
    }
    
    @Override
    public void goToBack(){
        uiHomeCorreUser.getContainer().showWidget(1);        
    }
    
    @Override
    public void seleccionar() {
        UsuarioProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){	
            uiHomeCorreUser.getUiMantCorreUser().setBeanUsuario(bean);
            uiHomeCorreUser.getContainer().showWidget(1);
        }else{
            //Window.alert("Seleccione un registro de la tabla");
            Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
            not.showPopup();
        }
    }
}
